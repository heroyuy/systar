package frame.dm;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.*;
import javax.swing.*;
import java.io.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.event.*;
import model.*;
import java.util.Vector;

public class GroupTab extends JPanel implements MouseListener, CaretListener {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private JLabel jLabel = null;
    private JScrollPane jScrollPane = null;
    private JScrollPane jScrollPane1 = null;
    private JTable jTable = null;
    private JTable jTable1 = null;
    private JLabel jLabel1 = null;
    private JLabel jLabel2 = null;
    private JLabel jLabel3 = null;
    private JLabel jLabel4 = null;
    private JComboBox jComboBox = null;
    private JComboBox jComboBox1 = null;
    private JComboBox jComboBox2 = null;
    private JComboBox jComboBox3 = null;
    private DefaultTableModel enemyTroopTM = null; // 定义一个表的模板
    private DefaultTableModel goodsTM = null; // 定义一个表的模板
    private String[] col = {"编号", "名称"};
    private JTextField jtf = null;

    public GroupTab() {
        // TODO 自动生成构造函数存根
        enemyTroopTM = new DefaultTableModel(col, 0);
        goodsTM = new DefaultTableModel(col, 0);
        initialize();

    }

    public void caretUpdate(CaretEvent e) {
        // TODO 监听名称框的字符变化
        if (curRow <= 0) {
            curRow = 0;
        }
        enemyTroopTM.setValueAt(jtf.getText(), curRow, 1);
    }

    /**
     * This method initializes this
     *
     */
    private void initialize() {
        jtf = new JTextField();
        jtf.setBounds(250, 20, 90, 25);
        jtf.addCaretListener(this);
        JLabel jlb = new JLabel("名称");
        jlb.setBounds(200, 20, 40, 20);
        jLabel4 = new JLabel();
        jLabel4.setText("位置4：");
        jLabel4.setSize(new Dimension(50, 20));
        jLabel4.setLocation(new Point(360, 100));
        jLabel3 = new JLabel();
        jLabel3.setText("位置3：");
        jLabel3.setSize(new Dimension(50, 20));
        jLabel3.setLocation(new Point(200, 100));
        jLabel2 = new JLabel();
        jLabel2.setText("位置2：");
        jLabel2.setSize(new Dimension(50, 20));
        jLabel2.setLocation(new Point(360, 60));
        jLabel1 = new JLabel();
        jLabel1.setText("位置1：");
        jLabel1.setLocation(new Point(200, 60));
        jLabel1.setSize(new Dimension(50, 20));
        jLabel = new JLabel();
        jLabel.setText("队伍列表");
        jLabel.setSize(new Dimension(160, 20));
        jLabel.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel.setLocation(new Point(10, 10));
        JLabel jLabel5 = new JLabel("物品列表");
        jLabel5.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel5.setBounds(380, 180, 150, 20);
        this.setLayout(null);
        this.setSize(new Dimension(566, 320));
        this.add(jtf, null);
        this.add(jlb, null);
        this.add(jLabel, null);
        this.add(getJScrollPane(), null);
        this.add(getJScrollPane1(), null);
        this.add(jLabel1, null);
        this.add(jLabel2, null);
        this.add(jLabel3, null);
        this.add(jLabel4, null);
        this.add(jLabel5, null);
        this.add(getJComboBox(), null);
        this.add(getJComboBox1(), null);
        this.add(getJComboBox2(), null);
        this.add(getJComboBox3(), null);

    }

    /**
     * This method initializes jScrollPane
     *
     * @return javax.swing.JScrollPane
     */
    private JScrollPane getJScrollPane() {
        if (jScrollPane == null) {
            jScrollPane = new JScrollPane();
            jScrollPane.setSize(new Dimension(160, 270));
            jScrollPane.setLocation(new Point(10, 40));
            jScrollPane.setViewportView(getJTable());
        }
        return jScrollPane;
    }

