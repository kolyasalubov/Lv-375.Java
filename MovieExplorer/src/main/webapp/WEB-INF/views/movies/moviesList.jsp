<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Movies list </title>
<style type="text/css">
	<%@include file="moviesList.css" %>
</style>
</head>
<body>
	<%--Navbar	--%>
	<jsp:include page="../commons/header.jsp">
		<jsp:param value="${dropdownValues }" name="dropdownValues"/>
	</jsp:include>
	<%--Body --%>
	<div class="crane-listview-section">
    <div class="container">
    	<form method="GET" action="${pageContext.request.contextPath}/moviesList" >
    		
    		<select class="pagination-form btn btn-primary" name="pageOffset" onchange="this.form.submit()">
				  <option value="3" ${moviesListDto.pageOffset == 3 ? 'selected' : ''}>3</option>
				  <option value="5" ${moviesListDto.pageOffset == 5 ? 'selected' : ''}>5</option>
				  <option value="10" ${moviesListDto.pageOffset == 10 ? 'selected' : ''}>10</option>
				  <option value="15" ${moviesListDto.pageOffset == 15 ? 'selected' : ''}>15</option>
			</select>
    	</form>
       	<c:forEach items="${moviesListDto.movies}" var="movie">
       		<div class="col">
		        <div class="crane-listing">
				    <div class="crane-img-size">
					  <a href="${pageContext.request.contextPath}/movie?movieId=${movie.id}"> 
					  	<img src="${movie.posterUrl}" alt=" " class="img-responsive">
					  </a>
					</div>
				    <div class="crane-text size ">
					    <h1><a href="${pageContext.request.contextPath}/movie?movieId=${movie.id}">${movie.title}</a></h1>
						<span class="imdbRatingPlugin" data-user="ur98022464" data-title="${movie.imdbMovieID}" data-style="p2">
							<a href="https://www.imdb.com/title/${movie.imdbMovieID}">
								<img src="https://ia.media-imdb.com/images/G/01/imdb/plugins/rating/images/imdb_38x18.png" />
							</a>
						</span>
						<p class="news">${movie.information}</p>
					</div>
					<div class="clearfix"></div>
				 </div>
	        </div>
        </c:forEach>
    
    	<ul class="pagination">
    		<c:if test="${moviesListDto.pageCount != 1}">
    		<c:if test="${moviesListDto.currentPage != 1}">
	            <li class="page-item">
	            	<a class="page-link" 
	                	href="${pageContext.request.contextPath}/moviesList?page=${moviesListDto.currentPage-1}&pageOffset=${moviesListDto.pageOffset}">
	                	Previous</a>
	            </li>
	        </c:if>

	        <c:forEach begin="1" end="${moviesListDto.pageCount}" var="i">
	            <c:choose>
	                <c:when test="${moviesListDto.currentPage eq i}">
	                    <li class="page-item active"><a class="page-link">
	                            ${i} <span class="sr-only">(current)</span></a>
	                    </li>
	                </c:when>
	                <c:otherwise>
	                    <li class="page-item">
			            	<a class="page-link" 
			                	href="${pageContext.request.contextPath}/moviesList?page=${i}&pageOffset=${moviesListDto.pageOffset}">
			                	${i}</a>
	                </c:otherwise>
	            </c:choose>
	        </c:forEach>
	
	        <c:if test="${moviesListDto.currentPage lt moviesListDto.pageCount}">
	            <li class="page-item">
	            	<a class="page-link" 
	                	href="${pageContext.request.contextPath}/moviesList?page=${moviesListDto.currentPage+1}&pageOffset=${moviesListDto.pageOffset}">
	                	Next</a>
	            </li>
	        </c:if>  
	        </c:if>                   
    	</ul>
    </div>
</div>
</body>
<script>
	<%@include file="../commons/imdbPlugin.js" %>
</script>
</html>
