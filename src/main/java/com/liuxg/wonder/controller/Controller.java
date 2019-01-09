package com.liuxg.wonder.controller;

import com.liuxg.wonder.po.Model;
import com.liuxg.wonder.service.IModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@org.springframework.stereotype.Controller
public class Controller {

    @Autowired
    IModelService modelService;

    private static final String h2Html = "<h2 id=\"hModel\" class=\"text-center  wowload fadeInUp\" style=\"margin-left: 5%;\">WONDER MODELS</h2>";

    private static final String modelsEnHtml = "<figure class=\"effect-oscar  wowload fadeInUp\">\n" +
            "        <img src=\"$image\"  width=\"1000\" height=\"800\"/>\n" +
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

    private static final String modelsCnHtml = "<figure class=\"effect-oscar  wowload fadeInUp\">\n" +
            "        <img src=\"$image\"  width=\"1000\" height=\"800\"/>\n" +
            "            <figcaption>\n" +
            "                <h2 style=\"margin-top: 60px; font-family: SimHei;\">$name</h2>\n" +
            "                <p>\n" +
            "                    身高/$height<br/>\n" +
            "                    胸围/$chest<br/>\n" +
            "                    腰围/$waist<br/>\n" +
            "                    臀围/$hips<br/>\n" +
            "                    <a href=\"detail.html?userId=$id&l=$l\" name=\"imgView\">View more</a>\n" +
            "                </p>\n" +
            "            </figcaption>\n" +
            "    </figure>\n";


    @RequestMapping("queryModels")
    @ResponseBody
    public String queryModels(String l) {

        StringBuffer stringBuffer = new StringBuffer(h2Html);
        List<Model> list = modelService.query();
        for (Model model : list) {
            String html = "cn".equals(l) ? modelsCnHtml : modelsEnHtml;
            html = html.replace("$image", model.getOpusTitle());
            html = html.replace("$name", model.getName());
            html = html.replace("$height", model.getHeight());
            html = html.replace("$chest", model.getChest());
            html = html.replace("$waist", model.getWaist());
            html = html.replace("$hips", model.getHips());
            html = html.replace("$id", model.getId());
            html = html.replace("$l", l == null ? "en" : l);
            stringBuffer.append(html);
        }
        return stringBuffer.toString();
    }

}
