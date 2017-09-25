<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Admin update</title>
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
    <div align="center">
        <div align="center">
            <form action="${pageContext.request.contextPath}/news" method="get">
                <input type="submit" value="News">
            </form>
        </div>
        <div align="center">
            <form action="${pageContext.request.contextPath}/logout" method="post">
                <input type="submit" value="Logout">
            </form>
        </div>
    </div>
</div>

<div>
    <form action="/admins/profile/${admin.id}/update/" method="post">
        <table>
            <caption><h3>Admin edit</h3></caption>
            <tr>
                <th></th>
                <th>Old admin</th>
                <th>New admin</th>
            </tr>
            <tr>
                <td>login</td>
                <td>${admin.login}</td>
                <td>not edit</td>
            </tr>
            <tr>
                <td>email</td>
                <td>${admin.email}</td>
                <td>
                    <input type="email" name="email" value="${admin.email}">
                </td>
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
</div>

</body>
</html>
