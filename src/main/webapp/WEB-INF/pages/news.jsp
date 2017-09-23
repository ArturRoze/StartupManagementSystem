<%--
  Created by IntelliJ IDEA.
  User: root
  Date: 20.09.2017
  Time: 16:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>News</title>
</head>
<body>
<div align="center">
    <h1 align="center">News Page</h1>
    <a href="/">To main page</a>

    <c:set var="id">
    <sec:authentication property="principal.id"/>
    </c:set>

    <a href="/users/profile/${id}">To profile page ${id}</a><br>

    <div align="center">
        <form action="/logout" method="post">
            <input type="submit" value="Logout">
        </form>
    </div>
</div>
</body>
</html>
