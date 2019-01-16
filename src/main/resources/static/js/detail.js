$(document).ready(function () {

    changeLaunage();
    queryDetail();

    // 切换至汉语
    function toChinese() {
        $("#aHome").attr("href", "home.html?l=cn");
        $("#aHome2").attr("href", "home.html?l=cn");
        $("#aHome").text("主页");
    }

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

    //语言切换
    function changeLaunage() {
        var theRequest = GetRequest();
        if (theRequest.l == 'cn') {
            toChinese();
        }
    }

    //查询模特
    function queryDetail() {
        var theRequest = GetRequest();
        var userId = theRequest.userId;
        var l = theRequest.l == 'cn' ? 'cn' : 'en';
        $.ajax({
            url: "queryDetail?userId=" + userId + "&l=" + l,
            async: false,
            success: function (data) {
                $("#peopleInfo").html(data.peopleInfo);
                $("#tab-title").html(data.tabTitle);
                $("#opus-content").html(data.opus);
                $("#makeup").html(data.makeup);
                $("#video-content").html(data.video);
            },
        });

    }

});
