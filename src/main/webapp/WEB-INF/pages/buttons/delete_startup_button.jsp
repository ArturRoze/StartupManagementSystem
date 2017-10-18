<form action="${pageContext.request.contextPath}/startups/${startup.id}/delete"
      class="my-auto" method="post">
    <input type="number" name="current_user_id" value="${current_user_id}" hidden>
    <input type="text" name="is_admin" value="${isAdmin}" hidden>
    <button class="btn btn-lg btn-danger mx-2 btn-with-icon float-right"
            type="submit" value="Delete"
            onclick="return confirm('Are you sure? Confirm delete this startup?')">
        <div><i class="material-icons">delete_sweep</i></div>
        <span>Delete startup</span></button>
</form>