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
    <title>adminUsers</title>

    <link href="//netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.min.css" rel="stylesheet"
          type="text/css"/>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.2.4/semantic.min.css" rel="stylesheet"
          type="text/css"/>

    <style type="text/css">
        <%@include file="../../../resources/css/home.css" %>
        <%@include file="../../../resources/css/pagination.css" %>
    </style>

</head>
<body style="margin-top: 80px">

<jsp:include page="${pageContext.request.contextPath}/WEB-INF/views/elements/navBar.jsp">
    <jsp:param name="active" value="P"/>
</jsp:include>

<div class="topTooltip">

    <div id="right">
    <button class="ui teal button" onclick="openPage('${pageContext.request.contextPath}/admin-users')">
        Users
    </button>

    <button class="ui teal button" onclick="openPage('${pageContext.request.contextPath}/admin-rooms')">
        Rooms
    </button>

    <div id="drop">

        <select class="ui dropdown" onchange="selectPerPage(value)">
            <option value="" hidden disabled> Show per page</option>
            <option id="1" value="1"> Show 1</option>
            <option id="5" value="5">  Show 5</option>
            <option id="10" value="10">  Show 10</option>
            <option id="15" value="15">  Show 15</option>
            <option id="20" value="20">  Show 20</option>
        </select>

        <script>
            function init(offset) {
                let el = document.getElementById(offset);
                el.selected = 'true';
            }

            init('${users.pageOffset}');
        </script>

    </div>

    </div>
</div>

<c:set var="userList" value="${users.collection}"/>
<table class="ui selectable celled teal table" id="marg">
    <thead>
    <tr>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Position</th>
        <th>Phone</th>
        <th>Email</th>
        <th>Admin</th>
        <th>Blocked</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="user" items="${userList}">

        <form id="giveRights${user.idUser}" action="${pageContext.request.contextPath}/admin-users" method="POST">
            <tr>
                <td> ${user.firstName} </td>
                <td> ${user.lastName} </td>
                <td> ${user.position} </td>
                <td> ${user.phone} </td>
                <td> ${user.email} </td>

                <td id="tdAdmin${user.idUser}">
                    <div class="ui toggle checkbox">
                        <input id="inAdmin${user.idUser}" type="checkbox" name="isAdmin"
                               onclick="submitForm(${user.idUser})" value="true">
                        <input type="hidden" name="isAdmin" value="false">
                        <label></label>
                    </div>
                </td>

                <td id="tdBlocked${user.idUser}">
                    <div class="ui toggle checkbox">
                        <input id="inBlocked${user.idUser}" type="checkbox" name="isBlocked"
                               onclick="submitForm(${user.idUser})" value="true">
                        <input type="hidden" name="isBlocked" value="false">
                        <label></label>
                    </div>
                </td>

                <input type="hidden" name="idUser" value="${user.idUser}">

                <c:if test="${user.isAdmin eq true}">
                    <script>
                        function init() {
                            let td = document.getElementById("tdAdmin${user.idUser}");
                            td.classList.add("positive");
                            let inp = document.getElementById("inAdmin${user.idUser}");
                            inp.checked = true;
                        } init();
                    </script>
                </c:if>

                <c:if test="${user.isBlocked eq true}">
                    <script>
                        function init() {
                            let td = document.getElementById("tdBlocked${user.idUser}");
                            td.classList.add("negative");
                            let inp = document.getElementById("inBlocked${user.idUser}");
                            inp.checked = true;
                        } init();
                    </script>
                </c:if>

            </tr>
        </form>


    </c:forEach>
    </tbody>
</table>

<%--PAGINATION--%>

    <div id="pag">
<div class="ui pagination menu">

    <c:forEach begin="1" end='${users.pageCount}' varStatus="loop">

        <c:url value='${pageContext.request.contextPath}/admin-users' var="pageUrl">
            <c:param name='pageOffset' value='${users.pageOffset}'/>
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

        selectPage('${users.page}');
    </script>

</div>
    </div>



<script type="text/javascript">
    <%@include file="../../../resources/js/openPage.js" %>
</script>

<script>
    function selectPerPage(value) {
        openPage('${pageContext.request.contextPath}/admin-users?pageOffset=' + value);
    }
</script>

<script>
    function submitForm(id) {
        var form = document.getElementById("giveRights" + id);
        form.submit();
    }
</script>

</body>
</html>
