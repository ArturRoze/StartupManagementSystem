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
    <%--<h2>Вы вошли как: <sec:authentication property="principal.username"/></h2>--%>
    <%--<h2>Registration date: <sec:authentication property="principal.registrationDate"/></h2>--%>
    <%--<h2>Email of user: <sec:authentication property="principal.email"/></h2>--%>

    <%--<c:set var="login" value="q" scope="session"/>--%>
    <%--<c:set var="login">--%>
        <%--<sec:authentication property="principal.username"/>--%>
    <%--</c:set>--%>
    <%--<c:if test = "${'admin' eq login}">--%>
         <%--You are ADMIN--%>
    <%--</c:if>--%>
    
    <table border="bold" id="products">
        <caption><h1>List of all products</h1></caption>
        <tr>
            <th>Id</th>
            <th>Name</th>
            <th>Manufacturer</th>
            <th>Cost</th>
            <th>Description</th>
            <sec:authorize access="hasRole('ADMIN')">
                <th>Update</th>
                <th>Delete</th>
            </sec:authorize>
        </tr>
        <c:forEach var="startup" items="${startups}">
            <tr>
                <td>${startup.id}</td>
                <td>${startup.name}</td>
                <td>${startup.idea}</td>
                <td>${startup.user.login}</td>
                <td>${startup.country.name}</td>
                    <td><form action="/startups/${startup.id}" method="get">
                        <input type="submit" value="show startup">
                    </form></td>
                    <%--<td><form action="/product/product_delete/id=${startup.id}" method="get">--%>
                        <%--<input type="submit" value="Delete product">--%>
                    <%--</form></td>--%>
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
