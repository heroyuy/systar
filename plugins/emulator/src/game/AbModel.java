package game;

import engine.GameEngine;
import game.impl.model.GameData;

/**
 *
 * @author wp_g4
 */
public abstract class AbModel {

    public GameEngine ge = GameEngine.getInstance();
    public RpgGame rpgGame = (RpgGame) ge.getGame();
    public GameData gd = (GameData) rpgGame.getModel("game.impl.model.GameData");
    private int index = -1;

    public final int getIndex() {
        return index;
    }

    public final void setIndex(int index) {
        if (this.index != -1) {
            try {
                throw new IllegalStateException("不能修改对象索引");
            } catch (IllegalStateException ex) {
                ex.printStackTrace();
            }
        } else {
            this.index = index;
        }
    }

    public abstract void update();
}
