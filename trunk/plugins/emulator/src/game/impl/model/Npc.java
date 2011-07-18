package game.impl.model;

import com.soyostar.app.Image;
import game.AbModel;

/**
 *
 * @author Administrator
 */
public class Npc extends AbModel {

    public String name = "";
    public int mapIndex = -1;
    public int stateNum = 0;
    public NpcState[] npcStates = null;
    public int row;
    public int col;
    public NpcState curNpcState = null;

    @Override
    public void update() {
        
    }

    public static class NpcState extends Sprite {

        public int stateType = 0;
        public Image charImage = null;
        public byte move = 0;
        public int speed = 0;
        public boolean transparent = false;
        public int scriptIndex = -1;

        @Override
        public void update() {
        }
    }
}
