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
            <h2 class="text-center">Details about <c:if test="${isOwner}">your</c:if>
                startup <span class="badge badge-light">${startup.id}</span></h2>
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
                                <td>
                                    <c:set var="dateOf" value="${startup.registrationDate}"/>
                                    <%@include file="date.jsp" %>
                                </td>
                            </tr>
                        </table>
                    </div>
                </div>
            </div>

            <div class="navbar padd0">
                <c:choose>
                    <c:when test="${isOwner || isAdmin}">
                        <c:choose>
                            <c:when test="${isOwner}">
                                <a class="btn btn-lg btn-success btn-with-icon" role="button"
                                   href="${pageContext.request.contextPath}/users/profile/${startup.user.id}">
                                    <div>
                                        <i class="material-icons">settings</i>
                                    </div>
                                    <span>My profile</span></a>
                                </a>
                            </c:when>
                            <c:otherwise>
                                <a class="btn btn-lg btn-success btn-with-icon" role="button"
                                   href="${pageContext.request.contextPath}/users/profile/${startup.user.id}">
                                    <div>
                                        <i class="material-icons">settings</i>
                                    </div>
                                    <span>Go to <b>${startup.user.firstName} ${startup.user.lastName}</b> profile</span>
                                </a>
                            </c:otherwise>
                        </c:choose>
                        <div>
                            <a class="btn btn-lg btn-primary mb-md-0 d-lg-inline-block btn-with-icon" role="button"
                               href="${pageContext.request.contextPath}/startups/${startup.id}/edit">
                                <div><i class="material-icons">update</i></div>
                                <span>Update</span>
                            </a>
                            <a class="btn btn-lg btn-danger ml-md-2 mb-md-0 d-lg-inline-block btn-with-icon" role="button"
                               href="${pageContext.request.contextPath}/startups/${startup.id}/delete">
                                <div><i class="material-icons">delete_forever</i></div>
                                <span>Delete</span>
                            </a>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <a class="btn btn-lg btn-success btn-with-icon" role="button"
                           href="${pageContext.request.contextPath}/users/profile/${startup.user.id}">
                            <div><i class="material-icons">info</i></div>
                            <span>Show <b>${startup.user.firstName} ${startup.user.lastName}</b> profile</span>
                        </a>
                    </c:otherwise>
                </c:choose>
            </div>
            <hr class="my-4">
            <%@include file="back_btn.jsp" %>
        </div>
    </div>
    <%@include file="footer.jsp" %>
</div>
</body>
</html>