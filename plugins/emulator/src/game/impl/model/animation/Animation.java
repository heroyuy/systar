
package game.impl.model.animation;

import game.AbModel;
import java.util.ArrayList;

/**
 *
 * @author Administrator
 */
public class Animation extends AbModel {

    public ArrayList<TileSet> tileSets = new ArrayList<TileSet>();
    public ArrayList<Sequence> sequences = new ArrayList<Sequence>();

    @Override
    public void update() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
