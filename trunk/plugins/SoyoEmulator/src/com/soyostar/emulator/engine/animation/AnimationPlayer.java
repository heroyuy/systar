package com.soyostar.emulator.engine.animation;

import com.soyostar.ui.Painter;



/**
 *
 * 动画播放器
 */
public class AnimationPlayer {

    private static AnimationPlayer animationPlayer = new AnimationPlayer();
    private AnimationPool animationPool = AnimationPool.getInstance();
    private boolean isRun = false;//动画播放器运行标识

    private AnimationPlayer() {
    }

    public static AnimationPlayer getInstance() {
        return animationPlayer;
    }

    public void removeAllAnimations() {
        animationPool.removeAllAnimations();
    }

    /**
     * 开始播放指定的动画
     * @param animation 要播放的动画
     * @param x 播放位置的x坐标
     * @param y 播放位置的y坐标
     * @param anchor 锚点
     */
    public void playAnimation(Animation animation, int x, int y) {
        if (animation == null) {
            return;
        }
        Animation ani = null;
        ani = animation.getClone();
        ani.x = x;
        ani.y = y;
        animationPool.addAnimation(ani);
    }

    /**
     * 在指定的图形设备上播放动画，此方法只应该由引擎在主循环中调用
     * @param eg 画笔
     */
    public void playNoClear(Painter painter) {
//        System.out.println(animationPool.size());
        /**
         * 1.顺序播放动画池中的动画
         * 2.清理动画池
         */
        if (!isRun) {
            return;
        }
        Animation ani = null;
//        Frame frame = null;
//        Image img = null;
        for (int i = 0; i < animationPool.size(); i++) {
            /**
             * 1.绘制所有动画的当前帧
             * 2.所有动画的当前帧自增
             */
            ani = animationPool.getAnimation(i);
//            frame = ani.frames[ani.curIndex];
            int picw = ani.image.getWidth() / ani.frameWidth;

            if (ani.getCurFrame() != null) {
                painter.drawRegion(ani.image, (ani.getCurFrame().num - 1) % picw * ani.frameWidth,
                        (ani.getCurFrame().num - 1) / picw * ani.frameHeight,
                        ani.frameWidth, ani.frameHeight, 0, ani.x + ani.getCurFrame().offsetX, ani.y + ani.getCurFrame().offsetY, Painter.HV);
            }
//            img = frame.image;
//            g.drawImage(img, ani.x + frame.offsetX, ani.y + frame.offsetY, Graphics.HCENTER | Graphics.VCENTER);
            ani.curIndex++;
            if (ani.curIndex >= ani.frameNum) {
                ani.curIndex = 0;
            }
        }
//        animationPool.clear();//如果清理动作影响了动画的播放速度，可以考虑另启一线程专门清理动画池
    }

    /**
     * 在指定的图形设备上播放动画，此方法只应该由引擎在主循环中调用
     * @param painter 画笔
     */
    public void play(Painter painter) {
//        System.out.println(animationPool.size());
        /**
         * 1.顺序播放动画池中的动画
         * 2.清理动画池
         */
        if (!isRun) {
            return;
        }
        Animation ani = null;
//        Frame frame = null;
//        Image img = null;
        for (int i = 0; i < animationPool.size(); i++) {
            /**
             * 1.绘制所有动画的当前帧
             * 2.所有动画的当前帧自增
             */
            ani = animationPool.getAnimation(i);
//            frame = ani.frames[ani.curIndex];
            int picw = ani.image.getWidth() / ani.frameWidth;

            if (ani.getCurFrame() != null) {
                painter.drawRegion(ani.image, (ani.getCurFrame().num - 1) % picw * ani.frameWidth,
                        (ani.getCurFrame().num - 1) / picw * ani.frameHeight,
                        ani.frameWidth, ani.frameHeight, 0, ani.x + ani.getCurFrame().offsetX, ani.y + ani.getCurFrame().offsetY, Painter.HV);
            }
//            img = frame.image;
//            g.drawImage(img, ani.x + frame.offsetX, ani.y + frame.offsetY, Graphics.HCENTER | Graphics.VCENTER);
            ani.curIndex++;
            if (ani.curIndex >= ani.frameNum) {

                animationPool.clear();//如果清理动作影响了动画的播放速度，可以考虑另启一线程专门清理动画池
                ani.curIndex = 0;
            }
        }

    }

    /**
     * 启动动画播放器
     */
    public void start() {
        isRun = true;
    }

    /**
     * 立即停止动画播放器并清除所有未播放完的动画
     */
    public void stop() {
        isRun = false;
        animationPool.removeAllAnimations();
    }
}
