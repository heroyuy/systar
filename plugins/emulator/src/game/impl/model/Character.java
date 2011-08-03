package game.impl.model;

import com.soyostar.app.Image;
import game.AbModel;
import game.actions.MoveAction;
import game.data.DataStore;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 游戏中可移动元素的父类，如Player、NPC等
 * @author wp_g4
 */
public abstract class Character extends AbModel {

    public Map curMap;// 角色当前所在地图
    public byte face = 0;
    public int row = 0;
    public int col = 0;
    public Image headImage = null;
    private Image[][] sequence = null;
    private Image curStepImage = null;
    private Queue<MoveAction> moveActionQueue = new LinkedList<MoveAction>();
    private MoveAction curMoveAction = null;
    public int x, y, width, height;
    public boolean moving = false;//移动标识

    public void setCharImg(String charImgPath) {
        Image characterImage = Image.createImage(charImgPath);
        sequence = new Image[4][4];
        width = characterImage.getWidth() / 4;
        height = characterImage.getHeight() / 4;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                sequence[i][j] = Image.copyImage(characterImage, j * width, i * height, width, height);
            }
        }
    }

    public Image getCurStepImage() {
        return curStepImage;
    }

    public void setCurStepImage(int face, int index) {
        curStepImage = sequence[(face + 3) % 4][index];
    }

    public void update() {
        move();
    }

    public void move() {
        //角色移动
        if (curMoveAction == null || !curMoveAction.isActive()) {
            if (!moveActionQueue.isEmpty()) {
                curMoveAction = moveActionQueue.poll();
                switch (curMoveAction.getFace()) {
                    case MoveAction.UP: {
                        if (curMap.isAccessible(row - 1, col)) {
                            //即将移动到的点可到达
                            row--;
                        } else {
                            curMoveAction = null;
                            moveActionQueue.clear();
                            return;
                        }
                    }
                    break;
                    case MoveAction.DOWN: {
                        if (curMap.isAccessible(row + 1, col)) {
                            //即将移动到的点可到达
                            row++;
                        } else {
                            curMoveAction = null;
                            moveActionQueue.clear();
                            return;
                        }
                    }
                    break;
                    case MoveAction.LEFT: {
                        if (curMap.isAccessible(row, col - 1)) {
                            //即将移动到的点可到达
                            col--;
                        } else {
                            curMoveAction = null;
                            moveActionQueue.clear();
                            return;
                        }
                    }
                    break;
                    case MoveAction.RIGHT: {
                        if (curMap.isAccessible(row, col + 1)) {
                            //即将移动到的点可到达
                            col++;
                        } else {
                            curMoveAction = null;
                            moveActionQueue.clear();
                            return;
                        }
                    }
                    break;
                }
            }
        }

        if (curMoveAction != null && curMoveAction.isActive()) {
            curMoveAction.run();
        }
    }

    public void setMoveAction(List<MoveAction> moveActions) {
        moveActionQueue.clear();
        moveActionQueue.addAll(moveActions);
    }

    public void addMoveAction(MoveAction moveAction) {
        moveActionQueue.add(moveAction);
    }

    public void removeAllMoveActions() {
        moveActionQueue.clear();
    }

    public void gotoMap(int mapIndex, int row, int col, byte face) {
        this.row = row;
        this.col = col;
        this.face = face;
        curMap = DataStore.getInstance().getMap(mapIndex);
        x = col * curMap.cellWidth + (curMap.cellWidth - width) / 2;
        y = (row + 1) * curMap.cellHeight - height;
        setCurStepImage(face, 0);
    }
}
