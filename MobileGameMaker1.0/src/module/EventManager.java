/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package module;

import frame.event.ui_event.*;
import engine.MapEditor;
import model.*;
//import event.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.*;

/**
 *事件管理器模块
 * @author Administrator
 */
public class EventManager extends JDialog implements MouseListener {

    private MapEditor owner;
    private static final long serialVersionUID = 1L;
    private String[] col = {"执行内容"};
//    private int index = 0;// 自动生成的编号
    public DefaultTableModel eventTM = null; // 定义一个表的模板

    public EventManager(MapEditor owner) {
        super(owner);
        this.owner = owner;
        eventTM = new javax.swing.table.DefaultTableModel(col, 0) {

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
//        loadScript();
        initialize();
//        this.addWindowListener(new WindowAdapter() {
//
//            @Override
//            public void windowClosing(WindowEvent e) {
////                checkTable();
//            }
//        });
    }
//    private void loadScript() {
//    }
    private JTable jTable;

    private JTable getJTable() {
        if (jTable == null) {
            jTable = new JTable();
            jTable.addMouseListener(this);
            jTable.setModel(eventTM);
            jTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        }
        return jTable;
    }
    private JScrollPane jScrollPane = null;

    private JScrollPane getJScrollPane() {
        if (jScrollPane == null) {
            jScrollPane = new JScrollPane();
            jScrollPane.setSize(new Dimension(340, 450));
            jScrollPane.setLocation(new Point(310, 50));
            jScrollPane.setViewportView(getJTable());
        }
        return jScrollPane;
    }
    private JPopupMenu jPopupMenu = null; // @jve:decl-index=0:visual-constraint="633,166"

    private JPopupMenu getJPopupMenu() {
        if (jPopupMenu == null) {
            jPopupMenu = new JPopupMenu();
            jPopupMenu.setSize(new Dimension(61, 100));
            jPopupMenu.add(getJMenuItem2());
            jPopupMenu.add(getJMenuItem());
            jPopupMenu.add(getJMenuItem1());

        }
        return jPopupMenu;
    }
    private JMenuItem jMenuItem = null;
    private JMenuItem jMenuItem1 = null, jMenuItem2 = null;

    private JMenuItem getJMenuItem() {
        if (jMenuItem == null) {
            jMenuItem = new JMenuItem();
            jMenuItem.setText("插入");
            jMenuItem.addActionListener(new java.awt.event.ActionListener() {

                public void actionPerformed(java.awt.event.ActionEvent e) {
                    insertRow();
                }
            });
        }
        return jMenuItem;
    }

    private void insertRow() {
        Dialog_Main dm = new Dialog_Main(this);
        dm.setVisible(true);
//        ActionManager e = new ActionManager(this, owner);
//        e.setVisible(true);
    }

    private JMenuItem getJMenuItem2() {
        if (jMenuItem2 == null) {
            jMenuItem2 = new JMenuItem();
            jMenuItem2.setText("新建");
            jMenuItem2.addActionListener(new java.awt.event.ActionListener() {

                public void actionPerformed(java.awt.event.ActionEvent e) {
                    addRow();
//                    editRow();
                }
            });
        }
        return jMenuItem2;
    }

//    private void editRow() {
////        System.out.println("编辑");
//    }
    private void addRow() {
        String[] rowData = {""};
        eventTM.addRow(rowData);
    }

    private void delRow() {
//        System.out.println("del");
        int num = jTable.getSelectedRow();
        System.out.println("select: " + num);
        if (num != -1) {
//            sl.getScript(owner.eventX, owner.eventY).delString(num);
            eventTM.removeRow(num);
            if (eventTM.getRowCount() == 0) {
//                index = 0;
                addRow();
            }
            curRow = 0;
        }
    }
//    private Hashtable ht;
    public int curRow = 0;

    public void updateList() {
        // TODO 更新列表

        // 清除现在表格中的所有数据
        int num = eventTM.getRowCount();
        for (int i = 0; i < num; i++) {
            eventTM.removeRow(0);
        }
        Script script = owner.getMap().getScriptList()[owner.eventY][owner.eventX];
        if (script == null) {
            addRow();
//            System.out.println("null updatelist");
        } else {
            int sum = script.size();
            //由于第一行和最后一行为事件开始条件行 不显示 由专门的jpanel显示
            for (int i = 1; i < sum - 1; i++) {
                String[] rowData = {script.getString(i)};
                eventTM.addRow(rowData);
            }
        }
        sType = owner.getMap().getScriptType()[owner.eventY][owner.eventX];
        switch (sType) {
//            case 1:
//                jrb1.setSelected(true);
//                break;
            case 2:
                jrb2.setSelected(true);
                break;
//            case 3:
//                jrb3.setSelected(true);
//                break;
            case 4:
                jrb4.setSelected(true);
                break;
            case 0:
                sType = 1;//恢复为1
                break;
        }

        String ss = "";
        if (owner.getMap().getScriptList()[owner.eventY][owner.eventX] != null) {
            ss = owner.getMap().getScriptList()[owner.eventY][owner.eventX].getString(0);
        }
        if (ss.equals("IF true")) {
            jch1.setSelected(false);
            jch3.setSelected(false);
        }
        if (ss.startsWith("IF $SWITCH") && ss.contains(">")) {
            String temp = ss.substring(ss.indexOf("[") + 1, ss.indexOf("]"));
            System.out.println("temp " + temp);
            String temp2 = ss.substring(ss.lastIndexOf("[") + 1, ss.lastIndexOf("]"));
            System.out.println("temp2 " + temp2);
            String temp3 = ss.substring(ss.indexOf(">") + 1);
            System.out.println("temp3 " + temp3);
            jch1.setSelected(true);
            jcb1.setEnabled(true);
            jcb1.removeAllItems();
            jcb1.addItem(temp);
            for (int i = 0; i < 50; i++) {
                jcb1.addItem(i);
            }
            jch3.setSelected(true);
            jcb3.removeAllItems();
            jcb3.addItem(temp2);
            for (int i = 0; i < 50; i++) {
                jcb3.addItem(i);
            }
            jSpinner1.setValue(Integer.parseInt(temp3));
            jcb3.setEnabled(true);
            jSpinner1.setEnabled(true);

        }
        if (ss.startsWith("IF $SWITCH") && !ss.contains(">")) {
            String temp = ss.substring(ss.indexOf("[") + 1, ss.indexOf("]"));

//            System.out.println(temp);
            jch1.setSelected(true);
            jcb1.setEnabled(true);
            jcb1.removeAllItems();
            jcb1.addItem(temp);
            for (int i = 0; i < 50; i++) {
                jcb1.addItem(i);
            }
            jch3.setSelected(false);
        }
        if (ss.startsWith("IF $VAR")) {
            jch1.setSelected(false);
            jch3.setSelected(true);
            jcb3.removeAllItems();
            String temp = ss.substring(ss.indexOf("[") + 1, ss.indexOf("]"));
            jcb3.addItem(temp);
            for (int i = 0; i < 50; i++) {
                jcb3.addItem(i);
            }
            String temp2 = ss.substring(ss.indexOf(">") + 1);
//            System.out.println(temp2);
            jSpinner1.setValue(Integer.parseInt(temp2));
            jcb3.setEnabled(true);
            jSpinner1.setEnabled(true);
        }
    }

    private JMenuItem getJMenuItem1() {
        if (jMenuItem1 == null) {
            jMenuItem1 = new JMenuItem();
            jMenuItem1.setText("删除");
            jMenuItem1.addActionListener(new java.awt.event.ActionListener() {

                public void actionPerformed(java.awt.event.ActionEvent e) {
                    delRow();
                }
            });
        }
        return jMenuItem1;
    }

    public void mouseClicked(MouseEvent e) {
        // TODO 弹出菜单和切换物品
        getCurRow();
//        System.out.println("Click curRow: " + curRow);
        if (e.getButton() == MouseEvent.BUTTON3) {
            // 右键弹出菜单
            getJPopupMenu();
            jPopupMenu.show(e.getComponent(), e.getX(), e.getY());
        } else if (e.getButton() == MouseEvent.BUTTON1) {
            // 左键进行物品切换
            if (e.getClickCount() >= 2) {
                insertRow();
            }
        }
    }

    public void mouseEntered(MouseEvent e) {
        // TODO 自动生成方法存根
    }

    public void mouseExited(MouseEvent e) {
        // TODO 自动生成方法存根
    }

    public void mousePressed(MouseEvent e) {
        // TODO 自动生成方法存根
        getCurRow();
//        System.out.println("Pressed curRow: " + curRow);
    }

    private void getCurRow() {
        curRow = jTable.getSelectedRow();
        if (curRow == -1) {
            curRow = 0;
        }
    }

    public void mouseReleased(MouseEvent e) {
        // TODO 自动生成方法存根
        getCurRow();
//        System.out.println("Release curRow: " + curRow);
    }

    public void initialize() {
        this.setSize(670, 580);
        Dimension screenSize =
            Toolkit.getDefaultToolkit().getScreenSize();
        Dimension labelSize = this.getSize();
        this.setLocation(screenSize.width / 2 - (labelSize.width / 2),
            screenSize.height / 2 - (labelSize.height / 2));//设置位置屏幕中部
        this.setTitle("编辑事件");
        this.setModal(true);
        this.setContentPane(getEventPanel());
        this.setResizable(false);
        updateList();
        jTable.setRowSelectionInterval(0, 0);
    }
    private JPanel jpEvent;
    private JButton jbEventOk;
    private JButton jbEventCancle;

    private JPanel getEventPanel() {
        if (jpEvent == null) {
            jpEvent = new JPanel();
            jpEvent.setLayout(null);
            jpEvent.add(getEventOk(), null);
            jpEvent.add(getEventCancle(), null);
            jpEvent.add(getEventUse(), null);
            jpEvent.add(getJpanel1(), null);
            jpEvent.add(getJpanel4(), null);
            jpEvent.add(getJScrollPane(), null);
        }
        return jpEvent;
    }
    private JComboBox jcb1, jcb2, jcb3, jcb4, jcb5, jcb6, jcb7;
    private JPanel jp1, jp2, jp3, jp4, jp5;
    private JCheckBox jch1, jch2, jch3, jch4, jch5, jch6, jch7, jch8, jch9;
    private JRadioButton jrb2, jrb4, jrb5;
    private ButtonGroup jbg = new ButtonGroup();
    private javax.swing.JSpinner jSpinner1;

    private JPanel getJpanel1() {
        if (jp1 == null) {
            jp1 = new JPanel();
            jp1.setLayout(null);
            jp1.setBounds(10, 40, 290, 175);
            JLabel jl1 = new JLabel("为ON时");
            jl1.setBounds(230, 40, 60, 20);
            JLabel jl3 = new JLabel("值为");
            jl3.setBounds(230, 90, 60, 20);
            jSpinner1 = new JSpinner();
            jSpinner1.setBounds(90, 120, 130, 20);
            jSpinner1.setEnabled(false);
            JLabel jl4 = new JLabel("以上");
            jl4.setBounds(230, 120, 60, 20);
            jcb1 = new JComboBox();
            jcb1.setBounds(90, 40, 130, 20);
            jcb1.setEnabled(false);
            for (int i = 0; i < 50; i++) {
                jcb1.addItem(i);
            }
            jch1 = new JCheckBox("开关");
            jch1.setBounds(10, 40, 60, 20);
            jch1.addActionListener(new java.awt.event.ActionListener() {

                @Override
                public void actionPerformed(java.awt.event.ActionEvent e) {
//                    checkTable();
                    if (jch1.isSelected()) {
                        jcb1.setEnabled(true);
                    } else {
                        jcb1.setEnabled(false);
                    }

                }
            });
            jcb3 = new JComboBox();
            jcb3.setBounds(90, 90, 130, 20);
            for (int i = 0; i < 50; i++) {
                jcb3.addItem(i);
            }
            jcb3.setEnabled(false);
            jch3 = new JCheckBox("变量");
            jch3.setBounds(10, 90, 60, 20);
            jch3.addActionListener(new java.awt.event.ActionListener() {

                @Override
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    if (jch3.isSelected()) {
                        jcb3.setEnabled(true);
                        jSpinner1.setEnabled(true);
                    } else {
                        jcb3.setEnabled(false);
                        jSpinner1.setEnabled(false);
                    }

                }
            });
            jp1.add(jl1, null);
            jp1.add(jl3, null);
            jp1.add(jl4, null);
            jp1.add(jSpinner1, null);
            jp1.add(jch1, null);
            jp1.add(jch3, null);
            jp1.add(jcb1, null);
            jp1.add(jcb3, null);
            jp1.setBorder(BorderFactory.createTitledBorder("事件出现条件"));
        }
        return jp1;
    }
    private byte sType = 1;

