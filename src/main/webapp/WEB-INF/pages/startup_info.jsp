<%--
  Created by IntelliJ IDEA.
  User: root
  Date: 24.09.2017
  Time: 2:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Startup ${startup.id}</title>
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

    <c:set var="isAdmin" value="false"/>
    <c:set var="id_test" value="false"/>

    <sec:authorize access="isAuthenticated()">
        <c:set var="current_user_id">
            <sec:authentication property="principal.id"/>
        </c:set>

        <c:set var="id_test" value="${startup.user.id == current_user_id}"/>

        <sec:authorize access="hasRole('ADMIN')">
            <c:set var="isAdmin" value="true"/>
        </sec:authorize>
    </sec:authorize>

    <div align="center">
        <table>
            <caption><h1>Startup info</h1></caption>

            <tr>
                <th>Id</th>
                <td>${startup.id}</td>
            </tr>
            <tr>
                <th>login</th>
                <td>${startup.name}</td>
            </tr>
            <tr>
                <th>Description</th>
                <td>${startup.description}</td>
            </tr>
            <tr>
                <th>User</th>
                <td>${startup.user.login}</td>
            </tr>
            <tr>
                <th>Registration</th>
                <td>${startup.registrationDate}</td>
            </tr>
            <tr>
                <th>Country</th>
                <td>${startup.country.name}</td>
            </tr>
        </table>

        <c:if test="${id_test || isAdmin}">
            <div align="center">
                <form action="/startups/${startup.id}/update" method="get">
                    <input type="submit" value="Update">
                </form>
            </div>

            <div align="center">
                <form action="/startups/${startup.id}/delete" method="get">
                    <input type="submit" value="Delete">
                </form>
            </div>
        </c:if>
    </div>
    <div align="center">
        <button onclick="goBack()">Go Back</button>
        <script>
            function goBack() {
                window.history.back();
            }
        </script>
    </div>
</div>

</body>
</html>