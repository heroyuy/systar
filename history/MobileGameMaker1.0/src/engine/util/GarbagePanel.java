/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package engine.util;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GarbagePanel extends JPanel implements MouseListener {

    private JProgressBar pb;

    public GarbagePanel() {
        pb = new JProgressBar();
        pb.setPreferredSize(new Dimension(150, 20));
        pb.setToolTipText("点击清理内存");
        pb.addMouseListener(this);
        // 设置定时器，用来控制进度条的处理
        javax.swing.Timer time = new javax.swing.Timer(1000, new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                int total = (int) Runtime.getRuntime().totalMemory();
                int used = (int) (total - Runtime.getRuntime().freeMemory());
                pb.setMaximum(total);
                pb.setValue(used);
                pb.setString(used / 1024 + " KB / " + total / 1024 + " KB");

            }
        });
        time.start();

        pb.setStringPainted(true);
        this.setLayout(new BorderLayout());
        this.add(pb, BorderLayout.EAST);
    }

    /**
     * 设置进度条的数据模型
     */
    public void setProcessBar(BoundedRangeModel rangeModel) {
        pb.setModel(rangeModel);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO 自动生成方法存根
//        System.gc();
//        System.out.println("gc");
    }

    public void mouseEntered(MouseEvent e) {
        // TODO 自动生成方法存根
    }

    public void mouseExited(MouseEvent e) {
        // TODO 自动生成方法存根
    }

    public void mousePressed(MouseEvent e) {
//            // 单击右键弹出菜单,左键选中地图
        System.gc();
//        System.out.println("gc");
    }

    public void mouseReleased(MouseEvent e) {
        // TODO 自动生成方法存根
    }
}
