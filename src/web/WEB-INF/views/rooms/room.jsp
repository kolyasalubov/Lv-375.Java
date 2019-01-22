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

    <style type="text/css">
        <%@include file="../../../resources/css/home.css" %>
        <%@include file="../../../resources/css/pagination.css" %>
    </style>
</head>
<body style="margin-top: 80px">


<jsp:include page="${pageContext.request.contextPath}/WEB-INF/views/elements/navBar.jsp">
    <jsp:param name="active" value="H"/>
</jsp:include>

<div class="topTooltip">

    <h2 class="ui header">
        <i class="calendar alternate icon"></i>
        <div class="content">
            Room ${roomDto.number}
            <div class="sub header">${roomDto.type}</div>
        </div>
    </h2>

    <div id="right">

        <c:url value='${pageContext.request.contextPath}/booking-create' var="createUrl">
            <c:param name='number' value='${roomDto.number}'/>
        </c:url>
        <button class="ui right floated large pink button" onclick="openPage('${pageScope.createUrl}')">
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

        <button class="ui right floated large pink button" onclick="openPage('${pageScope.url}')">
            ${pageScope.title}
        </button>

        <div id="drop">
            <select class="ui dropdown" onchange="selectPerPage(value)">
                <option value="" hidden disabled> Show per page</option>
                <option id="1" value="1"> Show 1</option>
                <option id="5" value="5"> Show 5</option>
                <option id="10" value="10"> Show 10</option>
                <option id="15" value="15"> Show 15</option>
                <option id="20" value="20"> Show 20</option>
            </select>

            <script>
                function init(offset) {
                    let el = document.getElementById(offset);
                    el.selected = 'true';
                }

                init('${bookings.pageOffset}');
            </script>

        </div>

    </div>
</div>

<c:set var="bookingList" value="${bookings.collection}"/>
<c:if test="${bookingList ne null && bookingList.size() gt 0}">
    <div class="roomList">
    <c:forEach var="booking" items="${bookingList}">

        <div class="ui special cards" id="room">
            <div class="purple card">

                <c:if test="${(booking.userEmail eq loginDto.email) and (archive eq null)}">

                    <div id="btns">
                        <c:url value='${pageContext.request.contextPath}/booking-edit' var="editUrl">
                            <c:param name='idBooking' value='${booking.idBooking}'/>
                            <c:param name='urlToGoBack' value='${pageContext.request.contextPath}/room'/>
                        </c:url>
                        <button class="ui purple button" onclick="openPage('${pageScope.editUrl}')">
                            Edit
                        </button>

                        <c:url value='${pageContext.request.contextPath}/booking-delete' var="deleteUrl">
                            <c:param name='idBooking' value='${booking.idBooking}'/>
                            <c:param name='urlToGoBack' value='${pageContext.request.contextPath}/room'/>
                        </c:url>
                        <button class="ui purple button"  onclick="openWithConfirm('${pageScope.deleteUrl}')">
                            Delete
                        </button>
                    </div>

                </c:if>

                <div class="content">
                    <a class="header"> ${booking.startTime} - ${booking.endTime} </a>
                    <div class="meta">
                            <span class="date"> ${booking.startDate}
                    <c:if test="${booking.startDate ne booking.endDate}">
                        - ${booking.endDate}
                    </c:if> </span>
                    </div>
                    <div class="meta">
                        <span class="date"> ${booking.purpose} </span>
                    </div>


                </div>
                <div class="extra content">
                    <a>
                        <i class="users icon"></i>
                            ${booking.userFirstName} ${booking.userLastName}
                    </a>
                </div>

            </div>

        </div>

    </c:forEach>
    </div>
</c:if>

<div id="error">
    <jsp:include page="${pageContext.request.contextPath}/WEB-INF/views/elements/errorMessage.jsp">
        <jsp:param name="error" value="${error}"/>
    </jsp:include>
</div>


<%--PAGINATION--%>
<c:if test="${bookingList ne null && bookingList.size() gt 0}">
    <div id="pag">
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
    </div>
</c:if>


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