    private JPanel getJpanel4() {
        if (jp4 == null) {
            jp4 = new JPanel();
            jp4.setLayout(null);
            jp4.setBounds(10, 370, 290, 130);
//            jrb1 = new JRadioButton("接触并行");
//
//            jrb1.setEnabled(false);
//            jrb1.addActionListener(new java.awt.event.ActionListener() {
//
//                public void actionPerformed(java.awt.event.ActionEvent e) {
//                    sType = 1;
//                }
//            });
//            jrb1.setBounds(50, 30, 100, 20);
            jrb2 = new JRadioButton("接触串行");
            jrb2.setBounds(50, 70, 100, 20);
            jrb2.setSelected(true);
            jrb2.addActionListener(new java.awt.event.ActionListener() {

                public void actionPerformed(java.awt.event.ActionEvent e) {
                    sType = 2;
                }
            });
//            jrb3 = new JRadioButton("按键并行");
//            jrb3.setBounds(160, 30, 100, 20);
//            jrb3.setEnabled(false);
//            jrb3.addActionListener(new java.awt.event.ActionListener() {
//
//                public void actionPerformed(java.awt.event.ActionEvent e) {
//                    sType = 3;
//                }
//            });
            jrb4 = new JRadioButton("按键串行");
            jrb4.setBounds(160, 70, 100, 20);
            jrb4.addActionListener(new java.awt.event.ActionListener() {

                public void actionPerformed(java.awt.event.ActionEvent e) {
                    sType = 4;
                }
            });
//            jbg.add(jrb1);
            jbg.add(jrb2);
//            jbg.add(jrb3);
            jbg.add(jrb4);
//            jp4.add(jrb1, null);
            jp4.add(jrb2, null);
//            jp4.add(jrb3, null);
            jp4.add(jrb4, null);
            jp4.setBorder(BorderFactory.createTitledBorder("事件开始条件"));
        }
        return jp4;
    }

