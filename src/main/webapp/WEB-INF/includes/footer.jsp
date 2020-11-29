<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<footer class="footer flex-shrink-0 text-white">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="row">
                    <div class="accordion d-lg-flex w-100 h-auto" id="accordion">
                        <div class="col-lg-3 col-md-12">
                            <a href="#one"
                               class="my-footerheader my-footerdropdown h3 nav-link py-0 d-block d-lg-none d-xl-none"
                               data-toggle="collapse" data-parent="#accordion">
                                Hvordan køber jeg?
                            </a>

                            <div class="h3 my-footerheader d-none d-lg-block d-xl-block">
                                Hvordan køber jeg?
                            </div>

                            <div class="collapse d-lg-flex" id="one" data-parent="#accordion">
                                <ul class="list-unstyled">
                                    <li>
                                        <div class="btn-group dropup">
                                            <a class="my-footerlinks" data-toggle="dropdown"
                                               aria-haspopup="true" aria-expanded="false" href="#">Ofte stillede
                                                spørgsmål</a>
                                            <div class="btn-group dropup">
                                                <div class="dropdown-menu text-muted"
                                                     style="width: 300px; padding: 30px">
                                                    <h5>Hvordan laver jeg en Carport</h5>
                                                    <p>
                                                        Du går ind på bestil siden, derinde kan du vælge hvilken carport
                                                        du kunne ønske dig.
                                                        <br> Når du har lavet en forespørgsel, vil du blive kontaktet
                                                        omkring din carport.
                                                    </p>
                                                    <hr>
                                                    <h5>Må jeg selv bygge min carport</h5>
                                                    <p>
                                                        Normalt vil vi selv sende nogen ud, som vil stå for at bygge din
                                                        carport,
                                                        <br>men hvis du har lyst til, at bygge din carport selv, må du
                                                        gerne det.
                                                    </p>
                                                </div>
                                            </div>
                                        </div>
                                    </li>
                                    <li>
                                        <c:choose>
                                            <c:when test="${sessionScope.user == null}">
                                                <a class="my-footerlinks" href="#" data-toggle="modal"
                                                   data-target="#loginModal">Bliv kunde i Fog</a>
                                            </c:when>
                                            <c:when test="${sessionScope.user != null}">
                                                <a class="my-footerlinks" href="Main?target=redirect&destination=createorder">Bliv kunde i Fog</a>
                                            </c:when>
                                        </c:choose>
                                    </li>
                                </ul>
                            </div>
                        </div>
                        <div class="col-lg-3 col-md-12">
                            <a href="#two"
                               class="h3 my-footerheader my-footerdropdown nav-link py-0 d-block d-lg-none d-xl-none"
                               data-toggle="collapse" data-parent="#accordion">
                                Aktuelt
                            </a>

                            <div class="h3 my-footerheader d-none d-lg-block d-xl-block">
                                Aktuelt
                            </div>

                            <div class="collapse d-lg-flex" id="two" data-parent="#accordion">
                                <ul class="list-unstyled">
                                    <li>
                                        <div class="btn-group dropup">
                                            <a class="my-footerlinks" data-toggle="dropdown"
                                               aria-haspopup="true" aria-expanded="false" href="#">Tilbud</a>
                                            <div class="btn-group dropup">
                                                <div class="dropdown-menu text-muted"
                                                     style="width: 300px; padding: 30px">
                                                    <h5>Tilbud</h5>
                                                    <p>
                                                        Vi har ikke nogle aktive tilbud, men du kan altid lave en
                                                        carport.
                                                    </p>
                                                </div>
                                            </div>
                                        </div>
                                    </li>
                                    <li>
                                        <a class="my-footerlinks" href="https://www.facebook.com/">Facebook</a>
                                    </li>
                                    <li>
                                        <a class="my-footerlinks" href="https://www.instagram.com/">Instagram</a>
                                    </li>
                                    <li>
                                        <a class="my-footerlinks" href="https://www.linkedin.com/">LinkedIn</a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                        <div class="col-lg-3 col-md-12">
                            <a href="#three"
                               class="h3 my-footerheader my-footerdropdown nav-link py-0 d-block d-lg-none d-xl-none"
                               data-toggle="collapse" data-parent="#accordion">
                                Om Fog
                            </a>

                            <div class="h3 my-footerheader d-none d-lg-block d-xl-block">
                                Om Fog
                            </div>

                            <div class="collapse d-lg-flex" id="three" data-parent="#accordion">
                                <ul class="list-unstyled">
                                    <li>
                                        <div class="btn-group dropup">
                                            <a class="my-footerlinks" data-toggle="dropdown"
                                               aria-haspopup="true" aria-expanded="false" href="#">Åbningstider</a>
                                            <div class="btn-group dropup">
                                                <div class="dropdown-menu text-muted"
                                                     style="width: 300px; padding: 30px">
                                                    <h5>Hver dage</h5>
                                                    <p>
                                                        Mandag: 10:00 - 18:00
                                                        <br>Tirsdag: 10:00 - 18:00
                                                        <br>Onsdag: 10:00 - 18:00
                                                        <br>Torsdag: 10:00 - 18:00
                                                        <br>Fredag: 10:00 - 16:00</p>
                                                    <hr>
                                                    <h5>Week end</h5>
                                                    <p>
                                                        Lørdag: 10:00 - 14:00
                                                        <br>Søndag: 10:00 - 14:00
                                                    </p>
                                                </div>
                                            </div>
                                        </div>
                                    </li>
                                    <li>
                                        <div class="btn-group dropup">
                                            <a class="my-footerlinks" data-toggle="dropdown"
                                               aria-haspopup="true" aria-expanded="false" href="#">Om Fog</a>
                                            <div class="btn-group dropup">
                                                <div class="dropdown-menu text-muted"
                                                     style="width: 300px; padding: 30px">
                                                    <h5>NOT Fog</h5>
                                                    <p>
                                                        Vores hjemmeside er lavet til at replikerer Fogs hjemmeside.
                                                        <br>
                                                        Dette er ikke på nogen måde Fogs hjemmeside, og du vil ikke
                                                        kunne bestille rigtige carporte.
                                                    </p>
                                                </div>
                                            </div>
                                        </div>
                                    </li>
                                    <li>
                                        <div class="btn-group dropup">
                                            <a class="my-footerlinks" data-toggle="dropdown"
                                               aria-haspopup="true" aria-expanded="false" href="#">Cookie-politik</a>
                                            <div class="btn-group dropup">
                                                <div class="dropdown-menu text-muted"
                                                     style="width: 300px; padding: 30px">
                                                    <h5>Cookie politik</h5>
                                                    <p>
                                                        På vores hjemmeside bruger vi cookies, de bliver brugt for at du
                                                        som bruger får en bedere oplevelse på hjemmesiden.</p>
                                                    <hr>
                                                    <p>Vi bruger sessions til at: <br><br>
                                                        1. Vide hvilken bruger der er logget ind.
                                                    </p>
                                                </div>
                                            </div>
                                        </div>
                                    </li>
                                    <li>
                                        <div class="btn-group dropup">
                                            <a class="my-footerlinks" data-toggle="dropdown"
                                               aria-haspopup="true" aria-expanded="false" href="#">Person data</a>
                                            <div class="btn-group dropup">
                                                <div class="dropdown-menu text-muted"
                                                     style="width: 300px; padding: 30px">
                                                    <h5>Person data</h5>
                                                    <p>
                                                        Vi gemmer desværre din data for altid, undskyld.
                                                    </p>
                                                </div>
                                            </div>
                                        </div>
                                    </li>
                                </ul>
                            </div>
                        </div>
                        <div class="col-lg-3 col-md-12">
                            <p class="fog-info" style="padding-top: 20px"><i class="fas fa-users"
                                                                             style="border: 1px solid white; border-radius: 40px; padding: 7px; margin-right: 10px"></i>
                                NOT Johannes Fog</p>
                            <p class="fog-info"><i class="fas fa-phone-alt"
                                                   style="border: 1px solid white; border-radius: 40px; padding: 7px; margin-right: 10px"></i>
                                98 71 23 77</p>
                            <a href="mailto:notfog@notfog.com" class="fog-info"><i class="far fa-envelope"
                                                   style="border: 1px solid white; border-radius: 40px; padding: 7px; margin-right: 10px"></i>
                                Send e-mail</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="container" style="margin-top: 30px">
        <div class="row">
            <div class="bottom-footer d-lg-flex h-auto w-100">
                <div class="col-lg-8 col-md-12">
                    <p>
                        NOT Johannes Fog A/S - Fogvej 15 - 2800 Lyngby - NOT FOG-CVR-nr. 12313374321
                    </p>
                </div>
                <div class="col-lg-1"></div>
                <div class="col-lg-3 col-md-12">
                    <p>
                        NOT Fog version (${applicationScope.version})
                    </p>
                </div>
            </div>
        </div>
    </div>
</footer>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"/>
<script src="https://kit.fontawesome.com/d558e38d6e.js" crossorigin="anonymous"></script>