package engine;

import emulator.EmulatorApp;

/**
 *
 * 游戏入口 类似于j2me的midlet和android的activity
 */
public class Main extends EmulatorApp {

    private GameEngine ge = null;

    public Main() {
        if (ge == null) {
            ge = GameEngine.getInstance();
            ge.setMain(this);
        }
    }

    public void start() {
        // 启动游戏引擎
        ge.start();
    }

    public void stop() {
    }

    public void pause() {
    }

    public void resume() {
    }
}
