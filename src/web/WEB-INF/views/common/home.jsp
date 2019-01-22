<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>HOME</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <link href="//netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.min.css" rel="stylesheet"
          type="text/css"/>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.4.1/semantic.min.css" rel="stylesheet"
          type="text/css"/>
    <%--<style type="text/css">--%>
    <%--<%@include file="../../../resources/css/login.css" %>--%>
    <%--</style>--%>
</head>
<body style="margin-top: 60px">
HOME

<jsp:include page="${pageContext.request.contextPath}/WEB-INF/views/elements/navBar.jsp">
    <jsp:param name="active" value="H"/>
</jsp:include>

<div class="topTooltip">
    <h1>
        Choose the room to book
    </h1>
    <button type="button" class="addBtn" onclick="openPage('${pageContext.request.contextPath}/booking-create')">
        Add booking
    </button>
</div>

<%-- TODO --- wrap constants in classes to use Beans --%>
<%--<jsp:useBean id="RoomConstants" class="com.it.academy.constants.RoomConstants"/>--%>

<jsp:include page="${pageContext.request.contextPath}/WEB-INF/views/elements/errorMessage.jsp">
    <jsp:param name="error" value="${error}"/>
</jsp:include>

<c:set var="roomList" value="${rooms.collection}"/>
<c:if test="${roomList ne null && roomList.size() gt 0}">
    <div class="roomList">
        <c:forEach var="room" items="${roomList}">

            <c:url value='${pageContext.request.contextPath}/room' var="url">
                <c:param name='number' value='${room.number}'/>
            </c:url>

            <div class="room" onclick="openPage('${pageScope.url}')">
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

        init('${rooms.pageOffset}');
    </script>

</div>

<div class="ui pagination menu">

    <c:forEach begin="1" end='${rooms.pageCount}' varStatus="loop">

        <c:url value='${pageContext.request.contextPath}/home' var="pageUrl">
            <c:param name='pageOffset' value='${rooms.pageOffset}'/>
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

        selectPage('${rooms.page}');
    </script>

</div>


<script type="text/javascript">
    <%@include file="../../../resources/js/openPage.js" %>
</script>

<script>
    function selectPerPage(value) {
        openPage('${pageContext.request.contextPath}/home?pageOffset=' + value);
    }
</script>


</body>
</html>