package com.soyostar.data.model;

/**
 *
 * 脚本
 */
public class Script extends Model {

    public byte type = -1;//脚本类型
    public int row = -1;//脚本所在的行
    public int col = -1;//脚本所在的列
    public String imagePath = null;
    public byte face = -1;
    public byte moveRule = -1;
    public int moveSpeed = -1;
    public int commandNum = -1;
    public String[] commands = null;//脚本语句
}
