$(document).ready(function () {

    queryManagerMotels();

    //查询模特
    function queryManagerMotels() {
        var modelsHtml = $.ajax({url: "queryManagerModels", async: false});
        $("#table").html(modelsHtml.responseText);
    }
});
