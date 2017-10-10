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
            <hr class="my-2">
            <div class="row">
                <c:forEach var="item" items="${startups}">
                    <%@include file="item.jsp" %>
                </c:forEach>
            </div>
            <hr class="mt-2 mb-4">
            <div class="btn-toolbar align-content-center">
                <a class="btn btn-success btn-lg btn-with-icon" role="button"
                   href="${pageContext.request.contextPath}/startups/new/startup">
                    <div>
                        <i class="material-icons">add_circle</i>
                    </div>
                    <span>Add startup</span>
                </a>
                <a class="btn btn-primary btn-lg ml-3 btn-with-icon" role="button"
                   href="${pageContext.request.contextPath}/offers/new/offer">
                    <div>
                        <i class="material-icons">add_circle</i>
                    </div>
                    <span>Add offer</span>
                </a>
                <a class="btn btn-dark btn-lg ml-auto btn-with-icon"
                   href="${pageContext.request.contextPath}/startups" role="button">
                    <div><i class="material-icons">view_list</i></div>
                    <span>View All startups</span>
                </a>
            </div>
        </div>
    </div>
    <%@include file="footer.jsp" %>
</div>
</body>
</html>