<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>HOME</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%--<style type="text/css">--%>
    <%--<%@include file="../../../resources/css/login.css" %>--%>
    <%--</style>--%>
</head>
<body style="margin-top: 60px">
HOME

<jsp:include page="/WEB-INF/views/common/navBar.jsp">
    <jsp:param name="active" value="H" />
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

<c:if test="${error ne null}">
    <p>
        <font color="red">${error}</font>
    </p>
</c:if>

<c:set var="roomList" value="${rooms.collection}"/>
<c:if test="${roomList ne null && roomList.size() gt 0}">
    <div class="roomList">
        <c:forEach var="room" items="${roomList}">

            <c:url value='${pageContext.request.contextPath}/room' var="url" >
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

<div>
    <%--TODO PAGINATION --%>
</div>

<script type="text/javascript">
    <%@include file="../../../resources/js/openPage.js" %>
</script>

</body>
</html>