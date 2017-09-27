<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Create admin</title>
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
<form action="${pageContext.request.contextPath}/admins/new/admin/" method="post">
    <table align="center">
        <tr>
            <th>Enter login</th>
            <td>
                <input autofocus type="text" required name="login" placeholder="login">
            </td>
        </tr>
        <tr>
            <th>Enter password</th>
            <td>
                <input autofocus type="text" required name="password" placeholder="password">
            </td>
        </tr>
        <tr>
            <th>Enter email</th>
            <td>
                <input autofocus type="email"  name="email" placeholder="email">
            </td>
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

</body>
</html>
