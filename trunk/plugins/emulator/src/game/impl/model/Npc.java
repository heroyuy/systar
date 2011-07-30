package game.impl.model;

/**
 *
 * @author Administrator
 */
public class Npc extends Character {

    public String name = "";
    public NpcState[] npcStates = null;
    public int curNpcStateIndex = -1;

    @Override
    public void update() {
        validateCurState();
        super.update();

    }

    public void setCurNpcState(int index) {
        this.curNpcStateIndex = index;
        this.face = npcStates[index].face;
        setCharImg(npcStates[index].charImage);
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
        validateCurState() ;
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
