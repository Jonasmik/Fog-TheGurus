<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="nav-container py-3" style="background-color: #0D2069">
    <nav class="navbar navbar-expand-md navbar-dark">

        <a href="#"><img class="foglogoheader" src="${pageContext.request.contextPath}/images/fogheaderlogo3.png" alt="FogLogo"/></a>

        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo02"
                aria-controls="navbarTogglerDemo02" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarTogglerDemo02">
            <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
                <li class="nav-item">
                    <a class="nav-link my-navlinks" href="#">Hjem</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link my-navlinks" href="Main?target=redirect&destination=createorder">Bestil</a>
                </li>
            </ul>
            <ul class="navbar-nav ml-auto">
                <li class="nav-item">
                    <c:if test="${sessionScope.user == null}">
                        <a class="nav-link my-navlinks" href="#" data-toggle="modal" data-target="#loginModal"><strong>Log ind</strong></a>
                    </c:if>
                </li>
            </ul>
        </div>
    </nav>
</div>