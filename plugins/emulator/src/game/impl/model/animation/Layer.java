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
public class Layer extends AbModel  {

    public byte type;
    public int x;
    public int y;
    public int width;
    public int height;
    public ArrayList<Module> modules = new ArrayList<Module>();

    @Override
    public void update() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
