<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Head Page</title>
    <%@include file="header_config.jsp" %>
</head>
<body>
<div class="wrapper">
    <div class="content">
        <%@include file="navbar.jsp" %>
        <div class="container">
            <h1 class="text-center mt-4">Welcome</h1>
            <h2 class="text-center mt-2">Recent startups</h2>
            <hr class="row my-2">
            <div class="row">
                <c:forEach var="item" items="${startups}">
                    <%@include file="item.jsp" %>
                </c:forEach>
            </div>
            <div class="row navbar bg-bar px-4 mt-3 mb-4">
                <%@include file="buttons/add_startup_button.jsp" %>
                <%@include file="buttons/add_offer_button.jsp" %>
                <div class="mx-auto"></div>
                <%@include file="buttons/all_startups_button.jsp" %>
            </div>
        </div>
    </div>
    <%@include file="footer.jsp" %>
</div>
</body>
</html>