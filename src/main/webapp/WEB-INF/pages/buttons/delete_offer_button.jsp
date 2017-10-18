<script>
    function confirmDelete() {
        if (confirm("Are you sure? Confirm delete this startup?")) {
            return true;
        } else {
            return false;
        }
    }
</script>
<form action="${pageContext.request.contextPath}/offers/${offer.id}/delete"
      class="my-auto" method="post">
    <input type="number" name="current_user_id" value="${current_user_id}" hidden>
    <input type="text" name="is_admin" value="${isAdmin}" hidden>
    <button class="btn btn-danger ml-2 btn-with-icon float-right"
            type="submit" value="Delete" onclick="return confirmDelete();">
        <div><i class="material-icons">delete_sweep</i></div>
        <span>Delete offer</span></button>
</form>