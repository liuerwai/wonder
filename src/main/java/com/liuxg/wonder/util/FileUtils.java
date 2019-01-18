package com.liuxg.wonder.util;

import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
     * @param path    保存文件路径
     *                使用上传的文件的文件名作为文件名
     * @return
     * @throws Exception
     */
    public static List<String> saveUploadFile(HttpServletRequest request, String path) throws Exception {
        return saveUploadFile(request, path, "");
    }

    /**
     * @param request
     * @param path    保存文件路径
     * @param name    文件名
     * @return
     * @throws Exception
     */
    public static List<String> saveUploadFile(HttpServletRequest request, String path, String name) throws Exception {

        List<String> files = new ArrayList<>();
        MultipartHttpServletRequest multipartHttpservletRequest = (MultipartHttpServletRequest) request;
        for (MultipartFile file : multipartHttpservletRequest.getFileMap().values()) {
            String origName = file.getOriginalFilename();
            // 替换文件名
            if (!StringUtils.isEmpty(name)) {
                origName = name + getFileSuffix(origName);
            }
            String fileName = path + origName;
            //导入文件目录
            File dir = new File(path);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            File destFile = new File(fileName);
            file.transferTo(destFile);
            files.add(fileName);
        }
        return files;
    }

    /**
     * 删除文件，可以是文件或文件夹
     *
     * @param fileName 要删除的文件名
     * @return 删除成功返回true，否则返回false
     */
    public static boolean delete(String fileName) {
        File file = new File(fileName);
        if (!file.exists()) {
            System.out.println("删除文件失败:" + fileName + "不存在！");
            return false;
        } else {
            if (file.isFile())
                return deleteFile(fileName);
            else
                return deleteDirectory(fileName);
        }
    }

    /**
     * 删除单个文件
     *
     * @param fileName 要删除的文件的文件名
     * @return 单个文件删除成功返回true，否则返回false
     */
    private static boolean deleteFile(String fileName) {
        File file = new File(fileName);
        // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                System.out.println("删除单个文件" + fileName + "成功！");
                return true;
            } else {
                System.out.println("删除单个文件" + fileName + "失败！");
                return false;
            }
        } else {
            System.out.println("删除单个文件失败：" + fileName + "不存在！");
            return false;
        }
    }

    /**
     * 删除目录及目录下的文件
     *
     * @param dir 要删除的目录的文件路径
     * @return 目录删除成功返回true，否则返回false
     */
    private static boolean deleteDirectory(String dir) {
        // 如果dir不以文件分隔符结尾，自动添加文件分隔符
        if (!dir.endsWith(File.separator))
            dir = dir + File.separator;
        File dirFile = new File(dir);
        // 如果dir对应的文件不存在，或者不是一个目录，则退出
        if ((!dirFile.exists()) || (!dirFile.isDirectory())) {
            System.out.println("删除目录失败：" + dir + "不存在！");
            return false;
        }
        boolean flag = true;
        // 删除文件夹中的所有文件包括子目录
        File[] files = dirFile.listFiles();
        for (int i = 0; i < files.length; i++) {
            // 删除子文件
            if (files[i].isFile()) {
                flag = deleteFile(files[i].getAbsolutePath());
                if (!flag)
                    break;
            }
            // 删除子目录
            else if (files[i].isDirectory()) {
                flag = deleteDirectory(files[i]
                        .getAbsolutePath());
                if (!flag)
                    break;
            }
        }
        if (!flag) {
            System.out.println("删除目录失败！");
            return false;
        }
        // 删除当前目录
        if (dirFile.delete()) {
            System.out.println("删除目录" + dir + "成功！");
            return true;
        } else {
            return false;
        }
    }

    public static String getFileName(String path) {
        int index = path.lastIndexOf(File.separator);
        return path.substring(index + 1, path.length());
    }

    public static String getFileSuffix(String orginName) {
        int index = orginName.lastIndexOf(".");
        return orginName.substring(index, orginName.length());
    }

    public static void main(String[] args) throws Exception {
        createFileIfNotExits("D:\\data\\xx.text");
    }
}
