package game.impl.model;

import engine.GameEngine;
import game.actions.MoveAction;
import java.util.Random;

/**
 *
 * @author Administrator
 */
public class Npc extends Character {

    public static final int TYPE_AUTO = 0;
    public static final int TYPE_NEAR = 1;
    public static final int TYPE_TOUCH = 2;
    //游戏数据
    public String name = "";
    public NpcState[] npcStates = null;
    public int curNpcStateIndex = -1;
    public NpcState curNpcState = null;
    //功能性变量
    private Random random = new Random();
    private int time = random.nextInt(10) + 10;

    @Override
    public void update() {
        super.update();
        //验证NPC状态
        validateCurState();
        //行走动作
        if (ge.getTicker() % time == 0) {
            int faceParam = random.nextInt(4);
            MoveAction me = null;
            me = new MoveAction(this, faceParam);
            me.activate();
            this.addMoveAction(me);
        }
    }

    public void setCurNpcState(int index) {
        this.curNpcStateIndex = index;
        curNpcState = npcStates[curNpcStateIndex];
        this.face = curNpcState.face;
        setCharImg(curNpcState.charImage);
    }

    private void validateCurState() {
        //判断当前NPC所处的状态
        //测试状态下始终使用第一个状态
        int stateIndex = 0;
        if (stateIndex != curNpcStateIndex) {
            setCurNpcState(stateIndex);
        }
    }

    public void init() {
        validateCurState();
        x = col * curMap.cellWidth + (curMap.cellWidth - width) / 2;
        y = (row + 1) * curMap.cellHeight - height;
        setCurStepImage(face, 0);
    }

    public static class NpcState {

        public int stateType = 0;
        public String charImage = null;
        public byte face = 0;
        public byte move = 0;
        public byte speed = 0;
        public boolean transparent = false;
        public int scriptIndex = -1;
    }
}
