/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyostar.game.view;

import com.soyostar.emulator.engine.BaseView;
import com.soyostar.game.control.HelpControl;
import com.soyostar.game.model.GameData;
import com.soyostar.ui.Painter;
import java.awt.Color;

/**@2011 4.6 byVV
 *
 * 游戏帮助视图
 */
public class HelpView extends BaseView {

    private GameData gd = GameData.getGameData();
    private String text = "";
    private int itemWidth = 0;
    private int x, y;

    public void init() {
        setControl(new HelpControl());
        text = "界面等待实现中";

        x = (gd.screenWidth - itemWidth) / 2;

    }

    public void paint(Painter painter) {
        itemWidth = painter.stringWidth(text);
        y = (gd.screenHeight - painter.getFontHeight()) >> 1;
        painter.setFontStyle(Painter.STYLE_PLAIN);
        painter.setFontSize(20);
        painter.setColor(Color.black);
        painter.fillRect(0, 0, gd.screenWidth, gd.screenHeight);
        painter.setColor(Color.white);
        painter.drawString(text, x, y, Painter.LT);

        //绘制按钮
        painter.drawString("确定", 5, gd.screenHeight - painter.getFontHeight() >> 1, Painter.LT);

    }

    public void release() {
        gd.xIndex = 0;
        gd.yIndex = 0;
        gd = null;
       
        setControl(null);
    }
}
