<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta charset="ISO-8859-1">
<style type="text/css">
 <%@include file="bootstrap.min.css" %>
 <%@include file="LogIn.css" %>
</style>
<title>Sign Up</title>
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
                <a class="navbar-brand" href="#">Home</a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                  <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarNav">
                  <ul class="navbar-nav" id="superNavbar">
                    <li class="nav-item">
                      <a class="nav-link" href="#">Courses</a>
                    </li>
                  </ul>
                    </div>
              </nav>
              
            
    <div class="row" style="justify-content: center;">
        <div class="col-4">
            <form id="register" action="${pageContext.request.contextPath}/signup" method="post">
               <div class="form-group" style="margin-top: 100px;">
                  <label for="inputFirstname">First name</label> 
                  <input type="text" class="form-control" id="inputFirstName" name="firstname" placeholder="Enter FirstName">
               </div>
                <div class="form-group">
                    <label for="inputLastName">Last name</label>
                    <input type="text" class="form-control" id="inputLastName" name="lastname" placeholder="Enter LastName">
                </div>
                <div class="form-group">
                    <label for="inputUserName">User name</label>
                    <input type="text" class="form-control" id="inputUserName" name="username" placeholder="Enter UserName">
                </div>
                 <div class="form-group">
                    <label for="inputPassword">Password</label>
                    <input type="password" class="form-control" id="inputPassword" name="password" placeholder="Enter Password">
                </div>
                <div style="text-align: right">
                    <button type="submit" class="btn btn-success">Create</button>
                </div>
                </form>
        </div>
    </div>



</body>
</html>