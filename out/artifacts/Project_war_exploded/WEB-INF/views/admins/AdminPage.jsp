<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%> --%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="hostUrl">${pageContext.request.requestURL}</c:set>
<c:set var="hostUri">${pageContext.request.requestURI}</c:set>
<c:set var="hostContext" value="${fn:substring(hostUrl, 0, fn:length(hostUrl) - fn:length(hostUri))}${pageContext.request.contextPath}" />
<html>
<head>
    <style type="text/css">
        <%@include file="../../../resources/css/ArticlePage.css" %>
    </style>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>All Users</title>
</head>
<body>
<div class="one"><h1>All Users Page</h1></div>
<a href="${pageContext.request.contextPath}/logout" class="logout">Logout</a>
<a href="${pageContext.request.contextPath}/useredit" class="editprofile">Edit Profile</a>
<br><br>
<select id="idvisibleUser" onchange="selectVisibleUsers('${hostContext}/usercount?visibleUser=')">
    <option value="100000"
            <c:if test="${visibleUser eq '100000'}">
                selected="selected"
            </c:if>
    >all items</option>
    <option value="5"
            <c:if test="${visibleUser eq '5'}">
                selected="selected"
            </c:if>
    >5 items</option>
    <option value="10"
            <c:if test="${visibleUser eq '10'}">
                selected="selected"
            </c:if>
    >10 items</option>
    <option value="25"
            <c:if test="${visibleUsere eq '25'}">
                selected="selected"
            </c:if>
    >25 items</option>
</select>
<br><br>
<c:set var="countUsers" value="${allUsersDto.users}"/>
<c:if test="${countUsers ne null && countUsers.size() gt 0}">
    <table border="1">
        <tr>
            <th>Name</th>
            <th>Password</th>
            <th>Email</th>
            <th>IsBlock</th>
            <th>IsAdmin</th>
            <th>Block</th>
            <th>Make Admin</th>
        </tr>
        <c:forEach var="row" items="${countUsers}">
            <tr>
                <td>${row.getUserName()}</td>
                <td>${row.getPassword()}</td>
                <td>${row.getEmail()}</td>
                <td>${row.isBlock()}</td>
                <td>${row.isAdmin()}</td>
                <td><a href="${pageContext.request.contextPath}/changeblockstatus?UserName=${row.getUserName()}"  class="butt">(Un)Block</a></td>
                <td><a href="${pageContext.request.contextPath}/changeadminstatus?UserName=${row.getUserName()}"  class="butt">(Make) Admin</a></td>
            </tr>
        </c:forEach>
    </table>

    <br><br>

</c:if>
<ul class="pagination">
    <li><a href="${hostContext}/alluser?pageNumber=1" class="butt">1</a></li>
    <li><a href="${hostContext}/alluser?pageNumber=2" class="butt">2</a></li>
    <li><a href="${hostContext}/alluser?pageNumber=3" class="butt">3</a></li>
    <li><a href="${hostContext}/alluser?pageNumber=4" class="butt">4</a></li>
    <li><a href="${hostContext}/alluser?pageNumber=6" class="butt">6</a></li>
    <li><a href="${hostContext}/alluser?pageNumber=7" class="butt">7</a></li>
    <li><a href="${hostContext}/alluser?pageNumber=8" class="butt">8</a></li>
    <li><a href="${hostContext}/alluser?pageNumber=9" class="butt">9</a></li>
    <li><a href="${hostContext}/alluser?pageNumber=10" class="butt">10</a></li>
</ul>
<script type="text/javascript">
    function selectVisibleUsers(url) {
        var visibleUser = document.getElementById("idvisibleUser");
        window.location.href = url
            + visibleUser.options[visibleUser.selectedIndex].value;
    }

</script>
</body>
</html>