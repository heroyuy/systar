/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyomaker.widget;

import com.soyomaker.dialog.SpriteEditorDialog;
import com.soyomaker.model.animation.Picture;
import com.soyomaker.AppData;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

/**
 *
 * @author Administrator
 */
public class ChoosePicturePane extends JPanel implements MouseListener, MouseMotionListener {

    private Dimension viewSize = new Dimension(128, 128);
    private static final int titleSize = 16;
    private int selectedIndex = -1;
    private Picture selectedPicture = null;
    private SpriteEditorDialog sed;
    private static final int WS = 16;
    private static final int HS = 16;
    private Point point = new Point();

    /**
     *
     * @param sed
     */
    public ChoosePicturePane(SpriteEditorDialog sed) {
        this.sed = sed;
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }

    /**
     *
     * @return
     */
    public int getSelectedIndex() {
        return selectedIndex;
    }

    /**
     *
     * @return
     */
    public Picture getSelectedPicture() {
        return selectedPicture;
    }

    @Override
    public Dimension getPreferredSize() {
        Dimension size = super.getPreferredSize();
        if (AppData.getInstance().getCurProject() == null) {
            return size;
        }
        int len = AppData.getInstance().getCurProject().getPictures().size();
        return new Dimension(Math.max(size.width, this.viewSize.width + 2 * WS),
                Math.max(size.height, len * (this.viewSize.height + HS + this.titleSize) + HS));
    }

    /**
     *
     * @param sel
     */
    public void setSelectedIndex(int sel) {
        selectedIndex = sel;
        selectedPicture = AppData.getInstance().getCurProject().getPicture(sel);
        this.updateUI();
    }

    /**
     *
     * @param x
     * @param y
     * @return
     */
    public boolean setSelectedPicture(int x, int y) {
        int len = AppData.getInstance().getCurProject().getPictures().size();
        if (len <= 0) {
            return false;
        }
        for (int i = 0; i < len; i++) {
            if (x > WS && y > HS + i * (this.viewSize.height + titleSize + HS) && y < HS + i * (this.viewSize.height + titleSize + HS) + this.viewSize.height + titleSize && x < WS + this.viewSize.width) {
                this.setSelectedIndex(i);
                return true;
            }
        }
        this.setSelectedIndex(-1);
        return false;
    }

    @Override
    public void paintComponent(Graphics gg) {
        gg.clearRect(0, 0, this.getWidth(), this.getHeight());
        if (AppData.getInstance().getCurProject() != null) {
            for (int i = 0; i < AppData.getInstance().getCurProject().getPictures().size(); i++) {
                Picture pic = AppData.getInstance().getCurProject().getPicture(i);
                gg.setColor(Color.WHITE);
                gg.fillRect(WS, HS + titleSize + i * titleSize + i * viewSize.height + HS * i, viewSize.width - 1, viewSize.height - 1);
                gg.setColor(Color.LIGHT_GRAY);
                for (int y = HS / 8 + titleSize / 8 + i * (titleSize + viewSize.height) / 8; y < HS / 8 + titleSize / 8 + i * (titleSize + viewSize.height) / 8 + viewSize.height / 8; y++) {
                    for (int x = WS / 8; x < (WS + viewSize.width) / 8; x++) {
                        if ((y + x) % 2 == 1) {
                            gg.fillRect(x * 8, y * 8 + 16 * i, 8, 8);
                        }
                    }
                }
                pic.paintPreview(gg,
                        WS + 1, i * (this.viewSize.height + this.titleSize + HS) + HS + titleSize + 1,
                        this.viewSize.width - 2, this.viewSize.height - 2);
                Color labelColor = Color.BLACK;
                if (this.selectedIndex == i) {
                    labelColor = Color.BLUE;
                }
                for (int j = 0; j < this.titleSize; j++) {
                    gg.setColor(new Color(labelColor.getRed(), labelColor.getGreen(), labelColor.getBlue(), 127 + j * 8));
                    gg.drawLine(WS, j + HS + i * (this.viewSize.height + titleSize + HS), WS + this.viewSize.width, i * (this.viewSize.height + titleSize + HS) + j + HS);
                }
                if (this.selectedIndex == i) {
                    gg.setColor(Color.BLUE);
                } else {
                    gg.setColor(Color.BLACK);
                }
                gg.draw3DRect(WS, HS + titleSize + i * (this.viewSize.height + HS + titleSize), this.viewSize.width, this.viewSize.height, true);
                gg.setColor(Color.WHITE);
                gg.drawString(pic.getSourceImageFile(), WS + 2, i * (this.viewSize.height + titleSize + HS) + HS + titleSize - 2);
            }
        }
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
        setSelectedPicture(e.getX(), e.getY());
        point.x = e.getX();
        point.y = e.getY();
        sed.handlePictureScrollPane.setViewportView(sed.getHandlePicturePanes().get(selectedPicture));
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseDragged(MouseEvent e) {
        int oy = e.getY() - point.y;
        point.x = e.getX();
        point.y = e.getY();
        Rectangle viewArea = sed.showPictureScrollPane.getViewport().getViewRect();
        //System.out.println(this.getPreferredSize().height + ":" + viewArea.height);
        if (viewArea.y - oy > 0 && viewArea.y - oy < this.getPreferredSize().height) {
            viewArea.y -= oy;
            sed.showPictureScrollPane.getViewport().setViewPosition(new Point(viewArea.x, viewArea.y));
            this.updateUI();
        }
    }

    public void mouseMoved(MouseEvent e) {
    }
}
