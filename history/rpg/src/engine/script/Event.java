package engine.script;

/**
 *
 * 游戏事件
 */
public class Event {

    public static final byte DIALOG = 0;
    public static final byte SOUND = 1;
    public static final byte WAIT = 2;
    public static final byte ITEMSHOP = 3;
    public static final byte EQUIPSHOP = 4;
    public static final byte GAMEOVER = 5;
    public static final byte MAP = 6;
    public static final byte FACE = 7;
    public static final byte MOVE = 8;
    public static final byte FIGHT = 9;
    public byte type = -1;
    public int row = -1;
    public int col = -1;
    public String[] data = null;



    protected Event(byte type, int row, int col, String[] data) {
        this.type = type;
        this.row = row;
        this.col = col;
        this.data = data;
    }
}
