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
</head>
<body>
<div align="center">
    <h1 align="center">News Page</h1>

    <div align="center">
        <form action="${pageContext.request.contextPath}/" method="get">
            <input type="submit" value="To main page">
        </form>
    </div>

    <c:set var="id">
    <sec:authentication property="principal.id"/>
    </c:set>

    <div align="center">
        <form action="/users/profile/${id}" method="get">
            <input type="submit" value="To profile page">
        </form>
    </div>

    <div align="center">
        <form action="/logout" method="post">
            <input type="submit" value="Logout">
        </form>
    </div>

    <div align="center">

        <table border="bold">
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
            <c:forEach var="user" items="${startups}">
                <tr>
                    <td>${user.id}</td>
                    <td>${user.name}</td>
                    <td>${user.description}</td>
                    <td>${user.user.login}</td>
                    <td>${user.registrationDate}</td>
                    <td>${user.country.name}</td>
                    <td>
                        <form action="/startups/${user.id}" method="get">
                            <input type="submit" value="show startup">
                        </form>
                    </td>

                </tr>
            </c:forEach>
        </table>
</div>
</body>
</html>
