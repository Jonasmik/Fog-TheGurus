<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="container">
    <div class="row">
        <div class="col">
            <h1>Salgsmedarbejder: ${sessionScope.user.name}</h1>
        </div>
    </div>

    <div class="row">
        <div class="col">

            <c:choose>

                <!-- Forespørgelser START -->
                <h3 style="padding-top: 20px">Forespørgelser</h3>
                <c:when test="${requestScope.preorders == null}">
                    <p>Der er ikke nogen preorders</p>
                </c:when>

                <c:when test="${requestScope.preorders != null}">
                    <p>Der findes en forespørgelse</p>
                </c:when>
                <!-- Forespørgelser END -->

                <!-- Tilbud START -->
                <h3>Aktive tilbud</h3>
                <c:when test="${requestScope.offers == null}">
                    <p>Der er ikke nogen aktive tilbud</p>
                </c:when>

                <c:when test="${requestScope.offers != null}">
                    <p>Der findes et tilbud</p>
                </c:when>
                <!-- Tilbud END -->

                <!-- Ordre START -->
                <h3>Afgivet ordre</h3>
                <c:when test="${requestScope.orders == null}">
                    <p>Der er ikke nogen afgivet ordre</p>
                </c:when>

                <c:when test="${requestScope.orders != null}">
                    <p>Der findes en afgivet ordre</p>
                </c:when>
                <!-- Ordre END -->

            </c:choose>

        </div>
    </div>
</div>