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

    <style type="text/css">
        <%@include file="../../../resources/css/home.css" %>
        <%@include file="../../../resources/css/pagination.css" %>
    </style>

</head>
<body style="margin-top: 60px">

<jsp:include page="${pageContext.request.contextPath}/WEB-INF/views/elements/navBar.jsp">
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

<div id="error">
    <jsp:include page="${pageContext.request.contextPath}/WEB-INF/views/elements/errorMessage.jsp">
        <jsp:param name="error" value="${error}"/>
    </jsp:include>
</div>

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

        <c:url value='${pageContext.request.contextPath}/admin-rooms' var="pageUrl">
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
        openPage('${pageContext.request.contextPath}/admin-rooms?pageOffset=' + value);
    }
</script>

<script type="text/javascript">
    <%@include file="../../../resources/js/openPage.js" %>
</script>

</body>
</html>

