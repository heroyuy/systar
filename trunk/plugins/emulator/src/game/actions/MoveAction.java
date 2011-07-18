package game.actions;

import com.soyostar.app.action.Action;
import engine.GameEngine;
import game.RpgGame;
import game.impl.model.GameData;
import game.impl.model.Player;
import game.scene.map.SpriteLayer;

/**
 *
 * @author Administrator
 */
public class MoveAction extends Action {

    private GameEngine ge = GameEngine.getInstance();
    private RpgGame rpgGame = (RpgGame) ge.getGame();
    private GameData gd = (GameData) rpgGame.getModel("game.impl.model.GameData");
    private static final int UP = 0;
    private static final int DOWN = 1;
    private static final int LEFT = 2;
    private static final int RIGHT = 3;
    private int face = UP;
    private int index = 0;//行动序列，0 1 2 3
    private Player player = null;
    private SpriteLayer spriteLayer = null;

    private MoveAction() {
    }

    public static MoveAction createMoveUpAction(SpriteLayer spriteLayer, Player player) {
        MoveAction ma = new MoveAction();
        ma.player = player;
        ma.spriteLayer = spriteLayer;
        ma.face = UP;
        return ma;
    }

    public static MoveAction createMoveDownAction(SpriteLayer spriteLayer, Player player) {
        MoveAction ma = new MoveAction();
        ma.player = player;
        ma.spriteLayer = spriteLayer;
        ma.face = DOWN;
        return ma;
    }

    public static MoveAction createMoveLeftAction(SpriteLayer spriteLayer, Player player) {
        MoveAction ma = new MoveAction();
        ma.player = player;
        ma.spriteLayer = spriteLayer;
        ma.face = LEFT;
        return ma;
    }

    public static MoveAction createMoveRightAction(SpriteLayer spriteLayer, Player player) {
        MoveAction ma = new MoveAction();
        ma.player = player;
        ma.spriteLayer = spriteLayer;
        ma.face = RIGHT;
        return ma;
    }

    public void run() {
        if (index < 4) {
            switch (face) {
                case UP:
                    spriteLayer.setLocation(spriteLayer.getX(), spriteLayer.getY() - gd.curMap.cellHeight / 4);
                    break;
                case DOWN:
                    spriteLayer.setLocation(spriteLayer.getX(), spriteLayer.getY() + gd.curMap.cellHeight / 4);
                    break;
                case LEFT:
                    spriteLayer.setLocation(spriteLayer.getX() - gd.curMap.cellWidth / 4, spriteLayer.getY());
                    break;
                case RIGHT:
                    spriteLayer.setLocation(spriteLayer.getX() + gd.curMap.cellWidth / 4, spriteLayer.getY());
                    break;
            }
            player.setCurStepImage(face, (index+1)%4);
            index++;
        } else {
            freeze();
        }
    }
}
