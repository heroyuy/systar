package game.actions;

import com.soyostar.app.action.Action;
import game.impl.model.Sprite;
import game.scene.map.LSprite;

/**
 *
 * @author Administrator
 */
public class MoveAction extends Action {

    public static final int UP = 0;
    public static final int DOWN = 1;
    public static final int LEFT = 2;
    public static final int RIGHT = 3;
    private int face = UP;
    private int index = 0;//行动序列，0 1 2 3
    private Sprite sprite = null;
    private LSprite spriteLayer = null;

    private MoveAction() {
    }

    public int getFace() {
        return face;
    }

    public static MoveAction createMoveUpAction(LSprite spriteLayer, Sprite sprite) {
        MoveAction ma = new MoveAction();
        ma.sprite = sprite;
        ma.spriteLayer = spriteLayer;
        ma.face = UP;
        return ma;
    }

    public static MoveAction createMoveDownAction(LSprite spriteLayer, Sprite sprite) {
        MoveAction ma = new MoveAction();
        ma.sprite = sprite;
        ma.spriteLayer = spriteLayer;
        ma.face = DOWN;
        return ma;
    }

    public static MoveAction createMoveLeftAction(LSprite spriteLayer, Sprite sprite) {
        MoveAction ma = new MoveAction();
        ma.sprite = sprite;
        ma.spriteLayer = spriteLayer;
        ma.face = LEFT;
        return ma;
    }

    public static MoveAction createMoveRightAction(LSprite spriteLayer, Sprite sprite) {
        MoveAction ma = new MoveAction();
        ma.sprite = sprite;
        ma.spriteLayer = spriteLayer;
        ma.face = RIGHT;
        return ma;
    }

    public void run() {
        switch (face) {
            case UP:
                spriteLayer.setLocation(spriteLayer.getX(), spriteLayer.getY() - sprite.curMap.cellHeight / 4);
                break;
            case DOWN:
                spriteLayer.setLocation(spriteLayer.getX(), spriteLayer.getY() + sprite.curMap.cellHeight / 4);
                break;
            case LEFT:
                spriteLayer.setLocation(spriteLayer.getX() - sprite.curMap.cellWidth / 4, spriteLayer.getY());
                break;
            case RIGHT:
                spriteLayer.setLocation(spriteLayer.getX() + sprite.curMap.cellWidth / 4, spriteLayer.getY());
                break;
        }
        sprite.setCurStepImage(face, (index + 1) % 4);
        index++;
        if (index >= 4) {
            freeze();
        }

    }
}
