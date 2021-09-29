let alertRemember;
let isAlertRemove = false;
$(".price-list-item > button").on("click", function (event) {
    event.preventDefault();
    let button = $(this);
    let itemInfo = $(this.previousElementSibling)[0];
    let itemName = $(itemInfo.firstElementChild)[0];
    let itemPrice = $(itemInfo.lastElementChild)[0];
    if ($(button).attr("data-type")) {
        return;
    } else {
        let icon = $(this.firstElementChild)[0];
        $(button).attr("data-type", true);
        changeClassForElement(icon, "fas fa-plus-circle", "fas fa-dot-circle");
        changeCartPrice(parseInt($(itemPrice).text()))
    }
    if (getClickedButtonCount() > 0) {
        if (!isAlertRemove) {
            removeAlert();
            createParentItem();
            isAlertRemove = true;
            enableButton();
        }
        addItem($(itemName).text());
    } else {
        isAlertRemove = false;
        addAlert();
    }
})

function enableButton() {
    $(".reserve__btn").removeAttr("disabled");
}

function disableButton() {
    $(".reserve__btn").attr("disabled", "disabled");
}

$(document).on("click", ".cart__items-list > div", function (event) {
    event.preventDefault();
    $(this).remove();
    let button = getButtonByText($(this).text());
    $(button).removeAttr("data-type");
    let parentDiv = getParentDiv($(this).text());
    let priceDiv = $(parentDiv.lastElementChild)[0];
    let icon = $(button.firstElementChild)[0];
    changeClassForElement(icon, "fas fa-dot-circle", "fas fa-plus-circle");
    changeCartPrice(-parseInt($(priceDiv).text()));
    if (getClickedButtonCount() < 1) {
        disableButton();
        isAlertRemove = false;
        $(".cart__items-list").remove();
        addAlert();
    }
});

function changeClassForElement(element, currentClass, nextClass) {
    $(element).removeClass(currentClass);
    $(element).addClass(nextClass);
}

function changeCartPrice(value) {
    let currentPrice = getCartPrice();
    currentPrice += value;
    $(".cart__price").text(currentPrice);
}

function getClickedButtonCount() {
    return $(".price-list-item > button[data-type]").length;
}

function getButtonByText(text) {
    let parentDiv = getParentDiv(text);
    return $(parentDiv.nextElementSibling)[0];
}

function getParentDiv(text) {
    let itemName;
    $(".price-list-item__name").each(function () {
        if ($(this).text() === text) {
            itemName = $(this)[0];
        }
    })
    return itemName.parentNode;
}

function removeAlert() {
    let parentDiv = $(".cart__header ~ div:not([class])")[0];
    let alertDiv = $(parentDiv.firstElementChild)[0]
    alertRemember = alertDiv;
    alertDiv.remove();
}

function addAlert() {
    let parentDiv = $(".cart__header ~ div:not([class])")[0];
    parentDiv.append(alertRemember);
}

function createParentItem() {
    let parent = document.createElement("div");
    $(parent).addClass("cart__items-list");
    let parentDiv = $(".cart__header ~ div:not([class])")[0];
    parentDiv.append(parent);
}

function addItem(service) {
    let icon = createIcon()
    let button = createButton();
    button.append(icon);
    let div = document.createElement("div");
    $(div).html(service);
    div.append(button);
    let parent = $(".cart__header ~ div:not([class]) > div")[0];
    parent.append(div);
}

function createIcon() {
    let icon = document.createElement('i');
    $(icon).addClass("far fa-check-circle");
    return icon;
}

function createButton() {
    let button = document.createElement('button');
    $(button).addClass("price-list-item__btn");
    return button;
}

function getCartPrice() {
    return parseInt($(".cart__price").text())
}

$(".reserve__btn").on("click", function (event) {
    event.preventDefault();
    let dateTime = $("#datetime").val();
    if (!dateTime || Date.now() >= Date.parse(dateTime)) {
        let errorMessage = $(this).attr("message");
        alert(errorMessage);
        return;
    }
    let services = [];
    let items = $(".cart__items-list > div");
    items.each(function () {
        services.push($(this).text());
    })
    let requestBody = {};
    requestBody.services = services;
    requestBody.dateTime = Date.parse(dateTime);
    let url = $("form").attr("action");
    request(url, "POST", JSON.stringify(requestBody));
})

function request(url, method, data) {
    $.ajax({
        type: method,
        url: url,
        data: data,
        success: function (response) {
            alert(response.body);
            window.location.href = response.redirect;
        },
        error: function (XMLHttpRequest) {
            let response = XMLHttpRequest.responseJSON;
            alert(response.body);
            if (response.redirect) {
                window.location.href = response.redirect;
            }
        },
        dataType: "json",
        contentType: "application/json"
    })
}