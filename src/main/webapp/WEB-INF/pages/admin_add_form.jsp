<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Create admin</title>
    <%@include file="header_config.jsp" %>
</head>
<body>
<div class="wrapper">
    <div class="content">
        <%@include file="navbar.jsp" %>
        <div class="container">
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
                            <input autofocus type="email" name="email" placeholder="email">
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
        </div>
    </div>
    <%@include file="footer.jsp" %>
</div>

</body>
</html>
