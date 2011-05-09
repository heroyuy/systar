package game.data;

import emulator.ui.EmulatorImage;
import engine.GameEngine;
import game.AbModel;
import game.RpgGame;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class ImageManager extends AbModel {

    private RpgGame rpgGame = (RpgGame) GameEngine.getInstance().getGame();
    /**
     * 超时时间，不被使用的图片在60秒后自动移除
     */
    private final static int TIMEOUT = 60000;
    private HashMap<String, ImageNode> imageList = new HashMap<String, ImageNode>();

    public EmulatorImage getImage(String path) {
        if (!imageList.containsKey(path)) {
            ImageNode in = new ImageNode();
            in.path = path;
            try {
                in.image = EmulatorImage.createImage(path);
            } catch (IOException ex) {
                Logger.getLogger(ImageManager.class.getName()).log(Level.SEVERE, null, ex);
            }
            imageList.put(path, in);
        }
        imageList.get(path).useTimes++;
        return imageList.get(path).image;
    }

    public void releaseImage(String path) {
        imageList.get(path).useTimes--;
        if (imageList.get(path).useTimes == 0) {
            imageList.get(path).time = rpgGame.getCurTime();
        }
    }

    public void update() {
        for (ImageNode in : imageList.values()) {
            if (in.useTimes <= 0 && rpgGame.getCurTime() - in.time >= TIMEOUT) {
                imageList.remove(in.path);
            }
        }
    }

    private class ImageNode {

        public String path = null;
        public int useTimes = 0;
        /**
         * 图片使用次数变为0的时间
         */
        public long time = 0;
        public EmulatorImage image = null;
    }
}
