/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game.impl.view.startpage;

import emulator.EmulatorGraphics;
import engine.GameEngine;
import game.View;
import java.awt.Color;

/**
 *
 * @author Administrator
 */
public class StartPageView implements View {

    private GameEngine ge=GameEngine.getInstance();
    public void init() {
    }

    public void paint(EmulatorGraphics eg) {
        eg.setColor(Color.black);
        eg.fillRect(0, 0, ge.getScreenWidth(), ge.getScreenHeight());
        eg.setColor(Color.white);
        eg.drawString("欢迎界面", ge.getScreenWidth()/2, ge.getScreenHeight()/2, EmulatorGraphics.HV);
    }

    public void release() {
    }
}
