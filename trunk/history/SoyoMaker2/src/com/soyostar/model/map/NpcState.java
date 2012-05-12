/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyostar.model.map;

import com.soyostar.proxy.SoftProxy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Administrator
 */
public class NpcState implements Cloneable {

    /**
     * 
     */
    public static final byte AUTOMATIC = 0;
    /**
     * 
     */
    public static final byte CONTACT = 1;
    /**
     * 
     */
    public static final byte BUTTON = 2;
    /**
     * 
     */
    public static final byte UP = 3;
    /**
     * 
     */
    public static final byte DOWN = 0;
    /**
     * 
     */
    public static final byte LEFT = 1;
    /**
     * 
     */
    public static final byte RIGHT = 2;
    /**
     * 
     */
    public static final byte FIXED = 0;  //固定
    /**
     * 
     */
    public static final byte RANDOW = 1; //随机
    /**
     * 
     */
    public static final byte NEAR = 2;   //接近
    /**
     * 
     */
    public static final byte FAST = 0;   //快
    /**
     * 
     */
    public static final byte MIDDLE = 1; //中
    /**
     * 
     */
    public static final byte SLOW = 2;   //慢
    private byte startType = 0;         //事件开始条件
    private String imgPath = "";        //行走图路径
    private BufferedImage img;
    private byte face = 0;              //初始面向
    private byte move = 0;              //移动规则
    private byte speed = 0;             //移动速度
    private boolean cross = false;      //允许穿透
    private ScriptFile script = new ScriptFile();

    @Override
    public Object clone() throws CloneNotSupportedException {
        NpcState clone = (NpcState) super.clone();
        clone.script = new ScriptFile();
//        clone.script.setIndex(script.getIndex());
//        clone.script.setFile(script.getFile());
        clone.script.setFunc(script.getFunc());
        return clone;
    }

    /**
     * 
     * @return
     */
    public ScriptFile getScript() {
        return script;
    }

    /**
     * 
     * @param scr
     */
    public void setScript(ScriptFile scr) {
        script = scr;
    }

    /**
     * 
     * @return
     */
    public boolean isCross() {
        return cross;
    }

    /**
     * 
     * @param cross
     */
    public void setCross(boolean cross) {
        this.cross = cross;
    }

    /**
     * 
     * @return
     */
    public BufferedImage getImg() {
        return img;
    }

    /**
     * 
     * @param img
     */
    public void setImg(BufferedImage img) {
        this.img = img;
    }

    /**
     * 
     * @return
     */
    public byte getFace() {
        return face;
    }

    /**
     * 
     * @param face
     */
    public void setFace(byte face) {
        this.face = face;
    }

    /**
     * 
     * @return
     */
    public String getImgPath() {
        return imgPath;
    }

    /**
     * 
     * @param imgPath
     * @throws IOException
     */
    public void setImgPath(String imgPath) throws IOException {

        int temp = imgPath.lastIndexOf("\\");
        this.imgPath = imgPath.substring(temp + 1);
        if (!this.imgPath.equals("")) {
            img = ImageIO.read(new File(SoftProxy.getInstance().getCurProject().getPath()
                + File.separatorChar + "image" + File.separatorChar + "character" + File.separatorChar + this.imgPath));
        }
//        System.out.println("精灵图片: " + Project.getInstance().getPath() + File.separatorChar + "image" + File.separatorChar + "character" + File.separatorChar + this.imgPath);
    }

    /**
     * 
     * @return
     */
    public byte getMove() {
        return move;
    }

    /**
     * 
     * @param move
     */
    public void setMove(byte move) {
        this.move = move;
    }

    /**
     * 
     * @return
     */
    public byte getSpeed() {
        return speed;
    }

    /**
     * 
     * @param speed
     */
    public void setSpeed(byte speed) {
        this.speed = speed;
    }

    /**
     * 
     * @return
     */
    public byte getStartType() {
        return startType;
    }

    /**
     * 
     * @param type
     */
    public void setStartType(byte type) {
        this.startType = type;
    }
}
