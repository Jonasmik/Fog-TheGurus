<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<div class="container" style="padding: 1rem">
    <form method="post">
        <div class="row">
            <div class="col">
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
                            <div class="form-group" style="padding-top: 10px">
                                <label for="carportWidth">Carport bredde</label>
                                <select class="form-control" id="carportWidth">
                                    <option>Vælg bredde</option>
                                    <option>2</option>
                                    <option>3</option>
                                    <option>4</option>
                                    <option>5</option>
                                </select>
                            </div>

                            <div class="form-group">
                                <label for="carportLength">Carport Længde</label>
                                <select class="form-control" id="carportLength">
                                    <option>Vælg Længde</option>
                                    <option>2</option>
                                    <option>3</option>
                                    <option>4</option>
                                    <option>5</option>
                                </select>
                            </div>

                            <div class="form-group">
                                <label for="carportTag">Carport Tag</label>
                                <select class="form-control" id="carportTag">
                                    <option selected>Vælg tagtype/farve</option>
                                    <option>Betontagsten - Sort</option>
                                    <option>Eternittag B6 - Grå</option>
                                    <option>Eternittag B7 - Rødbrun</option>
                                </select>
                            </div>

                            <div class="form-group">
                                <label for="tagHældning">Taghældning</label>
                                <select class="form-control" id="tagHældning">
                                    <option selected>Vælg taghældning</option>
                                    <option>15 grader</option>
                                    <option>20 grader</option>
                                    <option>25 grader</option>
                                </select>
                            </div>

                            <div class="form-check">
                                <label>
                                    <input class="form-check-input" type="checkbox" onchange="showObliqueRoofOptions()"
                                           name="agreeskrå"> Ønsker redskabsrum
                                </label>
                            </div>

                            <div id="myFirstExtraRoom" style="display: none">

                                <div class="form-group">
                                    <p>Hvis du ikke ønsker redskabsrum alligevel så husk, at sætte bredde og længde til
                                        "Vælg .."</p>
                                    <h4>Beregn 15 cm tagudhæng på hver side af redskabsrummet.</h4>
                                    <label for="redskabsRumBredde">Redskabsrum bredde</label>
                                    <select class="form-control" id="redskabsRumBredde">
                                        <option selected>Vælg bredde</option>
                                        <option>210 cm</option>
                                        <option>240 cm</option>
                                        <option>270 cm</option>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label for="redskabsRumLængde">Redskabsum længde</label>
                                    <select class="form-control" id="redskabsRumLængde">
                                        <option selected>Vælg længde</option>
                                        <option>210 cm</option>
                                        <option>240 cm</option>
                                        <option>270 cm</option>
                                    </select>
                                </div>
                            </div>

                            <hr>
                            <div class="d-none d-lg-block d-xl-block">
                                <button type="submit" class="btn btn-primary w-25">Send forespørgsel</button>
                            </div>
                            <div class="d-block d-lg-none d-xl-none">
                                <button type="submit" class="btn btn-primary w-50">Send forespørgsel</button>
                            </div>
                        </form>
                    </div>
                    <!-- Carport med skråt tag END -->

                    <!-- Carport med flat tag START -->
                    <div class="tab-pane" id="profile" role="tabpanel" aria-labelledby="profile-tab">
                        <form action="Main" method="post">
                            <div class="form-group" style="padding-top: 10px">
                                <label for="flatCarportWidth">Carport bredde</label>
                                <select class="form-control" id="flatCarportWidth">
                                    <option>Vælg bredde</option>
                                    <option>2</option>
                                    <option>3</option>
                                    <option>4</option>
                                    <option>5</option>
                                </select>
                            </div>

                            <div class="form-group">
                                <label for="flatCarportLength">Carport Længde</label>
                                <select class="form-control" id="flatCarportLength">
                                    <option>Vælg Længde</option>
                                    <option>2</option>
                                    <option>3</option>
                                    <option>4</option>
                                    <option>5</option>
                                </select>
                            </div>

                            <div class="form-check">
                                <label>
                                    <input class="form-check-input" type="checkbox" onchange="showFlatRoofOptions()"
                                           name="agreeflat"> Ønsker redskabsrum
                                </label>
                            </div>

                            <div id="mySecondExtraRoom" style="display: none">

                                <div class="form-group">
                                    <p>Hvis du ikke ønsker redskabsrum alligevel så husk, at sætte bredde og længde til
                                        "Vælg .."</p>
                                    <h4>Beregn 15 cm tagudhæng på hver side af redskabsrummet.</h4>
                                    <label for="flatRedskabsRumBredde">Redskabsrum bredde</label>
                                    <select class="form-control" id="flatRedskabsRumBredde">
                                        <option selected>Vælg bredde</option>
                                        <option>210 cm</option>
                                        <option>240 cm</option>
                                        <option>270 cm</option>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label for="flatRedskabsRumLængde">Redskabsum længde</label>
                                    <select class="form-control" id="flatRedskabsRumLængde">
                                        <option selected>Vælg længde</option>
                                        <option>210 cm</option>
                                        <option>240 cm</option>
                                        <option>270 cm</option>
                                    </select>
                                </div>
                            </div>

                            <hr>
                            <div class="d-none d-lg-block d-xl-block">
                                <button type="submit" class="btn btn-primary w-25">Send forespørgsel</button>
                            </div>
                            <div class="d-block d-lg-none d-xl-none">
                                <button type="submit" class="btn btn-primary w-50">Send forespørgsel</button>
                            </div>
                        </form>
                    </div>
                    <!-- Carport med flat tag END -->
                </div>
            </div>
        </div>

        <script>
            function showObliqueRoofOptions() {
                var x = document.getElementById("myFirstExtraRoom");
                if (x.style.display === "none") {
                    x.style.display = "block";
                } else {
                    x.style.display = "none";
                }
            }

            function showFlatRoofOptions() {
                var x = document.getElementById("mySecondExtraRoom");
                if (x.style.display === "none") {
                    x.style.display = "block";
                } else {
                    x.style.display = "none";
                }
            }
        </script>

    </form>
</div>
