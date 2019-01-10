package com.liuxg.wonder.controller;

import com.liuxg.wonder.html.DetailPage;
import com.liuxg.wonder.html.HomePage;
import com.liuxg.wonder.po.DetailPagePo;
import com.liuxg.wonder.po.Model;
import com.liuxg.wonder.service.IModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@org.springframework.stereotype.Controller
public class Controller {

    @Autowired
    IModelService modelService;

    @RequestMapping("queryModels")
    @ResponseBody
    public String queryModels(String l) {

        StringBuffer stringBuffer = new StringBuffer(HomePage.h2Html);
        List<Model> list = modelService.query();
        for (Model model : list) {
            String html = "cn".equals(l) ? HomePage.modelsCnHtml : HomePage.modelsEnHtml;
            html = html.replace("$image", model.getOpusTitle());
            html = html.replace("$name", model.getName());
            html = html.replace("$height", model.getHeight());
            html = html.replace("$chest", model.getChest());
            html = html.replace("$waist", model.getWaist());
            html = html.replace("$hips", model.getHips());
            html = html.replace("$id", model.getId());
            html = html.replace("$l", "cn".equals(l) ? "cn" : "en");
            stringBuffer.append(html);
        }
        return stringBuffer.toString();
    }

    @RequestMapping("queryDetail")
    @ResponseBody
    public DetailPagePo queryDetail(String l, String userId) {

        Model model = modelService.queryOne(userId);
        DetailPagePo detailPagePo = new DetailPagePo();
        detailPagePo.setPeopleInfo(DetailPage.getPeopleInfoHtml(model, l));
        detailPagePo.setTabTitle(DetailPage.getTabTitleHtml(model, l));
        detailPagePo.setOpus(DetailPage.getOpusImagehtml(model));
        detailPagePo.setMakeup(DetailPage.getMakeUpImageHtml(model));
        detailPagePo.setVideo(DetailPage.getVideoHtml(model));
        return detailPagePo;
    }

}
