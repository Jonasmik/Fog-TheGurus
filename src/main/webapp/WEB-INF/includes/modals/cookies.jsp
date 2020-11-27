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
                    På Fogs hjemmeside bruger vi cookies, ved at trykke på at "accepter" står du inde for,
                    at have læst vores <a class="my-footerlinks"
                                          style="font-size: 22px; border-bottom: solid #50a8fd 1px" href="#">cookie
                    politik</a> og accepteret den.
                </p>

                <button class="cookie-btn-accept btn btn-primary">Accepter</button>

            </div>
        </div>
    </div>
</div>