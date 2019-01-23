$(document).ready(function () {

    queryDetail();

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
                if(data != null){
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

});
