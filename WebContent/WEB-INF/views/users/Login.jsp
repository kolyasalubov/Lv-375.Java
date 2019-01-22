<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style type="text/css">
 <%@include file="Profiles.css" %>
 <%@include file="Tables.css" %>
</style>
<title>Login</title>
</head>
<body background="big-one.jpg">

	<c:if test="${error ne null}">
		<p>
			<font color="red">${error}</font>
		</p>
	</c:if>
	<br>
	<div class="container">
		<form action="${pageContext.request.contextPath}/login" method="post">
			<div class="row">
				<div class="col-75">
					<h3>Login account</h3>
				</div>
			</div>

			<div class="row">
				<div class="col-75">
					Login <input type="text" name="login">
				</div>
			</div>
			<div class="row">
				<div class="col-75">
					Password <input type="password" name="password"> <br>
					<br>
				</div>
			</div>
			<div class="row">
				<input type="submit" value="Submit">
			</div>
			<br> <a href="${pageContext.request.contextPath}/usercreate">Create
				an account</a>
		</form>
	</div>
</body>
</html>
