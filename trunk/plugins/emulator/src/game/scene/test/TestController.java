/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game.scene.test;

import com.soyostar.app.Button;
import com.soyostar.app.Color;
import com.soyostar.app.Image;
import com.soyostar.app.LButton;
import com.soyostar.app.LLabel;
import com.soyostar.app.LTextArea;
import com.soyostar.app.Layer;
import com.soyostar.app.Painter;
import engine.GameEngine;
import engine.Render;
import game.AbController;
import game.RpgGame;
import game.impl.model.GameData;
import game.util.Skin;

/**
 *
 * @author Administrator
 */
public class TestController extends AbController {

    private GameEngine ge = GameEngine.getInstance();
    private RpgGame rpgGame = (RpgGame) ge.getGame();
    private GameData gd = (GameData) rpgGame.getModel(0);
    private Layer bg = null;
    private Layer menu = null;
    private Layer menu2 = null;
    private LButton lb = null;
    private Button btn = null;
    private LLabel label = null;
    private LTextArea lta = null;

    public TestController(Render render) {
        super(render);
        Skin skin = new Skin("res/image/skin/windowskin_1.png");
        bg = new Layer();
//        bg.setBackground(Color.GREEN);
        bg.setBackgroundImage(skin.createAlphaBg(ge.getScreenWidth(), ge.getScreenHeight(), false));
        bg.setSize(ge.getScreenWidth(), ge.getScreenHeight());
        bg.setLocation(0, 0);
        bg.setVisible(true);
//        Action action = new ShadeAction(bg, Color.BLACK, Color.RED, 20, 1000);
//        action.activate();
//        gd.actionManager.addAction(action);

        menu = new Layer();
        menu.setBackground(Color.BLUE);
        menu.setSize(80, 40);
        menu.setLocation(20, 10);
        menu.setVisible(true);

        menu2 = new Layer();
        menu2.setBackground(Color.BLUE);
        menu2.setSize(80, 40);
        menu2.setLocation(20, 80);
        menu2.setVisible(true);

        lb = new LButton(Image.createImage("res/image/battler/001-Fighter01.png"), Image.createImage("res/image/battler/002-Fighter02.png"));
        lb.setText("LButton");
        lb.setBackground(Color.RED);
        lb.setLocation(-10, 20);
        lb.setSize(60, 30);
        lb.setVisible(true);

        btn = new Button("Button");
        btn.setSize(80, 30);
        btn.setLocation(100, 50);

        label = new LLabel() {

            @Override
            public void paint(Painter painter) {
                this.setText("fps:" + ge.getFps() + " ticker:" + ge.getTicker());
                super.paint(painter);
            }
        };
        label.setBackground(Color.GREEN);
        label.setText("LLabel");
        label.setLocation(100, 200);
        label.setSize(80, 30);
        label.setTextColor(Color.BLUE);
        label.setTextAnchor(Painter.HV);
        label.setVisible(true);

        lta = new LTextArea("支持图形调试的图形子类。重写 Graphics 中的大多数方法。"
                + "DebugGraphics 对象很少通过手工创建。它们通常在 JComponent 的 "
                + "debugGraphicsOptions 因使用 setDebugGraphicsOptions() "
                + "方法而发生更改时自动创建。");
        lta.setSize(100, 50);
        lta.setLocation(10, 200);
        lta.setVisible(true);
    }

    public void onObtain() {
        addWidget(bg);
        bg.addWidget(menu);
        bg.addWidget(menu2);
        menu.addWidget(lb);
        bg.addWidget(label);
        addComponent(btn);
        bg.addWidget(lta);
    }

    public void onLose() {
    }

    public void updateModel() {
    }
}