    private JScrollPane getJScrollPane1() {
        if (jScrollPane1 == null) {
            jScrollPane1 = new JScrollPane();
            jScrollPane1.setSize(new Dimension(150, 100));
            jScrollPane1.setLocation(new Point(380, 210));
            jScrollPane1.setViewportView(getJTable1());
        }
        return jScrollPane1;
    }
    private int index = 0;

    private void addRow() {
        EnemyTroop enemyTroop = new EnemyTroop();
        enemyTroop.index = index++;
        Project.getEnemyTroopList().add(enemyTroop);
        String[] rowData = {enemyTroop.index + "", enemyTroop.name};
        enemyTroopTM.addRow(rowData);
//        curRow = enemyTroopTM.getRowCount() - 1;
    }
    public int curIndex = 0;
    private int curRow = 0;

    private void delRow() {
        int num = jTable.getSelectedRow();
        if (num != -1) {
            Project.getEnemyTroopList().delEnemyTroop(num);
            enemyTroopTM.removeRow(num);
            if (enemyTroopTM.getRowCount() == 0) {
                index = 0;
                addRow();
            }
            curRow = 0;
            curIndex = Integer.parseInt(((String) enemyTroopTM.getValueAt(curRow, 0)));
            switchEnemyTroop(curIndex);
            index--;
        }
    }
    private JMenuItem jMenuItem;

    private JMenuItem getJMenuItem() {
        if (jMenuItem == null) {
            jMenuItem = new JMenuItem();
            jMenuItem.setText("新建");
            jMenuItem.addActionListener(new java.awt.event.ActionListener() {

                public void actionPerformed(java.awt.event.ActionEvent e) {
                    addRow();
                }
            });
        }
        return jMenuItem;
    }
    private JMenuItem jMenuItem1;

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
    private JPopupMenu jPopupMenu;

    private JPopupMenu getJPopupMenu() {
        if (jPopupMenu == null) {
            jPopupMenu = new JPopupMenu();
            jPopupMenu.setSize(new Dimension(61, 100));
            jPopupMenu.add(getJMenuItem());
            jPopupMenu.add(getJMenuItem1());
        }
        return jPopupMenu;
    }

    /**
     * This method initializes jTable
     *
     * @return javax.swing.JTable
     */
    private JTable getJTable() {
        if (jTable == null) {
            jTable = new JTable();
            jTable.setModel(enemyTroopTM);
            jTable.addMouseListener(this);
        }
        return jTable;
    }

