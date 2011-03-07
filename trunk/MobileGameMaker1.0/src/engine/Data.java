package engine;

import model.Map;

public class Data {

    private int startRow = 0;// 数据块左上角所在行号
    private int startCol = 0;// 数据块左上角所在列号
    private int endCol = 0;// 数据块右下角所在行号
    private int endRow = 0;// 数据块右下角所在列号
    private int startX = 0;// 数据块左上角的X坐标
    private int startY = 0;// 数据块左上角的Y坐标
    private int endX = 0;// 数据块右下角的X坐标
    private int endY = 0;// 数据块右下角的Y坐标
    private int a_X = 0;// 鼠标按下时的X坐标
    private int a_Y = 0;// 鼠标按下时的Y坐标
    private int b_X = 0;// 鼠标弹起时的X坐标
    private int b_Y = 0;// 鼠标弹起时的Y坐标

    public int getEndCol() {
        return endCol;
    }

    public int getEndRow() {
        return endRow;
    }

    public int getEndX() {
        return endX;
    }

    public int getEndY() {
        return endY;
    }

    public int getStartCol() {
        return startCol;
    }

    public int getStartRow() {
        return startRow;
    }

    public int getStartX() {
        return startX;
    }

    public int getStartY() {
        return startY;
    }

    public void setA_X(int a_x) {
        a_X = a_x;
    }

    public void setA_Y(int a_y) {
        a_Y = a_y;
    }

    public void setB_X(int b_x) {
        b_X = b_x;
    }

    public void setB_Y(int b_y) {
        b_Y = b_y;
    }

    public void update(Map map) {

        startX = Math.min(a_X, b_X);// 确定数据块左上角原始X坐标
        startY = Math.min(a_Y, b_Y);// 确定数据块左上角原始Y坐标
        endX = Math.max(a_X, b_X);// 确定数据块右下角原始X坐标
        endY = Math.max(a_Y, b_Y);// 确定数据块右下角原始Y坐标

        startRow = startY / map.getCellHeight();// 确定数据块左上角所在行号
        startCol = startX / map.getCellWidth();// 确定数据块左上角所在列号
        endRow = endY / map.getCellHeight();// 确定数据块右下角所在行号
        endCol = endX / map.getCellWidth();// 确定数据块右下角所在列号

        startX = startCol * map.getCellWidth(); // 确定数据块左上角修正X坐标
        startY = startRow * map.getCellHeight(); // 确定数据块左上角修正Y坐标
        endX = (endCol + 1) * map.getCellWidth();// 确定数据块右下角修正X坐标
        endY = (endRow + 1) * map.getCellHeight();// 确定数据块右下角修正Y坐标
    }
}
