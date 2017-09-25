<%--
  Created by IntelliJ IDEA.
  User: root
  Date: 20.09.2017
  Time: 16:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>News</title>
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
    <h1 align="center">News Page</h1>

    <c:set var="current_user_id">
        <sec:authentication property="principal.id"/>
    </c:set>

    <div align="center">
        <form action="${pageContext.request.contextPath}/" method="get">
            <input type="submit" value="To main page">
        </form>
    </div>

    <c:choose>

    <c:when test="isAdmin">
    <div align="center">
        <form action="/admins/profile/${current_user_id}" method="get">
            <input type="submit" value="To admin profile page">
        </form>
    </div>
    </c:when>

    <c:otherwise>
    <div align="center">
        <form action="/users/profile/${current_user_id}" method="get">
            <input type="submit" value="To user profile page">
        </form>
    </div>
    </c:otherwise>

    </c:choose>

    <div align="center">
        <form action="${pageContext.request.contextPath}/logout" method="post">
            <input type="submit" value="Logout">
        </form>
    </div>

    <div align="center">

        <table>
            <caption><h1>News</h1></caption>
            <tr>
                <th>Id</th>
                <th>Name</th>
                <th>Description</th>
                <th>User</th>
                <th>Registration</th>
                <th>Country</th>
                <th>To startup</th>
            </tr>
            <c:forEach var="startup" items="${startups}">
                <tr>
                    <td>${startup.id}</td>
                    <td>${startup.name}</td>
                    <td>${startup.description}</td>
                    <td>${startup.user.login}</td>
                    <td>${startup.registrationDate}</td>
                    <td>${startup.country.name}</td>

                    <td>
                        <form action="/startups/${startup.id}" method="get">
                            <input type="submit" value="Show startup">
                        </form>
                    </td>

                </tr>
            </c:forEach>
        </table>
    </div>
</body>
</html>
