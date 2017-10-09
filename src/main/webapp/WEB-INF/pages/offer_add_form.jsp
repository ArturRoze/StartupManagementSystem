<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Create offer</title>
    <%@include file="header_config.jsp" %>
</head>
<body>
<div class="wrapper">
    <div class="content">
        <%@include file="navbar.jsp" %>
        <div class="container">
            <%--<c:set var="current_user_id">--%>
            <%--<sec:authentication property="principal.id"/>--%>
            <%--</c:set>--%>
            <div>
                <form action="${pageContext.request.contextPath}/offers/new/offer/" method="post">
                    <input type="number" name="user_id" value="${current_user_id}" hidden>
                    <table align="center">
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
</html>
