/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyomaker.model.animation;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 *
 * @author Administrator
 */
public class Frame implements Cloneable {

    private int delay = 100;//单帧延迟时间,默认为100ms
    private String name = "新建帧";//可选，帧的名称
    private ArrayList<Clip> tiles = new ArrayList<Clip>();//所使用的模块集
    private Animation animation;
    private int width = 384;
    private int height = 384;
    private int pngX = 0;//在生成的动画图片中，该帧的x坐标
    private int pngY = 0;//在生成的动画图片中，该帧的y坐标

    @Override
    public Object clone() throws CloneNotSupportedException {
        Frame clone = (Frame) super.clone();
        clone.tiles = new ArrayList<Clip>();
        for (int i = 0; i < this.tiles.size(); i++) {
            clone.tiles.add((Clip) tiles.get(i).clone());
        }
        return clone;
    }

    /**
     * 在生成的动画图片中，该帧的高度（计算所有Clip所得到的实际高度）
     * @return
     */
    public int getPngHeight() {
        int minY = Integer.MAX_VALUE;
        int maxY = -Integer.MAX_VALUE;
        for (Clip clip : tiles) {
            if (clip.getFramePoint().y < minY) {
                minY = clip.getFramePoint().y;
            }
            if (clip.getFramePoint().y + clip.getH() > maxY) {
                maxY = clip.getFramePoint().y + clip.getH();
            }
        }
        return maxY - minY;
    }

    /**
     * 在生成的动画图片中，该帧的宽度（计算所有Clip所得到的实际宽度）
     * ?有疑问是要不要考虑帧的宽高
     * @return
     */
    public int getPngWidth() {
        int minX = Integer.MAX_VALUE;
        int maxX = -Integer.MAX_VALUE;
        for (Clip clip : tiles) {
            if (clip.getFramePoint().x < minX) {
                minX = clip.getFramePoint().x;
            }
            if (clip.getFramePoint().x + clip.getW() > maxX) {
                maxX = clip.getFramePoint().x + clip.getW();
            }
        }
        return maxX - minX;
    }

    /**
     *
     * @return
     */
    public int getPngX() {
        return pngX;
    }

    /**
     *
     * @param pngX
     */
    public void setPngX(int pngX) {
        this.pngX = pngX;
    }

    /**
     * 
     * @return
     */
    public int getPngY() {
        return pngY;
    }

    /**
     *
     * @param pngY
     */
    public void setPngY(int pngY) {
        this.pngY = pngY;
    }

    /**
     * 针对这个实际宽度，与原先设定的帧的宽度之间的偏移值
     * @return
     */
    public int getOffsetX() {
        int minX = Integer.MAX_VALUE;
        for (Clip clip : tiles) {
            if (clip.getFramePoint().x < minX) {
                minX = clip.getFramePoint().x;
            }
        }
        return minX;
    }

    /**
     * 针对这个实际高度，与原先设定的帧的高度之间的偏移值
     * @return
     */
    public int getOffsetY() {
        int minY = Integer.MAX_VALUE;
        for (Clip clip : tiles) {
            if (clip.getFramePoint().y < minY) {
                minY = clip.getFramePoint().y;
            }
        }
        return minY;
    }

    /**
     * 返回根据clip确定的真实大小的图像
     * @return
     */
    public BufferedImage getPngBufferedImage() {
        BufferedImage frameImage = new BufferedImage(this.getWidth(),
                this.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D frameg = frameImage.createGraphics();
        paint(frameg, width / 2, height / 2);
        int x = this.getOffsetX() + width / 2 - this.getPngWidth() / 2;
        int y = this.getOffsetY() + height / 2 - this.getPngHeight() / 2;
        return frameImage.getSubimage(x, y, this.getPngWidth(), this.getPngHeight());
    }

    /**
     *
     * @return
     */
    public int getHeight() {
        return height;
    }

    /**
     *
     * @param height
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     *
     * @return
     */
    public int getWidth() {
        return width;
    }

    /**
     *
     * @param width
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     *
     * @return
     */
    public int getID() {
        if (animation != null) {
            return animation.getFrames().indexOf(this);
        }
        return 0;
    }

    /**
     *
     * @param ani
     */
    public void setAnimation(Animation ani) {
        this.animation = ani;
    }

    /**
     *
     * @return
     */
    public Animation getAnimation() {
        return this.animation;
    }

    /**
     *
     * @param g
     * @param x
     * @param y
     */
    public void paint(Graphics g, int x, int y) {
        for (int i = 0; i < this.tiles.size(); i++) {
            this.tiles.get(i).paint(g, x, y);
        }
    }

    /**
     *
     * @param index
     */
    public void swapClipBottom(int index) {
        if (index - 1 < 0) {
            throw new RuntimeException(
                    "Can't swap down when already at the bottom.");
        }
        Clip clip = getTile(index);
        tiles.remove(clip);
        tiles.add(0, clip);
    }

    /**
     *
     * @param index
     */
    public void swapClipTop(int index) {
        if (index + 1 == tiles.size()) {
            throw new RuntimeException(
                    "Can't swap up when already at the top.");
        }
        Clip clip = getTile(index);
        tiles.remove(clip);
        tiles.add(clip);
    }

    /**
     *
     * @param index
     */
    public void swapClipUp(int index) {
        if (index + 1 == tiles.size()) {
            throw new RuntimeException(
                    "Can't swap up when already at the top.");
        }
        Clip clip = getTile(index + 1);
        tiles.set(index + 1, getTile(index));
        tiles.set(index, clip);
    }

    /**
     *
     * @param index
     */
    public void swapClipDown(int index) {
        if (index - 1 < 0) {
            throw new RuntimeException(
                    "Can't swap down when already at the bottom.");
        }
        Clip clip = getTile(index - 1);
        tiles.set(index - 1, getTile(index));
        tiles.set(index, clip);
    }

    /**
     *
     * @return
     */
    public ArrayList<Clip> getTiles() {
        return tiles;
    }

    /**
     *
     * @param id
     * @return
     */
    public Clip getTile(int id) {
        if (id < 0 || id > tiles.size() - 1) {
            return null;
        }
        return tiles.get(id);
    }

    /**
     *
     * @param act
     */
    public void addTile(Clip act) {
        tiles.add(act);
    }

    /**
     *
     * @param id
     */
    public void removeTile(int id) {
        tiles.remove(id);
    }

    /**
     *
     * @return
     */
    public int getDelay() {
        return delay;
    }

    /**
     *
     * @param delay
     */
    public void setDelay(int delay) {
        this.delay = delay;
    }

    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }
}
