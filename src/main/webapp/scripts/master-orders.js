$(".item_btn").on("click", function (event) {
    event.preventDefault();
    let parent = $(this.parentElement)[0];
    let items = $(this.previousElementSibling)[0];
    let url = $(items).attr("action");
    let idElement = $(items.firstElementChild)[0];
    let req = {}
    req.id = $(idElement).text();
    request(url, "PUT", JSON.stringify(req), parent);
})

function request(url, method, data, parent) {
    $.ajax({
        type: method,
        url: url,
        data: data,
        success: function () {
            $(parent).remove();
        },
        error: function (XMLHttpRequest) {
            let response = XMLHttpRequest.responseJSON;
            console.log(response);
        },
        dataType: "json",
        contentType: "application/json"
    })
}