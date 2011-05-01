package game.manager;

import emulator.EmulatorImage;
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

    public EmulatorImage getImage(String name) {
        EmulatorImage image = null;
        image = (EmulatorImage) imageList.get(name);
        if (image == null) {
            try {
                image = EmulatorImage.createImage(name);
                imageList.put(name, image);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return image;
    }
}
