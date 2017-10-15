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
            <h2 class="text-center">Details about<c:if test="${isOwner}"> my</c:if>
                startup <span class="badge badge-light">#${startup.id}</span></h2>
            <div class="row border border-gr rounded mb-4">
                <div class="col-md-6 p-2">
                    <div class="btn-block">
                        <table class="table table-hover mb-0">
                            <tr>
                                <th class="w-25" scope="row">Name</th>
                                <td>
                                    <h4>${startup.name}</h4>
                                </td>
                            </tr>
                            <tr>
                                <th scope="row">Description</th>
                                <td>${startup.description}</td>
                            </tr>
                            <tr>
                                <th>Owner</th>
                                <td><a class="btn btn-light text-success border-success float-left" role="button"
                                       href="${pageContext.request.contextPath}/users/profile/${startup.user.id}">
                                    Go to
                                    <c:choose>
                                        <c:when test="${isOwner}">
                                            my
                                        </c:when>
                                        <c:otherwise>
                                            owner
                                        </c:otherwise>
                                    </c:choose>
                                    profile</a>
                                    <c:if test="${(startup.user.firstName != null || startup.user.lastName != null)
                                    && !isOwner}">
                                        <div class="ml-md-4 mt-2 float-left text-secondary">user id: #${startup.user.id}</div>
                                    </c:if>
                                </td>
                            </tr>
                        </table>
                    </div>
                </div>
                <div class="col-md-6 p-2">
                    <div class="btn-block">
                        <table class="table table-hover mb-0">
                            <tr>
                                <th class="w-25" scope="row">Industry</th>
                                <td>${startup.industry.name}</td>
                            </tr>
                            <tr>
                                <th scope="row">Country</th>
                                <td>${startup.country.name}</td>
                            </tr>
                            <tr>
                                <th scope="row">Registration</th>
                                <td>
                                    <c:set var="dateOf" value="${startup.registrationDate}"/>
                                    <%@include file="patterns/date_pattern.jsp" %>
                                </td>
                            </tr>
                        </table>
                    </div>
                </div>
            </div>

            <div class="navbar bg-bar rounded px-3 mb-4">
                <%@include file="buttons/back_button.jsp" %>
            </div>
        </div>
    </div>
    <%@include file="footer.jsp" %>
</div>
</body>
</html>