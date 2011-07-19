/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package game.scene.about;

import com.soyostar.app.Button;
import com.soyostar.app.Color;
import com.soyostar.app.LButton;
import com.soyostar.app.LLabel;
import com.soyostar.app.LTextArea;
import com.soyostar.app.Layer;
import com.soyostar.app.event.ActionListener;
import com.soyostar.app.event.TouchEvent;
import com.soyostar.app.event.TouchListener;
import engine.GameEngine;
import engine.Render;
import game.AbController;
import game.RpgGame;
import game.impl.model.GameData;

/**
 *
 * @author Administrator
 */
public class AboutController extends AbController implements TouchListener, ActionListener{

    private GameEngine ge = GameEngine.getInstance();
    private RpgGame rpgGame = (RpgGame) ge.getGame();
    private GameData gd = (GameData) rpgGame.getModel("game.impl.model.GameData");
    private Layer bg = null;
    private Layer menu = null;
    private Layer menu2 = null;
    private LButton lbutton = null;
    private Button btn = null;
    private LLabel label = null;
    private LTextArea lta = null;

    /**
     * HelpController的构造函数
     * @param render 
     */
    public AboutController(Render render){
        super(render);
        bg = new Layer();
        bg.setBackground(Color.GRAY);
        bg.setSize(ge.getScreenWidth(), ge.getScreenHeight());
        bg.setLocation(0, 0);
        bg.setVisible(true);

    }

    public void onObtain() {
        String str = "这个只是关于游戏的测试版本，其中的内容是没有与实际的开发联系起来的，我们的团队会在"
                + "以后的开发中完善这个文件中的内容。敬请大家看看我们的成果。";
        lta = new LTextArea(str);
        lta.setSize(ge.getScreenWidth(), ge.getScreenHeight());
        lta.setMargin(10, 10, 15, 15);
        lta.setLeading(5);
        lta.setBackground(Color.GRAY);
        lta.setTextColor(Color.WHITE);
        lta.setLocation(0, 0);
        lta.setVisible(true);

        lbutton = new LButton();
        lbutton.setText("返回主菜单");
        lbutton.setVisible(true);
        lbutton.setSize(60, 20);
        lbutton.setLocation(ge.getScreenWidth()-60, ge.getScreenHeight()-20);
        lbutton.setBackground(Color.BLUE);
        lbutton.setActionListener(this);
        addWidget(bg);
        addWidget(lta);
        addWidget(lbutton);
    }

    public void onLose() {
   
    }

    public void updateModel() {
       
    }

    public boolean onTouchEvent(Object t, TouchEvent te) {
        System.out.println("按键");
        rpgGame.setCurrentControl("game.scene.menu.MenuController");
        return true;
    }

    public void actionPerformed(Object t) {
        if(t.equals(lbutton)){
            rpgGame.setCurrentControl("game.scene.menu.MenuController");
        }
    }
   
}
