<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Head Page</title>
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
    <h1> Welcome Page </h1>
    <a href="${pageContext.request.contextPath}/login">Login</a><br>
    <a href="${pageContext.request.contextPath}/registration">Registration</a><br>
    <a href="${pageContext.request.contextPath}/news">To news page</a><br>
    <a href="${pageContext.request.contextPath}/startups">Show all startups</a><br>

</div>
<div align="center">

    <table>
        <caption><h1>List of all startups</h1></caption>
        <tr>
            <th>Id</th>
            <th>Name</th>
            <th>Description</th>
            <th>Industry</th>
            <th>Owner</th>
            <th>Budget</th>
            <th>Registration</th>
            <th>Country</th>
            <th>To startup</th>
        </tr>
        <c:forEach var="startup" items="${startups}">
            <tr>
                <td>${startup.id}</td>
                <td>${startup.name}</td>
                <td>${startup.description}</td>
                <td>${startup.industry.name}</td>
                <td>${startup.user.firstName} ${startup.user.lastName}</td>
                <td>${startup.budget}</td>
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
