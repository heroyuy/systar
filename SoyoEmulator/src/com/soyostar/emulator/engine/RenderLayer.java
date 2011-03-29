package com.soyostar.emulator.engine;

import com.soyostar.ui.Container;
import com.soyostar.ui.Motion;
import com.soyostar.ui.MotionListener;
import com.soyostar.ui.Painter;
import java.awt.Color;

/**
 *
 * 视图渲染层
 */
public class RenderLayer extends Container implements MotionListener {

    private View curView = null;
    private GameEngine ge = null;
    private Game game = null;

    protected RenderLayer(GameEngine ge) {
        super();
        setMotionListener(this);
        this.ge = ge;
    }

    @Override
    public void paint(Painter painter) {
        painter.setColor(Color.yellow);
        painter.fillRect(0, 0, getWidth(), getHeight());
        game = ge.getGame();
        if (game != null) {
            curView = game.getCurView();
            if (curView != null) {
                curView.paint(painter);
            }
        }
    }

    @Override
    public void keyPressed(int key) {
    }

    @Override
    public void keyReleased(int key) {
    }

    public void onMotionDown(Motion m) {
        System.out.println("onMotionDown");
    }

    public void onMotionMove(Motion m) {
         System.out.println("onMotionMove");
    }

    public void onMotionUp(Motion m) {
         System.out.println("onMotionUp");
    }
}
