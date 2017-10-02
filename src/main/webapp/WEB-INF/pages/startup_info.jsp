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
    <c:set var="isOwner" value="false"/>

    <sec:authorize access="isAuthenticated()">
        <c:set var="current_user_id">
            <sec:authentication property="principal.id"/>
        </c:set>

        <c:set var="isOwner" value="${startup.user.id == current_user_id}"/>

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
                <th>Name</th>
                <td>${startup.name}</td>
            </tr>
            <tr>
                <th>Description</th>
                <td>${startup.description}</td>
            </tr>
            <tr>
                <th>User</th>
                <td><div align="center">
                    <form action="${pageContext.request.contextPath}/users/profile/${startup.user.id}" method="get">
                        <input type="submit" value="To user ${startup.user.id} profile page">
                    </form>
                </div></td>
            </tr>
            <tr>
                <th>Registration</th>
                <td>${startup.registrationDate}</td>
            </tr>
            <tr>
                <th>Industry</th>
                <td>${startup.industry.name}</td>
            </tr>
            <tr>
                <th>Country</th>
                <td>${startup.country.name}</td>
            </tr>
        </table>

        <c:if test="${isOwner || isAdmin}">
            <div align="center">
                <form action="${pageContext.request.contextPath}/startups/${startup.id}/edit" method="get">
                    <input type="submit" value="Update">
                </form>
            </div>

            <div align="center">
                <form action="${pageContext.request.contextPath}/startups/${startup.id}/delete" method="get">
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
