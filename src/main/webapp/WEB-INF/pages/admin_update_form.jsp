<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Admin update</title>
    <%@include file="header_config.jsp" %>
</head>
<body>
<div class="wrapper">
    <div class="content">
        <%@include file="navbar.jsp" %>
        <div class="container">
            <c:set var="isOwner" value="${admin.id == current_user_id}"/>

            <c:choose>
                <c:when test="${isOwner || isAdmin}">
                    <h2 class="text-center">Update user profile
                        <small>[id: ${admin.id}]</small>
                    </h2>
                    <div class="row justify-content-center">
                        <div class="col-8 border border-gr rounded p-3">
                            <form action="${pageContext.request.contextPath}/admins/profile/${admin.id}/update/"
                                  method="post">
                                <table class="table mb-0">
                                    <tr>
                                        <th width="20%"></th>
                                        <th width="40%">Old data</th>
                                        <th width="40%">New data</th>
                                    </tr>

                                    <tr class="bg-light">
                                        <th>Login</th>
                                        <td><h4 class="font-weight-bold text-danger">${admin.login}</h4></td>
                                        <td></td>
                                    </tr>

                                    <tr class="bg-light">
                                        <th>Password</th>
                                        <td class="text-muted"><em>
                                            <small>Enter your new password here
                                                <br>or leave empty to keep it
                                            </small>
                                        </em></td>
                                        <td><input type="password" class="form-control" name="password"
                                                   placeholder="Enter new password"></td>
                                    </tr>

                                    <tr>
                                        <th class="bg-light">E-mail</th>
                                        <td>
                                            <c:set var="check" value="${admin.email}"/>
                                            <%@include file="patterns/is_empty_pattern.jsp" %>
                                        </td>
                                        <td><input type="email" class="form-control" name="email" value="${admin.email}">
                                        </td>
                                    </tr>
                                </table>
                                <div class="navbar bg-bar mt-3">
                                    <div>
                                        <%@include file="buttons/back_button.jsp" %>
                                    </div>
                                    <div>
                                        <%@include file="buttons/reset_button_lg.jsp" %>
                                        <%@include file="buttons/update_button_lg.jsp" %>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </c:when>
                <c:otherwise>
                    <%@include file="no_permission.jsp" %>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
    <%@include file="footer.jsp" %>
</div>
</body>
</html>