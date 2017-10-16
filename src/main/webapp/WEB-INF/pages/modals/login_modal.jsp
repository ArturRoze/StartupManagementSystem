<div class="modal fade" id="loginModal" tabindex="-1" role="dialog" aria-labelledby="loginModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="loginModalLabel">Please enter your login data below:</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <form action="${pageContext.request.contextPath}/login" method="post">
                <div class="modal-body">
                    <label for="login-username">Enter your username:</label>
                    <div class="input-group input-group-lg">
                        <span class="input-group-addon" id="login-addon1"><i class="material-icons">fingerprint</i></span>
                        <input type="text" class="form-control" name="username" id="login-username"
                               placeholder="username" aria-label="Username" aria-describedby="login-addon1"
                               autofocus required>
                    </div>
                    <label for="login-password">Enter your password:</label>
                    <div class="input-group input-group-lg">
                        <span class="input-group-addon" id="login-addon2"><i class="material-icons">star_border</i></span>
                        <input type="password" class="form-control" name="password" id="login-password"
                               placeholder="password" aria-label="Username" aria-describedby="login-addon2"
                               required>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-outline-secondary" data-dismiss="modal">Cancel</button>
                    <button type="submit" class="btn btn-primary">Login</button>
                </div>
            </form>
        </div>
    </div>
</div>