    private JTable getJTable1() {
        if (jTable1 == null) {
            jTable1 = new JTable();
            jTable1.setModel(goodsTM);
            goodsTM = new javax.swing.table.DefaultTableModel(col, 0) {

                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            jTable1.setModel(goodsTM);
//            String[] s = {"", ""};
//            goodsTM.addRow(s);
            jTable1.addMouseListener(this);
        }
        return jTable1;
    }

    public void updateList() {
        int num = enemyTroopTM.getRowCount();
        for (int i = 0; i < num; i++) {
            enemyTroopTM.removeRow(0);
        }
        int nn = goodsTM.getRowCount();
        for (int i = 0; i < nn; i++) {
            goodsTM.removeRow(0);
        }
//        jTable1.removeAll();
//        jComboBox.removeAllItems();
//        jComboBox1.removeAllItems();
//        jComboBox2.removeAllItems();
//        jComboBox3.removeAllItems();
        if (Project.getEnemyTroopList().size() == 0) {
            //
            addRow();
        } else {
            index = Project.getEnemyTroopList().getMaxIndex() + 1;

            // 重新显示数据
            num = Project.getEnemyTroopList().size();
            EnemyTroop enemyTroop = null;
            for (int i = 0; i < num; i++) {
                enemyTroop = Project.getEnemyTroopList().EnemyTroopAt(i);
                String[] rowData = {enemyTroop.index + "", enemyTroop.name};
                enemyTroopTM.addRow(rowData);
            }
        }
        switchEnemyTroop(curIndex);
    }

    public void mouseClicked(MouseEvent e) {
        // TODO 自动生成方法存根
        if (e.getSource().equals(jTable)) {
            if (e.getButton() == MouseEvent.BUTTON3) {
                // 右键弹出菜单
                getJPopupMenu();
                jPopupMenu.show(e.getComponent(), e.getX(), e.getY());
            } else if (e.getButton() == MouseEvent.BUTTON1) {
                // 左键进行物品切换

                saveEnemyTroop(curIndex);
                curIndex = Integer.parseInt((String) enemyTroopTM.getValueAt(jTable.getSelectedRow(), 0));
                switchEnemyTroop(curIndex);

            }
        }
        if (e.getSource().equals(jTable1)) {
            if (e.getButton() == MouseEvent.BUTTON3) {
                // 右键弹出菜单
                getJPopupMenu1();
                jPopupMenu1.show(e.getComponent(), e.getX(), e.getY());
            } else if (e.getButton() == MouseEvent.BUTTON1) {
                curItemRow = jTable1.getSelectedRow();
            }
        }

    }
    private JMenuItem jMenuItem2;

    private JMenuItem getJMenuItem2() {
        if (jMenuItem2 == null) {
            jMenuItem2 = new JMenuItem();
            jMenuItem2.setText("插入");
            jMenuItem2.addActionListener(new java.awt.event.ActionListener() {

                public void actionPerformed(java.awt.event.ActionEvent e) {
                    getInsertDialog();
                    InsertDialog.setVisible(true);
                }
            });
        }
        return jMenuItem2;
    }
    private JMenuItem jMenuItem3;

    private JMenuItem getJMenuItem3() {
        if (jMenuItem3 == null) {
            jMenuItem3 = new JMenuItem();
            jMenuItem3.setText("删除");
            jMenuItem3.addActionListener(new java.awt.event.ActionListener() {

                public void actionPerformed(java.awt.event.ActionEvent e) {
                    delItemRow();
                }
            });
        }
        return jMenuItem3;
    }

    private void delItemRow() {
        int num = getJTable1().getSelectedRow();
        System.out.println("select: " + num);
        if (num != -1) {
            goodsTM.removeRow(num);
        }
        curItemRow = 0;
    }
    private int curItemRow = 0;
    private JDialog InsertDialog;

    private JDialog getInsertDialog() {
        if (InsertDialog == null) {
            InsertDialog = new JDialog();
            InsertDialog.setSize(220, 120);
            Dimension screenSize =
                Toolkit.getDefaultToolkit().getScreenSize();
            Dimension labelSize = InsertDialog.getSize();
            InsertDialog.setLocation(screenSize.width / 2 - (labelSize.width / 2),
                screenSize.height / 2 - (labelSize.height / 2));//设置位置屏幕中部
            InsertDialog.setTitle("物品");
            InsertDialog.setModal(true);
            InsertDialog.setResizable(false);
            InsertDialog.setContentPane(getInsertPanel());
        }
        return InsertDialog;
    }
    private JPanel insertPanel;

    private JPanel getInsertPanel() {
        if (insertPanel == null) {
            insertPanel = new JPanel();
            insertPanel.setLayout(null);
            JLabel jrp1 = new JLabel("物品");
            jrp1.setBounds(10, 10, 70, 20);
            jcb1 = new JComboBox();
            jcb1.setBounds(80, 10, 120, 20);
            int n = Project.getItemList().size();
            for (int i = 0; i < n; i++) {
                jcb1.addItem(Project.getItemList().itemAt(i).getName());
            }
            insertPanel.add(jrp1, null);
            insertPanel.add(jcb1, null);
            insertPanel.add(getEventOk2(), null);
            insertPanel.add(getEventCancle2(), null);
        }
        return insertPanel;
    }
    private JComboBox jcb1;
    private JButton jbEventOk2, jbEventCancle2;

    private JButton getEventOk2() {
        if (jbEventOk2 == null) {
            jbEventOk2 = new JButton();
            jbEventOk2.setText("确定");
            jbEventOk2.setBounds(30, 60, 60, 20);

            jbEventOk2.addActionListener(new java.awt.event.ActionListener() {

                @Override
                public void actionPerformed(java.awt.event.ActionEvent e) {

                    String[] s = {"" + Project.getItemList().itemAt(jcb1.getSelectedIndex()).getIndex(), jcb1.getSelectedItem().toString()};
                    goodsTM.insertRow(curItemRow, s);
                    InsertDialog.setVisible(false);

                }
            });
        }
        return jbEventOk2;
    }

    private JButton getEventCancle2() {
        if (jbEventCancle2 == null) {
            jbEventCancle2 = new JButton();
            jbEventCancle2.setText("取消");
            jbEventCancle2.setBounds(130, 60, 60, 20);

            jbEventCancle2.addActionListener(new java.awt.event.ActionListener() {

                @Override
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    InsertDialog.setVisible(false);
                }
            });

        }
        return jbEventCancle2;
    }
    private JPopupMenu jPopupMenu1;

