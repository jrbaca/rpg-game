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
            outputText(input, JSON.parse(data).response)
        }
    })
}

function outputText(input, response) {
    let textBox = $("#textOutput");

    let formattedInput = "<b>&gt " + input + "</b><br>";
    let formattedResponse = "<p>" + response.replace(/</g, "&lt")
        .replace(/>/g, "&gt")
        .replace(/\n/g, "<br>") + "</p>";

    textBox.append(formattedInput);
    textBox.append(formattedResponse);
    textBox.scrollTop(textBox.prop("scrollHeight")); // Go to bottom of page
}

$(document).ready(function () {
    outputText("intro", "Type \"help\" if you're stuck! Type \"new game\" to start over.");

    $("#submitButton").on("click", readInput);

    // Override enter with submission
    $("#entryBox").keydown(function (event) {
        if (event.keyCode === 13) {
            event.preventDefault();
            readInput()
        }
    })
});