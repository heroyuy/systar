package game.scene.map;

import com.soyostar.app.Image;
import com.soyostar.app.Painter;
import com.soyostar.app.Widget;
import game.impl.model.Player;

/**
 *
 * @author wp_g4
 */
public class SpriteLayer extends Widget {

    private Player player = null;

    public SpriteLayer(Player player) {
        this.player = player;
        this.setSize(player.getCurStepImage().getWidth(), player.getCurStepImage().getHeight());
    }

    @Override
    public void paint(Painter painter) {
        super.paint(painter);
        painter.drawImage(player.getCurStepImage(), 0, 0, Painter.LT);
    }
}
