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
    <div class="container">
        <div class="row">
            <div class="col">
                <h1 style="margin-bottom: 20px; margin-top: 20px">Din Kvitering for Ordre nr. ${requestScope.order.order.id}</h1>
                <h3>Ansvarlige salgsmedarbejder</h3>
                <p>Navn: ${requestScope.order.salesmanUser.name}
                <br>Kontakt e-mail: ${requestScope.order.salesmanUser.email}</p>
                <hr style="margin-top: 30px">
            </div>
        </div>
        <div class="row">
            <div class="col">
                <h3>Ordre oversigt</h3>
                <p>Navn: ${requestScope.order.customer.name}
                <br>Email: ${requestScope.order.customer.email}</p>
            </div>
        </div>
    </div>
</main>

<!-- Include footer -->
<jsp:include page="includes/footer.jsp" flush="true"/>
</body>
</html>
