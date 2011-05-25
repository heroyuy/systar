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

    public GameData() {
        dataStore = new DataStore();
        player = dataStore.getPlayer();
    }

    public void update() {
        player.update();
    }
}
