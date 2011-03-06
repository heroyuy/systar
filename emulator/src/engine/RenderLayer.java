package engine;

//import model.*;
import emulator.Canvas;
import emulator.EmulatorGraphics;
import emulator.EmulatorImage;
import java.awt.Graphics;

/**
 *
 * 视图渲染层
 */
public class RenderLayer extends Canvas {

    private GameEngine ge = null;
    private View curView = null;
    private Game game = null;
    private EmulatorImage buffImg;//双缓冲
    private EmulatorGraphics buffEG;

    protected RenderLayer(GameEngine ge) {
        super();
        this.ge = ge;
        this.setSize(240, 320);
        buffImg = EmulatorImage.createImage(getWidth(), getHeight());
        buffEG = new EmulatorGraphics(buffImg.getGraphics());

    }

    @Override
    public void paint(Graphics g) {
//        System.out.println("renderLayer_paint");
        game = ge.getGame();
        if (game != null) {
            curView = game.getCurView();
            if (curView != null) {
                curView.paint(buffEG);
            }
        }
        g.drawImage(buffImg, 0, 0, null);
    }

    @Override
    public void keyPressed(int key) {
        ge.setKey(key);
    }

    @Override
    public void keyReleased(int key) {
        ge.clearKey();
    }
}
