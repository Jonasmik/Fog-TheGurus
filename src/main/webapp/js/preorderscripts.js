function showObliqueRoofOptions() {
    const x = document.getElementById("myFirstExtraRoom");
    const width = document.getElementById("redskabsRumBredde");
    const height = document.getElementById("redskabsRumLængde")

    if (x.style.display === "none") {
        x.style.display = "block";
        width.disabled = false;
        height.disabled = false;
    } else {
        x.style.display = "none";
        width.disabled = true;
        width.disabled = true;
    }
}

function showFlatRoofOptions() {
    const x = document.getElementById("mySecondExtraRoom");
    const width = document.getElementById("flatRedskabsRumBredde");
    const height = document.getElementById("flatRedskabsRumLængde")

    if (x.style.display === "none") {
        x.style.display = "block";
        width.disabled = false;
        height.disabled = false;
    } else {
        x.style.display = "none";
        width.disabled = true;
        height.disabled = true;
    }
}

function showObliqueLogin() {
    const create = document.getElementById("myAngledCreate");
    const login = document.getElementById("myAngledLogin");
    const angledHasUser = document.getElementById("angledHasUser");
    const angledHasUserEmail = document.getElementById("angledHasUserEmail");
    const angledHasUserPassword = document.getElementById("angledHasUserPassword");

    const angledNoUserName = document.getElementById("angledNoUserName");
    const angledNoUserEmail = document.getElementById("angledNoUserEmail");
    const angledNoUserPassword = document.getElementById("angledNoUserPassword");
    const angledNoUserPassword2 = document.getElementById("angledNoUserPassword2");

    //show the login tab and activate the loginscreen
    create.style.display = "none";
    login.style.display = "block";
    angledHasUser.disabled = false;
    angledHasUserEmail.disabled = false;
    angledHasUserPassword.disabled = false;

    //disable the createscreen
    angledNoUserName.disabled = true;
    angledNoUserEmail.disabled = true;
    angledNoUserPassword.disabled = true;
    angledNoUserPassword2.disabled = true;
}

function showObliqueCreate() {
    const create = document.getElementById("myAngledCreate");
    const login = document.getElementById("myAngledLogin");
    const angledHasUser = document.getElementById("angledHasUser");
    const angledHasUserEmail = document.getElementById("angledHasUserEmail");
    const angledHasUserPassword = document.getElementById("angledHasUserPassword");

    const angledNoUserName = document.getElementById("angledNoUserName");
    const angledNoUserEmail = document.getElementById("angledNoUserEmail");
    const angledNoUserPassword = document.getElementById("angledNoUserPassword");
    const angledNoUserPassword2 = document.getElementById("angledNoUserPassword2");

    //show the login tab and activate the loginscreen
    create.style.display = "block";
    login.style.display = "none";
    angledHasUser.disabled = true;
    angledHasUserEmail.disabled = true;
    angledHasUserPassword.disabled = true;

    //disable the createscreen
    angledNoUserName.disabled = false;
    angledNoUserEmail.disabled = false;
    angledNoUserPassword.disabled = false;
    angledNoUserPassword2.disabled = false;
}


function showFlatLogin() {
    const create = document.getElementById("myFlatCreate");
    const login = document.getElementById("myFlatLogin");
    const angledHasUser = document.getElementById("flatHasUser");
    const angledHasUserEmail = document.getElementById("flatHasUserEmail");
    const angledHasUserPassword = document.getElementById("flatHasUserPassword");

    const angledNoUserName = document.getElementById("flatNoUserName");
    const angledNoUserEmail = document.getElementById("flatNoUserEmail");
    const angledNoUserPassword = document.getElementById("flatNoUserPassword");
    const angledNoUserPassword2 = document.getElementById("flatNoUserPassword2");

    //show the login tab and activate the loginscreen
    create.style.display = "none";
    login.style.display = "block";
    angledHasUser.disabled = false;
    angledHasUserEmail.disabled = false;
    angledHasUserPassword.disabled = false;

    //disable the createscreen
    angledNoUserName.disabled = true;
    angledNoUserEmail.disabled = true;
    angledNoUserPassword.disabled = true;
    angledNoUserPassword2.disabled = true;
}

function showFlatCreate() {
    const create = document.getElementById("myFlatCreate");
    const login = document.getElementById("myFlatLogin");
    const angledHasUser = document.getElementById("flatHasUser");
    const angledHasUserEmail = document.getElementById("flatHasUserEmail");
    const angledHasUserPassword = document.getElementById("flatHasUserPassword");

    const angledNoUserName = document.getElementById("flatNoUserName");
    const angledNoUserEmail = document.getElementById("flatNoUserEmail");
    const angledNoUserPassword = document.getElementById("flatNoUserPassword");
    const angledNoUserPassword2 = document.getElementById("flatNoUserPassword2");

    //show the login tab and activate the loginscreen
    create.style.display = "block";
    login.style.display = "none";
    angledHasUser.disabled = true;
    angledHasUserEmail.disabled = true;
    angledHasUserPassword.disabled = true;

    //disable the createscreen
    angledNoUserName.disabled = false;
    angledNoUserEmail.disabled = false;
    angledNoUserPassword.disabled = false;
    angledNoUserPassword2.disabled = false;
}