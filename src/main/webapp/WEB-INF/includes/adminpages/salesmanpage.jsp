<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>

<div class="container">
    <div class="row">
        <div class="col">
            <h1>Salgsmedarbejder: ${sessionScope.user.name}</h1>
        </div>
    </div>

    <div class="row d-lg-flex">
        <div class="col-lg-3 col-md-4">
            <div style="padding-top: 30px" class="nav flex-column nav-pills" id="v-pills-tab"
                 role="tablist"
                 aria-orientation="vertical">
                <a class="nav-link active" id="v-pills-home-tab" data-toggle="pill"
                   href="#v-pills-home" role="tab"
                   aria-controls="v-pills-home" aria-selected="true">Din profil</a>
                <a class="nav-link" id="v-pills-profile-tab" data-toggle="pill"
                   href="#v-pills-profile" role="tab"
                   aria-controls="v-pills-profile" aria-selected="false">Inaktive forespørgelser</a>
                <a class="nav-link" id="v-pills-messages-tab" data-toggle="pill"
                   href="#v-pills-messages" role="tab"
                   aria-controls="v-pills-messages" aria-selected="false">Materialer</a>
            </div>

        </div>
        <div class="col-lg-9 col-md-8">
            <div class="tab-content" id="v-pills-tabContent">
                <div class="tab-pane fade show active" id="v-pills-home" role="tabpanel"
                     aria-labelledby="v-pills-home-tab">
                    <h3 style="padding-top: 20px">Din profil</h3>

                    <!-- Active salesman preorders START -->
                    <h4 style="padding-top: 20px">Dine forespørgelser</h4>
                    <div class="table-responsive">
                        <table class="table table-bordered">
                            <thead>
                            <tr>
                                <th scope="col">Id</th>
                                <th scope="col">Navn</th>
                                <th scope="col">E-mail</th>
                                <th scope="col">Addresse</th>
                                <th scope="col">Vare</th>
                            </tr>
                            </thead>
                            <tbody>

                            <!-- doublecountactive = used to jump to the next letter in alphabetactivepreorder
                                 countactivepreorder = used to know where in every list we are
                             -->
                            <c:set var="countactive" value="0" scope="page"/>
                            <c:forEach var="activepreorder"
                                       items="${requestScope.activepreorder}">
                                <form action="Main" method="POST">
                                    <input type="hidden" name="target" value="generatebom">
                                    <input type="hidden" name="carportid"
                                           value="${activepreorder.carport.id}">
                                    <input type="hidden" name="preorderid"
                                           value="${activepreorder.preOrder.id}">
                                    <tr>
                                        <th scope="row">${activepreorder.preOrder.id}</th>
                                        <td>${activepreorder.customer.name}</td>
                                        <td>${activepreorder.customer.email}</td>
                                        <td>By: ${activepreorder.customer.city}, Post
                                            nr: ${activepreorder.customer.zipcode},
                                            Addresse: ${activepreorder.customer.adress}</td>
                                        <td>
                                            <button type="button" class="btn btn-outline-info"
                                                    data-toggle="modal"
                                                    data-target="#activepreorders${countactive}">
                                                Carport
                                            </button>
                                        </td>
                                    </tr>
                                    <div class="modal fade"
                                         id="activepreorders${countactive}"
                                         tabindex="-1"
                                         aria-labelledby="activepreorders${countactive}"
                                         aria-hidden="true">
                                        <div class="modal-dialog">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <h5 class="modal-title" id="activepreorders">
                                                        Carport
                                                        nr. ${activepreorder.carport.id}</h5>
                                                    <button type="button" class="close"
                                                            data-dismiss="modal"
                                                            aria-label="Close">
                                                        <span aria-hidden="true">&times;</span>
                                                    </button>
                                                </div>
                                                <div class="modal-body">
                                                    <h5>Carport</h5>
                                                    <p>
                                                        Bredde: ${activepreorder.carport.width}</p>
                                                    <p>
                                                        Længde: ${activepreorder.carport.length}</p>
                                                    <p>
                                                        Tagtype/farve: ${activepreorder.carport.roof}</p>
                                                    <c:if test="${activepreorder.carport.roofAngle > 0}">
                                                        <p>Tag
                                                            vinkel: ${activepreorder.carport.roofAngle}
                                                            grader</p>
                                                    </c:if>
                                                    <c:if test="${!activepreorder.customer.additional.equals('')}">
                                                        <p>Evt.
                                                            bemærkning: ${activepreorder.customer.additional}</p>
                                                    </c:if>
                                                    <c:if test="${activepreorder.shed.length > 0}">
                                                        <h5>Skur</h5>
                                                        <p>Bredde: ${activepreorder.shed.width}</p>
                                                        <p>Længde: ${activepreorder.shed.length}</p>
                                                    </c:if>
                                                </div>
                                                <div class="modal-footer">
                                                    <button type="button"
                                                            class="btn btn-outline-secondary"
                                                            data-dismiss="modal">Luk
                                                    </button>
                                                    <button type="submit"
                                                            class="btn btn-outline-primary">
                                                        Se forespørgelse
                                                    </button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <c:set var="countactive" value="${countactive + 1}"
                                           scope="page"/>
                                </form>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <!-- Active salesman preorders END -->
                </div>
                <div class="tab-pane fade" id="v-pills-profile" role="tabpanel"
                     aria-labelledby="v-pills-profile-tab">

                    <!-- Inaktive preorder START -->
                    <h3 style="padding-top: 20px">Forespørgelser</h3>
                    <div class="table-responsive">
                        <table class="table table-bordered">
                            <thead>
                            <tr>
                                <th scope="col">Id</th>
                                <th scope="col">Navn</th>
                                <th scope="col">E-mail</th>
                                <th scope="col">Addresse</th>
                                <th scope="col">Vare</th>
                            </tr>
                            </thead>
                            <tbody>

                            <!-- doublecount = used to jump to the next letter in alphabet
                                 count  = used to know where in every list we are
                             -->
                            <c:set var="intactivecount" value="0" scope="page"/>
                            <c:forEach var="unusedpreorder" items="${requestScope.unusedpreorders}">
                                <form action="Main" method="POST">
                                    <input type="hidden" name="target" value="takepreorder">
                                    <input type="hidden" name="preorderid"
                                           value="${unusedpreorder.preOrder.id}">
                                    <tr>
                                        <th scope="row">${unusedpreorder.preOrder.id}</th>
                                        <td>${unusedpreorder.customer.name}</td>
                                        <td>${unusedpreorder.customer.email}</td>
                                        <td>By: ${unusedpreorder.customer.city}, Post
                                            nr: ${unusedpreorder.customer.zipcode},
                                            Addresse: ${unusedpreorder.customer.adress}</td>
                                        <td>
                                            <button type="button" class="btn btn-outline-info"
                                                    data-toggle="modal"
                                                    data-target="#inactivepreorder${intactivecount}">
                                                Carport
                                            </button>
                                        </td>
                                    </tr>
                                    <div class="modal fade"
                                         id="inactivepreorder${intactivecount}" tabindex="-1"
                                         aria-labelledby="inactivepreorder${intactivecount}"
                                         aria-hidden="true">
                                        <div class="modal-dialog">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <h5 class="modal-title" id="exampleModalLabel">
                                                        Carport
                                                        nr. ${unusedpreorder.carport.id}</h5>
                                                    <button type="button" class="close"
                                                            data-dismiss="modal"
                                                            aria-label="Close">
                                                        <span aria-hidden="true">&times;</span>
                                                    </button>
                                                </div>
                                                <div class="modal-body">
                                                    <p>
                                                        Bredde: ${unusedpreorder.carport.width}</p>
                                                    <p>
                                                        Længde: ${unusedpreorder.carport.length}</p>
                                                    <p>
                                                        Tagtype/farve: ${unusedpreorder.carport.roof}</p>
                                                    <c:if test="${unusedpreorder.carport.roofAngle > 0}">
                                                        <p>Tag
                                                            vinkel: ${unusedpreorder.carport.roofAngle}
                                                            grader</p>
                                                    </c:if>
                                                    <c:if test="${!unusedpreorder.customer.additional.equals('')}">
                                                        <p>Evt.
                                                            bemærkning: ${unusedpreorder.customer.additional}</p>
                                                    </c:if>
                                                    <c:if test="${unusedpreorder.shed.length > 0}">
                                                        <h5>Skur</h5>
                                                        <p>Bredde: ${unusedpreorder.shed.width}</p>
                                                        <p>Længde: ${unusedpreorder.shed.length}</p>
                                                    </c:if>
                                                </div>
                                                <div class="modal-footer">
                                                    <button type="button"
                                                            class="btn btn-outline-secondary"
                                                            data-dismiss="modal">Luk
                                                    </button>
                                                    <button type="submit"
                                                            class="btn btn-outline-primary">Tag som
                                                        kunde
                                                    </button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <c:set var="inactivecount" value="${intactivecount + 1}"
                                           scope="page"/>
                                </form>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <!-- Inactive preorders END -->
                </div>
                <div class="tab-pane fade" id="v-pills-messages" role="tabpanel"
                     aria-labelledby="v-pills-messages-tab">
                    <h3 style="padding-top: 20px;">Materialer</h3>
                    <table class="table table-bordered">
                        <thead>
                        <tr>
                            <th scope="col">Navn</th>
                            <th scope="col">Meter pris kr.</th>
                            <th scope="col">Rediger</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="material" items="${requestScope.materials}">
                            <form action="Main" method="POST">
                                <input type="hidden" name="target" value="updatematerial">
                                <input type="hidden" name="materialid" value="${material.id}">
                                <tr>
                                    <th scope="row">${material.name}</th>
                                    <td>
                                        <input name="newprice" value="${material.price}"
                                               class="form-control"/>
                                    </td>
                                    <td>
                                        <button type="submit"
                                                class="btn btn-block btn-outline-success">
                                            Gem <i class="fas fa-check"></i>
                                        </button>
                                    </td>
                                </tr>
                            </form>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>

