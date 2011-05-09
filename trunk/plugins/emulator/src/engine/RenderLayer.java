package engine;

//import model.*;
import emulator.ui.Canvas;
import emulator.ui.EmulatorGraphics;
import emulator.ui.EmulatorImage;
import emulator.MotionEvent;

/**
 *
 * 视图渲染层
 */
public class RenderLayer extends Canvas {

    private GameEngine ge = null;
    private EmulatorImage buffImg;//双缓冲
    private EmulatorGraphics buffEG;

    public EmulatorGraphics getEmulatorGraphics() {
        return buffEG;
    }

    protected RenderLayer(GameEngine ge) {
        super();
        this.ge = ge;
        buffImg = EmulatorImage.createImage(getWidth(), getHeight());
        buffEG = new EmulatorGraphics(buffImg.getGraphics());

    }

    @Override
    public void paint(EmulatorGraphics g) {
        g.drawImage(buffImg, 0, 0, 0);
    }

    @Override
    public void keyPressed(int key) {
        ge.setKey(key);
    }

    @Override
    public void keyReleased(int key) {
        ge.clearKey();
    }

    @Override
    public void onTouchEvent(MotionEvent me) {
        ge.setMotionEvent(me);
    }

    @Override
    public void sizeChanged(int width, int height) {
        buffImg = EmulatorImage.createImage(width, height);
        buffEG = new EmulatorGraphics(buffImg.getGraphics());

    }
}
