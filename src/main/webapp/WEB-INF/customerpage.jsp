<%@ page import="com.yourcompany.api.Template" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>

<!-- Include header -->
<jsp:include page="includes/header.jsp" flush="true"/>

<!-- Insert title -->
<title>Fog: Bestilling</title>

<body class="d-flex flex-column h-100 bg-light">

<!-- Include navbar -->
<jsp:include page="includes/navbar.jsp" flush="true"/>

<main role="main" class="container flex-shrink-0">
    <jsp:include page="includes/userlogoutmodal.jsp" flush="true"/>

    <h1>Profil side</h1>

    <h3 style="padding-top: 20px">Forespørgelser</h3>
    <c:if test="${requestScope.preorder == null}">
        <p>Du har ikke nogle forespørgelser</p>
    </c:if>

    <h3>Aktive tilbud</h3>
    <c:if test="${requestScope.offer == null}">
        <p>Du har ikke nogle aktive tilbud</p>
    </c:if>

    <h3>Afgivet ordre</h3>
    <c:if test="${requestScope.order == null}">
        <p>Du har ikke nogle købte ordre</p>
    </c:if>


</main>

<!-- Include footer -->
<jsp:include page="includes/footer.jsp" flush="true"/>

</body>

</html>