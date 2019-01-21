<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="hostUrl">${pageContext.request.requestURL}</c:set>
<c:set var="hostUri">${pageContext.request.requestURI}</c:set>
<c:set var="hostContext" value="${fn:substring(hostUrl, 0, fn:length(hostUrl) - fn:length(hostUri))}${pageContext.request.contextPath}" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%-- <link rel="stylesheet" type="text/css" media="screen" href="${hostContext}/resources/css/style.css"> --%>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/style.css">
<title>Insert title here</title>
</head>
<body>
	<br><br>
	hostContext = ${hostContext}
	<br><br>
	pageContext.request.contextPath: ${pageContext.request.contextPath}
	<br><br>
	<h1>Login</h1>
	<c:if test="${error ne null}">
		<p>
			<font color="red">${error}</font>
		</p>
	</c:if>
	<br><br>
	<form action="${pageContext.request.contextPath}/login" method="post">
		Login: <input type="text" name="login">
		<br><br>
		Password: <input type="password" name="password">
		<br><br>
		<input type="submit" name="submit" value="Signin"> <br>
	</form>

</body>
</html>
