package com.soyostar.emulator.engine;

import com.soyostar.emulator.framework.App;
import com.soyostar.ui.Display;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * 游戏入口 类似于j2me的midlet和android的activity
 */
public class Main extends App {

    private GameEngine ge = null;

    public Main() {
        ge = GameEngine.getInstance();
        ge.setMain(this);
    }

    @Override
    public void start() {
        System.out.println("startgame");
        if (ge.isRun()) {
            try {
                throw new Exception("引擎已经启动");
            } catch (Exception ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            
        ge.start();
        }
    }

    @Override
    public void stop() {
        ge.exit();
    }

    @Override
    public void pause() {
        ge.pause();
    }

    @Override
    public void resume() {
        ge.resume();
    }
}
