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

    <div align="center">
        <div align="center">
            <form action="${pageContext.request.contextPath}/news" method="get">
                <input type="submit" value="News">
            </form>
        </div>
        <div align="center">
            <form action="${pageContext.request.contextPath}/logout" method="post">
                <input type="submit" value="Logout">
            </form>
        </div>
    </div>

    <c:set var="current_user_id">
        <sec:authentication property="principal.id"/>
    </c:set>

    <c:set var="id_test" value="${user.id == current_user_id}"/>

    <c:set var="isAdmin" value="false"/>

    <sec:authorize access="hasRole('ADMIN')">
        <c:set var="isAdmin" value="true"/>
    </sec:authorize>


    <div align="center">
        <table>
            <caption><h1>User profile</h1></caption>

            <c:if test="${id_test || isAdmin}">
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
            </c:if>

            <tr>
                <th>Email</th>
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
                <th>Last name</th>
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

    <c:if test="${id_test || isAdmin}">
        <div align="center">
            <form action="/users/profile/${user.id}/update" method="get">
                <input type="submit" value="Update">
            </form>
        </div>

        <div align="center">
            <form action="/users/profile/${user.id}/delete" method="get">
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
    </c:if>

</div>
</body>
</html>
