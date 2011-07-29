/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game.scene.setting;

import com.soyostar.app.LButton;
import com.soyostar.app.Layer;
import com.soyostar.app.event.ActionListener;
import engine.Render;
import game.AbController;
import game.util.Skin;

/**
 *菜单音乐设置选项
 *
 * @author by vv
 */
public class SettingController extends AbController {

    private LButton lbOk = null;
    private LButton lbNot = null;
    private Layer bg = null;
    private int lbW_ = 120, lbH_ = 30, lbGap_ = 10;

    public SettingController() {
        Skin skin = new Skin("res/image/skin/001-Blue01.png");
        bg = new Layer();
        bg.setBackgroundImage(skin.createBlueBg(ge.getScreenWidth(), ge.getScreenHeight(), false));
        bg.setSize(ge.getScreenWidth(), ge.getScreenHeight());
        bg.setLocation(0, 0);
        bg.setVisible(true);
        lbOk = new LButton("   音  乐  播   放 ");
        lbOk.setSize(lbW_, lbH_);
        lbOk.setAfocalImage(skin.createAlphaBg(lbW_, lbH_, false));
        lbOk.setFocusImage(skin.createAlphaBg(lbW_, lbH_, true));
        lbOk.setLocation((ge.getScreenWidth() - lbW_) / 2, ((ge.getScreenHeight()-lbH_) / 2) - (lbH_ + lbGap_));
        lbOk.setVisible(true);

        lbOk.setActionListener(new ActionListener() {

            public void actionPerformed(Object t) {

                rpgGame.setCurrentControl("game.scene.menu.MenuController");
//                throw new UnsupportedOperationException("Not supported yet.");
            }
        });
        lbNot = new LButton("   音  乐  关   闭  ");
        lbNot.setSize(lbW_, lbH_);
        lbNot.setAfocalImage(skin.createAlphaBg(lbW_, lbH_, false));
        lbNot.setFocusImage(skin.createAlphaBg(lbW_, lbH_, true));
        lbNot.setLocation((ge.getScreenWidth() - lbW_) / 2, (ge.getScreenHeight()-lbH_) / 2);
        lbNot.setVisible(true);
        lbNot.setActionListener(new ActionListener() {

            public void actionPerformed(Object t) {
                rpgGame.setCurrentControl("game.scene.menu.MenuController");
//                throw new UnsupportedOperationException("Not supported yet.");
            }
        });
        bg.addWidget(lbOk);
        bg.addWidget(lbNot);
    }

    public void onObtain() {

        addWidget(bg);

//        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void onLose() {
    }

    public void updateModel() {
    }
}
