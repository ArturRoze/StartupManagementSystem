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
                                <h4 class="text-danger">${admin.login}</h4>
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

            <div class="navbar mt-4 mb-5 row">
                <div class="col-md-5 bg-bar mx-auto p-3 text-center">
                    <%@include file="buttons/all_admins_button.jsp" %>
                    <%@include file="buttons/all_users_button.jsp" %>
                </div>
            </div>

        </div>
    </div>
    <%@include file="footer.jsp" %>
</div>

</body>
</html>