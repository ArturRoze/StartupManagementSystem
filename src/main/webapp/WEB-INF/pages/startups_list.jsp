<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
    <title>Show all startups</title>
    <%@include file="header_config.jsp" %>
</head>
<body>
<div class="wrapper">
    <div class="content">
        <%@include file="navbar.jsp" %>
        <div class="container">
                <table>
                    <caption><h1>List of all startups</h1></caption>
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
                    <c:forEach var="startup" items="${startups}">
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
                                <form action="${pageContext.request.contextPath}/startups/${startup.id}" method="get">
                                    <input type="submit" value="Show startup">
                                </form>
                            </td>

                        </tr>
                    </c:forEach>
                </table>
        </div>
    </div>
    <%@include file="footer.jsp" %>
</div>
</body>
</html>
