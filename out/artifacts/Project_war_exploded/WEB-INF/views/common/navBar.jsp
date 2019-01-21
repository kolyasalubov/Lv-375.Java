<%--
  Created by IntelliJ IDEA.
  User: kom
  Date: 21.01.2019
  Time: 7:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>NavBar</title>

    <link href="//netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.min.css" rel="stylesheet"
          type="text/css"/>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.2.4/semantic.min.css" rel="stylesheet"
          type="text/css"/>

</head>
<body>

<div class="ui top fixed menu" onload="setActive(${active})">
    <div class=" item">
        <%--<img src="/images/logo.png">--%>
    </div>
    <a class="item" id="H" onclick="openPage('${pageContext.request.contextPath}/home?active=H')" >Home</a>
    <a class="item" id="B" onclick="openPage('${pageContext.request.contextPath}/bookings?active=B')" >Bookings</a>
    <a class="item" id="P" onclick="openPage('${pageContext.request.contextPath}/profile?active=P')" >Profile</a>
</div>

<script type="text/javascript">
    <%@include file="../../../resources/js/openPage.js" %>
</script>

<script type="text/javascript">
    function setActive(id) {
        var el = document.getElementById(id);
        el.classList.add("active");
    }
</script>

</body>
</html>
