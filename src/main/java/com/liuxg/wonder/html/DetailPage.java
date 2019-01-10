package com.liuxg.wonder.html;

import com.liuxg.wonder.po.Model;
import org.springframework.util.StringUtils;

public class DetailPage {

    public static final String no_album = "images/no_album.jpg";

    public static final String peopleInfo = "" +
            "        <div id=\"name\">\n" +
            "            <h2>$name</h2>\n" +
            "        </div>\n" +
            "        <div id=\"image\">\n" +
            "            <img style=\"width: 350px; height: 350px;\" src=\"$peoImg\">\n" +
            "        </div>\n" +
            "        <div id=\"data\" style=\"margin: 15px;\">\n" +
            "            <div style=\"background-color: #dddddd;width: 350px; height: 350px;\">\n" +
            "                <li><span>生日</span>$birth</li>\n" +
            "                <li><span>SEX</span>$sex</li>\n" +
            "                <li><span>身高</span>$height cm</li>\n" +
            "                <li><span>胸围</span>$chest cm</li>\n" +
            "                <li><span>腰围</span>$waist cm</li>\n" +
            "                <li><span>臀围</span>$hips cm</li>\n" +
            "                <li><span>鞋码</span>$shoesSize</li>\n" +
            "                <li><span>眼睛眼色</span>$eyeColor</li>\n" +
            "                <li><span>发色</span>$hairColor</li>\n" +
            "                <li><span>肤色</span>$skinColor</li>\n" +
            "            </div>\n" +
            "        </div>";

    public static String getPeopleInfoHtml(Model model) {

        return peopleInfo.replace("$name", model.getName())
                .replace("$peoImg", model.getOpusTitle())
                .replace("$birth", model.getBirthday())
                .replace("$sex", model.getSex())
                .replace("$height", model.getHeight())
                .replace("$chest", model.getChest())
                .replace("$waist", model.getWaist())
                .replace("$hips", model.getHips())
                .replace("$shoesSize", model.getShoeSize())
                .replace("$eyeColor", model.getEyeColor())
                .replace("$hairColor", model.getHairColor())
                .replace("$skinColor", model.getSkinColor());
    }

    public static final String tabTitle = "" +
            "           <div class=\"tab-title-item\">\n" +
            "                <a href=\"#opus\" aria-controls=\"home\" role=\"tab\" data-toggle=\"tab\">\n" +
            "                    <img src=\"$opusImg\"> <br/>\n" +
            "                    <span>作品照</span>\n" +
            "                </a>\n" +
            "            </div>\n" +
            "            <div class=\"tab-title-item\">\n" +
            "                <a href=\"#makeup\" aria-controls=\"profile\" role=\"tab\" data-toggle=\"tab\">\n" +
            "                    <img src=\"$makeupImg\"> <br/>\n" +
            "                    <span>素颜</span>\n" +
            "                </a>\n" +
            "            </div>\n" +
            "            <div class=\"tab-title-item\">\n" +
            "                <a href=\"#video\" aria-controls=\"settings\" role=\"tab\" data-toggle=\"tab\">\n" +
            "                    <img src=\"$videoImg\"> <br/>\n" +
            "                    <span>视频</span>\n" +
            "                </a>\n" +
            "            </div>";

    public static String getTabTitleHtml(Model model) {

        return tabTitle.replace("$opusImg", StringUtils.isEmpty(model.getOpusTitle()) ? no_album : model.getOpusTitle())
                .replace("$makeupImg", StringUtils.isEmpty(model.getMakeupTitle()) ? no_album : model.getMakeupTitle())
                .replace("$videoImg", StringUtils.isEmpty(model.getVideioTite()) ? no_album : model.getVideioTite());
    }


    public static final String imageHtml = "" +
            "                    <a href=\"$image\" name=\"imgView\" class=\"tab-content-a\">\n" +
            "                        <img src=\"$image\" class=\"tab-content-img\">\n" +
            "                    </a>\n";

    public static String getOpusImagehtml(Model model) {

        StringBuffer stringBuffer = new StringBuffer("");
        if (model.getOpus() != null) {
            for (String image : model.getOpus().values()) {
                stringBuffer.append(imageHtml.replaceAll("\\$image", image));
            }
        }
        return stringBuffer.toString();
    }

    public static String getMakeUpImageHtml(Model model) {

        StringBuffer stringBuffer = new StringBuffer("");
        if (model.getMakeup() != null) {
            for (String image : model.getMakeup().values()) {
                stringBuffer.append(imageHtml.replaceAll("\\$image", image));
            }
        }
        return stringBuffer.toString();
    }

    public static final String videoHtml = "" +
            "                    <video ishivideo=\"true\" muted=\"muted\" autoplay=\"true\" isrotate=\"false\" autohide=\"true\" loop=\"loop\"\n" +
            "                           src=\"$video\">\n" +
            "                        <source src=\"$video\" type=\"video/mp4\">\n" +
            "                        <span data-i18n-text=\"video_bzc\">当前浏览器不支持video播放</span>\n" +
            "                    </video>";


    public static String getVideoHtml(Model model) {

        StringBuffer stringBuffer = new StringBuffer("");
        if (model.getVideo() != null) {
            for (String video : model.getVideo().values()) {
                stringBuffer.append(videoHtml.replaceAll("\\$video", video));
            }
        }
        return stringBuffer.toString();
    }


}
