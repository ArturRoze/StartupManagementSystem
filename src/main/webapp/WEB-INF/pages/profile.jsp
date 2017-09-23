<%--
  Created by IntelliJ IDEA.
  User: root
  Date: 20.09.2017
  Time: 19:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Profile</title>
</head>
<body>
<div align="center">

    <c:set var="id">
        <sec:authentication property="principal.id"/>
    </c:set>

    <div align="center">

        <table border="bold">
            <caption><h1>User profile</h1></caption>
            <tr>
                <th>Id</th>
                <td>${user.id}</td>
            </tr>
            <tr>

                <th>login</th>
                <td>${user.login}</td>
            </tr>
            <tr>
                <th>password</th>
                <td>${user.password}</td>
            </tr>
            <tr>
                <th>email</th>
                <td>${user.email}</td>
            </tr>
            <tr>
                <th>Registration</th>
                <td>${user.registrationDate}</td>
            </tr>
            <tr>
                <th>First name</th>
                <td>${user.firstName}</td>
            </tr>
            <tr>
                <th>last name</th>
                <td>${user.lastName}</td>
            </tr>
            <tr>
                <th>Description</th>
                <td>${user.description}</td>
            </tr>
            <tr>
                <th>Country</th>
                <td>${user.country.name}</td>
            </tr>
            <tr>
                <th>City</th>
                <td>${user.city.name}</td>
            </tr>
        </table>

    </div>
    <br>

    <div align="center">
        <form action="${pageContext.request.contextPath}/news" method="get">
            <input type="submit" value="News">
        </form>
    </div>

    <div align="center">
        <form action="/users/profile/${id}/update" method="get">
            <input type="submit" value="Update">
        </form>
    </div>

    <div align="center">
        <form action="/users/profile/${id}/delete" method="get">
            <input type="submit" value="Delete">
        </form>
    </div>
    <div align="center">
        <form action="${pageContext.request.contextPath}/startups/new/startup/" method="get">
            <input type="submit" value="New startup">
        </form>
    </div>

    <div align="center">
        <form action="${pageContext.request.contextPath}/offers/new/offer/" method="get">
            <input type="submit" value="New offer">
        </form>
    </div>

    <div align="center">
        <form action="${pageContext.request.contextPath}/logout" method="post">
            <input type="submit" value="Logout">
        </form>
    </div>
</div>
</body>
</html>
