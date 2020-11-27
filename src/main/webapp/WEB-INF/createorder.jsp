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
    <c:if test="${sessionScope.user == null}">
        <jsp:include page="includes/modals/userloginmodal.jsp" flush="true"/>
    </c:if>
    <c:if test="${sessionScope.user != null}">
        <jsp:include page="includes/modals/userlogoutmodal.jsp" flush="true"/>
    </c:if>

    <h1 style="padding-left: 18px">Byg din egen Carport</h1>

    <jsp:include page="includes/chooseorder.jsp" flush="true"/>

</main>

<!-- Include footer -->
<jsp:include page="includes/footer.jsp" flush="true"/>

</body>

</html>