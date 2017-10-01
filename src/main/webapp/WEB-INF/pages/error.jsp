<%--
  Created by IntelliJ IDEA.
  User: root
  Date: 19.09.2017
  Time: 13:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error page</title>
</head>
<body>
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
