/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game.impl.model;

import game.AbModel;
import game.data.DataStore;

/**
 *
 * @author Administrator
 */
public class GameData extends AbModel {

    private DataStore dataStore = null;
    private Player player = null;

    public GameData() {
        dataStore = new DataStore();
        player=dataStore.getPlayer();
    }

    public void update() {
        player.update();
    }
}