    private JPopupMenu getJPopupMenu1() {
        if (jPopupMenu1 == null) {
            jPopupMenu1 = new JPopupMenu();
            jPopupMenu1.setSize(new Dimension(61, 100));
            jPopupMenu1.add(getJMenuItem2());
            jPopupMenu1.add(getJMenuItem3());
        }
        return jPopupMenu1;
    }

    private void switchEnemyTroop(int curIndex) {
        EnemyTroop enemyTroop = Project.getEnemyTroopList().getEnemyTroop(curIndex);

        if (enemyTroop != null) {
            jtf.setText(enemyTroop.name);
            for (int i = 0; i < goodsTM.getRowCount(); i++) {
                goodsTM.removeRow(0);
            }
            if (enemyTroop.itemList != null) {
                if (enemyTroop.itemList.length != 0) {
                    for (int i = 0; i < goodsTM.getRowCount(); i++) {
                        goodsTM.removeRow(0);
                    }
                    for (int i = 0; i < enemyTroop.itemList.length; i++) {
//                        System.out.println("cut 1");
                        String[] data = {enemyTroop.itemList[i].getIndex() + "", enemyTroop.itemList[i].getName()};
                        goodsTM.addRow(data);
                    }
                } else {
                    for (int i = 0; i < goodsTM.getRowCount(); i++) {
                        goodsTM.removeRow(0);
                    }
//                    System.out.println("cut 2");
                    String[] rowData = {"", ""};
                    goodsTM.addRow(rowData);
                }

            } else {
                for (int i = 0; i < goodsTM.getRowCount(); i++) {
                    goodsTM.removeRow(0);
                }
//                System.out.println("cut 3");
                String[] rowData = {"", ""};
                goodsTM.addRow(rowData);
            }


//            jComboBox1.removeAllItems();
//            jComboBox2.removeAllItems();
//            jComboBox3.removeAllItems();
            jComboBox.removeAllItems();
            jComboBox.addItem("无");
            for (int i = 0; i < Project.getEnemyList().size(); i++) {
                jComboBox.addItem(Project.getEnemyList().enemyAt(i).getName());
            }
            if (enemyTroop.siteA != -1) {
                jComboBox.setSelectedItem(Project.getEnemyList().enemyAt(enemyTroop.siteA).getName());
            } else {
                jComboBox.setSelectedItem("无");
            }

            jComboBox1.removeAllItems();
            jComboBox1.addItem("无");
            for (int i = 0; i < Project.getEnemyList().size(); i++) {
                jComboBox1.addItem(Project.getEnemyList().enemyAt(i).getName());
            }
            if (enemyTroop.siteB != -1) {
                jComboBox1.setSelectedItem(Project.getEnemyList().enemyAt(enemyTroop.siteB).getName());
            } else {
                jComboBox1.setSelectedItem("无");
            }
            jComboBox2.removeAllItems();
            jComboBox2.addItem("无");
            for (int i = 0; i < Project.getEnemyList().size(); i++) {
                jComboBox2.addItem(Project.getEnemyList().enemyAt(i).getName());
            }
            if (enemyTroop.siteC != -1) {
                jComboBox2.setSelectedItem(Project.getEnemyList().enemyAt(enemyTroop.siteC).getName());
            } else {
                jComboBox2.setSelectedItem("无");
            }
            jComboBox3.removeAllItems();
            jComboBox3.addItem("无");
            for (int i = 0; i < Project.getEnemyList().size(); i++) {
                jComboBox3.addItem(Project.getEnemyList().enemyAt(i).getName());
            }
            if (enemyTroop.siteD != -1) {
                jComboBox3.setSelectedItem(Project.getEnemyList().enemyAt(enemyTroop.siteD).getName());
            } else {
                jComboBox3.setSelectedItem("无");
            }
//            if (enemyTroop.siteB != -1) {
////                jComboBox1.removeAllItems();
//                jComboBox1.addItem(Project.getEnemyList().enemyAt(enemyTroop.siteB).getName());
//                for (int i = 0; i < Project.getEnemyList().size(); i++) {
//                    if (enemyTroop.siteB == Project.getEnemyList().enemyAt(i).getIndex()) {
//                        continue;
//                    }
//                    jComboBox1.addItem(Project.getEnemyList().enemyAt(i).getName());
//                }
//            } else {
//                for (int i = 0; i < Project.getEnemyList().size(); i++) {
//                    jComboBox1.addItem(Project.getEnemyList().enemyAt(i).getName());
//                }
//            }
//
//            if (enemyTroop.siteC != -1) {
////                jComboBox2.removeAllItems();
//                jComboBox2.addItem(Project.getEnemyList().enemyAt(enemyTroop.siteC).getName());
//                for (int i = 0; i < Project.getEnemyList().size(); i++) {
//                    if (enemyTroop.siteC == Project.getEnemyList().enemyAt(i).getIndex()) {
//                        continue;
//                    }
//                    jComboBox2.addItem(Project.getEnemyList().enemyAt(i).getName());
//                }
//            } else {
//                for (int i = 0; i < Project.getEnemyList().size(); i++) {
//                    jComboBox2.addItem(Project.getEnemyList().enemyAt(i).getName());
//                }
//            }
//            if (enemyTroop.siteD != -1) {
////                jComboBox3.removeAllItems();
//                jComboBox3.addItem(Project.getEnemyList().enemyAt(enemyTroop.siteD).getName());
//                for (int i = 0; i < Project.getEnemyList().size(); i++) {
//                    if (enemyTroop.siteD == Project.getEnemyList().enemyAt(i).getIndex()) {
//                        continue;
//                    }
//                    jComboBox3.addItem(Project.getEnemyList().enemyAt(i).getName());
//                }
//            } else {
//                for (int i = 0; i < Project.getEnemyList().size(); i++) {
//                    jComboBox3.addItem(Project.getEnemyList().enemyAt(i).getName());
//                }
//            }

        }

    }

