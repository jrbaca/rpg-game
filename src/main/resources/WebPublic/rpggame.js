function readInput() {
    let entryBox = $("#entryBox");

    // Get text and clear box
    let input = entryBox.val();
    entryBox.val("");

    evalInput(input)
}

function evalInput(input) {
    $.ajax({
        url: "/rpgserver/",
        type: "GET",
        data: {'input': input},
        success: function (data) {
            outputText(JSON.parse(data).response)
        }
    })
}

function outputText(text) {
    let textBox = $("#textOutput");
    textBox.append("<p>" + text + "</p>");
    textBox.scrollTop(textBox.prop("scrollHeight")); // Go to bottom of page
}

$(document).ready(function () {

    $("#submitButton").on("click", readInput);

    // Override enter with submission
    $("#entryBox").keydown(function (event) {
        if (event.keyCode === 13) {
            event.preventDefault();
            readInput()
        }
    })
});