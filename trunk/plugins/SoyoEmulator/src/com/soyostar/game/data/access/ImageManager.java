package com.soyostar.game.data.access;

import com.soyostar.ui.Image;
import java.io.IOException;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author wp_g4
 */
public class ImageManager {

    private String root = "res/";
    /**
     * 图片仓库
     */
    private Hashtable imageStore = null;

    public ImageManager() {
        imageStore = new Hashtable();
    }

    /**
     * 释放图片
     * @param path
     */
    public void releaseImage(String path) {
        ImageUnit iu = (ImageUnit) imageStore.get(path);
        if (iu != null) {
            iu.num--;
            //释放图片的策略可以再讨论
            if (iu.num == 0) {
                iu.image = null;
                iu.path = null;
                System.gc();
            }
        }
    }

    /**
     * 获取图片
     * @param path
     * @return
     */
    public Image getImage(String path) {
        Image image = null;
        ImageUnit iu = (ImageUnit) imageStore.get(path);
        if (iu == null) {
            try {
                //图片不在仓库中
                image = Image.createImage(root + path);
                iu = new ImageUnit();
                iu.path = path;
                iu.image = image;
                iu.num++;
                imageStore.put(path, iu);
            } catch (IOException ex) {
                Logger.getLogger(ImageManager.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            //图片在仓库中
            iu.num++;
            image = iu.image;
        }
        return image;
    }

    private class ImageUnit {

        /**
         * 图片路径
         */
        public String path = null;
        /**
         * 图片
         */
        public Image image = null;
        /**
         * 使用次数，表示当前正在使用本图片的对象的个数
         */
        public int num = 0;
    }
}
