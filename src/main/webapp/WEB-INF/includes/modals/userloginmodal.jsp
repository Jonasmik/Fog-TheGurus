<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="container">
    <div class="row">
        <div class="col-md-12">
            <div class="modal fade" id="loginModal">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title">User log ind</h5>
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
                                        <c:if test="${requestScope.loginfail != null}">
                                            <div class="alert alert-danger border-danger">
                                                <p>${requestScope.loginfail}</p>
                                            </div>
                                        </c:if>
                                        <input type="hidden" name="target" value="authorizeuser">
                                        <label for="loginEmail" class="sr-only">E-mail adresse</label>
                                        <input type="email" id="loginEmail" class="form-control" name="email"
                                               placeholder="Indtast e-mail adresse" required=""
                                               autofocus="">
                                        <label for="loginPassword" class="sr-only">Password</label>
                                        <input style="margin-bottom: 10px" type="password" id="loginPassword"
                                               class="form-control" name="password"
                                               placeholder="Indtast password" required="">
                                        <hr class="w-100">
                                        <div class="d-flex justify-content-center">
                                            <button type="submit" class="btn btn-block w-50 btn-primary">
                                                Log ind
                                            </button>
                                        </div>
                                    </form>
                                </div>
                                <div class="tab-pane fade" id="nav-profile" role="tabpanel"
                                     aria-labelledby="nav-profile-tab">
                                    <br>
                                    <form class="form-signin" action="Main" method="POST">
                                        <input type="hidden" name="target" value="createuser">
                                        <label for="signupEmail" class="sr-only">Email adresse</label>
                                        <input type="email" id="signupEmail" class="form-control" name="email"
                                               placeholder="Indtast e-mail adresse" required=""
                                               autofocus="">

                                        <label for="signupPassword" class="sr-only">Password</label>
                                        <input style="margin-bottom: 15px" type="password" id="signupPassword"
                                               class="form-control" name="password1"
                                               placeholder="Indtast password" required="">
                                        <label for="signupPassword2" class="sr-only">Password2</label>
                                        <input style="margin-bottom: 25px" type="password" id="signupPassword2"
                                               class="form-control" name="password2"
                                               placeholder="Gentag password" required="">

                                        <div class="row">
                                            <div class="col">
                                                <label for="signupName" class="sr-only">Navn</label>
                                                <input type="text" id="signupName"
                                                       class="form-control" name="username"
                                                       placeholder="Indtast navn" required=""
                                                       autofocus="">
                                            </div>
                                            <div class="col">
                                                <label for="signupAddress" class="sr-only">Adresse</label>
                                                <input type="text" id="signupAddress" class="form-control"
                                                       name="address"
                                                       placeholder="Indtast adresse" required=""
                                                       autofocus="">
                                            </div>
                                        </div>

                                        <div class="row" style="padding-top: 15px">
                                            <div class="col-md-9">
                                                <label for="signupCity" class="sr-only">By</label>
                                                <input type="text" id="signupCity" class="form-control" name="city"
                                                       placeholder="Indtast by" required=""
                                                       autofocus="">
                                            </div>
                                            <div class="col-md-3">
                                                <label for="signupZip" class="sr-only">Postnummer</label>
                                                <input type="text" id="signupZip" class="form-control" name="zip"
                                                       placeholder="Post nr." required=""
                                                       autofocus="">
                                            </div>
                                        </div>

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