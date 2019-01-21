<%--
  Created by IntelliJ IDEA.
  User: kom
  Date: 17.01.2019
  Time: 11:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>admin rooms</title>

    <link href="//netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.min.css" rel="stylesheet"
          type="text/css"/>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.2.4/semantic.min.css" rel="stylesheet"
          type="text/css"/>


</head>
<body style="margin-top: 60px">

<jsp:include page="/WEB-INF/views/common/navBar.jsp">
    <jsp:param name="active" value="P"/>
</jsp:include>

<div class="topTooltip">

    <button type="button" onclick="openPage('${pageContext.request.contextPath}/admin-users')">
        Users
    </button>

    <button type="button" onclick="openPage('${pageContext.request.contextPath}/admin-rooms')">
        Rooms
    </button>

    <%--TODO move to right--%>
    <button type="button" onclick="openPage('${pageContext.request.contextPath}/room-create')">
        Add room
    </button>
</div>

<c:set var="roomList" value="${rooms.collection}"/>
<c:if test="${roomList ne null && roomList.size() gt 0}">
    <table class="ui selectable celled teal table">
        <thead>
        <tr>
            <th>Room number</th>
            <th>Type</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="room" items="${roomList}">
            <tr>
                <td> ${room.number} </td>
                <td> ${room.type} </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <div class="btns">
        <c:forEach var="room" items="${roomList}">
            <div class="pair">
                <c:url value='${pageContext.request.contextPath}/room-edit' var="editUrl">
                    <c:param name='idRoom' value='${room.idRoom}'/>
                </c:url>
                <button type="button" class="edit" onclick="openPage('${pageScope.editUrl}')">
                    Edit
                </button>

                <c:url value='${pageContext.request.contextPath}/room-delete' var="deleteUrl">
                    <c:param name='idRoom' value='${room.idRoom}'/>
                </c:url>
                <button type="button" class="delete" onclick="openWithConfirm('${pageScope.deleteUrl}')">
                    Delete
                </button>
            </div>
        </c:forEach>
    </div>
    </table>
</c:if>

<c:if test="${error ne null}">
    <p>
        <font color="red">${error}</font>
    </p>
</c:if>

<script type="text/javascript">
    <%@include file="../../../resources/js/openPage.js" %>
</script>

</body>
</html>

