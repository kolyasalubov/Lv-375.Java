<%--
  Created by IntelliJ IDEA.
  User: kom
  Date: 17.01.2019
  Time: 11:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>userProfile</title>

    <link href="//netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.min.css" rel="stylesheet"
          type="text/css"/>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.2.4/semantic.min.css" rel="stylesheet"
          type="text/css"/>

</head>
<body style="margin-top: 60px">

<jsp:include page="${pageContext.request.contextPath}/WEB-INF/views/elements/navBar.jsp">
    <jsp:param name="active" value="P"/>
</jsp:include>

<c:if test="${userDto.isAdmin eq true}">
    <button type="button" class="archive" onclick="openPage('${pageContext.request.contextPath}/admin-users')" >
            Admin page
    </button>
</c:if>

<div class="userInfo">
    <div class="ui segments">
        <div class="ui segment">
            <p> Email </p>
        </div>
        <div class="ui teal segment">
            <p> ${userDto.email} </p>
        </div>
    </div>

    <div class="ui segments">
        <div class="ui segment">
            <p> First Name </p>
        </div>
        <div class="ui teal segment">
            <p> ${userDto.firstName} </p>
        </div>
    </div>

    <div class="ui segments">
        <div class="ui segment">
            <p> Last Name </p>
        </div>
        <div class="ui teal segment">
            <p> ${userDto.lastName} </p>
        </div>
    </div>

    <div class="ui segments">
        <div class="ui segment">
            <p> Phone </p>
        </div>
        <div class="ui teal segment">
            <p> ${userDto.phone} </p>
        </div>
    </div>

    <div class="ui segments">
        <div class="ui segment">
            <p> Position </p>
        </div>
        <div class="ui teal segment">
            <p> ${userDto.position} </p>
        </div>
    </div>

    <button class="positive ui button left floated" type="button"
            onclick="openPage('${pageContext.request.contextPath}/user-edit')" > Edit profile </button>
    <button class="negative ui button right floated" type="button"
            onclick="openPage('${pageContext.request.contextPath}/logout')" > Log out </button>
</div>

<script type="text/javascript" charset="UTF-8">
    <%@include file="../../../resources/js/openPage.js" %>
</script>


</body>
</html>
