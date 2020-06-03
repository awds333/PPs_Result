<%@ page import="god.help.me.model.User" %><%--
  Created by IntelliJ IDEA.
  User: user
  Date: 05.05.2020
  Time: 17:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User created</title>
</head>
<body>
    <h1>User <%=((User)session.getAttribute("user")).getLogin()%> created!</h1>
    <a href="index.jsp">to main</a>
    <a href="login_menu.jsp">login</a>
</body>
</html>
