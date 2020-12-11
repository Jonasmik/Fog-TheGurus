<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>

<div class="container" style="padding: 1rem">
    <div class="row">
        <div class="col">

            <!-- Popups on request -->
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
            <c:if test="${sessionScope.carportpreview != null}">
                <div class="alert alert-info border-secondary">
                    <h1>Carport forsmag</h1>
                        ${sessionScope.carportpreview}
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
                    <form action="Main" class="form-preorder" method="post">
                        <input type="hidden" name="target" value="preorder">
                        <input type="hidden" name="angledroof" value="yes">
                        <div class="form-group" style="padding-top: 10px">
                            <h4 style="padding-top: 10px; padding-bottom: 10px">Carport størelse</h4>
                            <label for="carportWidth">Carport bredde</label>
                            <select class="form-control" id="carportWidth" name="width">
                                <c:if test="${sessionScope.carportwidth == null}">
                                    <option>Vælg bredde</option>
                                </c:if>
                                <c:forEach var="size" items="${requestScope.carportsettings.carportWidths}">
                                    <option <c:if test="${sessionScope.carportwidth == size}">selected</c:if>>
                                        ${size}</option>
                                </c:forEach>
                            </select>
                        </div>

                        <div class="form-group">
                            <label for="carportLength">Carport Længde</label>
                            <select class="form-control" id="carportLength" name="length">
                                <c:if test="${sessionScope.carportlength == null}">
                                    <option>Vælg Længde</option>
                                </c:if>
                                <c:forEach var="size" items="${requestScope.carportsettings.carportLengths}">
                                    <option <c:if test="${sessionScope.carportlength == size}">selected</c:if>>
                                            ${size}</option>
                                </c:forEach>
                            </select>
                        </div>

                        <div class="form-group">
                            <label for="carportTag">Carport Tag</label>
                            <select class="form-control" id="carportTag" name="roof">
                                <c:if test="${sessionScope.carportroof != null && !sessionScope.carportroof.equals('Plasttrapezplader')}">
                                    <option selected>${sessionScope.carportroof}</option>
                                </c:if>
                                <c:if test="${sessionScope.carportroof == null}">
                                    <option>Vælg tagtype/farve</option>
                                </c:if>
                                <option>Betontagsten - Sort</option>
                                <option>Eternittag B6 - Grå</option>
                                <option>Eternittag B7 - Rødbrun</option>
                            </select>
                        </div>

                        <div class="form-group">
                            <label for="tagHældning">Taghældning</label>
                            <select class="form-control" id="tagHældning" name="roofangle">
                                <c:if test="${sessionScope.carportangle == null}">
                                    <option>Vælg vinkel</option>
                                </c:if>
                                <c:forEach var="size" items="${requestScope.carportsettings.carportRoofAngle}">
                                    <option <c:if test="${sessionScope.carportangle != null && sessionScope.carportangle == size}">selected</c:if>>
                                            ${size}</option>
                                </c:forEach>
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
                                    <c:if test="${sessionScope.shedwidth == null}">
                                        <option selected>Vælg bredde</option>
                                    </c:if>
                                    <c:forEach var="size" items="${requestScope.shedsettings.shedWidths}">
                                        <option <c:if test="${sessionScope.shedwidth == size}">selected</c:if>>
                                                ${size}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-group">
                                <label for="redskabsRumLængde">Redskabsum længde</label>
                                <select class="form-control" id="redskabsRumLængde" name="shedlength">
                                    <c:if test="${sessionScope.shedlength == null}">
                                        <option selected>Vælg længde</option>
                                    </c:if>
                                    <c:forEach var="size" items="${requestScope.shedsettings.shedLengths}">
                                        <option <c:if test="${sessionScope.shedlength == size}">selected</c:if>>
                                                ${size}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="skråAdditionals">Evt. bemærkninger</label>
                            <textarea maxlength="255" class="form-control" id="skråAdditionals"
                                      name="additionals"
                                      rows="3"></textarea>
                        </div>

                        <hr>

                        <!-- If user is logged in - SUBMIT. Else Login modal -->
                        <c:if test="${sessionScope.user != null}">
                            <div class="d-none d-lg-block d-xl-block">
                                <button type="submit" name="secarport" value="yes"
                                        class="btn btn-outline-secondary w-25">Se carport
                                </button>
                                <button type="submit" class="btn btn-primary w-25">Send forespørgsel</button>
                            </div>
                            <div class="d-block d-lg-none d-xl-none">
                                <button type="submit" name="secarport" value="yes"
                                        class="btn btn-outline-secondary w-50">Se carport
                                </button>
                                <button type="submit" class="btn btn-primary w-50">Send forespørgsel</button>
                            </div>
                        </c:if>
                        <c:if test="${sessionScope.user == null}">
                            <button type="submit" name="secarport" value="yes" class="btn btn-outline-secondary">Se
                                carport
                            </button>
                            <div class="btn-group dropup allow-focus">
                                <button type="button" class="btn btn-primary" data-toggle="dropdown"
                                        aria-haspopup="true" aria-expanded="false">
                                    Send forespørgsel
                                </button>
                                <div class="userContainer dropdown-menu text-muted"
                                     style="width: 450px; padding: 30px">

                                    <div id="myAngledLogin" style="display: block">
                                        <h5>Log ind</h5>

                                        <hr style="padding-top: 10px; padding-bottom: 5px;">

                                        <input type="hidden" name="hasuser" value="yes" id="angledHasUser">
                                        <input type="email" class="form-control" name="email" id="angledHasUserEmail"
                                               placeholder="Indtast e-mail adresse"
                                               autofocus="">
                                        <small style="margin-bottom: 5px"
                                               class="form-text text-muted">Vi
                                            vil aldrig dele din e-mail med andre</small>
                                        <input style="margin-bottom: 25px" type="password"
                                               class="form-control" name="password" id="angledHasUserPassword"
                                               placeholder="Indtast password">
                                        <a type="button" style="color: blue" onclick="showObliqueLogin()">Eller opret en
                                            bruger
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

                                        <label for="angledNoUserEmail" class="sr-only">Email adresse</label>
                                        <input type="email" id="angledNoUserEmail" class="form-control"
                                               name="email"
                                               placeholder="Indtast e-mail adresse" required=""
                                               autofocus="" style="margin-bottom: 10px" disabled>

                                        <label for="angledNoUserPassword" class="sr-only">Password</label>
                                        <input style="margin-bottom: 15px" type="password" id="angledNoUserPassword"
                                               class="form-control" name="password1"
                                               placeholder="Indtast password" required="" disabled>

                                        <label for="angledNoUserPassword2" class="sr-only">Password2</label>
                                        <input style="margin-bottom: 25px" type="password" id="angledNoUserPassword2"
                                               class="form-control" name="password2"
                                               placeholder="Gentag password" required="" disabled>

                                        <div class="row">
                                            <div class="col">
                                                <label for="angledNoUserAddress" class="sr-only">Adresse</label>
                                                <input type="text" id="angledNoUserAddress" class="form-control"
                                                       name="address"
                                                       placeholder="Indtast adresse" required=""
                                                       disabled>
                                            </div>
                                            <div class="col">
                                                <label for="angledNoUserName" class="sr-only">Navn</label>
                                                <input style="margin-bottom: 15px" type="text" id="angledNoUserName"
                                                       class="form-control" name="username"
                                                       placeholder="Indtast navn" required=""
                                                       autofocus="" disabled>
                                            </div>
                                        </div>

                                        <div class="row">
                                            <div class="col-md-8">
                                                <label for="angledNoUserCity" class="sr-only">By</label>
                                                <input type="text" id="angledNoUserCity" class="form-control"
                                                       name="city"
                                                       placeholder="Indtast by" required=""
                                                       disabled>
                                            </div>
                                            <div class="col-md-4">
                                                <label for="angledNoUserZip" class="sr-only">Post nr.</label>
                                                <input type="text" id="angledNoUserZip" class="form-control" name="zip"
                                                       placeholder="Post nr." required=""
                                                       autofocus="" disabled>
                                            </div>

                                        </div>

                                        <a type="button" style="color: blue; padding-top: 15px"
                                           onclick="showObliqueLogin()">Tilbage
                                            til
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
                    <form action="Main" class="form-preorder" method="post">
                        <input type="hidden" name="target" value="preorder">
                        <input type="hidden" name="flatroof" value="yes">
                        <div class="form-group" style="padding-top: 10px">
                            <h4 style="padding-top: 10px; padding-bottom: 10px">Carport størelse</h4>
                            <label for="flatCarportWidth">Carport bredde</label>
                            <select class="form-control" id="flatCarportWidth" name="width">
                                <c:if test="${sessionScope.carportwidth == null}">
                                    <option>Vælg bredde</option>
                                </c:if>
                                <c:forEach var="size" items="${requestScope.carportsettings.carportWidths}">
                                    <option <c:if test="${sessionScope.carportwidth == size}">selected</c:if>>
                                            ${size}</option>
                                </c:forEach>
                            </select>
                        </div>

                        <div class="form-group">
                            <label for="flatCarportLength">Carport Længde</label>
                            <select class="form-control" id="flatCarportLength" name="length">
                                <c:if test="${sessionScope.carportlength == null}">
                                    <option>Vælg Længde</option>
                                </c:if>
                                <c:forEach var="size" items="${requestScope.carportsettings.carportLengths}">
                                    <option <c:if test="${sessionScope.carportlength == size}">selected</c:if>>
                                            ${size}</option>
                                </c:forEach>
                            </select>
                        </div>

                        <div class="form-group">
                            <label for="flatcarportTag">Carport Tag</label>
                            <select class="form-control" id="flatcarportTag" name="roof">
                                <c:if test="${sessionScope.carportroof != null && !sessionScope.carportroof.contains('-')}">
                                    <option selected>${sessionScope.carportroof}</option>
                                </c:if>
                                <c:if test="${sessionScope.carportroof == null}">
                                    <option>Vælg tagtype/farve</option>
                                </c:if>
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
                                    <c:if test="${sessionScope.shedwidth == null}">
                                        <option selected>Vælg bredde</option>
                                    </c:if>
                                    <c:forEach var="size" items="${requestScope.shedsettings.shedWidths}">
                                        <option <c:if test="${sessionScope.shedwidth == size}">selected</c:if>>
                                                ${size}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-group">
                                <label for="flatRedskabsRumLængde">Redskabsum længde</label>
                                <select class="form-control" id="flatRedskabsRumLængde" name="shedlength">
                                    <c:if test="${sessionScope.shedlength == null}">
                                        <option selected>Vælg længde</option>
                                    </c:if>
                                    <c:forEach var="size" items="${requestScope.shedsettings.shedLengths}">
                                        <option <c:if test="${sessionScope.shedlength == size}">selected</c:if>>
                                                ${size}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="flatBemærkning">Evt. bemærkninger</label>
                            <textarea maxlength="255" class="form-control" id="flatBemærkning"
                                      name="additionals"
                                      rows="3"></textarea>
                        </div>

                        <hr>

                        <!-- If user is logged in - SUBMIT. Else Login dropdown -->
                        <c:if test="${sessionScope.user != null}">
                            <div class="d-none d-lg-block d-xl-block">
                                <button type="submit" name="secarport" value="yes"
                                        class="btn btn-outline-secondary w-25">Se carport
                                </button>
                                <button type="submit" class="btn btn-primary w-25">Send forespørgsel</button>
                            </div>
                            <div class="d-block d-lg-none d-xl-none">
                                <button type="submit" name="secarport" value="yes"
                                        class="btn btn-outline-secondary w-50">Se carport
                                </button>
                                <button type="submit" class="btn btn-primary w-50">Send forespørgsel</button>
                            </div>
                        </c:if>
                        <c:if test="${sessionScope.user == null}">
                            <button type="submit" name="secarport" value="yes" class="btn btn-outline-secondary">Se
                                carport
                            </button>
                            <div class="btn-group dropup allow-focus">
                                <button type="button" class="btn btn-primary" data-toggle="dropdown"
                                        aria-haspopup="true" aria-expanded="false">
                                    Send forespørgsel
                                </button>
                                <div class="userContainer dropdown-menu text-muted"
                                     style="width: 450px; padding: 30px">

                                    <div id="myFlatLogin" style="display: block">
                                        <h5>Log ind</h5>

                                        <hr style="padding-top: 10px; padding-bottom: 5px;">

                                        <input type="hidden" name="hasuser" value="yes" id="flatHasUser">
                                        <input type="email" class="form-control" name="email" id="flatHasUserEmail"
                                               placeholder="E-mail adresse"
                                               autofocus="">
                                        <small style="margin-bottom: 5px"
                                               class="form-text text-muted">Vi
                                            vil aldrig dele din e-mail med andre</small>
                                        <input style="margin-bottom: 25px" type="password"
                                               class="form-control" name="password" id="flatHasUserPassword"
                                               placeholder="Password">
                                        <a type="button" style="color: blue" onclick="showFlatLogin()">Eller opret en
                                            bruger her</a>

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

                                        <label for="flatNoUserEmail" class="sr-only">Email adresse</label>
                                        <input type="email" id="flatNoUserEmail" class="form-control"
                                               name="email"
                                               placeholder="Indtast e-mail adresse" required=""
                                               autofocus="" style="margin-bottom: 10px" disabled>

                                        <label for="flatNoUserPassword" class="sr-only">Password</label>
                                        <input style="margin-bottom: 15px" type="password" id="flatNoUserPassword"
                                               class="form-control" name="password1"
                                               placeholder="Indtast password" required="" disabled>

                                        <label for="flatNoUserPassword2" class="sr-only">Password2</label>
                                        <input style="margin-bottom: 25px" type="password" id="flatNoUserPassword2"
                                               class="form-control" name="password2"
                                               placeholder="Gentag password" required="" disabled>

                                        <div class="row">
                                            <div class="col">
                                                <label for="flatNoUserAddress" class="sr-only">Adresse</label>
                                                <input type="text" id="flatNoUserAddress" class="form-control"
                                                       name="address"
                                                       placeholder="Indtast adresse" required=""
                                                       disabled>
                                            </div>
                                            <div class="col">
                                                <label for="flatNoUserName" class="sr-only">Navn</label>
                                                <input style="margin-bottom: 15px" type="text" id="flatNoUserName"
                                                       class="form-control" name="username"
                                                       placeholder="Indtast navn" required=""
                                                       autofocus="" disabled>
                                            </div>
                                        </div>

                                        <div class="row">
                                            <div class="col-md-8">
                                                <label for="flatNoUserCity" class="sr-only">By</label>
                                                <input type="text" id="flatNoUserCity" class="form-control" name="city"
                                                       placeholder="Indtast by" required=""
                                                       disabled>
                                            </div>
                                            <div class="col-md-4">
                                                <label for="flatNoUserZip" class="sr-only">Post nr.</label>
                                                <input type="text" id="flatNoUserZip" class="form-control" name="zip"
                                                       placeholder="Post nr." required=""
                                                       autofocus="" disabled>
                                            </div>

                                        </div>

                                        <a type="button" style="color: blue; padding-top: 15px"
                                           onclick="showFlatLogin()">Tilbage
                                            til
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

                <!-- Putting the .allow-focus on a dropdown menu will make it not close on click -->
                <script>
                    $(document).on('click', '.allow-focus .dropdown-menu', function (e) {
                        e.stopPropagation();
                    });

                    function showObliqueLogin() {
                        const create = document.getElementById("myAngledCreate");
                        const login = document.getElementById("myAngledLogin");
                        const angledHasUser = document.getElementById("angledHasUser");
                        const angledHasUserEmail = document.getElementById("angledHasUserEmail");
                        const angledHasUserPassword = document.getElementById("angledHasUserPassword");

                        const angledNoUserName = document.getElementById("angledNoUserName");
                        const angledNoUserEmail = document.getElementById("angledNoUserEmail");
                        const angledNoUserPassword = document.getElementById("angledNoUserPassword");
                        const angledNoUserPassword2 = document.getElementById("angledNoUserPassword2");
                        const angledNoUserAddress = document.getElementById("angledNoUserAddress");
                        const angledNoUserZip = document.getElementById("angledNoUserZip");
                        const angledNoUserCity = document.getElementById("angledNoUserCity");

                        //show the login tab and activate the loginscreen
                        if (create.style.display === "block") {
                            create.style.display = "none";
                            login.style.display = "block";
                            angledHasUser.disabled = false;
                            angledHasUserEmail.disabled = false;
                            angledHasUserPassword.disabled = false;

                            //disable the createscreen
                            angledNoUserName.disabled = true;
                            angledNoUserEmail.disabled = true;
                            angledNoUserPassword.disabled = true;
                            angledNoUserPassword2.disabled = true;
                            angledNoUserAddress.disabled = true;
                            angledNoUserZip.disabled = true;
                            angledNoUserCity.disabled = true;
                        } else {
                            //show the login tab and activate the loginscreen
                            create.style.display = "block";
                            login.style.display = "none";
                            angledHasUser.disabled = true;
                            angledHasUserEmail.disabled = true;
                            angledHasUserPassword.disabled = true;

                            //disable the createscreen
                            angledNoUserName.disabled = false;
                            angledNoUserEmail.disabled = false;
                            angledNoUserPassword.disabled = false;
                            angledNoUserPassword2.disabled = false;
                            angledNoUserAddress.disabled = false;
                            angledNoUserZip.disabled = false;
                            angledNoUserCity.disabled = false;
                        }
                    }

                    function showFlatLogin() {
                        const create = document.getElementById("myFlatCreate");
                        const login = document.getElementById("myFlatLogin");
                        const angledHasUser = document.getElementById("flatHasUser");
                        const angledHasUserEmail = document.getElementById("flatHasUserEmail");
                        const angledHasUserPassword = document.getElementById("flatHasUserPassword");

                        const angledNoUserName = document.getElementById("flatNoUserName");
                        const angledNoUserEmail = document.getElementById("flatNoUserEmail");
                        const angledNoUserPassword = document.getElementById("flatNoUserPassword");
                        const angledNoUserPassword2 = document.getElementById("flatNoUserPassword2");
                        const angledNoUserAddress = document.getElementById("flatNoUserAddress");
                        const angledNoUserZip = document.getElementById("flatNoUserZip");
                        const angledNoUserCity = document.getElementById("flatNoUserCity");

                        if (create.style.display === "block") {
                            //show the login tab and activate the loginscreen
                            create.style.display = "none";
                            login.style.display = "block";
                            angledHasUser.disabled = false;
                            angledHasUserEmail.disabled = false;
                            angledHasUserPassword.disabled = false;

                            //disable the createscreen
                            angledNoUserName.disabled = true;
                            angledNoUserEmail.disabled = true;
                            angledNoUserPassword.disabled = true;
                            angledNoUserPassword2.disabled = true;
                            angledNoUserAddress.disabled = true;
                            angledNoUserZip.disabled = true;
                            angledNoUserCity.disabled = true;
                        } else {
                            //show the login tab and activate the loginscreen
                            create.style.display = "block";
                            login.style.display = "none";
                            angledHasUser.disabled = true;
                            angledHasUserEmail.disabled = true;
                            angledHasUserPassword.disabled = true;

                            //disable the createscreen
                            angledNoUserAddress.disabled = false;
                            angledNoUserZip.disabled = false;
                            angledNoUserCity.disabled = false;
                            angledNoUserName.disabled = false;
                            angledNoUserEmail.disabled = false;
                            angledNoUserPassword.disabled = false;
                            angledNoUserPassword2.disabled = false;
                        }
                    }
                </script>
            </div>
        </div>
    </div>
</div>
