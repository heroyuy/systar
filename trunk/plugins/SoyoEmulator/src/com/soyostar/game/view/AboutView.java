package com.soyostar.game.view;

import com.soyostar.emulator.engine.BaseView;
import com.soyostar.game.control.AboutControl;
import com.soyostar.game.model.GameData;
import com.soyostar.ui.Painter;
import java.awt.Color;

/**
 *@2011.4.5 by vv
 * 游戏关于视图
 */
public class AboutView extends BaseView {

    private GameData gd = GameData.getGameData();
    private String text = "";
    private int itemWidth = 0;
    private int x, y;

    public void init() {
        setControl(new AboutControl());
        text = "界面等待实现中";
        x = (gd.screenWidth - itemWidth) >> 1;
    }

    public void paint(Painter painter) {
        itemWidth = painter.stringWidth(text);
        y = (gd.screenHeight - painter.getFontHeight()) >> 1;
        painter.setFontSize(16);//large
        painter.setColor(Color.black);
        painter.fillRect(0, 0, gd.screenWidth, gd.screenHeight);
        painter.setColor(Color.white);
        painter.drawString(text, x, y, Painter.LT);//?

        //绘制按钮
        painter.drawString("确定", 5, gd.screenHeight - painter.getFontHeight(),Painter.LT);

    }

    public void release() {
        gd.xIndex = 0;
        gd.yIndex = 0;
        gd = null;
        setControl(null);
    }
}
