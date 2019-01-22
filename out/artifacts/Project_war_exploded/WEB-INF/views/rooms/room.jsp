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
<body style="margin-top: 60px">

<div class="ui special cards">
    <div class="card">
        <div class="blurring dimmable image">
            <div class="ui dimmer">
                <div class="content">
                    <div class="center">
                        <div class="ui inverted button">Add Friend</div>
                    </div>
                </div>
            </div>
            <img src="/images/avatar/large/elliot.jpg">
        </div>
        <div class="content">
            <a class="header">Team Fu</a>
            <div class="meta">
                <span class="date">Created in Sep 2014</span>
            </div>
        </div>
        <div class="extra content">
            <a>
                <i class="users icon"></i>
                2 Members
            </a>
        </div>
    </div>
    <div class="card">
        <div class="blurring dimmable image">
            <div class="ui inverted dimmer">
                <div class="content">
                    <div class="center">
                        <div class="ui primary button">Add Friend</div>
                    </div>
                </div>
            </div>
            <img src="/images/avatar/large/jenny.jpg">
        </div>
        <div class="content">
            <a class="header">Team Hess</a>
            <div class="meta">
                <span class="date">Created in Aug 2014</span>
            </div>
        </div>
        <div class="extra content">
            <a>
                <i class="users icon"></i>
                2 Members
            </a>
        </div>
    </div>
</div>

<jsp:include page="${pageContext.request.contextPath}/WEB-INF/views/elements/navBar.jsp">
    <jsp:param name="active" value="H"/>
</jsp:include>

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
            <c:url value='${pageContext.request.contextPath}/room' var="urlToPage">
                <c:param name='number' value='${roomDto.number}'/>
            </c:url>
        </c:when>
        <c:otherwise>
            <c:set var="title" value="Current bookings"/>
            <c:url value='${pageContext.request.contextPath}/room' var="url">
                <c:param name='number' value='${roomDto.number}'/>
            </c:url>
            <c:url value='${pageContext.request.contextPath}/room-archive' var="urlToPage">
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

                    <%--TODO urlToGoBack--%>

                    <c:url value='${pageContext.request.contextPath}/booking-edit' var="editUrl">
                        <c:param name='idBooking' value='${booking.idBooking}'/>
                        <c:param name='urlToGoBack' value='${pageContext.request.contextPath}/room'/>
                    </c:url>
                    <button type="button" class="edit" onclick="openPage('${pageScope.editUrl}')">
                        Edit
                    </button>

                    <c:url value='${pageContext.request.contextPath}/booking-delete' var="deleteUrl">
                        <c:param name='idBooking' value='${booking.idBooking}'/>
                        <c:param name='urlToGoBack' value='${pageContext.request.contextPath}/room'/>
                    </c:url>
                    <button type="button" class="delete" onclick="openWithConfirm('${pageScope.deleteUrl}')">
                        Delete
                    </button>

                </c:if>
            </div>

        </c:forEach>
    </div>
</c:if>

<jsp:include page="${pageContext.request.contextPath}/WEB-INF/views/elements/errorMessage.jsp">
    <jsp:param name="error" value="${error}" />
</jsp:include>

<%--PAGINATION--%>
<div>

    <select class="ui dropdown" onchange="selectPerPage(value)">
        <option value="" hidden disabled> Show per page</option>
        <option id="1" value="1"> 1</option>
        <option id="5" value="5"> 5</option>
        <option id="10" value="10"> 10</option>
        <option id="15" value="15"> 15</option>
        <option id="20" value="20"> 20</option>
    </select>

    <script>
        function init(offset) {
            let el = document.getElementById(offset);
            el.selected = 'true';
        }

        init('${bookings.pageOffset}');
    </script>

</div>

<div class="ui pagination menu">

    <c:forEach begin="1" end='${bookings.pageCount}' varStatus="loop">

        <c:url value='${urlToPage}' var="pageUrl">
            <c:param name='pageOffset' value='${bookings.pageOffset}'/>
            <c:param name='page' value='${loop.index}'/>
        </c:url>

        <a id='${loop.index}' class="item" onclick="openPage('${pageScope.pageUrl}')">
                ${loop.index}
        </a>

    </c:forEach>

    <script>
        function selectPage(page) {
            let el = document.getElementById(page);
            el.classList.add("active");
        }

        selectPage('${bookings.page}');
    </script>

</div>


<script type="text/javascript">
    <%@include file="../../../resources/js/openPage.js" %>
</script>

<script>
    function selectPerPage(value) {
        openPage('${urlToPage}?pageOffset=' + value);
    }
</script>




</body>
</html>
