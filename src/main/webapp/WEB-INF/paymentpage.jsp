<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>

<!-- Include header -->
<jsp:include page="includes/header.jsp" flush="true"/>

<!-- Insert title -->
<title>Fog: Paymentpage</title>

<body class="d-flex flex-column h-100 bg-light">

<!-- Include navbar -->
<jsp:include page="includes/navs/navbar.jsp" flush="true"/>

<main role="main" class="container flex-shrink-0">
    <jsp:include page="includes/modals/userlogoutmodal.jsp" flush="true"/>
    <div class="row" style="padding-top: 50px">
        <div class="col">
            ${requestScope.carportpicture}
        </div>
        <div class="col">
            <h1 style="padding-top: 20px; padding-bottom: 10px;">Tilbud nr. ${requestScope.offer.id}</h1>
            <h4>Carport</h4>
            <table class="table table-bordered table-sm">
                <tbody>
                    <tr>
                        <td>Bredde</td>
                        <td>${requestScope.carport.width} cm.</td>
                    </tr>
                    <tr>
                        <td>Længde</td>
                        <td>${requestScope.carport.length} cm.</td>
                    </tr>
                    <tr>
                        <td>Tagtype</td>
                        <td>${requestScope.carport.roof}</td>
                    </tr>
                    <c:if test="${requestScope.carport.roofAngle > 0}">
                        <tr>
                            <td>Tag vinkel</td>
                            <td>${requestScope.carport.roofAngle} grader</td>
                        </tr>
                    </c:if>
                </tbody>
            </table>

            <c:if test="${requestScope.shed.length > 0}">
                <h4>Skur</h4>
                <table class="table table-bordered table-sm">
                    <tbody>
                        <tr>
                            <td>Bredde</td>
                            <td>${requestScope.shed.width} cm.</td>
                        </tr>
                        <tr>
                            <td>Længde</td>
                            <td>${requestScope.shed.length} cm.</td>
                        </tr>
                    </tbody>
                </table>
            </c:if>


        </div>
    </div>

    <div class="row">
        <div class="col">
            <div style="text-align: end;">
                <p><strong>Total pris: ${requestScope.offer.price} kr.</strong></p>
            </div>
        </div>
    </div>

    <div class="row">
        <div class="col-md-9">
        </div>
        <div class="col-md-3">
            <form action="Main" method="POST">
                <input type="hidden" name="target" value="acceptoffer">
                <input type="hidden" name="offerid" value="${requestScope.offer.id}">
                <input type="hidden" name="preorderid" value="${requestScope.offer.preorderid}">

                <button class="btn btn-block btn-primary">Køb</button>
            </form>
        </div>
    </div>

</main>

<!-- Include footer -->
<jsp:include page="includes/footer.jsp" flush="true"/>
</body>
</html>
