<%@ page import="god.help.me.model.User" %>
<%@ page import="god.help.me.model.Club" %>
<%@ page import="god.help.me.ClubManager" %><%--
  Created by IntelliJ IDEA.
  User: user
  Date: 05.05.2020
  Time: 18:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    User user = (User) session.getAttribute("user");
    ClubManager clubManager = ClubManager.getInstance();
    Club myClub = clubManager.myClub(user);
%>
<html>
<head>
    <title>Logined <%=user.getLogin()%></title>
</head>
<body>
<a href="<%=myClub!=null ? "club_menu.jsp" : "create_club_menu.jsp"%>">Club Create/Manage</a>
<br><br>
<a href="index.jsp">logout</a>
</body>
</html>
