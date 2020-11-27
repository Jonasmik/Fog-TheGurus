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
                                        <a class="my-footerlinks" href="#">Ofte Stillede spørgsmål</a>
                                    </li>
                                    <li>
                                        <a class="my-footerlinks" href="#">Bliv kunde i Fog</a>
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
                                        <a class="my-footerlinks" href="#">Tilbud</a>
                                    </li>
                                    <li>
                                        <a class="my-footerlinks" href="#">Facebook</a>
                                    </li>
                                    <li>
                                        <a class="my-footerlinks" href="#">Instagram</a>
                                    </li>
                                    <li>
                                        <a class="my-footerlinks" href="#">LinkedIn</a>
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
                                        <a class="my-footerlinks" href="#">Åbningstider</a>
                                    </li>
                                    <li>
                                        <a class="my-footerlinks" href="#">Om Fog</a>
                                    </li>
                                    <li>
                                        <a class="my-footerlinks" href="#">Cookie-politik</a>
                                    </li>
                                    <li>
                                        <a class="my-footerlinks" href="#">Persondata</a>
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
                            <p class="fog-info"><i class="far fa-envelope"
                                                   style="border: 1px solid white; border-radius: 40px; padding: 7px; margin-right: 10px"></i>
                                Send e-mail</p>
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

<script src="${pageContext.request.contextPath}/js/cookies.js"></script>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
        integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"
        integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js"
        integrity="sha384-w1Q4orYjBQndcko6MimVbzY0tgp4pWB4lZ7lr30WKz0vr/aWKhXdBNmNb5D92v7s"
        crossorigin="anonymous"></script>
<script src="https://kit.fontawesome.com/d558e38d6e.js" crossorigin="anonymous"></script>