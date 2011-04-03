package com.soyostar.ui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.swing.ImageIcon;

/**
 * Image类
 * 实现如下功能：
 * 1.根据图片路径创建Image
 * 2.创建指定大小的Image
 * 3.创建源Image的部分拷贝
 * 4.获取Image的Painter对象
 * 5.获取Image宽度
 * 6.获取Image高度
 * 7.旋转
 * 8.翻转
 * 9.缩放
 */
public class Image extends BufferedImage {

    public final static int TRANS_NONE = 0;
    public final static int TRANS_MIRROR = 2;
    public final static int TRANS_MIRROR_ROT180 = 1;
    public final static int TRANS_MIRROR_ROT270 = 4;
    public final static int TRANS_MIRROR_ROT90 = 7;
    public final static int TRANS_ROT180 = 3;
    public final static int TRANS_ROT270 = 6;
    public final static int TRANS_ROT90 = 5;
    private Painter painter = null;

    /**
     * 
     * @param width
     * @param height
     */
    private Image(int width, int height) {
        super(width, height, BufferedImage.TRANSLUCENT);
        painter = new Painter();
        painter.setGraphics(super.getGraphics());
    }

    /**
     * 1、根据图片路径创建Image
     * @param name 图片路径
     * @return image对象
     * @throws IOException
     */
    public static Image createImage(String name) throws IOException {
        //待优化
        java.awt.Image tempImage = Toolkit.getDefaultToolkit().createImage(name);
        if (tempImage == null) {
            throw new IOException();
        }
        ImageIcon tempIi = new ImageIcon(tempImage);
        Image tempEi = new Image(tempIi.getIconWidth(), tempIi.getIconHeight());
        Graphics g = tempEi.getGraphics();
        g.drawImage(tempImage, 0, 0, null);
        return tempEi;
    }

    /**
     * 2、创建指定大小的Image
     * @return image对象
     */
    public static Image createImage(int width, int height) {
        return new Image(width, height);
    }

    /**
     * 2、创建源Image的部分拷贝
     * @return image对象
     */
    public static Image createImage(Image src, int x, int y, int width, int height) {
        Image image = new Image(width, height);
        Painter p=image.getPainter();
        p.drawRegion(src,x,y,width,height,0,0,Painter.LT);
        return image;
    }

    public Painter getPainter() {
        return painter;
    }

    @Override
    public final Graphics getGraphics() {
        return null;
    }

    //未处理翻转
    public static Image createImage(Image srcImage, int x, int y, int width, int height, int transform) {
        Image tempImage = new Image(width, height);
        Graphics g = tempImage.getGraphics();
        Image temp = new Image(width, height);
        Graphics gg = temp.getGraphics();
        gg.drawImage(srcImage, 0, 0, width, height, x, y, x + width, y + height, null);
        switch (transform) {
            case TRANS_NONE:
                g.drawImage(temp, 0, 0, null);
                break;
            case TRANS_MIRROR_ROT180:
                g.drawImage(rotateImage(effect_mirror(temp), 180), 0, 0, null);
                break;
            case TRANS_MIRROR:
                g.drawImage(effect_mirror(temp), 0, 0, null);
                break;
            case TRANS_ROT180:
                g.drawImage(rotateImage(temp, 180), 0, 0, null);
                break;
            case TRANS_MIRROR_ROT270:
                g.drawImage(rotateImage(effect_mirror(temp), 270), 0, 0, null);
                break;
            case TRANS_ROT270:
                g.drawImage(rotateImage(temp, 270), 0, 0, null);
                break;
            case TRANS_ROT90:
                g.drawImage(rotateImage(temp, 90), 0, 0, null);
                break;
            case TRANS_MIRROR_ROT90:
                g.drawImage(rotateImage(effect_mirror(temp), 90), 0, 0, null);
                break;
        }

        return tempImage;
    }

    /**
     * 旋转图片
     * @param filename
     * @param angle
     * @return
     */
    public static Image rotateImage(Image buffimg, int angle) {
        int w = buffimg.getWidth();
        int h = buffimg.getHeight();
        //目标图片
        Image tempimg = null;
        Graphics2D graphics2d = null;
        int type = buffimg.getColorModel().getTransparency();
        if (angle % 360 == 0) {
            return buffimg;
        } else if (angle % 180 == 0) {
            //图片形状不变
            tempimg = new Image(w, h);
            graphics2d = tempimg.createGraphics();
            //Math.toRadians(angle), w / 2, h / 2, 三参数中,前者为角度，后两者为原图片左上角移动后的对齐点
            graphics2d.rotate(Math.toRadians(angle), w / 2, h / 2);
        } else if (angle % 90 == 0) {
            //图片形状变了
            tempimg = new Image(h, w);
            graphics2d = tempimg.createGraphics();
            //使之顺时针为-,逆时针为+
            angle = -angle;
            //Math.toRadians(angle),x,y, 前者为角度，后两者为原图片 移动后的对齐点
            if (angle < 0)//顺时针旋转-90(h/2,h/2)
            {
                graphics2d.rotate(Math.toRadians(angle), w / 2, w / 2);
            } else//逆时针旋转+90(w/2,w/2)
            {
                graphics2d.rotate(Math.toRadians(angle), h / 2, h / 2);
            }
        } else {
            return buffimg;
        }
        //把buffimg写到目标图片中去
        graphics2d.drawImage(buffimg, 0, 0, null);
        graphics2d.dispose();
        buffimg = tempimg; // 让用于显示的缓冲区图像指向过滤后的图像
        return buffimg;
    }

    public void getRGB(int[] rgbData, int offset, int scanlength, int x, int y, int width, int height) {
        this.getRGB(x, y, width, height, rgbData, offset, scanlength);
    }

    public static Image createRGBImage(int[] pixs, int w, int h, boolean flag) {
        Image buffImg = null;
        if (flag) {
            buffImg = new Image(w, h, BufferedImage.TYPE_INT_ARGB);
        } else {
            buffImg = new Image(w, h, BufferedImage.TYPE_INT_RGB);
        }
        buffImg.setRGB(0, 0, w, h, pixs, 0, w);
        return buffImg;
    }

    /*
     * 图片镜像特效
     */
    private static Image effect_mirror(Image src) {
        int srcW = src.getWidth();
        int srcH = src.getHeight();
        int[] srcPixels = getPixels(src);
        int len;
        int temp;
        for (int i = 0; i < srcH; i++) {
            len = (i + 1) * srcW;
            for (int ii = 0; ii < srcW / 2; ii++) {
                temp = srcPixels[i * srcW + ii];
                srcPixels[i * srcW + ii] = srcPixels[len - 1 - ii];
                srcPixels[len - 1 - ii] = temp;
            }
        }
        return drawPixels(srcPixels, srcW, srcH);
    }

    /*
     * 获取图片RGB数据，并返回大小为width*height大小的一维数组
     */
    private static int[] getPixels(Image src) {
        int w = src.getWidth();
        int h = src.getHeight();
        int[] pixels = new int[w * h];
        src.getRGB(pixels, 0, w, 0, 0, w, h);
        return pixels;
    }

    /*
     *将pixels[]里的数据，生成一张图片，图片宽为w，高为h
     */
    private static Image drawPixels(int[] pixels, int w, int h) {
        Image image = Image.createRGBImage(pixels, w, h, true);
        pixels = null;
        return image;
    }
}
