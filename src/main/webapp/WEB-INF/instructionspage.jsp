<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>

<!-- Include header -->
<jsp:include page="includes/header.jsp" flush="true"/>

<!-- Insert title -->
<title>Fog: Instruktioner</title>

<body class="d-flex flex-column h-100 bg-light">

<!-- Include navbar -->
<jsp:include page="includes/navs/navbar.jsp" flush="true"/>

<main role="main" class="container flex-shrink-0 text-center">
    <jsp:include page="includes/modals/userlogoutmodal.jsp" flush="true"/>

    <div class="container border-secondary" style="width: 100%;">
        <div class="row">
            <div class="col-md-12 col-sm-12">
                <h1>Carport</h1>
                <div class="alert alert-info border-secondary">
                    <div style="text-align: center">
                        ${sessionScope.carportpicture}
                    </div>
                </div>
            </div>
        </div>

        <div class="row" style="padding-top: 25px;">
            <div class="col-md-12 col-sm-12">
                <h1>Stykliste</h1>
                <p style="font-size: 20px;">Husk at kontrollerer materiale listen inden du går i
                    gang.</p>

                <div class="table-responsive">
                    <table class="table table-sm table-bordered" id="myTable">
                        <thead>
                        <tr>
                            <th scope="col" style="text-align: center">Beskrivelse</th>
                            <th scope="col" style="text-align: center">Længde</th>
                            <th scope="col" style="text-align: center">Antal</th>
                            <th scope="col" style="text-align: center">Enhed</th>
                            <th scope="col" style="text-align: center">Beskrivelse</th>
                            <c:if test="${sessionScope.user.role.equals('salesman')}">
                                <th scope="col" style="text-align: center">Pris</th>
                            </c:if>
                        </tr>
                        </thead>

                        <thead>
                        <tr>
                            <th scope="col" style="text-align: center;">Træ & Tagplader</th>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <c:if test="${sessionScope.user.role.equals('salesman')}">
                                <td></td>
                            </c:if>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="bom" items="${sessionScope.carportbom.bomItemList}">
                            <tr style="font-size: 14px">
                                <td style="text-align: start;">${bom.material.width}x${bom.material.height}mm. ${bom.material.description}</td>
                                <td style="text-align: center">${bom.length}</td>
                                <td style="text-align: center">${bom.amount}</td>
                                <td style="text-align: center">${bom.unit}</td>
                                <td style="text-align: start">${bom.description}</td>
                                <c:if test="${sessionScope.user.role.equals('salesman')}">
                                    <td style="text-align: center;">${bom.price} kr.</td>
                                </c:if>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <c:if test="${sessionScope.user.role.equals('salesman')}">
        <div class="row" style="margin-top: 20px;">
            <div class="col-md-9">

            </div>
            <div class="col-md-3">
                <table class="table table-sm table-bordered">
                    <thead>
                    <tr>
                        <th scope="col" style="text-align: center">Kost pris</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td scope="col" style="text-align: center">${sessionScope.cost} kr.</td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
    </c:if>

    <c:if test="${sessionScope.user.role.equals('salesman')}">
        <div class="container">
            <form action="Main" method="POST">
                <input type="hidden" name="target" value="unknowncontent">
                <input type="hidden" name="cost" value="${sessionScope.cost}">
                <div class="row">
                    <div class="col-md-9">
                    </div>
                    <div class="col-md-3">
                        <label style="text-align: start" for="angledNoUserCity">Pris</label>
                        <input type="text" id="angledNoUserCity"
                               class="form-control"
                               name="offerprice"
                               value="${sessionScope.startprice}"
                               required="">
                        <span style="opacity: 0.8; font-size: 13px; font-weight: 500;">Start pris er 20% oven i kost pris</span>
                    </div>
                </div>
                <div class="row" style="margin-top: 10px;">
                    <div class="col-md-9">
                    </div>
                    <div class="col-md-3">
                        <button type="submit" class="btn btn-block btn-primary">Giv tilbud</button>
                    </div>
                </div>
            </form>
        </div>
    </c:if>

</main>

<!-- Include footer -->
<jsp:include page="includes/footer.jsp" flush="true"/>
</body>
</html>