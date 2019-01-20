<%--
  Created by IntelliJ IDEA.
  User: Сява
  Date: 19.01.2019
  Time: 17:11
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
    <c:if test="${error ne null}">
        <p>
            <font color="red">${error}</font>
        </p>
    </c:if>
    <form action="${pageContext.request.contextPath}/articleadd" method="post">
        <label for="name">Name:</label>
        <input type="text" id="name" name="name" >
        <label for="description">Description</label>
        <input type="text" id="description" name="description" >
        <label for="url">Url:</label>
        <input type="text" id="url" name="url" >
        <div id="lower">
            <input type="submit" value="ADD">
        </div><!--/ lower-->
    </form>
</div>
</body>
</html>
