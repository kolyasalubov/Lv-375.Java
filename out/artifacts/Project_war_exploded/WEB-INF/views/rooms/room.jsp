<%--
  Created by IntelliJ IDEA.
  User: kom
  Date: 17.01.2019
  Time: 11:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>ROOM</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%--<style type="text/css">--%>
    <%--<%@include file="../../../resources/css/login.css" %>--%>
    <%--</style>--%>
</head>
<body>
ROOM

<div class="topTooltip">
    <h5> Room ${roomDto.number} </h5>
    <p> ${roomDto.type} </p>

    <c:url value='${pageContext.request.contextPath}/booking-create-room' var="createUrl" >
        <c:param name='id' value='${room.idRoom}'/>
    </c:url>

    <c:url value='${pageContext.request.contextPath}/room-archive' var="archiveUrl" >
        <c:param name='id' value='${room.idRoom}'/>
    </c:url>

    <button type="button" class="addBtn" onclick="openPage('${pageScope.createUrl}')">
        Add booking
    </button>
    <button type="button" class="archive" onclick="openPage('${pageScope.archiveUrl}')">
        Archive
    </button>
</div>

<c:set var="bookingList" value="${bookings.collection}"/>
<c:if test="${bookingList ne null && bookingList.size() gt 0}">
    <div class="bookingist">
        <c:forEach var="booking" items="${bookingList}">

            <div class="booking" >
                <h1>
                    Room ${room.number}
                </h1>
                <h2>
                        ${room.type}
                </h2>
            </div>

        </c:forEach>
    </div>
</c:if>

<c:if test="${error ne null}">
    <p>
        <font color="red">${error}</font>
    </p>
</c:if>

<div>
    <%--TODO PAGINATION --%>
</div>


<script type="text/javascript">
    <%@include file="../../../resources/js/openPage.js" %>
</script>
</body>
</html>
