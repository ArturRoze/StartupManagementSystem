<header class="navbar bd-navbar">
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
                <c:set var="current_user_id">
                    <sec:authentication property="principal.id"/>
                </c:set>

                <sec:authorize access="hasRole('ADMIN')">
                    <c:set var="isAdmin" value="true"/>
                </sec:authorize>

                <a class="btn btn-outline-info d-lg-inline-block mb-md-0"
                   href="${pageContext.request.contextPath}/" role="button">
                    <i class="material-icons f18">home</i> Main page</a>

                <c:choose>
                    <c:when test="${isAdmin}">
                        <a class="btn btn-danger ml-md-3 d-lg-inline-block mb-md-0"
                           href="${pageContext.request.contextPath}/admins/profile/${current_user_id}" role="button">
                            <i class="material-icons f18">settings</i> My profile</a>
                    </c:when>
                    <c:otherwise>
                        <a class="btn btn-success ml-md-3 d-lg-inline-block mb-md-0"
                           href="${pageContext.request.contextPath}/users/profile/${current_user_id}" role="button">
                            <i class="material-icons f18">settings</i> My profile</a>
                    </c:otherwise>
                </c:choose>

                <a class="btn btn-outline-secondary ml-md-1 d-lg-inline-block mb-md-0"
                   href="${pageContext.request.contextPath}/logout" role="button">
                    <i class="material-icons f18">exit_to_app</i></a>
            </sec:authorize>
            <sec:authorize access="isAnonymous()">
                <a class="btn btn-outline-success ml-md-3 d-lg-inline-block mb-md-0"
                   href="${pageContext.request.contextPath}/login" role="button"><i
                        class="material-icons f18">fingerprint</i><span> Login</span></a>
                <a class="btn btn-outline-secondary ml-md-3 d-lg-inline-block mb-md-0"
                   href="${pageContext.request.contextPath}/registration" role="button">
                    <i class="material-icons f18">touch_app</i><span> Sign in</span></a>
            </sec:authorize>
        </div>
    </div>
</header>