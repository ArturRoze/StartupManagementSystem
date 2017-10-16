<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>New startup form</title>
    <%@include file="header_config.jsp" %>
</head>
<body>
<div class="wrapper">
    <div class="content">
        <%@include file="navbar.jsp" %>
        <div class="container">
            <div>
                <form action="${pageContext.request.contextPath}/startups/new/startup/"
                      accept-charset="windows-1251" method="post">
                    <input type="number" name="user_id" value="${current_user_id}" hidden>
                    <table align="center">
                        <tr>
                            <th>Enter name of startup</th>
                            <td>
                                <input autofocus type="text" name="name">
                            </td>
                        </tr>
                        <tr>
                            <th>Enter description</th>
                            <td>
                                <input type="text" name="description">
                            </td>
                        </tr>

                        <tr>
                            <th>Enter budget</th>
                            <td>
                                <input type="number" name="budget">
                            </td>
                        </tr>

                        <tr>
                            <th>Choose industry</th>
                            <td><select name="industry_id">
                                <c:forEach var="industry" items="${industries}">
                                    <option value="${industry.id}">${industry.name}</option>
                                </c:forEach>
                            </select></td>
                        </tr>

                        <tr>
                            <th>Choose country</th>
                            <td><select name="country_id">
                                <c:forEach var="country" items="${countries}">
                                    <option value="${country.id}">${country.name}</option>
                                </c:forEach>
                            </select></td>
                        </tr>

                        <tr>
                            <td>
                                <div align="center">
                                    <button onclick="goBack()">Go Back</button>
                                    <script>
                                        function goBack() {
                                            window.history.back();
                                        }
                                    </script>
                                </div>
                            </td>
                            <td align="center">
                                <button type="submit">Enter</button>
                            </td>
                        </tr>
                    </table>
                </form>
            </div>
        </div>
    </div>
    <%@include file="footer.jsp" %>
</div>
</body>
</html>--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>New startup form</title>
    <%@include file="header_config.jsp" %>
</head>
<body>
<div class="wrapper">
    <div class="content">
        <%@include file="navbar.jsp" %>
        <div class="container">
            <div class="justify-content-md-center">
                <h2 class="text-center mt-4">Please enter data for a new startup</h2>
                <form action="${pageContext.request.contextPath}/startups/new/startup/" method="post">
                    <input type="number" name="user_id" value="${current_user_id}" hidden>
                    <table align="center">
                        <tr>
                            <th>Enter name of startup</th>
                            <td>
                                <input autofocus type="text" name="name">
                            </td>
                        </tr>
                        <tr>
                            <th>Enter description</th>
                            <td>
                                <input type="text" name="description">
                            </td>
                        </tr>

                        <tr>
                            <th>Enter budget</th>
                            <td>
                                <input type="number" name="budget">
                            </td>
                        </tr>

                        <tr>
                            <th>Choose industry</th>
                            <td>
                                <select name="industry_id">
                                    <c:forEach var="industry" items="${industries}">
                                        <option value="${industry.id}">${industry.name}</option>
                                    </c:forEach>
                                </select>
                            </td>
                        </tr>

                        <tr>
                            <th>Choose country</th>
                            <td>
                                <select name="country_id">
                                    <c:forEach var="country" items="${countries}">
                                        <option value="${country.id}">${country.name}</option>
                                    </c:forEach>
                                </select>
                            </td>
                        </tr>
                    </table>
                    <div class="navbar bg-bar rounded px-3 mt-3 mb-5">
                        <%@include file="buttons/back_button.jsp" %>
                        <button class="btn btn-success" type="submit">Enter</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <%@include file="footer.jsp" %>
</div>
</body>
</html>
