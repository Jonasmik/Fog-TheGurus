<%@page contentType="text/html" pageEncoding="UTF-8" %>

<style>
    .cookie-container {
        position: fixed;
        bottom: -100%;
        left: 0;
        right: 0;
        padding: 0 32px;
        background-color: #003d76;
        color: white;

        transition: 400ms;
    }

    .cookies.active {
        background-color: rgba(0, 0, 0, 0.5) !important;
        z-index: -999999;
        transition: 400ms;
    }

    .cookie-container.active {
        z-index: 999999;
        bottom: 0;
    }

    .cookie-btn-accept {
        padding: 12px 48px;
        margin-bottom: 16px;
    }
</style>

<!--
Css only works if its in this file???
-->

<div class="cookie-container">
    <div class="container">
        <div class="row">
            <div class="col">
                <h1 class="my-footerheader">Fog cookie policy</h1>
            </div>
        </div>

        <div class="row">
            <div class="col">
                <p>
                    På Fogs hjemmeside bruger vi cookies, ved at trykke på at "accepter" står du
                    inde for,
                    at have læst vores cookie politik og accepteret den.</p>
                <div class="btn-group dropup">
                    <a class="my-footerlinks" data-toggle="dropdown"
                       style="font-size: 22px; border-bottom: solid #50a8fd 1px"
                       aria-haspopup="true" aria-expanded="false" href="#">Se politik</a>
                    <div class="dropdown-menu text-muted"
                         style="width: 300px; padding: 30px">
                        <h5>Cookie politik</h5>
                        <p>
                            På vores hjemmeside bruger vi cookies, de bliver brugt for at du
                            som bruger får en bedere oplevelse på hjemmesiden.</p>
                        <hr>
                        <p>Vi bruger sessions til at: <br><br>
                            1. Vide hvilken bruger der er logget ind.
                            <br>2. Gemme dine valgte carport længder.
                            <br>3. Gemme dit carport billed.
                        </p>
                    </div>
                </div>

                <br><br>

                <button class="cookie-btn-accept btn btn-primary">Accepter</button>

            </div>
        </div>
    </div>
</div>

<script>
    const cookieContainer = document.querySelector(".cookie-container");
    const cookieButton = document.querySelector(".cookie-btn-accept");
    const cookieActive = document.querySelector(".cookies");

    //When they click the cookieButton
    cookieButton.addEventListener("click", () => {
        //Removes the .active from the cookie-container
        cookieContainer.classList.remove("active");
        cookieActive.classList.remove("active");
        //Adds it to the localStorage
        localStorage.setItem('cookieBannerDisplayed', 'true');
    });

    //adds the .active to the cookieContainer after 2 seconds.
    setTimeout(() => {
        if (!localStorage["cookieBannerDisplayed"]) {
            cookieContainer.classList.add("active");
            cookieActive.classList.add("active");
        }

    }, 2000);
</script>