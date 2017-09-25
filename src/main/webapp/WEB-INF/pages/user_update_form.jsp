<%--
  Created by IntelliJ IDEA.
  User: root
  Date: 20.09.2017
  Time: 19:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Update user form</title>
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
    <h1 align="center">Users update form</h1>


    <c:set var="current_user_id">
        <sec:authentication property="principal.id"/>
    </c:set>
    <c:set var="id_test" value="${user.id == current_user_id}"/>
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

    <c:choose>
    <c:when test="${id_test || isAdmin}">

    <form action="/users/profile/${user.id}/update/" method="post">

        <table>
            <caption><h1>User update</h1></caption>
            <tr>
                <th></th>
                <th>Old profile</th>
                <th>New profile</th>
            </tr>

            <tr>
                <td>Password</td>
                <td>${user.password}</td>
                <td><input type="text" name="password" value="${user.password}"></td>
            </tr>

            <tr>
                <td>First name</td>
                <td>${user.firstName}</td>
                <td><input type="text" name="first_name" value="${user.firstName}"></td>
            </tr>

            <tr>
                <td>Last name</td>
                <td>${user.lastName}</td>
                <td><input type="text" name="last_name" value="${user.lastName}"></td>
            </tr>

            <tr>
                <td>Description</td>
                <td>${user.description}</td>
                <td><input type="text" name="description" value="${user.description}"></td>
            </tr>

            <tr>
                <td>Country</td>
                <td>${user.country.name}</td>
                <td><select name="country_id" >
                    <option value="${user.country.id}" selected>${user.country.name}</option>
                    <c:forEach var="country" items="${countries}">
                        <option value="${country.id}">${country.name}</option>
                    </c:forEach>
                </select></td>
            </tr>



            <tr>
                <th><div align="center">
                    <button onclick="goBack()">Go Back</button>
                    <script>
                        function goBack() {
                            window.history.back();
                        }
                    </script>
                </div></th>
                <th><input type="submit" value="Update"></th>
                <th><input type="reset" value="Reset"></th>
            </tr>
        </table>

    </form>
    </c:when>

    <c:otherwise>
    <h1>This is not your profile</h1>
    </c:otherwise>
    </c:choose>
</body>
</html>
