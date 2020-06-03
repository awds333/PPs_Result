<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 27.05.2020
  Time: 2:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create Club</title>
</head>
<body>
<h1>Create Your Club!</h1>
<form action="${pageContext.request.contextPath}/createclub" method="post">
    Club name:    <input type="text" id="name" name="name" required minlength="3" maxlength="12" size="10">
    <br><br>
    <input type="submit" value="Register">
</form>
<a href="usermenu.jsp">back</a>
</body>
</html>
