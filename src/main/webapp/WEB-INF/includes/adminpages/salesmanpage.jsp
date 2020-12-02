<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>


<div class="container">
    <div class="row">
        <div class="col">
            <h1>Salgsmedarbejder: ${sessionScope.user.name}</h1>
        </div>
    </div>

    <div class="row">
        <div class="col-lg-12">

            <!-- Forespørgelser START -->
            <h3 style="padding-top: 20px">Forespørgelser</h3>
            <div class="table-responsive">
                <table class="table table-bordered">
                    <thead>
                    <tr>
                        <th scope="col">Id</th>
                        <th scope="col">Kunde navn</th>
                        <th scope="col">Kunde e-mail</th>
                        <th scope="col">Kunde information</th>
                        <th scope="col">Evt. bemærkning</th>
                        <th scope="col">Tildel</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:set var="doublecount" value="0" scope="page"/>
                    <c:set var="alphabet" value="A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z"/>
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
                                <td>${customer.additional}</td>
                                <td>
                                    <button type="button" class="btn btn-outline-info"
                                            data-toggle="modal"
                                            data-target="#${alphabet.charAt(doublecount)}Modal">
                                        Carport
                                    </button>
                                </td>
                            </tr>
                            <div class="modal fade" id="${alphabet.charAt(doublecount)}Modal" tabindex="-1"
                                 aria-labelledby="${alphabet.charAt(doublecount)}ModalLabel"
                                 aria-hidden="true">
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h5 class="modal-title" id="exampleModalLabel">Carport
                                                nr. ${requestScope.unusedcarportsinpreorder.get(count).id}</h5>
                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                <span aria-hidden="true">&times;</span>
                                            </button>
                                        </div>
                                        <div class="modal-body">
                                            <p>Bredde: ${requestScope.unusedcarportsinpreorder.get(count).width}</p>
                                            <p>Længde: ${requestScope.unusedcarportsinpreorder.get(count).length}</p>
                                            <p>Tagtype/farve: ${requestScope.unusedcarportsinpreorder.get(count).roof}</p>
                                            <p>Tag vinkel: ${requestScope.unusedcarportsinpreorder.get(count).roofAngle} grader</p>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="submit" class="btn btn-outline-success">Tag som kunde</button>
                                            <button type="button" class="btn btn-outline-secondary" data-dismiss="modal">Luk
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <c:set var="doublecount" value="${doublecount + 2}" scope="page"/>
                        </form>
                        <c:set var="count" value="${count + 1}" scope="page"/>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <!-- Forespørgelser END -->


        </div>
    </div>

    <div class="row">
        <div class="col">
            <h3>Aktive tilbud</h3>
            ${requestScope.carport}
        </div>
    </div>

    <div class="row">
        <div class="col">
            <!-- Ordre START -->
            <h3>Afgivet ordre</h3>
        </div>
    </div>
</div>

