<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style type="text/css">
 <%@include file="Profiles.css" %>
 <%@include file="Tables.css" %>
</style>
<title>User profile</title>
</head>
<body>

	<c:if test="${error ne null}">
		<p>
			<font color="red">${error}</font>
		</p>
	</c:if>
	<br>
<body>

	<div class="container">
		<form action="${pageContext.request.contextPath}/usercreate" method="post">
					<div class="row">
				<div class="col-75">
					<h3>User profile</h3>
				</div>
			</div>
			
			<div class="row">
				<div class="col-75">
					Login <input type="text" name="login">
				</div>
			</div>
			<div class="row">
				<div class="col-75">
					Password <input type="password" name="password">
				</div>
			</div>
			<div class="row">
				<div class="col-75">
					Alias <input type="text" name="alias">
				</div>
			</div>
			    <div class="col-75">
			Role <select class="form-control" name="role">
				<option value="user">User</option>
				<option value="admin">Admin</option>
			</select> 
			<br>
				</div>
			
			<div class="row">
			<div class="col-75">
			<input type="submit" value="Confirm" /> 
			</div>
			</div>
			<br>
			<a href="${pageContext.request.contextPath}/login">Cancel</a>
		</form>
	</div>
</body>
</body>
</html>