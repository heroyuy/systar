package game.impl.model;

/**
 *
 * @author Administrator
 */
public class Npc extends Sprite {

    public String name = "";
    public int stateNum = 0;
    public NpcState[] npcStates = null;
    public NpcState curNpcState = null;

    @Override
    public void update() {
        super.update();
    }

    public void setCurNpcState(int index) {
        curNpcState = npcStates[0];
        setCharImg(curNpcState.charImage);
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
