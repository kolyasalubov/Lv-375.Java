<%--
  Created by IntelliJ IDEA.
  User: kom
  Date: 16.01.2019
  Time: 15:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>LOGIN</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <link href="//netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.min.css" rel="stylesheet"
          type="text/css"/>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.2.4/semantic.min.css" rel="stylesheet"
          type="text/css"/>

    <style type="text/css">
        <%@include file="../../../resources/css/yellowForm.css" %>
    </style>

</head>

<style type="text/css">

    body > .grid {
        height: 100%;
    }

    .image {
        margin-top: -100px;
    }

    .column {
        max-width: 450px;
    }

</style>

</head>
<body>

<h1> Your Booking Manager </h1>

<div class="ui middle aligned center aligned grid" id="main">
    <div class="column">
        <form class="ui large form" action = "${pageContext.request.contextPath}/login" method = "POST">
            <div class="ui stacked segment">
                <div class="field">
                    <div class="ui left icon input">
                        <i class="user icon"></i>
                        <input type="text" placeholder="Enter email" name="email" required>
                    </div>
                </div>
                <div class="field">
                    <div class="ui left icon input">
                        <i class="lock icon"></i>
                        <input type="password" placeholder="Enter Password" name="password" required>
                    </div>
                </div>
                <button type="submit" class="ui fluid large submit button" id="login">Login</button>
            </div>

        </form>

        <div class="ui message">
            New to us? <a href='${pageContext.request.contextPath}/registration'> Sign Up </a>
        </div>

        <jsp:include page="${pageContext.request.contextPath}/WEB-INF/views/elements/errorMessage.jsp">
            <jsp:param name="error" value="${error}" />
        </jsp:include>

    </div>
</div>
</body>
</html>
