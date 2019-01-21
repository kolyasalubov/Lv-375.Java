<%--
  Created by IntelliJ IDEA.
  User: kom
  Date: 17.01.2019
  Time: 11:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

    <c:url value='${pageContext.request.contextPath}/booking-create' var="createUrl">
        <c:param name='number' value='${roomDto.number}'/>
    </c:url>
    <button type="button" class="addBtn" onclick="openPage('${pageScope.createUrl}')">
        Add booking
    </button>

    <c:choose>
        <c:when test="${archive eq null}">
            <c:set var="title" value="Archive"/>
            <c:url value='${pageContext.request.contextPath}/room-archive' var="url">
                <c:param name='number' value='${roomDto.number}'/>
            </c:url>
        </c:when>
        <c:otherwise>
            <c:set var="title" value="Current bookings"/>
            <c:url value='${pageContext.request.contextPath}/room' var="url">
                <c:param name='number' value='${roomDto.number}'/>
            </c:url>
        </c:otherwise>
    </c:choose>

    <button type="button" class="archive" onclick="openPage('${pageScope.url}')">
        ${pageScope.title}
    </button>
</div>

<c:set var="bookingList" value="${bookings.collection}"/>
<c:if test="${bookingList ne null && bookingList.size() gt 0}">
    <div class="bookingist">
        <c:forEach var="booking" items="${bookingList}">

            <div class="booking">
                <p>
                        ${booking.startDate}
                    <c:if test="${booking.startDate ne booking.endDate}">
                        - ${booking.endDate}
                    </c:if>
                </p>
                <p> ${booking.startTime} - ${booking.endTime} </p>
                <div class="userInfo">
                    <h5> ${booking.userFirstName} ${booking.userLastName}</h5>

                    <p> ${booking.purpose} </p>
                </div>

                <c:if test="${(booking.userEmail eq loginDto.email) and (archive eq null)}">

                    <c:url value='${pageContext.request.contextPath}/booking-edit' var="editUrl">
                        <c:param name='idBooking' value='${booking.idBooking}'/>
                    </c:url>
                    <button type="button" class="edit" onclick="openPage('${pageScope.editUrl}')">
                        Edit
                    </button>

                    <c:url value='${pageContext.request.contextPath}/booking-delete' var="deleteUrl">
                        <c:param name='idBooking' value='${booking.idBooking}'/>
                        <c:param name='urlToPost' value='${pageContext.request.contextPath}/room'/>
                    </c:url>
                    <button type="button" class="delete" onclick="openWithConfirm('${pageScope.deleteUrl}')">
                        Delete
                    </button>

                </c:if>
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
