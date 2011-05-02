package engine.script;

import game.system.Queue;

/**
 *
 * 脚本引擎
 */
public final class ScriptEngine implements Runnable {

    /**
     * 单根模式
     */
    private static ScriptEngine se = new ScriptEngine();
    /**
     * 游戏数据处理器
     */
    private IDataHandler dataHandler = null;
    /**
     * 循环延迟时间
     */
    private int delay = 50;
    /**
     * 运行状态标识
     */
    private volatile boolean isRun = false;
    /**
     * 脚本解释器
     */
    Interpreter interpreter = null;
    /**
     * 处理的脚本队列
     */
    private Queue scriptQueue = new Queue();
    /**
     * 脚本引擎处理脚本生成的游戏事件队列
     */
    private Queue eventQueue = new Queue();
    /**
     * 表达式处理器
     */
    private ExpCalc expCalc = null;

    public ExpCalc getExpCalc() {
        return expCalc;
    }
    /**
     * 脚本引擎中的变量表
     */
    private VarList varList = null;

    /**
     * 获取脚本引擎的变量表
     * @return 脚本引擎的变量表
     */
    public VarList getVarList() {
        return varList;
    }

    public Queue getEventQueue() {
        return eventQueue;
    }

    public void setEventQueue(Queue eventQueue) {
        this.eventQueue = eventQueue;
    }

    /**
     * 构造器
     */
    private ScriptEngine() {
        super();
        interpreter = new Interpreter(this);
        expCalc = new ExpCalc(this);
        varList = new VarList(this);
    }

    /**
     * 获取脚本引擎的实例
     * @return 脚本引擎的实例
     */
    public static ScriptEngine getInstance() {
        return se;
    }

    /**
     * 脚本引擎主循环
     */
    public void run() {
        while (isRun) {
            if (!scriptQueue.isEmpty()) {
                //如果脚本队列不为空则处理脚本
                runScript((Script) scriptQueue.poll());
            } else {
                try {
                    Thread.sleep(delay);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    /**
     * 启动脚本引擎
     */
    public void start() {
        isRun = true;
        new Thread(this).start();
    }
    /**
     * 测试用变量，正式发布时删除
     */
    int scriptNum = 0;

    /**
     * 运行脚本，并生成游戏事件存放到带队队列中
     * @param script 要处理的脚本
     */
    private void runScript(Script script) {
        System.out.println("脚本引擎运行脚本");
        interpreter.interpret(script);
    }

    /**
     * 为脚本引擎添加要处理脚本
     * @param script 要处理的脚本
     */
    public void addScript(Script script) {
        scriptQueue.offer(script);
        System.out.println("脚本引擎添加脚本");
    }

    /**
     * 向事件列表添加事件
     * @param event 要处理的事件
     */
    public void addEvent(GameEvent event) {
        eventQueue.offer(event);

    }

    public void exit() {
        isRun = false;
    }

    public void setDataHandler(IDataHandler dhi) {
        this.dataHandler = dhi;
    }

    public IDataHandler getDataHandler() {
        return dataHandler;
    }
}
