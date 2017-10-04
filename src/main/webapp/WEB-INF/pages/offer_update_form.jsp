<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Offer update</title>
    <style>
        table, td, th {
            border: 1px solid #ddd;
            text-align: left;
        }

        table {
            border-collapse: collapse;
            width: 50%;
        }

        th, td {
            padding: 15px;
        }
    </style>
</head>
<body>
<h1 align="center">Offer update form</h1>

<c:set var="current_user_id">
    <sec:authentication property="principal.id"/>
</c:set>
<c:set var="isOwner" value="${offer.user.id == current_user_id}"/>
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
<div align="center">
    <c:choose>
        <c:when test="${isOwner || isAdmin}">

            <form action="${pageContext.request.contextPath}/offers/${offer.id}/update" method="post">

                <table>
                    <caption><h1>Offer update</h1></caption>
                    <tr>
                        <th></th>
                        <th>Old offer data</th>
                        <th>New offer data</th>
                    </tr>

                    <tr>
                        <td>Description</td>
                        <td>${offer.description}</td>
                        <td><input type="text" name="description" value="${offer.description}"></td>
                    </tr>

                    <tr>
                        <td>Budget</td>
                        <td>${offer.budget}</td>
                        <td><input type="number" name="budget" value="${offer.budget}"></td>
                    </tr>

                    <tr>
                        <td>Industry</td>
                        <td>${offer.industry.name}</td>
                        <td>
                            <select name="industry_id">

                                <option value="${offer.industry.id}" selected>${offer.industry.name}</option>

                                <c:forEach var="industry" items="${industries}">
                                    <option value="${industry.id}">${industry.name}</option>
                                </c:forEach>

                            </select>
                        </td>
                    </tr>

                    <tr>
                        <td>Country</td>
                        <td>${offer.country.name}</td>
                        <td>
                            <select name="country_id">

                                <option value="${offer.country.id}" selected>${offer.country.name}</option>

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
            <h1>This is not your offer</h1>
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>
