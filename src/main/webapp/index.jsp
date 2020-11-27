<%@ page import="com.yourcompany.api.Fog" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>

<!-- Include header -->
<jsp:include page="WEB-INF/includes/header.jsp" flush="true"/>

<!-- Insert title -->

<c:choose>
    <c:when test="${sessionScope.user == null}">
        <title>Fog: Hjem</title>
    </c:when>
    <c:when test="${sessionScope.user != null}">
        <title>Fog: Kundeside</title>
    </c:when>
</c:choose>


<%
    if (request.getServletContext().getAttribute("version") == null) {
        request.getServletContext().setAttribute("version", Fog.getVERSION());
    }
%>

<body class="d-flex flex-column h-100 bg-light">

<!-- Include navbar -->
<jsp:include page="WEB-INF/includes/navbar.jsp" flush="true"/>

<main role="main" class="container flex-shrink-0">
    <c:if test="${sessionScope.user == null}">
        <jsp:include page="WEB-INF/includes/modals/userloginmodal.jsp" flush="true"/>
    </c:if>
    <c:if test="${sessionScope.user != null}">
        <jsp:include page="WEB-INF/includes/modals/userlogoutmodal.jsp" flush="true"/>
    </c:if>

    <h1>Fog Carport </h1>
</main>

<!-- Include footer -->
<jsp:include page="WEB-INF/includes/footer.jsp" flush="true"/>

</body>

</html>