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
    textBox.append("<b>&gt " + input + "</b><br>");
    textBox.append("<p>" + response.replace(/\n/g,"<br>") + "</p>");
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