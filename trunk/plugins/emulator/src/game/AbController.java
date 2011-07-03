package game;

import com.soyostar.app.Component;
import com.soyostar.app.widget.Widget;
import engine.Render;

/**
 *
 * @author wp_g4
 */
public abstract class AbController implements Controller {

    private Render render = null;

    public AbController(Render render) {
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
