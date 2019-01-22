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
<%--Navbar--%>
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
        	<table id="mytable" class="table table-bordred table-striped">
            	<thead>
                   <th>Email</th>
                   <th>Full name</th>
                   <th>Role</th>
                   <th>Is active</th>
              	</thead>
    			<tbody>
       			<c:forEach items="${usersListDto.users}" var="user">
			    <tr>
				    <td>
				    	${user.email}
				    </td>
				    <td>
				    	${user.fullName}
				    </td>
				    <td>
				    	${user.role}
				    </td>
				    <td>
				    	${user.isActive}
				    </td>
				    <td>
				    	<p data-placement="top" data-toggle="tooltip" title="Edit">				    		
							<a href="${pageContext.request.contextPath}/adminEditUser?userId=${user.id}" class="btn btn-primary btn-xs">
								<span class="glyphicon glyphicon-pencil"></span>							
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
</div>

</body>
</html>