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
    private GameData gd = (GameData) rpgGame.getModel("game.impl.model.GameData");
    private LButton lbOk = null;
    private LButton lbNot = null;
    private Layer bg = null;

    public SettingController(Render render) {
        super(render);

        Skin skin = new Skin("res/image/skin/001-Blue01.png");
        bg = new Layer();
        bg.setBackgroundImage(skin.createBlueBg(ge.getScreenWidth(), ge.getScreenHeight(), false));
        bg.setSize(ge.getScreenWidth(), ge.getScreenHeight());
        bg.setLocation(0, 0);
        bg.setVisible(true);
        lbOk = new LButton("   音  乐   播  放");
        lbOk.setAfocalImage(skin.createAlphaBg(100, 35, false));
        lbOk.setFocusImage(skin.createAlphaBg(100, 35, true));
        lbOk.setLocation(80, 80);
        lbOk.setSize(100, 35);
        lbOk.setBackground(Color.RED);
        lbOk.setVisible(true);
        lbOk.setActionListener(new ActionListener() {

            public void actionPerformed(Object t) {

                rpgGame.setCurrentControl("game.scene.menu.MenuController");
//                throw new UnsupportedOperationException("Not supported yet.");
            }
        });
        lbNot = new LButton("   音  乐  关   闭  ");
        lbNot.setAfocalImage(skin.createAlphaBg(100, 35, false));
        lbNot.setFocusImage(skin.createAlphaBg(100, 35, true));
        lbNot.setLocation(80, 125);
        lbNot.setSize(100, 35);
        lbNot.setVisible(true);
        lbNot.setBackground(Color.RED);
        lbNot.setActionListener(new ActionListener() {

            public void actionPerformed(Object t) {
                rpgGame.setCurrentControl("game.scene.menu.MenuController");
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
    }

    public void updateModel() {
    }
}
