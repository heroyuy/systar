package frame.dm;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.*;
import javax.swing.*;
import java.io.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.event.*;
import java.util.Vector;
import model.*;

public class EnemyTab extends JPanel implements MouseListener, CaretListener {

    private static final long serialVersionUID = 1L;
    private JLabel jLabel2 = null;
    private JButton jButton = null;
    private JLabel jLabel3 = null;
//    private JDialog jDialog = null; // @jve:decl-index=0:visual-constraint="566,6"
//    private JPanel jContentPane = null;
//    private JScrollPane jScrollPane1 = null;
//    private JTable jTable = null;
//    private JLabel jLabel18 = null;
    private String[] col = {"图片列表"};
    private String[] col1 = {"编号", "名称"};
//    private DefaultTableModel characterTM = null; // 定义一个表的模板
    private DefaultTableModel battlerTM = null; // 定义一个表的模板
//    private DefaultTableModel skillTM = null; // 定义一个表的模板
    private DefaultTableModel skillTM = null; // 定义一个表的模板
    public DefaultTableModel enemyTM = null; // 定义一个表的模板
//    private JButton jButton2 = null;
//    private JButton jButton3 = null;
    private JDialog jDialog1 = null; // @jve:decl-index=0:visual-constraint="568,366"
    private JPanel jContentPane1 = null;
    private JScrollPane jScrollPane2 = null;
    private JTable jTable1 = null;
    private JLabel jLabel19 = null;
    private JButton jButton4 = null;
    private JButton jButton5 = null;
    private JLabel jLabel = null;
    private JScrollPane jScrollPane = null;
    private JTable jTable2 = null;
    private JLabel jLabel1 = null;
    private JLabel jLabel6 = null;
    private JLabel jLabel7 = null;
    private JLabel jLabel8 = null;
    private JLabel jLabel9 = null;
    private JLabel jLabel10 = null;
    private JLabel jLabel4 = null;
    private JLabel jLabel5 = null;
    private JLabel jLabel11 = null;
    private JLabel jLabel12 = null;
    private JTextField jTextField = null;
    private JTextField jTextField1 = null;
    private JTextField jTextField2 = null;
    private JTextField jTextField3 = null;
    private JTextField jTextField4 = null;
    private JTextField jTextField5 = null;
    private JTextField jTextField6 = null;
    private JTextField jTextField7 = null;
    private JTextField jTextField8 = null;
    private JTextField jTextField9 = null;
    private JTextField jTextField10 = null;
//    private JLabel jLabel13 = null;
    private JLabel jLabel14 = null;
//    private JScrollPane jScrollPane3 = null;
//    private JTable jTable3 = null;
    private JScrollPane jScrollPane4 = null;
    private JTable jTable4 = null;
    private int index = 0;// 自动生成的物品编号
    public int curIndex = 0;// 当前编号
    private int curRow = 0;// 当前行号

