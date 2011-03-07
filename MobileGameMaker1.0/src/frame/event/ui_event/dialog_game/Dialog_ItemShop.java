package frame.event.ui_event.dialog_game;

import java.awt.Dimension;
import java.awt.Point;

import javax.swing.*;

import frame.event.ui_event.Dialog_Main;
import frame.event.ui_event.Event_Dialog;
import javax.swing.JScrollPane;
import java.awt.*;
import javax.swing.JTable;
import javax.swing.JDialog;
import java.awt.BorderLayout;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.*;
import javax.swing.table.DefaultTableModel;
import model.*;

public class Dialog_ItemShop extends Event_Dialog implements MouseListener {

    private JPanel jContentPane = null;
    private JScrollPane jScrollPane = null;
    private JTable jTable_item = null;
    private JDialog jDialog_sub = null;  //  @jve:decl-index=0:visual-constraint="431,80"
    private JPanel jContentPane1 = null;
    private JLabel jLabel = null;
    private JComboBox jComboBox_item = null;
    private JButton jButton_confirm = null;
    private JButton jButton_cancel = null;
    private Dialog_Main p;

    public Dialog_ItemShop(Dialog_Main parent) {
        super(parent);
        p = parent;
        initialize();
    }

    /**
     * This method initializes this
     *
     */
    private void initialize() {
        this.setSize(new Dimension(347, 322));
         this.setTitle("物品商店");
        Dimension screenSize =
            Toolkit.getDefaultToolkit().getScreenSize();
        Dimension labelSize = this.getSize();
        this.setLocation(screenSize.width / 2 - (labelSize.width / 2),
            screenSize.height / 2 - (labelSize.height / 2));//设置位置屏幕中部
        this.setContentPane(getJContentPane());
        this.getContentPane().setLayout(null);
        addDefaultButton();
    }

    public boolean confirm() {
        // TODO 自动生成方法存根
        StringBuilder sc = new StringBuilder();
        sc.append("ITEMSHOP");
        for (int i = 0; i < jTable_item.getRowCount(); i++) {
            sc.append(" ").append(jTable_item.getValueAt(i, 0).toString());
        }
        p.insertEventTM(p.getEventDialog().curRow, sc.toString());
        return false;
    }
    private int curRow = 0;

