<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Update user form</title>
    <%@include file="header_config.jsp" %>
</head>
<body>
<div class="wrapper">
    <div class="content">
        <%@include file="navbar.jsp" %>
        <div class="container">
            <div class="container">
                <h1 class="text-center">Update user profile</h1>

                <c:set var="current_user_id">
                    <sec:authentication property="principal.id"/>
                </c:set>
                <c:set var="isOwner" value="${user.id == current_user_id}"/>
                <c:set var="isAdmin" value="false"/>
                <sec:authorize access="hasRole('ADMIN')">
                    <c:set var="isAdmin" value="true"/>
                </sec:authorize>

                <div align="center">
                    <form action="${pageContext.request.contextPath}/" method="get">
                        <input type="submit" value="To main page">
                    </form>
                </div>

                <div align="center">
                    <form action="${pageContext.request.contextPath}/users/profile/${current_user_id}" method="get">
                        <input type="submit" value="To profile page">
                    </form>
                </div>

                <c:choose>
                    <c:when test="${isOwner || isAdmin}">

                        <form action="${pageContext.request.contextPath}/users/profile/${user.id}/update" method="post">

                            <table>
                                <h1>User update</h1>
                                <tr>
                                    <th></th>
                                    <th>Old profile</th>
                                    <th>New profile</th>
                                </tr>

                                <tr>
                                    <td>Password</td>
                                    <td>Enter new password</td>
                                    <td><input type="text" name="password" placeholder="Enter new password"></td>
                                </tr>

                                <tr>
                                    <td>Email</td>
                                    <td>${user.email}</td>
                                    <td><input type="email" name="email" value="${user.email}"></td>
                                </tr>

                                <tr>
                                    <td>First name</td>
                                    <td>${user.firstName}</td>
                                    <td><input type="text" name="first_name" value="${user.firstName}"></td>
                                </tr>

                                <tr>
                                    <td>Last name</td>
                                    <td>${user.lastName}</td>
                                    <td><input type="text" name="last_name" value="${user.lastName}"></td>
                                </tr>

                                <tr>
                                    <td>Description</td>
                                    <td>${user.description}</td>
                                    <td><input type="text" name="description" value="${user.description}"></td>
                                </tr>

                                <tr>
                                    <td>Country</td>
                                    <td>${user.country.name}</td>
                                    <td>
                                        <select name="country_id">

                                            <option value="${user.country.id}" selected>${user.country.name}</option>

                                            <c:forEach var="country" items="${countries}">
                                                <option value="${country.id}">${country.name}</option>
                                            </c:forEach>

                                        </select>
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
                    </c:when>

                    <c:otherwise>
                        <h1>This is not your profile</h1>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
        <%@include file="footer.jsp" %>
    </div>
</body>
</html>
