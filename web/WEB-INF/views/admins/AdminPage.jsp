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
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <style type="text/css">
        <%@include file="../../../resources/css/bootstrap.min.css" %>
        <%@include file="../../../resources/css/common.css" %>
    </style>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>All Users</title>
</head>
<body>
<div class="one"><h1>All Users Page</h1></div>
<a href="${pageContext.request.contextPath}/logout" class="btn btn-outline-dark" id="log">Logout</a>
<a href="${pageContext.request.contextPath}/useredit" class="btn btn-outline-primary" id="editprofile">Edit Profile</a>
<br><br>
<select id="idvisibleUser" class="custom-select" onchange="selectVisibleUsers('${hostContext}/usercount?visibleUser=')">
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
            <c:if test="${visibleUser eq '25'}">
                selected="selected"
            </c:if>
    >25 items</option>
</select>
<br><br>
<c:set var="countUsers" value="${allUsersDto.users}"/>
<c:if test="${countUsers ne null && countUsers.size() gt 0}">
    <table border="1" class="table table-striped" id="tableUser">
        <tr>
            <th>Name</th>
            <th>Password</th>
            <th>Email</th>
            <th>IsBlock</th>
            <th>IsAdmin</th>
            <th>Block Status</th>
            <th>Admin Status</th>
        </tr>
        <c:forEach var="row" items="${countUsers}">
            <tr>
                <td>${row.getUserName()}</td>
                <td>${row.getPassword()}</td>
                <td>${row.getEmail()}</td>
                <td>${row.isBlock()}</td>
                <td>${row.isAdmin()}</td>
                <td><a type="button" rel="tooltip" class="btn btn-success btn-just-icon btn-sm" href="${pageContext.request.contextPath}/changeblockstatus?username=${row.getUserName()}" style="color: #1c7430">Change</a></td>
                <td><a type="button" rel="tooltip" class="btn btn-success btn-just-icon btn-sm" href="${pageContext.request.contextPath}/changeadminstatus?username=${row.getUserName()}" style="color: #1c7430">Change</a></td>
            </tr>
        </c:forEach>
    </table>

    <br><br>

</c:if>
<ul class="pagination" id="pagin">
    <li class="page-item"><a class="page-link" href="${hostContext}/alluser?pageNumber=1" >1</a></li>
    <li class="page-item"><a class="page-link" href="${hostContext}/alluser?pageNumber=2" >2</a></li>
    <li class="page-item"><a class="page-link" href="${hostContext}/alluser?pageNumber=3" >3</a></li>
    <li class="page-item"><a class="page-link" href="${hostContext}/alluser?pageNumber=4" >4</a></li>
    <li class="page-item"><a class="page-link" href="${hostContext}/alluser?pageNumber=6" >6</a></li>
    <li class="page-item"><a class="page-link" href="${hostContext}/alluser?pageNumber=7" >7</a></li>
    <li class="page-item"><a class="page-link" href="${hostContext}/alluser?pageNumber=8" >8</a></li>
    <li class="page-item"><a class="page-link" href="${hostContext}/alluser?pageNumber=9" >9</a></li>
    <li class="page-item"><a class="page-link" href="${hostContext}/alluser?pageNumber=10" >10</a></li>
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