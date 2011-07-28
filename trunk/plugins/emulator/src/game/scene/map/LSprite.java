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
        this.setSize(sprite.getCurStepImage().getWidth(), sprite.getCurStepImage().getHeight());
    }

    @Override
    public void paint(Painter painter) {
        super.paint(painter);
        painter.drawImage(sprite.getCurStepImage(), 0, 0, Painter.LT);
    }

    public static class LSpriteComparator implements Comparator<LSprite> {

        public int compare(LSprite ls1, LSprite ls2) {
            int num = ls1.sprite.row - ls2.sprite.row;
            if (num == 0) {
                num = ls1.sprite.col - ls2.sprite.col;
            }
            return num;
        }
    }
}