    /**
     * This method initializes
     *
     */
    public EnemyTab() {
        super();
        skillTM = new DefaultTableModel(col1, 0) {

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        enemyTM = new DefaultTableModel(col1, 0) {

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        initialize();
        loadBattlers();
    }

    private void addRow() {
        Enemy enemy = new Enemy();
        enemy.setIndex(index++);
        Project.getEnemyList().add(enemy);
        String[] rowData = {enemy.getIndex() + "", enemy.getName()};
        enemyTM.addRow(rowData);
//        curRow = enemyTM.getRowCount() - 1;
    }

    public void caretUpdate(CaretEvent e) {
        // TODO 监听名称框的字符变化
        if (curRow <= 0) {
            curRow = 0;
        }
        enemyTM.setValueAt(jTextField.getText(), curRow, 1);
    }

    private void delRow() {
        int num = jTable2.getSelectedRow();
        if (num != -1) {
            Project.getEnemyList().delEnemy(num);
            enemyTM.removeRow(num);
            if (enemyTM.getRowCount() == 0) {
                index = 0;
                addRow();
            }
            curRow = 0;
            curIndex = Integer.parseInt(((String) enemyTM.getValueAt(curRow, 0)));
            switchEnemy(curIndex);
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
                    delSKillRow();
                }
            });
        }
        return jMenuItem3;
    }

    private void delSKillRow() {
        int num = getJTable4().getSelectedRow();
        System.out.println("select: " + num);
        if (num != -1) {
            skillTM.removeRow(num);
        }
        curSkillRow = 0;
    }
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
            InsertDialog.setTitle("技能");
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
            JLabel jrp1 = new JLabel("技能");
            jrp1.setBounds(10, 10, 70, 20);
            jcb1 = new JComboBox();
            jcb1.setBounds(80, 10, 120, 20);
            int n = Project.getSkillList().size();
            for (int i = 0; i < n; i++) {
                jcb1.addItem(Project.getSkillList().skillAt(i).getName());
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

                    String[] s = {"" + Project.getSkillList().skillAt(jcb1.getSelectedIndex()).getIndex(), jcb1.getSelectedItem().toString()};
                    skillTM.insertRow(curSkillRow, s);
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

    /**
     * This method initializes jButton
     *
     * @return javax.swing.JButton
     */
    private JButton getJButton() {
        if (jButton == null) {
            jButton = new JButton();
            jButton.setText("设置");
            jButton.setLocation(new Point(460, 20));
            jButton.setSize(new Dimension(60, 20));
            jButton.addActionListener(new java.awt.event.ActionListener() {

                public void actionPerformed(java.awt.event.ActionEvent e) {
                    getJDialog1();
                    jDialog1.setVisible(true);
                }
            });
        }
        return jButton;
    }

    /**
     * This method initializes jButton4
     *
     * @return javax.swing.JButton
     */
    private JButton getJButton4() {
        if (jButton4 == null) {
            jButton4 = new JButton();
            jButton4.setText("确定");
            jButton4.setSize(new Dimension(60, 20));
            jButton4.setLocation(new Point(200, 260));
            jButton4.addActionListener(new java.awt.event.ActionListener() {

                public void actionPerformed(java.awt.event.ActionEvent e) {
                    String name = (String) battlerTM.getValueAt(jTable1.getSelectedRow(), 0);
                    setBattlerImg(name);
                }
            });
        }
        return jButton4;
    }

    /**
     * This method initializes jButton5
     *
     * @return javax.swing.JButton
     */
    private JButton getJButton5() {
        if (jButton5 == null) {
            jButton5 = new JButton();
            jButton5.setPreferredSize(new Dimension(60, 20));
            jButton5.setLocation(new Point(270, 260));
            jButton5.setSize(new Dimension(60, 20));
            jButton5.setText("取消");
            jButton5.addActionListener(new java.awt.event.ActionListener() {

                public void actionPerformed(java.awt.event.ActionEvent e) {
                    jDialog1.setVisible(false);
                }
            });
        }
        return jButton5;
    }

    /**
     * This method initializes jContentPane1
     *
     * @return javax.swing.JPanel
     */
    private JPanel getJContentPane1() {
        if (jContentPane1 == null) {
            jLabel19 = new JLabel();
            jLabel19.setText("");
            jLabel19.setLocation(new Point(150, 10));
            jLabel19.setBorder(BorderFactory.createLineBorder(Color.lightGray,
                2));
            jLabel19.setSize(new Dimension(200, 240));
            jContentPane1 = new JPanel();
            jContentPane1.setLayout(null);
            jContentPane1.add(getJScrollPane2(), null);
            jContentPane1.add(jLabel19, null);
            jContentPane1.add(getJButton4(), null);
            jContentPane1.add(getJButton5(), null);
        }
        return jContentPane1;
    }

    /**
     * This method initializes jDialog1
     *
     * @return javax.swing.JDialog
     */
    private JDialog getJDialog1() {
        if (jDialog1 == null) {
            jDialog1 = new JDialog();
            jDialog1.setSize(new Dimension(370, 320));
            Dimension screenSize =
                Toolkit.getDefaultToolkit().getScreenSize();
            Dimension labelSize = jDialog1.getSize();
            jDialog1.setLocation(screenSize.width / 2 - (labelSize.width / 2),
                screenSize.height / 2 - (labelSize.height / 2));//设置位置屏幕中部
            jDialog1.setTitle("战斗图设置");
            jDialog1.setModal(true);
            jDialog1.setContentPane(getJContentPane1());
        }
        return jDialog1;
    }

    /**
     * This method initializes jScrollPane2
     *
     * @return javax.swing.JScrollPane
     */
    private JScrollPane getJScrollPane2() {
        if (jScrollPane2 == null) {
            jScrollPane2 = new JScrollPane();
            jScrollPane2.setLocation(new Point(10, 10));
            jScrollPane2.setSize(new Dimension(120, 240));
            jScrollPane2.setViewportView(getJTable1());
        }
        return jScrollPane2;
    }

    /**
     * This method initializes jTable1
     *
     * @return javax.swing.JTable
     */
    private JTable getJTable1() {
        if (jTable1 == null) {
            jTable1 = new JTable();
            jTable1.setModel(battlerTM);
            jTable1.addMouseListener(this);
        }
        return jTable1;
    }

    /**
     * This method initializes this
     *
     */
    private void initialize() {
        jLabel14 = new JLabel();
        jLabel14.setText("技能列表");
        jLabel14.setSize(new Dimension(150, 20));
        jLabel14.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel14.setLocation(new Point(380, 180));
//        jLabel13 = new JLabel();
//        jLabel13.setText("技能列表");
//        jLabel13.setLocation(new Point(200, 180));
//        jLabel13.setHorizontalAlignment(SwingConstants.CENTER);
//        jLabel13.setSize(new Dimension(150, 20));
        jLabel12 = new JLabel();
        jLabel12.setText("攻击：");
        jLabel12.setSize(new Dimension(55, 20));
        jLabel12.setLocation(new Point(290, 70));
        jLabel11 = new JLabel();
        jLabel11.setText("智力：");
        jLabel11.setSize(new Dimension(40, 20));
        jLabel11.setLocation(new Point(390, 160));
        jLabel5 = new JLabel();
        jLabel5.setText("敏捷：");
        jLabel5.setSize(new Dimension(40, 20));
        jLabel5.setLocation(new Point(290, 160));
        jLabel4 = new JLabel();
        jLabel4.setText("力量：");
        jLabel4.setSize(new Dimension(40, 20));
        jLabel4.setLocation(new Point(200, 160));
        jLabel10 = new JLabel();
        jLabel10.setText("经验：");
        jLabel10.setSize(new Dimension(40, 20));
        jLabel10.setLocation(new Point(290, 130));
        JLabel jLabel16 = new JLabel();
        jLabel16.setText("防御：");
        jLabel16.setSize(new Dimension(40, 20));
        jLabel16.setLocation(new Point(390, 130));
        jLabel9 = new JLabel();
        jLabel9.setText("金钱：");
        jLabel9.setSize(new Dimension(40, 20));
        jLabel9.setLocation(new Point(200, 130));
        jLabel8 = new JLabel();
        jLabel8.setText("SP：");
        jLabel8.setSize(new Dimension(40, 20));
        jLabel8.setLocation(new Point(290, 100));
        jLabel7 = new JLabel();
        jLabel7.setText("HP：");
        jLabel7.setSize(new Dimension(40, 20));
        jLabel7.setLocation(new Point(200, 100));
        jLabel6 = new JLabel();
        jLabel6.setText("等级：");
        jLabel6.setSize(new Dimension(40, 20));
        jLabel6.setLocation(new Point(200, 70));
        jLabel1 = new JLabel();
        jLabel1.setText("名称：");
        jLabel1.setSize(new Dimension(40, 20));
        jLabel1.setLocation(new Point(200, 10));
        jLabel = new JLabel();
        jLabel.setText("介绍");
        jLabel.setLocation(new Point(200, 40));
        jLabel.setSize(new Dimension(40, 20));
        jLabel3 = new JLabel();
        jLabel3.setText("");
        jLabel3.setLocation(new Point(400, 50));
        jLabel3.setBorder(BorderFactory.createLineBorder(Color.lightGray, 2));
        jLabel3.setSize(new Dimension(120, 60));
        jLabel2 = new JLabel();
        jLabel2.setText("战斗图：");
        jLabel2.setLocation(new Point(400, 20));
        jLabel2.setSize(new Dimension(60, 20));
        this.setLayout(null);
        this.setSize(new Dimension(544, 326));
        this.add(jLabel2, null);
        this.add(getJButton(), null);
        this.add(jLabel3, null);
        this.add(jLabel, null);
        this.add(getJScrollPane(), null);
        this.add(jLabel1, null);
        this.add(jLabel6, null);
        this.add(jLabel7, null);
        this.add(jLabel8, null);
        this.add(jLabel9, null);
        this.add(jLabel10, null);
        this.add(jLabel4, null);
        this.add(jLabel5, null);
        this.add(jLabel11, null);
        this.add(jLabel12, null);
        this.add(getJTextField(), null);
        this.add(getJTextField1(), null);
        this.add(getJTextField2(), null);
        this.add(getJTextField3(), null);
        this.add(getJTextField4(), null);
        this.add(getJTextField5(), null);
        this.add(getJTextField6(), null);
        this.add(getJTextField7(), null);
        this.add(getJTextField8(), null);
        this.add(getJTextField9(), null);
        this.add(getJTextField10(), null);
        this.add(getJTextField16(), null);
//        this.add(jLabel13, null);
        this.add(jLabel14, null);
        this.add(jLabel16, null);
//        this.add(getJScrollPane3(), null);
        this.add(getJScrollPane4(), null);
    }

    private String[] listFileName(String path) {
        File f = new File(Project.getProjectPath() + "\\" + path);
        String[] s = f.list();
        return s;
    }

    private void loadBattlers() {
        battlerTM = new DefaultTableModel(col, 0); // 定义一个表的模板

        String[] str = listFileName("image/battler");
        for (int i = 0; i < str.length; i++) {
            String[] tcol = {str[i]};
            battlerTM.addRow(tcol);
        }
    }

    private boolean checkData() {
        boolean judge = true;
        if (jTextField1.getText().trim().equals("")) {
            jTextField1.setText(0 + "");
        }
        if (jTextField2.getText().trim().equals("")) {
            jTextField2.setText(0 + "");
        }
        if (jTextField3.getText().trim().equals("")) {
            jTextField3.setText(0 + "");
        }
        if (jTextField4.getText().trim().equals("")) {
            jTextField4.setText(0 + "");
        }
        if (jTextField5.getText().trim().equals("")) {
            jTextField5.setText(0 + "");
        }
        if (jTextField6.getText().trim().equals("")) {
            jTextField6.setText(0 + "");
        }
        if (jTextField7.getText().trim().equals("")) {
            jTextField7.setText(0 + "");
        }
        if (jTextField8.getText().trim().equals("")) {
            jTextField8.setText(0 + "");
        }
        if (jTextField9.getText().trim().equals("")) {
            jTextField9.setText(0 + "");
        }
        if (jTextField16.getText().trim().equals("")) {
            jTextField16.setText(0 + "");
        }

        try {
            Integer.parseInt(jTextField1.getText());
            Integer.parseInt(jTextField2.getText());
            Integer.parseInt(jTextField3.getText());
            Integer.parseInt(jTextField4.getText());
            Integer.parseInt(jTextField5.getText());
            Integer.parseInt(jTextField6.getText());
            Integer.parseInt(jTextField7.getText());
            Integer.parseInt(jTextField8.getText());
            Integer.parseInt(jTextField9.getText());
            Integer.parseInt(jTextField16.getText());
        } catch (Exception e) {
            judge = false;
        }
        return judge;

    }

    public void mouseClicked(MouseEvent e) {
        // TODO 自动生成方法存根
        if (e.getSource().equals(jTable2)) {
            if (e.getButton() == MouseEvent.BUTTON3) {
                // 右键弹出菜单
                getJPopupMenu();
                jPopupMenu.show(e.getComponent(), e.getX(), e.getY());
            } else if (e.getButton() == MouseEvent.BUTTON1) {
                // 左键进行物品切换
                if (checkData()) {
                    saveEnemy(curIndex);
                    curIndex = Integer.parseInt((String) enemyTM.getValueAt(jTable2.getSelectedRow(), 0));
                    switchEnemy(curIndex);
                } else {
                    JOptionPane.showMessageDialog(this, "请输入正确的整数");
                }
            }
        }
        if (e.getSource().equals(jTable4)) {
            if (e.getButton() == MouseEvent.BUTTON3) {
                // 右键弹出菜单
                getJPopupMenu1();
                jPopupMenu1.show(e.getComponent(), e.getX(), e.getY());
            } else {
                curSkillRow = jTable4.getSelectedRow();
            }
        }

    }
    private int curSkillRow = 0;

    private void switchEnemy(int curIndex) {
        Enemy enemy = Project.getEnemyList().getEnemy(curIndex);
        jTextField.setText(enemy.getName());
        jTextField4.setText(enemy.getMaxsp() + "");
        jTextField2.setText(enemy.getAtk() + "");
        jTextField3.setText(enemy.getMaxhp() + "");
        jTextField1.setText(enemy.getLev() + "");
        jTextField5.setText(enemy.getMoney() + "");
        jTextField6.setText(enemy.getExp() + "");
        jTextField7.setText(enemy.getStre() + "");
        jTextField8.setText(enemy.getAgil() + "");
        jTextField9.setText(enemy.getInte() + "");
        jTextField10.setText(enemy.getDescription() + "");
        jTextField16.setText(enemy.getDef() + "");
        battlerImgName = enemy.getBImgName();
        jLabel3.setIcon(new ImageIcon(Project.getProjectPath() + "\\" + "image/battler/" + battlerImgName));
        for (int i = 0; i < skillTM.getRowCount(); i++) {
            skillTM.removeRow(0);
        }
        if (enemy.skillList != null) {
            if (enemy.skillList.length != 0) {
                for (int i = 0; i < skillTM.getRowCount(); i++) {
                    skillTM.removeRow(0);
                }
                for (int i = 0; i < enemy.skillList.length; i++) {
                    String[] data = {enemy.skillList[i].getIndex() + "", enemy.skillList[i].getName()};
                    skillTM.addRow(data);
                }
            } else {
                for (int i = 0; i < skillTM.getRowCount(); i++) {
                    skillTM.removeRow(0);
                }
                String[] rowData = {"", ""};
                skillTM.addRow(rowData);
            }
        } else {
            for (int i = 0; i < skillTM.getRowCount(); i++) {
                skillTM.removeRow(0);
            }
            String[] rowData = {"", ""};
            skillTM.addRow(rowData);
        }
    }

    public void saveEnemy(int curIndex) {
        // TODO 保存技能
        Enemy enemy = Project.getEnemyList().getEnemy(curIndex);
        if (enemy != null) {
            enemy.setName(jTextField.getText());
            enemy.setMaxsp(Integer.parseInt(jTextField4.getText()));
            enemy.setAtk(Integer.parseInt(jTextField2.getText()));
            enemy.setMaxhp(Integer.parseInt(jTextField3.getText()));
            enemy.setLev(Integer.parseInt(jTextField1.getText()));
            enemy.setMoney(Integer.parseInt(jTextField5.getText()));
            enemy.setExp(Integer.parseInt(jTextField6.getText()));
            enemy.setStre(Integer.parseInt(jTextField7.getText()));
            enemy.setAgil(Integer.parseInt(jTextField8.getText()));
            enemy.setInte(Integer.parseInt(jTextField9.getText()));
            enemy.setDef(Integer.parseInt(jTextField16.getText()));
            enemy.setDescription(jTextField10.getText());
            enemy.setBImgName(getbattleImgName());
        }
        curRow = jTable2.getSelectedRow();
        int n = skillTM.getRowCount();
//            System.out.println("n " + n);
        int tempN = n;
        for (int i = 0; i < n; i++) {
            if (skillTM.getValueAt(i, 0).toString().equals("")) {
                n--;
            }
        }
//            System.out.println("n: " + n);
        if (n != 0) {
            enemy.skillList = new Skill[n];
            int[] tem = new int[n];
            Vector save = new Vector();
            for (int i = 0; i < tempN; i++) {
                if (!skillTM.getValueAt(i, 0).toString().equals("")) {
                    save.addElement(skillTM.getValueAt(i, 0).toString());
                }
            }
            for (int i = 0; i < n; i++) {
                tem[i] = Integer.parseInt(save.elementAt(i).toString());
            }
            for (int j = 0; j < n; j++) {
                System.out.println("tem " + tem[j]);
                enemy.skillList[j] = Project.getSkillList().getSkill(tem[j]);
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
        if (e.getSource().equals(jTable1)) {

            int selected = jTable1.getSelectedRow();
            if (selected != -1) {
                String name = (String) battlerTM.getValueAt(jTable1.getSelectedRow(), 0);
                jLabel19.setIcon(new ImageIcon(Project.getProjectPath() + "\\" + "image/battler/" + name));
            }
        }
    }

    public void mouseReleased(MouseEvent e) {
        // TODO 自动生成方法存根
    }
    private String battlerImgName;

    private void setBattlerImg(String name) {
        battlerImgName = name;
        jLabel3.setIcon(new ImageIcon(Project.getProjectPath() + "\\" + "image/battler/" + name));
//        Project.getEnemyList().enemyAt(jTable2.getSelectedRow()).setBImgName(name);
        jDialog1.setVisible(false);
    }

    private String getbattleImgName() {
        return battlerImgName;
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
            jScrollPane.setViewportView(getJTable2());
        }
        return jScrollPane;
    }

    /**
     * This method initializes jTable2
     *
     * @return javax.swing.JTable
     */
    private JTable getJTable2() {
        if (jTable2 == null) {
            jTable2 = new JTable();
            jTable2.setModel(enemyTM);
            jTable2.addMouseListener(this);
        }

        return jTable2;
    }

    public void updateList() {
        int num = enemyTM.getRowCount();
        for (int i = 0; i < num; i++) {
            enemyTM.removeRow(0);
        }

        if (Project.getEnemyList().size() == 0) {
            //
            addRow();
        } else {
            index = Project.getEnemyList().getMaxIndex() + 1;

            // 重新显示数据
            num = Project.getEnemyList().size();
            Enemy enemy = null;
            for (int i = 0; i < num; i++) {
                enemy = Project.getEnemyList().enemyAt(i);
                String[] rowData = {enemy.getIndex() + "", enemy.getName()};
                enemyTM.addRow(rowData);
            }
        }
        switchEnemy(curIndex);
    }

    /**
     * This method initializes jTextField
     *
     * @return javax.swing.JTextField
     */
    private JTextField getJTextField() {
        if (jTextField == null) {
            jTextField = new JTextField();
            jTextField.setLocation(new Point(240, 10));
            jTextField.setSize(new Dimension(140, 20));
            jTextField.addCaretListener(this);
        }
        return jTextField;
    }

    /**
     * This method initializes jTextField1
     *
     * @return javax.swing.JTextField
     */
    private JTextField getJTextField1() {
        if (jTextField1 == null) {
            jTextField1 = new JTextField();
            jTextField1.setLocation(new Point(240, 70));
            jTextField1.setSize(new Dimension(40, 20));
        }
        return jTextField1;
    }

    /**
     * This method initializes jTextField2
     *
     * @return javax.swing.JTextField
     */
    private JTextField getJTextField2() {
        if (jTextField2 == null) {
            jTextField2 = new JTextField();
            jTextField2.setLocation(new Point(340, 70));
            jTextField2.setSize(new Dimension(40, 20));
        }
        return jTextField2;
    }

    /**
     * This method initializes jTextField3
     *
     * @return javax.swing.JTextField
     */
    private JTextField getJTextField3() {
        if (jTextField3 == null) {
            jTextField3 = new JTextField();
            jTextField3.setLocation(new Point(240, 100));
            jTextField3.setSize(new Dimension(40, 20));
        }
        return jTextField3;
    }

    /**
     * This method initializes jTextField4
     *
     * @return javax.swing.JTextField
     */
    private JTextField getJTextField4() {
        if (jTextField4 == null) {
            jTextField4 = new JTextField();
            jTextField4.setLocation(new Point(340, 100));
            jTextField4.setSize(new Dimension(40, 20));
        }
        return jTextField4;
    }

    /**
     * This method initializes jTextField5
     *
     * @return javax.swing.JTextField
     */
    private JTextField getJTextField5() {
        if (jTextField5 == null) {
            jTextField5 = new JTextField();
            jTextField5.setLocation(new Point(240, 130));
            jTextField5.setSize(new Dimension(40, 20));
        }
        return jTextField5;
    }

    /**
     * This method initializes jTextField6
     *
     * @return javax.swing.JTextField
     */
    private JTextField getJTextField6() {
        if (jTextField6 == null) {
            jTextField6 = new JTextField();
            jTextField6.setLocation(new Point(340, 130));
            jTextField6.setSize(new Dimension(40, 20));
        }
        return jTextField6;
    }

    /**
     * This method initializes jTextField7
     *
     * @return javax.swing.JTextField
     */
    private JTextField getJTextField7() {
        if (jTextField7 == null) {
            jTextField7 = new JTextField();
            jTextField7.setLocation(new Point(240, 160));
            jTextField7.setSize(new Dimension(40, 20));
        }
        return jTextField7;
    }

    /**
     * This method initializes jTextField8
     *
     * @return javax.swing.JTextField
     */
    private JTextField getJTextField8() {
        if (jTextField8 == null) {
            jTextField8 = new JTextField();
            jTextField8.setLocation(new Point(340, 160));
            jTextField8.setSize(new Dimension(40, 20));
        }
        return jTextField8;
    }

    /**
     * This method initializes jTextField9
     *
     * @return javax.swing.JTextField
     */
    private JTextField getJTextField9() {
        if (jTextField9 == null) {
            jTextField9 = new JTextField();
            jTextField9.setLocation(new Point(440, 160));
            jTextField9.setSize(new Dimension(40, 20));
        }
        return jTextField9;
    }
    private JTextField jTextField16;

    private JTextField getJTextField16() {
        if (jTextField16 == null) {
            jTextField16 = new JTextField();
            jTextField16.setLocation(new Point(440, 130));
            jTextField16.setSize(new Dimension(40, 20));
        }
        return jTextField16;
    }

    private JTextField getJTextField10() {
        if (jTextField10 == null) {
            jTextField10 = new JTextField();
            jTextField10.setLocation(new Point(240, 40));
            jTextField10.setSize(new Dimension(140, 20));
        }
        return jTextField10;
    }

    /**
     * This method initializes jScrollPane4
     *
     * @return javax.swing.JScrollPane
     */
    private JScrollPane getJScrollPane4() {
        if (jScrollPane4 == null) {
            jScrollPane4 = new JScrollPane();
            jScrollPane4.setLocation(new Point(380, 210));
            jScrollPane4.setSize(new Dimension(150, 100));
            jScrollPane4.setViewportView(getJTable4());
        }
        return jScrollPane4;
    }

    /**
     * This method initializes jTable4
     *
     * @return javax.swing.JTable
     */
    private JTable getJTable4() {
        if (jTable4 == null) {
            jTable4 = new JTable();

            jTable4.addMouseListener(this);

            skillTM = new javax.swing.table.DefaultTableModel(col1, 0) {

                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            jTable4.setModel(skillTM);
            String[] s = {"", ""};
            skillTM.addRow(s);
        }
        return jTable4;
    }
} // @jve:decl-index=0:visual-constraint="10,10"

