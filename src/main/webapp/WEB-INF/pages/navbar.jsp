<%@include file="login_modal.jsp"%>
<%@include file="register_modal.jsp"%>
<div class="navbar bd-navbar">
    <div class="container">
        <div>
            <a class="nav-link" href="${pageContext.request.contextPath}/">
                <div class="logo">
                    <img src="${pageContext.request.contextPath}/logo.svg">
                </div>
                <div class="brand ">Startup Management System</div>
            </a>
        </div>
        <div class="menu">

            <c:set var="isAdmin" value="false"/>
            <c:set var="isOwner" value="false"/>

            <sec:authorize access="isAuthenticated()">
                <a class="btn btn-lg btn-outline-info d-lg-inline-block mb-md-0 btn-with-icon" role="button"
                   href="${pageContext.request.contextPath}/news">
                    <div><i class="material-icons">home</i></div>
                    <span>NEWS</span>
                </a>

                <div class="btn-group d-lg-inline-block" role="group" aria-label="Basic example">
                    <c:set var="current_user_id">
                        <sec:authentication property="principal.id"/>
                    </c:set>

                    <sec:authorize access="hasRole('ADMIN')">
                        <c:set var="isAdmin" value="true"/>
                    </sec:authorize>

                    <c:choose>
                        <c:when test="${isAdmin}">
                            <a class="btn btn-lg btn-danger ml-md-3 btn-with-icon"
                               role="button"
                               href="${pageContext.request.contextPath}/admins/profile/${current_user_id}">
                                <div><i class="material-icons">settings</i></div>
                                <span>My profile</span>
                            </a>
                        </c:when>
                        <c:otherwise>
                            <a class="btn btn-lg btn-success ml-md-3 d-lg-inline-block mb-md-0 btn-with-icon"
                               role="button"
                               href="${pageContext.request.contextPath}/users/profile/${current_user_id}">
                                <div><i class="material-icons">settings</i></div>
                                <span>My profile</span>
                            </a>
                        </c:otherwise>
                    </c:choose>

                    <a class="btn btn-lg btn-outline-secondary ml-md-1 d-lg-inline-block mb-md-0" role="button"
                       href="${pageContext.request.contextPath}/logout">
                        <i class="material-icons">exit_to_app</i></a>
                </div>
            </sec:authorize>
            <sec:authorize access="isAnonymous()">
                <a class="btn btn-lg btn-outline-success ml-md-3 d-lg-inline-block mb-md-0 btn-with-icon" role="button"
                   <%--href="${pageContext.request.contextPath}/login">--%>
                   href="#" data-toggle="modal" data-target="#loginModal">
                    <div><i class="material-icons">fingerprint</i></div>
                    <span>Login</span>
                </a>
                <a class="btn btn-lg btn-outline-secondary ml-md-3 d-lg-inline-block mb-md-0 btn-with-icon"
                   role="button"
                   <%--href="${pageContext.request.contextPath}/registration">--%>
                   href="#" data-toggle="modal" data-target="#registerModal">
                    <div><i class="material-icons">touch_app</i></div>
                    <span>Join</span>
                </a>
            </sec:authorize>
        </div>
    </div>
</div>