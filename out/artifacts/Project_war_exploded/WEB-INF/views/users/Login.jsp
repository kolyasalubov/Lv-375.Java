<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>
    <link rel="stylesheet" href = "Login.css">
    <meta http-equiv="Content-Type" content="text/html;">
    <meta charset="utf-8">
    <title>Insert title here</title>
</head>
<body>
<div id="container">
        <c:if test="${error ne null}">
            <p>
                <font color="red">${error}</font>
            </p>
        </c:if>
    <form action="${pageContext.request.contextPath}/login" method="post">
        <label for="username">Username:</label>
        <input type="text" id="username" name="username">
        <label for="password">Password:</label>
        <input type="password" id="password" name="password">
        <div id="lower">
            <input type="submit" value="Login">
        </div><!--/ lower-->
        </form>
</div>

</body>
</html>
