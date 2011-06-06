package engine.script;

import engine.Game;
import engine.GameEngine;

/**
 * 脚本基类
 * @author wp_g4
 */
public abstract class Script {

    private Game game = GameEngine.getInstance().getGame();

    /**
     * 退出游戏
     */
    public void exitGame() {
        if (game.getGameEventListener() != null) {
            game.getGameEventListener().exitGame();
        }
    }

    ;

    /**
     * 执行公共事件
     * @param id 公共事件ID
     */
    public void runPublicScript(int id) {

        if (game.getGameEventListener() != null) {
            game.getGameEventListener().runPublicScript(id);
        }
    }

    ;

    /**
     * 强制等待
     * @param frameNum 等待的帧数
     */
    public void wait(int frameNum) {


        if (game.getGameEventListener() != null) {
            game.getGameEventListener().wait(frameNum);
        }
    }

    ;

    /**
     * 所有脚本实现此方法
     */
    public abstract void execute();
}