    public void saveEnemyTroop(int curIndex) {
        EnemyTroop enemyTroop = Project.getEnemyTroopList().getEnemyTroop(curIndex);
        if (enemyTroop != null) {
            enemyTroop.name = jtf.getText();
            for (int i = 0; i < Project.getEnemyList().size(); i++) {
                if (Project.getEnemyList().enemyAt(i).getName().equals(jComboBox.getSelectedItem().toString())) {
                    enemyTroop.siteA = Project.getEnemyList().enemyAt(i).getIndex();
                }
                if (Project.getEnemyList().enemyAt(i).getName().equals(jComboBox1.getSelectedItem().toString())) {
                    enemyTroop.siteB = Project.getEnemyList().enemyAt(i).getIndex();
                }
                if (Project.getEnemyList().enemyAt(i).getName().equals(jComboBox2.getSelectedItem().toString())) {
                    enemyTroop.siteC = Project.getEnemyList().enemyAt(i).getIndex();
                }
                if (Project.getEnemyList().enemyAt(i).getName().equals(jComboBox3.getSelectedItem().toString())) {
                    enemyTroop.siteD = Project.getEnemyList().enemyAt(i).getIndex();
                }
            }
            if (jComboBox.getSelectedItem().toString().equals("无")) {
                enemyTroop.siteA = -1;
            }
            if (jComboBox1.getSelectedItem().toString().equals("无")) {
                enemyTroop.siteB = -1;
            }
            if (jComboBox2.getSelectedItem().toString().equals("无")) {
                enemyTroop.siteC = -1;
            }
            if (jComboBox3.getSelectedItem().toString().equals("无")) {
                enemyTroop.siteD = -1;
            }
            int n = goodsTM.getRowCount();
//            System.out.println("n " + n);
            int tempN = n;
            for (int i = 0; i < n; i++) {
                if (goodsTM.getValueAt(i, 0).toString().equals("")) {
                    n--;
                }
            }
//            System.out.println("n: " + n);
            if (n != 0) {
                enemyTroop.itemList = new Goods[n];
                int[] tem = new int[n];
                Vector save = new Vector();
                for (int i = 0; i < tempN; i++) {
                    if (!goodsTM.getValueAt(i, 0).toString().equals("")) {
                        save.addElement(goodsTM.getValueAt(i, 0).toString());
                    }
                }
                for (int i = 0; i < n; i++) {
                    tem[i] = Integer.parseInt(save.elementAt(i).toString());
                }
                for (int j = 0; j < n; j++) {
//                    System.out.println("tem " + tem[j]);
                    enemyTroop.itemList[j] = Project.getItemList().getItem(tem[j]);
                }

            }
        }
        curRow = jTable.getSelectedRow();
    }

