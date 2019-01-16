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
            "        <th>上传</th>\n" +
            "        <th>预览</th>\n" +
            "    </tr>";


    public static final String trHtml = "<tr>\n" +
            "            <td><a href=\"info.html?userId=$userId\">$name</a></td>\n" +
            "            <td>$opusTitle</td>\n" +
            "            <td>\n" +
            "                <div class=\"contarner\">$opus</div>\n" +
            "            </td>\n" +
            "            <td>$makeupTitle</td>\n" +
            "            <td>\n" +
            "                <div class=\"contarner\">$makeup</div>\n" +
            "            </td>\n" +
            "            <td>$videoTitle</td>\n" +
            "            <td>\n" +
            "                <div class=\"contarner\">$video</div>\n" +
            "            </td>\n" +
            "            <td>\n" +
            "                <a href=\"upload.html?userId=$userId\">上传</a>\n" +
            "            </td>\n" +
            "            <td><a href=\"detail.html?userId=$userId\">预览</a></td>\n" +
            "        </tr>";

    public static final String imageHtml =
            "<div class=\"img-container\">\n" +
                    "    <img src=\"$src\" class=\"img\">\n" +
                    "</div>";

    public static final String videoHtml = "<div class=\"video-container\">\n" +
            "    <video class=\"video\" ishivideo=\"true\" muted=\"muted\" autoplay=\"true\" isrotate=\"false\" autohide=\"true\" loop=\"loop\"\n" +
            "           src=\"$src\">\n" +
            "        <source src=\"$src\" type=\"video/mp4\">\n" +
            "        <span data-i18n-text=\"video_bzc\">当前浏览器不支持video播放</span>\n" +
            "    </video>\n" +
            "</div>";

    public static String getImageHtml(String src) {
        return imageHtml.replace("$src", src);
    }

    public static String getImageHtml(Collection<String> srcs) {
        StringBuffer stringBuffer = new StringBuffer("");
        for (String src : srcs) {
            stringBuffer.append(getImageHtml(src));
        }
        return stringBuffer.toString();
    }

    public static String getVideoHtml(String src) {
        return videoHtml.replaceAll("\\$src", src);
    }

    public static String getVideoHtml(Collection<String> srcs) {
        StringBuffer stringBuffer = new StringBuffer("");
        for (String src : srcs) {
            stringBuffer.append(getVideoHtml(src));
        }
        return stringBuffer.toString();
    }


}
