<%--
  Created by IntelliJ IDEA.
  User: root
  Date: 26.08.2017
  Time: 12:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
    <title>Show all startups</title>
    <%--<link type="text/css" href="styles.css" rel="stylesheet">--%>

    <style>
        #products {
            font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
            border-collapse: collapse;
            width: 80%;
        }

        #products td, #products th {
            border: 1px solid #ddd;
            padding: 8px;
        }

        #products tr:nth-child(even) {
            background-color: #f2f2f2;
        }

        #products tr:hover {
            background-color: #ddd;
        }

        #products th {
            padding-top: 12px;
            padding-bottom: 12px;
            text-align: left;
            background-color: #4CAF50;
            color: white;
        }
    </style>
</head>
<body>
<div align="center">

    <table border="bold" id="products">
        <caption><h1>List of all products</h1></caption>
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

    <div align="center">
        <form action="/logout" method="post">
            <input type="submit" value="Logout">
        </form>
    </div>
</div>
</body>
</html>
