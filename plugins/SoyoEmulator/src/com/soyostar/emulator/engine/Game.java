package com.soyostar.emulator.engine;

import com.soyostar.emulator.engine.script.DataHandlerInterface;
import com.soyostar.emulator.engine.script.Script;
import com.soyostar.emulator.engine.script.ScriptEngine;

public abstract class Game implements GameInterface {

    /**
     * 游戏的当前视图
     */
    private BaseView curView = null;
    /**
     * 游戏主引擎
     */
    private GameEngine ge = GameEngine.getInstance();
    /**
     * 脚本引擎
     */
    private ScriptEngine se = ScriptEngine.getInstance();
    /**
     *事件处理标识
     */
    private volatile boolean isDealEvent = false;

    public boolean isDealEvent() {
        return isDealEvent;
    }

    /**
     * 开始事件处理
     */
    public void startEvent() {
        System.out.println("start deal event");
        isDealEvent = true;
    }

    /**
     * 完成事件处理
     */
    public void finishEvent() {
        System.out.println("end del event");
        isDealEvent = false;
    }

    /**
     * 获取游戏的当前视图
     * @return 游戏的当前视图，如果不存在则返回null
     */
    public final BaseView getCurView() {
        return curView;
    }

    /**
     * 设置游戏的当前视图
     * @param curView 要设置为当前视图的视图
     */
    protected final void setCurView(BaseView curView) {
        this.curView = curView;
    }

    /**
     * 退出游戏
     */
    public final void exit() {
        ge.exit();
    }

    /**
     * 向脚本引擎提交脚本
     * @param script 脚本
     */
    public final void referScript(Script script) {
        se.addScript(script);
        System.out.println("referScript 提交脚本");
    }

    public final void setDataHandler(DataHandlerInterface dhi) {
        se.setDataHandler(dhi);
    }
}
