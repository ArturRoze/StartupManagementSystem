<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Update user form</title>
    <%@include file="header_config.jsp" %>
</head>
<body>
<div class="wrapper">
    <div class="content">
        <%@include file="navbar.jsp" %>
        <div class="container">
            <c:set var="isOwner" value="${user.id == current_user_id && !isAdmin}"/>

            <c:choose>
                <c:when test="${isOwner || isAdmin}">
                    <h2 class="text-center">Update user profile <small>[id: ${user.id}]</small>
                    </h2>
                    <div class="row justify-content-center">
                        <div class="col-8 border border-gr rounded p-3">
                            <form action="${pageContext.request.contextPath}/users/profile/${user.id}/update"
                                  class="mb-0" method="post">
                                <table class="table mb-0">
                                    <tr>
                                        <th width="20%"></th>
                                        <th width="40%">Old data</th>
                                        <th width="40%">New data</th>
                                    </tr>

                                    <tr class="bg-light">
                                        <th>login</th>
                                        <td><h4 class="font-weight-bold text-success">${user.login}</h4></td>
                                        <td></td>
                                    </tr>

                                    <tr>
                                        <th class="bg-light">Password</th>
                                        <td class="text-muted"><em>
                                            <small>Enter your new password here
                                                <br>or leave empty to keep it
                                            </small>
                                        </em></td>
                                        <td><input type="text" class="form-control" name="password"
                                                   placeholder="Enter new password"></td>
                                    </tr>

                                    <tr>
                                        <th class="bg-light">E-mail</th>
                                        <td>
                                            <c:set var="check" value="${user.email}"/>
                                            <%@include file="patterns/is_empty_pattern.jsp" %>
                                        </td>
                                        <td><input type="email" class="form-control" name="email" value="${user.email}">
                                        </td>
                                    </tr>

                                    <tr>
                                        <th class="bg-light">First name</th>
                                        <td>
                                            <c:set var="check" value="${user.firstName}"/>
                                            <%@include file="patterns/is_empty_pattern.jsp" %>
                                        </td>
                                        <td><input type="text" class="form-control" name="first_name"
                                                   value="${user.firstName}"></td>
                                    </tr>

                                    <tr>
                                        <th class="bg-light">Last name</th>
                                        <td>
                                            <c:set var="check" value="${user.lastName}"/>
                                            <%@include file="patterns/is_empty_pattern.jsp" %>
                                        </td>
                                        <td><input type="text" class="form-control" name="last_name"
                                                   value="${user.lastName}"></td>
                                    </tr>

                                    <tr>
                                        <th class="bg-light">Description</th>
                                        <td>
                                            <c:set var="check" value="${user.description}"/>
                                            <%@include file="patterns/is_empty_pattern.jsp" %>
                                        </td>
                                        <td><input type="text" class="form-control" name="description"
                                                   value="${user.description}"></td>
                                    </tr>

                                    <tr>
                                        <th class="bg-light">Country</th>
                                        <td>${user.country.name}</td>
                                        <td>
                                            <select class="custom-select" name="country_id">
                                                <c:forEach var="country" items="${countries}">
                                                    <c:choose>
                                                        <c:when test="${country.id == user.country.id}">
                                                            <option value="${country.id}"
                                                                    selected>${country.name}</option>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <option value="${country.id}">${country.name}</option>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </c:forEach>
                                            </select>
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