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
    <title>userProfileEdit</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%--<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/login.css">--%>
    <%--<link rel="stylesheet" type="text/css" href=" <c:url value = "/resources/css/login.css"/> ">--%>

    <style type="text/css">
        <%@include file="../../../resources/css/login.css" %>
    </style>
</head>
<body>
    userProfileEdit

    <c:if test="${error ne null}">
        <p>
            <font color="red">${error}</font>
        </p>
    </c:if>

    <form action = "${pageContext.request.contextPath}/${urlToPost}" method = "POST">

        <div class="container">
            <label ><b>Email</b></label>
            <input type="text" placeholder="Enter email" name="email" value="${userDto.email}" required>

            <label ><b>Password</b></label>
            <input type="password" placeholder="Enter Password" name="password" value="${userDto.password}"required>

            <label ><b>Password again</b></label>
            <input type="password" placeholder="Enter Password again" name="passwordRepeat" value="${userDto.password}" required>

            <label ><b>First Name</b></label>
            <input type="text" placeholder="Enter First Name" name="firstName" value="${userDto.firstName}" required>

            <label ><b>Last Name</b></label>
            <input type="text" placeholder="Enter Last Name" name="lastName"  value="${userDto.lastName}">

            <label ><b>Phone</b></label>
            <input type="text" placeholder="Enter Phone" name="phone" value="${userDto.phone}">

            <label ><b>Position</b></label>
            <input type="text" placeholder="Enter Position" name="position" value="${userDto.position}" >

            <input type="hidden" name="idUser" value="${userDto.idUser}">
            <button type="submit"> ${onSubmit} </button>
        </div>

        <%--TODO go to prev page ---- change eberywhere--%>
        <div class="container" style="background-color:#f1f1f1">
            <button type="button" class="cancelbtn" onclick="goBack()">
                Cancel
            </button>
        </div>
    </form>

    <script type="text/javascript">
        <%@include file="../../../resources/js/openPage.js" %>
    </script>
</body>
</html>

