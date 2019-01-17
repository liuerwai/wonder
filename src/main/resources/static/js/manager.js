$(document).ready(function () {

    queryManagerMotels();

    $(".img-container").mouseenter(function () {
        $(this).find(".delete").show();

    });


    $(".img-container").mouseleave(function () {
        $(this).find(".delete").hide();
    });

    //查询模特
    function queryManagerMotels() {
        var modelsHtml = $.ajax({url: "queryManagerModels", async: false});
        $("#table").html(modelsHtml.responseText);
    }


});
