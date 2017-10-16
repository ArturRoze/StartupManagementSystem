<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Show admins</title>
    <%@include file="header_config.jsp" %>
</head>
<body>
<div class="wrapper">
    <div class="content">
        <%@include file="navbar.jsp" %>
        <div class="container">
            <%--<c:set var="current_user_id">--%>
            <%--<sec:authentication property="principal.id"/>--%>
            <%--</c:set>--%>

            <div align="center">
                <h2 class="text-center">
                    List of all admins
                    <a href="${pageContext.request.contextPath}/admins/new/admin"
                       class="btn btn-outline-success ml-1" role="button">
                        <i class="material-icons">add_circle_outline</i>
                        <span>Create admin</span>
                    </a>
                </h2>
                <div class="row mb-5 justify-content-md-center">
                    <div class="col-8">
                        <table class="table">
                            <thead class="thead-default">
                            <tr>
                                <th class="w-25">ID</th>
                                <th>Login</th>
                                <th>E-mail</th>
                            </tr>
                            </thead>
                            <c:forEach var="admin" items="${admins}">
                                <tr>
                                    <td>${admin.id}</td>
                                    <td>${admin.login}</td>
                                    <td>${admin.email}</td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>
                </div>

                <div class="navbar bg-bar rounded px-3 mb-4 row">
                    <%@include file="buttons/back_button.jsp" %>
                </div>
            </div>
        </div>
    </div>
    <%@include file="footer.jsp" %>
</div>
</body>
</html>