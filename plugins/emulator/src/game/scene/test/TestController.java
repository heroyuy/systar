package game.scene.test;

import com.soyostar.app.Color;
import com.soyostar.app.LLabel;
import com.soyostar.app.LTab;
import com.soyostar.app.Layer;
import com.soyostar.app.Widget;
import game.AbController;
import game.util.Skin;

/**
 *
 * @author Administrator
 */
public class TestController extends AbController {

    private Layer bg = null;
//    private Layer menu = null;
//    private Layer menu2 = null;
//    private LButton lb = null;
//    private Button btn = null;
//    private LLabel label = null;
//    private LTextArea lta = null;
//    private LProgressBar lpb = null;
//    private LTextDialog ltd = null;
    private LTab lTab = null;

    public TestController() {
        Skin skin = new Skin("res/image/skin/001-Blue01.png");
        bg = new Layer();
//        bg.setBackground(Color.GREEN);
        bg.setBackgroundImage(skin.createBlueBg(ge.getScreenWidth(), ge.getScreenHeight(), false));
        bg.setSize(ge.getScreenWidth(), ge.getScreenHeight());
        bg.setLocation(0, 0);
        bg.setVisible(true);

        lTab = new LTab();
        lTab.setSize(300, 400);
        lTab.setVisible(true);
        lTab.setLocation(20, 20);
        lTab.setTitleBackground(skin.createAlphaBg(lTab.getTitleWidth(), lTab.getTitleHeight(), false), skin.createBlueBg(lTab.getTitleWidth(), lTab.getTitleHeight(), false), skin.createAlphaBg(lTab.getTitleWidth(), lTab.getTitleHeight(), false));
        Layer tab1 = new Layer();
        tab1.setSize(300, 350);
        tab1.setBackgroundImage(skin.createBlueBg(300, 350, true));
        tab1.setVisible(true);
        LLabel lab1 = new LLabel();
        lab1.setSize(100, 20);
        lab1.setLocation(10, 10);
        lab1.setVisible(true);
        lab1.setText("这是好啊的内容");
        tab1.addWidget(lab1);
        lTab.addTab("好啊", tab1);
        Layer tab2 = new Layer();
        tab2.setSize(300, 350);
        tab2.setBackgroundImage(skin.createBlueBg(300, 350, true));
        tab2.setVisible(true);
        LLabel lab2 = new LLabel();
        lab2.setSize(100, 20);
        lab2.setLocation(10, 10);
        lab2.setVisible(true);
        lab2.setText("这是不好啊的内容");
        tab2.addWidget(lab2);
        lTab.addTab("不好啊", tab2);
        bg.addWidget(lTab);
//        Action action = new ShadeAction(bg, Color.BLACK, Color.RED, 20, 1000);
//        action.activate();
//        gd.actionManager.addAction(action);

//        menu = new Layer();
//        menu.setBackground(Color.BLUE);
//        menu.setSize(80, 40);
//        menu.setLocation(20, 10);
//        menu.setVisible(true);
//
//        menu2 = new Layer();
//        menu2.setBackground(Color.BLUE);
//        menu2.setSize(80, 40);
//        menu2.setLocation(20, 80);
//        menu2.setVisible(true);
//
//        lb = new LButton(Image.createImage("res/image/battler/001-Fighter01.png"), Image.createImage("res/image/battler/002-Fighter02.png"));
//        lb.setText("LButton");
//        lb.setBackground(Color.RED);
//        lb.setLocation(-10, 20);
//        lb.setSize(60, 30);
//        lb.setVisible(true);
//
//        btn = new Button("Button");
//        btn.setSize(80, 30);
//        btn.setLocation(100, 50);
//
//        label = new LLabel() {
//
//            @Override
//            public void paint(Painter painter) {
//                this.setText("fps:" + ge.getFps() + " ticker:" + ge.getTicker());
//                super.paint(painter);
//            }
//        };
//        label.setBackground(Color.GREEN);
//        label.setText("LLabel");
//        label.setLocation(100, 200);
//        label.setSize(80, 30);
//        label.setTextColor(Color.BLUE);
//        label.setTextAnchor(Painter.HV);
//        label.setVisible(true);
//
//        lpb = new LProgressBar();
//        lpb.setBackground(Color.GRAY);
//        lpb.setForeground(Color.GREEN);
//        lpb.setMaxValue(100);
//        lpb.setValue(23);
//        lpb.setVisible(true);
//        lpb.setSize(150, 20);
//        lpb.setDrawable(true);
//        lpb.setLocation(10, 150);
//
//        lta = new LTextArea("支持图形调试的图形子类。重写 Graphics 中的大多数方法。"
//                + "DebugGraphics 对象很少通过手工创建。它们通常在 JComponent 的 "
//                + "debugGraphicsOptions 因使用 setDebugGraphicsOptions() "
//                + "方法而发生更改时自动创建。");
//        lta.setSize(120, 80);
//        lta.setLocation(10, 200);
//        lta.setMargin(5, 5, 10, 10);
//        lta.setBackground(Color.RED);
//        lta.setTextColor(Color.WHITE);
//        lta.setLeading(5);
//        lta.setVisible(true);
//        String text = "支持图形\n调试的<c>[0xaabbccdd]图形子\r类。重写<c>[0xffff0000]Graphics 中的大多数方法。"
//                + "DebugGraphics对象很少通<c>[0xff00ff00]过手工创建。它们通常在JComponent的"
//                + "debugGraphicsOptions 因<c>[0xff0000ff]使用 setDebugGraphicsOptions()"
//                + "方法而发生更改时自\n动创建。";
//        ltd = new LTextDialog();
//        ltd.setText(text);
//        ltd.setSize(200, 100);
//        ltd.setLocation(20, 40);
//        ltd.setBackground(Color.GRAY);
//        ltd.setTextColor(Color.MAGENTA);
//        ltd.setVisible(true);

    }

    public void onObtain() {
        addWidget(bg);
//        bg.addWidget(menu);
//        bg.addWidget(menu2);
//        menu.addWidget(lb);
//        bg.addWidget(label);
//        addComponent(btn);
//        bg.addWidget(lta);
//        bg.addWidget(lpb);
//        bg.addWidget(ltd);
    }

    public void onLose() {
    }

    public void updateModel() {
    }
}
