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
    if(localStorage["cookieBannerDisplayed"]) {
        cookieContainer.classList.remove("active");
        cookieActive.classList.remove("active");
    } else {
        cookieContainer.classList.add("active");
        cookieActive.classList.add("active");
    }

}, 2000);