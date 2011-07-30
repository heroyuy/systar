package game.actions;

import com.soyostar.app.action.Action;
import game.impl.model.Character;

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
    private Character sprite = null;

    private MoveAction() {
    }

    public int getFace() {
        return face;
    }

    public static MoveAction createMoveUpAction(Character sprite) {
        MoveAction ma = new MoveAction();
        ma.sprite = sprite;
        ma.face = UP;
        return ma;
    }

    public static MoveAction createMoveDownAction(Character sprite) {
        MoveAction ma = new MoveAction();
        ma.sprite = sprite;
        ma.face = DOWN;
        return ma;
    }

    public static MoveAction createMoveLeftAction(Character sprite) {
        MoveAction ma = new MoveAction();
        ma.sprite = sprite;
        ma.face = LEFT;
        return ma;
    }

    public static MoveAction createMoveRightAction(Character sprite) {
        MoveAction ma = new MoveAction();
        ma.sprite = sprite;
        ma.face = RIGHT;
        return ma;
    }

    public void run() {
        switch (face) {
            case UP:
                sprite.y -= sprite.curMap.cellHeight / 4;
                break;
            case DOWN:
                sprite.y += sprite.curMap.cellHeight / 4;
                break;
            case LEFT:
                sprite.x -= sprite.curMap.cellWidth / 4;
                break;
            case RIGHT:
                sprite.x += sprite.curMap.cellWidth / 4;
                break;
        }
        sprite.setCurStepImage(face, (index + 1) % 4);
        index++;
        if (index >= 4) {
            freeze();
        }

    }
}
