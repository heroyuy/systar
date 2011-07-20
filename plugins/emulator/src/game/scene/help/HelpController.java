/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package game.scene.help;

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
public class HelpController extends AbController implements TouchListener, ActionListener{

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
    public HelpController(Render render){
        super(render);
        bg = new Layer();
        bg.setBackground(Color.GRAY);
        bg.setSize(ge.getScreenWidth(), ge.getScreenHeight());
        bg.setLocation(0, 0);
        bg.setVisible(true);

    }

    public void onObtain() {
        String str = "很早前我就说过，我很懒的，自己一般不会去敲代码的（目前自己没电脑），又因为最近比较忙，"
                + "所以就一般模仿了。完成代码：ctrl+\\ //任何地方按下此组合键，"
                + "均会提示相应的参考字段；2、错误提示：alt + enter //顾名思义，当系统报错时，"
                + "按下此组合可以查看系统提示；3、自动完成字符串： ctrl+L ctrl+k //后者（Ctrl+L没用过）"
                + "组合键自动打出字符串，每按一次打出一个新串，串序自下向上；4、右键：修复自动导入"
                + "5、右键：格式化代码风格6、导入所需包：ctrl+shift+i 7、格式化代码：alt+shift+F";
        lta = new LTextArea(str);
        lta.setSize(ge.getScreenWidth(), ge.getScreenHeight());
        lta.setMargin(10,10, 15, 15);
        lta.setLeading(5);
        lta.setBackground(Color.GRAY);
        lta.setTextColor(Color.WHITE);
        lta.setTextSize(20);
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
