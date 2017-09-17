
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
<form method="post" action="${pageContext.request.contextPath}/register">
    <div>
        <table align="center">
            <tr align="center">
                <th>Login</th>
                <td>
                    <input type="text" name="login">
                </td>
            </tr>
            <tr align="center">
                <th>Password</th>
                <td>
                    <input type="password" name="password">
                </td>
            </tr>
            <tr align="center">
                <td>
                    <input type="submit" value="Register">
                </td>
            </tr>
        </table>
    </div>
</form>
</body>
</html>
