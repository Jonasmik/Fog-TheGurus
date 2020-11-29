<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="container">
    <div class="row">
        <div class="col-md-12">
            <div class="modal fade" id="preorderLogin">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title">Log ind</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body text-center">
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
                            <div class="modal-footer">
                                <div class="d-flex justify-content-center">
                                    <button type="submit" class="btn btn-primary">
                                        Login
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!--
<input type="hidden" name="target" value="createuser">
<label for="signupName" class="sr-only">Navn</label>
<input style="margin-bottom: 15px" type="text" id="signupName"
       class="form-control" name="username"
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
-->