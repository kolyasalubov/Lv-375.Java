<%--
  Created by IntelliJ IDEA.
  User: kom
  Date: 20.01.2019
  Time: 22:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>BOOKING_PROFILE</title>

    <link href="//netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.min.css" rel="stylesheet"
          type="text/css"/>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.2.4/semantic.min.css" rel="stylesheet"
          type="text/css"/>
</head>
<body>

<c:if test="${error ne null}">
    <p>
        <font color="red">${error}</font>
    </p>
</c:if>

<form action="${pageContext.request.contextPath}/booking-create" method="POST">

    <jsp:include page='./bookingProfile.jsp'>
        <jsp:param name="roomNumber" value='${roomNumber}' />
    </jsp:include>

    <button class="negative ui button left floated" onclick="goBack()" >
        Cancel
    </button>
    <button class="positive ui button right floated" type="submit" > Save </button>

</form>


</body>
</html>

