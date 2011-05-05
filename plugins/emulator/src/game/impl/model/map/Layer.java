/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game.impl.model.map;

import game.AbModel;



/**
 *
 * @author Administrator
 */
public class Layer  extends AbModel{

    public static final byte TILELAYER = 0;
    public static final byte COLLIDELAYER = 1;
    public static final byte SPRITELAYER = 2;
    public byte type = 0;
    public int deepth = 0;//深度

    @Override
    public void update() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
