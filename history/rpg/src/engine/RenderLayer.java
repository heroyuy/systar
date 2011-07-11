package engine;

import javax.microedition.lcdui.*;
//import model.*;

/**
 *
 * 视图渲染层
 */
public class RenderLayer extends Canvas {

    private GameEngine ge = null;
    private View curView = null;
    private Game game = null;
    private Image buffImg;//双缓冲
    private Graphics buffG;

    protected RenderLayer(GameEngine ge) {
        super();
        setFullScreenMode(true);// 全屏模式
        this.ge = ge;
        buffImg = Image.createImage(getWidth(), getHeight());
        buffG = buffImg.getGraphics();

    }

    protected void paint(Graphics g) {

        game = ge.getGame();
        if (game != null) {
            curView = game.getCurView();
            if (curView != null) {
                curView.paint(buffG);
            }
        }
        g.drawImage(buffImg, 0, 0, 0);
    }
//    protected void logic(){
//        game = ge.getGame();
//		if (game != null) {
//			curView = game.getCurView();
//			if (curView != null) {
//				curView.logic();
//			}
//		}
//    }

    protected void keyPressed(int key) {
        ge.setKey(key);
    }

    protected void keyReleased(int key) {
        ge.clearKey();
    }
}
