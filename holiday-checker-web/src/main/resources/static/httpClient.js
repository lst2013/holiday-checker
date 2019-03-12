window.onload = function() {
    document.getElementById("holidayCheckButton").onclick = httpPost;
};

function httpPost() {
    var xhr = new XMLHttpRequest();

    xhr.onload = function() {
        var holidayCheckResponse = document.getElementById("holidayCheckResponse");
        holidayCheckResponse.innerHTML = this.responseText;
    };

    xhr.open("POST", "/holidayCheck");
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.send(holidayCheckFormToJson());
}

function holidayCheckFormToJson() {
    var jsonString = '{';
    var holidayCheckForm = document.getElementById("holidayCheckForm");

    for (var i = 0; i < holidayCheckForm.elements.length; i++) {
        var formElement = holidayCheckForm.elements[i];
        jsonString += '"' + formElement.id + '":"' + formElement.value + '"';

        if (i < holidayCheckForm.elements.length - 1) {
            jsonString += ',';
        }
    }

    jsonString += '}';

    return jsonString;
}