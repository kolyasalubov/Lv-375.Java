<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%> --%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="hostUrl">${pageContext.request.requestURL}</c:set>
<c:set var="hostUri">${pageContext.request.requestURI}</c:set>
<c:set var="hostContext" value="${fn:substring(hostUrl, 0, fn:length(hostUrl) - fn:length(hostUri))}${pageContext.request.contextPath}" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
<style type="text/css">
 <%@include file="Profiles.css" %>
 <%@include file="Tables.css" %>
</style>
<title>Users</title>
</head>
<body>

	<div class="topnav">
	<a href="#">Users</a>
	<div class="topnav-right">
	<a href="${pageContext.request.contextPath}/logout">logout</a>
	</div>
	</div>
	<br><br>
	
	<c:set var="currentUsers" value="${usersDto.users}"/>
	<c:if test="${currentUsers ne null && currentUsers.size() gt 0}">
		<table id=trips border="1">
			<tr>
				<th>Login</th>
				<th>Password</th>
				<th>Alias</th>
				<th>is blocked</th>
				<th>Id_Role</th>
				<th>Delete_user</th>
				
			</tr>
			<c:forEach var="row" items="${currentUsers}">
				<tr>
					<td>${row.getLogin()}</td>
					<td>${row.getPassword()}</td>
					<td>${row.getAlias()}</td>
					<td>${row.getBlocked()}</td>
					<td>${row.getRole()}</td>
					<td><a href="#" onclick="checkDeleteItem('${hostContext}/userdelete?login=${row.getLogin()}')" class="btn btn-danger">delete</a></td>
				</tr>
			</c:forEach>			
		</table>
		<c:if test="${countTrips/visibleItems gt 1}">
			<br><br>
			<a href="${hostContext}/usertrip?pageNumber=1">1</a>
			&nbsp;...
			&nbsp;<a href="${hostContext}/usertrip?pageNumber=1">4</a>
			&nbsp;5
			&nbsp;<a href="${hostContext}/usertrip?pageNumber=1">6</a>
			&nbsp;...
			&nbsp;<a href="${hostContext}/usertrip?pageNumber=1">100</a>
		</c:if>
	</c:if>
	<script type="text/javascript">
		function checkDeleteItem(url) {
			if (confirm("Are you sure?")) {
				window.location.href = url;
			}
		}
		function selectVisibleItems(url) {
			var visibleItems = document.getElementById("idVisibleItems");
			window.location.href = url
				+ visibleItems.options[visibleItems.selectedIndex].value;
		}
	</script>
</body>
</html>
