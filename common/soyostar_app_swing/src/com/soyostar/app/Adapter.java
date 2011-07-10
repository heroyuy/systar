package com.soyostar.app;

/**
 * 适配器
 * @author wp_g4
 */
public interface Adapter {

    /**
     * 获取数据项数量
     * @return 数据项数量
     */
    public int getCount();

    /**
     * 获取与第index个数据项相关的Widget
     * @param inedx 数据项编号
     * @return 与第index个数据项相关的Widget
     */
    public Widget getWidget(int inedx);

    /**
     * 获取第index个数据项
     * @param index 数据项编号
     * @return 第index个数据项
     */
    public Object getItem(int index);
}
