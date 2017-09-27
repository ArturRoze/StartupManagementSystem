<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Show admins</title>
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

    <div align="center">

        <table>
            <caption>
                <h3>Show all admins</h3>
            </caption>

            <tr>
                <th>Id</th>
                <th>Login</th>
                <th>Password</th>
                <th>Email</th>
            </tr>

            <c:forEach var="admin" items="${admins}">
                <tr>
                    <td>${admin.id}</td>
                    <td>${admin.login}</td>
                    <td>${admin.password}</td>
                    <td>${admin.email}</td>
                </tr>
            </c:forEach>
        </table>

        <div align="center">
            <form action="/admins/profile/${current_user_id}" method="get">
                <input type="submit" value="To admin profile">
            </form>
        </div>

    </div>

</body>
</html>
