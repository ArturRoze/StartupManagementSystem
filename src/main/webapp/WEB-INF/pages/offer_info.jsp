<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Offer: ${offer.id}</title>
    <%@include file="header_config.jsp" %>
</head>
<body>
<div class="wrapper">
    <div class="content">
        <%@include file="navbar.jsp" %>
        <div class="container">
            <c:set var="isOwner" value="${offer.user.id == current_user_id && !isAdmin}"/>
            <h2 class="text-center">Offer info <span class="badge badge-light text-secondary">id:${offer.id}</span></h2>
            <div class="row justify-content-center">
                <div class="col-md-7 border border-gr rounded p-3 mb-4">
                    <div class="btn-block">
                        <table class="table table-hover mb-0">
                            <tr>
                                <th class="w-25 bg-light" scope="row">Offer ID</th>
                                <td>${offer.id}</td>
                            </tr>
                            <tr>
                                <th class="bg-light" scope="row">Description</th>
                                <td>${offer.description}</td>
                            </tr>
                            <tr>
                                <th class="bg-light" scope="row">Registration</th>
                                <td><c:set var="dateOf" value="${offer.registrationDate}"/>
                                    <%@include file="patterns/date_pattern.jsp" %>
                                </td>
                            </tr>
                            <tr>
                                <th class="bg-light" scope="row">Industry</th>
                                <td>${offer.industry.name}</td>
                            </tr>
                            <tr>
                                <th class="bg-light" scope="row">Country</th>
                                <td>${offer.country.name}</td>
                            </tr>
                            <tr>
                                <th class="bg-light" scope="row">Owner</th>
                                <td>
                                    <div>
                                        <a class="btn btn-light text-success border-success float-left" role="button"
                                            href="${pageContext.request.contextPath}/users/profile/${offer.user.id}">
                                        Go to<c:choose><c:when test="${isOwner}"> my</c:when>
                                        <c:otherwise> owner</c:otherwise></c:choose> profile</a>
                                        <c:if test="${(offer.user.firstName != null || offer.user.lastName != null)
                                    && !isOwner}">
                                            <div class="ml-md-4 mt-2 float-left text-secondary">user id:
                                                #${offer.user.id}</div>
                                        </c:if>
                                    </div>
                                </td>
                            </tr>
                        </table>
                    </div>
                </div>
            </div>

            <div class="row justify-content-center">
                <div class="navbar bg-bar col-md-7 px-3 mb-4">
                    <%@include file="buttons/back_button.jsp" %>
                    <c:if test="${isOwner || isAdmin}">
                        <div class="d-inline-flex">
                            <a href="${pageContext.request.contextPath}/offers/${offer.id}/edit"
                               class="btn btn-info mx-2 btn-with-icon float-left" role="button">
                                <div><i class="material-icons">create</i></div>
                                <span class="">Edit offer</span></a>
                            <form action="${pageContext.request.contextPath}/offers/${offer.id}/delete"
                                  class="my-auto" method="post">
                                <input type="number" name="current_user_id" value="${current_user_id}" hidden>
                                <input type="text" name="is_admin" value="${isAdmin}" hidden>
                                <button class="btn btn-danger ml-2 btn-with-icon float-right"
                                        type="submit" value="Delete">
                                    <div><i class="material-icons">delete_sweep</i></div>
                                    <span>Delete offer</span></button>
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
