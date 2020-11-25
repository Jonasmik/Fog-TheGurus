<div class="container">
    <div class="row">
        <div class="col-md-12">

            <div class="modal fade" id="loginModal">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title">User login</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body text-center">
                            <nav>
                                <div class="nav nav-tabs" id="nav-tab" role="tablist">
                                    <a class="nav-link active" id="nav-home-tab" data-toggle="tab" href="#nav-home"
                                       role="tab" aria-controls="nav-home" aria-selected="true">Log ind</a>
                                    <a class="nav-link" id="nav-profile-tab" data-toggle="tab" href="#nav-profile"
                                       role="tab" aria-controls="nav-profile" aria-selected="false">Opret bruger</a>
                                </div>
                            </nav>
                            <div class="tab-content" id="nav-tabContent">
                                <div class="tab-pane fade show active" id="nav-home" role="tabpanel"
                                     aria-labelledby="nav-home-tab">
                                    <br>
                                    <form class="form-signin" action="Main" method="POST">
                                        <input type="hidden" name="target" value="authorizeuser">
                                        <label for="loginEmail" class="sr-only">E-mail adresse</label>
                                        <input type="email" id="loginEmail" class="form-control" name="email"
                                               placeholder="E-mail adresse" required=""
                                               autofocus="">
                                        <small style="margin-bottom: 5px" id="loginemailHelp"
                                               class="form-text text-muted">Vi
                                            vil aldrig dele din e-mail med andre</small>
                                        <label for="loginPassword" class="sr-only">Password</label>
                                        <input style="margin-bottom: 25px" type="password" id="loginPassword"
                                               class="form-control" name="password"
                                               placeholder="Password" required="">
                                        <hr class="w-100">
                                        <div class="d-flex justify-content-center">
                                            <button type="submit" class="btn btn-block w-50 btn-primary">
                                                Login
                                            </button>
                                        </div>
                                    </form>
                                </div>
                                <div class="tab-pane fade" id="nav-profile" role="tabpanel"
                                     aria-labelledby="nav-profile-tab">
                                    <br>
                                    <form class="form-signin" action="Main" method="POST">
                                        <input type="hidden" name="target" value="createuser">
                                        <label for="signupName" class="sr-only">Navn</label>
                                        <input style="margin-bottom: 15px" type="text" id="signupName"
                                               class="form-control" name="name"
                                               placeholder="Indtast navn" required=""
                                               autofocus="">
                                        <label for="signupEmail" class="sr-only">Email adresse</label>
                                        <input type="email" id="signupEmail" class="form-control" name="email"
                                               placeholder="Indtast e-mail adresse" required=""
                                               autofocus="">
                                        <small style="margin-bottom: 5px" id="signupemailHelp"
                                               class="form-text text-muted">Vi
                                            vil aldrig dele din e-mail med andre</small>
                                        <label for="signupPassword" class="sr-only">Password</label>
                                        <input style="margin-bottom: 15px" type="password" id="signupPassword"
                                               class="form-control" name="password1"
                                               placeholder="Indtast password" required="">
                                        <label for="signupPassword2" class="sr-only">Password2</label>
                                        <input style="margin-bottom: 25px" type="password" id="signupPassword2"
                                               class="form-control" name="password2"
                                               placeholder="Gentag password" required="">
                                        <hr class="w-100">
                                        <div class="d-flex justify-content-center">
                                            <button type="submit" class="btn btn-block w-50 btn-primary">
                                                Opret bruger
                                            </button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        </div>
    </div>
</div>