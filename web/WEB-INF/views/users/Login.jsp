<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>
    <style type="text/css">
        <%@include file="../../../resources/Login.css" %>
    </style>
    <meta http-equiv="Content-Type" content="text/html;">
    <meta charset="utf-8">
    <title>MyArticles</title>
</head>
<body>
<div class="one"><h1>MyArticles</h1></div>
<div id="container">
        <c:if test="${error ne null}">
            <p>
                <font color="red" style="text-align: center">${error}</font>
            </p>
        </c:if>
    <form action="${pageContext.request.contextPath}/login" method="post">
        <label for="username">Username:</label>
        <input type="text" id="username" name="username" required>
        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required>
        <div id="lower">
            <input type="submit" value="Login">
            <input type="button" value="Register" onclick="openPage('${pageContext.request.contextPath}/usercreate')">
        </div>
        </form>
</div>
<script type="text/javascript">
    <%@include file="../../../resources/js/openPage.js"%>
</script>
</body>
</html>