    public void mouseClicked(MouseEvent e) {
        // TODO 弹出菜单和切换物品
        if (jTable_item.getSelectedRow() != -1) {
            curRow = jTable_item.getSelectedRow();
        }
        if (e.getClickCount() >= 2) {
            getJDialog_sub().setVisible(true);
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
        if (jTable_item.getSelectedRow() != -1) {
            curRow = jTable_item.getSelectedRow();
        }
        if (e.getButton() == MouseEvent.BUTTON3) {
            // 右键弹出菜单
            getJPopupMenu();
            jPopupMenu.show(e.getComponent(), e.getX(), e.getY());
        }
    }

    public void mouseReleased(MouseEvent e) {
        // TODO 自动生成方法存根
        if (jTable_item.getSelectedRow() != -1) {
            curRow = jTable_item.getSelectedRow();
        }

    }

    private void delRow() {
//        System.out.println("del");
        int num = jTable_item.getSelectedRow();
        System.out.println("select: " + num);
        if (num != -1) {
            dtm.removeRow(num);
        }
        curRow = 0;
    }
    private JMenuItem jMenuItem1 = null, jMenuItem2 = null;
    private JPopupMenu jPopupMenu = null; // @jve:decl-index=0:visual-constraint="633,166"

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

    private JPopupMenu getJPopupMenu() {
        if (jPopupMenu == null) {
            jPopupMenu = new JPopupMenu();
            jPopupMenu.setSize(new Dimension(61, 100));
            jPopupMenu.add(getJMenuItem2());
            jPopupMenu.add(getJMenuItem1());

        }
        return jPopupMenu;
    }

    private JMenuItem getJMenuItem2() {
        if (jMenuItem2 == null) {
            jMenuItem2 = new JMenuItem();
            jMenuItem2.setText("编辑");
            jMenuItem2.addActionListener(new java.awt.event.ActionListener() {

                public void actionPerformed(java.awt.event.ActionEvent e) {
                    getJDialog_sub().setVisible(true);
//                    insertRow();
                }
            });
        }
        return jMenuItem2;
    }

    /**
     * This method initializes jContentPane
     *
     * @return javax.swing.JPanel
     */
    private JPanel getJContentPane() {
        if (jContentPane == null) {
            jContentPane = new JPanel();
            jContentPane.setLayout(null);
            jContentPane.add(getJScrollPane(), null);
        }
        return jContentPane;
    }

    /**
     * This method initializes jScrollPane
     *
     * @return javax.swing.JScrollPane
     */
    private JScrollPane getJScrollPane() {
        if (jScrollPane == null) {
            jScrollPane = new JScrollPane();
            jScrollPane.setSize(new Dimension(300, 200));
            jScrollPane.setLocation(new Point(15, 15));
            jScrollPane.setViewportView(getjTable_item());
        }
        return jScrollPane;
    }
    private String[] col = {"编号", "名称", "价格"};
    private DefaultTableModel dtm = new DefaultTableModel(col, 0);

    /**
     * This method initializes jTable_item
     *
     * @return javax.swing.JTable
     */
    private JTable getjTable_item() {
        if (jTable_item == null) {
            jTable_item = new JTable();
            dtm = new javax.swing.table.DefaultTableModel(col, 0) {

                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            jTable_item.setModel(dtm);
            jTable_item.addMouseListener(this);
            String[] s = {"", "", ""};
            dtm.addRow(s);
        }
        return jTable_item;
    }

    /**
     * This method initializes jDialog_sub
     *
     * @return javax.swing.JDialog
     */
    private JDialog getJDialog_sub() {
        if (jDialog_sub == null) {
            jDialog_sub = new JDialog(this);
            jDialog_sub.setSize(new Dimension(272, 153));
            Dimension screenSize =
                Toolkit.getDefaultToolkit().getScreenSize();
            Dimension labelSize = jDialog_sub.getSize();
            jDialog_sub.setLocation(screenSize.width / 2 - (labelSize.width / 2),
                screenSize.height / 2 - (labelSize.height / 2));//设置位置屏幕中部
            jDialog_sub.setTitle("商品");
            jDialog_sub.setContentPane(getJContentPane1());
        }
        return jDialog_sub;
    }

    /**
     * This method initializes jContentPane1
     *
     * @return javax.swing.JPanel
     */
    private JPanel getJContentPane1() {
        if (jContentPane1 == null) {
            jLabel = new JLabel();
            jLabel.setText("装备");
            jLabel.setLocation(new Point(15, 15));
            jLabel.setSize(new Dimension(40, 20));
            jContentPane1 = new JPanel();
            jContentPane1.setLayout(null);
            jContentPane1.add(jLabel, null);
            jContentPane1.add(getJComboBox_item(), null);
            jContentPane1.add(getJButton_confirm(), null);
            jContentPane1.add(getJButton_cancel(), null);
        }
        return jContentPane1;
    }

    /**
     * This method initializes jComboBox_item
     *
     * @return javax.swing.JComboBox
     */
    private JComboBox getJComboBox_item() {
        if (jComboBox_item == null) {
            jComboBox_item = new JComboBox();
            jComboBox_item.setLocation(new Point(60, 15));
            jComboBox_item.setSize(new Dimension(180, 20));
            int m = Project.getItemList().size();
            for (int i = 0; i < m; i++) {
                jComboBox_item.addItem(Project.getItemList().itemAt(i).getName());
            }
        }
        return jComboBox_item;
    }

    /**
     * This method initializes jButton_confirm
     *
     * @return javax.swing.JButton
     */
    private JButton getJButton_confirm() {
        if (jButton_confirm == null) {
            jButton_confirm = new JButton();
            jButton_confirm.setText("确定");
            jButton_confirm.setSize(new Dimension(90, 30));
            jButton_confirm.setLocation(new Point(30, 60));
            jButton_confirm.addActionListener(new java.awt.event.ActionListener() {

                @Override
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    String[] s = {"" + Project.getItemList().itemAt(jComboBox_item.getSelectedIndex()).getIndex(), jComboBox_item.getSelectedItem().toString(), "" + Project.getItemList().itemAt(jComboBox_item.getSelectedIndex()).getPrice()};
                    dtm.insertRow(curRow, s);
                    jDialog_sub.setVisible(false);
                }
            });
        }
        return jButton_confirm;
    }

    /**
     * This method initializes jButton_cancel
     *
     * @return javax.swing.JButton
     */
    private JButton getJButton_cancel() {
        if (jButton_cancel == null) {
            jButton_cancel = new JButton();
            jButton_cancel.setText("取消");
            jButton_cancel.setSize(new Dimension(90, 30));
            jButton_cancel.setLocation(new Point(140, 60));
            jButton_cancel.addActionListener(new java.awt.event.ActionListener() {

                @Override
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    jDialog_sub.setVisible(false);
                }
            });
        }
        return jButton_cancel;
    }
}  //  @jve:decl-index=0:visual-constraint="10,10"

