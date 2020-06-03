<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 05.05.2020
  Time: 17:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
    <h1>Registration</h1>
    <form action="${pageContext.request.contextPath}/registration" method="post">
        Login:    <input type="text" id="login" name="login" required minlength="3" maxlength="12" size="10">
        <br><br>
        Password: <input type="password" id="password" name="password" required minlength="6" maxlength="18" size="10">
        <br><br>
        <input type="submit" value="Register">
    </form>
    <a href="index.jsp">back</a>
</body>
</html>