    private JButton getEventOk() {
        if (jbEventOk == null) {
            jbEventOk = new JButton();
            jbEventOk.setText("确定");
            jbEventOk.setBounds(390, 520, 60, 20);
//            jItemExit.addActionListener(this);
            jbEventOk.addActionListener(new java.awt.event.ActionListener() {

                @Override
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    save();
//                    checkTable();
                    setVisible(false);
                }
            });
        }
        return jbEventOk;
    }

//    private void checkTable() {
//        System.out.println(jTable.getRowCount());
//        if ((jTable.getRowCount() == 1)) {
//            Script s = owner.getMap().getScriptList()[owner.eventY][owner.eventX];
//            System.out.println(s!=null);
//            if (s!=null) {
//                System.out.println("remove");
//                if (s.getString(0).equals("")) {
//                    owner.getMap().getScriptList()[owner.eventY][owner.eventX]=null;
//                }
//            }
//        }
////        owner.getMap().getScriptList().printList();
//    }
    private JButton getEventCancle() {
        if (jbEventCancle == null) {
            jbEventCancle = new JButton();
            jbEventCancle.setText("取消");
            jbEventCancle.setBounds(470, 520, 60, 20);
//            jItemExit.addActionListener(this);
            jbEventCancle.addActionListener(new java.awt.event.ActionListener() {

                @Override
                public void actionPerformed(java.awt.event.ActionEvent e) {
//                    checkTable();
                    setVisible(false);
                }
            });
        }
        return jbEventCancle;
    }
    private JButton jbEventUse;

    private JButton getEventUse() {
        if (jbEventUse == null) {
            jbEventUse = new JButton();
            jbEventUse.setText("应用");
            jbEventUse.setBounds(550, 520, 60, 20);
            jbEventUse.addActionListener(new java.awt.event.ActionListener() {

                @Override
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    save();
                }
            });
        }
        return jbEventUse;
    }

    private void save() {
        Script s = new Script(owner.eventX, owner.eventY);
//        System.out.println("jTable.getRowCount() " + jTable.getRowCount());
        if (jch1.isSelected() && !jch3.isSelected()) {
            s.addString("IF $SWITCH[" + jcb1.getSelectedItem().toString() + "]");

        }
        if (jch1.isSelected() && jch3.isSelected()) {
            s.addString("IF $SWITCH[" + jcb1.getSelectedItem().toString() + "]&&" + "$VAR[" + jcb3.getSelectedItem().toString() + "]>" + jSpinner1.getValue().toString());

        }
        if (!jch1.isSelected() && jch3.isSelected()) {
            s.addString("IF $VAR[" + jcb3.getSelectedItem().toString() + "]>" + jSpinner1.getValue().toString());

        }
        if (!jch1.isSelected() && !jch3.isSelected()) {
            s.addString("IF true");

        }
        for (int i = 0; i < jTable.getRowCount(); i++) {
            s.addString(jTable.getValueAt(i, 0).toString());
        }
        s.addString("ENDIF");
        owner.getMap().getScriptList()[owner.eventY][owner.eventX] = s;
        owner.getMap().getScriptType()[owner.eventY][owner.eventX] = sType;

    }
}
