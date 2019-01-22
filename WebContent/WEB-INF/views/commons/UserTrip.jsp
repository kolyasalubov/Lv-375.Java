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
<title>Your trips</title>
</head>
<body>
	<%--
	<br>
	contextPath = ${pageContext.request.contextPath}
	<br>
	requestURL = ${pageContext.request.requestURL}
	<br>
	requestURI = ${pageContext.request.requestURI}
	<br>
	hostContext = ${hostContext}

	
	
	User Login from session: ${loginDto.getLogin()}
	<br><br>
	User Login from userTripsDto: ${userTripDto.getUserLogin()}
	<br><br>
--%>	
	<div class="topnav">
		<a href="${pageContext.request.contextPath}/tripcreate">Create new trip</a>
		<a href="${pageContext.request.contextPath}/alltrips">To see all travel story</a>
		<div class="topnav-right">
		<a href="${pageContext.request.contextPath}/useredit">My profile</a>
		<a href="${pageContext.request.contextPath}/logout">Logout</a>
	</div>
	</div>
	<br><br>
	
	<%-- 
<div class="dropdown">
  <button class="dropbtn">all items</button>
  <div class="dropdown-content">
  <a href="#">5 trips</a>
  <a href="#">10 trips</a>
  <a href="#">25 trips</a>
  </div>
</div>
	--%>
	
	
<%-- 
	<div class="dropdown">
	<select id="idVisibleItems" onchange="selectVisibleItems('${hostContext}/tripcount?visibleItems=')">
		<option value="100000"
				<c:if test="${visibleItems eq '100000'}">
					selected="selected"
				</c:if>
			>all trips</option>
			
		<option value="5" 
				<c:if test="${visibleItems eq '5'}">
					selected="selected"
				</c:if>
			>5 trips</option>
		<option value="10"
				<c:if test="${visibleItems eq '10'}">
					selected="selected"
				</c:if>
			>10 trips</option>
		<option value="25"
				<c:if test="${visibleItems eq '25'}">
					selected="selected"
				</c:if>
			>25 trips</option>
	</select>
	</div>
--%>	
	

	<c:set var="currentTrips" value="${userTripDto.trips}"/>
	<c:if test="${currentTrips ne null && currentTrips.size() gt 0}">
		<table id = trips border="1">
			<tr>
				<th>Id_Trip</th> 
				<th>Country</th>
				<th>Title</th>
				<th>Date</th>
				<th>Description</th>
				<th>Authors_alias</th>
				<th>Edit_trip</th>
				<th>Delete_trip</th>
			</tr>
			<c:forEach var="row" items="${currentTrips}">
				<tr>
 				<td>${row.getIdTrip()}</td>     
					<td>${row.getCountry()}</td>
					<td>${row.getTitle()}</td>
					<td>${row.getDate()}</td>
					<td>${row.getDescription()}</td>
					<td>${row.getAuthors_alias()}</td>
					<td><a href="${pageContext.request.contextPath}/tripedit?idTrip=${row.getIdTrip()}" class="btn btn-primary">edit</a></td>
					<td><a href="${pageContext.request.contextPath}/tripdelete?idTrip=${row.getIdTrip()}" onclick="checkDeleteItem('${hostContext}/tripdelete?idTrip=${row.getIdTrip()}')" class="btn btn-danger">delete</a></td>
		<%--		<td><a href="#" onclick="checkDeleteItem('${hostContext}/tripdelete?idTrip=${row.getIdTrip()}')">delete</a></td>
		--%>		
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
		
		}
	</script>
</body>
</html>
