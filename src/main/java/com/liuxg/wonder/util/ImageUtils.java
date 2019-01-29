package com.liuxg.wonder.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

public class ImageUtils {

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
            result[0] = src.getWidth(null);
            result[1] = src.getHeight(null);
            is.close();  //关闭输入流
        } catch (Exception ef) {
            ef.printStackTrace();
        }

        return result;
    }

    /**
     * 压缩图片
     *
     * @throws Exception
     */
    public static void reduceImg(String file, int maxWidth) throws Exception {

        File srcFile = new File(file);
        int[] widthHeight = getImgWidthHeight(srcFile);
        int width = widthHeight[0];
        int height = widthHeight[1];
        if (width > maxWidth) {
            Float rate = Float.valueOf(maxWidth) / width;
            width = Float.valueOf(width * rate).intValue();
            height = Float.valueOf(height * rate).intValue();
        }
        reduceImg(file, width, height);
    }

    /**
     * 压缩图片至固定分辨率  填充空白
     *
     * @param file
     * @throws Exception
     */
    public static void reduceImg(String file, int width, int height) throws Exception {

        File srcFile = new File(file);
        File distfile = new File(file.split("\\.")[0] + "redusTo" + file.split("\\.")[1]);
        scale(srcFile.getPath(), distfile.getPath(), width, height);
        // 删除原图片
        Files.deleteIfExists(srcFile.toPath());
        // 压缩后的图片改名为原图片名字
        Files.copy(distfile.toPath(), srcFile.toPath());
        Files.deleteIfExists(distfile.toPath());
    }


    /**
     * 缩放图片方法
     *
     * @param srcPath 要缩放的图片路径
     * @param result  缩放后的图片路径
     * @param height  目标高度像素
     * @param width   目标宽度像素
     */
    private final static void scale(String srcPath, String result, int width, int height) {

        try {
            BufferedImage bufferedImage = ImageIO.read(new File(srcPath));
            int srcWidth = bufferedImage.getWidth(null);
            int srcHeight = bufferedImage.getHeight(null);
            Image itemp = bufferedImage.getScaledInstance(srcWidth, srcHeight, bufferedImage.SCALE_SMOOTH);
            // 如果源图片大于目的图片 先将源图片缩小
//            if (srcWidth > width || srcHeight > height) {
                // 取较小的比例
                double ratioHeight = Double.valueOf(height) / srcHeight;
                double ratioWhidth = Double.valueOf(width) / srcWidth;
                double ratio = ratioHeight < ratioWhidth ? ratioHeight : ratioWhidth;
                AffineTransformOp op = new AffineTransformOp(AffineTransform//仿射转换
                        .getScaleInstance(ratio, ratio), null);//返回表示剪切变换的变换
                itemp = op.filter(bufferedImage, null);//转换源 BufferedImage 并将结果存储在目标 BufferedImage 中。
//            }
//            // 将小的源图片填充到大的纯白的目的图片中
//            BufferedImage image = new BufferedImage(width, height,
//                    BufferedImage.TYPE_INT_RGB);
//            Graphics2D g = image.createGraphics();
//            g.setColor(Color.white);
//            g.fillRect(0, 0, width, height);
//            // 再将源图片填充在白色图片中央
//            g.drawImage(itemp, (width - itemp.getWidth(null)) / 2, (height - itemp.getHeight(null)) / 2,
//                    itemp.getWidth(null), itemp.getHeight(null),
//                    Color.white, null);
//            g.dispose();
//            itemp = image;

            ImageIO.write((BufferedImage) itemp, "JPEG", new File(result));      //输出压缩图片
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {

        reduceImg("C:\\Users\\liuxu\\Desktop\\4.jpg", 1000);
    }


}
