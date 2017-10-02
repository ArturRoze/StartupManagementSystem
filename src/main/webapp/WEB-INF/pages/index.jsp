<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Head Page</title>
    <%@include file="header.jsp" %>
</head>
<body>
<%@include file="navbar.jsp" %>
<div class="container">
    <h1 class="bd-title text-center">Welcome Page</h1>
    <h2 class="text-center">List of all products</h2>
    <div class="row">
        <div class="col-md-1">Id</div>
        <div class="col-md-2">Name</div>
        <div class="col-md-3">Description</div>
        <div class="col-md-1">Industry</div>
        <div class="col-md-1">Owner</div>
        <div class="col-md-1">Budget</div>
        <div class="col-md-1">Registration</div>
        <div class="col-md-1">Country</div>
        <div class="col-md-1">To startup</div>
    </div>
    <div class="row">
        <c:forEach var="startup" items="${startups}">
            <div class="col-md-1">${startup.id}</div>
            <div class="col-md-2">${startup.name}</div>
            <div class="col-md-3">${startup.description}</div>
            <div class="col-md-1">${startup.industry.name}</div>
            <div class="col-md-1">${startup.user.firstName} ${startup.user.lastName}</div>
            <div class="col-md-1">${startup.budget}</div>
            <div class="col-md-1">${startup.registrationDate}</div>
            <div class="col-md-1">${startup.country.name}</div>
            <div class="col-md-1">
                <form action="${pageContext.request.contextPath}/startups/${startup.id}" method="get">
                    <input type="submit" value="Show this startup" class="btn btn-primary">
                </form>
            </div>
        </c:forEach>
    </div>

</div>
</body>
</html>
