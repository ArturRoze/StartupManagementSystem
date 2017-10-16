<form class="my-auto" action="${pageContext.request.contextPath}/users/profile/${user.id}/delete" method="get">
    <input type="text" name="isAdmin" value="${isAdmin}" hidden>
    <button class="btn btn-lg btn-danger mx-1 btn-with-icon-lg" type="submit" value="Delete">
        <div><i class="material-icons">delete_sweep</i></div>
        <span>delete this account</span></button>
</form>
<%--
admin
$2a$10$UqZYWlXk6u5OsgtWU1P7nOwZrRz9miMvds4CCeh3OQZDZL2ja3wp6
--%>