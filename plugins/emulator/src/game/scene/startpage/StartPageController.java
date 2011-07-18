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
import game.RpgGame;

/**
 *
 * @author wp_g4
 */
public class StartPageController extends AbController implements TouchListener, ActionListener {

    private GameEngine ge = GameEngine.getInstance();
    private RpgGame rpgGame = (RpgGame) ge.getGame();
    private StartPageLayer startPageLayer = null;
    private LButton lButton = null;
    private Button button = null;

    public StartPageController(Render render) {
        super(render);
    }

    int color = 0xffaaaaaa;
    public void updateModel() {
        startPageLayer.setBackground(color++);
    }

    public void onObtain() {
        startPageLayer = new StartPageLayer();
        startPageLayer.setVisible(true);
        startPageLayer.setBackground(Color.BLACK);
        startPageLayer.setSize(ge.getScreenWidth(), ge.getScreenHeight());
        startPageLayer.setTouchListener(this);
        lButton = new LButton(Image.createImage("res/image/battler/001-Fighter01.png"), Image.createImage("res/image/battler/002-Fighter02.png"));
        lButton.setText("你好");
        lButton.setVisible(true);
        lButton.setSize(200, 60);
        lButton.setLocation(10, 20);
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
        rpgGame.setCurrentControl("game.scene.menu.MenuController");
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
