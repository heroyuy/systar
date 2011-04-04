/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyostar.pluginimpl.sprite.widge;

import com.soyostar.pluginimpl.sprite.model.Animation;
import com.soyostar.pluginimpl.sprite.model.Frame;
import com.soyostar.pluginimpl.sprite.util.ActionFrameSelection;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragGestureListener;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

/**
 *
 * @author Administrator
 */
public class FrameViewPane extends JPanel implements DragGestureListener {

    private int selectedIndex = -1;
    private Dimension viewSize = new Dimension(128, 128);
    private int titleSize = 16;

    public FrameViewPane() {
    }

    /**
     *
     * @return
     */
    public int getSelectedIndex() {
        return this.selectedIndex;
    }

    /**
     *
     * @param index
     */
    public void setSelectedIndex(int index) {
        this.selectedIndex = index;
        this.updateUI();
    }

    public Frame getSelectedFrame() {

        return this.selectedIndex < 0 ? null : Animation.getInstance().getFrame(this.selectedIndex);
    }

    /**
     *
     * @param x
     * @param y
     */
    public void setSelectedFrame(int x, int y) {
        int len = Animation.getInstance().getFramesCount();
        if (y > 0 && y <= this.viewSize.height + this.titleSize) {
            if (x > 0 && x <= len * this.viewSize.width) {
                this.setSelectedIndex(x / this.viewSize.width);
                return;
            }
        }
        this.setSelectedIndex(-1);
    }

    @Override
    public Dimension getPreferredSize() {
        Dimension size = super.getPreferredSize();
        int len = Animation.getInstance().getFramesCount();
        return new Dimension(Math.max(size.width, len * (this.viewSize.width + 1)), Math.max(size.height, this.viewSize.height + this.titleSize));
    }

    @Override
    public void paintComponent(Graphics g) {
        g.clearRect(0, 0, this.getWidth(), this.getHeight());
        int cc = 150 / this.titleSize;
        for (int i = 0; i < Animation.getInstance().getFramesCount(); i++) {
            g.setColor(Color.WHITE);
            g.fillRect(i * viewSize.width, titleSize, viewSize.width - 1, viewSize.height - 1);
            g.setColor(Color.LIGHT_GRAY);
            for (int y = titleSize / 8; y < (titleSize + viewSize.height) / 8; y++) {
                for (int x = i * viewSize.width / 8; x < (i + 1) * viewSize.width / 8; x++) {
                    if ((y + x) % 2 == 1) {
                        g.fillRect(x * 8, y * 8, 8, 8);
                    }
                }
            }
            Animation.getInstance().getFrame(i).paintPreview(g, i * this.viewSize.width + 2, this.titleSize + 2, this.viewSize.width - 4);
            Color labelColor = Color.BLACK;
            if (this.selectedIndex == i) {
                labelColor = Color.BLUE;
            }
            for (int j = 0; j < this.titleSize; j++) {
                g.setColor(new Color(labelColor.getRed(), labelColor.getGreen(), labelColor.getBlue(), 105 + j * cc));
                g.drawLine(i * this.viewSize.width, j + 1, i * this.viewSize.width + this.viewSize.width - this.titleSize + j, j + 1);
            }
            if (this.selectedIndex == i) {
                g.setColor(Color.BLUE);
            } else {
                g.setColor(Color.BLACK);
            }
            g.draw3DRect(i * (this.viewSize.width), this.titleSize, this.viewSize.width - 1, this.viewSize.height - 1, true);
            g.setColor(Color.WHITE);
            g.drawString(Animation.getInstance().getFrame(i).getName(), i * (this.viewSize.width) + 2, this.titleSize - 2);
        }
    }

    public void dragGestureRecognized(DragGestureEvent dge) {
        if (getSelectedFrame() != null) {
            Transferable transferable = new ActionFrameSelection(getSelectedFrame());
            dge.startDrag(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR), transferable);
        }
    }
}
