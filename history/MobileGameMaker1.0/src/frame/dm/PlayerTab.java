package frame.dm;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import model.Project;
//import org.apache.log4j.*;

public class PlayerTab extends JPanel implements MouseListener {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private JLabel jLabel = null;
    private JTextField jTextField = null;
    private JLabel jLabel1 = null;
    private JTextField jTextField1 = null;
    private JLabel jLabel2 = null;
    private JButton jButton = null;
    private JLabel jLabel3 = null;
    private JLabel jLabel4 = null;
    private JButton jButton1 = null;
    private JLabel jLabel5 = null;
    private JLabel jLabel6 = null;
    private JLabel jLabel7 = null;
    private JLabel jLabel8 = null;
    private JTextField jTextField2 = null;
    private JTextField jTextField3 = null;
    private JTextField jTextField4 = null;
    private JLabel jLabel9 = null;
    private JLabel jLabel10 = null;
    private JLabel jLabel11 = null;
    private JLabel jLabel12 = null;
    private JLabel jLabel13 = null;
    private JTextField jTextField5 = null;
    private JTextField jTextField6 = null;
    private JTextField jTextField7 = null;
    private JTextField jTextField8 = null;
    private JTextField jTextField9 = null;
    private JLabel jLabel14 = null;
    private JLabel jLabel15 = null;
//    private JLabel jLabel16 = null;
    private JLabel jLabel17 = null;
    private JTextField jTextField10 = null;
    private JTextField jTextField11 = null;
//    private JTextField jTextField12 = null;
    private JTextField jTextField13 = null;
    private JLabel jLabel20 = null;
    private JScrollPane jScrollPane = null;
    private JTextArea jTextArea = null;
    private JDialog jDialog = null; // @jve:decl-index=0:visual-constraint="566,6"
    private JPanel jContentPane = null;
    private JScrollPane jScrollPane1 = null;
    private JTable jTable = null;
    private JLabel jLabel18 = null;
    private String[] col = {"图片列表"};
    private DefaultTableModel characterTM = null; // 定义一个表的模板
    private DefaultTableModel battlerTM = null; // 定义一个表的模板
    private JButton jButton2 = null;
    private JButton jButton3 = null;
    private JDialog jDialog1 = null; // @jve:decl-index=0:visual-constraint="568,366"
    private JPanel jContentPane1 = null;
    private JScrollPane jScrollPane2 = null;
    private JTable jTable1 = null;
    private JLabel jLabel19 = null;
    private JButton jButton4 = null;
    private JButton jButton5 = null;
    private JButton jButton6 = null;
//    static Logger logger = Logger.getLogger(PlayerTab.class.getName());

    /**
     * This method initializes
     *
     */
    public PlayerTab() {
        super();
        initialize();
        loadCharacters();
        loadBattlers();
    }

    private void createLevList() {
        if (jTextField1.getText().equals("") || jTextField1.getText() == null) {
            jTextField1.setText("0");
        }
        int levMax = 0;
        try {
            levMax = Integer.parseInt(jTextField1.getText());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "请输入正确的封顶等级", "提醒",
                    JOptionPane.INFORMATION_MESSAGE);
        }

