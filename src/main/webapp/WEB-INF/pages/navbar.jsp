<header class="navbar bd-navbar">
    <div class="container">
        <div>
            <a class="nav-link" href="/">
                <div class="logo">
                    <img src="${pageContext.request.contextPath}/logo.svg">
                </div>
                <div class="brand">Startup Management System</div>
            </a>
        </div>
        <div class="menu">
            <a class="btn btn-outline-success ml-md-3 d-lg-inline-block mb-md-0" href="${pageContext.request.contextPath}/login" role="button"><i class="material-icons f18">fingerprint</i><span> Login</span></a>
            <a class="btn btn-outline-secondary ml-md-3 d-lg-inline-block mb-md-0" href="${pageContext.request.contextPath}/registration" role="button"><i class="material-icons fix">touch_app</i><span> Registration</span></a>
            <%--<li class="nav-item"><a href="${pageContext.request.contextPath}/startups">Show all startups</a></li>--%>
        </div>
    </div>
</header>