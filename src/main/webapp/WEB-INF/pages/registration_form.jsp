<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Registration</title>
    <%@include file="header_config.jsp" %>
</head>
<body>
<div class="wrapper">
    <div class="content">
        <%@include file="navbar.jsp" %>
        <div class="container">
            <form method="post" action="${pageContext.request.contextPath}/register">
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
        </div>
    </div>
    <%@include file="footer.jsp" %>
</div>
</body>
</html>
