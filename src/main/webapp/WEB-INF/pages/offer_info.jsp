<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Offer: ${offer.id}</title>
    <%@include file="header_config.jsp" %>
</head>
<body>
<div class="wrapper">
    <div class="content">
        <%@include file="navbar.jsp" %>
        <div class="container">
            <c:set var="isOwner" value="${offer.user.id == current_user_id}"/>
            <h2 class="text-center">Offer info</h2>
            <div class="row">
                <div class="col-md-6 p-2">
                    <div class="btn-block">
                        <table class="table table-hover">

                        </table>
                    </div>
                </div>
            </div>

                <tr>
                    <th>Id</th>
                    <td>${offer.id}</td>
                </tr>
                <tr>
                    <th>Description</th>
                    <td>${offer.description}</td>
                </tr>
                <tr>
                    <th>User</th>
                    <td>
                        <div align="center">
                            <form action="${pageContext.request.contextPath}/users/profile/${offer.user.id}"
                                  method="get">
                                <input type="submit" value="To user ${offer.user.id} profile page">
                            </form>
                        </div>
                    </td>
                </tr>
                <tr>
                    <th>Registration</th>
                    <td>${offer.registrationDate}</td>
                </tr>
                <tr>
                    <th>Industry</th>
                    <td>${offer.industry.name}</td>
                </tr>
                <tr>
                    <th>Country</th>
                    <td>${offer.country.name}</td>
                </tr>

            <c:if test="${isOwner || isAdmin}">
                <div align="center">
                    <form action="${pageContext.request.contextPath}/offers/${offer.id}/edit" method="get">
                        <input type="submit" value="Update">
                    </form>
                </div>

                <div align="center">
                    <form action="${pageContext.request.contextPath}/offers/${offer.id}/delete" method="get">
                        <input type="submit" value="Delete">
                    </form>
                </div>
            </c:if>
            <%@include file="buttons/back_button.jsp" %>
        </div>
    </div>
    <%@include file="footer.jsp" %>
</div>
</body>
</html>
