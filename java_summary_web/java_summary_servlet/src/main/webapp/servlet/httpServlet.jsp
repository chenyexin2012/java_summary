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
    <title>Test HttpServlet</title>
</head>
<body>
<h3>Hello ${name}</h3>
<%
    out.print("test request Attribute</br>");
    Enumeration<String> attributeNames = request.getAttributeNames();
    while (attributeNames.hasMoreElements()) {
        String name = attributeNames.nextElement();
        out.print(name + " : " + request.getAttribute(name) + "</br>");
    }
%>
</body>
</html>