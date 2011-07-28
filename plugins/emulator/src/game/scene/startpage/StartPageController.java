package game.scene.startpage;

import com.soyostar.app.Color;
import com.soyostar.app.event.TouchEvent;
import com.soyostar.app.event.TouchListener;
import engine.Render;
import game.AbController;

/**
 *
 * @author wp_g4
 */
public class StartPageController extends AbController implements TouchListener {

    private StartPageLayer startPageLayer = null;

    public StartPageController(Render render) {
        super(render);
    }

    public void updateModel() {
    }

    public void onObtain() {
        startPageLayer = new StartPageLayer();
        startPageLayer.setVisible(true);
        startPageLayer.setBackground(Color.BLACK);
        startPageLayer.setSize(ge.getScreenWidth(), ge.getScreenHeight());
        startPageLayer.setTouchListener(this);
        addWidget(startPageLayer);
    }

    public void onLose() {
    }

    public boolean onTouchEvent(Object t, TouchEvent te) {
        if (te.getType() == TouchEvent.TOUCH_DOWN) {
            System.out.println("StartPage-down");
            rpgGame.setCurrentControl("game.scene.menu.MenuController");
        }
        return true;
    }
}
