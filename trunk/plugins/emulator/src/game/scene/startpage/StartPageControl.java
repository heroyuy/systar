/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game.scene.startpage;

import com.soyostar.app.Button;
import com.soyostar.app.Color;
import com.soyostar.app.Image;
import com.soyostar.app.LButton;
import com.soyostar.app.event.ActionListener;
import com.soyostar.app.event.TouchEvent;
import com.soyostar.app.event.TouchListener;
import engine.GameEngine;
import engine.Render;
import game.AbController;
import game.Const;
import game.RpgGame;

/**
 *
 * @author Administrator
 */
public class StartPageControl extends AbController implements TouchListener, ActionListener {

    private GameEngine ge = GameEngine.getInstance();
    private RpgGame rpgGame = (RpgGame) ge.getGame();
    private StartPageLayer startPageLayer = null;
    private LButton lButton = null;
    private Button button = null;

    public StartPageControl(Render render) {
        super(render);
    }

    public void updateModel() {
    }

    public void onObtain() {
        startPageLayer = new StartPageLayer();
        startPageLayer.setVisible(true);
        startPageLayer.setBackground(Color.BLACK);
        startPageLayer.setSize(ge.getScreenWidth(), ge.getScreenHeight());
        startPageLayer.setTouchListenet(this);
        lButton = new LButton(Image.createImage("res/image/battler/001-Fighter01.png"), Image.createImage("res/image/battler/002-Fighter02.png"));
        lButton.setText("你好");
        lButton.setVisible(true);
        lButton.setSize(200, 60);
        lButton.setBackground(Color.RED);
        lButton.setActionListener(this);
        button = new Button("Good!!");
        button.setWidth(50);
        button.setHeight(20);
        button.setX(10);
        button.setY(100);
        button.setListener(this);
        addWidget(startPageLayer);
        addWidget(lButton);
        addComponent(button);
    }

    public void onLose() {
    }

    public boolean onTouchEvent(Object t, TouchEvent te) {
        System.out.println("按键");
        rpgGame.setCurrentControl(Const.ControlId.MENU);
        return true;
    }

    public void actionPerformed(Object t) {
        if (t.equals(button)) {
            lButton.setText("高级按键被按了");
        } else if (t.equals(lButton)) {
            button.setText("低级的按键被按了");
        }
    }
}
