<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>All admins list</title>
    <%@include file="header_config.jsp" %>
</head>
<body>
<div class="wrapper">
    <div class="content">
        <%@include file="navbar.jsp" %>
        <div class="container">

            <h2 class="text-center">List of all admins</h2>
            <div class="row mb-5 justify-content-md-center">
                <div class="col-8">
                    <table class="table">
                        <thead class="thead-default">
                        <tr>
                            <th class="w-15 text-center">ID</th>
                            <th>Login</th>
                            <th>E-mail</th>
                        </tr>
                        </thead>
                        <c:forEach var="admin" items="${admins}">
                            <tr>
                                <th class="bg-light text-center">${admin.id}</th>
                                <td>${admin.login}</td>
                                <td>
                                    <c:set var="check" value="${admin.email}"/>
                                    <%@include file="patterns/is_empty_pattern.jsp" %>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </div>

            <div class="px-4 mb-4 row">
                <div class="navbar col-md-8 bg-bar mx-auto p-3">
                    <%@include file="buttons/back_button.jsp" %>
                    <%@include file="buttons/add_admin_button.jsp" %>
                </div>
            </div>
        </div>
    </div>
    <%@include file="footer.jsp" %>
</div>
</body>
</html>