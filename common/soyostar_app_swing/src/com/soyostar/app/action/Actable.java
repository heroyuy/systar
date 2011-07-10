package com.soyostar.app.action;

/**
 * 动作接口
 * @author wp_g4
 */
public interface Actable {

    /**
     * 检查动作是否处于活跃状态
     * @return 动作的活跃状态
     */
    public boolean isActive();

    /**
     * 动作的主体
     */
    public void run();
}
