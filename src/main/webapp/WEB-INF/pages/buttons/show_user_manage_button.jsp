<div class="navbar">
    <a class="btn btn-sm btn-success btn-with-icon" role="button"
   href="${pageContext.request.contextPath}/users/profile/${user.id}">
    <div><i class="material-icons">search</i></div></a>
    <form action="${pageContext.request.contextPath}/users/profile/${user.id}/delete" class="m-0" method="post">
        <input type="text" name="isAdmin" value="${isAdmin}" hidden>
        <input type="number" name="current_user_id" value="${current_user_id}" hidden>
        <button class="btn btn-sm btn-danger btn-with-icon" type="submit" value="Delete">
            <div><i class="material-icons">delete_sweep</i></div></button>
    </form>
    <a class="btn btn-sm btn-info btn-with-icon" role="button"
       href="${pageContext.request.contextPath}/users/profile/${user.id}/edit">
        <div><i class="material-icons">create</i></div></a>
</div>