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
        <c:param name='room' value='${roomDto.number}'/>
    </c:url>
    <button type="button" class="addBtn" onclick="openPage('${pageScope.createUrl}')">
        Add booking
    </button>

    <%--TODO maybe change id to number--%>
    <c:url value='${pageContext.request.contextPath}/room-archive' var="archiveUrl">
        <c:param name='id' value='${room.idRoom}'/>
    </c:url>
    <button type="button" class="archive" onclick="openPage('${pageScope.archiveUrl}')">
        Archive
    </button>
</div>

<c:set var="bookingList" value="${bookings.collection}"/>
<c:if test="${bookingList ne null && bookingList.size() gt 0}">
    <div class="bookingist">
        <c:forEach var="booking" items="${bookingList}">

            <%--${booking.toString()}--%>

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

                <c:if test="${booking.userEmail eq loginDto.email}">

                    <c:url value='${pageContext.request.contextPath}/booking-edit' var="editUrl">
                        <c:param name='id' value='${booking.idBooking}'/>
                    </c:url>
                    <button type="button" class="edit" onclick="openPage('${pageScope.editUrl}')">
                        Edit
                    </button>

                    <c:url value='${pageContext.request.contextPath}/booking-delete' var="deleteUrl">
                        <c:param name='id' value='${booking.idBooking}'/>
                    </c:url>
                    <button type="button" class="delete" onclick="openPage('${pageScope.deleteUrl}')">
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
