package com.soyostar.game.model.manager;

import com.soyostar.ui.Image;
import java.io.IOException;
import java.util.Hashtable;

/**
 *
 * 图像管理器
 */
public class ImageManager {

    private static ImageManager instance = new ImageManager();

    public static ImageManager getInstance() {
        return instance;
    }

    private ImageManager() {
    }
    private Hashtable imageList = new Hashtable();

    public Image getImage(String name) {
        Image image = null;
        image = (Image) imageList.get(name);
        if (image == null) {
            try {
                image = Image.createImage(name);
                imageList.put(name, image);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return image;
    }
}
