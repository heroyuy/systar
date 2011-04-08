/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.soyostar.game.view;

import com.soyostar.emulator.engine.BaseView;
import com.soyostar.emulator.engine.GameEngine;
import com.soyostar.game.control.SettingControl;
import com.soyostar.game.model.GameData;
import com.soyostar.game.tools.Tools;
import com.soyostar.ui.Painter;
import java.awt.Color;

/**@2011.4.6 byVV
 *
 * 游戏设置
 */
public class SettingView extends BaseView {

    private GameData gd = GameData.getGameData();
    private GameEngine ge = GameEngine.getInstance();
    private int itemWidth = 0;
    private int space = 5;//行间距
    private int num = 2;
    private int x, y;

    public void init() {
        setControl(new SettingControl());
        itemWidth = 14;
        x = (gd.screenWidth - itemWidth * 5) >>1;
   
    }

    public void paint(Painter painter) {
     y = (gd.screenHeight - num * painter.getFontHeight() - (num - 1) * space) >>1;
     painter.setFontStyle(Painter.STYLE_PLAIN);
         painter.setFontSize(20);
         painter.setColor(Color.BLACK);
        painter.fillRect( 0, 0, gd.screenWidth, gd.screenHeight);
        //绘制按钮
           painter.setColor( Color.white);
        painter.drawString( "确定", 5, gd.screenHeight - painter.getFontHeight(),Painter.LT);
        //绘制阴影
          painter.setColor( Color.red);
        painter.fillRoundRect( x, y + gd.yIndex * (painter.getFontHeight() + space), itemWidth * 5, painter.getFontHeight() + space - 2, 8,8);
        //绘制选项
        painter.setColor(Color.white);
        painter.drawString( "音乐", x, y,Painter.LT);
        if (gd.musicOn) {
            painter.drawString( "开", x + itemWidth * 3, y,Painter.LT);
        } else {
            painter.drawString( "关", x + itemWidth * 3, y,Painter.LT);
        }

        painter.drawString( "音效", x, y +painter.getFontHeight() + space, Painter.LT);
        if (gd.soundOn) {
            painter.drawString("开", x + itemWidth * 3, y +painter.getFontHeight()+ space, Painter.LT);
        } else {
           painter.drawString( "关", x + itemWidth * 3, y + painter.getFontHeight()+ space,  Painter.LT);
        }
        //绘制两个三角形
        Color color = Color.white;
        if (ge.getTicker() % 12 > 6) {
            color = Color.white;
        } else {
            color = Color.blue;
        }
       Tools.drawTriangle(painter, x + itemWidth * 3 - 10, y + painter.getFontHeight()>>1 + gd.yIndex * (painter.getFontHeight() + space), x + itemWidth * 3 - 5, y + 2 + gd.yIndex * (painter.getFontHeight() + space), x + itemWidth * 3 - 5, y +painter.getFontHeight() - 2 + gd.yIndex * (painter.getFontHeight() + space), color);
       Tools.drawTriangle(painter, x + itemWidth * 5 - 10, y + 2 + gd.yIndex * (painter.getFontHeight() + space), x + itemWidth * 5 - 10, y + painter.getFontHeight() - 2 + gd.yIndex * (painter.getFontHeight()+ space), x + itemWidth * 5 - 5, y + painter.getFontHeight()>>1 + gd.yIndex * (painter.getFontHeight() + space), color);

    }

    public void release() {
        gd.xIndex = 0;
        gd.yIndex = 0;
        gd = null;
        ge = null;
      
        setControl(null);
    }
}
