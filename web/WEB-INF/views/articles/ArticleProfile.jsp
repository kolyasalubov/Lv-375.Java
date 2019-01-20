<%--
  Created by IntelliJ IDEA.
  User: Сява
  Date: 19.01.2019
  Time: 15:50
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
<div id="articleform">
    <form action="${pageContext.request.contextPath}/articleupdate" method="post">
        <label for="name">Name:</label>
        <input type="text" id="name" name="name" value="${articleDto.getName()}" readonly>
        <label for="description">Description</label>
        <input type="text" id="description" name="description" value="${articleDto.getDescription()}">
        <label for="url">Url:</label>
        <input type="text" id="url" name="url" value="${articleDto.getUrl()}">
        <input type="hidden" id="userId" name="userId" value="${articleDto.getUserId()}">

        <div id="lower">
            <input type="submit" value="OK">
        </div><!--/ lower-->
    </form>
</div>
</body>
</html>