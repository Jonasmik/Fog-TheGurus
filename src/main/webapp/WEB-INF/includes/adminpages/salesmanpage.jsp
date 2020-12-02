<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<div class="container">
    <div class="row">
        <div class="col">
            <h1>Salgsmedarbejder: ${sessionScope.user.name}</h1>
        </div>
    </div>

    <div class="row">
        <div class="col">

            <!-- Forespørgelser START -->
            <h3 style="padding-top: 20px">Forespørgelser</h3>
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
                <c:set var="count" value="0" scope="page"/>
                <c:forEach var="customer" items="${requestScope.unusedcustomers}">
                    <form action="Main" method="POST">
                        <input type="hidden" name="target" value="takepreorder">
                        <input type="hidden" name="preorderid" value="${requestScope.unusedpreorders.get(count).id}">
                        <tr>
                            <th scope="row">${requestScope.unusedpreorders.get(count).id}</th>
                            <td>${customer.name}</td>
                            <td>${customer.email}</td>
                            <td>By: ${customer.city}, Post nr: ${customer.zipcode},
                                Addresse: ${customer.adress}</td>
                            <td>${customer.additional}</td>
                            <td>
                                <button type="submit" class="btn btn-outline-success">Tildel</button>
                            </td>
                        </tr>
                    </form>
                    <c:set var="count" value="${count + 1}" scope="page"/>
                </c:forEach>
                </tbody>
            </table>
            <!-- Forespørgelser END -->

        </div>
    </div>

    <div class="row">
        <div class="col">
            <h3>Aktive tilbud</h3>
        </div>
    </div>

    <div class="row">
        <div class="col">
            <!-- Ordre START -->
            <h3>Afgivet ordre</h3>
        </div>
    </div>
</div>

