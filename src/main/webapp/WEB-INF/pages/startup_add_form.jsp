<%--
  Created by IntelliJ IDEA.
  User: root
  Date: 24.09.2017
  Time: 2:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>New startup form</title>
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
<c:set var="current_user_id">
    <sec:authentication property="principal.id"/>
</c:set>
<div>
    <form action="${pageContext.request.contextPath}/startups/new/startup/" method="post">
        <input type="number" name="user_id" value="${current_user_id}" hidden>
        <table align="center">
            <tr>
                <th>Enter name of startup</th>
                <td>
                    <input autofocus type="text" name="name">
                </td>
            </tr>
            <tr>
                <th>Enter description</th>
                <td>
                    <input type="text" name="description">
                </td>
            </tr>

            <tr>
                <th>Enter budget</th>
                <td>
                    <input type="number" name="budget">
                </td>
            </tr>

            <tr>
                <th>Choose industry</th>
                <td><select name="country_id">
                    <c:forEach var="industry" items="${industries}">
                        <option value="${industry.id}">${industry.name}</option>
                    </c:forEach>
                </select></td>
            </tr>

            <tr>
                <th>Choose country</th>
                <td><select name="country_id">
                    <c:forEach var="country" items="${countries}">
                        <option value="${country.id}">${country.name}</option>
                    </c:forEach>
                </select></td>
            </tr>

            <tr>
                <td>
                    <div align="center">
                        <button onclick="goBack()">Go Back</button>
                        <script>
                            function goBack() {
                                window.history.back();
                            }
                        </script>
                    </div>
                </td>
                <td align="center">
                    <button type="submit">Enter</button>
                </td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
