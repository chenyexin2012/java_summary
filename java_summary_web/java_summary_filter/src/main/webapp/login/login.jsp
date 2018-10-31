<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/8/8
  Time: 16:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<h3 style="color:red;">${msg}</h3>
<form action="/login.do" method="post">
    用户名：<input type="text" id="name" name="name"><br><br>
    密码：<input type="password" id="pwd" name="pwd"><br><br>
    信息：<input type="text" id="message" name="message"><br><br>
    <input type="checkbox" name="autoLogin" value="true">自动登录<br>
    <input type="submit" value="登录">
    <input type="reset" value="重置">
</form>
</body>
</html>
