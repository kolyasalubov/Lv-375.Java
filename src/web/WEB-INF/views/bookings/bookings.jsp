<%--
  Created by IntelliJ IDEA.
  User: kom
  Date: 17.01.2019
  Time: 11:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>bookings</title>
</head>
<body style="margin-top: 60px">

<jsp:include page="${pageContext.request.contextPath}/WEB-INF/views/elements/navBar.jsp">
    <jsp:param name="active" value="B"/>
</jsp:include>

<div class="topTooltip">

    <button type="button" class="addBtn" onclick="openPage('${pageContext.request.contextPath}/booking-create')">
        Add booking
    </button>

    <c:choose>
        <c:when test="${archive eq null}">
            <c:set var="title" value="Archive"/>
            <c:url value='${pageContext.request.contextPath}/bookings-archive' var="url"/>
            <c:url value='${pageContext.request.contextPath}/bookings' var="urlToPage"/>
        </c:when>
        <c:otherwise>
            <c:set var="title" value="Current bookings"/>
            <c:url value='${pageContext.request.contextPath}/bookings' var="url"/>
            <c:url value='${pageContext.request.contextPath}/bookings-archive' var="urlToPage"/>
        </c:otherwise>
    </c:choose>

    <button type="button" class="archive" onclick="openPage('${pageScope.url}')">
        ${pageScope.title}
    </button>
</div>

<c:set var="bookingList" value="${bookings.collection}"/>
<c:if test="${bookingList ne null && bookingList.size() gt 0}">
    <table class="ui selectable celled teal table">
        <thead>
        <tr>
            <th>Room</th>
            <th>Start Date</th>
            <th>End Date</th>
            <th>Purpose</th>
        </tr>
        </thead>
        <tbody>
            <c:forEach var="booking" items="${bookingList}">
                <tr>
                    <td> ${booking.roomNumber} </td>
                    <td> ${booking.startDate} ${booking.startTime} </td>
                    <td> ${booking.endDate} ${booking.endTime} </td>
                    <td> ${booking.purpose} </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <div class="btns">
        <c:forEach var="booking" items="${bookingList}">
            <c:if test="${archive eq null}">

                <div class="pair">
                    <c:url value='${pageContext.request.contextPath}/booking-edit' var="editUrl">
                        <c:param name='idBooking' value='${booking.idBooking}'/>
                        <c:param name='urlToGoBack' value='${pageContext.request.contextPath}/bookings'/>
                    </c:url>
                    <button type="button" class="edit" onclick="openPage('${pageScope.editUrl}')">
                        Edit
                    </button>

                    <c:url value='${pageContext.request.contextPath}/booking-delete' var="deleteUrl">
                        <c:param name='idBooking' value='${booking.idBooking}'/>
                        <c:param name='urlToGoBack' value='${pageContext.request.contextPath}/bookings'/>
                    </c:url>
                    <button type="button" class="delete" onclick="openWithConfirm('${pageScope.deleteUrl}')">
                        Delete
                    </button>
                </div>

            </c:if>
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