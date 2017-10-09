<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
    <title>Startup management system</title>
    <%@include file="header_config.jsp" %>
</head>
<body>
<div class="wrapper">
    <div class="content">
        <%@include file="navbar.jsp" %>
        <div class="container">
            <div align="center">
                <form action="${pageContext.request.contextPath}/login" method="post">
                    <table align="center">
                        <tr>
                            <th>Enter login</th>
                            <td>
                                <input autofocus type="text" required name="username" placeholder="Login">
                            </td>
                        </tr>
                        <tr>
                            <th>Enter password</th>
                            <td>
                                <input type="password" required name="password" placeholder="Password">
                            </td>
                        </tr>
                        <tr>
                            <td align="center">
                                <button type="submit">Enter</button>
                            </td>
                        </tr>
                    </table>
                </form>
            </div>
            <div align="center">
                <form action="${pageContext.request.contextPath}/registration" method="get">
                    <input type="submit" value="Registration">
                </form>
                <form action="${pageContext.request.contextPath}/" method="get">
                    <input type="submit" value="To main page">
                </form>
            </div>
        </div>
    </div>
    <%@include file="footer.jsp" %>
</div>
</body>
</html>