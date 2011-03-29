package com.soyostar.emulator.engine;

import com.soyostar.emulator.framework.App;
import com.soyostar.emulator.Display;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * 游戏入口 类似于j2me的midlet和android的activity
 */
public class Main extends App {

    private Display display = null;
    private GameEngine ge = null;

    public Main() {
        display = Display.getDefaultDisplay();
        ge = GameEngine.getInstance();
        ge.setMain(this);
    }

    @Override
    public void start() {
        if (ge.isRun()) {
            try {
                throw new Exception("引擎已经启动");
            } catch (Exception ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
//        ge.start();
    }

    @Override
    public void stop() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }
}
