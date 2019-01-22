<%--
  Created by IntelliJ IDEA.
  User: kom
  Date: 16.01.2019
  Time: 15:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>userProfileEdit</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <link href="//netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.min.css" rel="stylesheet"
          type="text/css"/>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.2.4/semantic.min.css" rel="stylesheet"
          type="text/css"/>

    <style type="text/css">
        <%@include file="../../../resources/css/yellowForm.css" %>
    </style>

    <style type="text/css">

        body > .grid {
            height: 100%;
        }

        .image {
            margin-top: -100px;
        }

        .column {
            max-width: 450px;
        }

    </style>

</head>
<body>


<div class="ui middle aligned center aligned grid" >
    <div class="column">

        <jsp:include page="${pageContext.request.contextPath}/WEB-INF/views/elements/errorMessage.jsp">
            <jsp:param name="error" value="${error}" />
        </jsp:include>

        <form class="ui large form" action = "${pageContext.request.contextPath}/${urlToPost}" method = "POST">
            <div class="ui stacked segment">

                <div class="field">
                    <div class="ui left icon input">
                        <i class="user icon"></i>
                        <input type="text" placeholder="Enter email" name="email"
                               value="${userDto.email}" maxlength="30" required>
                    </div>
                </div>

                <div class="field">
                    <div class="ui left icon input">
                        <i class="lock icon"></i>
                        <input type="password" placeholder="Enter Password" name="password"
                               value="${userDto.password}" maxlength="20" required>
                    </div>
                </div>

                <div class="field">
                    <div class="ui left icon input">
                        <i class="lock icon"></i>
                        <input type="password" placeholder="Enter Password again" name="passwordRepeat"
                               value="${userDto.password}" maxlength="20" required>
                    </div>
                </div>

                <div class="field">
                    <div class="ui left icon input">
                        <i class="address book outline icon"></i>
                        <input type="text" placeholder="Enter First Name" name="firstName"
                               value="${userDto.firstName}" maxlength="20" required>
                    </div>
                </div>

                <div class="field">
                    <div class="ui left icon input">
                        <i class="address book icon"></i>
                        <input type="text" placeholder="Enter Last Name" name="lastName"
                               value="${userDto.lastName}" maxlength="40" >
                    </div>
                </div>

                <div class="field">
                    <div class="ui left icon input">
                        <i class="phone icon"></i>
                        <input type="number" placeholder="Enter Phone" name="phone"
                               value="${userDto.phone}" maxlength="20" >
                    </div>
                </div>

                <div class="field">
                    <div class="ui left icon input">
                        <i class="suitcase icon"></i>
                        <input type="text" placeholder="Enter Position" name="position"
                               value="${userDto.position}" maxlength="12" >
                    </div>
                </div>

                <input type="hidden" name="idUser" value="${userDto.idUser}">

            </div>

            <button type="submit" class="ui right floated large submit button" id="login"> ${onSubmit} </button>
            <button type="button" class="ui left floated large negative button" onclick="goBack()"> Cancel </button>

        </form>

    </div>
</div>

<script>
    <%@include file="../../../resources/js/openPage.js" %>
</script>

</body>
</html>

