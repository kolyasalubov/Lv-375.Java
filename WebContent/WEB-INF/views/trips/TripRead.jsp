<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%> --%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style type="text/css">
 <%@include file="Profiles.css" %>
 <%@include file="Tables.css" %>
</style>
<title>Trip</title>
</head>
<body>
	<br>
	<div class="container">
		<form action="${pageContext.request.contextPath}/alltrips"
					method="post">
						<div class="row">
				<div class="col-75">
					<h1>Trip details</h1>
				</div>
			</div>
			<div class="row">
				<div class="col-75">
					Article id: <input type="text" name="idTrip"
						value="${tripDto.idTrip}" readonly />
				</div>
			</div>
			<div class="row">
				<div class="col-75">
					Country: <input type="text" name="country"
						value="${tripDto.country}" readonly />
				</div>
			</div>
			<div class="row">
				<div class="col-75">
					Article title: <input type="text" name="title"
						value="${tripDto.title}" readonly />
				</div>
			</div>
			<div class="row">
				<div class="col-75">
					Visit date: <input type="text" name="date" value="${tripDto.date}"
						readonly />
				</div>
			</div>
			<div class="row">
				<div class="col-75">
					Description: <input type="text" name="description"
						value="${tripDto.description}" readonly />
				</div>
			</div>
			<div class="row">
				<div class="col-75">
					Author's alias: <input type="text" name="authors_alias"
						value="${tripDto.authors_alias}" readonly />
				</div>
			</div>
			<br> <a href="${pageContext.request.contextPath}/alltrips">Finish
				reading</a>
		</form>
	</div>
</body>
</html>