
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration failed</title>
</head>
<body>
    <h1>Registration failed</h1>
    <i><%=((Exception)session.getAttribute("error")).getMessage()%></i>
    <a href="registration_menu.jsp">Registration</a>
    <a href="index.jsp">to main</a>
</body>
</html>
