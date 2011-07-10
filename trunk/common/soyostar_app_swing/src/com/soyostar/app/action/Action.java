/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyostar.app.action;

/**
 * 动作的骨架实现
 * @author wp_g4
 */
public abstract class Action implements Actable {

    private boolean active = false;

    /**
     * 激活动作
     */
    public void activate() {
        active = true;
    }

    /**
     * 冻结动作
     */
    public void freeze() {
        active = false;
    }

    /**
     * 检查动作是否处于活跃状态
     * @return 动作的活跃状态
     */
    public boolean isActive() {
        return active;
    }
}
