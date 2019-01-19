<%--
  Created by IntelliJ IDEA.
  User: kom
  Date: 16.01.2019
  Time: 15:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>LOGIN</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%--<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/login.css">--%>
    <%--<link rel="stylesheet" type="text/css" href=" <c:url value = "/resources/css/login.css"/> ">--%>

    <style type="text/css">
        <%@include file="../../../resources/css/login.css" %>
    </style>
</head>
<body>
LOGIN

<c:if test="${error ne null}">
    <p>
        <font color="red">${error}</font>
    </p>
</c:if>

    <form action = "${pageContext.request.contextPath}/login" method = "POST">
        <%--<div class="imgcontainer">--%>
            <%--<img src="img_avatar2.png" alt="Avatar" class="avatar">--%>
        <%--</div>--%>

        <div class="container">
            <label ><b>Email</b></label>
            <input type="text" placeholder="Enter email" name="email" required>

            <label ><b>Password</b></label>
            <input type="password" placeholder="Enter Password" name="password" required>

            <button type="submit">Login</button>
        </div>

        <div class="container" style="background-color:#f1f1f1">
            <button type="button" class="cancelbtn" onclick="openPage('${pageContext.request.contextPath}/registration')" >
               Registration
            </button>
        </div>
    </form>

    <script type="text/javascript">
        <%@include file="../../../resources/js/openPage.js" %>
    </script>
</body>
</html>
