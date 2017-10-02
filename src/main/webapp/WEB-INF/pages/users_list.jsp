<%--
  Created by IntelliJ IDEA.
  User: root
  Date: 20.09.2017
  Time: 18:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Users list</title>
    <style>
        table, td, th {
            border: 1px solid #ddd;
            text-align: left;
        }

        table {
            border-collapse: collapse;
            width: 50%;
        }

        th, td {
            padding: 15px;
        }
    </style>
</head>
<body>
<div align="center">

    <c:set var="current_user_id">
        <sec:authentication property="principal.id"/>
    </c:set>

    <div align="center">
        <div align="center">
            <form action="${pageContext.request.contextPath}/news" method="get">
                <input type="submit" value="News">
            </form>
        </div>

        <div align="center">
            <form action="${pageContext.request.contextPath}/admins/profile/${current_user_id}" method="get">
                <input type="submit" value="Admin profile">
            </form>
        </div>

        <div align="center">
            <form action="${pageContext.request.contextPath}/logout" method="post">
                <input type="submit" value="Logout">
            </form>
        </div>
    </div>
    <div align="center">
        <table>
            <caption><h1>List of all users</h1></caption>
            <tr>
                <th>Id</th>
                <th>Login</th>
                <th>Email</th>
                <th>First name</th>
                <th>Last name</th>
                <th>Registration</th>
                <th>Description</th>
                <th>Country</th>
                <th>To profile</th>
            </tr>
            <c:forEach var="user" items="${users}">
                <tr>
                    <td>${user.id}</td>
                    <td>${user.login}</td>
                    <td>${user.email}</td>
                    <td>${user.firstName}</td>
                    <td>${user.lastName}</td>
                    <td>${user.registrationDate}</td>
                    <td>${user.description}</td>
                    <td>${user.country.name}</td>

                    <td>
                        <form action="${pageContext.request.contextPath}/users/profile/${current_user_id}" method="get">
                            <input type="submit" value="Show profile">
                        </form>
                    </td>

                </tr>
            </c:forEach>
        </table>
    </div>
</div>
</body>
</html>
