package com.liuxg.wonder.controller;

import com.liuxg.wonder.constant.UploadType;
import com.liuxg.wonder.html.DetailPage;
import com.liuxg.wonder.html.HomePage;
import com.liuxg.wonder.html.ManagerPage;
import com.liuxg.wonder.po.DetailPagePo;
import com.liuxg.wonder.po.Model;
import com.liuxg.wonder.service.IModelService;
import com.liuxg.wonder.util.FileUtils;
import com.liuxg.wonder.util.TimestampUtils;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.liuxg.wonder.html.ManagerPage.getImageHtml;

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

    @RequestMapping("queryManagerModels")
    @ResponseBody
    public String queryManagerModels() {

        StringBuffer stringBuffer = new StringBuffer(ManagerPage.titleHtml);
        List<Model> list = modelService.query();
        for (Model model : list) {
            String trHtml = ManagerPage.trHtml;
            trHtml = trHtml.replace("$name", model.getName());
            trHtml = trHtml.replace("$opusTitle", ManagerPage.getImageHtml(model.getOpusTitle()));
            trHtml = trHtml.replace("$opus", ManagerPage.getImageHtml(model.getOpus().values()));
            trHtml = trHtml.replace("$makeupTitle", ManagerPage.getImageHtml(model.getMakeupTitle()));
            trHtml = trHtml.replace("$makeup", ManagerPage.getImageHtml(model.getMakeup().values()));
            trHtml = trHtml.replace("$videoTitle", ManagerPage.getImageHtml(model.getVideioTite()));
            trHtml = trHtml.replace("$video", ManagerPage.getVideoHtml(model.getVideo().values()));
            trHtml = trHtml.replace("$userId", model.getId());
            stringBuffer.append(trHtml).append("\n");
        }
        return stringBuffer.toString();
    }


    @RequestMapping("updateInfo")
    public String updateInfo(Model model) {

        try {
            if (StringUtils.isEmpty(model.getId())) {
                model.setId(TimestampUtils.getId());
                modelService.add(model);
            } else {
                Model oldModel = modelService.queryOne(model.getId());
                oldModel.setName(model.getName());
                oldModel.setBirthday(model.getBirthday());
                oldModel.setSex(model.getSex());
                oldModel.setHeight(model.getHeight());
                oldModel.setChest(model.getChest());
                oldModel.setWaist(model.getWaist());
                oldModel.setHips(model.getHips());
                oldModel.setEyeColor(model.getEyeColor());
                oldModel.setHairColor(model.getHairColor());
                oldModel.setSkinColor(model.getSkinColor());
                oldModel.setShoeSize(model.getShoeSize());
                modelService.add(oldModel);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "error.html";
        }
        return "manager.html";
    }

    @RequestMapping("queryInfo")
    @ResponseBody
    public Model queryInfo(String userId) {

        if (StringUtils.isEmpty(userId)) {
            return null;
        }
        Model model = modelService.queryOne(userId);
        return model;
    }

    @RequestMapping("upload")
    public String upload(HttpServletRequest request, String userId, String type) {

        try {
            Model model = modelService.queryOne(userId);
            String basePath = UploadType.getBathPath(type, model);
            FileUtils.saveUploadFile(request, basePath);
        } catch (Exception e) {
            return "error.html";
        }
        return "manager.html";
    }

}
