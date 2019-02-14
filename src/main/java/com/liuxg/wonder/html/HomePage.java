package com.liuxg.wonder.html;

public class HomePage {

    public static final String h2Html = "<h2 id=\"hModel\" class=\"text-center  wowload fadeInUp\" style=\"margin-left: 5%;\">WONDER MODELS</h2>";

    public static final String modelsEnHtml = "<figure class=\"effect-oscar  wowload fadeInUp\">\n" +
            "        <img src=\"$image\"  width=\"1000\" height=\"auto\"/>\n" +
            "            <figcaption>\n" +
            "                <h2 style=\"margin-top: 60px; font-family: SimHei;\">$name</h2>\n" +
            "                <p>\n" +
            "                    Height/$height<br/>\n" +
            "                    Chest/$chest<br/>\n" +
            "                    Waist/$waist<br/>\n" +
            "                    Hips/$hips<br/>\n" +
            "                    <a href=\"detail.html?userId=$id&l=$l\" name=\"imgView\">View more</a>\n" +
            "                </p>\n" +
            "            </figcaption>\n" +
            "    </figure>\n";

    public static final String modelsCnHtml = "<figure class=\"effect-oscar  wowload fadeInUp\">\n" +
            "        <img src=\"$image\"  width=\"1000\" height=\"auto\"/>\n" +
            "            <figcaption>\n" +
            "                <h2 style=\"margin-top: 60px; font-family: SimHei;\">$name</h2>\n" +
            "                <p>\n" +
            "                    身高/$height<br/>\n" +
            "                    胸围/$chest<br/>\n" +
            "                    腰围/$waist<br/>\n" +
            "                    臀围/$hips<br/>\n" +
            "                    <a href=\"detail.html?userId=$id&l=$l\" name=\"imgView\">详细信息</a>\n" +
            "                </p>\n" +
            "            </figcaption>\n" +
            "    </figure>\n";
}
