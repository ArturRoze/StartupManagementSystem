<header class="navbar navbar-expand navbar-dark flex-column flex-md-row bd-navbar">
    <div class="container">
        <div class="navbar-nav-scroll">
            <a class="nav-link" href="/">
                <div class="logo">
                    <img src="${pageContext.request.contextPath}/logo.svg">
                </div>
                <div class="brand">Startup Management System</div>
            </a>
        </div>
        <div class="menu">
            <a class="btn btn-outline-success ml-md-3 d-none d-lg-inline-block mb-3 mb-md-0" href="${pageContext.request.contextPath}/login" role="button">Login</a>
            <a class="btn btn-outline-secondary ml-md-3 d-none d-lg-inline-block mb-3 mb-md-0" href="${pageContext.request.contextPath}/registration" role="button">Registration</a>
            <%--<li class="nav-item"><a href="${pageContext.request.contextPath}/startups">Show all startups</a></li>--%>
        </div>
    </div>
</header>