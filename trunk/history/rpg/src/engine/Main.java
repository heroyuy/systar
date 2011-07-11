package engine;

import javax.microedition.midlet.*;
import model.GameData;

/**
 * 程序入口
 */
public class Main extends MIDlet {

    /**
     * 游戏引擎
     */
    private GameEngine ge = null;

    /**
     * 启动程序
     */
    public void startApp() {
        if (ge == null) {
            ge = GameEngine.getInstance();
            ge.setMain(this);
            ge.start();
        }


    }

    /**
     * 暂停程序
     */
    public void pauseApp() {
    }

    /**
     * 销毁程序
     */
    public void destroyApp(boolean unconditional) {
        System.gc();
        destroyApp(false);
        notifyDestroyed();
    }

    /**
     * 退出程序
     *
     */
    public void exit() {
        notifyDestroyed();
        destroyApp(true);
    }
}
