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
    <title>roomProfile</title>

    <link href="//netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.min.css" rel="stylesheet"
          type="text/css"/>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.2.4/semantic.min.css" rel="stylesheet"
          type="text/css"/>

    <style type="text/css">
        <%@include file="../../../resources/css/home.css" %>
        <%@include file="../../../resources/css/pagination.css" %>
    </style>
</head>
<body>

<div id="error">
    <jsp:include page="${pageContext.request.contextPath}/WEB-INF/views/elements/errorMessage.jsp">
        <jsp:param name="error" value="${error}"/>
    </jsp:include>
</div>

<form action="${pageContext.request.contextPath}${urlToPost}" method="POST">

    <div class="ui container">
        <h1>New Room</h1>

        <div class="ui form">

            <div class="field">
                <h3>Room Number</h3>
                <div class="ui input left icon">
                    <i class="calendar icon"></i>

                    <input type="number" min="1" placeholder="Input room number"
                           name="number" value='${roomDto.number}' maxlength="10" required>
                    <input type="hidden" name="idRoom" value='${roomDto.idRoom}'>

                </div>
            </div>

            <div class="field">
                <h3>Type</h3>
                <div class="ui input left icon">
                    <i class="calendar icon"></i>
                    <input type="text" placeholder="Input room type"
                           name="type" value='${roomDto.type}' maxlength="40">
                </div>
            </div>

        </div>

        <button class="negative ui button left floated" type="button" onclick="goBack()">
            Cancel
        </button>
        <button class="positive ui button right floated" type="submit"> Save</button>

    </div>
</form>


<script type="text/javascript" charset="UTF-8">
    <%@include file="../../../resources/js/openPage.js" %>
</script>

</body>
</html>
