$("#role").change(function () {
    if ($(this).val() === "Admin") {
        $("#serv").hide();
    } else {
        $("#serv").show();
    }
})

$("#emp_role").change(function () {
    if ($(this).val() === "Admin") {
        $("#admins").show();
        $("#masters").hide();
    } else {
        $("#admins").hide();
        $("#masters").show();
    }
})

$("#add").submit(function (event) {
    event.preventDefault();
    addEmployee(getFormData($(this)), $(this).attr("action"));
})

$("#remove").on("click", function (event) {
    event.preventDefault();
    if ($("#emp_role").val() === "Admin") {
        removeAdmin($(this).attr("action"));
    } else {
        removeMaster($(this).attr("action"));
    }
})

function removeMaster(url) {
    let masters = $("#masters").val();
    if (masters.length === 0) {
        alert($("#masters").attr("message"));
        return;
    }
    let requestBody = {}
    requestBody.userIds = masters;
    request(url, "DELETE", JSON.stringify(requestBody))
}

function removeAdmin(url) {
    let admins = $("#admins").val();
    if (admins.length === 0) {
        alert($("#admins").attr("message"));
        return;
    }
    let requestBody = {}
    requestBody.userIds = admins;
    request(url, "DELETE", JSON.stringify(requestBody))
}

function addEmployee(formData, url) {
    let employeeRole = $("#role").val();
    if (employeeRole === "Master") {
        addMaster(formData, url);
    } else {
        addAdmin(formData, url);
    }
}

function addMaster(formData, url) {
    let services = $("#service").val();
    if (services.length === 0) {
        alert($("#service").attr("message"));
        return;
    }
    formData.serviceIds = services;
    formData.role = "Master";
    request(url, "POST", JSON.stringify(formData));
}

function addAdmin(formData, url) {
    formData.role = "Admin";
    request(url, "POST", JSON.stringify(formData));
}

function getFormData(form) {
    let un_indexed_array = form.serializeArray();
    let indexed_array = {};

    $.map(un_indexed_array, function (n) {
        indexed_array[n['name']] = n['value'];
    });

    return indexed_array;
}

function request(url, method, data) {
    $.ajax({
        type: method,
        url: url,
        data: data,
        success: function (response) {
            if (response.status === 201) {
                alert($("#add").attr("success"));
            }
            if (response.status === 200) {
                alert($("#remove").attr("message"))
            }
            if (response.redirect) {
                console.log(response);
                window.location.href = response.redirect;
            }
        },
        error: function (XMLHttpRequest) {
            let response = XMLHttpRequest.responseJSON;
            alert(response.body);
        },
        dataType: "json",
        contentType: "application/json"
    })
}