    public void mouseEntered(MouseEvent e) {
        // TODO 自动生成方法存根
    }

    public void mouseExited(MouseEvent e) {
        // TODO 自动生成方法存根
    }

    public void mousePressed(MouseEvent e) {
        // TODO 自动生成方法存根
    }

    public void mouseReleased(MouseEvent e) {
        // TODO 自动生成方法存根
    }

    /**
     * This method initializes jComboBox
     *
     * @return javax.swing.JComboBox
     */
    private JComboBox getJComboBox() {
        if (jComboBox == null) {
            jComboBox = new JComboBox();
            jComboBox.setLocation(new Point(250, 60));
            jComboBox.setSize(new Dimension(90, 20));
//            for (int i = 0; i < Project.getEnemyList().size(); i++) {
//                jComboBox.addItem(Project.getEnemyList().enemyAt(i).getName());
//            }
        }
        return jComboBox;
    }

    /**
     * This method initializes jComboBox1
     *
     * @return javax.swing.JComboBox
     */
    private JComboBox getJComboBox1() {
        if (jComboBox1 == null) {
            jComboBox1 = new JComboBox();
            jComboBox1.setLocation(new Point(410, 60));
            jComboBox1.setSize(new Dimension(90, 20));
        }
        return jComboBox1;
    }

    /**
     * This method initializes jComboBox2
     *
     * @return javax.swing.JComboBox
     */
    private JComboBox getJComboBox2() {
        if (jComboBox2 == null) {
            jComboBox2 = new JComboBox();
            jComboBox2.setSize(new Dimension(90, 20));
            jComboBox2.setLocation(new Point(250, 100));
        }
        return jComboBox2;
    }

    /**
     * This method initializes jComboBox3
     *
     * @return javax.swing.JComboBox
     */
    private JComboBox getJComboBox3() {
        if (jComboBox3 == null) {
            jComboBox3 = new JComboBox();
            jComboBox3.setLocation(new Point(410, 100));
            jComboBox3.setSize(new Dimension(90, 20));

        }
        return jComboBox3;
    }
}  //  @jve:decl-index=0:visual-constraint="10,10"

