/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game.scene.setting;

import com.soyostar.app.Color;
import com.soyostar.app.Image;
import com.soyostar.app.LButton;
import com.soyostar.app.Layer;
import com.soyostar.app.event.ActionListener;
import engine.GameEngine;
import engine.Render;
import game.AbController;
import game.Const;
import game.RpgGame;
import game.impl.model.GameData;
import game.util.Skin;

/**
 *菜单音乐设置选项
 *
 * @author by vv
 */
public class SettingController extends AbController {

    private GameEngine ge = GameEngine.getInstance();
    private RpgGame rpgGame = (RpgGame) ge.getGame();
    private GameData gd = (GameData) rpgGame.getModel(0);
    private LButton lbOk = null;
    private LButton lbNot = null;
    private Layer bg = null;

    public SettingController(Render render) {
        super(render);

        Skin skin = new Skin("res/image/skin/windowskin_1.png");
        bg = new Layer();
//        bg.setBackground(Color.GREEN);
        bg.setBackgroundImage(skin.createAlphaBg(ge.getScreenWidth(), ge.getScreenHeight(), false));
        bg.setSize(ge.getScreenWidth(), ge.getScreenHeight());
        bg.setLocation(0, 0);
        bg.setVisible(true);
        lbOk = new LButton(Image.createImage("res/image/battler/001-Fighter01.png"), Image.createImage("res/image/battler/002-Fighter02.png"));
        lbOk.setText("   音  乐   播  放");
        lbOk.setBackground(Color.RED);

        lbOk.setLocation(80, 80);
        lbOk.setSize(100, 35);
        lbOk.setVisible(true);
        lbOk.setActionListener(new ActionListener() {

            public void actionPerformed(Object t) {

                rpgGame.setCurrentControl(Const.ControlId.MENU);
//                throw new UnsupportedOperationException("Not supported yet.");
            }
        });
        lbNot = new LButton(Image.createImage("res/image/battler/001-Fighter01.png"), Image.createImage("res/image/battler/002-Fighter02.png"));
        lbNot.setText("   音  乐  关   闭  ");
        lbNot.setLocation(80, 125);
        lbNot.setSize(100, 35);
        lbNot.setVisible(true);
        lbNot.setBackground(Color.RED);

        lbNot.setActionListener(new ActionListener() {

            public void actionPerformed(Object t) {
                rpgGame.setCurrentControl(Const.ControlId.MENU);
//                throw new UnsupportedOperationException("Not supported yet.");
            }
        });

    }

    public void onObtain() {

        addWidget(bg);
        bg.addWidget(lbOk);
        bg.addWidget(lbNot);
//        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void onLose() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void updateModel() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
