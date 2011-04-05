package com.soyostar.emulator.engine;

/**
 *
 * 所有视图的父类
 */
public abstract class BaseView implements View {

    /**
     * 视图事件控制器
     */
    private Control control = null;

    /**
     * 获取视图控制器
     *
     * @return 视图控制，如果没有则返回null
     */
    public final Control getControl() {
        return control;
    }

    /**
     * 设置视图控制
     *
     * @param control
     *            视图控制器
     */
    public final void setControl(Control control) {
        this.control = control;
    }
}
