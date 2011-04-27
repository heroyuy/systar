package com.soyostar.data.model;

/**
 *
 * 脚本转换后开成的指令集，一个脚本对应一个指令集，一个指令集中可以有很多条指令
 */
public class Script extends Model {

    public byte type = -1;//指令集类型
    public int row = -1;//指令集所在的行
    public int col = -1;//指令集所在的列
    public String imagePath = null;
    public byte face = -1;
    public byte moveRule = -1;
    public byte moveSpeed = -1;
    public int commandNum = -1;
    public String[] commands = null;//脚本语句
}
