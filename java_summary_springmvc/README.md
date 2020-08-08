#### Spring MVC DispatcherServlet 初始化流程

在DispatcherServlet进行初始化过程代码如下：

    HttpServletBean.init()
    
	public final void init() throws ServletException {
		if (logger.isDebugEnabled()) {
			logger.debug("Initializing servlet '" + getServletName() + "'");
		}

		// Set bean properties from init parameters.
		PropertyValues pvs = new ServletConfigPropertyValues(getServletConfig(), this.requiredProperties);
		if (!pvs.isEmpty()) {
			try {
				BeanWrapper bw = PropertyAccessorFactory.forBeanPropertyAccess(this);
				ResourceLoader resourceLoader = new ServletContextResourceLoader(getServletContext());
				bw.registerCustomEditor(Resource.class, new ResourceEditor(resourceLoader, getEnvironment()));
				initBeanWrapper(bw);
				bw.setPropertyValues(pvs, true);
			}
			catch (BeansException ex) {
				if (logger.isErrorEnabled()) {
					logger.error("Failed to set bean properties on servlet '" + getServletName() + "'", ex);
				}
				throw ex;
			}
		}

		// Let subclasses do whatever initialization they like.
		// 此处实际调用的 FrameworkServlet.initServletBean()
		initServletBean();

		if (logger.isDebugEnabled()) {
			logger.debug("Servlet '" + getServletName() + "' configured successfully");
		}
	}
	
	FrameworkServlet.initServletBean() -> initWebApplicationContext() -> 
	
    @Override
    protected final void initServletBean() throws ServletException {
        getServletContext().log("Initializing Spring FrameworkServlet '" + getServletName() + "'");
        if (this.logger.isInfoEnabled()) {
            this.logger.info("FrameworkServlet '" + getServletName() + "': initialization started");
        }
        long startTime = System.currentTimeMillis();

        try {
            // 初始化一个Context对象
            this.webApplicationContext = initWebApplicationContext();
            initFrameworkServlet();
        }
        catch (ServletException | RuntimeException ex) {
            this.logger.error("Context initialization failed", ex);
            throw ex;
        }

        if (this.logger.isInfoEnabled()) {
            long elapsedTime = System.currentTimeMillis() - startTime;
            this.logger.info("FrameworkServlet '" + getServletName() + "': initialization completed in " +
                    elapsedTime + " ms");
        }
    }
    
    protected WebApplicationContext initWebApplicationContext() {
        WebApplicationContext rootContext =
                WebApplicationContextUtils.getWebApplicationContext(getServletContext());
        WebApplicationContext wac = null;

        if (this.webApplicationContext != null) {
            // A context instance was injected at construction time -> use it
            wac = this.webApplicationContext;
            if (wac instanceof ConfigurableWebApplicationContext) {
                ConfigurableWebApplicationContext cwac = (ConfigurableWebApplicationContext) wac;
                if (!cwac.isActive()) {
                    // The context has not yet been refreshed -> provide services such as
                    // setting the parent context, setting the application context id, etc
                    if (cwac.getParent() == null) {
                        // The context instance was injected without an explicit parent -> set
                        // the root application context (if any; may be null) as the parent
                        cwac.setParent(rootContext);
                    }
                    configureAndRefreshWebApplicationContext(cwac);
                }
            }
        }
        if (wac == null) {
            // No context instance was injected at construction time -> see if one
            // has been registered in the servlet context. If one exists, it is assumed
            // that the parent context (if any) has already been set and that the
            // user has performed any initialization such as setting the context id
            wac = findWebApplicationContext();
        }
        if (wac == null) {
            // No context instance is defined for this servlet -> create a local one
            wac = createWebApplicationContext(rootContext);
        }

        if (!this.refreshEventReceived) {
            // Either the context is not a ConfigurableApplicationContext with refresh
            // support or the context injected at construction time had already been
            // refreshed -> trigger initial onRefresh manually here.
            onRefresh(wac);
        }

        if (this.publishContext) {
            // Publish the context as a servlet context attribute.
            String attrName = getServletContextAttributeName();
            getServletContext().setAttribute(attrName, wac);
            if (this.logger.isDebugEnabled()) {
                this.logger.debug("Published WebApplicationContext of servlet '" + getServletName() +
                        "' as ServletContext attribute with name [" + attrName + "]");
            }
        }

        return wac;
    }
    
    DispatcherServlet.onRefresh() -> initStrategies()
    @Override
    protected void onRefresh(ApplicationContext context) {
        initStrategies(context);
    }
    
    // 初始化九个重要的组件
    protected void initStrategies(ApplicationContext context) {
        initMultipartResolver(context);
        initLocaleResolver(context);
        initThemeResolver(context);
        initHandlerMappings(context);
        initHandlerAdapters(context);
        initHandlerExceptionResolvers(context);
        initRequestToViewNameTranslator(context);
        initViewResolvers(context);
        initFlashMapManager(context);
    }
    
    
