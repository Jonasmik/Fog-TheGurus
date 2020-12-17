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

    <div class="row">
        <div class="col">
            <h2>Carport nr. ${requestScope.carport.id}</h2>
        </div>
    </div>

    <div class="row" style="padding-top: 50px">
        <div class="col">
            ${requestScope.carportpicture}
        </div>
        <div class="col">
            <h4>Carport</h4>
            <p>Bredde :${requestScope.carport.width}</p>
            <p>Længde: ${requestScope.carport.length}</p>
            <p>Tagtype: ${requestScope.carport.roof}</p>

            <c:if test="${requestScope.carport.roofAngle > 0}">
                <p>Tag vinkel: ${requestScope.carport.roofAngle}</p>
            </c:if>

            <c:if test="${requestScope.shed.length > 0}">
                <h4>Skur</h4>
                <p>Bredde: ${requestScope.shed.width}</p>
                <p>Længde: ${requestScope.shed.length}</p>
            </c:if>
        </div>
    </div>

    <div class="row">
        <div class="col">
            <div style="text-align: end;">
                <p><strong>Total pris: ${requestScope.offer.price}</strong></p>
            </div>
        </div>
    </div>

    <div class="row">
        <div class="col">
            <div style="text-align: right">
                <button class="btn btn-block w-25 btn-primary">Køb</button>
            </div>
        </div>
    </div>

</main>

<!-- Include footer -->
<jsp:include page="includes/footer.jsp" flush="true"/>
</body>
</html>
