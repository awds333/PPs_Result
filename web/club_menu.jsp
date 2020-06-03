<%@ page import="god.help.me.model.Club" %>
<%@ page import="god.help.me.ClubManager" %>
<%@ page import="god.help.me.model.User" %>
<%@ page import="god.help.me.model.Session" %><%--
  Created by IntelliJ IDEA.
  User: user
  Date: 27.05.2020
  Time: 2:16
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
    <title><%=myClub.getName()%>
    </title>
</head>
<body>
<h1><%=myClub.getName()%> Menu!</h1>
<div>
    <h2>Moderators:</h2>
    <br>
    <%
        for (User moderator : myClub.getModerators()) {
            out.write("<h3>" + moderator.getLogin() + "</h3>");
            out.write("<br>");
        }
    %>
    <form action="${pageContext.request.contextPath}/add_moderator" method="post">
        Login: <input type="text" id="login" name="login" required minlength="3" maxlength="12" size="10">
        <br><br>
        <input type="submit" value="Add Moderator">
    </form>
</div>
<div>
    <h2>Sessions:</h2>
    <form action="${pageContext.request.contextPath}/session">
        <%
            for (Session s : myClub.getSessions()) {
                out.write("<input type=\"submit\" value=\"" + s.getName() + "\" name=\"name\">");
                out.write("<br>");
            }
        %>
    </form>
</div>
<a href="usermenu.jsp">Back to user menu</a>
</body>
</html>
