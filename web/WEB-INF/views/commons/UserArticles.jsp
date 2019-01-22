<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%> --%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="hostUrl">${pageContext.request.requestURL}</c:set>
<c:set var="hostUri">${pageContext.request.requestURI}</c:set>
<c:set var="hostContext" value="${fn:substring(hostUrl, 0, fn:length(hostUrl) - fn:length(hostUri))}${pageContext.request.contextPath}" />
<html>
<head>
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
    <script src="//netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
    <style type="text/css">
        <%@include file="../../../resources/css/bootstrap.min.css"%>
        <%@include file="../../../resources/css/common.css"%>
    </style>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Your Articles</title>
</head>
<body>
<div class="one"><h1>Welcome to your profile</h1></div>
<a class="btn btn-outline-dark" id="log" href="${pageContext.request.contextPath}/logout">Logout</a>
<a class="btn btn-outline-primary" id="editprofile" href="${pageContext.request.contextPath}/useredit">Edit Profile</a>
<br><br>
<a href="${pageContext.request.contextPath}/articleadd" class="createarticle" data-title="Add new Article"></a>
<br><br>
<select id="idvisibleArticle" class="custom-select" onchange="selectVisibleItems('${hostContext}/articlescount?visibleArticle=')">
    <option value="100000"
            <c:if test="${visibleArticle eq '100000'}">
                selected="selected"
            </c:if>
    >all items</option>
    <option value="5"
            <c:if test="${visibleArticle eq '5'}">
                selected="selected"
            </c:if>
    >5 items</option>
    <option value="10"
            <c:if test="${visibleArticle eq '10'}">
                selected="selected"
            </c:if>
    >10 items</option>
    <option value="25"
            <c:if test="${visibleArticle eq '25'}">
                selected="selected"
            </c:if>
    >25 items</option>
</select>
<br><br>
<c:set var="countArticles" value="${usersArticleDto.articles}"/>
<c:if test="${countArticles ne null && countArticles.size() gt 0}">
    <table border="1" class="table table-striped" id="tableArticle">
        <tr>
            <th scope="col">Name</th>
            <th scope="col">Description</th>
            <th scope="col">Url</th>
            <th scope="col">Edit</th>
            <th scope="col">Delete</th>
        </tr>
        <c:forEach var="row" items="${countArticles}">
            <tr>
                <td>${row.getName()}</td>
                <td>${row.getDescription()}</td>
                <td>${row.getUrl()}</td>
                <td class="text-center"><a href="${pageContext.request.contextPath}/articleedit?name=${row.getName()}"class='btn btn-info btn-xs'><span class="glyphicon glyphicon-edit"></span>Edit</a></td>
                <td><a class="btn btn-danger btn-xs" onclick="checkDeleteItem('${hostContext}/articledelete?name=${row.getName()}')" ><span class="glyphicon glyphicon-remove"></span>Del</a></td>
            </tr>
        </c:forEach>
    </table>

        <br><br>

</c:if>
<ul class="pagination" id="pagin">
    <li class="page-item"><a class="page-link" href="${hostContext}/userarticle?pageNumber=1" >1</a></li>
    <li class="page-item"><a class="page-link" href="${hostContext}/userarticle?pageNumber=2" >2</a></li>
    <li class="page-item"><a class="page-link" href="${hostContext}/userarticle?pageNumber=3" >3</a></li>
    <li class="page-item"><a class="page-link" href="${hostContext}/userarticle?pageNumber=4" >4</a></li>
    <li class="page-item"><a class="page-link" href="${hostContext}/userarticle?pageNumber=6" >6</a></li>
    <li class="page-item"><a class="page-link" href="${hostContext}/userarticle?pageNumber=7" >7</a></li>
    <li class="page-item"><a class="page-link" href="${hostContext}/userarticle?pageNumber=8" >8</a></li>
    <li class="page-item"><a class="page-link" href="${hostContext}/userarticle?pageNumber=9" >9</a></li>
    <li class="page-item"><a class="page-link" href="${hostContext}/userarticle?pageNumber=10">10</a></li>
</ul>
<script type="text/javascript">
    function checkDeleteItem(url) {
        if (confirm("Are you sure?")) {
            window.location.href = url;
        }
    }
    function selectVisibleItems(url) {
        var visibleArticle = document.getElementById("idvisibleArticle");
        window.location.href = url
            + visibleArticle.options[visibleArticle.selectedIndex].value;
    }
</script>
</body>
</html>