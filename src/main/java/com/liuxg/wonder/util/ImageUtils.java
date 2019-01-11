package com.liuxg.wonder.util;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.file.Files;

public class ImageUtils {

    /**
     * 指定图片宽度和高度和压缩比例对图片进行压缩
     *
     * @param imgsrc     源图片地址
     * @param imgdist    目标图片地址
     * @param widthdist  压缩后图片的宽度
     * @param heightdist 压缩后图片的高度
     * @param rate       压缩的比例
     */
    public static void reduceImg(String imgsrc, String imgdist, int widthdist, int heightdist, Float rate) {
        try {
            File srcfile = new File(imgsrc);
            // 检查图片文件是否存在
            if (!srcfile.exists()) {
                System.out.println("文件不存在");
            }
            // 如果比例不为空则说明是按比例压缩
            if (rate != null && rate > 0) {
                //获得源图片的宽高存入数组中
                int[] results = getImgWidthHeight(srcfile);
                if (results == null || results[0] == 0 || results[1] == 0) {
                    return;
                } else {
                    //按比例缩放或扩大图片大小，将浮点型转为整型
                    widthdist = (int) (results[0] * rate);
                    heightdist = (int) (results[1] * rate);
                }
            }
            // 开始读取文件并进行压缩
            Image src = ImageIO.read(srcfile);

            // 构造一个类型为预定义图像类型之一的 BufferedImage
            BufferedImage tag = new BufferedImage((int) widthdist, (int) heightdist, BufferedImage.TYPE_INT_RGB);

            //绘制图像  getScaledInstance表示创建此图像的缩放版本，返回一个新的缩放版本Image,按指定的width,height呈现图像
            //Image.SCALE_SMOOTH,选择图像平滑度比缩放速度具有更高优先级的图像缩放算法。
            tag.getGraphics().drawImage(src.getScaledInstance(widthdist, heightdist, Image.SCALE_SMOOTH), 0, 0, null);

            //创建文件输出流
            FileOutputStream out = new FileOutputStream(imgdist);
            //将图片按JPEG压缩，保存到out中
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
            encoder.encode(tag);
            //关闭文件输出流
            out.close();
        } catch (Exception ef) {
            ef.printStackTrace();
        }
    }

    /**
     * 获取图片宽度和高度
     *
     * @return 返回图片的宽度
     */
    public static int[] getImgWidthHeight(File file) {

        InputStream is = null;
        BufferedImage src = null;
        int result[] = {0, 0};
        try {
            // 获得文件输入流
            is = new FileInputStream(file);
            // 从流里将图片写入缓冲图片区
            src = ImageIO.read(is);
            result[0] = src.getWidth(null); // 得到源图片宽
            result[1] = src.getHeight(null);// 得到源图片高
            is.close();  //关闭输入流
        } catch (Exception ef) {
            ef.printStackTrace();
        }

        return result;
    }

    /**
     * 压缩图片（等宽，等高）
     *
     * @param file
     * @throws Exception
     */
    public static void reduceImg(String file) throws Exception {

        // 压缩
        File srcFile = new File(file);
        File distfile = new File(file.split("\\.")[0] + "redusTo" + file.split("\\.")[1]);
        // 等宽 等高 压缩
        reduceImg(srcFile.getPath(), distfile.getPath(),
                getImgWidthHeight(srcFile)[0], getImgWidthHeight(srcFile)[1], null);
        // 删除原图片
        Files.deleteIfExists(srcFile.toPath());
        // 压缩后的图片改名为原图片名字
        Files.copy(distfile.toPath(), srcFile.toPath());
        Files.deleteIfExists(distfile.toPath());
    }

    public static void main(String[] args) throws Exception {

//        File srcfile = new File("C:\\Users\\liuxu\\Desktop\\home2.jpg");
//        File distfile = new File("C:\\Users\\liuxu\\Desktop\\home3.jpg");
//
//        System.out.println("压缩前图片大小：" + srcfile.length());
//        reduceImg(srcfile.getPath(), distfile.getPath(), getImgWidthHeight(srcfile)[0], getImgWidthHeight(srcfile)[1], null);
//        System.out.println("压缩后图片大小：" + distfile.length());

        reduceImg("C:\\Users\\liuxu\\Desktop\\home.jpg");

    }

}
