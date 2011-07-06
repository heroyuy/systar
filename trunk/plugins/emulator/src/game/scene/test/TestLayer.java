/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package game.scene.test;

import com.soyostar.app.Color;
import com.soyostar.app.Layer;
import com.soyostar.app.Painter;
import engine.GameEngine;

/**
 *
 * @author Administrator
 */
public class TestLayer extends Layer {
 private GameEngine ge = GameEngine.getInstance();
    @Override
    public void paintSelf(Painter p){
        super.paintSelf(p);
        p.setColor(Color.RED);
        p.drawString("fps:"+ge.getFps(), 10, 10, Painter.LT);
    }

}
