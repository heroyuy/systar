/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyomaker.model.map;

import com.soyomaker.AppData;
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
    public static final byte UP = 0;
    /**
     *
     */
    public static final byte DOWN = 1;
    /**
     *
     */
    public static final byte LEFT = 2;
    /**
     *
     */
    public static final byte RIGHT = 3;
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
    public static final byte FAST = 3;   //快
    /**
     * 
     */
    public static final byte MIDDLE = 2; //中
    /**
     * 
     */
    public static final byte SLOW = 1;   //慢
    private byte startType = 0;         //事件开始条件
    private String imgPath = "";        //行走图路径
    private String battlerPath = "";        //行走图路径
    private BufferedImage moveImg;
    private BufferedImage headImg;
    private byte face = 0;              //初始面向
    private byte move = 0;              //移动规则
    private byte speed = 1;             //移动速度
    private boolean cross = false;      //允许穿透
    private Script script = new Script();
    private NpcStateCondition switchCondition;
    private NpcStateCondition varCondition;

    @Override
    public Object clone() throws CloneNotSupportedException {
        NpcState clone = (NpcState) super.clone();
        clone.script = script.clone();
        clone.script.setNpcState(clone);
        if (switchCondition != null) {
            clone.switchCondition = switchCondition.clone();
        }
        if (varCondition != null) {
            clone.varCondition = varCondition.clone();
        }
        return clone;
    }

    /**
     *
     * @param switchConditions
     */
    public void setSwitchCondition(NpcStateCondition switchConditions) {
        this.switchCondition = switchConditions;
    }

    /**
     *
     * @param varConditions
     */
    public void setVarCondition(NpcStateCondition varConditions) {
        this.varCondition = varConditions;
    }

    /**
     *
     * @return
     */
    public NpcStateCondition getSwitchCondition() {
        return switchCondition;
    }

    /**
     *
     * @return
     */
    public NpcStateCondition getVarCondition() {
        return varCondition;
    }

    /**
     *
     * @return
     */
    public String getBattlerPath() {
        return battlerPath;
    }

    /**
     *
     * @param battlerPath
     * @throws IOException
     */
    public void setBattlerPath(String battlerPath) throws IOException {
        int temp = battlerPath.lastIndexOf(File.separator);
        this.battlerPath = battlerPath.substring(temp + 1);
        if (!this.battlerPath.equals("")) {
            headImg = ImageIO.read(new File(AppData.getInstance().getCurProject().getPath()
                    + File.separatorChar + "image" + File.separatorChar + "battler" + File.separatorChar + this.battlerPath));
        }
    }

    /**
     *
     * @return
     */
    public BufferedImage getHeadImg() {
        return headImg;
    }

    /**
     *
     * @param headImg
     */
    public void setHeadImg(BufferedImage headImg) {
        this.headImg = headImg;
    }

    /**
     * 
     * @return
     */
    public Script getScript() {
        return script;
    }

    /**
     * 
     * @param scr
     */
    public void setScript(Script scr) {
        script = scr;
        script.setNpcState(this);
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
        return moveImg;
    }

    /**
     * 
     * @param img
     */
    public void setImg(BufferedImage img) {
        this.moveImg = img;
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

        int temp = imgPath.lastIndexOf(File.separator);
        this.imgPath = imgPath.substring(temp + 1);
        if (!this.imgPath.equals("")) {
            moveImg = ImageIO.read(new File(AppData.getInstance().getCurProject().getPath()
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
