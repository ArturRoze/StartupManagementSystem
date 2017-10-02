<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Offer ${offer.id}</title>
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

        <c:set var="isOwner" value="${offer.user.id == current_user_id}"/>

        <sec:authorize access="hasRole('ADMIN')">
            <c:set var="isAdmin" value="true"/>
        </sec:authorize>
    </sec:authorize>

    <div align="center">
        <table>
            <caption><h1>Offer info</h1></caption>

            <tr>
                <th>Id</th>
                <td>${offer.id}</td>
            </tr>
            <tr>
                <th>Description</th>
                <td>${offer.description}</td>
            </tr>
            <tr>
                <th>User</th>
                <td><div align="center">
                    <form action="${pageContext.request.contextPath}/users/profile/${offer.user.id}" method="get">
                        <input type="submit" value="To user ${offer.user.id} profile page">
                    </form>
                </div></td>
            </tr>
            <tr>
                <th>Registration</th>
                <td>${offer.registrationDate}</td>
            </tr>
            <tr>
                <th>Industry</th>
                <td>${offer.industry.name}</td>
            </tr>
            <tr>
                <th>Country</th>
                <td>${offer.country.name}</td>
            </tr>
        </table>

        <c:if test="${isOwner || isAdmin}">
            <div align="center">
                <form action="${pageContext.request.contextPath}/offers/${offer.id}/edit" method="get">
                    <input type="submit" value="Update">
                </form>
            </div>

            <div align="center">
                <form action="${pageContext.request.contextPath}/offers/${offer.id}/delete" method="get">
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
