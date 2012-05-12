/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyomaker.io.animation;

import com.soyomaker.model.animation.Action;
import com.soyomaker.model.animation.Animation;
import com.soyomaker.model.animation.Clip;
import com.soyomaker.model.animation.Frame;
import com.soyomaker.model.animation.Picture;
import com.soyomaker.AppData;
import java.awt.Point;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;

/**
 *
 * @author Administrator
 */
public class DefaultSoftAnimationBinaryReader implements IAnimationReader {

    private AppData data = AppData.getInstance();

    public void readAnimation(String aniFile) throws Exception {
        DataInputStream dis = null;
        FileInputStream fis = null;
        fis = new FileInputStream(aniFile);
        dis = new DataInputStream(fis);
        int picNum = dis.readInt();
        for (int i = 0; i < picNum; i++) {
            Picture pic = new Picture();
            String s = dis.readUTF();
            int temp = s.lastIndexOf(File.separator);
            pic.setSourceImageFile(s.substring(temp + 1));
            int picClipsSize = dis.readInt();
            for (int j = 0; j < picClipsSize; j++) {
                Clip clip = new Clip();
                int clipID = dis.readInt();//暂时不用
                clip.setSourcePoint(new Point(dis.readInt(), dis.readInt()));
                clip.setW(dis.readInt());
                clip.setH(dis.readInt());
                pic.addTile(clip);
            }
            data.getCurProject().addPicture(pic);
        }
        int anisSize = dis.readInt();
        for (int i = 0; i < anisSize; i++) {
            Animation ani = new Animation();
            ani.setIndex(dis.readInt());
            ani.setName(dis.readUTF());
            int frameSize = dis.readInt();
            for (int j = 0; j < frameSize; j++) {
                Frame frame = new Frame();
                int frameID = dis.readInt();//暂时未用
                frame.setName(dis.readUTF());
                frame.setDelay(dis.readInt());
                frame.setW(dis.readInt());
                frame.setH(dis.readInt());
                int frameClipsSize = dis.readInt();
                for (int k = 0; k < frameClipsSize; k++) {
                    Clip clip = data.getCurProject().getClips().get(dis.readInt());//根据ID还原切块
                    clip.setFramePoint(new Point(dis.readInt(), dis.readInt()));
                    clip.setTransparency(dis.readInt());
                    clip.setMirror(dis.readBoolean());
                    clip.setRotation(dis.readInt());
                    clip.setZoom(dis.readFloat());
                    clip.setRenderType(dis.readInt());
                    frame.addTile(clip);
                }
                ani.addFrame(frame);
            }
            int actionSize = dis.readInt();
            for (int j = 0; j < actionSize; j++) {
                Action act = new Action();
                act.setFrameId(dis.readInt());
//                System.out.println("起始帧ID:" + act.getFrameId());
                String s = dis.readUTF();
                int temp = s.lastIndexOf(File.separator);
                act.setMusicFile(s.substring(temp + 1));
//                System.out.println("音效:" + act.getMusicFile());
                act.setType(dis.readByte());
//                System.out.println("效果类型:" + act.getType());
                act.setRed(dis.readInt());
                act.setGreen(dis.readInt());
                act.setBlue(dis.readInt());
                act.setAlpha(dis.readInt());
                act.setDuration(dis.readInt());
                ani.addAction(act);
            }
            data.getCurProject().addAnimation(ani);
        }

        dis.close();
        fis.close();
    }
}
