package engine;

import com.soyostar.app.Component;
import com.soyostar.app.widget.Widget;

/**
 *
 * @author Administrator
 */
public interface Render {

    public void addComponent(Component component);

    public void removeComponent(Component component);

    public void removeAllComponents();

    public void addWidget(Widget widget);

    public void removeWidget(Widget widget);

    public void removeAllWidgets();
}
