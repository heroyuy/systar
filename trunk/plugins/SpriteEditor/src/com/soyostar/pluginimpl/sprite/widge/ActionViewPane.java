/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyostar.pluginimpl.sprite.widge;

import com.soyostar.pluginimpl.sprite.model.Sequence;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import javax.swing.JPanel;

/**
 *
 * @author Administrator
 */
public class ActionViewPane extends JPanel implements DropTargetListener, Runnable {

    private Sequence sequence;
    private boolean isCircle = true;//循环播放
    private boolean isPlay = false;//播放标志

    public ActionViewPane() {
        new Thread(this).start();
    }

    @Override
    public void paintComponent(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        g.setColor(Color.GREEN);
        g.drawLine(0, this.getHeight() >> 1, this.getWidth(), this.getHeight() >> 1);
        g.drawLine(this.getWidth() >> 1, 0, this.getWidth() >> 1, this.getHeight());
    }

    /**
     *
     */
    public boolean isIsCircle() {
        return isCircle;
    }

    public void setIsCircle(boolean isCircle) {
        this.isCircle = isCircle;
        this.repaint();
    }

    public boolean isIsPlay() {
        return isPlay;
    }

    public void setIsPlay(boolean isPlay) {
        this.isPlay = isPlay;
        this.repaint();
    }

    public void dragEnter(DropTargetDragEvent dtde) {
    }

    public void dragOver(DropTargetDragEvent dtde) {
    }

    public void dropActionChanged(DropTargetDragEvent dtde) {
    }

    public void dragExit(DropTargetEvent dte) {
    }

    public void drop(DropTargetDropEvent dtde) {
    }

    public void run() {
    }
}
