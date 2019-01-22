<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css">
	<%@include file="adminMovieList.css" %>
</style>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

</head>
<body>
	<jsp:include page="header.jsp">
		<jsp:param value="${dropdownValues }" name="dropdownValues"/>
	</jsp:include>
<div class="container portfolio">
	<div class="bio-info">
		<div class="row">
		<div class="col-md-12">
	    <div class="table-responsive">
	    	<c:if test="${error ne null}">
					<p>
						<font color="red">${error}</font>
					</p>
			</c:if>
			
			<form method="GET" action="${pageContext.request.contextPath}/adminMovieList" >
    		<select class="pagination-form btn btn-primary" name="pageOffset" onchange="this.form.submit()">
				  <option value="3" ${moviesListDto.pageOffset == 3 ? 'selected' : ''}>3</option>
				  <option value="5" ${moviesListDto.pageOffset == 5 ? 'selected' : ''}>5</option>
				  <option value="10" ${moviesListDto.pageOffset == 10 ? 'selected' : ''}>10</option>
				  <option value="15" ${moviesListDto.pageOffset == 15 ? 'selected' : ''}>15</option>
			</select>
    		</form>
        	<table id="mytable" class="table table-bordred table-striped">
            	<thead>
                   <th>Movie Title</th>
                   <th>Edit</th>
                   <th>Delete</th>
              	</thead>
    			<tbody>
       			<c:forEach items="${moviesListDto.movies}" var="movie">
			    <tr>
				    <td>
					  	<a href="${pageContext.request.contextPath}/movie?movieId=${movie.id}">
				    		${movie.title}
				    	</a>
				    </td>
				    <td>
				    	<p data-placement="top" data-toggle="tooltip" title="Edit">				    		
							<a href="${pageContext.request.contextPath}/editMovie?movieId=${movie.id}" class="btn btn-primary btn-xs">
								<span class="glyphicon glyphicon-pencil"></span>							
							</a>
				    	</p>
				    </td>
				    <td>
				    	<p data-placement="top" data-toggle="tooltip" title="Delete">
				    		<a href="${pageContext.request.contextPath}/deleteMovie?movieId=${movie.id}" class="btn btn-danger btn-xs">
								<span class="glyphicon glyphicon-trash"></span>							
							</a>
				    	</p>
				    </td>
				    </tr> 
			    </c:forEach>
			    </tbody>
        	</table>
		<div class="clearfix"></div>        
        </div>
        </div>
		</div>	
	</div>
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

</body>
</html>