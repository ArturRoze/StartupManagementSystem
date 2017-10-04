<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error page</title>
</head>
<body>
<%@include file="navbar.jsp" %>
<h1 align="center">OOPS SOMETHING WENT WRONG</h1>
<div align="center">
    <button onclick="goBack()">Go Back</button>
    <script>
        function goBack() {
            window.history.back();
        }
    </script>
</div>

</body>
</html>
