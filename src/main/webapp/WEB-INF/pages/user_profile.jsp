<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>User profile ${user.id}</title>
    <%@include file="header_config.jsp" %>
</head>
<body>
<div class="wrapper">
    <div class="content">
        <%@include file="navbar.jsp" %>
        <div class="container">

            <c:set var="isOwner" value="${user.id == current_user_id}"/>
            <sec:authorize access="hasRole('ADMIN')">
                <c:set var="isAdmin" value="true"/>
            </sec:authorize>

            <h2 class="text-center"><c:choose>
                <c:when test="${isOwner}">My</c:when><c:otherwise>User</c:otherwise></c:choose> profile <small>[id: ${user.id}]</small>
            </h2>
            <div class="row border border-gr rounded">
                <%-- Left part of user's data --%>
                <div class="col-md-6 p-2">
                    <div class="btn-block">
                        <table class="table mb-0">
                            <tr>
                                <th class="bg-light w-25" scope="row">Login</th>
                                <td class="bg-light">
                                    <h4 class="text-success">${user.login}</h4>
                                </td>
                            </tr>
                            <c:if test="${isOwner}">
                                <tr>
                                    <th class="bg-light">Password</th>
                                    <td>
                                        <div class="text-gr mt-1 float-left"><em>[hidden]</em></div>
                                        <a class="btn btn-sm btn-light ml-4 text-danger float-left"
                                           role="button" data-toggle="tooltip" data-placement="top" data-html="true"
                                           title="<em>Currently unavailable.</em><br>Please go to 'Edit profile' to change your password."
                                           disabled>
                                            change password</a>
                                    </td>
                                </tr>
                            </c:if>
                            <tr>
                                <th class="bg-light">Registered</th>
                                <td>
                                    <c:set var="dateOf" value="${user.registrationDate}"/>
                                    <%@include file="patterns/date_pattern.jsp" %>
                                </td>
                            </tr>
                            <tr>
                                <th class="bg-light">Notes</th>
                                <td>
                                    <c:set var="check" value="${user.description}"/>
                                    <%@include file="patterns/is_empty_pattern.jsp" %>
                                </td>
                            </tr>
                        </table>
                    </div>
                </div>
                <%-- Right part of user's data --%>
                <div class="col-md-6 p-2">
                    <div class="btn-block">
                        <table class="table table-hover mb-0">
                            <tr>
                                <th class="bg-light w-25">First name</th>
                                <td>
                                    <c:set var="check" value="${user.firstName}"/>
                                    <%@include file="patterns/is_empty_pattern.jsp" %>
                                </td>
                            </tr>
                            <tr>
                                <th class="bg-light">Last name</th>
                                <td>
                                    <c:set var="check" value="${user.lastName}"/>
                                    <%@include file="patterns/is_empty_pattern.jsp" %>
                                </td>
                            </tr>
                            <tr>
                                <th class="bg-light">Email</th>
                                <td>
                                    <c:set var="check" value="${user.email}"/>
                                    <%@include file="patterns/is_empty_pattern.jsp" %>
                                </td>
                            </tr>
                            <tr>
                                <th class="bg-light">Country</th>
                                <td>${user.country.name}</td>
                            </tr>
                        </table>
                    </div>
                </div>
            </div>

            <%-- Toolbar to hanle user --%>
            <div class="navbar bg-bar px-4 mt-3 mb-4 row">
                <%--<%@include file="buttons/all_startups_button.jsp" %>--%>
                <div class="mx-auto"></div>
                <c:if test="${isOwner && !isAdmin}">
                    <%@include file="buttons/add_startup_button.jsp" %>
                    <%@include file="buttons/add_offer_button.jsp" %>
                </c:if>
                <c:if test="${isOwner || isAdmin}">
                    <%@include file="buttons/update_user_profile_button.jsp" %>
                    <%@include file="buttons/delete_user_button.jsp" %>
                </c:if>
            </div>

            <%-- Tabs for startups and offers --%>
            <ul class="nav nav-tabs mt-5 row" id="myTab" role="tablist">
                <li class="nav-item mr-auto">&nbsp;</li>
                <li class="nav-item">
                    <a class="nav-link active" id="startups-tab" data-toggle="tab" href="#startups"
                       aria-controls="startups" aria-expanded="true" role="tab">List of all
                        <c:choose><c:when test="${isOwner}">my</c:when>
                            <c:otherwise>user's</c:otherwise></c:choose>
                        startups</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" id="offers-tab" data-toggle="tab" href="#offers"
                       aria-controls="offers" role="tab">List of all
                        <c:choose><c:when test="${isOwner}">my</c:when>
                            <c:otherwise>user's</c:otherwise></c:choose> offers</a>
                </li>
                <li class="nav-item mr-auto">&nbsp;</li>
            </ul>
            <%-- Content for tabs --%>
            <div class="tab-content" id="myTabContent">
                <%-- Block with startups (active on start) --%>
                <div class="tab-pane fade show active" id="startups" aria-labelledby="startup-tab" role="tabpanel">
                    <c:choose>
                        <c:when test="${user.startups.size() > 0}">
                            <h2 class="text-center">List of all
                                <c:choose><c:when test="${isOwner}">my</c:when>
                                    <c:otherwise>user's</c:otherwise></c:choose> startups <small>[total: ${user.startups.size()}]</small></h2>
                            <div class="row mb-5">
                                <c:forEach var="item" items="${user.startups}">
                                    <%@include file="item.jsp" %>
                                </c:forEach>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <h1 class="text-gr text-center mt-5"><em>No one startups</em></h1>
                        </c:otherwise>
                    </c:choose>
                </div>

                <%-- Block with offers (hidden on start) --%>
                <div class="tab-pane fade" id="offers" aria-labelledby="offers-tab" role="tabpanel">
                    <c:choose>
                        <c:when test="${user.offers.size() > 0}">
                            <h2 class="text-center">List of all
                                <c:choose><c:when test="${isOwner}">my</c:when>
                                    <c:otherwise>user's</c:otherwise></c:choose> offers <small>[total: ${user.offers.size()}]</small></h2>
                            <div class="row mb-5">
                                <c:forEach var="item" items="${user.offers}">
                                    <%@include file="item.jsp" %>
                                </c:forEach>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <h1 class="text-gr text-center mt-5"><em>No one offers</em></h1>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>
    </div>
    <%@include file="footer.jsp" %>
</div>
</body>
</html>
