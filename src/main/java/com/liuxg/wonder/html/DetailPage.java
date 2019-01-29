package com.liuxg.wonder.html;

import com.liuxg.wonder.po.Model;
import org.springframework.util.StringUtils;

public class DetailPage {

    public static final String no_album = "images/no_album.jpg";

    public static final String peopleInfoCn = "" +
            "        <div id=\"name\">\n" +
            "            <span class=\"name\">$name</span>\n" +
            "        </div>\n" +
            "        <div id=\"image\">\n" +
            "            <img style=\"width: 350px; height: auto;\" src=\"$peoImg\">\n" +
            "        </div>\n" +
            "        <div id=\"data\" style=\"margin: 15px;\">\n" +
            "            <div style=\"background-color: #dddddd;width: 350px; height: 350px;\">\n" +
            "                <li><span>生日</span>$birth</li>\n" +
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


    public static final String peopleInfoEn = "" +
            "        <div id=\"name\">\n" +
            "            <span class=\"name\">$name</span>\n" +
            "        </div>\n" +
            "        <div id=\"image\">\n" +
            "            <img style=\"width: 350px; height: auto;\" src=\"$peoImg\">\n" +
            "        </div>\n" +
            "        <div id=\"data\" style=\"margin: 15px;\">\n" +
            "            <div style=\"background-color: #dddddd;width: 350px; height: 350px;\">\n" +
            "                <li><span>BIRTHDAY</span>$birth</li>\n" +
            "                <li><span>HEIHT</span>$height cm</li>\n" +
            "                <li><span>CHEST</span>$chest cm</li>\n" +
            "                <li><span>WAIST</span>$waist cm</li>\n" +
            "                <li><span>HIPS</span>$hips cm</li>\n" +
            "                <li><span>SHOSESIZE</span>$shoesSize</li>\n" +
            "                <li><span>EYECOLOR</span>$eyeColor</li>\n" +
            "                <li><span>HAIRCOLOR</span>$hairColor</li>\n" +
            "                <li><span>SKINCOLOR</span>$skinColor</li>\n" +
            "            </div>\n" +
            "        </div>";

    public static String getPeopleInfoHtml(Model model, String l) {
        String html = "cn".equals(l) ? peopleInfoCn : peopleInfoEn;
        return html.replace("$name", model.getName())
                .replace("$peoImg", model.getOpusTitle())
                .replace("$birth", model.getBirthday())
                .replace("$sex", getSex(l, model))
                .replace("$height", model.getHeight())
                .replace("$chest", model.getChest())
                .replace("$waist", model.getWaist())
                .replace("$hips", model.getHips())
                .replace("$shoesSize", model.getShoeSize())
                .replace("$eyeColor", model.getEyeColor())
                .replace("$hairColor", model.getHairColor())
                .replace("$skinColor", model.getSkinColor());
    }

    public static final String tabTitleCn = "" +
            "           <div class=\"tab-title-item\">\n" +
            "                <a href=\"#opus\" aria-controls=\"home\" role=\"tab\" data-toggle=\"tab\">\n" +
            "                    <span>作品照</span>\n" +
            "                </a>\n" +
            "            </div>\n" +
            "            <div class=\"tab-title-item\">\n" +
            "                <a href=\"#makeup\" aria-controls=\"profile\" role=\"tab\" data-toggle=\"tab\">\n" +
            "                    <span>素颜</span>\n" +
            "                </a>\n" +
            "            </div>\n" +
            "            <div class=\"tab-title-item\">\n" +
            "                <a href=\"#video\" aria-controls=\"settings\" role=\"tab\" data-toggle=\"tab\">\n" +
            "                    <span>视频</span>\n" +
            "                </a>\n" +
            "            </div>";


    public static final String tabTitleEn = "" +
            "           <div class=\"tab-title-item\">\n" +
            "                <a href=\"#opus\" aria-controls=\"home\" role=\"tab\" data-toggle=\"tab\">\n" +
            "                    <span>WORKS</span>\n" +
            "                </a>\n" +
            "            </div>\n" +
            "            <div class=\"tab-title-item\">\n" +
            "                <a href=\"#makeup\" aria-controls=\"profile\" role=\"tab\" data-toggle=\"tab\">\n" +
            "                    <span>NATURAL</span>\n" +
            "                </a>\n" +
            "            </div>\n" +
            "            <div class=\"tab-title-item\">\n" +
            "                <a href=\"#video\" aria-controls=\"settings\" role=\"tab\" data-toggle=\"tab\">\n" +
            "                    <span>VIDEO</span>\n" +
            "                </a>\n" +
            "            </div>";

    public static String getTabTitleHtml(Model model, String l) {

        String html = "cn".equals(l) ? tabTitleCn : tabTitleEn;
        return html.replace("$opusImg", StringUtils.isEmpty(model.getOpusTitle()) ? no_album : model.getOpusTitle())
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
            "                    <video muted=\"muted\" controls=\"controls\" isrotate=\"false\" autohide=\"true\" loop=\"loop\"\n" +
            "                           src=\"$video\">\n" +
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

    public static String getSex(String l, Model model) {

        if ("女".equals(model.getSex())) {
            return "cn".equals(l) ? "女" : "WOMEN";
        }
        return "cn".equals(l) ? "男" : "MEN";
    }

}
