$(".item_btn").on("click", function (event) {
    event.preventDefault();
    let detailsDiv = $(this.nextElementSibling)[0];
    if ($(detailsDiv).css("display") === "none") {
        $(detailsDiv).show();
    } else {
        $(detailsDiv).hide();
    }
})