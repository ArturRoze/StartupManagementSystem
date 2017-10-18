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
            <h2 class="text-center">
                Startup update form <span class="badge badge-light text-secondary">id:${startup.id}]</span></h2>

            <c:choose>
                <c:when test="${isOwner || isAdmin}">
                    <div class="row justify-content-center">
                        <div class="col-md-8 border border-gr rounded p-3 mb-4">
                            <div class="btn-block">
                                <form action="${pageContext.request.contextPath}/startups/${startup.id}/update"
                                      method="post">

                                    <table class="table table-hover mb-0">
                                        <tr class="bg-light">
                                            <th width="20%"></th>
                                            <th width="40%">Old startup data</th>
                                            <th width="40%">New startup data</th>
                                        </tr>

                                        <tr>
                                            <th class="bg-light">Startup name</th>
                                            <td>${startup.name}</td>
                                            <td><input class="form-control" type="text" name="name" value="${startup.name}"></td>
                                        </tr>

                                        <tr>
                                            <th class="bg-light">Description</th>
                                            <td>${startup.description}</td>
                                            <td><textarea rows="4" class="form-control" name="description" maxlength="255"
                                                          placeholder="Enter description for your startup..."
                                                          required>${startup.description}</textarea>
                                            </td>
                                        </tr>

                                        <tr>
                                            <th class="bg-light">Budget</th>
                                            <td>${startup.budget}</td>
                                            <td><input class="form-control" type="number" min="1" name="budget" value="${startup.budget}">
                                            </td>
                                        </tr>

                                        <tr>
                                            <th class="bg-light">Industry</th>
                                            <td>${startup.industry.name}</td>
                                            <td>
                                                <select class="custom-select btn-block" name="industry_id">
                                                    <c:forEach var="industry" items="${industries}">
                                                        <c:choose>
                                                            <c:when test="${industry.id == startup.industry.id}">
                                                                <option value="${industry.id}"
                                                                        selected>${industry.name}</option>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <option value="${industry.id}">${industry.name}</option>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </c:forEach>
                                                </select>
                                            </td>
                                        </tr>

                                        <tr>
                                            <th class="bg-light">Country</th>
                                            <td>${startup.country.name}</td>
                                            <td>
                                                <select class="custom-select btn-block" name="country_id">
                                                    <c:forEach var="country" items="${countries}">
                                                        <c:choose>
                                                            <c:when test="${country.id == startup.country.id}">
                                                                <option value="${country.id}"
                                                                        selected>${country.name}</option>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <option value="${country.id}">${country.name}</option>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </c:forEach>
                                                </select>
                                            </td>
                                        </tr>
                                    </table>
                                    <div class="navbar bg-bar mt-3">
                                        <div>
                                            <%@include file="buttons/back_button.jsp" %>
                                        </div>
                                        <div>
                                            <%@include file="buttons/reset_button.jsp" %>
                                            <%@include file="buttons/update_button.jsp" %>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </c:when>
                <c:otherwise>
                    <%@include file="no_permission.jsp" %>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
    <%@include file="footer.jsp" %>
</div>
</body>
</html>