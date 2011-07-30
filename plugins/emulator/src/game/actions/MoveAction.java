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

    public MoveAction(Character sprite, int face) {
        if (face < UP || face > RIGHT) {
            throw new IllegalArgumentException("方向参数不对，只能为0 1 2 3");
        }
        this.sprite = sprite;
        this.face = face;
    }

    public int getFace() {
        return face;
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
        sprite.moving = true;
        if (index >= 4) {
            sprite.moving = false;
            freeze();
        }

    }
}
