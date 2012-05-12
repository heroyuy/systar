/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyomaker.model.map;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import javax.imageio.ImageIO;

/**
 * 图集的图像缓存，避免两个图集所用图像文件一样时重复加载，浪费内存
 * @author Administrator
 */
public class TileSetImageCache {

    private static TileSetImageCache instance = new TileSetImageCache();
    private HashMap<String, BufferedImage> imageCacheMap = new HashMap<String, BufferedImage>();

    /**
     *
     * @return
     */
    public static TileSetImageCache getInstance() {
        return instance;
    }

    /**
     *
     * @param file
     * @return
     */
    public BufferedImage addImage(String file) {
        try {
            BufferedImage image = ImageIO.read(new File(file));
            imageCacheMap.put(file, image);
            return image;
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     *
     * @param file
     * @return
     */
    public BufferedImage getImage(String file) {
        if (imageCacheMap.containsKey(file)) {
            return imageCacheMap.get(file);
        } else {
            return addImage(file);
        }
    }

    /**
     *
     */
    public void clear() {
        imageCacheMap.clear();
    }
}
