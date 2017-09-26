<%--
  Created by IntelliJ IDEA.
  User: root
  Date: 26.09.2017
  Time: 17:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Startup update</title>
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
<h1 align="center">Startup update form</h1>

<c:set var="current_user_id">
    <sec:authentication property="principal.id"/>
</c:set>
<c:set var="isOwner" value="${startup.user.id == current_user_id}"/>
<c:set var="isAdmin" value="false"/>
<sec:authorize access="hasRole('ADMIN')">
    <c:set var="isAdmin" value="true"/>
</sec:authorize>

<div align="center">
    <form action="${pageContext.request.contextPath}/" method="get">
        <input type="submit" value="To main page">
    </form>
</div>

<div align="center">
    <form action="/users/profile/${current_user_id}" method="get">
        <input type="submit" value="To profile page">
    </form>
</div>
<div align="center">
    <c:choose>
        <c:when test="${isOwner || isAdmin}">

            <form action="/startups/${startup.id}/update" method="post">

                <table>
                    <caption><h1>Startup update</h1></caption>
                    <tr>
                        <th></th>
                        <th>Old startup data</th>
                        <th>New startup data</th>
                    </tr>

                    <tr>
                        <td>Startup name</td>
                        <td>${startup.name}</td>
                        <td><input type="text" name="name" value="${startup.name}"></td>
                    </tr>

                    <tr>
                        <td>Description</td>
                        <td>${startup.description}</td>
                        <td><input type="text" name="description" value="${startup.description}"></td>
                    </tr>

                    <tr>
                        <td>Budget</td>
                        <td>${startup.budget}</td>
                        <td><input type="number" name="budget" value="${startup.budget}"></td>
                    </tr>

                    <tr>
                    <td>Industry</td>
                    <td>${startup.industry.name}</td>
                    <td>
                        <select name="industry_id">

                            <option value="${startup.industry.id}" selected>${startup.industry.name}</option>

                            <c:forEach var="industry" items="${industries}">
                                <option value="${industry.id}">${industry.name}</option>
                            </c:forEach>

                        </select>
                    </td>
                    </tr>

                    <tr>
                        <td>Country</td>
                        <td>${startup.country.name}</td>
                        <td>
                            <select name="country_id">

                                <option value="${startup.country.id}" selected>${startup.country.name}</option>

                                <c:forEach var="country" items="${countries}">
                                    <option value="${country.id}">${country.name}</option>
                                </c:forEach>

                            </select>
                        </td>
                    </tr>

                    <tr>
                        <th>
                            <div align="center">
                                <button onclick="goBack()">Go Back</button>
                                <script>
                                    function goBack() {
                                        window.history.back();
                                    }
                                </script>
                            </div>
                        </th>
                        <th><input type="submit" value="Update"></th>
                        <th><input type="reset" value="Reset"></th>
                    </tr>
                </table>

            </form>
        </c:when>

        <c:otherwise>
            <h1>This is not your startup</h1>
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>
