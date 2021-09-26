$("form").submit(function (event) {
    event.preventDefault();
    register($("form").attr("action"), $("form").attr("success"));
})

function register(url, alertMessage) {
    let form = $("form");
    let jsonFormData = getJsonFormData(form);
    request(url, "POST", jsonFormData, alertMessage);
}

function getJsonFormData(form) {
    let un_indexed_array = form.serializeArray();
    let indexed_array = {};

    $.map(un_indexed_array, function (n, i) {
        indexed_array[n['name']] = n['value'];
    });

    return JSON.stringify(indexed_array);
}

function request(url, method, data, alertMessage) {
    $.ajax({
        type: method,
        url: url,
        data: data,
        success: function (response) {
            alert(alertMessage);
            window.location.href = response.redirect;
        },
        error: function (XMLHttpRequest) {
            let response = XMLHttpRequest.responseJSON;
            alert(response.body);
        },
        dataType: "json",
        contentType: "application/json"
    })
}