<%--
  Created by IntelliJ IDEA.
  User: kom
  Date: 20.01.2019
  Time: 11:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>BOOKING_PROFILE</title>

    <link href="//netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.min.css" rel="stylesheet"
          type="text/css"/>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.2.4/semantic.min.css" rel="stylesheet"
          type="text/css"/>
    <link href="https://cdn.rawgit.com/mdehoog/Semantic-UI-Calendar/76959c6f7d33a527b49be76789e984a0a407350b/dist/calendar.min.css"
          rel="stylesheet" type="text/css"/>

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
        <h1>New Booking</h1>

        <div class="ui form">

            <div class="field">
                <h3>Room Number</h3>
                <div class="ui input left icon">
                    <i class="calendar icon"></i>
                    <c:choose>
                        <c:when test="${urlToPost eq '/booking-create'}">
                            <input type="number" min="1" placeholder="Input room number"
                                   name="roomNumber" value='${roomNumber}' maxlength="10" required>
                        </c:when>
                        <c:otherwise>
                            <input type="number" min="1" placeholder="Input room number"
                                   name="roomNumber" value='${bookingDto.roomNumber}' maxlength="10" required>
                            <input type="hidden" name="idBooking" value='${bookingDto.idBooking}' >
                        </c:otherwise>
                    </c:choose>

                </div>
            </div>

            <div class="two fields">
                <div class="field">
                    <h3>Start date</h3>
                    <div class="ui calendar" id="startDate">
                        <div class="ui input left icon">
                            <i class="calendar icon"></i>
                            <input type="text" placeholder="Click to select the start date" name="startDate"
                                   value='${bookingDto.startDate} ${bookingDto.startTime}' autocomplete="off" required>
                        </div>
                    </div>
                </div>
                <div class="field">
                    <h3>End date</h3>
                    <div class="ui calendar" id="endDate">
                        <div class="ui input left icon">
                            <i class="calendar icon"></i>
                            <input type="text" placeholder="Click to select the end date" name="endDate"
                                   value='${bookingDto.endDate} ${bookingDto.endTime}' autocomplete="off" required>
                        </div>
                    </div>
                </div>
            </div>

            <div class="field">
                <h3>Purpose</h3>
                <p> Maximum 100 characters</p>
                <div class="ui input left icon">
                    <i class="calendar icon"></i>
                    <input type="text" placeholder="Input purpose of booking" name="purpose"
                           value='${bookingDto.purpose}' maxlength="100" >
                </div>
            </div>

        </div>

        <button class="negative ui button left floated" type="button" onclick="goBack()" >
            Cancel
        </button>
        <button class="positive ui button right floated" type="submit" > Save </button>

    </div>
</form>


<script type="text/javascript" charset="UTF-8">
    <%@include file="../../../resources/js/openPage.js" %>
</script>

<script src="https://code.jquery.com/jquery-2.1.4.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.2.4/semantic.min.js"></script>
<script src="https://cdn.rawgit.com/mdehoog/Semantic-UI-Calendar/76959c6f7d33a527b49be76789e984a0a407350b/dist/calendar.min.js"></script>

<script>

    var today = new Date();
    $('#startDate').calendar({
        firstDayOfWeek: 1,
        today: true,
        minDate: today,
        endCalendar: $('#endDate'),
        ampm: false,
    });
    $('#endDate').calendar({
        firstDayOfWeek: 1,
        minDate: today,
        startCalendar: $('#startDate'),
        ampm: false,
    });

</script>

</body>
</html>
