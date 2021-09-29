$(".item_btn").on("click", function (event) {
    event.preventDefault();
    let divSibling = $(this.nextElementSibling)[0];
    if ($(divSibling).css("display") === "none") {
        $(divSibling).show();
    } else {
        $(divSibling).hide();
    }
})

$(".process").on("click", function (event) {
    event.preventDefault();
    let url = $(this).attr("action");
    let parentButton = $(this).parent();
    let divDate = $(parentButton).next();
    let date = $(divDate).children().last().val();
    if (!date || Date.now() >= Date.parse(date)) {
        let errorMessage = $(divDate).children().last().attr("message");
        alert(errorMessage);
        return;
    }
    if (!isItemCorrect()) {
        let errorMessage = $("select:visible").attr("message");
        alert(errorMessage);
        return;
    }

    let requestBody = {};
    requestBody.date = Date.parse(date);
    requestBody.items = getItems();
    request(url, "PUT", JSON.stringify(requestBody));
})

function getItems() {
    let items = []
    $("select:visible").each(function () {
        let item = {}
        item.orderId = $(this).attr("id");
        item.employeeId = $(this).val();
        items.push(item);
    })
    return items;
}

function isItemCorrect() {
    let isCorrect = true;
    $("select:visible").each(function () {
        if ($(this).val() === "default") {
            isCorrect = false;
        }
    })
    return isCorrect;
}

$(".cancelled").on("click", function (event) {
    event.preventDefault();
    let parentOrderId = $(this).attr("parent");
    let url = $(this).attr("action");
    let requestBody = {};
    requestBody.parentOrderId = parentOrderId;
    request(url, "PUT", JSON.stringify(requestBody))
})

function request(url, method, data) {
    $.ajax({
        type: method,
        url: url,
        data: data,
        success: function (response) {
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