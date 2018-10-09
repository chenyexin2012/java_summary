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
<form action="/login" method="post"><!-- action写要请求Servlet的名 -->
    用户名：<input type="text" id="uname" name="uname"><br><br>
    密码：<input type="password" id="pwd" name="pwd"><br><br>
    <input type="submit" value="登录">
    <input type="reset" value="重置">
</form>
</body>
</html>
