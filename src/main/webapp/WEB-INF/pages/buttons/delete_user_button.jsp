<form class="my-auto" action="${pageContext.request.contextPath}/users/profile/${user.id}/delete" method="post">
    <input type="text" name="isAdmin" value="${isAdmin}" hidden>
    <input type="number" name="current_user_id" value="${current_user_id}" hidden>
    <button class="btn btn-lg btn-danger mx-1 btn-with-icon" type="submit" value="Delete"
            onclick="return confirm('Are you sure? Confirm delete this user account?')">
        <div><i class="material-icons">delete_sweep</i></div>
        <span>Delete this account</span></button>
</form>