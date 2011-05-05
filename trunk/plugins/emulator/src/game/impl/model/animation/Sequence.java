/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game.impl.model.animation;

import game.AbModel;
import java.util.ArrayList;

/**
 *
 * @author Administrator
 */
public class Sequence extends AbModel  {

    public ArrayList<Layer> layers = new ArrayList<Layer>();
    public int delay;

    @Override
    public void update() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
