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
<div align="center">
    <h1 align="center">News Page</h1>

    <c:set var="current_user_id">
        <sec:authentication property="principal.id"/>
    </c:set>

    <c:set var="isAdmin" value="false"/>

    <sec:authorize access="hasRole('ADMIN')">
        <c:set var="isAdmin" value="true"/>
    </sec:authorize>

    <div align="center">
        <form action="${pageContext.request.contextPath}/" method="get">
            <input type="submit" value="To main page">
        </form>
    </div>

    <c:choose>

    <c:when test="${isAdmin}">
    <div align="center">
        <form action="/admins/profile/${current_user_id}" method="get">
            <input type="submit" value="To admin profile page">
        </form>
    </div>
    </c:when>

    <c:otherwise>
    <div align="center">
        <form action="/users/profile/${current_user_id}" method="get">
            <input type="submit" value="To user profile page">
        </form>
    </div>
    </c:otherwise>

    </c:choose>

    <div align="center">
        <form action="${pageContext.request.contextPath}/logout" method="post">
            <input type="submit" value="Logout">
        </form>
    </div>

    <div align="center">
        <table>
            <caption><h1>News</h1></caption>
            <tr>
                <th>Id</th>
                <th>Class name</th>
                <th>Name</th>
                <th>Description</th>
                <th>Industry</th>
                <th>Owner</th>
                <th>Budget</th>
                <th>Registration</th>
                <th>Country</th>
                <th>To startup</th>
            </tr>

            <c:forEach var="news" items="${news_list}">
                <tr>
                    <td>${news.id}</td>
                    <td>${news['class'].simpleName}</td>

                    <c:choose>
                        <c:when test="${news['class'].simpleName eq 'Startup'}">
                            <td>${news.name}</td>
                        </c:when>
                        <c:otherwise>
                            <td></td>
                        </c:otherwise>
                    </c:choose>

                    <td>${news.description}</td>
                    <td>${news.industry.name}</td>
                    <td>${news.user.firstName} ${news.user.lastName}</td>
                    <td>${news.budget}</td>
                    <td>${news.registrationDate}</td>
                    <td>${news.country.name}</td>

                    <c:choose>
                        <c:when test="${news['class'].simpleName eq 'Startup'}">
                            <td>
                                <form action="/startups/${news.id}" method="get">
                                    <input type="submit" value="Show startup">
                                </form>
                            </td>
                        </c:when>
                        <c:when test="${news['class'].simpleName eq 'Offer'}">
                            <td>
                                <form action="/offers/${news.id}" method="get">
                                    <input type="submit" value="Show offer">
                                </form>
                            </td>
                        </c:when>
                        <c:otherwise>
                            <td></td>
                        </c:otherwise>
                    </c:choose>

                </tr>
            </c:forEach>
        </table>

        <br>

        <div>

            <table>
                <tr>
                    <c:if test="${current_page > 1}">
                        <th>
                            <form action="${pageContext.request.contextPath}/news" method="get">
                                <input type="number" name="page" value="1" hidden>
                                <input type="submit" value="First page">
                            </form>
                        </th>
                        <th>
                            <form action="${pageContext.request.contextPath}/news" method="get">
                                <input type="number" name="page" value="${current_page -1}" hidden>
                                <input type="submit" value="Previous page">
                            </form>
                        </th>
                    </c:if>

                    <th>
                        Page ${current_page} of ${pages_count}
                    </th>

                    <c:if test="${current_page < pages_count}">
                        <th>
                            <form action="${pageContext.request.contextPath}/news" method="get">
                                <input type="number" name="page" value="${current_page + 1}" hidden>
                                <input type="submit" value="Next page">
                            </form>
                        </th>
                        <th>
                            <form action="${pageContext.request.contextPath}/news" method="get">
                                <input type="number" name="page" value="${pages_count}" hidden>
                                <input type="submit" value="Last page">
                            </form>
                        </th>
                    </c:if>
                </tr>
            </table>

        </div>

    </div>
</body>
</html>
