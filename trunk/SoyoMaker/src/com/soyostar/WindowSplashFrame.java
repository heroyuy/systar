/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyostar;

import java.awt.*;

import java.io.File;

import javax.swing.*;

public class WindowSplashFrame extends JFrame {

    private JWindow splashWin;

    public void prepareSplash() {

        splashWin = new JWindow(this);
        Toolkit toolkit = Toolkit.getDefaultToolkit();

        Image image = toolkit.getImage("apple.png");
        JLabel label = new JLabel();
        label.setSize(new Dimension(100, 100));
        label.setIcon(new ImageIcon(image));
        label.setBackground(Color.red);
        label.setVisible(true);
        splashWin.add(label);

        Dimension scmSize = toolkit.getScreenSize();
        int imgWidth = image.getWidth(this);
        int imgHeight = image.getHeight(this);
        System.out.println(imgWidth + "," + imgHeight);
        splashWin.setLocation(scmSize.width / 2 - (imgWidth / 2), scmSize.height / 2
            - (imgHeight / 2));
        splashWin.setSize(imgWidth, imgHeight);
        splashWin.setVisible(true);
    }

    public void startSplash() {
        splashWin.setVisible(true);
        splashWin.toFront();
    }

    public void stopSplash() {
        splashWin.dispose();
    }

    public static void main(String args[]) {
        WindowSplashFrame frame = new WindowSplashFrame();
        frame.prepareSplash();
        frame.startSplash();
        try {
            Thread.currentThread().sleep(5000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        frame.stopSplash();
    }
}
