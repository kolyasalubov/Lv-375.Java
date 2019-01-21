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
</div>

<c:set var="userList" value="${users.collection}"/>
<table class="ui selectable celled teal table">
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

<script>
    function submitForm(id) {
        var form = document.getElementById("giveRights" + id);
        form.submit();
    }
</script>

</body>
</html>
