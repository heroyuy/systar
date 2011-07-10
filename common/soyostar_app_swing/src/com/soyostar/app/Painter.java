package com.soyostar.app;

/**
 * 画笔
 * @author wp_g4
 */
public interface Painter {

    /**
     * 锚点：水平左对齐，竖向顶对齐
     */
    public static final int LT = 0;
    /**
     * 锚点：水平左对齐，竖向居中对齐
     */
    public static final int LV = 1;
    /**
     * 锚点：水平左对齐，竖向底对齐
     */
    public static final int LB = 2;
    /**
     * 锚点：水平居中对齐，竖向顶对齐
     */
    public static final int HT = 3;
    /**
     * 锚点：水平居中对齐，竖向居中对齐
     */
    public static final int HV = 4;
    /**
     * 锚点：水平居中对齐，竖向底对齐
     */
    public static final int HB = 5;
    /**
     * 锚点：水平右对齐，竖向顶对齐
     */
    public static final int RT = 6;
    /**
     * 锚点：水平右对齐，竖向居中对齐
     */
    public static final int RV = 7;
    /**
     * 锚点：水平右对齐，竖向底对齐
     */
    public static final int RB = 8;

    /**
     * 设置文字尺寸
     * @param size 绘制文字的尺寸
     */
    public void setTextSize(int size);

    /**
     * 获取绘制文字的尺寸
     * @return 绘制文字的尺寸
     */
    public int getTextSize();

    /**
     * 获取字符串的宽度
     * @param str 要测量的字符串
     * @return 字符串的宽度
     */
    public int stringWidth(String str);

    /**
     * 绘制字符串
     * @param str 要绘制的字符串
     * @param x 绘制的位置的 x 坐标
     * @param y 绘制的位置的 y 坐标
     * @param anchor 锚点
     */
    public void drawString(String str, int x, int y, int anchor);

    /**
     * 绘制直线
     * @param x1 起点 x 坐标
     * @param y1 起点 y 坐标
     * @param x2 终点 x 坐标
     * @param y2 终点 y 坐标
     */
    public void drawLine(int x1, int y1, int x2, int y2);

    /**
     * 绘制图片
     * @param img 要绘制的图片
     * @param x 绘制的位置的 x 坐标
     * @param y 绘制的位置的 y 坐标
     * @param anchor 锚点
     */
    public void drawImage(Image img, int x, int y, int anchor);

    /**
     * 绘制矩形区域
     * @param x 矩形区域左上角 x 坐标
     * @param y 矩形区域左上角 y 坐标
     * @param width 矩形区域宽度
     * @param height 矩形区域高度
     */
    public void drawRect(int x, int y, int width, int height);

    /**
     * 绘制圆角矩形区域
     * @param x 矩形区域左上角 x 坐标
     * @param y 矩形区域左上角 y 坐标
     * @param width 矩形区域宽度
     * @param height 矩形区域高度
     * @param arcSize 圆角半径
     */
    public void drawRoundRect(int x, int y, int width, int height, int arcSize);

    /**
     * 绘制椭圆的边框。得到一个圆或椭圆，它刚好能放入由 x、y、width 和 height 参数指定的矩形中。 
     * @param x 要绘制椭圆的左上角的 x 坐标。
     * @param y 要绘制椭圆的左上角的 y 坐标。
     * @param width 要绘制椭圆的宽度。
     * @param height 要绘制椭圆的高度。
     */
    public void drawOval(int x, int y, int width, int height);

    /**
     * 填充矩形区域
     * @param x 矩形区域左上角 x 坐标
     * @param y 矩形区域左上角 y 坐标
     * @param width 矩形区域宽度
     * @param height 矩形区域高度
     */
    public void fillRect(int x, int y, int width, int height);

    /**
     * 填充圆角矩形区域
     * @param x 矩形区域左上角 x 坐标
     * @param y 矩形区域左上角 y 坐标
     * @param width 矩形区域宽度
     * @param height 矩形区域高度
     * @param arcSize 圆角半径
     */
    public void fillRoundRect(int x, int y, int width, int height, int arcSize);

    /**
     * 填充椭圆。得到一个圆或椭圆，它刚好能放入由 x、y、width 和 height 参数指定的矩形中。
     * @param x 要填充椭圆的左上角的 x 坐标。
     * @param y 要填充椭圆的左上角的 y 坐标。
     * @param width 要填充椭圆的宽度。
     * @param height 要填充椭圆的高度。
     */
    public void fillOval(int x, int y, int width, int height);

    /**
     * 设置画笔颜色
     * @param color 颜色
     */
    public void setColor(int color);

    /**
     * 获取当前画笔颜色
     * @return 当前画笔颜色
     */
    public int getColor();

    /**
     * 保存画笔的当前状态，包括画笔的颜色、字号
     */
    public void save();

    /**
     * 还原画笔到上一次备份的状态
     */
    public void restore();
}
