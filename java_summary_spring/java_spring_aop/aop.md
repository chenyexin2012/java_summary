### AOP的通知类型
| 类型 | 接口 | 执行点 |
|:-------|:-------------|:----------|
Before|MethodBeforeAdvice|在接合点之前执行通知
After Returning|AfterReturningAdvice|在接合点执行返回之后执行通知
After Throwing|ThrowsAdvice|接合点抛出异常时执行通知
After（finally）|N/A|接合执行完成，不管是否抛出异常，都会执行同时
Around|N/A|在接合点周围执行通知，即可在执行之前，亦可在执行之后

1 使用After Throwing通知时，需要实现ThrowsAdvice接口，但是在ThrowsAdvice接口中未定义任何方法，因为它仅仅是作为一个标记接口来使用的。
实现该接口时，应当在通知中实现下面几个方法中的其中一个，实现的方法将会被反射调用。

     public void afterThrowing(Exception ex)
     public void afterThrowing(RemoteException)
     public void afterThrowing(Method method, Object[] args, Object target, Exception ex)
     public void afterThrowing(Method method, Object[] args, Object target, ServletException ex)
     
当afterThrowing方法中出现异常时，该异常会覆盖目标方法的异常。

2 After和Around需要使用aop:config中的aspect标签进行配置，且对于Before、After Returning、After Throwing在aspect标签中也有相应的
配置。
    
    <aop:config>
        <aop:aspect ref="operator">
            <aop:pointcut id="calculatorMethod" expression="execution(* com.holmes.spring.aop.Calculator.*(..))"/>
            <aop:before method="beforeMethod" pointcut-ref="calculatorMethod"/>
            <aop:after-returning method="afterReturning" pointcut-ref="calculatorMethod"/>
            <aop:after-throwing method="afterThrowing" pointcut-ref="calculatorMethod"/>
            <aop:after method="afterMethod" pointcut-ref="calculatorMethod"/>
            <aop:around method="aroundMethod" pointcut-ref="calculatorMethod"/>
        </aop:aspect>
    </aop:config>
     
并且在Around通知中，可以传入ProceedingJoinPoint实例，其它几种通知只能传入JoinPoint实例，如：

    public Object aroundMethod(ProceedingJoinPoint joinPoint)
    
    public void afterMethod(JoinPoint point) 
     

### AOP的执行顺序

1 实现org.springframework.core.Ordered接口
  注解@Order(1)
  配置文件 <aop:aspect order="0"> 
  设置一个整数，值越小，执行范围广，即对于Before通知来说order值越小则优先级越高，对于After和Throwing通知来说order值越小优先级越低。
    
2 order值相同时，配置在前，执行范围广，注解随后。


     