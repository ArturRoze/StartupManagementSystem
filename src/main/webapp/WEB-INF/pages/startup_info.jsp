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
            <c:set var="isOwner" value="${startup.user.id == current_user_id && !isAdmin}"/>
            <h2 class="text-center">Details about<c:if test="${isOwner}"> my</c:if>
                startup <span class="badge badge-light text-secondary">id:${startup.id}</span></h2>
            <div class="row justify-content-center">
                <div class="col-md-8 border border-gr rounded mb-4 p-3">
                    <div class="btn-block">
                        <table class="table table-hover mb-0">
                            <tr>
                                <th class="w-25 bg-light" scope="row">Name</th>
                                <td>
                                    <h4>${startup.name}</h4>
                                </td>
                            </tr>
                            <tr>
                                <th class="bg-light" scope="row">Description</th>
                                <td>${startup.description}</td>
                            </tr>
                            <tr>
                                <th class="bg-light" scope="row">Budget</th>
                                <td>${startup.budget}</td>
                            </tr>
                            <tr>
                                <th class="bg-light">Owner</th>
                                <td><a class="btn btn-light text-success border-success float-left" role="button"
                                       href="${pageContext.request.contextPath}/users/profile/${startup.user.id}">
                                    Go to<c:choose><c:when test="${isOwner}"> my</c:when>
                                    <c:otherwise> owner</c:otherwise></c:choose> profile</a>
                                    <c:if test="${(startup.user.firstName != null || startup.user.lastName != null)
                                    && !isOwner}">
                                        <div class="ml-md-4 mt-2 float-left text-secondary">user id:
                                            #${startup.user.id}</div>
                                    </c:if>
                                </td>
                            </tr>
                            <tr>
                                <th class="w-25 bg-light" scope="row">Industry</th>
                                <td>${startup.industry.name}</td>
                            </tr>
                            <tr>
                                <th class="bg-light" scope="row">Country</th>
                                <td>${startup.country.name}</td>
                            </tr>
                            <tr>
                                <th class="bg-light" scope="row">Registration</th>
                                <td>
                                    <c:set var="dateOf" value="${startup.registrationDate}"/>
                                    <%@include file="patterns/date_pattern.jsp" %>
                                </td>
                            </tr>
                        </table>
                    </div>
                </div>
            </div>

            <div class="row justify-content-center">
                <div class="navbar col-md-8 bg-bar px-3">
                    <%@include file="buttons/back_button.jsp" %>
                    <c:if test="${isOwner || isAdmin}">
                        <div class="d-inline-flex">
                            <a href="${pageContext.request.contextPath}/startups/${startup.id}/edit"
                               class="btn btn-lg btn-info mx-2 btn-with-icon float-left" role="button">
                                <div><i class="material-icons">create</i></div>
                                <span>Edit startup</span></a>
                            <form action="${pageContext.request.contextPath}/startups/${startup.id}/delete"
                                  class="my-auto" method="post">
                                <input type="number" name="current_user_id" value="${current_user_id}" hidden>
                                <input type="text" name="is_admin" value="${isAdmin}" hidden>
                                <button class="btn btn-lg btn-danger mx-2 btn-with-icon float-right"
                                        type="submit" value="Delete">
                                    <div><i class="material-icons">delete_sweep</i></div>
                                    <span>Delete startup</span></button>
                            </form>
                        </div>
                    </c:if>
                </div>
            </div>
        </div>
    </div>
    <%@include file="footer.jsp" %>
</div>
</body>
</html>