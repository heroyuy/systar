package engine;

import emulator.MotionEvent;
import engine.script.GameEvent;
import engine.script.ScriptEngine;

/**
 * 游戏主引擎
 *
 */
public final class GameEngine implements Runnable {

    /**
     * 游戏引擎单例模式
     */
    private static GameEngine ge = new GameEngine();
    /**
     * 脚本引擎
     */
    private static ScriptEngine se = ScriptEngine.getInstance();

    /**
     * 获取游戏引擎运行时实例
     * @return 游戏引擎运行时实例
     */
    public static GameEngine getInstance() {
        return ge;
    }
    /**
     * 视图渲染层
     */
    private RenderLayer renderLayer = null;
    /**
     * 循环计数器
     */
    private int ticker = 0;
    /**
     * 按键值
     */
    private int key = 0;
    /**
     * 当前触屏事件
     */
    private MotionEvent me = null;
    /**
     * 循环等待时间
     */
//    private int delay = 80;
    /**
     * fps
     */
    private int fps = 12;
    /**
     * 游戏引擎运行状态标识
     */
    private volatile boolean isRun = false;
    /**
     * 程序入口的引用
     */
    private Main main = null;
    /**
     * 游戏实例
     */
    private IGame game = null;

    /**
     * 构造器
     *
     */
    private GameEngine() {
        renderLayer = new RenderLayer(this);
    }

    /**
     * 退出游戏引擎
     *
     */
    public void exit() {
        isRun = false;
    }

    /**
     * 获取正在游戏引擎中运行的游戏实例
     * @return 正在游戏引擎中运行的游戏实例
     */
    public IGame getGame() {
        return game;
    }

    /**
     * 游戏引擎初始化
     */
    private void init() {
        System.out.println("初始化游戏实例");
        isRun = true;
        try {
            game = (IGame) Class.forName("game.RpgGame").newInstance();
        } catch (InstantiationException ex) {
            ex.printStackTrace();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }


    }

    /**
     * 检测游戏引擎的运行状态
     *
     * @return 游戏引擎的运行状态
     */
    protected boolean isRun() {
        return isRun;
    }

    /**
     * 游戏引擎主循环
     */
    public void run() {
        try {
            while (isRun) {
                long time = System.currentTimeMillis();
                //更新游戏
                game.update();
                
                //处理游戏事件
                if (!se.getEventQueue().isEmpty()) {
                    if (game.dealGameEvent((GameEvent) se.getEventQueue().peck())) {
                        //事件处理完成
                        se.getEventQueue().poll();
                    }
                }

                //触屏处理
                if (me != null) {
                    game.onTouchEvent(me);
                }
                // 处理按键
                if (key != 0) {
                    game.dealKeyEvent(key);
                }

                // 渲染视图
                game.render(renderLayer.getEmulatorGraphics());
                renderLayer.repaint();
                time = System.currentTimeMillis() - time;

                // 循环等待

                if (time < 1000 / fps) {
                    Thread.sleep(1000 / fps - time);//保证fps维持一个定值
                }

                // 循环计数器自增
                ticker++;
            }
        } catch (InterruptedException e) {
//            e.printStackTrace();
        }
        main.stop();
    }

    protected void setKey(int key) {
        this.key = key;
    }

    protected void setMotionEvent(MotionEvent me) {
        this.me = me;
    }

    public void clearMotionEvent() {
        me = null;
    }

    public void clearKey() {
        key = 0;
    }

    /**
     * 设置程序入口
     *
     * @param main
     *            程序入口
     */
    public void setMain(Main main) {
        this.main = main;
        System.out.println("设置Canvas");
        main.setCanvas(renderLayer);
    }

    /**
     * 启动游戏引擎
     *
     */
    public void start() {
        System.out.println("启动游戏引擎");
        init();
        if (game != null) {
            se.start();
            game.start();
            new Thread(this).start();
        } else {
            main.stop();
        }
    }
}
