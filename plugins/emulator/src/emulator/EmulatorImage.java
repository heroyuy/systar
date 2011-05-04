package emulator;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.swing.ImageIcon;

public class EmulatorImage extends BufferedImage {

    private EmulatorImage(int width, int height) {
        super(width, height, BufferedImage.TRANSLUCENT);
    }

    private EmulatorImage(int width, int height, int type) {
        super(width, height, type);
    }

    public static EmulatorImage createImage(int width, int height) {
        return new EmulatorImage(width, height);
    }
    public final static int TRANS_NONE = 0;
    public final static int TRANS_MIRROR = 2;
    public final static int TRANS_MIRROR_ROT180 = 1;
    public final static int TRANS_MIRROR_ROT270 = 4;
    public final static int TRANS_MIRROR_ROT90 = 7;
    public final static int TRANS_ROT180 = 3;
    public final static int TRANS_ROT270 = 6;
    public final static int TRANS_ROT90 = 5;
    //未处理翻转

    public static EmulatorImage createImage(EmulatorImage srcImage, int x, int y, int width, int height, int transform) {
        EmulatorImage tempImage = new EmulatorImage(width, height);
        Graphics g = tempImage.getGraphics();
        EmulatorImage temp = new EmulatorImage(width, height);
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

    public static EmulatorImage createImage(String name) throws IOException {
        Image tempImage = Toolkit.getDefaultToolkit().createImage(name);
        if (tempImage == null) {
            throw new IOException();
        }
        ImageIcon tempIi = new ImageIcon(tempImage);
        EmulatorImage tempEi = new EmulatorImage(tempIi.getIconWidth(), tempIi.getIconHeight());
        Graphics g = tempEi.getGraphics();
        g.drawImage(tempImage, 0, 0, null);
        return tempEi;
    }

    /**
     * 旋转图片
     * @param filename
     * @param angle
     * @return
     */
    public static EmulatorImage rotateImage(EmulatorImage buffimg, int angle) {
        int w = buffimg.getWidth();
        int h = buffimg.getHeight();
        //目标图片
        EmulatorImage tempimg = null;
        Graphics2D graphics2d = null;
        int type = buffimg.getColorModel().getTransparency();
        if (angle % 360 == 0) {
            return buffimg;
        } else if (angle % 180 == 0) {
            //图片形状不变
            tempimg = new EmulatorImage(w, h, type);
            graphics2d = tempimg.createGraphics();
            //Math.toRadians(angle), w / 2, h / 2, 三参数中,前者为角度，后两者为原图片左上角移动后的对齐点
            graphics2d.rotate(Math.toRadians(angle), w / 2, h / 2);
        } else if (angle % 90 == 0) {
            //图片形状变了
            tempimg = new EmulatorImage(h, w, type);
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

    public static EmulatorImage createRGBImage(int[] pixs, int w, int h, boolean flag) {
        EmulatorImage buffImg = null;
        if (flag) {
            buffImg = new EmulatorImage(w, h, BufferedImage.TYPE_INT_ARGB);
        } else {
            buffImg = new EmulatorImage(w, h, BufferedImage.TYPE_INT_RGB);
        }
        buffImg.setRGB(0, 0, w, h, pixs, 0, w);
        return buffImg;
    }

    /*
     * 图片镜像特效
     */
    public static EmulatorImage effect_mirror(EmulatorImage src) {
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

    /*******************************下面是各个图片处理函数***********************************/

    /*
     * 获取图片RGB数据，并返回大小为width*height大小的一维数组
     */
    public static int[] getPixels(EmulatorImage src) {
        int w = src.getWidth();
        int h = src.getHeight();
        int[] pixels = new int[w * h];
        src.getRGB(pixels, 0, w, 0, 0, w, h);
        return pixels;
    }
    /*
     *将pixels[]里的数据，生成一张图片，图片宽为w，高为h
     */

    public static EmulatorImage drawPixels(int[] pixels, int w, int h) {
        EmulatorImage image = EmulatorImage.createRGBImage(pixels, w, h, true);
        pixels = null;
        return image;
    }
    /*
     *调整图片大小
     *destW 调整后的宽，destH调整后的高
     */

    public static EmulatorImage effect_resizeImage(EmulatorImage src, int destW, int destH) {
        int srcW = src.getWidth();
        int srcH = src.getHeight();
        int[] destPixels = new int[destW * destH];
        int[] srcPixels = getPixels(src);
        for (int destY = 0; destY < destH; ++destY) {
            for (int destX = 0; destX < destW; ++destX) {
                int srcX = (destX * srcW) / destW;
                int srcY = (destY * srcH) / destH;
                destPixels[destX + destY * destW] = srcPixels[srcX + srcY * srcW];
            }
        }
        return drawPixels(destPixels, destW, destH);
    }

    /**
     *
     * @param img
     *            原始图片
     * @param transparent
     *            透明度 0-255之间
     * 越大越不透明，只半透明不透明的部分
     * @return 处理透明度后的图片
     */
    public static EmulatorImage effect_transparent_Other(EmulatorImage img, int transparent) {
        if (transparent < 0 || transparent > 255) {
            return img;
        }
        int srcW = img.getWidth();
        int srcH = img.getHeight();
        int[] srcPixels = getPixels(img); //函数功能 讲图片数据存入指定数组
        int r = 0;
        int g = 0;
        int b = 0;
        int a = 0;
        int argb;
        for (int i = 0; i < srcH; i++) {
            for (int ii = 0; ii < srcW; ii++) {
                argb = srcPixels[i * srcW + ii];
                a = ((argb & 0xff000000) >> 24); // alpha channel
                r = ((argb & 0x00ff0000) >> 16); // red channel
                g = ((argb & 0x0000ff00) >> 8); // green channel
                b = (argb & 0x000000ff); // blue channel
                if (a != 0) {
                    srcPixels[i * srcW + ii] = ((transparent << 24) | (r << 16) | (g << 8) | b);
                } else {
                    srcPixels[i * srcW + ii] = 0x00ffffff;
                }
            }
        }
        return drawPixels(srcPixels, srcW, srcH); //将数组转化为图片
    }
}