##### 九大组件介绍

1. MultipartResolver

用于处理上传请求。将普通的请求包装成 MultipartHttpServletRequest，而MultipartHttpServletRequest可以直接通过getFile方法获取文件信息。

	boolean isMultipart(HttpServletRequest request);

    // 将普通的请求包装成 MultipartHttpServletRequest
	MultipartHttpServletRequest resolveMultipart(HttpServletRequest request) throws MultipartException;
    
	void cleanupMultipart(MultipartHttpServletRequest request);
	
2. LocaleResolver


    // 从request信息中解析 Locale
    Locale resolveLocale(HttpServletRequest request);

    // 设置 Locale
    void setLocale(HttpServletRequest request, @Nullable HttpServletResponse response, @Nullable Locale locale);

3. ThemeResolver

用于解析主题。

	String resolveThemeName(HttpServletRequest request);

	void setThemeName(HttpServletRequest request, @Nullable HttpServletResponse response, @Nullable String themeName);
    
4. HandlerMapping：

在Spring MVC中每个请求都对应着一个Handler，HandlerMapping的作用就是为每个请求返回对应的Handler对象。
   
HandlerMapping接口中提供了一个方法：
    
    HandlerExecutionChain getHandler(HttpServletRequest request) throws Exception;
    
这个方法主要为对应的请求返回一个HandlerExecutionChain，一个HandlerExecutionChain的对象中包含了一个Handler和一组Interceptor。

5. HandlerAdapter

这是一个适配器。

    // 是否支持指定的handler
    boolean supports(Object handler);

    // 使用handler处理请求
    @Nullable
    ModelAndView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception;

    // 返回上次修改时间，可以返回-1表示不支持
    long getLastModified(HttpServletRequest request, Object handler);

6. HandlerExceptionResolver

用于对异常情况的处理

    @Nullable
    ModelAndView resolveException(
            HttpServletRequest request, HttpServletResponse response, @Nullable Object handler, Exception ex);


7. RequestToViewNameTranslator

ViewResolver通过viewName来找到对应的视图，但是某些时候handler对请求处理完成之后并没有设置viewName，这时候就可以通过RequestToViewNameTranslator从request信息中去获取视图。

    @Nullable
	String getViewName(HttpServletRequest request) throws Exception;

8. ViewResolver

用来将String类型的视图名和Locale解析为View类型的视图。

	@Nullable
	View resolveViewName(String viewName, Locale locale) throws Exception;

9. FlashMapManager


	@Nullable
	FlashMap retrieveAndUpdate(HttpServletRequest request, HttpServletResponse response);

	void saveOutputFlashMap(FlashMap flashMap, HttpServletRequest request, HttpServletResponse response);



#### SpringMVC请求参数接收处理过程

在SpringMVC中，处理Controller中方法请求参数的基础接口为HandlerMethodArgumentResolver，定义如下：

    package org.springframework.web.method.support;
    
    import org.springframework.core.MethodParameter;
    import org.springframework.lang.Nullable;
    import org.springframework.web.bind.WebDataBinder;
    import org.springframework.web.bind.support.WebDataBinderFactory;
    import org.springframework.web.context.request.NativeWebRequest;
    
    public interface HandlerMethodArgumentResolver {
    
    	boolean supportsParameter(MethodParameter parameter);

    	@Nullable
    	Object resolveArgument(MethodParameter parameter, @Nullable ModelAndViewContainer mavContainer,
    			NativeWebRequest webRequest, @Nullable WebDataBinderFactory binderFactory) throws Exception;
    
    }
    
其常见的实现类有下列几种：

1. RequestParamMethodArgumentResolver：用于处理一些简单类型（String, int/Integer等）参数、@RequestParam 注解的参数，MultipartFile参数。

2. RequestResponseBodyMethodProcessor：用于处理 @RequestBody 注解的参数。

3. PathVariableMapMethodArgumentResolver：用于处理 @PathVariable 注解的参数。


