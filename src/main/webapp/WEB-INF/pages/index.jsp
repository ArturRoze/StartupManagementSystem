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
            <h2 class="text-center mt-2">List of all startups</h2>
            <hr class="my-2">
            <div class="row">
                <c:forEach var="item" items="${startups}">
                    <%@include file="item.jsp" %>
                </c:forEach>
            </div>
            <hr class="mt-2 mb-4">
            <%-- TODO make buttons 'create_startup' and 'view_all_startups' --%>
            <div class="btn-toolbar align-content-center">
                <a class="btn btn-success btn-lg"
                   href="${pageContext.request.contextPath}/startups/new/startup" role="button">
                    Add startup
                </a>
                <a class="btn btn-primary ml-3"
                   href="${pageContext.request.contextPath}/offers/new/offer" role="button">
                    Add offer
                </a>
                <a class="btn btn-outline-dark ml-auto"
                   href="${pageContext.request.contextPath}/startups" role="button">
                    View All startups
                </a>
            </div>
        </div>
    </div>
    <%@include file="footer.jsp" %>
</div>

</body>
</html>