/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyomaker.model.animation;

import com.soyomaker.AppData;
import com.soyomaker.util.PaintParameter;
import java.awt.AlphaComposite;
import java.awt.Composite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

/**
 *
 * @author Administrator
 */
public class Picture {

    private BufferedImage sourceImage;
    private AppData data = AppData.getInstance();
    private String sourceImageFile = "";//源图片位置
    private ArrayList<Clip> tiles = new ArrayList<Clip>();//切割得到的图块集

    /**
     *
     * @return
     */
    public BufferedImage getSourceImage() {
        return sourceImage;
    }

    /**
     *
     * @param sourceImage
     */
    public void setSourceImage(BufferedImage sourceImage) {
        this.sourceImage = sourceImage;
    }

    /**
     *
     * @return
     */
    public String getSourceImageFile() {
        return sourceImageFile;
    }

    /**
     *
     * @param file
     * @throws IOException
     */
    public void setSourceImageFile(String file) throws IOException {
        this.sourceImageFile = file;
        Image image = ImageIO.read(new File(AppData.getInstance().getCurProject().getPath()
                + File.separator + "image" + File.separator + "animation" + File.separator + file));
        if (image == null) {
            throw new IOException("Failed to load " + file);
        }
        sourceImage = new BufferedImage(
                image.getWidth(null),
                image.getHeight(null),
                BufferedImage.TYPE_INT_ARGB);
        sourceImage.getGraphics().drawImage(image, 0, 0, null);
    }
    //参数 g-画笔 par-绘制参数 x,y-要绘制的位置 frameZoom-帧引起的缩放

    /**
     *
     * @param g
     * @param pai
     * @param x
     * @param y
     */
    public void paint(Graphics g, PaintParameter pai, float x, float y) {
        Graphics2D g2d = (Graphics2D) g;
        Composite composite = g2d.getComposite();

        BufferedImage bImage = sourceImage.getSubimage(pai.getRec().x, pai.getRec().y, pai.getRec().width, pai.getRec().height);
        g2d.rotate(Math.toRadians(pai.getRotate()), x + pai.getRec().width * pai.getZoom() / 2, y + pai.getRec().height * pai.getZoom() / 2);
        g2d.setComposite(AlphaComposite.getInstance(
                AlphaComposite.SRC_ATOP, (255 - pai.getTransparency() * 1.0f) / 255));
        if (pai.isMirror()) {
            AffineTransform transform = new AffineTransform(-1, 0, 0, 1, bImage.getWidth() - 1, 0);
            AffineTransformOp op = new AffineTransformOp(transform, AffineTransformOp.TYPE_BILINEAR);
            bImage = op.filter(bImage, null);
        }
        g2d.drawImage(bImage, Math.round(x), Math.round(y),
                Math.round(pai.getRec().width * pai.getZoom()), Math.round(pai.getRec().height * pai.getZoom()), null);

        g2d.setComposite(composite);
        g2d.rotate(-Math.toRadians(pai.getRotate()), x + pai.getRec().width * pai.getZoom() / 2, y + pai.getRec().height * pai.getZoom() / 2);
    }
    //按照原样进行缩放预览

    /**
     *
     * @param g
     * @param x
     * @param y
     * @param w
     * @param h
     */
    public void paintPreview(Graphics g, int x, int y, int w, int h) {
        //小于时，居中显示原图，否则按比例压缩显示
        if (sourceImage.getWidth() <= w && sourceImage.getHeight() <= h) {
            g.drawImage(sourceImage, x + (w - sourceImage.getWidth()) / 2, y + (h - sourceImage.getHeight()) / 2, null);
        } else if (sourceImage.getWidth() <= w && sourceImage.getHeight() > h) {
            double ratio = sourceImage.getHeight() * 1.0 / sourceImage.getWidth();
            int newW = (int) (h / ratio);
            g.drawImage(sourceImage.getScaledInstance(newW, h, BufferedImage.SCALE_FAST), x + (w - newW) / 2, y, null);
        } else if (sourceImage.getWidth() > w && sourceImage.getHeight() <= h) {
            double ratio = sourceImage.getWidth() * 1.0 / sourceImage.getHeight();
            int newH = (int) (w / ratio);
            g.drawImage(sourceImage.getScaledInstance(w, newH, BufferedImage.SCALE_FAST), x, y + (h - newH) / 2, null);
        } else if (sourceImage.getWidth() > w && sourceImage.getHeight() > h) {
            if (sourceImage.getWidth() > sourceImage.getHeight()) {
                double ratio = sourceImage.getWidth() * 1.0 / sourceImage.getHeight();
                int newH = (int) (w / ratio);
                g.drawImage(sourceImage.getScaledInstance(w, (int) (w / ratio), BufferedImage.SCALE_FAST), x, y + (h - newH) / 2, null);
            } else if (sourceImage.getWidth() == sourceImage.getHeight()) {
                g.drawImage(sourceImage.getScaledInstance(w, h, BufferedImage.SCALE_FAST), x, y, null);
            } else {
                double ratio = sourceImage.getHeight() * 1.0 / sourceImage.getWidth();
                int newW = (int) (h / ratio);
                g.drawImage(sourceImage.getScaledInstance((int) (h / ratio), h, BufferedImage.SCALE_FAST), x + (w - newW) / 2, y, null);
            }
        }
    }

    /**
     *
     * @return
     */
    public ArrayList<Clip> getTiles() {
        return tiles;
    }

    /**
     *
     * @param id
     * @return
     */
    public Clip getTile(int id) {
        if (id < 0 || id >= tiles.size()) {
//            System.out.println("id:" + id + " size:" + tiles.size());
            return null;
        }
        return tiles.get(id);
    }

    /**
     *
     * @param tiles
     */
    public void setTiles(ArrayList<Clip> tiles) {
        this.tiles = tiles;
    }

    /**
     *
     * @param tile
     */
    public void addTile(Clip tile) {
        tile.setPicture(this);
        tiles.add(tile);
    }

    /**
     *
     * @param id
     */
    public void removeTile(int id) {
        tiles.remove(id);
    }
}
