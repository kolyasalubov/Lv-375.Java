<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">
	<%@include file="movie.css" %>
</style>
<title>Insert title here</title>
</head>
<body> 
<%--Navbar	--%>
	<jsp:include page="../commons/header.jsp">
		<jsp:param value="${dropdownValues }" name="dropdownValues"/>
	</jsp:include>
<div class="container portfolio">
	<div class="bio-info">
		<div class="row">
			<div class="col-md-6">
				<div class="row">
					<div class="col-md-12">
						<div class="bio-image">
							<img src="${movie.posterUrl}" />
						</div>			
					</div>
				</div>	
			</div>
			<div class="col-md-6">
				<div class="bio-content">
					<div class="row">						
						<h1>${movie.title}</h1>
						<form action="${pageContext.request.contextPath}/movie" method="post">
							<input type="hidden" name="movieId" value="${movie.id}" >
							<c:if test="${movie.isFavourite eq true }">
								<input type="submit" name="submit" value="Added" 
									class="btn add_btn" disabled="true">
							</c:if>
							<c:if test="${movie.isFavourite eq false }">
								<input type="submit" name="submit" value="Watch Later" 
									class="btn add_btn">
							</c:if>
							<span class="imdbRatingPlugin" data-user="ur98022464" data-title="${movie.imdbMovieID}" data-style="p2">
							<a href="https://www.imdb.com/title/${movie.imdbMovieID}">
								<img src="https://ia.media-imdb.com/images/G/01/imdb/plugins/rating/images/imdb_38x18.png" />
							</a>
							</span>
						</form>
						<h6>${movie.information}</h6>
					</div>
				</div>
			</div>
		</div>	
	</div>
</div>
</body>
<script>
	<%@include file="../commons/imdbPlugin.js" %>
</script>
</html>