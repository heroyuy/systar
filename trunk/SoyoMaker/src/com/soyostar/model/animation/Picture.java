/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyostar.model.animation;

import com.soyostar.proxy.SoftProxy;
import java.awt.Image;
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

    public BufferedImage getSourceImage() {
        return sourceImage;
    }

    public void setSourceImage(BufferedImage sourceImage) {
        this.sourceImage = sourceImage;
    }

    public String getSourceImageFile() {
        return sourceImageFile;
    }

    public void setSourceImageFile(String file) throws IOException {
        this.sourceImageFile = file;
        Image image = ImageIO.read(new File(SoftProxy.getInstance().getCurProject().getPath()
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

    public ArrayList<Tile> getTiles() {
        return tiles;
    }

    public void setTiles(ArrayList<Tile> tiles) {
        this.tiles = tiles;
    }
    String sourceImageFile = "";//源图片位置
    ArrayList<Tile> tiles = new ArrayList<Tile>();//切割得到的图块集
}
