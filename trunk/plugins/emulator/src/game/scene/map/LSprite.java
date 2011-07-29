package game.scene.map;

import com.soyostar.app.Painter;
import com.soyostar.app.Widget;
import game.impl.model.Sprite;
import java.util.Comparator;

/**
 *
 * @author wp_g4
 */
public class LSprite extends Widget {

    private Sprite sprite = null;

    public LSprite(Sprite sprite) {
        this.sprite = sprite;
        this.setSize(sprite.width, sprite.height);
        this.setLocation(sprite.x, sprite.y);
        this.setVisible(true);
    }

    @Override
    public void paint(Painter painter) {
        super.paint(painter);
        painter.drawImage(sprite.getCurStepImage(), 0, 0, Painter.LT);
    }

    public void update() {
        this.setLocation(sprite.x, sprite.y);
    }
}
