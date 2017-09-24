
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
<form method="post" action="${pageContext.request.contextPath}/registration/">
    <div>
        <table align="center">
            <tr align="center">
                <th>Login</th>
                <td>
                    <input type="text" required name="login">
                </td>
            </tr>
            <tr align="center">
                <th>Password</th>
                <td>
                    <input type="password" required name="password">
                </td>
            </tr>
            <tr align="center">
                <td>
                    <input type="submit" value="Register">
                </td>
            </tr>
        </table>
        <div align="center">
            <button onclick="goBack()">Go Back</button>
            <script>
                function goBack() {
                    window.history.back();
                }
            </script>
        </div>
    </div>
</form>
</body>
</html>
