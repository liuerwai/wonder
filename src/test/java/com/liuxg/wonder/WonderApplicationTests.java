package com.liuxg.wonder;

import com.liuxg.wonder.po.Model;
import com.liuxg.wonder.service.IModelService;
import com.liuxg.wonder.util.TimestampUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WonderApplicationTests {

    @Autowired
    IModelService modelService;

    @Test
    public void contextLoads() throws Exception {
       addLucy();
       addYeva();
       List list = modelService.query();
       System.out.println(list);
    }

    public void addLucy() throws Exception {
        Model model = new Model();
        model.setId("1");
        model.setName("Lucy");
        model.setBirthday("08-19");
        model.setSex("女");
        model.setHeight("179");
        model.setChest("83");
        model.setWaist("61");
        model.setHips("91");
        model.setShoeSize("39");
        model.setEyeColor("Blue");
        model.setHairColor("Blonde");
        model.setSkinColor("White");
        model.setOpusTitle(model.getImageWebBathPath() + "opus.jpg");
        model.setMakeupTitle(model.getImageWebBathPath() + "makeup.jpg");
        for (int i = 1; i <= 11; i++) {
            model.addOps(i + ".jpg", model.getOpusWebBathPath() + i + ".jpg");
        }
        for (int i = 1; i <= 1; i++) {
            model.addMakeup(i + ".jpg", model.getMakeupWebBathPath() + i + ".jpg");
        }
        modelService.add(model);
    }

    public void addYeva() throws Exception {
        Model model = new Model();
        model.setId("2");
        model.setName("Yeva");
        model.setBirthday("08-19");
        model.setSex("女");
        model.setHeight("170");
        model.setChest("81");
        model.setWaist("57");
        model.setHips("89");
        model.setShoeSize("38");
        model.setEyeColor("Blue");
        model.setHairColor("Blonde");
        model.setSkinColor("White");
        model.setOpusTitle(model.getImageWebBathPath() + "opus.jpg");
        model.setMakeupTitle(model.getImageWebBathPath() + "makeup.jpg");
        model.setVideioTite(model.getImageWebBathPath() + "video.jpg");
        for (int i = 1; i <= 13; i++) {
            model.addOps(i + ".jpg", model.getOpusWebBathPath() + i + ".jpg");
        }
        for (int i = 1; i <= 1; i++) {
            model.addMakeup(i + ".jpg", model.getMakeupWebBathPath() + i + ".jpg");
        }
        model.addVideo("video.mp4", model.getVideoWebBathPath() + "video.mp4");
        modelService.add(model);
    }

}

