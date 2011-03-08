package view;

import engine.BaseView;
import control.HelpControl;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import model.GameData;
import control.HelpControl;
import model.Const.Color;
import system.Painter;

/**
 *
 * 游戏帮助视图
 */
public class HelpView extends BaseView {

    private GameData gd = GameData.getGameData();
    private Font font = Font.getFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_LARGE);
    private String text = "";
    private int itemWidth = 0;
    private int x, y;

    public void init() {
        setControl(new HelpControl());
        text = gd.gameObjectManager.getConfig().help;
        System.out.println("helpview：" + text);
        itemWidth = font.stringWidth(text);
        x = (gd.screenWidth - itemWidth) / 2;
        y = (gd.screenHeight - font.getHeight()) / 2;
    }

    public void paint(Graphics g) {
        g.setFont(font);
        Painter.fillRect(g, 0, 0, gd.screenWidth, gd.screenHeight, 0x000000);
        Painter.drawDialog(g, 0, 0, gd.screenWidth, gd.screenHeight, Painter.DIALOG_DEEP);
        Painter.drawWordWrapString(g, text, 10, 20, gd.screenWidth - 20, gd.screenHeight - 20, Color.white);
        //绘制按钮
        Painter.drawString(g, "确定", 5, gd.screenHeight - font.getHeight(), 0xffffff);

    }

    public void release() {
//        gd.xIndex = 0;
//        gd.yIndex = 0;
        gd = null;
        font = null;
        setControl(null);
    }
}
