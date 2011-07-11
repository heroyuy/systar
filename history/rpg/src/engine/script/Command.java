package engine.script;

/**
 *
 * 指令，一条脚本语句对应一条指令
 */
public final class Command {

    //流程控制类
    public static final byte EXPRESSION = 0;
    public static final byte IF = 1;
    public static final byte ENDIF = 2;
    public static final byte WHILE = 3;
    public static final byte ENDWHILE = 4;
    public static final byte BREAK = 5;
    public static final byte CONTINUE = 6;
    public static final byte EXIT = 7;
    //系统处理类
    public static final byte DIALOG = 20;
    public static final byte SOUND = 21;
    public static final byte WAIT = 22;
    public static final byte ITEMSHOP = 23;
    public static final byte EQUIPSHOP = 24;
    public static final byte GAMEOVER = 25;
    //玩家操作类
    public static final byte MAP = 40;
    public static final byte FACE = 41;
    public static final byte MOVE = 42;
    public static final byte FIGHT = 43;
    public byte type = -1;//指令类型
    public int nextIndex = -1;//指令解析完成后要执行的下一条指令的索引
    public String param = null;//指令的参数

    private Command(byte type, String param, int nextIndex) {
        this.type = type;
        this.param = param;
        this.nextIndex = nextIndex;
    }

    public Command() {
    }

    /**
     * 创建指令
     * @param type 指令类型
     * @param param 指令参数
     * @param nextIndex 下一条指令索引
     * @return 指令
     */
    public static Command creatCommand(byte type, String param, int nextIndex) {
        return new Command(type, param, nextIndex);
    }

    public static Command creatExpressionCommand(String param) {
        return new Command(EXPRESSION, param, -1);
    }

    public static Command creatIfCommand(String param, int nextIndex) {
        return new Command(IF, param, nextIndex);
    }

    public static Command creatEndIfCommand() {
        return new Command(ENDIF, null, -1);
    }

    public static Command creatWhileCommand(String param, int nextIndex) {
        return new Command(WHILE, param, nextIndex);
    }

    public static Command creatEndWhileCommand(int nextIndex) {
        return new Command(ENDWHILE, null, nextIndex);
    }

    public static Command creatBreakCommand(int nextIndex) {
        return new Command(BREAK, null, nextIndex);
    }

    public static Command creatContinueCommand(int nextIndex) {
        return new Command(CONTINUE, null, nextIndex);
    }

    public static Command creatExitCommand() {
        return new Command(EXIT, null, -1);
    }

    public static Command creatDialogCommand(String param) {
        return new Command(DIALOG, param, -1);
    }

    public static Command creatSoundCommand(String param) {
        return new Command(SOUND, param, -1);
    }

    public static Command creatWaitCommand(String param) {
        return new Command(WAIT, param, -1);
    }

    public static Command creatItemShopCommand(String param) {
        return new Command(ITEMSHOP, param, -1);
    }

    public static Command creatEquipShopCommand(String param) {
        return new Command(EQUIPSHOP, param, -1);
    }

    public static Command creatGameOverCommand() {
        return new Command(GAMEOVER, null, -1);
    }

    public static Command creatMapCommand(String param) {
        return new Command(MAP, param, -1);
    }

    public static Command creatMoveCommand(String param) {
        return new Command(MOVE, param, -1);
    }

    public static Command creatFaceCommand(String param) {
        return new Command(FACE, param, -1);
    }

    public static Command creatFightCommand(String param) {
        return new Command(FIGHT, param, -1);
    }
}
