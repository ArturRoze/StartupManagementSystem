<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>All of startups</title>
    <%@include file="header_config.jsp" %>
</head>
<body>
<div class="wrapper">
    <div class="content">
        <%@include file="navbar.jsp" %>
        <div class="container">
            <h2 class="text-center mt-2">List of all startups</h2>
            <hr class="my-2">
            <div class="row">
                <c:forEach var="item" items="${startups}">
                    <%@include file="item.jsp" %>
                </c:forEach>
            </div>
            <hr class="my-4">
            <%@include file="back_btn.jsp" %>
        </div>
    </div>
    <%@include file="footer.jsp" %>
</div>
</body>
</html>
