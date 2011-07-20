package game.scene.map;

import com.soyostar.app.Painter;
import com.soyostar.app.Widget;
import game.impl.model.Sprite;

/**
 *
 * @author wp_g4
 */
public class SpriteLayer extends Widget {

    private Sprite sprite = null;

    public SpriteLayer(Sprite sprite) {
        this.sprite = sprite;
        this.setSize(sprite.getCurStepImage().getWidth(), sprite.getCurStepImage().getHeight());
    }

    @Override
    public void paint(Painter painter) {
        super.paint(painter);
        painter.drawImage(sprite.getCurStepImage(), 0, 0, Painter.LT);
    }
}
