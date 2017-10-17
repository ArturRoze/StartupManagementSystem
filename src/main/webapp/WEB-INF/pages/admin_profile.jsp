<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Admin profile ${admin.id}</title>
    <%@include file="header_config.jsp" %>
</head>
<body>
<div class="wrapper">
    <div class="content">
        <%@include file="navbar.jsp" %>
        <div class="container">

            <c:set var="isOwner" value="${admin.id == current_user_id}"/>
            <sec:authorize access="hasRole('ADMIN')">
                <c:set var="isAdmin" value="true"/>
            </sec:authorize>

            <%-- TODO fix variable admin_current_id to current_user_id
            <c:set var="admin_current_id">
                <sec:authentication property="principal.id"/>
            </c:set>
            <c:set var="isOwner" value="${admin_current_id == admin.id}"/> --%>
            <h2 class="text-center"><c:choose>
                <c:when test="${isOwner}">My admin</c:when><c:otherwise>Admin</c:otherwise></c:choose> data
                <small>[admin-id: ${admin.id}]</small>
            </h2>
            <div class="row justify-content-md-center">
                <div class="col-md-5 p-2 border border-gr rounded">
                    <div class="btn-block">
                        <table class="table table-hover mb-0">
                            <th class="bg-light w-25" scope="row">Login</th>
                            <td>
                                <h4 class="text-warning">${admin.login}</h4>
                            </td>
                            </tr>
                            <th class="bg-light w-25" scope="row">E-mail</th>
                            <td>
                                <c:set var="check" value="${admin.email}"/>
                                <%@include file="patterns/is_empty_pattern.jsp" %>
                            </td>
                            </tr>
                        </table>
                        <c:if test="${isOwner}">
                            <hr class="mb-4">
                            <div class="navbar">
                                <%@include file="buttons/update_admin_profile_button.jsp" %>
                                <%@include file="buttons/delete_admin_button.jsp" %>
                            </div>
                        </c:if>
                    </div>
                </div>
            </div>

            <%--
            <ul class="nav nav-pills mt-5 row" id="myTab" role="tablist">
                <li class="nav-item mr-auto active">&nbsp;</li>
                <li class="nav-item">
                    <a class="nav-link" id="startups-tab" data-toggle="tab" href="#admins"
                       aria-controls="startups" aria-expanded="true" role="tab">List of all admins</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" id="offers-tab" data-toggle="tab" href="#users"
                       aria-controls="offers" role="tab">List of all users</a>
                </li>
                <li class="nav-item mr-auto">&nbsp;</li>
            </ul>
            <hr>
            <div class="tab-content" id="myTabContent">
                <div class="tab-pane fade" id="admins" aria-labelledby="startup-tab" role="tabpanel">
                    <h2 class="text-center">
                        List of all admins
                        <a href="${pageContext.request.contextPath}/admins/new/admin"
                           class="btn btn-outline-success ml-1" role="button">
                            <i class="material-icons">add_circle_outline</i>
                        </a>
                    </h2>
                    <div class="row mb-5">
                        <table class="table table-striped">
                            <thead class="thead-default">
                            <tr>
                                <th>ID</th>
                                <th>Login</th>
                                <th>E-mail</th>
                                <th>Password</th>
                            </tr>
                            </thead>
                            <c:forEach var="admin" items="${admins}">
                                <tr>
                                    <td>${admin.id}</td>
                                    <td>${admin.login}</td>
                                    <td>${admin.email}</td>
                                    <td class="text-gr mt-1 float-left"><em>[hidden]</em></td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>
                </div>

                <div class="tab-pane fade" id="users" aria-labelledby="offers-tab" role="tabpanel">
                    <c:choose>
                        <c:when test="${users.size() > 0}">
                            <h2 class="text-center">List of all users</h2>
                            <div class="row mb-5">
                                <c:forEach var="user" items="${users}">
                                </c:forEach>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <h1 class="text-gr text-center mt-5"><em>There is no one user</em></h1>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
            --%>

            <div class="navbar mt-4 mb-5 row">
                <div class="col-md-5 bg-bar mx-auto p-3 text-center">
                    <%@include file="buttons/all_admins_button.jsp" %>
                    <%@include file="buttons/all_users_button.jsp" %>
                </div>
            </div>
            <%--
            <div>
                <div>
                    <form action="${pageContext.request.contextPath}/admins/list" method="get">
                        <input type="submit" value="show all admins">
                    </form>
                </div>
            </div>
            <div>
                <form action="${pageContext.request.contextPath}/users/list" method="get">
                    <input type="submit" value="show all users">
                </form>
            </div>
            --%>
        </div>
    </div>
    <%@include file="footer.jsp" %>
</div>

</body>
</html>