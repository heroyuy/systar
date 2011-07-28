/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package game.scene.about;

import com.soyostar.app.Color;
import com.soyostar.app.LButton;
import com.soyostar.app.LLabel;
import com.soyostar.app.LTextArea;
import com.soyostar.app.Layer;
import com.soyostar.app.event.ActionListener;
import com.soyostar.app.event.TouchEvent;
import com.soyostar.app.event.TouchListener;
import engine.Render;
import game.AbController;
import game.util.Skin;

/**
 *
 * @author Administrator
 */
public class AboutController extends AbController implements TouchListener, ActionListener{

    private Layer bg = null;
    private Layer menu = null;
    private Layer menu2 = null;
    private LButton lbutton = null;
    private Skin skin = null;
    private LLabel label = null;
    private LTextArea lta = null;

    private int lbW = 120, lbH = 30;

    /**
     * HelpController的构造函数
     * @param render 
     */
    public AboutController(Render render){
        super(render);
        bg = new Layer();
        skin = new Skin("res/image/skin/001-Blue01.png");
        bg.setBackgroundImage(skin.createBlueBg(ge.getScreenWidth(), ge.getScreenHeight(), false));
        bg.setSize(ge.getScreenWidth(), ge.getScreenHeight());
        bg.setVisible(true);

        String str = "这个只是关于游戏的测试版本，其中的内容是没有与实际的开发联系起来的，我们的团队会在"
                + "以后的开发中完善这个文件中的内容。敬请大家看看我们的成果。";
        lta = new LTextArea(str);
        lta.setSize(ge.getScreenWidth(), ge.getScreenHeight());
        lta.setMargin(10, 10, 15, 15);
        lta.setLeading(5);
        lta.setBackgroundImage(skin.createBlueBg(ge.getScreenWidth(), ge.getScreenHeight(), false));
        lta.setTextColor(Color.WHITE);
        lta.setTextSize(16);
        lta.setLocation(0, 0);
        lta.setVisible(true);

        lbutton = new LButton();
        lbutton.setText("返回主菜单");
        lbutton.setVisible(true);
        lbutton.setSize(lbW, lbH);
        lbutton.setLocation(0, ge.getScreenHeight()-lbH);
        lbutton.setAfocalImage(skin.createAlphaBg(lbW, lbH, false));
        lbutton.setFocusImage(skin.createAlphaBg(lbW, lbH, true));

    }

    public void onObtain() {
        
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
