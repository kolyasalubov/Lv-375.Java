<%--
  Created by IntelliJ IDEA.
  User: kom
  Date: 22.01.2019
  Time: 6:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Error</title>
</head>
<body>
<c:if test="${error ne null}">
    <p>
        <font color="red">${error}</font>
    </p>
</c:if>
</body>
</html>
