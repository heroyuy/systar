/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game.scene.startpage;

import com.soyostar.app.Color;
import com.soyostar.app.Image;
import com.soyostar.app.widget.LButton;
import engine.GameEngine;
import engine.Render;
import game.AbController;
import game.RpgGame;

/**
 *
 * @author Administrator
 */
public class StartPageControl extends AbController {

    private GameEngine ge = GameEngine.getInstance();
    private RpgGame rpgGame = (RpgGame) ge.getGame();
    private StartPageLayer startPageLayer = null;

    public StartPageControl(Render render) {
        super(render);
    }

    public void updateModel() {
    }

    public void onObtain() {
        System.out.println("StartPageControl-onObtain");
        startPageLayer = new StartPageLayer();
        startPageLayer.setVisible(true);
        startPageLayer.setSize(ge.getScreenWidth(), ge.getScreenHeight());
        LButton lb = new LButton(Image.createImage("res/image/battler/001-Fighter01.png"), Image.createImage("res/image/battler/002-Fighter02.png"));
        lb.setText("你好");
        lb.setVisible(true);
        lb.setSize(200, 60);
        lb.setBackground(Color.RED);

        addWidget(startPageLayer);
        addWidget(lb);
    }

    public void onLose() {
    }
}
