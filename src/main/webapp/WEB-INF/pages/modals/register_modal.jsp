<div class="modal fade" id="registerModal" tabindex="-1" role="dialog" aria-labelledby="registerModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="registerModalLabel">Please input your data</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <form method="post" action="${pageContext.request.contextPath}/register">
                <div class="modal-body">
                    <label for="reg-login">Choose your username:</label>
                    <div class="input-group input-group-lg">
                        <span class="input-group-addon" id="reg-addon1"><i class="material-icons">fingerprint</i></span>
                        <input type="text" required class="form-control" name="login" id="reg-login"
                               placeholder="username" aria-label="Username" aria-describedby="reg-addon1">
                    </div>
                    <label for="reg-password">Choose your password:</label>
                    <div class="input-group input-group-lg">vb
                        <span class="input-group-addon" id="reg-addon2"><i class="material-icons">star_border</i></span>
                        <input type="password" required class="form-control" name="password" id="reg-password"
                               placeholder="password" aria-label="Username" aria-describedby="reg-addon2">
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-outline-secondary" data-dismiss="modal">Cancel</button>
                    <button type="submit" class="btn btn-primary">Register</button>
                </div>
            </form>
        </div>
    </div>
</div>