<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Admin update</title>
    <%@include file="header_config.jsp" %>
</head>
<body>
<div class="wrapper">
    <div class="content">
        <%@include file="navbar.jsp" %>
        <div class="container">

            <div>
                <form action="${pageContext.request.contextPath}/admins/profile/${admin.id}/update/" method="post">
                    <table>
                        <h3>Update admin data</h3>
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
                            <th>
                                <div align="center">
                                    <button onclick="goBack()">Go Back</button>
                                    <script>
                                        function goBack() {
                                            window.history.back();
                                        }
                                    </script>
                                </div>
                            </th>
                            <th><input type="submit" value="Update"></th>
                            <th><input type="reset" value="Reset"></th>
                        </tr>
                    </table>
                </form>
            </div>
        </div>
    </div>
    <%@include file="footer.jsp" %>
</div>
</body>
</html>