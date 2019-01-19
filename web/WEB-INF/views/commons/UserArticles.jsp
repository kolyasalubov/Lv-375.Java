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
    <title>Insert title here</title>
</head>
<body>
<h1>UserArticles</h1>
<br><br>
contextPath = ${pageContext.request.contextPath}
<br><br>
requestURL = ${pageContext.request.requestURL}
<br><br>
requestURI = ${pageContext.request.requestURI}
<br><br>
hostContext = ${hostContext}

<br><br>
<a href="${pageContext.request.contextPath}/logout">logout</a>
<br><br>
User Login from session: ${loginDto.getUserName()}
<br><br>
User Login from userItemsDto: ${usersArticleDto.getUserName()}
<br><br>
<a href="${pageContext.request.contextPath}/articleadd">Create new Item</a>
<br><br>
<select id="idVisibleItems" onchange="selectVisibleItems('${hostContext}/articlescount?visibleItems=')">
    <option value="100000"
            <c:if test="${visibleItems eq '100000'}">
                selected="selected"
            </c:if>
    >all items</option>
    <option value="5"
            <c:if test="${visibleItems eq '5'}">
                selected="selected"
            </c:if>
    >5 items</option>
    <option value="10"
            <c:if test="${visibleItems eq '10'}">
                selected="selected"
            </c:if>
    >10 items</option>
    <option value="25"
            <c:if test="${visibleItems eq '25'}">
                selected="selected"
            </c:if>
    >25 items</option>
</select>
<br><br>
<c:set var="countArticles" value="${usersArticleDto.articles}"/>
<c:if test="${countArticles ne null && countArticles.size() gt 0}">
    <table border="1">
        <tr>
            <th>Name_Article</th>
            <th>Description_Article</th>
            <th>Url_Article</th>
            <th>Edit_Article</th>
            <th>Delete_Article</th>
        </tr>
        <c:forEach var="row" items="${countArticles}">
            <tr>
                <td>${row.getName()}</td>
                <td>${row.getDescription()}</td>
                <td>${row.getUrl()}</td>
                <td><a href="${pageContext.request.contextPath}/articleedit?Name=${row.getName()}">edit</a></td>
                <td><a href="#" onclick="checkDeleteItem('${hostContext}/articledelete?Name=${row.getName()}')">delete</a></td>
            </tr>
        </c:forEach>
    </table>
    <c:if test="${countItems/visibleItems gt 1}">
        <br><br>
        <a href="${hostContext}/userarticles?pageNumber=1">1</a>
        &nbsp;...
        &nbsp;<a href="${hostContext}/userarticles?pageNumber=1">4</a>
        &nbsp;5
        &nbsp;<a href="${hostContext}/userarticles?pageNumber=1">6</a>
        &nbsp;...
        &nbsp;<a href="${hostContext}/userarticles?pageNumber=1">100</a>
    </c:if>
</c:if>
<script type="text/javascript">
    function checkDeleteItem(url) {
        if (confirm("Are you sure?")) {
            window.location.href = url;
        }
    }
    function selectVisibleItems(url) {
        var visibleArticle = document.getElementById("idVisibleArticle");
        window.location.href = url
            + visibleArticle.options[visibleArticle.selectedIndex].value;
    }
</script>
</body>
</html>