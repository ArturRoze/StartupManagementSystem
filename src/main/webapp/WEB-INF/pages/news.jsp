<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>News</title>
    <%@include file="header_config.jsp" %>
</head>
<body>
<div class="wrapper">
    <div class="content">
        <%@include file="navbar.jsp" %>
        <div class="container">
            <h1 class="text-center mt-4">News</h1>
            <hr class="row">
            <div align="center">
                <div class="row">
                        <c:forEach var="item" items="${news_list}">
                            <%@include file="item.jsp" %>
                        </c:forEach>
                </div>
                <hr class="row">
                <%@include file="pagination.jsp" %>
            </div>
        </div>
    </div>
    <%@include file="footer.jsp" %>
</div>
</body>
</html>