处理返回参数的基础接口为HandlerMethodReturnValueHandler，定义如下：

    package org.springframework.web.method.support;
    
    import org.springframework.core.MethodParameter;
    import org.springframework.lang.Nullable;
    import org.springframework.web.context.request.NativeWebRequest;

    public interface HandlerMethodReturnValueHandler {

        boolean supportsReturnType(MethodParameter returnType);

        void handleReturnValue(@Nullable Object returnValue, MethodParameter returnType,
                ModelAndViewContainer mavContainer, NativeWebRequest webRequest) throws Exception;
    
    }

HandlerMethodArgumentResolver、HandlerMethodReturnValueHandler在实际使用中是通过HandlerMethodArgumentResolverComposite和HandlerMethodReturnValueHandlerComposite
来完成的。HandlerMethodArgumentResolverComposite和HandlerMethodReturnValueHandlerComposite分别包含了ArgumentResolver和ReturnValueHandler的列表。通过遍历列表找到对应的
解析器进行处理。

而不管是ArgumentResolver，还是ReturnValueHandler最终都会用到HttpMessageConverter消息转换器，HttpMessageConverter接口定义如下：


    package org.springframework.http.converter;
    
    import java.io.IOException;
    import java.util.List;
    
    import org.springframework.http.HttpInputMessage;
    import org.springframework.http.HttpOutputMessage;
    import org.springframework.http.MediaType;
    import org.springframework.lang.Nullable;
    
    public interface HttpMessageConverter<T> {
    
        boolean canRead(Class<?> clazz, @Nullable MediaType mediaType);
    
        boolean canWrite(Class<?> clazz, @Nullable MediaType mediaType);
    
        List<MediaType> getSupportedMediaTypes();
    
        // 转换 request 的请求数据
        T read(Class<? extends T> clazz, HttpInputMessage inputMessage)
                throws IOException, HttpMessageNotReadableException;
                
        // 转换 response 的响应信息
        void write(T t, @Nullable MediaType contentType, HttpOutputMessage outputMessage)
                throws IOException, HttpMessageNotWritableException;
    }

用于处理 @ResponseBody 和 @RequestBody 标注的方法或参数。常见的消息转换器有如下几种：

1. MappingJackson2HttpMessageConverter：通常用于处理 JSON 类型的请求或返回参数，此时ContentType类型为 application/json。

2. StringHttpMessageConverter: 通常用于处理 String 类型的请求或返回参数，此时ContentType类型为 text/plain。


在ArgumentResolver中，通常通过ContentType来决定使用何种消息转换器，参考AbstractMessageConverterMethodArgumentResolver.readWithMessageConverters()方法的部分代码：

    protected <T> Object readWithMessageConverters(HttpInputMessage inputMessage, MethodParameter parameter,
            Type targetType) throws IOException, HttpMediaTypeNotSupportedException, HttpMessageNotReadableException {

        MediaType contentType;
        boolean noContentType = false;
        try {
            // 从请求头中获取 ContentType
            contentType = inputMessage.getHeaders().getContentType();
        }
        catch (InvalidMediaTypeException ex) {
            throw new HttpMediaTypeNotSupportedException(ex.getMessage());
        }
        // 代码省略...

        EmptyBodyCheckingHttpInputMessage message;
        try {
            message = new EmptyBodyCheckingHttpInputMessage(inputMessage);
            // 遍历消息转换器列表，找到对应的可以处理请求参数的消息转换器
            for (HttpMessageConverter<?> converter : this.messageConverters) {
                Class<HttpMessageConverter<?>> converterType = (Class<HttpMessageConverter<?>>) converter.getClass();
                GenericHttpMessageConverter<?> genericConverter =
                        (converter instanceof GenericHttpMessageConverter ? (GenericHttpMessageConverter<?>) converter : null);
                if (genericConverter != null ? genericConverter.canRead(targetType, contextClass, contentType) :
                        (targetClass != null && converter.canRead(targetClass, contentType))) {
                    if (message.hasBody()) {
                        HttpInputMessage msgToUse =
                                getAdvice().beforeBodyRead(message, parameter, targetType, converterType);
                        body = (genericConverter != null ? genericConverter.read(targetType, contextClass, msgToUse) :
                                ((HttpMessageConverter<T>) converter).read(targetClass, msgToUse));
                        body = getAdvice().afterBodyRead(body, msgToUse, parameter, targetType, converterType);
                    }
                    else {
                        body = getAdvice().handleEmptyBody(null, message, parameter, targetType, converterType);
                    }
                    break;
                }
            }
        }
        catch (IOException ex) {
            throw new HttpMessageNotReadableException("I/O error while reading input message", ex, inputMessage);
        }

        // 代码省略...
        
        return body;
    }












































