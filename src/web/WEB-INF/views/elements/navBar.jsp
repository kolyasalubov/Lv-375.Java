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

    <style type="text/css">
        <%@include file="../../../resources/css/navbar.css" %>
    </style>

</head>
<body>

<div class="ui top fixed large menu bar" onload="setActive(${active})">
    <div class="item image">
        <img src="http://www.cscestrie.on.ca/wp-content/uploads/2018/09/calendar-2027122_1280.png">
    </div>
    <a class="item el" id="H" onclick="openPage('${pageContext.request.contextPath}/home?active=H')" >Home</a>
    <a class="item el" id="B" onclick="openPage('${pageContext.request.contextPath}/bookings?active=B')" >Bookings</a>
    <a class="item el" id="P" onclick="openPage('${pageContext.request.contextPath}/profile?active=P')" >Profile</a>

    <div class="right menu">
        <div class="item">
            <button class="negative ui button right floated btn" type="button"
                    onclick="openPage('${pageContext.request.contextPath}/logout')" > Log out </button>
        </div>
    </div>
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
