package com.soyomaker.emulator.core;

import java.awt.AlphaComposite;
import java.awt.Composite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 * ͼ��ͼ��
 * @author wp_g4
 */
public class Image {

    /**
     * ˮƽ��ת����
     */
    public static final byte HORIZONTAL = 0;
    /**
     * ��ֱ��ת����
     */
    public static final byte VERTICAL = 1;
    private BufferedImage content = null;

    private Image() {
    }

    java.awt.Image getContent() {
        return content;
    }
    private Painter painter = null;

    /**
     * �������ڻ���ͼ���ͼ��������
     * @return ����ͼ���ͼ�������ģ����ʣ�
     */
    public Painter getPainter() {
        if (painter == null) {
            painter = new Painter(content.getGraphics());
        }
        return painter;
    }

    /**
     * ���� Image �Ŀ�ȡ�
     * @return ��Image�Ŀ�ȡ�
     */
    public int getWidth() {
        return content.getWidth();
    }

    /**
     * ���� Image �ĸ߶ȡ�
     * @return ��Image�ĸ߶ȡ�
     */
    public int getHeight() {
        return content.getHeight();
    }

    /**
     * ���ش�ָ���ļ���ȡ������ݵ�ͼ��
     * @param fileName �Կ�ʶ���ļ���ʽ��������ݵ��ļ���
     * @return ��ָ���ļ���ȡ������ݵ�ͼ��
     */
    public static Image createImage(String fileName) {
        Image image = new Image();
        try {
            image.content = ImageIO.read(new File(fileName));
        } catch (IOException ex) {
            Logger.getLogger(Image.class.getName()).log(Level.SEVERE, null, ex);
        }
        return image;
    }

    /**
     * ����ָ����С�Ŀհ�ͼ�񣬱���͸��
     * @param width ��ͼ��Ŀ��
     * @param height ��ͼ��ĸ߶�
     * @return �����Ŀհ�ͼ��
     */
    public static Image createImage(int width, int height) {
        Image img = new Image();
        img.content = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        return img;
    }

    /**
     * ������ָ���������������ͼ��
     * @param src Դͼ��
     * @param x ָ�������������Ͻǵ� x ���
     * @param y ָ�������������Ͻǵ� y ���
     * @param width ָ����������Ŀ��
     * @param height ָ����������ĸ߶�
     * @return ��ͼ��
     */
    public static Image copyImage(Image src, int x, int y, int width, int height) {
        Image res = new Image();
        res.content = src.content.getSubimage(x, y, width, height);
        return res;
    }

    /**
     * ����ָ��ͼ��İ�͸��汾
     * @param src ԴͼƬ
     * @param alpha ͸��� 0-100
     * @return ָ��ͼ��İ�͸��汾
     */
    public static Image createAlphaImage(Image src, int alpha) {
        if (src == null) {
            return null;
        }
        if (alpha > 100) {
            alpha = 100;
        } else if (alpha < 0) {
            alpha = 0;
        }
        Image res = Image.createImage(src.getWidth(), src.getHeight());
        Graphics2D g = (Graphics2D) res.content.getGraphics();
        Composite alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) (1.0 * alpha / 100)); //ָ��͸���
        g.setComposite(alphaComposite);
        g.drawImage(src.content, 0, 0, null);
        g.dispose();
        return res;
    }

    /**
     * ��תͼƬ���Ƕ�ֻ����90�ı������ɸ����Ƕ�Ϊ��ʱ˳ʱ����ת��Ϊ��ʱ��ʱ����ת��
     * @param src Դͼ��
     * @param angle Ҫ��ת�Ķ���
     * @return ��ת���ͼ��
     */
    public static Image rotateImage(Image src, int angle) {
        while (angle < 0) {
            angle += 360;
        }
        if (angle % 90 != 0) {
            throw new IllegalArgumentException("�Ƕ�ֻ����90������");
        }
        Image res = null;
        for (int i = 0, num = angle / 90; i < num; i++) {
            if (res == null) {
                res = src.rotateImageToCW90();
            } else {
                res = res.rotateImageToCW90();
            }
        }
        return res;
    }

    /**
     * ���ͼ��
     * @param src Դͼ��
     * @param width ��ź��ͼ����
     * @param height ��ź��ͼ��߶�
     * @return ��ź��ͼ��
     */
    public static Image zoomImage(Image src, int width, int height) {
        Image res = createImage(width, height);
        Graphics g = res.content.getGraphics();
        g.drawImage(src.content, 0, 0, width, height, null);
        g.dispose();
        return res;
    }

    /**
     * ��תͼ��
     * @param src Դͼ��
     * @param type ��ת����  HORIZONTAL��ˮƽ��ת������ VERTICAL����ֱ��ת��
     * @return ��ת���ͼ��
     */
    public static Image flipImage(Image src, byte type) {
        int w = src.getWidth();
        int h = src.getHeight();
        Image res = createImage(w, h);
        Graphics g = res.content.getGraphics();

        switch (type) {
            case Image.HORIZONTAL:
                g.drawImage(src.content, 0, 0, w, h, w, 0, 0, h, null);
                g.dispose();
                break;
            case Image.VERTICAL:
                Graphics2D g2d = (Graphics2D) g;
                g2d.drawImage(src.content, 0, 0, w, h, 0, h, w, 0, null);
                g2d.dispose();
                break;
            default:
                throw new IllegalArgumentException("��ת����ֻ����0��ˮƽ��ת������1����ֱ��ת��");
        }
        return res;
    }

    /**
     * ������˳ʱ����תͼƬ90��
     */
    private Image rotateImageToCW90() {
        int w = getWidth();
        int h = getHeight();
        Image res = createImage(h, w);
        Graphics2D g2d = (Graphics2D) res.content.getGraphics();
        g2d.rotate(Math.toRadians(90), h, 0);
        g2d.drawImage(content, h, 0, w, h, null);
        g2d.dispose();
        return res;
    }
}
