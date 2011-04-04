/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyostar.pluginimpl.sprite.widge;

import com.soyostar.pluginimpl.sprite.data.Proxy;
import com.soyostar.pluginimpl.sprite.model.Action;
import com.soyostar.pluginimpl.sprite.model.Frame;
import com.soyostar.pluginimpl.sprite.model.Sequence;
import com.soyostar.pluginimpl.sprite.util.ActionFrameSelection;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.datatransfer.Transferable;
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

    private Action action;
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
        if (isPlay) {
        } else {
        }
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
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
        if (action != null) {
            try {
                Transferable transferable = dtde.getTransferable();
                Object o = transferable.getTransferData(ActionFrameSelection.FRAME_FLAVOR);
                if (o != null) {
                    if (o instanceof Frame) {
                        Frame m = (Frame) o;
                        Sequence seq = new Sequence();
                        seq.setFrame(m);
                        seq.setName("新建序列");
                        action.addSequence(seq);
                        System.out.println("add frame");
                    }
                }
                dtde.dropComplete(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void run() {
    }
}
