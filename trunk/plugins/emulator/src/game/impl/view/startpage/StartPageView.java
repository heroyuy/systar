/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game.impl.view.startpage;

import emulator.ui.EmulatorGraphics;
import emulator.ui.EmulatorImage;
import engine.GameEngine;
import game.RpgGame;
import game.View;
import game.impl.model.GameData;
import java.awt.Color;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class StartPageView implements View {

    private GameEngine ge = GameEngine.getInstance();
    private RpgGame rpgGame = (RpgGame) ge.getGame();
    private GameData gd=null;
    private EmulatorImage player = null;

    public void init() {
        gd=(GameData) rpgGame.getModel(0);
        try {
            player = EmulatorImage.createImage("res/image/battler/"+gd.player.headImg);
        } catch (IOException ex) {
            Logger.getLogger(StartPageView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void paint(EmulatorGraphics eg) {
        eg.setColor(Color.black);
        eg.fillRect(0, 0, ge.getScreenWidth(), ge.getScreenHeight());
        eg.setColor(Color.white);
        eg.drawString("欢迎界面", ge.getScreenWidth() / 2, ge.getScreenHeight() / 2, EmulatorGraphics.HV);
        eg.drawImage(player, 0, 0, 0);
    }

    public void release() {
    }
}
