/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package module;

import java.awt.Image;
import java.awt.Graphics;
import java.io.*;
import java.util.Vector;
import java.awt.image.BufferedImage;
import javax.swing.*;
import model.*;

/**
 * 动画管理器
 *
 */
public class AnimationManager {

    private static AnimationManager am = new AnimationManager();

    public static AnimationManager getInstance() {
        return am;
    }
    private String gamePath = Project.getProjectPath();
    private Vector<Animation> animationList = null;
    private int curAnimationIndex = -1;

//	private int curFrameIndex = -1;
    private AnimationManager() {
        animationList = new Vector<Animation>();
    }

    public boolean saveAniFile() {
        boolean judge = true;
        if (animationList.isEmpty()) {
            return false;
        }
        try {
            FileOutputStream fos = new FileOutputStream(Project.getProjectPath() + "\\" + "data/ani.gat");
            DataOutputStream dos = new DataOutputStream(fos);
            int num = animationList.size();
            Animation ani = null;
            dos.writeInt(num);// 写入总数
            for (int i = 0; i < num; i++) {
                ani = animationList.elementAt(i);
                dos.writeInt(ani.index);
                dos.writeUTF(ani.name);
                dos.writeUTF(ani.imageName);
                dos.writeUTF(ani.soundName);
                dos.writeInt(ani.frameWidth);
                dos.writeInt(ani.frameHeight);
                dos.writeInt(ani.frameNum);
                for (int j = 0; j < ani.frameNum; j++) {
                    dos.writeInt(ani.frames[j].num);
                    dos.writeInt(ani.frames[j].offsetX);
                    dos.writeInt(ani.frames[j].offsetY);
                }
            }
            dos.close();
            fos.close();
        } catch (IOException e) {
            judge = false;
            e.printStackTrace();
        }
        return judge;
    }

    private void analysisAniFile(File aniFile) {

        FileInputStream is = null;
        DataInputStream dis = null;
        Animation ani[] = null;
        try {
            is = new FileInputStream(aniFile);
            dis = new DataInputStream(is);
            int sum = dis.readInt();
            ani = new Animation[sum];
            for (int i = 0; i < sum; i++) {
                ani[i] = new Animation();
                ani[i].index = dis.readInt();
                ani[i].name = dis.readUTF();
                ani[i].imageName = dis.readUTF();
                ani[i].image = new ImageIcon(gamePath + "\\image\\animation\\" + ani[i].imageName).getImage();
                ani[i].soundName = dis.readUTF();
                ani[i].frameWidth = dis.readInt();
                ani[i].frameHeight = dis.readInt();
                ani[i].frameNum = dis.readInt();
                ani[i].frames = new Frame[ani[i].frameNum];
                for (int j = 0; j < ani[i].frameNum; j++) {
                    ani[i].frames[j] = new Frame();
                    ani[i].frames[j].num = dis.readInt();
                    ani[i].frames[j].offsetX = dis.readInt();
                    ani[i].frames[j].offsetY = dis.readInt();
                    Image curImage = new BufferedImage(ani[i].frameWidth, ani[i].frameHeight, BufferedImage.TYPE_INT_ARGB);
                    Graphics g = curImage.getGraphics();
                    int picw = ani[i].image.getWidth(null) / ani[i].frameWidth;
                    g.drawImage(ani[i].image, 0, 0, ani[i].frameWidth, ani[i].frameHeight, (ani[i].getFrame(j).num - 1) % picw * ani[i].frameWidth,
                        (ani[i].getFrame(j).num - 1) / picw * ani[i].frameHeight, (ani[i].getFrame(j).num - 1) % picw * ani[i].frameWidth + ani[i].frameWidth,
                        (ani[i].getFrame(j).num - 1) / picw * ani[i].frameHeight + ani[i].frameHeight,
                        null);
                    ani[i].getFrame(j).image = curImage;
                }
                animationList.add(ani[i]);
            }
            dis.close();
            is.close();
        } catch (Exception e) {
//            e.printStackTrace();
            System.out.println("找不到ani.gat");
        }

    }

    public String[] getAllAnimations() {
        // TODO 自动生成方法存根
        String[] data = new String[animationList.size()];
        for (int i = 0; i < data.length; i++) {
            data[i] = animationList.get(i).name;
        }
        return data;
    }

    public Animation getCurAnimation() {
        if (isEmpty()) {
            return null;
        }
        if (curAnimationIndex == -1) {
            return null;
        } else {
            return animationList.get(curAnimationIndex);
        }
    }

    public boolean isEmpty() {
        return animationList.isEmpty();
    }

    public Animation getAnimation(int index) {
        if (isEmpty()) {
            return null;
        }
        if (index == -1) {
            return null;
        } else {
            return animationList.get(index);
        }
    }

    public void setCurAnimation(Animation ani) {
        if (curAnimationIndex == -1) {
            return;
        }
        System.out.println("curAnimationIndex " + curAnimationIndex);
        animationList.setElementAt(ani, curAnimationIndex);
    }

    public int getAnimationMaxNum() {
        return animationList.size();
    }

    public int getCurAnimationIndex() {
        return curAnimationIndex;
    }

    public void init() {
        File file = new File(gamePath + "\\data\\ani.gat");

        analysisAniFile(file);
//		File[] f = file.listFiles();
//		System.out.println(f);
//		if (f != null) {
//			for (int i = 0; i < f.length; i++) {
//				if (f[i].getName().endsWith(".ani")) {
//					System.out.println(f[i]);// debug
//					analysisAniFile(f[i]);
//				}
//			}
//		}
    }

    public void setCurAnimationIndex(int curAnimationIndex) {
        this.curAnimationIndex = curAnimationIndex;
    }

    public void delAnimation(int num) {
        System.out.println("anisize:" + animationList.size());
        if ((num >= 0) && (num < animationList.size())) {
            animationList.removeElementAt(num);
            System.out.println("remove");
        }
    }

    public void addAnimation(String name, int frameNum, int frameWidth, int frameHeight, String imageName, String soundName) {
        Animation ani = new Animation();
        ani.index = animationList.size();
        ani.name = name;
        ani.frameNum = frameNum;
        ani.frames = new Frame[frameNum];
        for (int i = 0; i < frameNum; i++) {
            ani.frames[i] = new Frame();
        }
        ani.frameWidth = frameWidth;
        ani.frameHeight = frameHeight;
        ani.imageName = imageName;
        ani.image = new ImageIcon(gamePath + "\\image\\animation\\" + ani.imageName).getImage();
        ani.soundName = soundName;
        animationList.add(ani);
        curAnimationIndex = ani.index;

        System.out.println("添加新动画：\n" + "  编号：" + ani.index + "  名称：" + ani.name + "  宽度：" + ani.frameWidth + "  高度" + ani.frameHeight + "  图片名：" + ani.imageName + "  音效名：" + ani.soundName);

    }
}
