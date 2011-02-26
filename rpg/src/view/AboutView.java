package view;

import engine.BaseView;
import control.AboutControl;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import model.GameData;
import control.AboutControl;
import system.Painter;

/**
 *
 * 游戏关于视图
 */
public class AboutView extends BaseView {

    private GameData gd = GameData.getGameData();
    private Font font = Font.getFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_LARGE);
    private String text = "";
    private int itemWidth = 0;
    private int x,  y;

    public void init() {
        setControl(new AboutControl());
        text = "界面等待实现中";
        itemWidth = font.stringWidth(text);
        x = (gd.screenWidth - itemWidth) / 2;
        y = (gd.screenHeight - font.getHeight()) / 2;
    }

    public void paint(Graphics g) {
        g.setFont(font);
        Painter.fillRect(g, 0, 0, gd.screenWidth, gd.screenHeight, 0x000000);
        Painter.drawString(g, text, x, y, 0xffffff);

        //绘制按钮
        Painter.drawString(g, "确定", 5, gd.screenHeight - font.getHeight(), 0xffffff);

    }

    public void release() {
        gd.xIndex = 0;
        gd.yIndex = 0;
        gd = null;
        font = null;
        setControl(null);
    }
}
