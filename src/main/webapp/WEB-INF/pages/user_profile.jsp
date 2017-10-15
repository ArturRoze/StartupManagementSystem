<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Profile ${user.id}</title>
    <%@include file="header_config.jsp" %>
</head>
<body>
<div class="wrapper">
    <div class="content">
        <%@include file="navbar.jsp" %>
        <div class="container">
            <div class="container">

                <c:set var="isOwner" value="${user.id == current_user_id}"/>
                <sec:authorize access="hasRole('ADMIN')">
                    <c:set var="isAdmin" value="true"/>
                </sec:authorize>

                <h2 class="text-center">User profile</h2>
                <div class="row border border-gr rounded">
                    <%-- Left part of user's data --%>
                    <div class="col-md-6 p-2">
                        <div class="btn-block">
                            <table class="table table-hover mb-0">
                                <tr>
                                    <th class="bg-light w-25" scope="row">Login</th>
                                    <td>
                                        <h4 class="text-success">${user.login}</h4>
                                    </td>
                                </tr>
                                <tr>
                                    <th class="bg-light">password</th>
                                    <td>
                                        <div class="text-secondary mt-2 float-left"><em>[hidden]</em></div>
                                        <a class="btn btn-sm btn-light ml-4 text-danger float-left"
                                           role="button" data-toggle="tooltip" data-placement="top" data-html="true"
                                           title="<em>Currently unavailable</em>" disabled>
                                            change password</a>
                                    </td>
                                </tr>
                                <tr>
                                    <th class="bg-light">Registred</th>
                                    <td>
                                        <c:set var="dateOf" value="${user.registrationDate}"/>
                                        <%@include file="patterns/date_pattern.jsp" %>
                                    </td>
                                </tr>
                                <tr>
                                    <th class="bg-light">Notes</th>
                                    <td>${user.description}</td>
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
                                    <td>${user.firstName}</td>
                                </tr>
                                <tr>
                                    <th class="bg-light">Last name</th>
                                    <td>${user.lastName}</td>
                                </tr>
                                <tr>
                                    <th class="bg-light">Email</th>
                                    <td>${user.email}</td>
                                </tr>
                                <tr>
                                    <th class="bg-light">Country</th>
                                    <td>${user.country.name}</td>
                                </tr>
                            </table>
                        </div>
                    </div>
                </div>
                <%--<table>
                    <c:if test="${isOwner || isAdmin}">
                        <tr>
                            <th>Id</th>
                            <td>${user.id}</td>
                        </tr>
                        <tr>
                            <th>login</th>
                            <td>${user.login}</td>
                        </tr>
                        <tr>
                            <th>password</th>
                            <td>${user.password}</td>
                        </tr>
                    </c:if>

                    <tr>
                        <th>Email</th>
                        <td>${user.email}</td>
                    </tr>
                    <tr>
                        <th>Registration</th>
                        <td>${user.registrationDate}</td>
                    </tr>
                    <tr>
                        <th>First name</th>
                        <td>${user.firstName}</td>
                    </tr>
                    <tr>
                        <th>Last name</th>
                        <td>${user.lastName}</td>
                    </tr>
                    <tr>
                        <th>Description</th>
                        <td>${user.description}</td>
                    </tr>
                    <tr>
                        <th>Country</th>
                        <td>${user.country.name}</td>
                    </tr>
                </table>--%>

                <h2 class="text-center">List of all user's startups</h2>
                <div class="row">
                    <c:forEach var="item" items="${user.startups}">
                        <%@include file="item.jsp" %>
                    </c:forEach>
                </div>
                <%--<table>
                    <tr>
                        <th>Id</th>
                        <th>Name</th>
                        <th>Description</th>
                        <th>Industry</th>
                        <th>Owner</th>
                        <th>Budget</th>
                        <th>Registration</th>
                        <th>Country</th>
                        <th>To startup</th>
                    </tr>
                    <c:forEach var="startup" items="${user.startups}">
                        <tr>
                            <td>${startup.id}</td>
                            <td>${startup.name}</td>
                            <td>${startup.description}</td>
                            <td>${startup.industry.name}</td>
                            <td>${startup.user.firstName} ${startup.user.lastName}</td>
                            <td>${startup.budget}</td>
                            <td>${startup.registrationDate}</td>
                            <td>${startup.country.name}</td>
                            <td>
                                <form action="${pageContext.request.contextPath}/startups/${startup.id}"
                                      method="get">
                                    <input type="submit" value="Show startup">
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </table>--%>

                <h2 class="text-center">List of all user's offers</h2>
                <div class="row">
                    <c:forEach var="item" items="${user.offers}">
                        <%@include file="item.jsp" %>
                    </c:forEach>
                </div>
                <%--<table>
                    <tr>
                        <th>Id</th>
                        <th>Description</th>
                        <th>Industry</th>
                        <th>Owner</th>
                        <th>Budget</th>
                        <th>Registration</th>
                        <th>Country</th>
                        <th>To startup</th>
                    </tr>
                    <c:forEach var="offer" items="${user.offers}">
                        <tr>
                            <td>${offer.id}</td>
                            <td>${offer.description}</td>
                            <td>${offer.industry.name}</td>
                            <td>${offer.user.firstName} ${offer.user.lastName}</td>
                            <td>${offer.budget}</td>
                            <td>${offer.registrationDate}</td>
                            <td>${offer.country.name}</td>
                            <td>
                                <form action="${pageContext.request.contextPath}/offers/${offer.id}"
                                      method="get">
                                    <input type="submit" value="Show offer">
                                </form>
                            </td>

                        </tr>
                    </c:forEach>
                </table>--%>

                <div class="navbar bg-bar rounded px-4 mt-3 mb-4">
                    <%@include file="buttons/all_startups_button.jsp" %>
                    <div class="mx-auto"></div>
                    <c:if test="${isOwner && !isAdmin}">
                        <%@include file="buttons/add_startup_button.jsp" %>
                        <%@include file="buttons/add_offer_button.jsp" %>
                    </c:if>
                    <c:if test="${isOwner || isAdmin}">
                        <%@include file="buttons/update_profile_button.jsp" %>
                        <%@include file="buttons/delete_user_button.jsp" %>
                    </c:if>
                </div>
            </div>
        </div>
    </div>
    <%@include file="footer.jsp" %>
</div>
</body>
</html>
