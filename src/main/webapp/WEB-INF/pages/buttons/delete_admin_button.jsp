<script>
    function confirmDelete() {
        if (confirm("Are you sure? Confirm delete this admin account?")) {
            return true;
        } else {
            return false;
        }
    }
</script>
<form class="my-auto" action="${pageContext.request.contextPath}/admins/profile/${admin.id}/delete" method="post">
    <input type="number" name="currentAdminId" value="${current_user_id}" hidden>
    <button class="btn btn-lg btn-danger mx-1 btn-with-icon" type="submit" value="Delete" onclick="return confirmDelete();">
        <div><i class="material-icons">delete_sweep</i></div>
        <span>delete this account</span></button>
</form>
<%--
data in DB
login:  admin
pass:   $2a$10$UqZYWlXk6u5OsgtWU1P7nOwZrRz9miMvds4CCeh3OQZDZL2ja3wp6
--%>