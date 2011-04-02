package com.soyostar.emulator.engine;

import com.soyostar.emulator.ScreenListener;
import com.soyostar.ui.Container;
import com.soyostar.ui.Motion;
import com.soyostar.ui.MotionListener;
import com.soyostar.ui.Painter;
import java.awt.Color;

/**
 *
 * 视图渲染层
 */
public class RenderLayer extends Container implements MotionListener ,ScreenListener{

    private View curView = null;
    private GameEngine ge = null;
    private Game game = null;

    protected RenderLayer(GameEngine ge) {
        super();
        setMotionListener(this);
        this.ge = ge;
    }

    int test=0;
    @Override
    public void paint(Painter painter) {
        System.out.println("paint");
        painter.setColor(Color.yellow);
        painter.fillRect(0, 0, getWidth(), getHeight());
        painter.setColor(Color.blue);
        painter.setFontSize(test);
        painter.drawString(++test+"",10,10,Painter.LT);
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
        System.out.println("keyPressed:"+key);
    }

    @Override
    public void keyReleased(int key) {
        System.out.println("keyReleased:"+key);
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

    public void sizeChanged(int width, int height) {
        this.setWidth(width);
        this.setHeight(height);
    }
}
