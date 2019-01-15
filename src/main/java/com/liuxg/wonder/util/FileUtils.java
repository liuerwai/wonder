package com.liuxg.wonder.util;

import com.liuxg.wonder.constant.UploadType;
import com.liuxg.wonder.po.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Random;

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

    /**
     * 保存上传的文件
     *
     * @param request
     * @return
     */
    public static void saveUploadFile(HttpServletRequest request, String path) throws Exception {

        MultipartHttpServletRequest multipartHttpservletRequest = (MultipartHttpServletRequest) request;
        for (MultipartFile file : multipartHttpservletRequest.getFileMap().values()) {
            String origName = file.getOriginalFilename();
            String fileName = path + origName;
            //导入文件目录
            File dir = new File(path);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            File destFile = new File(fileName);
            file.transferTo(destFile);
        }
    }

    public static void main(String[] args) throws Exception {
        createFileIfNotExits("D:\\data\\xx.text");
    }
}
