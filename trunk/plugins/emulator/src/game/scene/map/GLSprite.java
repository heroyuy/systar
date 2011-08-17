package game.scene.map;

import game.impl.model.Character;

import com.soyostar.app.Image;
import com.soyostar.app.Painter;
import com.soyostar.app.Widget;

/**
 * 
 * @author wp_g4
 */
public class GLSprite extends Widget {

	private Character sprite = null;

	public GLSprite(Character sprite) {
		this.sprite = sprite;
		this.setSize(sprite.width, sprite.height);
		this.setLocation(sprite.x, sprite.y);
		this.setVisible(true);
	}

	@Override
	public void paint(Painter painter) {
		super.paint(painter);
		painter.drawImage(Image.test(sprite.getCurStepImage()), 0, 0,
				Painter.LT);
	}

}
