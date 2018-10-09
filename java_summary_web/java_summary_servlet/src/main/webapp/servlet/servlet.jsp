<%@page import="org.springframework.http.HttpRequest" %>
<%@page import="java.util.Map.Entry" %>
<%@page import="java.util.Set" %>
<%@page import="java.util.Iterator" %>
<%@page import="java.util.Map" %>
<%@page import="java.util.Enumeration" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
</head>
<body>
<h2>jsp的九个内置对象</h2>
<h3>1.request</h3>
<p>用于存储由客户端的请求信息，通常指由http协议传输至服务端的数据。</p>
<%
    out.print("test request Attribute</br>");
    Enumeration<String> attributeNames = request.getAttributeNames();
    while (attributeNames.hasMoreElements()) {
        String name = attributeNames.nextElement();
        out.print(name + " : " + request.getAttribute(name) + "</br>");
    }
    out.print("test request Parameter</br>");
    Map<String, String[]> paramterMap = request.getParameterMap();
    Set<Entry<String, String[]>> set = paramterMap.entrySet();
    for (Entry<String, String[]> entry : set) {
        System.out.print(entry.getKey() + " : ");
        for (String str : entry.getValue()) {
            System.out.print(str + "\t");
        }
        System.out.println();
    }

%>

<h3>2.response</h3>
<p>代表服务端的响应信息，将jsp容器处理的结果返回值客户端。</p>
<%

%>
<h3>3.session</h3>
<p>是服务器产生的与用户请求相关的对象，用于存储用户信息，以及跟踪用户的状态。服务器会为每个用户都生成的一个session。</p>
<%
    out.print("test request Parameter</br>");
    Enumeration<String> sessionAttributeNames = session.getAttributeNames();
    while (sessionAttributeNames.hasMoreElements()) {
        String name = sessionAttributeNames.nextElement();
        out.print(name + " : " + session.getAttribute(name) + "</br>");
    }
%>

<h3>4.page</h3>
<p>表示jsp转换后的servlet本身，类似于this关键字</p>
<%

%>

<h3>5.pageContext</h3>
<p>本JSP的页面上下文，可以通过pageContext获取到当前页面的所有信息。</p>
<%
    HttpServletRequest req = (HttpServletRequest) pageContext.getRequest();
    out.print("内置request=pageContext.getRequest()：" + (req == request) + "</br>");
%>

<h3>6.config</h3>
<p>主要作用是取得服务器的配置信息。</p>
<%
    out.print("服务器配置参数</br>");
    Enumeration<String> initParameterNames = config.getInitParameterNames();
    while (initParameterNames.hasMoreElements()) {
        String name = initParameterNames.nextElement();
        out.print(name + " : " + config.getInitParameter(name) + "</br>");
    }
%>

<h3>7.application</h3>
<p>application
    对象可将信息保存在服务器中，直到服务器关闭，否则application对象中保存的信息会在整个应用中都有效。与session对象相比，application对象生命周期更长，类似于系统的“全局变量”。</p>
<%
    /* out.print("application.getAttributeNames()</br>");
    Enumeration<String> applicationAttributeNames = application.getAttributeNames();
    while (applicationAttributeNames.hasMoreElements()) {
        String name = applicationAttributeNames.nextElement();
        out.print(name + " : " + application.getAttribute(name) + "</br>");
    } */
    out.print("application.getAttributeNames()</br>");
    Enumeration<String> applicationInitParameterNames = application.getInitParameterNames();
    while (applicationInitParameterNames.hasMoreElements()) {
        String name = applicationInitParameterNames.nextElement();
        out.print(name + " : " + application.getInitParameter(name) + "</br>");
    }
%>

<h3>8.out</h3>
<%
    out.print("<p>用于在页面中输出信息，并且管理服务器的输出缓冲区。在使用 out 对象输出数据时，可以对数据缓冲区进行操作，及时清除缓冲区中的残余数据，为其他的输出让出缓冲空间。待数据输出完毕后，要及时关闭输出流。</p>");
%>

<h3>8.exception</h3>
<p>exception 对象的作用是显示异常信息，只有在包含 isErrorPage="true"
    的页面中才可以被使用，在一般的JSP页面中使用该对象将无法编译JSP文件。excepation对象和Java的所有对象一样，都具有系统提供的继承结构。exception
    对象几乎定义了所有异常情况。在Java程序中，可以使用try/catch关键字来处理异常情况； 如果在JSP页面中出现没有捕获到的异常，就会生成 exception 对象，并把 exception
    对象传送到在page指令中设定的错误页面中，然后在错误页面中处理相应的 exception 对象。</p>
</body>
</html>