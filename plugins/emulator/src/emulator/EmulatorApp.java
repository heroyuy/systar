package emulator;

import emulator.ui.Canvas;

/**
 *
 * @author Administrator
 */
public abstract class EmulatorApp {

    private Canvas canvas = null;

    public abstract void start();

    public abstract void stop();

    public abstract void pause();

    public abstract void resume();

    public Canvas getCanvas() {
        return canvas;
    }

    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
    }
}
