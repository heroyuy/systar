/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package game.scene.test;

import com.soyostar.app.Button;
import com.soyostar.app.Color;
import com.soyostar.app.LButton;
import com.soyostar.app.Layer;
import engine.GameEngine;
import engine.Render;
import game.AbController;

/**
 *
 * @author Administrator
 */
public class TestController extends AbController{
   private GameEngine ge = GameEngine.getInstance();
    private Layer bg=null;
    
    private Layer menu=null;
    
    private LButton lb=null;
    
    private Button btn=null;

    public TestController(Render render){
        super(render);
        bg=new Layer();
        bg.setBackground(Color.DKGRAY);
        bg.setSize(ge.getScreenWidth(), ge.getScreenHeight());
        bg.setVisible(true);
    }

    public void onObtain() {
       addWidget(bg);
    }

    public void onLose() {
    }

    public void updateModel() {
    }

}
