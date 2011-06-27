package engine;

import com.soyostar.app.KeyEvent;
import com.soyostar.app.Painter;
import com.soyostar.app.TouchEvent;
import engine.script.GameEventListener;

/**
 * 游戏抽象类，所有游戏实体必需继承自此类
 * @author g4 
 *
 */
public abstract class Game {

    private GameEventListener gameEventListener = null;

    public GameEventListener getGameEventListener() {
        return gameEventListener;
    }

    public void setGameEventListener(GameEventListener gameEventListener) {
        this.gameEventListener = gameEventListener;
    }

    /**
     * 通过脚本ID运行脚本
     * @param id
     */
    public void runScript(int id) {
        //TODO
    }

    /**
     * 开始游戏
     *
     */
    public abstract void start();

    /**
     * 更新游戏，每个心跳周期由游戏引擎调用一次，先于render(Graphics g) 被调用
     */
    public abstract void update();

    /**
     * 处理触屏事件的回调方法
     */
    public abstract void onTouchEvent(TouchEvent me);

    /**
     * 处理按键事件的回调方法
     */
    public abstract void onKeyEvent(KeyEvent ke);

    /**
     * 渲染游戏，每个心跳周期由游戏引擎调用一次
     * @param g
     */
    public abstract void render(Painter p);

    /**
     * 退出游戏
     *
     */
    public abstract void exit();
}
