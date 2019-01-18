package com.liuxg.wonder.util;


import com.liuxg.wonder.constant.Properties;
import com.liuxg.wonder.po.Model;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class SerializerUtills {

    public final static String modelPath = Properties.PackagePath + "data/modelMap.ser";

    public static void main(String[] args) {

        try {
            Map<String, Model> map = getModel();
            Model model = new Model();
            model.setId("51");
            map.put(model.getId(), model);
            saveModel(map);
            map = getModel();
            System.out.println(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 保存序列化对象
     *
     * @param object
     * @param path
     * @return
     */
    public static synchronized int save(Object object, String path) {

        try {
            FileUtils.createFileIfNotExits(path);
            FileOutputStream fileOut =
                    new FileOutputStream(path);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(object);
            out.close();
            fileOut.close();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        return 1;
    }

    /**
     * 查询序列化对象
     *
     * @param path
     * @return
     */
    public static synchronized Object get(String path) {

        try {
            FileInputStream fileIn = new FileInputStream(path);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            Object object = in.readObject();
            in.close();
            fileIn.close();
            return object;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 获取模特信息
     *
     * @return
     */
    public static Map<String, Model> getModel() {

        Map<String, Model> map = (Map<String, Model>) SerializerUtills.get(SerializerUtills.modelPath);
        if (map == null) {
            map = new HashMap<>();
        }
        return map;
    }

    /**
     * 保存模特信息
     *
     * @param object
     * @return
     */
    public static int saveModel(Map<String, Model> object) {

        return save(object, modelPath);
    }


}
