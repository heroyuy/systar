package com.soyostar.emulator.engine;

import com.soyostar.emulator.Display;
import com.soyostar.emulator.engine.script.Event;
import com.soyostar.emulator.engine.script.ScriptEngine;

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
     * 游戏当前视图
     */
    private View curView = null;
    /**
     * 循环计数器
     */
    private int ticker = 0;
    /**
     * 按键值
     */
    private int key = 0;
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
    private Game game = null;

    /**
     * 构造器
     *
     */
    private GameEngine() {
        super();
        renderLayer = new RenderLayer(this);
        Display.getDefaultDisplay().setCurrentCanvas(renderLayer);
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
    public Game getGame() {
        return game;
    }

    /**
     * 获取按键值
     * @return
     */
    public int getKey() {
        return key;
    }

    /**
     * 游戏引擎初始化
     *
     */
    private void init() {
        System.out.println("初始化游戏实例");
        isRun = true;
        try {
            game = (Game) Class.forName("game.RpgGame").newInstance();
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
                curView = game.getCurView();
                if (curView != null) {
                    curView.dealGameEvent((Event) se.getEventQueue().poll());
                    //处理游戏事件
                    if (!game.isDealEvent() && !se.getEventQueue().isEmpty()) {
                        curView.dealGameEvent((Event) se.getEventQueue().poll());
                    }
                    //处理触屏事件
                    curView.dealMotion();
                    //处理按键事件
                    curView.dealKeyEvent();
                }

                // 渲染视图
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
            e.printStackTrace();
        }
        main.stop();
    }

    protected void setKey(int key) {
        this.key = key;
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

    /**
     * 获取循环计数器
     * @return 循环计数器
     */
    public int getTicker() {
        return ticker;
    }
}
