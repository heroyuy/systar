/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyostar.model.script;

import com.soyostar.project.Project;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Administrator
 */
public class Npc {

    public static final byte CONTACTPARALLEL = 1;
    public static final byte SERIALACCESS = 2;
    public static final byte PARALLELlKEY = 3;
    public static final byte SERIALKEY = 4;
    public static final byte UP = 0;
    public static final byte DOWN = 1;
    public static final byte LEFT = 2;
    public static final byte RIGHT = 3;
    public static final byte FIXED = 0;  //固定
    public static final byte RANDOW = 1; //随机
    public static final byte NEAR = 2;   //接近
    public static final byte FAST = 0;   //快
    public static final byte MIDDLE = 1; //中
    public static final byte SLOW = 2;   //慢
    private byte type = 0;              //脚本类型
    private String imgPath = "";        //行走图路径
    private BufferedImage img;
    private int row = 0;                //脚本行号
    private int col = 0;                //脚本列号
    private byte face = 0;              //初始面向
    private byte move = 0;              //移动规则
    private byte speed = 0;             //移动速度
    private Script script;              //脚本

    public BufferedImage getImg() {
        return img;
    }

    public void setImg(BufferedImage img) {
        this.img = img;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public byte getFace() {
        return face;
    }

    public void setFace(byte face) {
        this.face = face;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) throws IOException {
        this.imgPath = imgPath;
        img = ImageIO.read(new File(Project.getInstance().getPath() + File.separatorChar + "image" + File.separatorChar + "character" + File.separatorChar + imgPath));
    }

    public byte getMove() {
        return move;
    }

    public void setMove(byte move) {
        this.move = move;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public Script getScript() {
        return script;
    }

    public void setScript(Script script) {
        this.script = script;
    }

    public byte getSpeed() {
        return speed;
    }

    public void setSpeed(byte speed) {
        this.speed = speed;
    }

    public byte getType() {
        return type;
    }

    public void setType(byte type) {
        this.type = type;
    }
}
