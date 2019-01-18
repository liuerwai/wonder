package com.liuxg.wonder.controller;

import com.liuxg.wonder.constant.Properties;
import com.liuxg.wonder.constant.UploadType;
import com.liuxg.wonder.html.DetailPage;
import com.liuxg.wonder.html.HomePage;
import com.liuxg.wonder.html.ManagerPage;
import com.liuxg.wonder.po.DetailPagePo;
import com.liuxg.wonder.po.Model;
import com.liuxg.wonder.service.IModelService;
import com.liuxg.wonder.util.FileUtils;
import com.liuxg.wonder.util.ImageUtils;
import com.liuxg.wonder.util.TimestampUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
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

    @RequestMapping("queryManagerModels")
    @ResponseBody
    public String queryManagerModels() {

        StringBuffer stringBuffer = new StringBuffer(ManagerPage.titleHtml);
        List<Model> list = modelService.query();
        for (Model model : list) {
            String trHtml = ManagerPage.trHtml;
            trHtml = trHtml.replace("$name", model.getName());
            trHtml = trHtml.replace("$opusTitle", ManagerPage.getImageHtml(model.getOpusTitle(), "opusTitle"));
            trHtml = trHtml.replace("$opus", ManagerPage.getImageHtml(model.getOpus().values(), "opus"));
            trHtml = trHtml.replace("$makeupTitle", ManagerPage.getImageHtml(model.getMakeupTitle(), "makeupTitle"));
            trHtml = trHtml.replace("$makeup", ManagerPage.getImageHtml(model.getMakeup().values(), "makeup"));
            trHtml = trHtml.replace("$videoTitle", ManagerPage.getImageHtml(model.getVideioTite(), "videoTitle"));
            trHtml = trHtml.replace("$video", ManagerPage.getVideoHtml(model.getVideo().values(), "video"));
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
            return "redirect:manager.html";
        }
        return "redirect:manager.html";
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
            List<String> files = FileUtils.saveUploadFile(request, basePath);
            for (String file : files) {
                // 压缩
                if (UploadType.MAKEUP_TITLE.type.equals(type) ||
                        UploadType.OPUS_Title.type.equals(type) ||
                        UploadType.VIDEO_TITLE.type.equals(type)) {
                    ImageUtils.reduceImg(file, 1500);
                } else if (UploadType.MAKEUP.type.equals(type) ||
                        UploadType.OPUS.type.equals(type) ||
                        UploadType.HOME_IMG.equals(type)) {
                    ImageUtils.reduceImg(file, 1000);
                }
                model.saveWebPath(type, file);
            }
            // 保存web路径
            modelService.add(model);
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:manager.html";
        }
        return "redirect:manager.html";
    }

    @RequestMapping("uploadHomeFile")
    public String uploadHomeFile(HttpServletRequest request, String type) {

        try {
            String name = "";
            if (UploadType.HOME_IMG.type.equals(type)) {
                name = Properties.HomeImage;
            }
            if (UploadType.INDEX_VIDEO.type.equals(type)) {
                name = Properties.IndexVideio;
            }
            List<String> files = FileUtils.saveUploadFile(request, Properties.ImagePath, name);
            for (String file : files) {
                if (UploadType.HOME_IMG.equals(type)) {
                    ImageUtils.reduceImg(file, 1500);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:manager.html";
        }
        return "redirect:manager.html";
    }

    @RequestMapping("deleteModel")
    public String deleteModel(HttpServletRequest request, String userId) {

        try {
            Model model = modelService.queryOne(userId);
            modelService.delete(model);
            FileUtils.delete(model.getImageFileBathPath());
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:manager.html";
        }
        return "redirect:manager.html";
    }

    @RequestMapping("deleteFile")
    public String deleteFile(HttpServletRequest request, String src, String userId, String type) {

        try {
            String staticPath = Properties.StaticPath;
            String fileName = staticPath + src.replace("/", File.separator);
            if (fileName.contains("model")) {
                FileUtils.delete(fileName);
                Model model = modelService.queryOne(userId);
                model.removeWebPath(type, fileName);
                modelService.add(model);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:manager.html";
        }
        return "redirect:manager.html";
    }

    public static void main(String[] args) throws Exception{


    }


}
