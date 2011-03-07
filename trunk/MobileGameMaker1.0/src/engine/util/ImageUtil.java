/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package engine.util;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
//import org.junit.*;
//import static org.junit.Assert.*;
/**
 * 图像处理工具类。
 *
 * @version 1.00 2010-1-15
 * @since 1.5
 * @author ZhangShixi
 */
public class ImageUtil {

    public static BufferedImage effect_transparent(final Image img, int transparent) {
        int srcW = img.getWidth(null);
        int srcH = img.getHeight(null);
        int srcPixels[] = new int[srcW * srcH];
        BufferedImage temImg = new BufferedImage(srcW, srcH, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = temImg.createGraphics();
        g2d.drawImage(img, 0, 0, null);
        temImg.getRGB(0, 0, srcW, srcH, srcPixels, 0, srcW);
        if (transparent < 0 || transparent > 255) {
            return temImg;
        }
        for (int i = 0; i < srcPixels.length; i++) {
            int a = srcPixels[i] >> 24;
            srcPixels[i] = (transparent << 24) | (srcPixels[i] & 0x00ffffff);
        }
        BufferedImage newImg = new BufferedImage(srcW, srcH, BufferedImage.TYPE_INT_ARGB);
        newImg.setRGB(0, 0, srcW, srcH, srcPixels, 0, srcW);
        return newImg;
    }

    //半透明 
    public static BufferedImage effect_transparent(final BufferedImage img, int transparent) {
        if (img == null) {
            return null;
        }
        if (transparent < 0 || transparent > 255) {
            return img;
        }
        int srcW = img.getWidth();
        int srcH = img.getHeight();
        int srcPixels[] = new int[srcW * srcH];
        img.getRGB(0, 0, srcW, srcH, srcPixels, 0, srcW);
        for (int i = 0; i < srcPixels.length; i++) {
            int a = srcPixels[i] >> 24;
            if (a != 0) {//只半透明不透明的
                srcPixels[i] = (transparent << 24) | (srcPixels[i] & 0x00ffffff);
            }
        }
        BufferedImage newImg = new BufferedImage(srcW, srcH, BufferedImage.TYPE_INT_ARGB);
        newImg.setRGB(0, 0, srcW, srcH, srcPixels, 0, srcW);
        return newImg;
    }
//    @Test
//    public static void test(){
//        assertEquals(2,3);
////        System.out.println("2 2");
//    }
    /**
     * 旋转图像。
     * @param bufferedImage 图像。
     * @param degree 旋转角度。
     * @return 旋转后的图像。
     */

    public static BufferedImage rotateImage(
            final BufferedImage bufferedImage, final int degree) {
        int width = bufferedImage.getWidth();
        int height = bufferedImage.getHeight();
        int type = bufferedImage.getColorModel().getTransparency();

        BufferedImage image = new BufferedImage(width, height, type);
        Graphics2D graphics2D = image.createGraphics();
        graphics2D.setRenderingHint(
                RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BILINEAR);

        graphics2D.rotate(Math.toRadians(degree), width >> 1, height >> 1);
        graphics2D.drawImage(bufferedImage, 0, 0, null);

        try {
            return image;
        } finally {
            if (graphics2D != null) {
                graphics2D.dispose();
            }
        }
    }

    /**
     * 将图像按照指定的比例缩放。
     * 比如需要将图像放大20%，那么调用时scale参数的值就为20；如果是缩小，则scale值为-20。
     * @param bufferedImage 图像。
     * @param scale 缩放比例。
     * @return 缩放后的图像。
     */
    public static BufferedImage resizeImageScale(
            final BufferedImage bufferedImage, final int scale) {
        if (scale == 0) {
            return bufferedImage;
        }

        int width = bufferedImage.getWidth();
        int height = bufferedImage.getHeight();

        int newWidth = 0;
        int newHeight = 0;

        double nowScale = (double) Math.abs(scale) / 100;
        if (scale > 0) {
            newWidth = (int) (width * (1 + nowScale));
            newHeight = (int) (height * (1 + nowScale));
        } else if (scale < 0) {
            newWidth = (int) (width * (1 - nowScale));
            newHeight = (int) (height * (1 - nowScale));
        }

        return resizeImage(bufferedImage, newWidth, newHeight);
    }

    /**
     * 将图像缩放到指定的宽高大小。
     * @param bufferedImage 图像。
     * @param width 新的宽度。
     * @param height 新的高度。
     * @return 缩放后的图像。
     */
    public static BufferedImage resizeImage(
            final BufferedImage bufferedImage,
            final int width, final int height) {
        int type = bufferedImage.getColorModel().getTransparency();
        BufferedImage image = new BufferedImage(width, height, type);

        Graphics2D graphics2D = image.createGraphics();
        graphics2D.setRenderingHint(
                RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BILINEAR);

        graphics2D.drawImage(bufferedImage, 0, 0, width, height, 0, 0,
                bufferedImage.getWidth(), bufferedImage.getHeight(), null);

        try {
            return image;
        } finally {
            if (graphics2D != null) {
                graphics2D.dispose();
            }
        }
    }

    /**
     * 将图像以指定的格式进行输出，调用者需自行关闭输出流。
     * @param bufferedImage 图像。
     * @param output 输出流。
     * @param formatName 图片格式名称。
     * @throws java.io.IOException IO异常。
     */
    public static void writeImage(final BufferedImage bufferedImage,
            final OutputStream output, final String formatName)
            throws IOException {
        ImageIO.write(bufferedImage, formatName, output);
    }

    /**
     * 将图像以指定的格式进行输出，调用者需自行关闭输出流。
     * @param bufferedImage 图像。
     * @param output 文件。
     * @param formatName 图片格式名称。
     * @throws java.io.IOException IO异常。
     */
    public static void writeImage(final BufferedImage bufferedImage,
            final File output, final String formatName)
            throws IOException {
        ImageIO.write(bufferedImage, formatName, output);
    }
}
