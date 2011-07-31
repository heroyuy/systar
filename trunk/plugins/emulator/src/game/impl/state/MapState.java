package game.impl.state;

import com.soyostar.util.astar.AStar;
import game.impl.model.Map;
import java.util.List;
import game.impl.model.Character;
import game.impl.model.Npc;

/**
 *
 * @author wp_g4
 */
public class MapState {

    public static final int STATE_MAP = 0;//地图
    public static final int STATE_MENU = 1;//菜单
    public static final int STATE_DIALOG = 2;//对话
    public Map curMap = null;
    public AStar aStar = new AStar();
    public List<Character> sprites = null;
    public Npc tarfetNpc = null;
    public int sceneState = STATE_MAP;
}
