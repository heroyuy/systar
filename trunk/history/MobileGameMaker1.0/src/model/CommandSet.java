package model;

/**
 *
 * 脚本转换后开成的指令集，一个脚本对应一个指令集，一个指令集中可以有很多条指令
 */
public class CommandSet {

    public static final byte TOUCH_PAIALLEL = 1;//接触并行
    public static final byte TOUCH_SERIAL = 2;//接触串行
    public static final byte KEY_PAIALLEL = 3;//按键并行
    public static final byte KEY_SERIAL = 4;//按键串行
    public byte type = -1;//指令集类型
    public int row = -1;//指令集所在的行
    public int col = -1;//指令集所在的列
    public Command[] command = null;//指令集中的语句，一条脚本语句对应一条指令

    @Override
    public String toString() {
        String text = "";
        switch (type) {
            case TOUCH_PAIALLEL:
                text += "类型：接触并行\n";
                break;
            case TOUCH_SERIAL:
                text += "类型：接触串行\n";
                break;
            case KEY_PAIALLEL:
                text += "类型：按键并行\n";
                break;
            case KEY_SERIAL:
                text += "类型：按键串行\n";
                break;
            default:
                text += "类型：类型错误\n";
                break;
        }
        text += "所在行号：" + row + " 所在列号：" + col + "\n";
        if (command != null) {
            int length = command.length;
            for (int i = 0; i < length; i++) {
                switch (command[i].type) {
                    case Command.EXPRESSION:
                        text += i + ":" + command[i].param + "\n";
                        break;
                    case Command.IF:
                        text += i + ":IF " + command[i].param + " " + command[i].nextIndex + "\n";
                        break;
                    case Command.ENDIF:
                        text += i + ":ENDIF\n";
                        break;
                    case Command.WHILE:
                        text += i + ":WHILE " + command[i].param + " " + command[i].nextIndex + "\n";
                        break;
                    case Command.ENDWHILE:
                        text += i + ":ENDWHILE " + command[i].nextIndex + "\n";
                        break;
                    case Command.BREAK:
                        text += i + ":BREAK " + command[i].nextIndex + "\n";
                        break;
                    case Command.CONTINUE:
                        text += i + ":CONTINUE " + command[i].nextIndex + "\n";
                        break;
                    case Command.EXIT:
                        text += i + ":EXIT\n";
                        break;
                    case Command.DIALOG:
                        text += i + ":DIALOG " + command[i].param + "\n";
                        break;
                    case Command.SOUND:
                        text += i + ":SOUND " + command[i].param + "\n";
                        break;
                    case Command.WAIT:
                        text += i + ":WAIT " + command[i].param + "\n";
                        break;
                    case Command.ITEMSHOP:
                        text += i + ":ITEMSHOP " + command[i].param + "\n";
                        break;
                    case Command.EQUIPSHOP:
                        text += i + ":EQUIPSHOP " + command[i].param + "\n";
                        break;
                    case Command.GAMEOVER:
                        text += i + ":GAMEOVER\n";
                        break;
                    case Command.MAP:
                        text += i + ":MAP " + command[i].param + "\n";
                        break;
                    case Command.FACE:
                        text += i + ":FACE " + command[i].param + "\n";
                        break;
                    case Command.MOVE:
                        text += i + ":MOVE " + command[i].param + "\n";
                        break;
                    case Command.FIGHT:
                        text += i + ":FIGHT " + command[i].param + "\n";
                        break;
                }
            }
        }
        return text;
    }
}
