/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyostar.game.db;

import com.soyostar.emulator.engine.animation.Animation;
import com.soyostar.game.model.Frame;
import com.soyostar.ui.Image;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.HashMap;

/**
 *
 * @author Administrator
 */
public class AnimationDao implements Dao {

    private DataInputStream dis = null;
    private HashMap animations = null;

    public Animation getAnimation(int index) {
        return (Animation) animations.get(index);
    }

    public Animation[] getAnimationList() {
        return (Animation[]) animations.values().toArray();
    }

    public void load() {
        animations = new HashMap();


        dis = DataManager.getInstance().getFileBridge().getDataInputStream(FileBridge.FILE_TYPE_ANIMATION, 0);
        try {
            int animationSum = dis.readInt();
            Animation animation = null;
            for (int i = 0; i < animationSum; i++) {
                animation = new Animation();
                animation.index = dis.readInt();
                animation.name = dis.readUTF();
                animation.imageName = dis.readUTF();
                animation.image = Image.createImage("product/image/animation/" + animation.imageName);
                animation.soundName = dis.readUTF();
                animation.frameWidth = dis.readInt();
                animation.frameHeight = dis.readInt();
                animation.frameNum = dis.readInt();
                animation.frames = new Frame[animation.frameNum];
                for (int j = 0; j < animation.frameNum; j++) {
                    animation.frames[j] = new Frame();
                    animation.frames[j].num = dis.readInt();
                    animation.frames[j].offsetX = dis.readInt();
                    animation.frames[j].offsetY = dis.readInt();
                }
                animations.put(animation.index, animation);
            }
            dis.close();
        } catch (IOException e) {
            System.out.println("ani.gat readError!");
        }
    }

    public void save() {
    }
}
