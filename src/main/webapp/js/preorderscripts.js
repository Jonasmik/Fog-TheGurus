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

