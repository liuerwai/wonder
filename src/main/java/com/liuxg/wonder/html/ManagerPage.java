package com.liuxg.wonder.html;

import java.util.Collection;

public class ManagerPage {

    public static final String titleHtml = "<tr>\n" +
            "        <th>姓名</th>\n" +
            "        <th>作品封面</th>\n" +
            "        <th>作品</th>\n" +
            "        <th>生活照封面图片</th>\n" +
            "        <th>生活照</th>\n" +
            "        <th>视频封面图片</th>\n" +
            "        <th>视频</th>\n" +
            "    </tr>";


    public static final String trHtml = "<tr>\n" +
            "            <td>" +
            "               <a href=\"info.html?userId=$userId\">$name&nbsp;&nbsp;</a> <br/><br/>" +
            "               <a href=\"deleteModel?userId=$userId\" onClick=\"return confirm('确定删除?');\">删除</a> <br/><br/>" +
            "               <a href=\"detail.html?userId=$userId\">预览</a> <br/><br/>" +
            "            </td>\n" +
            "            <td>" +
            "                $opusTitle" +
            "                <form action=upload?userId=$userId&type=opusTitle method=post enctype=multipart/form-data>\n" +
            "                    <input type=file name=pic accept=image/*>\n" +
            "                    <input type=submit>\n" +
            "                </form>" +
            "            </td>\n" +
            "            <td>\n" +
            "                <div class=\"contarner\">$opus</div>\n" +
            "                <form action=upload?userId=$userId&type=opus method=post enctype=multipart/form-data>\n" +
            "                    <input type=file name=pic accept=image/*>\n" +
            "                    <input type=submit>\n" +
            "                </form>" +
            "            </td>\n" +
            "            <td>" +
            "                $makeupTitle" +
            "                <form action=upload?userId=$userId&type=makeupTitle method=post enctype=multipart/form-data>\n" +
            "                    <input type=file name=pic accept=image/*>\n" +
            "                    <input type=submit>\n" +
            "                </form>" +
            "            </td>\n" +
            "            <td>\n" +
            "                <div class=\"contarner\">$makeup</div>\n" +
            "                <form action=upload?userId=$userId&type=makeup method=post enctype=multipart/form-data>\n" +
            "                    <input type=file name=pic accept=image/*>\n" +
            "                    <input type=submit>\n" +
            "                </form>" +
            "            </td>\n" +
            "            <td>" +
            "                $videoTitle" +
            "                <form action=upload?userId=$userId&type=videoTitle method=post enctype=multipart/form-data>\n" +
            "                    <input type=file name=pic accept=image/*>\n" +
            "                    <input type=submit>\n" +
            "                </form>" +
            "            </td>\n" +
            "            <td>\n" +
            "                <div class=\"contarner\">$video</div>\n" +
            "                <form action=upload?userId=$userId&type=video method=post enctype=multipart/form-data>\n" +
            "                    <input type=file name=pic accept=video/*>\n" +
            "                    <input type=submit>\n" +
            "                </form>" +
            "            </td>\n" +
            "        </tr>";

    public static final String imageHtml =
            "<div class=\"img-container\">\n" +
                    "    <img src=\"$src\" class=\"img\">\n" +
                    "    <a href=\"deleteFile?src=$src&userId=$userId&type=$type\"><img src=\"images/button-cross.png\" class=\"delete\" /></a>" +
                    "</div>";

    public static final String videoHtml = "<div class=\"img-container\">\n" +
            "    <video class=\"video\" ishivideo=\"true\" muted=\"muted\" autoplay=\"true\" isrotate=\"false\" autohide=\"true\" loop=\"loop\"\n" +
            "           src=\"$src\">\n" +
            "        <source src=\"$src\" type=\"video/mp4\">\n" +
            "        <span data-i18n-text=\"video_bzc\">当前浏览器不支持video播放</span>\n" +
            "    </video>\n" +
            "    <a href=\"deleteFile?src=$src&userId=$userId&type=$type\"><img src=\"images/button-cross.png\" class=\"delete\" /></a>" +
            "</div>";

    public static String getImageHtml(String src, String type) {
        return imageHtml.replace("$src", src).replace("$type", type);
    }

    public static String getImageHtml(Collection<String> srcs, String type) {
        StringBuffer stringBuffer = new StringBuffer("");
        for (String src : srcs) {
            stringBuffer.append(getImageHtml(src, type));
        }
        return stringBuffer.toString();
    }

    public static String getVideoHtml(String src, String type) {
        return videoHtml.replaceAll("\\$src", src).replace("$type", type);
    }

    public static String getVideoHtml(Collection<String> srcs, String type) {
        StringBuffer stringBuffer = new StringBuffer("");
        for (String src : srcs) {
            stringBuffer.append(getVideoHtml(src, type));
        }
        return stringBuffer.toString();
    }


}
