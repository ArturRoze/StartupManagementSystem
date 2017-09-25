<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>admin profile</title>
</head>
<body>
<div align="center">
    <div align="center">
        <div align="center">
            <form action="${pageContext.request.contextPath}/news" method="get">
                <input type="submit" value="News">
            </form>
        </div>
        <div align="center">
            <form action="${pageContext.request.contextPath}/logout" method="post">
                <input type="submit" value="Logout">
            </form>
        </div>
    </div>

    <div>
        <c:set var="admin_current_id">
            <sec:authentication property="id"/>
        </c:set>
        <c:set var="isOwner" value="${admin_current_id == admin.id}"/>
    </div>
    <div>
        <table>
            <caption><h3>Admin profile</h3></caption>
            <tr>
                <th>id</th>
                <td>${admin.id}</td>
            </tr>
            <th>login</th>
            <td>${admin.login}</td>
            </tr>
            <th>email</th>
            <td>${admin.email}</td>
            </tr>
        </table>
    </div>

    <c:if test="${isOwner}">
        <div>
            <div>
                <form action="/admins/profile/${admin.id}/update" method="get">
                    <input type="submit" value="update">
                </form>
            </div>
            <div>
                <form action="/admins/profile/${admin.id}/delete" method="get">
                    <input type="submit" value="delete">
                </form>
            </div>
        </div>
    </c:if>

    <div>
        <div>
            <form action="/admins" method="get">
                <input type="submit" value="show all admins">
            </form>
        </div>
        <div>
            <form action="/admins/new/admin" method="get">
                <input type="submit" value="create new admin">
            </form>
        </div>
    </div>
    <div>
        <form action="/users" method="get">
            <input type="submit" value="show all users">
        </form>
    </div>
</div>

</body>
</html>
