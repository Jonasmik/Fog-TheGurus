<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>

<div class="container" style="padding: 1rem">
    <div class="row">
        <div class="col">
            <c:if test="${requestScope.preorderfail != null}">
                <div class="alert alert-danger border-danger">
                    <h1>Fejl</h1>
                    <p>${requestScope.preorderfail}</p>
                </div>
            </c:if>
            <c:if test="${requestScope.preordersucces != null}">
                <div class="alert alert-success border-success">
                    <h1>Forespørgsel</h1>
                    <p>${requestScope.preordersucces}</p>
                </div>
            </c:if>
            <!-- Nav tabs -->
            <ul class="nav nav-tabs" id="myTab" role="tablist">
                <li class="nav-item" role="presentation">
                    <a class="nav-link active" id="home-tab" data-toggle="tab" href="#home" role="tab"
                       aria-controls="home" aria-selected="true">Carport med skråt tag</a>
                </li>
                <li class="nav-item" role="presentation">
                    <a class="nav-link" id="profile-tab" data-toggle="tab" href="#profile" role="tab"
                       aria-controls="profile" aria-selected="false">Carport med flat tag</a>
                </li>
            </ul>

            <!-- Tab panes -->
            <div class="tab-content">
                <div class="tab-pane active" id="home" role="tabpanel" aria-labelledby="home-tab">
                    <!-- Carport med skråt tag START -->
                    <form action="Main" method="post">
                        <input type="hidden" name="target" value="angledroofpreorder">
                        <div class="form-group" style="padding-top: 10px">
                            <h4 style="padding-top: 10px; padding-bottom: 10px">Carport størelse</h4>
                            <label for="carportWidth">Carport bredde</label>
                            <select class="form-control" id="carportWidth" name="width">
                                <option>Vælg bredde</option>
                                <option>2</option>
                                <option>3</option>
                                <option>4</option>
                                <option>5</option>
                            </select>
                        </div>

                        <div class="form-group">
                            <label for="carportLength">Carport Længde</label>
                            <select class="form-control" id="carportLength" name="lenght">
                                <option>Vælg Længde</option>
                                <option>2</option>
                                <option>3</option>
                                <option>4</option>
                                <option>5</option>
                            </select>
                        </div>

                        <div class="form-group">
                            <label for="carportTag">Carport Tag</label>
                            <select class="form-control" id="carportTag" name="roof">
                                <option selected>Vælg tagtype/farve</option>
                                <option>Betontagsten - Sort</option>
                                <option>Eternittag B6 - Grå</option>
                                <option>Eternittag B7 - Rødbrun</option>
                            </select>
                        </div>

                        <div class="form-group">
                            <label for="tagHældning">Taghældning</label>
                            <select class="form-control" id="tagHældning" name="roofangle">
                                <option selected>Vælg taghældning</option>
                                <option>15 grader</option>
                                <option>20 grader</option>
                                <option>25 grader</option>
                            </select>
                        </div>

                        <div class="form-check">
                            <label>
                                <input class="form-check-input" style="padding-top: 20px; padding-bottom: 20px;"
                                       type="checkbox" onchange="showObliqueRoofOptions()"
                                       name="shed" value="yes"> Ønsker redskabsrum
                            </label>
                        </div>

                        <div id="myFirstExtraRoom" style="display: none">

                            <div class="form-group">
                                <h4 style="padding-top: 10px">Beregn 15 cm tagudhæng på hver side af
                                    redskabsrummet.</h4>
                                <label for="redskabsRumBredde">Redskabsrum bredde</label>
                                <select class="form-control" id="redskabsRumBredde" name="shedwidth">
                                    <option selected>Vælg bredde</option>
                                    <option>210 cm</option>
                                    <option>240 cm</option>
                                    <option>270 cm</option>
                                </select>
                            </div>
                            <div class="form-group">
                                <label for="redskabsRumLængde">Redskabsum længde</label>
                                <select class="form-control" id="redskabsRumLængde" name="shedlength">
                                    <option selected>Vælg længde</option>
                                    <option>210 cm</option>
                                    <option>240 cm</option>
                                    <option>270 cm</option>
                                </select>
                            </div>
                        </div>

                        <div class="form-group">
                            <h4 style="padding-top: 10px">Kontakt information</h4>
                        </div>

                        <div class="form-row">
                            <div class="col">
                                <label for="skråNavn">Navn</label>
                                <input type="text" class="form-control" id="skråNavn"
                                       aria-describedby="nameHelp" name="angledname">
                            </div>
                            <div class="col">
                                <label for="skråAdresse">Adresse</label>
                                <input type="text" class="form-control" id="skråAdresse"
                                       aria-describedby="emailHelp" name="angledadress">
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="skråZip">Post nummer</label>
                            <input type="text" class="form-control" id="skråZip"
                                   aria-describedby="emailHelp" name="angledzip">
                        </div>

                        <div class="form-group">
                            <label for="skråBy">By</label>
                            <input type="text" class="form-control" id="skråBy"
                                   aria-describedby="emailHelp" name="angledcity">
                        </div>

                        <div class="form-group">
                            <label for="skråEmail">E-mail</label>
                            <input type="email" class="form-control" id="skråEmail"
                                   aria-describedby="emailHelp" name="angledemail">
                        </div>

                        <div class="form-group">
                            <label for="skråAdditionals">Evt. bemærkninger</label>
                            <textarea maxlength="255" class="form-control" id="skråAdditionals"
                                      name="angledadditional"
                                      rows="3"></textarea>
                        </div>

                        <hr>

                        <!-- If user is logged in - SUBMIT. Else Login modal -->
                        <c:if test="${sessionScope.user != null}">
                            <div class="d-none d-lg-block d-xl-block">
                                <button type="submit" class="btn btn-primary w-25">Send forespørgsel</button>
                            </div>
                            <div class="d-block d-lg-none d-xl-none">
                                <button type="submit" class="btn btn-primary w-50">Send forespørgsel</button>
                            </div>
                        </c:if>
                        <c:if test="${sessionScope.user == null}">
                            <div class="btn-group dropup allow-focus">
                                <button type="button" class="btn btn-primary" data-toggle="dropdown"
                                        aria-haspopup="true" aria-expanded="false">
                                    Send forespørgsel
                                </button>
                                <div class="userContainer dropdown-menu text-muted"
                                     style="width: 300px; padding: 30px">

                                    <div id="myAngledLogin" style="display: block">
                                        <h5>Log ind</h5>

                                        <hr style="padding-top: 10px; padding-bottom: 5px;">

                                        <input type="hidden" name="hasuser" value="yes" id="angledHasUser">
                                        <input type="email" class="form-control" name="email" id="angledHasUserEmail"
                                               placeholder="E-mail adresse" required=""
                                               autofocus="">
                                        <small style="margin-bottom: 5px"
                                               class="form-text text-muted">Vi
                                            vil aldrig dele din e-mail med andre</small>
                                        <input style="margin-bottom: 25px" type="password"
                                               class="form-control" name="password" id="angledHasUserPassword"
                                               placeholder="Password" required="">
                                        <a style="color: blue" onclick="showObliqueCreate()">Eller opret en bruger
                                            her</a>

                                        <hr style="padding-top: 5px; padding-bottom: 5px;">
                                        <div class="d-flex justify-content-end">
                                            <button type="submit" class="btn btn-primary">
                                                Log ind og send forespørgsel
                                            </button>
                                        </div>

                                    </div>

                                    <div id="myAngledCreate" style="display: none">
                                        <h5>Opret bruger</h5>

                                        <hr style="padding-top: 10px; padding-bottom: 5px;">

                                        <input type="hidden" name="nouser" value="yes" id="angledNoUser" disabled>
                                        <label for="angledNoUserName" class="sr-only">Navn</label>
                                        <input style="margin-bottom: 15px" type="text" id="angledNoUserName"
                                               class="form-control" name="username"
                                               placeholder="Indtast navn" required=""
                                               autofocus="" disabled>
                                        <label for="angledNoUserEmail" class="sr-only">Email adresse</label>
                                        <input type="email" id="angledNoUserEmail" class="form-control" name="email"
                                               placeholder="Indtast e-mail adresse" required=""
                                               autofocus="" disabled>
                                        <small style="margin-bottom: 5px" id="signupemailHelp"
                                               class="form-text text-muted">Vi
                                            vil aldrig dele din e-mail med andre</small>
                                        <label for="angledNoUserPassword" class="sr-only">Password</label>
                                        <input style="margin-bottom: 15px" type="password" id="angledNoUserPassword"
                                               class="form-control" name="password1"
                                               placeholder="Indtast password" required="" disabled>
                                        <label for="angledNoUserPassword2" class="sr-only">Password2</label>
                                        <input style="margin-bottom: 25px" type="password" id="angledNoUserPassword2"
                                               class="form-control" name="password2"
                                               placeholder="Gentag password" required="" disabled>

                                        <a style="color: blue" onclick="showObliqueLogin()">Tilbage til
                                            login</a>
                                        <hr style="padding-top: 5px; padding-bottom: 5px;">

                                        <div class="d-flex justify-content-end">
                                            <button type="submit" class="btn btn-primary">
                                                Opret bruger og send forespørgsel
                                            </button>
                                        </div>

                                    </div>
                                </div>
                            </div>
                        </c:if>
                    </form>
                </div>
                <!-- Carport med skråt tag END -->

                <!-- Carport med flat tag START -->
                <div class="tab-pane" id="profile" role="tabpanel" aria-labelledby="profile-tab">
                    <form action="Main" method="post">
                        <input type="hidden" name="target" value="flatroofpreorder">
                        <div class="form-group" style="padding-top: 10px">
                            <h4 style="padding-top: 10px; padding-bottom: 10px">Carport størelse</h4>
                            <label for="flatCarportWidth">Carport bredde</label>
                            <select class="form-control" id="flatCarportWidth" name="width">
                                <option>Vælg bredde</option>
                                <option>2</option>
                                <option>3</option>
                                <option>4</option>
                                <option>5</option>
                            </select>
                        </div>

                        <div class="form-group">
                            <label for="flatCarportLength">Carport Længde</label>
                            <select class="form-control" id="flatCarportLength" name="length">
                                <option>Vælg Længde</option>
                                <option>2</option>
                                <option>3</option>
                                <option>4</option>
                                <option>5</option>
                            </select>
                        </div>

                        <div class="form-group">
                            <label for="flatcarportTag">Carport Tag</label>
                            <select class="form-control" id="flatcarportTag" name="roof">
                                <option selected>Vælg tagtype/farve</option>
                                <option>Plasttrapezplader</option>
                            </select>
                        </div>

                        <div class="form-check">
                            <label>
                                <input class="form-check-input" style="padding-top: 20px; padding-bottom: 20px;"
                                       type="checkbox" onchange="showFlatRoofOptions()"
                                       name="shed" value="yes"> Ønsker redskabsrum
                            </label>
                        </div>

                        <div id="mySecondExtraRoom" style="display: none">

                            <div class="form-group">
                                <h4 style="padding-top: 10px">Beregn 15 cm tagudhæng på hver side af
                                    redskabsrummet.</h4>
                                <label for="flatRedskabsRumBredde">Redskabsrum bredde</label>
                                <select class="form-control" id="flatRedskabsRumBredde" name="shedwidth" disabled>
                                    <option selected>Vælg bredde</option>
                                    <option>210</option>
                                    <option>240</option>
                                    <option>270</option>
                                </select>
                            </div>
                            <div class="form-group">
                                <label for="flatRedskabsRumLængde">Redskabsum længde</label>
                                <select class="form-control" id="flatRedskabsRumLængde" name="shedlength">
                                    <option selected>Vælg længde</option>
                                    <option>210</option>
                                    <option>240</option>
                                    <option>270</option>
                                </select>
                            </div>
                        </div>

                        <div class="form-group">
                            <h4 style="padding-top: 10px">Kontakt information</h4>
                        </div>

                        <div class="form-row">
                            <div class="col">
                                <label for="flatNavn">Navn</label>
                                <input type="text" class="form-control" id="flatNavn"
                                       aria-describedby="nameHelp" name="flatname">
                            </div>
                            <div class="col">
                                <label for="flatAdresse">Adresse</label>
                                <input type="text" class="form-control" id="flatAdresse"
                                       aria-describedby="emailHelp" name="flatadress">
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="flatZip">Post nummer</label>
                            <input type="text" class="form-control" id="flatZip"
                                   aria-describedby="emailHelp" name="flatzip">
                        </div>

                        <div class="form-group">
                            <label for="flatBy">By</label>
                            <input type="text" class="form-control" id="flatBy"
                                   aria-describedby="emailHelp" name="flatcity">
                        </div>

                        <div class="form-group">
                            <label for="flatEmail">E-mail</label>
                            <input type="email" class="form-control" id="flatEmail"
                                   aria-describedby="emailHelp" name="flatemail">
                        </div>

                        <div class="form-group">
                            <label for="flatBemærkning">Evt. bemærkninger</label>
                            <textarea maxlength="255" class="form-control" id="flatBemærkning"
                                      name="flatadditionals"
                                      rows="3"></textarea>
                        </div>

                        <hr>

                        <!-- If user is logged in - SUBMIT. Else Login dropdown -->
                        <c:if test="${sessionScope.user != null}">
                            <div class="d-none d-lg-block d-xl-block">
                                <button type="submit" class="btn btn-primary w-25">Send forespørgsel</button>
                            </div>
                            <div class="d-block d-lg-none d-xl-none">
                                <button type="submit" class="btn btn-primary w-50">Send forespørgsel</button>
                            </div>
                        </c:if>
                        <c:if test="${sessionScope.user == null}">
                            <div class="btn-group dropup allow-focus">
                                <button type="button" class="btn btn-primary" data-toggle="dropdown"
                                        aria-haspopup="true" aria-expanded="false">
                                    Send forespørgsel
                                </button>
                                <div class="userContainer dropdown-menu text-muted"
                                     style="width: 300px; padding: 30px">

                                    <div id="myFlatLogin" style="display: block">
                                        <h5>Log ind</h5>

                                        <hr style="padding-top: 10px; padding-bottom: 5px;">

                                        <input type="hidden" name="hasuser" value="yes" id="flatHasUser">
                                        <input type="email" class="form-control" name="email" id="flatHasUserEmail"
                                               placeholder="E-mail adresse" required=""
                                               autofocus="">
                                        <small style="margin-bottom: 5px"
                                               class="form-text text-muted">Vi
                                            vil aldrig dele din e-mail med andre</small>
                                        <input style="margin-bottom: 25px" type="password"
                                               class="form-control" name="password" id="flatHasUserPassword"
                                               placeholder="Password" required="">
                                        <a style="color: blue" onclick="showFlatCreate()">Eller opret en bruger her</a>

                                        <hr style="padding-top: 5px; padding-bottom: 5px;">
                                        <div class="d-flex justify-content-end">
                                            <button type="submit" class="btn btn-primary">
                                                Log ind og send forespørgsel
                                            </button>
                                        </div>

                                    </div>

                                    <div id="myFlatCreate" style="display: none">
                                        <h5>Opret bruger</h5>

                                        <hr style="padding-top: 10px; padding-bottom: 5px;">

                                        <input type="hidden" name="nouser" value="yes" id="flatNoUser" disabled>
                                        <label for="angledNoUserName" class="sr-only">Navn</label>
                                        <input style="margin-bottom: 15px" type="text" id="flatNoUserName"
                                               class="form-control" name="username"
                                               placeholder="Indtast navn" required=""
                                               autofocus="" disabled>
                                        <label for="angledNoUserEmail" class="sr-only">Email adresse</label>
                                        <input type="email" id="flatNoUserEmail" class="form-control" name="email"
                                               placeholder="Indtast e-mail adresse" required=""
                                               autofocus="" disabled>
                                        <small style="margin-bottom: 5px" id="signupemailHelpFlat"
                                               class="form-text text-muted">Vi
                                            vil aldrig dele din e-mail med andre</small>
                                        <label for="angledNoUserPassword" class="sr-only">Password</label>
                                        <input style="margin-bottom: 15px" type="password" id="flatNoUserPassword"
                                               class="form-control" name="password1"
                                               placeholder="Indtast password" required="" disabled>
                                        <label for="angledNoUserPassword2" class="sr-only">Password2</label>
                                        <input style="margin-bottom: 25px" type="password" id="flatNoUserPassword2"
                                               class="form-control" name="password2"
                                               placeholder="Gentag password" required="" disabled>

                                        <a style="color: blue" onclick="showFlatLogin()">Tilbage til
                                            login</a>
                                        <hr style="padding-top: 5px; padding-bottom: 5px;">

                                        <div class="d-flex justify-content-end">
                                            <button type="submit" class="btn btn-primary">
                                                Opret bruger og send forespørgsel
                                            </button>
                                        </div>

                                    </div>
                                </div>
                            </div>
                        </c:if>
                    </form>
                </div>
                <!-- Carport med flat tag END -->
            </div>
        </div>
    </div>
</div>
