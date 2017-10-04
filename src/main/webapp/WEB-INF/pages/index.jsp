<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Head Page</title>
    <%@include file="header.jsp" %>
</head>
<body>
<div class="wrapper">
    <div class="content">
        <%@include file="navbar.jsp" %>
        <div class="container">
            <h1 class="text-center">Welcome</h1>
            <h2 class="text-center">List of all startups</h2>
            <hr>
            <div class="row">
                <c:forEach var="startup" items="${startups}">
                    <div class="col-md-4 padd05">
                        <a class="btn btn-light btn-block" href="${pageContext.request.contextPath}/startups/${startup.id}" role="button">
                            <div class="btn-block hight-7rem">
                                <i class="material-icons f24">work</i>
                                <div>Industry: ${startup.industry.name}</div>
                                <div>${startup.country.name}</div>
                                <div class="btn-block">
                                    <h4>${startup.name}</h4>
                                </div>
                                <div>${startup.description}</div>
                                <div>${startup.budget}</div>
                            </div>
                        </a>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
    <%@include file="footer.jsp" %>
</div>

</body>
</html>
