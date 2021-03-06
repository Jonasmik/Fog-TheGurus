<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>

<!-- Include header -->
<jsp:include page="includes/header.jsp" flush="true"/>

<!-- Insert title -->
<title>Fog: Error</title>

<body class="d-flex flex-column h-100 text-center bg-light">

<!-- Include navbar -->
<jsp:include page="includes/navs/navbar.jsp" flush="true"/>

<main role="main" class="container flex-shrink-0">
    <c:if test="${sessionScope.user == null}">
        <jsp:include page="includes/modals/userloginmodal.jsp" flush="true"/>
    </c:if>
    <!-- Called if an error happens in the system -->
    <div class="alert alert-danger border-danger">
        <h1>Fejl</h1>
        <c:if test="${requestScope.error != null}">
            <h4>${requestScope.error}</h4>
        </c:if>
    </div>

    <!-- Button to return to index -->
    <form action="Main" method="get">
        <input type="hidden" name="target" value="redirect">
        <input type="hidden" name="destination" value="index">
        <button type="submit" class="btn btn-secondary">Tilbage til hjemmesiden</button>
    </form>

</main>

<!-- Include footer -->
<jsp:include page="includes/footer.jsp" flush="true"/>
</body>
</html>