<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="nav-container py-3" style="background-color: #69696e">
    <nav class="navbar navbar-expand-md navbar-dark">

        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#admincustomerpage"
                aria-controls="admincustomerpage" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="admincustomerpage">

            <!-- Links on the left START -->
            <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
                <li class="nav-item">
                    <a class="nav-link my-navlinks" href="Main?target=redirect&destination=index">Hjem</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link my-navlinks" href="Main?target=redirect&destination=createorder">Bestil</a>
                </li>
            </ul>
        </div>
    </nav>
</div>