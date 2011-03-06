package emulator;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
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
        AffineTransform trans = null;
        AffineTransformOp op = null;
        switch (transform) {
            case TRANS_NONE:
                g.drawImage(srcImage, 0, 0, width, height, x, y, x + width, y + height, null);
                break;
            case TRANS_MIRROR_ROT180:
                break;
            case TRANS_MIRROR:
                trans = new AffineTransform(-1, 0, 0, 1, srcImage.getWidth(), 0);// 水平翻转
                op = new AffineTransformOp(trans, AffineTransformOp.TYPE_BILINEAR);
                g.drawImage(op.filter(srcImage, null), 0, 0, width, height, x, y, x + width, y + height, null);
                break;
            case TRANS_ROT180:
                trans = new AffineTransform(-1, 0, 0, -1, srcImage.getWidth(), srcImage.getHeight());// 旋转180度
                op = new AffineTransformOp(trans, AffineTransformOp.TYPE_BILINEAR);
                g.drawImage(op.filter(srcImage, null), 0, 0, width, height, x, y, x + width, y + height, null);
                break;
            case TRANS_MIRROR_ROT270:
                break;
            case TRANS_ROT270:
                break;
            case TRANS_ROT90:
                break;
            case TRANS_MIRROR_ROT90:
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

    public static EmulatorImage rotate(EmulatorImage ei, int degree) {

        int width = ei.getWidth();
        int height = ei.getHeight();
        int type = ei.getColorModel().getTransparency();

        EmulatorImage image = new EmulatorImage(width, height, type);
        Graphics2D graphics2D = image.createGraphics();
        graphics2D.setRenderingHint(
            RenderingHints.KEY_INTERPOLATION,
            RenderingHints.VALUE_INTERPOLATION_BILINEAR);

        graphics2D.rotate(Math.toRadians(degree), width >> 1, height >> 1);
        graphics2D.drawImage(ei, 0, 0, null);

        try {
            return image;
        } finally {
            if (graphics2D != null) {
                graphics2D.dispose();
            }
        }

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
        buffImg.setRGB(0, 0, w, h, pixs, 0, pixs.length);
        return buffImg;
    }
}
