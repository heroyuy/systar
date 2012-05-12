/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyomaker.widget;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Rectangle;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SpringLayout;
import javax.swing.border.Border;

/**
 *
 * @author Administrator
 */
public class JAccordionPane extends JPanel {

    private static final long serialVersionUID = 1L;
    private final JPanel panel = new JPanel();
    private final JScrollPane scroll;
    private AccordionListener exr = new AccordionListener() {

        public void accordionStateChanged(AccordionEvent e) {
            initComponent();
        }
    };
    // 折叠效果

    /**
     *
     */
    public JAccordionPane() {
        super(new BorderLayout());
        panel.setOpaque(true);
        panel.setBackground(new Color(116, 149, 226));
        scroll = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.getVerticalScrollBar().setUnitIncrement(10);
        scroll.getViewport().add(panel);
        // 加载滚动条监听
        scroll.getViewport().addComponentListener(new ComponentAdapter() {

            @Override
            public void componentResized(ComponentEvent e) {
                initComponent();
            }
        });
        scroll.setPreferredSize(new Dimension(256, 256));
        scroll.setMinimumSize(new Dimension(256, 256));
        add(scroll, BorderLayout.CENTER);
    }
    private List<JScrollPane> panels = new ArrayList<JScrollPane>();
    private List<AccordionPanel> accordionPanels = new ArrayList<AccordionPanel>();

    /**
     *
     * @param name
     * @param panel
     */
    public void addPanel(String name, final JScrollPane panel) {
        AccordionPanel ap = new AccordionPanel(name) {

            @Override
            public JScrollPane makePanel() {
                panel.setSize(panel.getPreferredSize());
                //panel.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
                return panel;
            }
        };
        accordionPanels.add(ap);
        panels.add(panel);
        refreshPanes();
    }

    /**
     *
     * @param panel
     */
    public void removePanel(JScrollPane panel) {
        accordionPanels.remove(panels.indexOf(panel));
        panels.remove(panel);
        refreshPanes();
    }

    private void refreshPanes() {
        panel.removeAll();
        for (Iterator it = accordionPanels.iterator(); it.hasNext();) {
            AccordionPanel epl = (AccordionPanel) it.next();
            addComponent(epl);
            epl.addaccordionListener(exr);
        }
    }

    /**
     *
     */
    public void initComponent() {
        Rectangle re = scroll.getViewport().getViewRect();
        Insets ins = panel.getInsets();
        int cw = (int) re.getWidth() - ins.left - ins.right - 20;
        int ch = 10;
        Component[] list = panel.getComponents();
        for (int i = 0; i < list.length; i++) {
            JComponent tmp = (JComponent) list[i];
            int th = tmp.getPreferredSize().height;
            tmp.setPreferredSize(new Dimension(cw, th));
            ch = ch + th + 10;
        }
        panel.setPreferredSize(new Dimension((int) re.getWidth(), ch + ins.top
                + ins.bottom));
        panel.revalidate();
    }

    /**
     *
     * @param label
     */
    public void addComponent(Component label) {
        SpringLayout layout = new SpringLayout();
        Component[] list = panel.getComponents();
        if (list.length == 0) {
            layout.putConstraint(SpringLayout.WEST, label, 10,
                    SpringLayout.WEST, panel);
            layout.putConstraint(SpringLayout.NORTH, label, 10,
                    SpringLayout.NORTH, panel);
        } else {
            JComponent cmp = null;
            for (int i = 0; i < list.length; i++) {
                JComponent tmp = (JComponent) list[i];
                layout.putConstraint(SpringLayout.WEST, tmp, 10,
                        SpringLayout.WEST, panel);
                if (cmp == null) {
                    layout.putConstraint(SpringLayout.NORTH, tmp, 10,
                            SpringLayout.NORTH, panel);
                } else {
                    layout.putConstraint(SpringLayout.NORTH, tmp, 10,
                            SpringLayout.SOUTH, cmp);
                }
                cmp = tmp;
            }
            layout.putConstraint(SpringLayout.WEST, label, 10,
                    SpringLayout.WEST, panel);
            layout.putConstraint(SpringLayout.NORTH, label, 10,
                    SpringLayout.SOUTH, cmp);
        }
        panel.add(label);
        panel.setLayout(layout);
        initComponent();
    }
}

class AccordionEvent extends java.util.EventObject {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public AccordionEvent(Object source) {
        super(source);
    }
}

interface AccordionListener {

    public void accordionStateChanged(AccordionEvent e);
}

abstract class AccordionPanel extends JPanel {

    protected abstract JScrollPane makePanel();
    private final String _title;
    private final JLabel label;
    private final JScrollPane panel;
    private boolean openFlag = false;

    public AccordionPanel(String title) {
        super(new BorderLayout());
        _title = title;
        label = new JLabel("↓ " + title) {

            private static final long serialVersionUID = 1L;

            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                // 绘制渐变
                g2.setPaint(new GradientPaint(50, 0, Color.WHITE, getWidth(),
                        getHeight(), new Color(199, 212, 247)));
                g2.fillRect(0, 0, getWidth(), getHeight());
                super.paintComponent(g);
            }
        };
        label.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent evt) {
                openFlag = !openFlag;
                initPanel();
                fireaccordionEvent();
            }
        });
        label.setForeground(new Color(33, 93, 198));
        label.setFont(new Font("宋体", 1, 12));
        label.setBorder(BorderFactory.createEmptyBorder(2, 5, 2, 2));
        panel = makePanel();
        panel.setOpaque(true);
        Border outBorder = BorderFactory.createMatteBorder(0, 0, 0, 0,
                Color.WHITE);
        Border inBorder = BorderFactory.createEmptyBorder(1, 1, 1, 1);
        Border border = BorderFactory.createCompoundBorder(outBorder, inBorder);
        panel.setBorder(border);
        panel.setBackground(new Color(240, 240, 255));
        add(label, BorderLayout.NORTH);
    }

    public boolean isSelected() {
        return openFlag;
    }

    public void setSelected(boolean flg) {
        openFlag = flg;
        initPanel();
    }

    protected void initPanel() {
        if (isSelected()) {
            label.setText("↑ " + _title);
            add(panel, BorderLayout.CENTER);
            setPreferredSize(new Dimension(getSize().width,
                    label.getSize().height + panel.getSize().height));
        } else {
            label.setText("↓ " + _title);
            remove(panel);
            setPreferredSize(new Dimension(getSize().width,
                    label.getSize().height));
        }
        revalidate();
    }
    protected ArrayList accordionListenerList = new ArrayList();

    public void addaccordionListener(AccordionListener listener) {
        if (!accordionListenerList.contains(listener)) {
            accordionListenerList.add(listener);
        }
    }

    public void removeaccordionListener(AccordionListener listener) {
        accordionListenerList.remove(listener);
    }

    public void fireaccordionEvent() {
        List list = (List) accordionListenerList.clone();
        Iterator it = list.iterator();
        AccordionEvent e = new AccordionEvent(this);
        while (it.hasNext()) {
            AccordionListener listener = (AccordionListener) it.next();
            listener.accordionStateChanged(e);
        }
    }
}
