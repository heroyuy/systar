package com.soyostar.app;

/**
 * 可执行程序。App的子类必需有一个无参构造器,否则系统无法加载。
 * @author wp_g4
 */
public abstract class App {

    private Component contentPanel = null;

    /**
     * 获取App的显示组件
     * @return 绑定到App的显示组件
     */
    public final Component getContentPanel() {
        return contentPanel;
    }

    /**
     * 设置App的显示组件
     * @param contentPanel 绑定到App的显示组件
     */
    public final void setContentPanel(Component contentPanel) {
        this.contentPanel = contentPanel;
    }

    /**
     * 启动程序，系统调用
     */
    public abstract void start();

    /**
     * 停止程序，系统调用
     */
    public abstract void stop();

    /**
     * 暂停程序，系统调用
     */
    public abstract void pause();

    /**
     * 继续程序，系统调用
     */
    public abstract void resume();
}
