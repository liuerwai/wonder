package com.liuxg.wonder.util;


import com.liuxg.wonder.po.Model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimestampUtils {

    public static String getId() {
        SimpleDateFormat sdf = new SimpleDateFormat("S");
        Date now = new Date();
        String nowTimeStr = sdf.format(now);
        return ((int) (Math.random() * 90) + 10) + nowTimeStr + ((int) (Math.random() * 90) + 10);
    }


    public static void main(String[] args){
        Model model = new Model();
        model.setName("Yeva");
        model.setId(getId());

        System.out.println(model.getOpusFileBathPath());
        System.out.println(model.getOpusWebBathPath());



    }
}

