<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Startup: "${startup.name}"</title>
    <%@include file="header_config.jsp" %>
</head>
<body>
<div class="wrapper">
    <div class="content">
        <%@include file="navbar.jsp" %>
        <div class="container">
            <c:set var="isOwner" value="${startup.user.id == current_user_id}"/>
            <h2 class="text-center">Details about startup#${startup.id}</h2>
            <div class="row">
                <div class="col-md-6 padd05">
                    <div class="btn-block">
                        <table class="table table-hover">
                            <tr>
                                <th scope="row">Name</th>
                                <td>
                                    <h4>${startup.name}</h4>
                                </td>
                            </tr>
                            <tr>
                                <th scope="row">Description</th>
                                <td>${startup.description}</td>
                            </tr>
                        </table>
                    </div>
                </div>
                <div class="col-md-6 padd05">
                    <div class="btn-block">
                        <table class="table table-hover">
                            <tr>
                                <th scope="row">Industry</th>
                                <td>${startup.industry.name}</td>
                            </tr>
                            <tr>
                                <th scope="row">Country</th>
                                <td>${startup.country.name}</td>
                            </tr>
                            <tr>
                                <th style="width: 25%" scope="row">Registration</th>
                                <td>${startup.registrationDate}</td>
                            </tr>
                        </table>
                    </div>
                </div>
            </div>

            <div class="navbar padd0">
                <div>
                    <form action="${pageContext.request.contextPath}/users/profile/${startup.user.id}" method="get">
                        <input class="btn btn-info" type="submit"
                               value="Go to user ${startup.user.firstName} ${startup.user.lastName} profile page">
                    </form>
                </div>

                <c:if test="${isOwner || isAdmin}">
                    <div>
                        <div class="d-lg-inline-block">
                            <form action="${pageContext.request.contextPath}/startups/${startup.id}/edit" method="get">
                                <input class="btn btn-primary ml-md-3 mb-md-0" type="submit" value="Update">
                            </form>
                        </div>
                        <div class="d-lg-inline-block">
                            <form action="${pageContext.request.contextPath}/startups/${startup.id}/delete"
                                  method="get">
                                <input class="btn btn-danger ml-md-3 mb-md-0" type="submit" value="Delete">
                            </form>
                        </div>
                    </div>
                </c:if>
            </div>
            <hr class="my-4">
            <%@include file="back_btn.jsp"%>
        </div>
    </div>
    <%@include file="footer.jsp" %>
</div>
</body>
</html>