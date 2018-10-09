<%@ page import="com.java.summary.web.listener.example.MyHttpSessionBindingListenerBean" %>
<%@ page import="java.io.ByteArrayInputStream" %>
<%@ page import="java.io.ByteArrayOutputStream" %>
<%@ page import="com.java.summary.web.listener.example.MyHttpSessionActivationListenerBean" %>
<%@ page import="java.io.ObjectOutputStream" %>
<%@ page import="java.io.ObjectInputStream" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<html>
<body>
<h3>当前在线人数为: <%=application.getAttribute("currentUserNumber")%>
</h3>
<%

    System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>");
    //将对象绑定至session
    session.setAttribute("name", new MyHttpSessionBindingListenerBean("Shelock", "Holmes"));
    //将对象与session解除绑定
    session.removeAttribute("name");

    System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>");
    MyHttpSessionActivationListenerBean obj = new MyHttpSessionActivationListenerBean("Shelock", "Holmes");
    session.setAttribute("obj", obj);
%>

</body>
</html>
