package game.scene.startpage;

import com.soyostar.app.Color;
import com.soyostar.app.Image;
import com.soyostar.app.Painter;
import com.soyostar.app.Widget;
import engine.GameEngine;
import game.RpgGame;
import game.impl.model.GameData;
import game.util.Skin;

/**
 *
 * @author Administrator
 */
public class StartPageLayer extends Widget {

    private GameEngine ge = GameEngine.getInstance();
    private RpgGame rpgGame = (RpgGame) ge.getGame();
    private GameData gd = (GameData) rpgGame.getModel("game.impl.model.GameData");
    private Skin skin = null;
    private Image bg = null;

    public StartPageLayer() {
        skin = new Skin("res/image/skin/001-Blue01.png");
        bg = skin.createBlueBg(ge.getScreenWidth(), ge.getScreenHeight(), false);

    }

    @Override
    public void paint(Painter p) {
        super.paint(p);
        p.drawImage(bg, ge.getScreenWidth() / 2, ge.getScreenHeight() / 2, Painter.HV);
        if (ge.getTicker() % 10 > 4) {
            p.setColor(Color.WHITE);
            p.setTextSize(30);
            p.drawString("按任意键继续游戏", ge.getScreenWidth() / 2, ge.getScreenHeight() / 2, Painter.HV);
        }
    }
}
