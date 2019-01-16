$(document).ready(function () {

    var request = GetRequest();
    $("#opus").click(function (event) {
        uploadFile(request.userId, 'opus');
    });
    $("#opusTitle").click(function (event) {
        uploadFile(request.userId, 'opusTitle');
    });
    $("#makeup").click(function (event) {
        uploadFile(request.userId, 'makeup');
    });
    $("#makeupTitle").click(function (event) {
        uploadFile(request.userId, 'makeupTitle');
    });
    $("#video").click(function (event) {
        uploadFile(request.userId, 'video');
    });
    $("#videoTitle").click(function (event) {
        uploadFile(request.userId, 'videoTitle');
    });

    //获取参数
    function GetRequest() {
        var url = location.search; //获取url中"?"符后的字串
        var theRequest = new Object();
        if (url.indexOf("?") != -1) {
            var str = url.substr(1);
            strs = str.split("&");
            for (var i = 0; i < strs.length; i++) {
                theRequest[strs[i].split("=")[0]] = (strs[i].split("=")[1]);
            }
        }
        return theRequest;
    }

    //查询信息
    function queryDetail() {
        var theRequest = GetRequest();
        var userId = theRequest.userId;
        $.ajax({
            url: "queryInfo?userId=" + userId,
            async: false,
            success: function (data) {
                if (data != null) {
                    $("#id").val(data.id);
                    $("#name").val(data.name);
                    $("#birthday").val(data.birthday);
                    $("#sex").val(data.sex);
                    $("#height").val(data.height);
                    $("#chest").val(data.chest);
                    $("#waist").val(data.waist);
                    $("#hips").val(data.hips);
                    $("#shoeSize").val(data.shoeSize);
                    $("#eyeColor").val(data.eyeColor);
                    $("#hairColor").val(data.hairColor);
                    $("#skinColor").val(data.skinColor);
                }
            },
        });

    }

    function uploadFile(userId, type) {

        var pic = $("#pic").get(0).files[0];
        var formData = new FormData();
        formData.append("file", pic);
        $.ajax({
            type: "POST",
            url: "/upload?userId=" + userId + "&type=" + type,
            data: formData,
            processData: false, //必须false才会自动加上正确的Content-Type
            dataType: 'json',
            contentType: false,//必须false才会避开jQuery对 formdata 的默认处理 
            xhr: function () {
                var xhr = $.ajaxSettings.xhr();
                if (onprogress && xhr.upload) {
                    xhr.upload.addEventListener("progress", onprogress, false);
                    return xhr;
                }
            },
            success: function (res) {
                console.log(res);
            }
        });
        window.location.href="manager.html";

    }

    function onprogress(evt) {
        var loaded = evt.loaded;         //已经上传大小情况
        var tot = evt.total;           //附件总大小
        var per = Math.floor(100 * loaded / tot);   //已经上传的百分比
        $("#son").html(per + "%");
        $("#son").css("width", per / 4 + "%");
    }

});
