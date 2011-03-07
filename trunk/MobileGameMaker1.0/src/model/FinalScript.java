package model;

/**
 *
 * 脚本
 */
public class FinalScript {

    public static final byte TOUCH_PAIALLEL = 1;//接触并行
    public static final byte TOUCH_SERIAL = 2;//接触串行
    public static final byte KEY_PAIALLEL = 3;//按键并行
    public static final byte KEY_SERIAL = 4;//按键串行
    public byte type;//脚本类型
    public int row;
    public int col;
    public String[] data = {""};
}
