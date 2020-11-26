<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="nav-container py-3" style="background-color: #0D2069">
    <nav class="navbar navbar-expand-md navbar-dark">

        <a href="Main?target=redirect&destination=index"><img class="foglogoheader" src="${pageContext.request.contextPath}/images/fogheaderlogo3.png"
                         alt="FogLogo"/></a>

        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo02"
                aria-controls="navbarTogglerDemo02" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarTogglerDemo02">

            <!-- Links on the left START -->
            <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
                <c:choose>

                    <c:when test="${sessionScope.user.role.equals('customer') || sessionScope.user == null}">
                        <li class="nav-item">
                            <a class="nav-link my-navlinks" href="Main?target=redirect&destination=index">Hjem</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link my-navlinks" href="Main?target=redirect&destination=createorder">Bestil</a>
                        </li>
                    </c:when>

                    <c:when test="${sessionScope.user.role.equals('salesman')}">
                        <li class="nav-item">
                            <a class="nav-link my-navlinks" href="Main?target=redirect&destination=adminpage">Admin side</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link my-navlinks" href="Main?target=redirect&destination=createorder">Se kunde valgmuligheder</a>
                        </li>
                    </c:when>

                </c:choose>
            </ul>
            <!-- Links on the left END -->

            <!-- Links on the right START -->
            <ul class="navbar-nav ml-auto">
                <c:choose>

                    <c:when test="${sessionScope.user.role.equals('customer')}">
                        <li class="nav-item">
                            <a class="nav-link my-navlinks"
                               href="Main?target=redirect&destination=customerpage">${sessionScope.user.name}</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link my-navlinks" href="#" data-toggle="modal"
                               data-target="#logoutModal"><strong>Log
                                ud</strong></a>
                        </li>
                    </c:when>

                    <c:when test="${sessionScope.user.role.equals('salesman')}">
                        <li class="nav-item">
                            <a class="nav-link my-navlinks" href="#" data-toggle="modal"
                               data-target="#logoutModal"><strong>Log
                                ud</strong></a>
                        </li>
                    </c:when>

                    <c:when test="${sessionScope.user == null}">
                        <li class="nav-item">
                            <a class="nav-link my-navlinks" href="#" data-toggle="modal"
                               data-target="#loginModal"><strong>Log
                                ind</strong></a>
                        </li>
                    </c:when>

                </c:choose>
            </ul>
            <!-- Links on the right END -->
        </div>
    </nav>
</div>