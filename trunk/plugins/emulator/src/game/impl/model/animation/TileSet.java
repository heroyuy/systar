
package game.impl.model.animation;

import game.AbModel;
import java.util.ArrayList;

/**
 *
 * @author Administrator
 */
public class TileSet extends AbModel{

    public int id = -1;
    public String path = "";
    public ArrayList<Tile> tiles = new ArrayList<Tile>();

    @Override
    public void update() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
