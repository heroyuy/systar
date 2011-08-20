package game.scene.menu;

import game.AbController;
import game.Const;
import game.util.Skin;

import com.soyostar.app.Color;
import com.soyostar.app.Image;
import com.soyostar.app.LButton;
import com.soyostar.app.Layer;
import com.soyostar.app.event.ActionListener;

/**
 * 
 * @author Administrator
 */
public class MenuController extends AbController {

	private Layer bg = null;
	private Skin skin = null;
	private LButton[] lbs = null;
	private int lbW = 120, lbH = 30, lbGap = 10;

	public MenuController() {
		skin = new Skin("res/image/skin/001-Blue01.png");
		bg = new Layer();
		// Image img = skin.createBlueBg(ge.getScreenWidth(),
		// ge.getScreenHeight(), false);
		Image img = Image.createImage("res/image/picture/DSC00033.png");
		img.changeBrightness(20.0f, 1.0f, 1.0f);
		// img.grayed();
		bg.setBackgroundImage(img);
		bg.setSize(ge.getScreenWidth(), ge.getScreenHeight());
		bg.setVisible(true);

		lbs = new LButton[Const.Text.MENU.length];
		for (int i = 0; i < lbs.length; i++) {
			lbs[i] = new LButton(Const.Text.MENU[i]);
			lbs[i].setSize(lbW, lbH);
			lbs[i].setLocation((ge.getScreenWidth() - lbW) / 2,
					(ge.getScreenHeight() - lbs.length * lbH - (lbs.length - 1)
							* lbGap)
							/ 2 + i * (lbH + lbGap));
			lbs[i].setTextColor(Color.WHITE);
			lbs[i].setAfocalImage(skin.createAlphaBg(lbW, lbH, false));
			lbs[i].setFocusImage(skin.createAlphaBg(lbW, lbH, true));
			lbs[i].setVisible(true);
			lbs[i].setActionListener(new ActionListener() {

				public void actionPerformed(Object t) {

					for (int i = 0; i < lbs.length; i++) {
						if (t.equals(lbs[i])) {
							switch (i) {
							case 0:
								// 开始游戏
								rpgGame.setCurrentControl("game.scene.map.MapController");
								break;
							case 1:
								break;
							case 2:
								// 游戏设置
								rpgGame.setCurrentControl("game.scene.setting.SettingController");
								break;
							case 3:
								// 游戏帮助
								rpgGame.setCurrentControl("game.scene.help.HelpController");
								break;
							case 4:
								// 关于帮助
								rpgGame.setCurrentControl("game.scene.about.AboutController");
								break;
							case 5:
								break;

							}
						}
					}
				}
			});
			bg.addWidget(lbs[i]);
		}
	}

	@Override
	public void onObtain() {
		addWidget(bg);

	}

	public void updateModel() {
	}

	public void onLose() {
	}
}
