package game;

import com.soyostar.app.Component;
import com.soyostar.app.Widget;
import engine.GameEngine;
import engine.Render;
import game.impl.model.GameData;

/**
 *
 * @author wp_g4
 */
public abstract class AbController implements Controller {

    public GameEngine ge = GameEngine.getInstance();
    public RpgGame rpgGame = (RpgGame) ge.getGame();
    public GameData gd = (GameData) rpgGame.getModel("game.impl.model.GameData");
    private Render render = null;

    void setRender(Render render) {
        this.render = render;
    }

    public void addComponent(Component component) {
        render.addComponent(component);
    }

    public void removeComponent(Component component) {
        render.removeComponent(component);
    }

    public void removeAllComponents() {
        render.removeAllComponents();
    }

    public void addWidget(Widget widget) {
        render.addWidget(widget);
    }

    public void removeWidget(Widget widget) {
        render.removeWidget(widget);
    }

    public void removeAllWidgets() {
        render.removeAllWidgets();
    }
}
