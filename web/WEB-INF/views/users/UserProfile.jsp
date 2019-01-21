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
    <style type="text/css">
        <%@include file="../../../resources/css/UsersPage.css" %>
    </style>
    <meta http-equiv="Content-Type" content="text/html;">
    <meta charset="utf-8">
    <title>User Profile</title>
</head>
<body>
<div class="one"><h1>Edit Profile</h1></div>

<div id="userform">
    <c:if test="${error ne null}">
        <p>
            <font color="red">${error}</font>
        </p>
    </c:if>
    <form action="${pageContext.request.contextPath}/userupdate" method="post">
        <label for="username">Username:</label>
        <input type="text" id="username" name="username" style="background: #f0f0f0" value="${userDto.getUserName()}" readonly>
        <label for="email">Email:</label>
        <input type="text" id="email" name="email" value="${userDto.getEmail()}" required>
        <label for="password">Password:</label>
        <input type="password" id="password" name="password">
        <label for="repeat">Repeat Password:</label>
        <input type="password" id="repeat" name="repeat" required>
        <input type="hidden" id="isAdmin" name="isAdmin"  value="${userDto.isAdmin()}">
        <input type="hidden" id="isBlock" name="isBlock" value="${userDto.isBlock()}">


        <div id="lower">
            <input type="submit" value="Edit">
            <input type="button" value="Cancel" onclick="openPage('${pageContext.request.contextPath}${Cancel}')">
        </div><!--/ lower-->
    </form>


</div>

<script type="text/javascript">
    <%@include file="../../../resources/js/openPage.js"%>
</script>
</body>
</html>
