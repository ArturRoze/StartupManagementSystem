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
            <h2 class="text-center mt-4">Please enter data for a new startup</h2>
            <form action="${pageContext.request.contextPath}/startups/new/startup/" method="post" novalidate>
                <input type="number" name="user_id" value="${current_user_id}" hidden>
                <div class="row border border-gr rounded mb-4">
                    <div class="col-md-6 p-2">
                        <div class="btn-block">
                            <table class="table table-hover mb-0">
                                <tr>
                                    <th class="w-25" scope="row">Enter name<br>for startup</th>
                                    <td>
                                        <input type="text" class="form-control" name="name"
                                               autofocus required>
                                    </td>
                                </tr>
                                <tr>
                                    <th scope="row">Enter description</th>
                                    <td>
                                        <input type="text" class="form-control" name="description" required>
                                    </td>
                                </tr>
                                <tr>
                                    <th>Enter budget</th>
                                    <td>
                                        <input type="number" class="form-control" name="budget" value="0" required>
                                    </td>
                                </tr>
                            </table>
                        </div>
                    </div>
                    <div class="col-md-6 p-2">
                        <div class="btn-block">
                            <table class="table table-hover mb-0">
                                <tr>
                                    <th class="w-25" scope="row">Industry</th>
                                    <td>
                                        <select class="custom-select mb-2 mr-sm-2 mb-sm-0" name="industry_id" required>
                                            <c:forEach var="industry" items="${industries}">
                                                <option value="${industry.id}">${industry.name}</option>
                                            </c:forEach>
                                        </select>
                                    </td>
                                </tr>
                                <tr>
                                    <th scope="row">Country</th>
                                    <td>
                                        <select class="custom-select mb-2 mr-sm-2 mb-sm-0 btn-block" name="country_id" required>
                                            <c:forEach var="country" items="${countries}">
                                                <option value="${country.id}">${country.name}</option>
                                            </c:forEach>
                                        </select>
                                    </td>
                                </tr>
                            </table>
                        </div>
                    </div>
                </div>
                <div class="justify-content-md-center">
                    <div class="navbar bg-bar px-3 mt-3 mb-5">
                        <%@include file="buttons/back_button.jsp" %>
                        <button class="btn btn-lg btn-success btn-with-icon" type="submit">
                            <div><i class="material-icons">done</i></div>
                            <span>Create</span></button>
                    </div>
                </div>
            </form>
        </div>
    </div>
    <%@include file="footer.jsp" %>
</div>
</body>
</html>
