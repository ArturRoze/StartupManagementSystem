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
            <h2 class="text-center mt-4">Please enter data for a new offer</h2>
            <div class="row justify-content-center">
                <div class="col-md-8">
                    <form action="${pageContext.request.contextPath}/offers/new/offer/" method="post">
                        <input type="number" name="user_id" value="${current_user_id}" hidden>
                        <div class="border border-gr rounded mb-4 p-3 btn-block">
                            <table class="table table-hover mb-0">
                                <tr>
                                    <th class="w-25 bg-light" scope="row">Description</th>
                                    <td>
                                <textarea rows="4" class="form-control" name="description" maxlength="255"
                                          placeholder="Enter description for your startup..."
                                          required></textarea>
                                    </td>
                                </tr>

                                <tr>
                                    <th class="bg-light" scope="row">Budget</th>
                                    <td>
                                        <input type="number" min="1" class="form-control" name="budget" value="1"
                                               required>
                                    </td>
                                </tr>

                                <tr>
                                    <th class="bg-light" scope="row">Industry</th>
                                    <td><select name="industry_id">
                                        <c:forEach var="industry" items="${industries}">
                                            <option value="${industry.id}">${industry.name}</option>
                                        </c:forEach>
                                    </select></td>
                                </tr>

                                <tr>
                                    <th class="bg-light" scope="row">Country</th>
                                    <td><select name="country_id">
                                        <c:forEach var="country" items="${countries}">
                                            <option value="${country.id}">${country.name}</option>
                                        </c:forEach>
                                    </select></td>
                                </tr>
                            </table>
                        </div>
                        <div class="justify-content-md-center">
                            <div class="navbar bg-bar px-3 mt-3 mb-5">
                                <div>
                                    <%@include file="buttons/back_button.jsp" %>
                                </div>
                                <div>
                                    <%@include file="buttons/reset_button_lg.jsp" %>
                                    <%@include file="buttons/create_button_lg.jsp" %>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <%@include file="footer.jsp" %>
</div>
</body>
</html>
