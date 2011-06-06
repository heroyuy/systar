package engine.script;

/**
 * 游戏事件接口
 * @author wp_g4
 */
public interface GameEventListener {
    //游戏流程类=====================================

    /**
     * 退出游戏
     */
    public void exitGame();

    /**
     * 执行公共事件
     * @param id 公共事件ID
     */
    public void runPublicScript(int id);

    /**
     * 强制等待
     * @param frameNum 等待的帧数
     */
    public void wait(int frameNum);
}
