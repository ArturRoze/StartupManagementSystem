<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>All users list</title>
    <%@include file="header_config.jsp" %>
</head>
<body>
<div class="wrapper">
    <div class="content">
        <%@include file="navbar.jsp" %>
        <div class="container">

            <h2 class="text-center">List of all users</h2>
            <div class="row mb-5 justify-content-md-center">
                <div class="col">
                    <table class="table">
                        <thead class="thead-default">
                        <tr>
                            <th class="w-10 text-center">ID</th>
                            <th>Login</th>
                            <th>Email</th>
                            <th>First name</th>
                            <th>Last name</th>
                            <th>Registration</th>
                            <th>Description</th>
                            <th>Country</th>
                            <th>Action</th>
                        </tr>
                        </thead>
                        <c:forEach var="user" items="${users}">
                            <tr>
                                <th class="bg-light text-center">${user.id}</th>
                                <td>${user.login}</td>
                                <td>
                                    <c:set var="check" value="${user.email}"/>
                                    <%@include file="patterns/is_empty_pattern.jsp" %>
                                </td>
                                <td>
                                    <c:set var="check" value="${user.firstName}"/>
                                    <%@include file="patterns/is_empty_pattern.jsp" %>
                                </td>
                                <td>
                                    <c:set var="check" value="${user.lastName}"/>
                                    <%@include file="patterns/is_empty_pattern.jsp" %>
                                </td>
                                <td><c:set var="dateOf" value="${user.registrationDate}"/>
                                    <%@include file="patterns/date_pattern.jsp"%>
                                </td>
                                <td>
                                    <c:set var="check" value="${user.description}"/>
                                    <%@include file="patterns/is_empty_pattern.jsp" %>
                                </td>
                                <td>${user.country.name}</td>
                                <td>
                                    <%@include file="buttons/user_manage_buttons.jsp" %>
                                </td>

                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </div>

            <div class="px-4 mb-4 row">
                <div class="navbar col bg-bar mx-auto p-3">
                    <%@include file="buttons/back_button.jsp" %>
                </div>
            </div>
        </div>
    </div>
    <%@include file="footer.jsp" %>
</div>
</body>
</html>
