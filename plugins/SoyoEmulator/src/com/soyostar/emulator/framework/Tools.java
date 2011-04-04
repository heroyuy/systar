package com.soyostar.emulator.framework;

import com.soyostar.game.model.GameData;
import com.soyostar.ui.Painter;
import java.awt.Color;
import java.util.Random;

/**
 *
 * @author Administrator
 */
public class Tools {

    public static int GetRandom(int data) {
        int rand = new Random().nextInt();
        return Math.abs(rand % data);
    }

    public static int GetRandom(int rs, int re)//获取随机数
    {
        int tmp = rs + Math.abs(new Random().nextInt() % (re - rs + 1));
        return tmp;
    }
    //黑色清屏

    public static void cleanScreen(Painter painter) {
        painter.setClip(0, 0, GameData.getGameData().screenWidth, GameData.getGameData().screenHeight);
        painter.setColor(Color.black);
        painter.fillRect(0, 0, GameData.getGameData().screenWidth, GameData.getGameData().screenHeight);
    }

    public static int[] sort(int[] arraylist) {//冒泡排序
        int len = arraylist.length;
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                if (arraylist[j] < arraylist[i]) {
                    int temp = 0;
                    temp = arraylist[j];
                    arraylist[j] = arraylist[i];
                    arraylist[i] = temp;
                }
            }
        }
        return arraylist;
    }
}
