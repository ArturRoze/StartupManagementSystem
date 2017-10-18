<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Update startup: "${startup.name}"</title>
    <%@include file="header_config.jsp" %>
</head>
<body>
<div class="wrapper">
    <div class="content">
        <%@include file="navbar.jsp" %>
        <div class="container">
            <c:set var="isOwner" value="${startup.user.id == current_user_id && !isAdmin}"/>

            <h2 class="text-center"><c:choose>
                <c:when test="${isOwner}">My</c:when><c:otherwise>User</c:otherwise></c:choose> startup update form
                <small>[id: ${startup.id}]</small>
                </h2>

            <div align="center">
                <form action="${pageContext.request.contextPath}/users/profile/${current_user_id}" method="get">
                    <input type="submit" value="To profile page">
                </form>
            </div>
            <div align="center">
                <c:choose>
                    <c:when test="${isOwner || isAdmin}">

                        <form action="${pageContext.request.contextPath}/startups/${startup.id}/update" method="post">

                            <table>
                                <h1>Startup update</h1>
                                <tr>
                                    <th></th>
                                    <th>Old startup data</th>
                                    <th>New startup data</th>
                                </tr>

                                <tr>
                                    <td>Startup name</td>
                                    <td>${startup.name}</td>
                                    <td><input type="text" name="name" value="${startup.name}"></td>
                                </tr>

                                <tr>
                                    <td>Description</td>
                                    <td>${startup.description}</td>
                                    <td><input type="text" name="description" value="${startup.description}"></td>
                                </tr>

                                <tr>
                                    <td>Budget</td>
                                    <td>${startup.budget}</td>
                                    <td><input type="number" min="1" name="budget" value="${startup.budget}"></td>
                                </tr>

                                <tr>
                                    <td>Industry</td>
                                    <td>${startup.industry.name}</td>
                                    <td>
                                        <select name="industry_id">

                                            <option value="${startup.industry.id}"
                                                    selected>${startup.industry.name}</option>

                                            <c:forEach var="industry" items="${industries}">
                                                <option value="${industry.id}">${industry.name}</option>
                                            </c:forEach>

                                        </select>
                                    </td>
                                </tr>

                                <tr>
                                    <td>Country</td>
                                    <td>${startup.country.name}</td>
                                    <td>
                                        <select name="country_id">

                                            <option value="${startup.country.id}"
                                                    selected>${startup.country.name}</option>

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
                        <%@include file="no_permission.jsp" %>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>
    <%@include file="footer.jsp" %>
</div>
</body>
</html>