        if (levMax < 1) {
            JOptionPane.showMessageDialog(this, "封顶等级不能小于1", "提醒",
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            jTextArea.setText("");
            int[] levList = new int[levMax];
            for (int i = 1; i <= levMax; i++) {
                levList[i - 1] = i * i * 10;
                jTextArea.append(levList[i - 1] + "\n");
            }
        }
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
            jButton.setLocation(new Point(460, 130));
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
     * This method initializes jButton1
     *
     * @return javax.swing.JButton
     */
    private JButton getJButton1() {
        if (jButton1 == null) {
            jButton1 = new JButton();
            jButton1.setText("设置");
            jButton1.setSize(new Dimension(60, 20));
            jButton1.setLocation(new Point(460, 20));
            jButton1.addActionListener(new java.awt.event.ActionListener() {

                public void actionPerformed(java.awt.event.ActionEvent e) {
                    getJDialog();
                    jDialog.setVisible(true);
                }
            });
        }
        return jButton1;
    }

    /**
     * This method initializes jButton2
     *
     * @return javax.swing.JButton
     */
    private JButton getJButton2() {
        if (jButton2 == null) {
            jButton2 = new JButton();
            jButton2.setPreferredSize(new Dimension(60, 20));
            jButton2.setLocation(new Point(200, 260));
            jButton2.setSize(new Dimension(60, 20));
            jButton2.setText("确定");
            jButton2.addActionListener(new java.awt.event.ActionListener() {

                public void actionPerformed(java.awt.event.ActionEvent e) {
                    String name = (String) characterTM.getValueAt(jTable.getSelectedRow(), 0);
                    setCharImg(name);
                }
            });
        }
        return jButton2;
    }

    /**
     * This method initializes jButton3
     *
     * @return javax.swing.JButton
     */
    private JButton getJButton3() {
        if (jButton3 == null) {
            jButton3 = new JButton();
            jButton3.setText("取消");
            jButton3.setSize(new Dimension(60, 20));
            jButton3.setPreferredSize(new Dimension(60, 20));
            jButton3.setLocation(new Point(270, 260));
            jButton3.addActionListener(new java.awt.event.ActionListener() {

                public void actionPerformed(java.awt.event.ActionEvent e) {
                    jDialog.setVisible(false);
                }
            });
        }
        return jButton3;
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
                    setBatttlerImg(name);
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
     * This method initializes jButton6
     *
     * @return javax.swing.JButton
     */
    private JButton getJButton6() {
        if (jButton6 == null) {
            jButton6 = new JButton();
            jButton6.setText("自动生成");
            jButton6.setSize(new Dimension(90, 20));
            jButton6.setLocation(new Point(280, 200));
            jButton6.addActionListener(new java.awt.event.ActionListener() {

                public void actionPerformed(java.awt.event.ActionEvent e) {
                    createLevList();
                }
            });
        }
        return jButton6;
    }

    /**
     * This method initializes jContentPane
     *
     * @return javax.swing.JPanel
     */
    private JPanel getJContentPane() {
        if (jContentPane == null) {
            jLabel18 = new JLabel();
            jLabel18.setText("");
            jLabel18.setSize(new Dimension(200, 240));
            jLabel18.setBorder(BorderFactory.createLineBorder(Color.lightGray,
                    2));
            jLabel18.setLocation(new Point(150, 10));
            jContentPane = new JPanel();
            jContentPane.setLayout(null);
            jContentPane.add(getJScrollPane1(), null);
            jContentPane.add(jLabel18, null);
            jContentPane.add(getJButton2(), null);
            jContentPane.add(getJButton3(), null);
        }
        return jContentPane;
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
     * This method initializes jDialog
     *
     * @return javax.swing.JDialog
     */
    private JDialog getJDialog() {
        if (jDialog == null) {
            jDialog = new JDialog();
            jDialog.setSize(new Dimension(370, 320));
            Dimension screenSize =
                    Toolkit.getDefaultToolkit().getScreenSize();
            Dimension labelSize = jDialog.getSize();
            jDialog.setLocation(screenSize.width / 2 - (labelSize.width / 2),
                    screenSize.height / 2 - (labelSize.height / 2));//设置位置屏幕中部
            jDialog.setTitle("行走图设置");
            jDialog.setModal(true);
            jDialog.setContentPane(getJContentPane());
        }
        return jDialog;
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
     * This method initializes jScrollPane
     *
     * @return javax.swing.JScrollPane
     */
    private JScrollPane getJScrollPane() {
        if (jScrollPane == null) {
            jScrollPane = new JScrollPane();
            jScrollPane.setSize(new Dimension(320, 80));
            jScrollPane.setLocation(new Point(200, 230));
            jScrollPane.setViewportView(getJTextArea());
        }
        return jScrollPane;
    }

    /**
     * This method initializes jScrollPane1
     *
     * @return javax.swing.JScrollPane
     */
    private JScrollPane getJScrollPane1() {
        if (jScrollPane1 == null) {
            jScrollPane1 = new JScrollPane();
            jScrollPane1.setLocation(new Point(10, 10));
            jScrollPane1.setSize(new Dimension(120, 240));
            jScrollPane1.setViewportView(getJTable());
        }
        return jScrollPane1;
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
     * This method initializes jTable
     *
     * @return javax.swing.JTable
     */
    private JTable getJTable() {
        if (jTable == null) {
            jTable = new JTable();
            jTable.setModel(characterTM);
            jTable.addMouseListener(this);
        }

        return jTable;
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
     * This method initializes jTextArea
     *
     * @return javax.swing.JTextArea
     */
    private JTextArea getJTextArea() {
        if (jTextArea == null) {
            jTextArea = new JTextArea();
            jTextArea.setText("请输入角色升级所需要达到的经验值\n每行一个,第一行表示升到1级所需要的经验值\n第二行表示升到2级所需要的经验值、依次类推\n如不会请自动生成");
        }
        return jTextArea;
    }

    /**
     * This method initializes jTextField
     *
     * @return javax.swing.JTextField
     */
    private JTextField getJTextField() {
        if (jTextField == null) {
            jTextField = new JTextField();
//            jTextField.setText("角色名称");
            jTextField.setSize(new Dimension(100, 20));
            jTextField.setLocation(new Point(90, 20));
        }
        return jTextField;
    }

    private JTextField getJTextField29() {
        if (jTextField29 == null) {
            jTextField29 = new JTextField();
//            jTextField29.setText("角色介绍");
            jTextField29.setSize(new Dimension(280, 20));
            jTextField29.setLocation(new Point(90, 50));
        }
        return jTextField29;
    }
    private JTextField jTextField29;

    /**
     * This method initializes jTextField1
     *
     * @return javax.swing.JTextField
     */
    private JTextField getJTextField1() {
        if (jTextField1 == null) {
            jTextField1 = new JTextField();
            jTextField1.setLocation(new Point(270, 20));
            jTextField1.setSize(new Dimension(100, 20));
        }
        return jTextField1;
    }

    /**
     * This method initializes jTextField10
     *
     * @return javax.swing.JTextField
     */
    private JTextField getJTextField10() {
        if (jTextField10 == null) {
            jTextField10 = new JTextField();
            jTextField10.setLocation(new Point(100, 200));
            jTextField10.setSize(new Dimension(90, 20));
        }
        return jTextField10;
    }

    /**
     * This method initializes jTextField11
     *
     * @return javax.swing.JTextField
     */
    private JTextField getJTextField11() {
        if (jTextField11 == null) {
            jTextField11 = new JTextField();
            jTextField11.setLocation(new Point(100, 230));
            jTextField11.setSize(new Dimension(90, 20));
        }
        return jTextField11;
    }

    /**
     * This method initializes jTextField12
     *
     * @return javax.swing.JTextField
     */
//    private JTextField getJTextField12() {
//        if (jTextField12 == null) {
//            jTextField12 = new JTextField();
//            jTextField12.setLocation(new Point(100, 260));
//            jTextField12.setSize(new Dimension(90, 20));
//        }
//        return jTextField12;
//    }
    /**
     * This method initializes jTextField13
     *
     * @return javax.swing.JTextField
     */
    private JTextField getJTextField13() {
        if (jTextField13 == null) {
            jTextField13 = new JTextField();
            jTextField13.setLocation(new Point(100, 290));
            jTextField13.setSize(new Dimension(90, 20));
        }
        return jTextField13;
    }

    /**
     * This method initializes jTextField2
     *
     * @return javax.swing.JTextField
     */
    private JTextField getJTextField2() {
        if (jTextField2 == null) {
            jTextField2 = new JTextField();
            jTextField2.setLocation(new Point(270, 80));
            jTextField2.setSize(new Dimension(100, 20));
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
            jTextField3.setLocation(new Point(270, 110));
            jTextField3.setSize(new Dimension(100, 20));
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
            jTextField4.setLocation(new Point(270, 140));
            jTextField4.setSize(new Dimension(100, 20));
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
            jTextField5.setLocation(new Point(90, 80));
            jTextField5.setSize(new Dimension(100, 20));
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
            jTextField6.setLocation(new Point(90, 110));
            jTextField6.setSize(new Dimension(100, 20));
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
            jTextField7.setLocation(new Point(90, 140));
            jTextField7.setSize(new Dimension(100, 20));
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
            jTextField8.setLocation(new Point(90, 170));
            jTextField8.setSize(new Dimension(100, 20));
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
            jTextField9.setLocation(new Point(270, 170));
            jTextField9.setSize(new Dimension(100, 20));
        }
        return jTextField9;
    }

    /**
     * This method initializes this
     *
     */
    private void initialize() {
        jLabel20 = new JLabel();
        jLabel20.setText("经验值表：");
        jLabel20.setSize(new Dimension(70, 20));
        jLabel20.setLocation(new Point(200, 200));
        jLabel17 = new JLabel();
        jLabel17.setText("闪避：");
        jLabel17.setSize(new Dimension(90, 20));
        jLabel17.setLocation(new Point(20, 290));
//        jLabel16 = new JLabel();
//        jLabel16.setText("等级附加HP：");
//        jLabel16.setSize(new Dimension(90, 20));
//        jLabel16.setLocation(new Point(20, 260));
        jLabel15 = new JLabel();
        jLabel15.setText("防御力：");
        jLabel15.setSize(new Dimension(90, 20));
        jLabel15.setLocation(new Point(20, 230));
        jLabel14 = new JLabel();
        jLabel14.setText("攻击力：");
        jLabel14.setSize(new Dimension(90, 20));
        jLabel14.setLocation(new Point(20, 200));
        jLabel13 = new JLabel();
        jLabel13.setText("智力初值：");
        jLabel13.setSize(new Dimension(70, 20));
        jLabel13.setLocation(new Point(20, 140));
        jLabel12 = new JLabel();
        jLabel12.setText("敏捷初值：");
        jLabel12.setSize(new Dimension(70, 20));
        jLabel12.setLocation(new Point(20, 110));
        jLabel11 = new JLabel();
        jLabel11.setText("力量初值：");
        jLabel11.setSize(new Dimension(70, 20));
        jLabel11.setLocation(new Point(20, 80));
        jLabel10 = new JLabel();
        jLabel10.setText("SP初值：");
        jLabel10.setSize(new Dimension(70, 20));
        jLabel10.setLocation(new Point(200, 170));
        jLabel9 = new JLabel();
        jLabel9.setText("HP初值：");
        jLabel9.setSize(new Dimension(70, 20));
        jLabel9.setLocation(new Point(20, 170));
        jLabel8 = new JLabel();
        jLabel8.setText("智力成长：");
        jLabel8.setSize(new Dimension(70, 20));
        jLabel8.setLocation(new Point(200, 140));
        jLabel7 = new JLabel();
        jLabel7.setText("敏捷成长：");
        jLabel7.setSize(new Dimension(70, 20));
        jLabel7.setLocation(new Point(200, 110));
        jLabel6 = new JLabel();
        jLabel6.setText("力量成长：");
        jLabel6.setSize(new Dimension(70, 20));
        jLabel6.setLocation(new Point(200, 80));
        jLabel5 = new JLabel();
        jLabel5.setText("");
        jLabel5.setLocation(new Point(400, 50));
        jLabel5.setBorder(BorderFactory.createLineBorder(Color.lightGray, 2));
        jLabel5.setSize(new Dimension(120, 60));
        jLabel4 = new JLabel();
        jLabel4.setText("行走图：");
        jLabel4.setLocation(new Point(400, 20));
        jLabel4.setSize(new Dimension(60, 20));
        jLabel3 = new JLabel();
        jLabel3.setText("");
        jLabel3.setLocation(new Point(400, 160));
        jLabel3.setBorder(BorderFactory.createLineBorder(Color.lightGray, 2));
        jLabel3.setSize(new Dimension(120, 60));
        jLabel2 = new JLabel();
        jLabel2.setText("战斗图：");
        jLabel2.setLocation(new Point(400, 130));
        jLabel2.setSize(new Dimension(60, 20));
        jLabel1 = new JLabel();
        jLabel1.setText("封顶等级：");
        jLabel1.setLocation(new Point(200, 20));
        jLabel1.setSize(new Dimension(70, 20));
        JLabel jLabel29 = new JLabel("角色介绍：");
        jLabel29.setBounds(20, 50, 70, 20);
        jLabel = new JLabel();
        jLabel.setText("角色名称：");
        jLabel.setLocation(new Point(20, 20));
        jLabel.setDisplayedMnemonic(KeyEvent.VK_UNDEFINED);
        jLabel.setSize(new Dimension(70, 20));
        this.setLayout(null);
        this.setSize(new Dimension(544, 326));
        this.add(jLabel, null);
        this.add(jLabel29, null);
        this.add(getJTextField(), null);
        this.add(getJTextField29(), null);
        this.add(jLabel1, null);
        this.add(getJTextField1(), null);
        this.add(jLabel2, null);
        this.add(getJButton(), null);
        this.add(jLabel3, null);
        this.add(jLabel4, null);
        this.add(getJButton1(), null);
        this.add(jLabel5, null);
        this.add(jLabel6, null);
        this.add(jLabel7, null);
        this.add(jLabel8, null);
        this.add(getJTextField2(), null);
        this.add(getJTextField3(), null);
        this.add(getJTextField4(), null);
        this.add(jLabel9, null);
        this.add(jLabel10, null);
        this.add(jLabel11, null);
        this.add(jLabel12, null);
        this.add(jLabel13, null);
        this.add(getJTextField5(), null);
        this.add(getJTextField6(), null);
        this.add(getJTextField7(), null);
        this.add(getJTextField8(), null);
        this.add(getJTextField9(), null);
        this.add(jLabel14, null);
        this.add(jLabel15, null);
//        this.add(jLabel16, null);
        this.add(jLabel17, null);
        this.add(getJTextField10(), null);
        this.add(getJTextField11(), null);
//        this.add(getJTextField12(), null);
        this.add(getJTextField13(), null);
        this.add(jLabel20, null);
        this.add(getJScrollPane(), null);

        this.add(getJButton6(), null);
    }

    private String[] listFileName(String path) {

        File f = new File(Project.getProjectPath() + "\\" + path);
        String[] s = f.list();

        return s;
    }

    private void loadBattlers() {
        battlerTM = new DefaultTableModel(col, 0); // 定义一个表的模板
        try {
            String[] str = listFileName("image\\battler");
            for (int i = 0; i < str.length; i++) {
                String[] tcol = {str[i]};
                battlerTM.addRow(tcol);
            }
        } catch (Exception e) {
//            logger.error("Exception : ",e);
        }
    }

    private void loadCharacters() {
        characterTM = new DefaultTableModel(col, 0); // 定义一个表的模板
        try {
            String[] str = listFileName("image\\character");
            for (int i = 0; i < str.length; i++) {
                String[] tcol = {str[i]};
                characterTM.addRow(tcol);
            }
        } catch (Exception e) {
//            logger.error("Exception : ",e);
        }
    }

    public void mouseClicked(MouseEvent e) {
        // TODO 自动生成方法存根
    }

    public void mouseEntered(MouseEvent e) {
        // TODO 自动生成方法存根
    }

    public void mouseExited(MouseEvent e) {
        // TODO 自动生成方法存根
    }

    public void mousePressed(MouseEvent e) {
        // TODO 自动生成方法存根
        if (e.getSource().equals(jTable)) {
            int selected = jTable.getSelectedRow();
            if (selected != -1) {
                String name = (String) characterTM.getValueAt(jTable.getSelectedRow(), 0);
                jLabel18.setIcon(new ImageIcon(Project.getProjectPath() + "\\" + "image/character/" + name));
            }
        } else if (e.getSource().equals(jTable1)) {

            int selected = jTable1.getSelectedRow();
            if (selected != -1) {
                String name = (String) battlerTM.getValueAt(jTable1.getSelectedRow(), 0);
                jLabel19.setIcon(new ImageIcon(Project.getProjectPath() + "\\" + "image/battler/" + name));
            }
        }
    }

    public void updateList() {
        if (Project.getPlayer().getName() == null) {
            Project.getPlayer().setName("无");
        }
        if (Project.getPlayer().getDescription() == null) {
            Project.getPlayer().setDescription("无");
        }
        getJTextField().setText(Project.getPlayer().getName());
        getJTextField29().setText(Project.getPlayer().getDescription());
        getJTextField1().setText("" + Project.getPlayer().getMaxlev());
        getJTextField3().setText("" + Project.getPlayer().getAgilbylev());
        getJTextField2().setText("" + Project.getPlayer().getStrebylev());
        getJTextField4().setText("" + Project.getPlayer().getIntebylev());
        getJTextField8().setText("" + Project.getPlayer().getHp());
        getJTextField9().setText("" + Project.getPlayer().getSp());
        getJTextField6().setText("" + Project.getPlayer().getAgil());
        getJTextField5().setText("" + Project.getPlayer().getStre());
        getJTextField7().setText("" + Project.getPlayer().getInte());
        getJTextField10().setText("" + Project.getPlayer().getAtk());
        getJTextField11().setText("" + Project.getPlayer().getDef());
//        getJTextField12().setText("" + Project.getPlayer().getHpbylev());
        getJTextField13().setText("" + Project.getPlayer().getFlee());
        jLabel3.setIcon(new ImageIcon(Project.getProjectPath() + "\\" + "image/battler/" + Project.getPlayer().getBImgName()));
        jLabel5.setIcon(new ImageIcon(Project.getProjectPath() + "\\" + "image/character/" + Project.getPlayer().getCImgName()));
        jTextArea.setText("");
        for (int i = 0; i < Project.getPlayer().getMaxlev(); i++) {
            jTextArea.append(Project.getPlayer().getLevList()[i] + "\n");
        }
    }

    public void mouseReleased(MouseEvent e) {
        // TODO 自动生成方法存根
    }

    public boolean save() {
        boolean judge = true;

        // 检测战斗图
        if (Project.getPlayer().getBImgName() == null) {
            JOptionPane.showMessageDialog(this, "战斗图不能为空", "提醒",
                    JOptionPane.INFORMATION_MESSAGE);
            judge = false;
            return judge;
        }

        // 检测行走图
        if (Project.getPlayer().getCImgName() == null) {
            JOptionPane.showMessageDialog(this, "行走图不能为空", "提醒",
                    JOptionPane.INFORMATION_MESSAGE);
            judge = false;
            return judge;
        }

        // 检测姓名
        String name = jTextField.getText();
        if (name.equals("") || name == null) {
            JOptionPane.showMessageDialog(this, "姓名不能为空", "提醒",
                    JOptionPane.INFORMATION_MESSAGE);
            judge = false;
            return judge;
        }

        // 检测封顶等级
        int levMax = 0;
        if (jTextField1.getText().equals("") || jTextField1.getText() == null) {
            jTextField1.setText("0");
        }
        try {
            levMax = Integer.parseInt(jTextField1.getText());
            if (levMax < 1) {
                JOptionPane.showMessageDialog(this, "封顶等级不能小于1", "提醒",
                        JOptionPane.INFORMATION_MESSAGE);
                judge = false;
                return judge;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "请输入正确的封顶等级", "提醒",
                    JOptionPane.INFORMATION_MESSAGE);
            judge = false;
            return judge;
        }

        // 检测角色属性
        int strebylev = 0;
        int agilbylev = 0;
        int intebylev = 0;
        int stre = 0;
        int agil = 0;
        int inte = 0;
        int hp = 0;
        int sp = 0;
        int ack = 0;
        int def = 0;
        int flee = 0;
//        int spbylev = 0;

        if (jTextField2.getText().equals("") || jTextField2.getText() == null) {
            jTextField2.setText("0");
        }
        if (jTextField3.getText().equals("") || jTextField3.getText() == null) {
            jTextField3.setText("0");
        }
        if (jTextField4.getText().equals("") || jTextField4.getText() == null) {
            jTextField4.setText("0");
        }
        if (jTextField5.getText().equals("") || jTextField5.getText() == null) {
            jTextField5.setText("0");
        }
        if (jTextField6.getText().equals("") || jTextField6.getText() == null) {
            jTextField6.setText("0");
        }
        if (jTextField7.getText().equals("") || jTextField7.getText() == null) {
            jTextField7.setText("0");
        }
        if (jTextField8.getText().equals("") || jTextField8.getText() == null) {
            jTextField8.setText("0");
        }
        if (jTextField9.getText().equals("") || jTextField9.getText() == null) {
            jTextField9.setText("0");
        }
        if (jTextField10.getText().equals("") || jTextField10.getText() == null) {
            jTextField10.setText("0");
        }
        if (jTextField11.getText().equals("") || jTextField11.getText() == null) {
            jTextField11.setText("0");
        }
//        if (jTextField12.getText().equals("") || jTextField12.getText() == null) {
//            jTextField12.setText("0");
//        }
        if (jTextField13.getText().equals("") || jTextField13.getText() == null) {
            jTextField13.setText("0");
        }
        try {
            strebylev = Integer.parseInt(jTextField2.getText());
            agilbylev = Integer.parseInt(jTextField3.getText());
            intebylev = Integer.parseInt(jTextField4.getText());
            stre = Integer.parseInt(jTextField5.getText());
            agil = Integer.parseInt(jTextField6.getText());
            inte = Integer.parseInt(jTextField7.getText());
            hp = Integer.parseInt(jTextField8.getText());
            sp = Integer.parseInt(jTextField9.getText());
            ack = Integer.parseInt(jTextField10.getText());
            def = Integer.parseInt(jTextField11.getText());
//            hpbylev = Integer.parseInt(jTextField12.getText());
            flee = Integer.parseInt(jTextField13.getText());

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "属性设置不正确", "提醒",
                    JOptionPane.INFORMATION_MESSAGE);
            judge = false;
            return judge;
        }

        //检测经验表
        String[] str = jTextArea.getText().trim().replace('\n', 'A').split("A");
        if (str.length != levMax) {
            JOptionPane.showMessageDialog(this, "经验表不正确", "提醒",
                    JOptionPane.INFORMATION_MESSAGE);
            judge = false;
            return judge;
        }
        int[] levList = new int[levMax];
        try {
            for (int i = 0; i < str.length; i++) {
                levList[i] = Integer.parseInt(str[i]);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "经验表不正确", "提醒",
                    JOptionPane.INFORMATION_MESSAGE);
            judge = false;
            return judge;
        }

        if (judge) {
            Project.getPlayer().setName(name);
            Project.getPlayer().setMaxlev(levMax);
            Project.getPlayer().setStre(stre);
            Project.getPlayer().setAgil(agil);
            Project.getPlayer().setInte(inte);
            Project.getPlayer().setStrebylev(strebylev);
            Project.getPlayer().setAgilbylev(agilbylev);
            Project.getPlayer().setIntebylev(intebylev);
            Project.getPlayer().setHp(hp);
            Project.getPlayer().setSp(sp);

            Project.getPlayer().setAtk(ack);
            Project.getPlayer().setDef(def);
            Project.getPlayer().setFlee(flee);
//            Project.getPlayer().setSpbylev(spbylev);

            Project.getPlayer().setLevList(levList);
            Project.getPlayer().setDescription(jTextField29.getText());
        }

        return judge;
    }
    private String BattleImageName, CharImgName;

    private void setBatttlerImg(String name) {
        BattleImageName = name;
        jLabel3.setIcon(new ImageIcon(Project.getProjectPath() + "\\" + "image/battler/" + name));
        Project.getPlayer().setBImgName(name);
        jDialog1.setVisible(false);
    }

    private void setCharImg(String name) {
        CharImgName = name;
        jLabel5.setIcon(new ImageIcon(Project.getProjectPath() + "\\" + "image/character/" + name));
        Project.getPlayer().setCImgName(name);
        jDialog.setVisible(false);
    }
} // @jve:decl-index=0:visual-constraint="10,10"

