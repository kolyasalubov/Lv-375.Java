<%--
  Created by IntelliJ IDEA.
  User: Сява
  Date: 17.01.2019
  Time: 18:36
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;">
    <meta charset="utf-8">
    <title>Insert title here</title>
</head>
<body>
<div id="userform">
    <form action="${pageContext.request.contextPath}/userupdate" method="post">
        <label for="username">Username:</label>
        <input type="text" id="username" name="username" value="${pageContext.request.getAttribute("username")}">
        <label for="password">Password:</label>
        <input type="text" id="password" name="password" value="${pageContext.request.getAttribute("password")}">
        <label for="email">Email:</label>
        <input type="text" id="email" name="email" value="${pageContext.request.getAttribute("email")}">
        <input type="hidden" id="isAdmin" name="isAdmin" value="${pageContext.request.getAttribute("isAdmin")}">
        <input type="hidden" id="isBlock" name="isBlock" value="${pageContext.request.getAttribute("isBlock")}">


        <div id="lower">
            <input type="submit" value="OK">
        </div><!--/ lower-->
    </form>


</div>
</body>
</html>
