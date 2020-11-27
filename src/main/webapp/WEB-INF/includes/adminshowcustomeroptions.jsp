<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="nav-container py-2" id="showUserMenu" style="background-color: rgba(80,80,81, 0.5); display: none">
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

<script>
    function showUserMenu() {
        const x = document.getElementById("showUserMenu");

        if (x.style.display === "none") {
            x.style.display = "block";
        } else {
            x.style.display = "none";
        }
    }
</script>

