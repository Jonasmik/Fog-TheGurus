<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>

<!-- Include header -->
<jsp:include page="includes/header.jsp" flush="true"/>

<!-- Insert title -->
<title>Fog: Profil</title>

<body class="d-flex flex-column h-100 bg-light">

<!-- Include navbar -->
<jsp:include page="includes/navs/navbar.jsp" flush="true"/>

<main role="main" class="container flex-shrink-0">
    <jsp:include page="includes/modals/userlogoutmodal.jsp" flush="true"/>

    <h1>Profil side</h1>


    <div class="row">
        <div class="col-md-4"
             style="background-color: rgba(173,216,230, 0.2); border-radius: 30px; padding: 15px; height: 380px">
            <h3>Information</h3>
            <p>Navn: ${sessionScope.user.name}</p>
            <p>Email: ${sessionScope.user.email}</p>
            <button type="button" class="btn btn-secondary">Rediger Information</button>

            <h3 style="padding-top: 40px">Sikkerhed</h3>
            <button type="button" class="btn btn-secondary">Rediger password</button>
        </div>
        <div class="col-md-8">

            <c:if test="${sessionScope.correctinput != null}">
                <div class="alert alert-success border-secondary">
                    <h1>Opdatering</h1>
                        ${sessionScope.correctinput}
                </div>
            </c:if>

            <c:if test="${sessionScope.failinput != null}">
                <div class="alert alert-danger border-secondary">
                    <h1>Fejl</h1>
                        ${sessionScope.failinput}
                </div>
            </c:if>

            <c:if test="${sessionScope.carportpreview != null}">
                <div class="alert alert-info border-secondary">
                    <h1>Din carport</h1>
                        ${sessionScope.carportpreview}
                </div>
            </c:if>

            <!-- Forespørgelser START -->
            <h3 style="padding-top: 20px">Forespørgelser</h3>
            <c:if test="${requestScope.haspreorder == null}">
                <p>Du har ikke nogle forespørgelser</p>
            </c:if>

            <!-- Untaken preorders START -->
            <c:if test="${not empty requestScope.untakenpreorders}">
                <h3 style="padding-top: 20px">Venter på salgsmedarbejder</h3>
                <div class="table-responsive">
                    <table class="table table-bordered">
                        <thead>
                        <tr>
                            <th scope="col">Id</th>
                            <th scope="col">Evt. besked</th>
                            <th scope="col" style="text-align: center">Vare</th>
                        </tr>
                        </thead>
                        <tbody>

                        <!-- doublecount = used to jump to the next letter in alphabet
                             count  = used to know where in every list we are
                         -->
                        <c:set var="countuntakenpreorder" value="0" scope="page"/>
                        <c:forEach var="untakenpreorder"
                                   items="${requestScope.untakenpreorders}">
                            <tr>
                                <th scope="row">${untakenpreorder.preOrder.id}</th>
                                <td>${untakenpreorder.customer.additional}</td>
                                <td style="width: 20%;">
                                    <button type="button"
                                            class="btn btn-block btn-outline-info"
                                            data-toggle="modal"
                                            data-target="#untakenpreorders${countuntakenpreorder}">
                                        Se carport
                                    </button>
                                </td>
                            </tr>
                            <div class="modal fade"
                                 id="untakenpreorders${countuntakenpreorder}"
                                 tabindex="-1"
                                 aria-labelledby="untakenpreorders${countuntakenpreorder}"
                                 aria-hidden="true">
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h5 class="modal-title" id="untakenpreorders">
                                                Forespørgelse
                                                nr. ${untakenpreorder.preOrder.id}</h5>
                                            <button type="button" class="close"
                                                    data-dismiss="modal"
                                                    aria-label="Close">
                                                <span aria-hidden="true">&times;</span>
                                            </button>
                                        </div>
                                        <div class="modal-body">
                                            <form action="Main" method="POST">
                                                <input type="hidden" name="target"
                                                       value="editpreorder">
                                                <input type="hidden" name="carportid"
                                                       value="${untakenpreorder.carport.id}">
                                                <input type="hidden" name="shedid"
                                                       value="${untakenpreorder.shed.id}">
                                                <h5>Carport</h5>
                                                <div class="row" style="padding-bottom: 10px">
                                                    <div class="col-5">
                                                        <label for="carportWidth">Bredde</label>
                                                    </div>
                                                    <div class="col-7">
                                                        <select class="form-control"
                                                                id="carportWidth"
                                                                name="carportwidth">
                                                            <c:forEach var="size"
                                                                       items="${requestScope.carportsettings.carportWidths}">
                                                                <option
                                                                        <c:if test="${untakenpreorder.carport.width == size}">selected</c:if>>
                                                                        ${size}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                </div>
                                                <div class="row" style="padding-bottom: 10px">
                                                    <div class="col-5">
                                                        <label for="carportLength">Længde</label>
                                                    </div>
                                                    <div class="col-7">
                                                        <select class="form-control"
                                                                id="carportLength"
                                                                name="carportlength">
                                                            <c:forEach var="size"
                                                                       items="${requestScope.carportsettings.carportLengths}">
                                                                <option
                                                                        <c:if test="${untakenpreorder.carport.length == size}">selected</c:if>>
                                                                        ${size}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                </div>
                                                <div class="row" style="padding-bottom: 10px">
                                                    <div class="col-5">
                                                        <label for="carportRoof">Tag
                                                            type/farve</label>
                                                    </div>
                                                    <div class="col-7">
                                                        <select class="form-control"
                                                                id="carportRoof"
                                                                name="carportroof">
                                                            <c:choose>
                                                                <c:when test="${untakenpreorder.carport.roof.equals('Plasttrapezplader')}">
                                                                    <option selected>
                                                                        Plasttrapezplader
                                                                    </option>
                                                                </c:when>

                                                                <c:when test="${!untakenpreorder.carport.roof.equals('Plasttrapezplader')}">
                                                                    <option selected>${untakenpreorder.carport.roof}</option>
                                                                    <option>Betontagsten - Sort
                                                                    </option>
                                                                    <option>Eternittag B6 - Grå
                                                                    </option>
                                                                    <option>Eternittag B7 -
                                                                        Rødbrun
                                                                    </option>
                                                                </c:when>
                                                            </c:choose>
                                                        </select>
                                                    </div>
                                                </div>
                                                <c:if test="${untakenpreorder.carport.roofAngle > 0}">
                                                    <div class="row" style="padding-bottom: 10px">
                                                        <div class="col-5">
                                                            <label for="carportRoofAngle">Tag
                                                                vinkel</label>
                                                        </div>
                                                        <div class="col-7">
                                                            <select class="form-control"
                                                                    id="carportRoofAngle"
                                                                    name="carportroofangle">
                                                                <c:forEach var="size"
                                                                           items="${requestScope.carportsettings.carportRoofAngle}">
                                                                    <option
                                                                            <c:if test="${untakenpreorder.carport.roofAngle == size}">selected</c:if>>
                                                                            ${size}</option>
                                                                </c:forEach>
                                                            </select>
                                                        </div>
                                                    </div>
                                                </c:if>
                                                <c:if test="${untakenpreorder.shed.length > 0}">
                                                    <h5>Skur</h5>
                                                    <div class="row" style="padding-bottom: 10px">
                                                        <div class="col-5">
                                                            <label for="ShedWidth">Brædde</label>
                                                        </div>
                                                        <div class="col-7">
                                                            <select class="form-control"
                                                                    id="ShedWidth"
                                                                    name="shedwidth">
                                                                <c:forEach var="size"
                                                                           items="${requestScope.shedsettings.shedWidths}">
                                                                    <option
                                                                            <c:if test="${untakenpreorder.shed.width == size}">selected</c:if>>
                                                                            ${size}</option>
                                                                </c:forEach>
                                                            </select>
                                                        </div>
                                                    </div>
                                                    <div class="row" style="padding-bottom: 10px">
                                                        <div class="col-5">
                                                            <label for="ShedLength">Længde</label>
                                                        </div>
                                                        <div class="col-7">
                                                            <select class="form-control"
                                                                    id="ShedLength"
                                                                    name="shedlength">
                                                                <c:forEach var="size"
                                                                           items="${requestScope.shedsettings.shedLengths}">
                                                                    <option
                                                                            <c:if test="${untakenpreorder.shed.length == size}">selected</c:if>>
                                                                            ${size}</option>
                                                                </c:forEach>
                                                            </select>
                                                        </div>
                                                    </div>
                                                </c:if>
                                                <div class="row">
                                                    <div class="col-5">
                                                    </div>
                                                    <div class="col-7">
                                                        <span style="text-align: end; font-size: 12px; font-weight: 300;">Ved redigering bliver dit tilbud deaktiveret</span>
                                                        <button type="submit"
                                                                class="btn btn-block btn-outline-success">
                                                            Gem
                                                            redigering
                                                        </button>
                                                    </div>
                                                </div>
                                            </form>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button"
                                                    class="btn btn-outline-secondary"
                                                    data-dismiss="modal">Luk
                                            </button>
                                            <form action="Main" method="POST">
                                                <input type="hidden" name="target"
                                                       value="generatecarportpicture">
                                                <input type="hidden" name="carportlength"
                                                       value="${untakenpreorder.carport.length}">
                                                <input type="hidden" name="carportwidth"
                                                       value="${untakenpreorder.carport.width}">
                                                <input type="hidden" name="shedlength" value="${untakenpreorder.shed.length}">
                                                <input type="hidden" name="shedwidth" value="${untakenpreorder.shed.width}">
                                                <button type="submit"
                                                        class="btn btn-outline-primary">
                                                    Se tegning
                                                </button>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <c:set var="countuntakenpreorder" value="${countuntakenpreorder + 1}"
                                   scope="page"/>

                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </c:if>
            <!-- Untaken preorders END -->

            <!-- Taken preorders START -->
            <c:if test="${not empty requestScope.takenpreorder}">
                <h3 style="padding-top: 20px">Aktive forespørgelser</h3>
                <div class="table-responsive">
                    <table class="table table-bordered">
                        <thead>
                        <tr>
                            <th scope="col">Id</th>
                            <th scope="col">Evt. besked</th>
                            <th scope="col">Salgsmedarbejder</th>
                            <th scope="col" style="text-align: center">Vare</th>
                        </tr>
                        </thead>
                        <tbody>

                        <!-- doublecount = used to jump to the next letter in alphabet
                             count  = used to know where in every list we are
                         -->
                        <c:set var="countactivepreorder" value="0" scope="page"/>
                        <c:forEach var="takenpreorder" items="${requestScope.takenpreorder}">
                            <tr>
                                <th scope="row">${takenpreorder.preOrder.id}</th>
                                <td>${takenpreorder.customer.additional}</td>
                                <td>
                                    Navn: ${requestScope.preordersalesmen.get(countactivepreorder).name},
                                    Kontakt
                                    e-mail: ${requestScope.preordersalesmen.get(countactivepreorder).email}</td>
                                <td style="width: 20%">
                                    <button type="button"
                                            class="btn btn-block btn-outline-info"
                                            data-toggle="modal"
                                            data-target="#activepreorders${countactivepreorder}">
                                        Se carport
                                    </button>
                                </td>
                            </tr>
                            <div class="modal fade"
                                 id="activepreorders${countactivepreorder}"
                                 tabindex="-1"
                                 aria-labelledby="activepreorders${countactivepreorder}"
                                 aria-hidden="true">
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h5 class="modal-title">
                                                Forespørgelse
                                                nr. ${takenpreorder.preOrder.id}</h5>
                                            <button type="button" class="close"
                                                    data-dismiss="modal"
                                                    aria-label="Close">
                                                <span aria-hidden="true">&times;</span>
                                            </button>
                                        </div>
                                        <div class="modal-body">
                                            <form action="Main" method="POST">
                                                <input type="hidden" name="target"
                                                       value="editpreorder">
                                                <input type="hidden" name="carportid"
                                                       value="${takenpreorder.carport.id}">
                                                <input type="hidden" name="shedid"
                                                       value="${takenpreorder.shed.id}">
                                                <h5>Carport</h5>
                                                <div class="row" style="padding-bottom: 10px">
                                                    <div class="col-5">
                                                        <label for="carportWidthTaken">Bredde</label>
                                                    </div>
                                                    <div class="col-7">
                                                        <select class="form-control"
                                                                id="carportWidthTaken"
                                                                name="carportwidth">
                                                            <c:forEach var="size"
                                                                       items="${requestScope.carportsettings.carportWidths}">
                                                                <option
                                                                        <c:if test="${takenpreorder.carport.width == size}">selected</c:if>>
                                                                        ${size}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                </div>
                                                <div class="row" style="padding-bottom: 10px">
                                                    <div class="col-5">
                                                        <label for="carportLengthTaken">Længde</label>
                                                    </div>
                                                    <div class="col-7">
                                                        <select class="form-control"
                                                                id="carportLengthTaken"
                                                                name="carportlength">
                                                            <c:forEach var="size"
                                                                       items="${requestScope.carportsettings.carportLengths}">
                                                                <option
                                                                        <c:if test="${takenpreorder.carport.length == size}">selected</c:if>>
                                                                        ${size}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                </div>
                                                <div class="row" style="padding-bottom: 10px">
                                                    <div class="col-5">
                                                        <label for="carportRoofTaken">Tag
                                                            type/farve</label>
                                                    </div>
                                                    <div class="col-7">
                                                        <select class="form-control"
                                                                id="carportRoofTaken"
                                                                name="carportroof">
                                                            <c:choose>
                                                                <c:when test="${takenpreorder.carport.roof.equals('Plasttrapezplader')}">
                                                                    <option selected>
                                                                        Plasttrapezplader
                                                                    </option>
                                                                </c:when>

                                                                <c:when test="${!takenpreorder.carport.roof.equals('Plasttrapezplader')}">
                                                                    <option selected>${takenpreorder.carport.roof}</option>
                                                                    <option>Betontagsten - Sort
                                                                    </option>
                                                                    <option>Eternittag B6 - Grå
                                                                    </option>
                                                                    <option>Eternittag B7 -
                                                                        Rødbrun
                                                                    </option>
                                                                </c:when>
                                                            </c:choose>
                                                        </select>
                                                    </div>
                                                </div>
                                                <c:if test="${takenpreorder.carport.roofAngle > 0}">
                                                    <div class="row" style="padding-bottom: 10px">
                                                        <div class="col-5">
                                                            <label for="carportAngleTaken">Tag
                                                                vinkel</label>
                                                        </div>
                                                        <div class="col-7">
                                                            <select class="form-control"
                                                                    id="carportAngleTaken"
                                                                    name="carportlength">
                                                                <c:forEach var="size"
                                                                           items="${requestScope.carportsettings.carportRoofAngle}">
                                                                    <option
                                                                            <c:if test="${takenpreorder.carport.roofAngle == size}">selected</c:if>>
                                                                            ${size}</option>
                                                                </c:forEach>
                                                            </select>
                                                        </div>
                                                    </div>
                                                </c:if>
                                                <c:if test="${takenpreorder.shed.length > 0}">
                                                    <h5>Skur</h5>
                                                    <div class="row" style="padding-bottom: 10px">
                                                        <div class="col-5">
                                                            <label for="ShedWidthTaken">Brædde</label>
                                                        </div>
                                                        <div class="col-7">
                                                            <select class="form-control"
                                                                    id="ShedWidthTaken"
                                                                    name="shedwidth">
                                                                <c:forEach var="size"
                                                                           items="${requestScope.shedsettings.shedWidths}">
                                                                    <option
                                                                            <c:if test="${takenpreorder.shed.width == size}">selected</c:if>>
                                                                            ${size}</option>
                                                                </c:forEach>
                                                            </select>
                                                        </div>
                                                    </div>
                                                    <div class="row" style="padding-bottom: 10px">
                                                        <div class="col-5">
                                                            <label for="ShedLengthTaken">Længde</label>
                                                        </div>
                                                        <div class="col-7">
                                                            <select class="form-control"
                                                                    id="ShedLengthTaken"
                                                                    name="shedlength">
                                                                <c:forEach var="size"
                                                                           items="${requestScope.shedsettings.shedLengths}">
                                                                    <option
                                                                            <c:if test="${takenpreorder.shed.length == size}">selected</c:if>>
                                                                            ${size}</option>
                                                                </c:forEach>
                                                            </select>
                                                        </div>
                                                    </div>
                                                </c:if>
                                                <div class="row">
                                                    <div class="col-5">
                                                    </div>
                                                    <div class="col-7">
                                                        <span style="text-align: end; font-size: 12px; font-weight: 300;">Ved redigering bliver dit tilbud deaktiveret</span>
                                                        <button type="submit"
                                                                class="btn btn-block btn-outline-success">
                                                            Gem redigering
                                                        </button>
                                                    </div>
                                                </div>
                                            </form>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button"
                                                    class="btn btn-outline-secondary"
                                                    data-dismiss="modal">Luk
                                            </button>
                                            <form action="Main" method="POST">
                                                <input type="hidden" name="target"
                                                       value="generatecarportpicture">
                                                <input type="hidden" name="carportlength"
                                                       value="${takenpreorder.carport.length}">
                                                <input type="hidden" name="carportwidth"
                                                       value="${takenpreorder.carport.width}">
                                                <input type="hidden" name="shedlength" value="${takenpreorder.shed.length}">
                                                <input type="hidden" name="shedwidth" value="${takenpreorder.shed.width}">
                                                <button type="submit"
                                                        class="btn btn-outline-primary">
                                                    Se tegning
                                                </button>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <c:set var="countactivepreorder" value="${countactivepreorder + 1}"
                                   scope="page"/>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </c:if>
            <!-- Taken preorders END -->
            <!-- Forespørgelser END -->

            <!-- Tilbud START -->
            <h3>Aktive tilbud</h3>
            <c:if test="${requestScope.listoffers == null}">
                <p>Du har ikke nogle aktive tilbud</p>
            </c:if>

            <c:if test="${requestScope.listoffers != null}">
                <table class="table table-sm table-bordered">
                    <thead>
                    <tr>
                        <th scope="col" style="text-align: center">Forespørgelser Id</th>
                        <th scope="col" style="text-align: center">Pris</th>
                        <th scope="col" style="text-align: center">Muligheder</th>
                    </tr>
                    </thead>
                    <c:forEach var="offer" items="${requestScope.listoffers}">
                    <tbody>
                    <tr>
                        <td scope="col" style="text-align: center">${offer.preorderid}</td>
                        <td scope="col" style="text-align: center">${offer.price}</td>
                        <td scope="col" style="text-align: center">
                            <button class="btn btn-block btn-success" type="submit">Accepter tilbud</button>
                        </td>
                    </tr>
                    </tbody>
                    </c:forEach>
                </table>
            </c:if>
            <!-- Tilbud END -->

            <!-- Ordre START -->
            <h3>Afgivet ordre</h3>
            <c:if test="${requestScope.order == null}">
                <p>Du har ikke nogle aftivet ordre</p>
            </c:if>

            <c:if test="${requestScope.order != null}">
                <p>Du har en afgivet ordre</p>
            </c:if>
            <!-- Ordre END -->
        </div>
    </div>

</main>

<!-- Include footer -->
<jsp:include page="includes/footer.jsp" flush="true"/>

</body>

</html>