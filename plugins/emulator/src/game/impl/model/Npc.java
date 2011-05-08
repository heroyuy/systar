package game.impl.model;

/**
 *
 * @author Administrator
 */
public class Npc extends Sprite {

    /**
     * 可穿透性
     */
    public boolean penetrable = false;
    /**
     * 运动类型
     */
    public byte moveType = 0;
    public byte scriptType = -1;
    public int scriptNum = -1;
    public String[] scripts = null;
}
