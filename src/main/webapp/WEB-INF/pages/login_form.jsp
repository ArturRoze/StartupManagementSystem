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
            <h2 class="text-center">Please authorise</h2>
            <form action="${pageContext.request.contextPath}/login" method="post">
                <div class="row justify-content-md-center">
                    <div class="col-md-5">
                        <hr>
                        <div class="form-group">
                            <label for="loginInput">Enter your login:</label>
                            <div class="input-group">
                                <span class="input-group-addon"><i
                                        class="material-icons">fingerprint</i></span>
                                <input class="form-control" id="loginInput" type="text" name="username"
                                       placeholder="login" aria-describedby="loginHelp" required autofocus>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="passwordInput">Enter password</label>
                            <div class="input-group">
                                <span class="input-group-addon"><i class="material-icons">star_border</i></span>
                                <input class="form-control" id="passwordInput" type="password" name="password"
                                       placeholder="password" aria-describedby="loginHelp" required>
                            </div>
                            <small id="loginHelp" class="form-text text-muted">Please do not share your personal
                                authorization data to anyone else.
                            </small>
                        </div>
                        <hr>
                        <div class="navbar rounded px-4 mt-3 mb-4 row">
                            <button type="submit" class="btn btn-primary mx-1">Login</button>
                            <button type="reset" class="btn btn-outline-secondary mx-1">Reset</button>
                            <a class="btn btn-success text-light ml-auto btn-with-icon"
                               role="button" data-toggle="modal" data-target="#registerModal">
                                <div><i class="material-icons">touch_app</i></div>
                                <span class="">Join</span></a>
                        </div>
                    </div>
                </div>
                <%--<table class="table">
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
                </table>--%>
            </form>
            <%--<div align="center">
                <form action="${pageContext.request.contextPath}/registration" method="get">
                    <input type="submit" value="Registration">
                </form>
            </div>--%>
        </div>
    </div>
    <%@include file="footer.jsp" %>
</div>
</body>
</html>