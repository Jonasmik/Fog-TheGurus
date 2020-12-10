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
                            <c:set var="doublecountactive" value="0" scope="page"/>
                            <c:set var="alphabetactivepreorder"
                                   value="A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z,a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z"/>
                            <c:set var="countactivepreorder" value="0" scope="page"/>
                            <c:forEach var="activecustomer"
                                       items="${requestScope.activepreordercustomers}">
                                <form action="Main" method="POST">
                                    <input type="hidden" name="target" value="showbompage">
                                    <input type="hidden" name="carportid" value="${requestScope.activepreordercarports.get(countactivepreorder).carport.id}">
                                    <input type="hidden" name="shedid" value="${requestScope.activepreordercarports.get(countactivepreorder).shed.id}">
                                    <tr>
                                        <th scope="row">${requestScope.activepreorder.get(countactivepreorder).id}</th>
                                        <td>${activecustomer.name}</td>
                                        <td>${activecustomer.email}</td>
                                        <td>By: ${activecustomer.city}, Post
                                            nr: ${activecustomer.zipcode},
                                            Addresse: ${activecustomer.adress}</td>
                                        <td>
                                            <button type="button" class="btn btn-outline-info"
                                                    data-toggle="modal"
                                                    data-target="#${alphabetactivepreorder.charAt(doublecountactive)}activepreorders">
                                                Carport
                                            </button>
                                        </td>
                                    </tr>
                                    <div class="modal fade"
                                         id="${alphabetactivepreorder.charAt(doublecountactive)}activepreorders"
                                         tabindex="-1"
                                         aria-labelledby="${alphabetactivepreorder.charAt(doublecountactive)}ModalLabel"
                                         aria-hidden="true">
                                        <div class="modal-dialog">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <h5 class="modal-title" id="activepreorders">
                                                        Carport
                                                        nr. ${requestScope.activepreordercarports.get(countactivepreorder).carport.id}</h5>
                                                    <button type="button" class="close"
                                                            data-dismiss="modal"
                                                            aria-label="Close">
                                                        <span aria-hidden="true">&times;</span>
                                                    </button>
                                                </div>
                                                <div class="modal-body">
                                                    <h5>Carport</h5>
                                                    <p>
                                                        Bredde: ${requestScope.activepreordercarports.get(countactivepreorder).carport.width}</p>
                                                    <p>
                                                        Længde: ${requestScope.activepreordercarports.get(countactivepreorder).carport.length}</p>
                                                    <p>
                                                        Tagtype/farve: ${requestScope.activepreordercarports.get(countactivepreorder).carport.roof}</p>
                                                    <p>Tag
                                                        vinkel: ${requestScope.activepreordercarports.get(countactivepreorder).carport.roofAngle}
                                                        grader</p>
                                                    <c:if test="${!activecustomer.additional.equals('')}">
                                                        <p>Evt.
                                                            bemærkning: ${activecustomer.additional}</p>
                                                    </c:if>
                                                    <c:if test="${requestScope.activepreordercarports.get(countactivepreorder).shed.length > 0}">
                                                        <h5>Skur</h5>
                                                        <p>Bredde: ${requestScope.activepreordercarports.get(countactivepreorder).shed.width}</p>
                                                        <p>Længde: ${requestScope.activepreordercarports.get(countactivepreorder).shed.length}</p>
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
                                    <c:set var="doublecountactive" value="${doublecountactive + 2}"
                                           scope="page"/>
                                </form>
                                <c:set var="countactivepreorder" value="${countactivepreorder + 1}"
                                       scope="page"/>
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
                            <c:set var="doublecount" value="0" scope="page"/>
                            <c:set var="alphabet"
                                   value="A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z,a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z"/>
                            <c:set var="count" value="0" scope="page"/>
                            <c:forEach var="customer" items="${requestScope.unusedcustomers}">
                                <form action="Main" method="POST">
                                    <input type="hidden" name="target" value="takepreorder">
                                    <input type="hidden" name="preorderid"
                                           value="${requestScope.unusedpreorders.get(count).id}">
                                    <tr>
                                        <th scope="row">${requestScope.unusedpreorders.get(count).id}</th>
                                        <td>${customer.name}</td>
                                        <td>${customer.email}</td>
                                        <td>By: ${customer.city}, Post nr: ${customer.zipcode},
                                            Addresse: ${customer.adress}</td>
                                        <td>
                                            <button type="button" class="btn btn-outline-info"
                                                    data-toggle="modal"
                                                    data-target="#${alphabet.charAt(doublecount)}Modal">
                                                Carport
                                            </button>
                                        </td>
                                    </tr>
                                    <div class="modal fade"
                                         id="${alphabet.charAt(doublecount)}Modal" tabindex="-1"
                                         aria-labelledby="${alphabet.charAt(doublecount)}ModalLabel"
                                         aria-hidden="true">
                                        <div class="modal-dialog">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <h5 class="modal-title" id="exampleModalLabel">
                                                        Carport
                                                        nr. ${requestScope.unusedcarportsinpreorder.get(count).carport.id}</h5>
                                                    <button type="button" class="close"
                                                            data-dismiss="modal"
                                                            aria-label="Close">
                                                        <span aria-hidden="true">&times;</span>
                                                    </button>
                                                </div>
                                                <div class="modal-body">
                                                    <p>
                                                        Bredde: ${requestScope.unusedcarportsinpreorder.get(count).carport.width}</p>
                                                    <p>
                                                        Længde: ${requestScope.unusedcarportsinpreorder.get(count).carport.length}</p>
                                                    <p>
                                                        Tagtype/farve: ${requestScope.unusedcarportsinpreorder.get(count).carport.roof}</p>
                                                    <p>Tag
                                                        vinkel: ${requestScope.unusedcarportsinpreorder.get(count).carport.roofAngle}
                                                        grader</p>
                                                    <c:if test="${!customer.additional.equals('')}">
                                                        <p>Evt.
                                                            bemærkning: ${customer.additional}</p>
                                                    </c:if>
                                                    <c:if test="${requestScope.unusedcarportsinpreorder.get(count).shed.length > 0}">
                                                        <h5>Skur</h5>
                                                        <p>Bredde: ${requestScope.unusedcarportsinpreorder.get(count).shed.width}</p>
                                                        <p>Længde: ${requestScope.unusedcarportsinpreorder.get(count).shed.length}</p>
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
                                    <c:set var="doublecount" value="${doublecount + 2}"
                                           scope="page"/>
                                </form>
                                <c:set var="count" value="${count + 1}" scope="page"/>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <!-- Inactive preorders END -->
                </div>
                <div class="tab-pane fade" id="v-pills-messages" role="tabpanel"
                     aria-labelledby="v-pills-messages-tab">
                    <h3 style="padding-top: 20px;">Materialer</h3>
                </div>
            </div>
        </div>
    </div>
</div>

