$("form").submit(function (event) {
    event.preventDefault();
    register($("form").attr("action"), $("form").attr("success"));
})

function register(url, alertMessage) {
    let form = $("form");
    let formData = getFormData(form);
    if (formData.confirm_password !== formData.password) {
        alert($("#confirm").attr("message"));
        return;
    }
    request(url, "POST", JSON.stringify(formData), alertMessage);
}

function getFormData(form) {
    let un_indexed_array = form.serializeArray();
    let indexed_array = {};

    $.map(un_indexed_array, function (n, i) {
        indexed_array[n['name']] = n['value'];
    });

    return indexed_array;
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