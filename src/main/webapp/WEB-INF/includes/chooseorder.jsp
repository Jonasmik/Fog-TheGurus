<%@page contentType="text/html" pageEncoding="UTF-8" %>
<div class="container" style="padding: 1rem">
    <form method="post">
        <div class="row">
            <div class="col">
                <!-- Nav tabs -->
                <ul class="nav nav-tabs" id="myTab" role="tablist">
                    <li class="nav-item" role="presentation">
                        <a class="nav-link active" id="home-tab" data-toggle="tab" href="#home" role="tab" aria-controls="home" aria-selected="true">Materiale</a>
                    </li>
                    <li class="nav-item" role="presentation">
                        <a class="nav-link" id="profile-tab" data-toggle="tab" href="#profile" role="tab" aria-controls="profile" aria-selected="false">Størelse</a>
                    </li>
                </ul>

                <!-- Tab panes -->
                <div class="tab-content" style="height: 60px; overflow-y: scroll;">
                    <div class="tab-pane active" id="home" role="tabpanel" aria-labelledby="home-tab">
                        <div class="form-group" style="padding-top: 10px">
                            <label for="username">Username</label>
                            <input type="text" class="form-control w-50" id="username" aria-describedby="username"
                                   placeholder="Enter username" name="username" required>
                            <small id="usernameNameHelp" class="form-text text-muted">We'll never share your username with
                                anyone.</small>
                        </div>
                    </div>
                    <div class="tab-pane" id="profile" role="tabpanel" aria-labelledby="profile-tab">
                        <div class="form-group" style="padding-top: 10px">
                            <label for="password">Password</label>
                            <input type="password" class="form-control w-50" id="password" aria-describedby="password"
                                   placeholder="Enter password" name="password" required>
                            <small id="passwordNameHelp" class="form-text text-muted">We'll never share your password with
                                anyone.</small>
                        </div>
                    </div>
                </div>
                <hr>
                <button type="submit" class="btn btn-primary w-25">Læg i kurv</button>
            </div>
        </div>
    </form>
</div>
