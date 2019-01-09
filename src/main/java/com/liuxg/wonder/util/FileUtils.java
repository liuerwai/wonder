package com.liuxg.wonder.util;

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
}
