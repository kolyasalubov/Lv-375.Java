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

    <%--<link href="//netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.min.css" rel="stylesheet"--%>
          <%--type="text/css"/>--%>
    <%--<link href="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.2.4/semantic.min.css" rel="stylesheet"--%>
          <%--type="text/css"/>--%>


</head>
<body>

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

        <form id="giveRights" action="${pageContext.request.contextPath}/admin-users" method="POST">
            <tr>
                <td> ${user.firstName} </td>
                <td> ${user.lastName} </td>
                <td> ${user.position} </td>
                <td> ${user.phone} </td>
                <td> ${user.email} </td>
                <c:choose>
                    <c:when test="${user.isAdmin eq true}">
                        <td class="positive">
                            <div class="ui fitted slider checkbox">
                                <input type="checkbox" name="isAdmin" value="true" onclick="submitForm()" checked>
                            </div>
                        </td>
                    </c:when>
                    <c:otherwise>
                        <td class="collapsing">
                            <div class="ui fitted slider checkbox">
                                <input type="checkbox" name="isAdmin" onclick="submitForm()" value="true">
                            </div>
                        </td>
                    </c:otherwise>
                </c:choose>

                <c:choose>
                    <c:when test="${user.isBlocked eq true}">
                        <td class="negative">
                            <div class="ui toggle checkbox">
                                <input type="checkbox" name="isBlocked" onclick="submitForm()" value="true" checked>
                            </div>
                        </td>
                    </c:when>
                    <c:otherwise>
                        <td>
                            <div class="ui toggle checkbox">
                                <input type="checkbox" name="isBlocked" onclick="submitForm()" value="true">
                            </div>
                        </td>
                    </c:otherwise>
                </c:choose>

                <input type="hidden" name="idUser" value="${userDto.idUser}">

            </tr>
        </form>


    </c:forEach>
    </tbody>
</table>

<div class="ui fitted slider checkbox">
    <input type="checkbox" name="isAdmin" value="true">
</div>

<div class="ui toggle checkbox">
    <input type="checkbox" name="isBlocked" value="true">
</div>

<script>
    function submitForm() {
        var form = document.getElementById("giveRights");
        form.submit();
    }
</script>

</body>
</html>
