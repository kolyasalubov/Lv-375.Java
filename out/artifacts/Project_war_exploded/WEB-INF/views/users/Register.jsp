<%--
  Created by IntelliJ IDEA.
  User: Сява
  Date: 18.01.2019
  Time: 20:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div id="container">
    <c:if test="${error ne null}">
        <p>
            <font color="red">${error}</font>
        </p>
    </c:if>
    <form action="${pageContext.request.contextPath}/usercreate" method="post">
        <label for="username">Username:</label>
        <input type="text" id="username" name="username" required>
        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required>
        <label for="email">Email:</label>
        <input type="text" id="email" name="email" required>
        <input type="hidden" id="isBlock" name="isBlock" value="false">
        <div id="lower">

                <input type="checkbox" id = "checkbox" name="isAdmin" value="1">
           <label for="checkbox">Create like Admin</label>
            <input type="submit" value="Login">
        </div><!--/ lower-->
        </form>
</div>
</body>
</html>
