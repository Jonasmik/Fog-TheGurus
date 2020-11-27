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

<jsp:include page="WEB-INF/includes/modals/cookies.jsp" flush="true"/>

<div class="cookies">
    <!-- Include navbar -->
    <jsp:include page="WEB-INF/includes/navs/navbar.jsp" flush="true"/>

    <main role="main" class="container flex-shrink-0">
        <c:if test="${sessionScope.user == null}">
            <jsp:include page="WEB-INF/includes/modals/userloginmodal.jsp" flush="true"/>
        </c:if>
        <c:if test="${sessionScope.user != null}">
            <jsp:include page="WEB-INF/includes/modals/userlogoutmodal.jsp" flush="true"/>
        </c:if>

        <div class="row">
            <div class="col">
                <h1>Velkommen til Fog Carport handel</h1>
                <p>Her i carport handlen, vil du have mulighed for at bygge din helt egen carport.
                    <br>
                    Ud fra dine egne mål vil du få en carport udleveret, af vores bedste folk.</p>
            </div>
        </div>

        <div class="row">
            <div class="col">
                <img src="images/carport1.jpeg" alt="Carport1">
            </div>
        </div>

        <div class="row">
            <div class="col">
                <p>Når du handler med NOT Fog kan du være helt sikker, at du bliver glad. Vi er meget glade for tæt
                    samarbejde med dig, så vi kan blive ved med at komme med tilbud frem og tilbage.</p>
                <h2>Købsgarenti</h2>
                <p>Hvis du nu alligevel ender med ikke at være glad for din carport, har du en købsgarenti på 50.000 kroner.</p>
            </div>
        </div>

    </main>

    <!-- Include footer -->
    <jsp:include page="WEB-INF/includes/footer.jsp" flush="true"/>
</div>

</body>

</html>