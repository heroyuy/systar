/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game.impl.model;

import game.AbModel;
import game.data.DataStore;
import game.impl.state.MapState;
import game.impl.state.MenuState;

/**
 *
 * @author Administrator
 */
public class GameData extends AbModel {

    public DataStore dataStore = null;
    public Player player = null;
    public MenuState menuState = new MenuState();
    public MapState mapState = new MapState();
    public Map curMap = null;
    public ActionManager actionManager = null;

    public GameData() {
        actionManager = new ActionManager();
        dataStore = new DataStore();
        player = dataStore.getPlayer();
        System.out.println("player.curMapIndex:"+player.curMapIndex);
        curMap = dataStore.getMap(player.curMapIndex);
    }

    public void update() {
        player.update();
        actionManager.run();
    }
}
