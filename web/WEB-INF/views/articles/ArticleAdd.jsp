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
    <style type="text/css">
        <%@include file="../../../resources/css/ArticleProfile.css" %>
    </style>
    <meta http-equiv="Content-Type" content="text/html;">
    <meta charset="utf-8">
    <title>Add Article</title>
</head>
<body>
<div class="one"><h1>Add new Article</h1></div>
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
            <input type="submit" value="Add">
            <input type="button" value="Cancel" onclick="openPage('${pageContext.request.contextPath}/userarticle')">
        </div><!--/ lower-->
    </form>
</div>

<script type="text/javascript">
    <%@include file="../../../resources/js/openPage.js"%>
</script>
</body>
</html>
