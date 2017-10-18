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
            <h2 class="text-center">Create new admin</h2>
            <div class="row justify-content-center">
                <div class="col-6 border border-gr rounded p-3">
                    <form action="${pageContext.request.contextPath}/admins/new/admin/"
                          class="mb-0" method="post">
                        <table class="table mb-0">
                            <tr>
                                <th class="bg-light">Login</th>
                                <td>
                                    <input type="text" class="form-control" name="login"
                                           placeholder="login" autofocus required>
                                </td>
                            </tr>
                            <tr>
                                <th class="bg-light">Password</th>
                                <td>
                                    <input type="password" class="form-control" name="password"
                                           placeholder="password" required>
                                </td>
                            </tr>
                            <tr>
                                <th class="bg-light">E-mail</th>
                                <td>
                                    <input type="email" class="form-control" name="email" placeholder="E-mail">
                                </td>
                            </tr>
                        </table>
                        <div class="navbar bg-bar mt-3">
                            <div>
                                <%@include file="buttons/back_button.jsp" %>
                            </div>
                            <div>
                                <%@include file="buttons/create_button_lg.jsp" %>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <%@include file="footer.jsp" %>
</div>

</body>
</html>
