<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>

<!-- Include header -->
<jsp:include page="includes/header.jsp" flush="true"/>

<!-- Insert title -->
<title>Fog: Kvitering</title>

<body class="d-flex flex-column h-100 bg-light">

<!-- Include navbar -->
<jsp:include page="includes/navs/navbar.jsp" flush="true"/>

<main role="main" class="container flex-shrink-0">
    <jsp:include page="includes/modals/userlogoutmodal.jsp" flush="true"/>
    <div class="container">
        <div class="row">
            <div class="col-md-8">
                <h1 style="margin-bottom: 20px; margin-top: 20px">Din Kvitering for Ordre
                    nr. ${requestScope.order.order.id}</h1>
                <h3>Ansvarlige salgsmedarbejder</h3>
                <p>Navn: ${requestScope.order.salesmanUser.name}
                    <br>Kontakt e-mail: ${requestScope.order.salesmanUser.email}</p>
            </div>
            <div class="col-md-4">
                <form>
                    <input type="hidden" name="target" value="generatebom">
                    <input type="hidden" name="carportid"
                           value="${requestScope.order.carport.id}">
                    <input type="hidden" name="preorderid"
                           value="${requestScope.order.preOrder.id}">
                    <button style="margin-top: 145px" type="submit" class="btn btn-block btn-primary">Se stykliste</button>
                </form>
            </div>
        </div>

        <hr>

        <div class="row">
            <div class="col-md-4">
                <h3>Ordre oversigt</h3>
                <h5>Kunde</h5>
                <p>${requestScope.order.customer.name}
                    <br>${requestScope.order.customer.email}
                    <br>By: ${requestScope.order.customer.city} -
                    Adresse: ${requestScope.order.customer.adress} ${requestScope.order.customer.zipcode}</p>
                </p>
            </div>
            <div class="col-md-4" style="margin-top: 35px">
                <h5>Carport</h5>
                <p>Bredde: ${requestScope.order.carport.width}
                    <br>Længde: ${requestScope.order.carport.length}
                    <br>Tagtype: ${requestScope.order.carport.roof}
                    <c:if test="${requestScope.order.carport.roofAngle > 0}">
                    <br>${requestScope.order.carport.roofAngle}
                    </c:if>
            </div>
            <div class="col-md-4" style="margin-top: 35px">
                <c:if test="${requestScope.order.shed.length > 0}">
                    <h5>Skur</h5>
                    <p>
                        Bredde: ${requestScope.order.shed.width}
                        <br>Længde: ${requestScope.order.shed.length}
                    </p>
                </c:if>
            </div>
        </div>

        <hr style="padding-bottom: 5px; padding-top: 5px">

        <div class="row">
            <div class="col">
                <p style="text-align: center"><strong>Total pris: ${requestScope.order.offer.price} kr.</strong></p>
            </div>
        </div>

        <hr style="margin-bottom: 20px">

        <div class="row">
            <div class="col">
                <p>Kontakt din salgsmedarbejder, hvis du har spørgsmål til ordren</p>
            </div>
        </div>
    </div>
</main>

<!-- Include footer -->
<jsp:include page="includes/footer.jsp" flush="true"/>
</body>
</html>
