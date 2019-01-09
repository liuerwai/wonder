package com.liuxg.wonder.util;

import java.io.File;
import java.io.IOException;

public class FileUtils {

    /**
     * 获取class路径
     *
     * @return
     */
    public static String getClassPath() {

        String classPath = FileUtils.class.getClassLoader().getResource("").getPath();
        return classPath;
    }

    /**
     * 创建文件
     *
     * @param path
     * @throws IOException
     */
    public static void createFileIfNotExits(String path) throws IOException {

        File file = new File(path);
        if (!file.exists()) {
            File parent = file.getParentFile();
            parent.mkdirs();
            if (path.contains(".")) {
                file.createNewFile();
            } else {
                file.mkdir();
            }

        }
    }

    public static void main(String[] args) throws Exception {
        createFileIfNotExits("D:\\data\\xx.text");
    }
}
