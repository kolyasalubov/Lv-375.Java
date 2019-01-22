<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css" integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">
<style type="text/css">
	<%@include file="addMovie.css" %>
</style>
<title>ADD movie</title>
</head>

<body>
	<div class="container">
	<div class="d-flex justify-content-center h-100">
		<div class="card">
			<div class="card-header">
				<h3>Edit movie</h3>
			</div>
			<div class="card-body">
				<c:if test="${error ne null}">
					<p>
						<font color="yellow">${error}</font>
					</p>
				</c:if>
				<form action="${pageContext.request.contextPath}/editMovie?movieID=${movie.id}" method="post">
					<input type="hidden" name="id" value="${movie.id}" >

					<div class="input-group form-group">
						<div class="input-group-prepend">
							<span class="input-group-text">
								<i class="fas fa-align-justify icon-size"></i>
							</span>
						</div>
						<input type="text" name="title" value="${movie.title}" class="form-control" placeholder="title">
					</div>
			
					<div class="input-group form-group">
						<div class="input-group-prepend">
							<span class="input-group-text">
								<i class="fab fa-imdb icon-size"></i>
							</span>
						</div>
						<input type="text" name="imdbMovieID" value="${movie.imdbMovieID}" class="form-control" placeholder="Imdb Movie ID">
					</div>
					
					<div class="input-group form-group">
						<div class="input-group-prepend">
							<span class="input-group-text">
								<i class="fas fa-link icon-size"></i>
							</span>
						</div>
						<input type="text" name="posterUrl" value="${movie.posterUrl}" class="form-control" placeholder="Poster url">
					</div>
					
					<div class="input-group form-group">
						<div class="input-group-prepend">
							<span class="input-group-text">
								<i class="fas fa-pen icon-size"></i>
							</span>
						</div>
						<textarea rows="5" name="information" class="form-control heigh-control" >${movie.information}</textarea>
					</div>
					<div class="form-group">
						<a href="${pageContext.request.contextPath}/adminMovieList" class="btn cancel_btn">Cancel</a>
						<input type="submit" name="submit" value="Edit" class="btn float-right add_btn">
					</div>
				</form>
			</div>
		</div>
	</div>
</div>


</body>
</html>