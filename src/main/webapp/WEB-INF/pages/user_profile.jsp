<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Profile ${user.id}</title>
    <%@include file="header.jsp"%>
</head>
<body>
<%@include file="navbar.jsp"%>
<div class="container">

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

    <c:set var="current_user_id">
        <sec:authentication property="principal.id"/>
    </c:set>

    <c:set var="isOwner" value="${user.id == current_user_id}"/>

    <c:set var="isAdmin" value="false"/>

    <sec:authorize access="hasRole('ADMIN')">
        <c:set var="isAdmin" value="true"/>
    </sec:authorize>


    <div align="center">
        <table>
            <caption><h1>User profile</h1></caption>

            <c:if test="${isOwner || isAdmin}">
                <tr>
                    <th>Id</th>
                    <td>${user.id}</td>
                </tr>
                <tr>
                    <th>login</th>
                    <td>${user.login}</td>
                </tr>
                <tr>
                    <th>password</th>
                    <td>${user.password}</td>
                </tr>
            </c:if>

            <tr>
                <th>Email</th>
                <td>${user.email}</td>
            </tr>
            <tr>
                <th>Registration</th>
                <td>${user.registrationDate}</td>
            </tr>
            <tr>
                <th>First name</th>
                <td>${user.firstName}</td>
            </tr>
            <tr>
                <th>Last name</th>
                <td>${user.lastName}</td>
            </tr>
            <tr>
                <th>Description</th>
                <td>${user.description}</td>
            </tr>
            <tr>
                <th>Country</th>
                <td>${user.country.name}</td>
            </tr>
        </table>

        <table>
            <caption><h1>List of all user ${user.id} startups</h1></caption>
            <tr>
                <th>Id</th>
                <th>Name</th>
                <th>Description</th>
                <th>Industry</th>
                <th>Owner</th>
                <th>Budget</th>
                <th>Registration</th>
                <th>Country</th>
                <th>To startup</th>
            </tr>
            <c:forEach var="startup" items="${user.startups}">
                <tr>
                    <td>${startup.id}</td>
                    <td>${startup.name}</td>
                    <td>${startup.description}</td>
                    <td>${startup.industry.name}</td>
                    <td>${startup.user.firstName} ${startup.user.lastName}</td>
                    <td>${startup.budget}</td>
                    <td>${startup.registrationDate}</td>
                    <td>${startup.country.name}</td>
                    <td>
                        <form action="/startups/${startup.id}" method="get">
                            <input type="submit" value="Show startup">
                        </form>
                    </td>

                </tr>
            </c:forEach>
        </table>

        <table>
            <caption><h1>List of all user ${user.id} offers</h1></caption>
            <tr>
                <th>Id</th>
                <th>Description</th>
                <th>Industry</th>
                <th>Owner</th>
                <th>Budget</th>
                <th>Registration</th>
                <th>Country</th>
                <th>To startup</th>
            </tr>
            <c:forEach var="offer" items="${user.offers}">
                <tr>
                    <td>${offer.id}</td>
                    <td>${offer.description}</td>
                    <td>${offer.industry.name}</td>
                    <td>${offer.user.firstName} ${offer.user.lastName}</td>
                    <td>${offer.budget}</td>
                    <td>${offer.registrationDate}</td>
                    <td>${offer.country.name}</td>
                    <td>
                        <form action="/offers/${offer.id}" method="get">
                            <input type="submit" value="Show offer">
                        </form>
                    </td>

                </tr>
            </c:forEach>
        </table>
    </div>
    <br>

    <div align="center">
            <div align="center">
                <form action="${pageContext.request.contextPath}/startups" method="get">
                    <input type="submit" value="Show startups">
                </form>
            </div>

            <div align="center">
                <form action="${pageContext.request.contextPath}/offers" method="get">
                    <input type="submit" value="Show offers">
                </form>
            </div>
    </div>
    <br>

    <div>
        <c:if test="${isOwner || isAdmin}">
            <div align="center">
                <form action="/users/profile/${user.id}/edit" method="get">
                    <input type="submit" value="Update">
                </form>
            </div>

            <div align="center">
                <form action="/users/profile/${user.id}/delete" method="get">
                    <input type="submit" value="Delete">
                </form>
            </div>
        </c:if>

        <c:if test="${isOwner && !isAdmin}">
            <div align="center">
                <form action="${pageContext.request.contextPath}/startups/new/startup" method="get">
                    <input type="submit" value="New startup">
                </form>
            </div>

            <div align="center">
                <form action="${pageContext.request.contextPath}/offers/new/offer" method="get">
                    <input type="submit" value="New offer">
                </form>
            </div>
        </c:if>
    </div>

</div>
</body>
</html>
