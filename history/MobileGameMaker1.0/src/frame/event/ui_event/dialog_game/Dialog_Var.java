package frame.event.ui_event.dialog_game;

import frame.event.ui_event.Dialog_Main;
import frame.event.ui_event.Event_Dialog;
import java.awt.Dimension;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Point;
import java.awt.Toolkit;

public class Dialog_Var extends Event_Dialog {

    private JPanel jContentPane = null;
    private JLabel jLabel = null;
    private JComboBox jComboBox_var1 = null;
    private JRadioButton jRadioButton_equal = null;
    private JRadioButton jRadioButton_plus = null;
    private JRadioButton jRadioButton_reduce = null;
    private JRadioButton jRadioButton_multiply = null;
    private JRadioButton jRadioButton_divide = null;
    private JRadioButton jRadioButton_mod = null;
    private JRadioButton jRadioVar = null;
    private JRadioButton jRadioConst = null;
//    private JLabel jLabel1 = null;
//    private JLabel jLabel2 = null;
    private JTextField jTextField_const = null;
    private JComboBox jComboBox_var2 = null;
    private ButtonGroup buttonGroup_type = null;  //  @jve:decl-index=0:
    private ButtonGroup buttonGroup_oType = null;  //
    private Dialog_Main p;

    public Dialog_Var(Dialog_Main parent) {
        super(parent);
        p = parent;
        initialize();
    }

    /**
     * This method initializes this
     *
     */
    private void initialize() {
        buttonGroup_type = new ButtonGroup();
        buttonGroup_oType = new ButtonGroup();
        this.setSize(new Dimension(387, 235));
         this.setTitle("变量控制");
        this.setContentPane(getJContentPane());
        this.getContentPane().setLayout(null);
        addDefaultButton();
         Dimension screenSize =
                Toolkit.getDefaultToolkit().getScreenSize();
        Dimension labelSize = this.getSize();
        this.setLocation(screenSize.width / 2 - (labelSize.width / 2),
                screenSize.height / 2 - (labelSize.height / 2));//设置位置屏幕中部
    }
    private int type = 0;

    public boolean confirm() {
        // TODO 自动生成方法存根
        String sc;
        sc = "$VAR[" + jComboBox_var1.getSelectedIndex() + "]";
        switch (type) {
            case 0:
                sc += "=";
                break;
            case 1:
                sc += "+=";
                break;
            case 2:
                sc += "-=";
                break;
            case 3:
                sc += "*=";
                break;
            case 4:
                sc += "/=";
                break;
            case 5:
                sc += "%=";
                break;
        }
        switch (Optype) {
            case 0:
                sc += getJTextField_const().getText();
                break;
            case 1:
                sc += "$VAR[" + jComboBox_var2.getSelectedIndex() + "]";
                break;
        }
        p.insertEventTM(p.getEventDialog().curRow, sc);
        return false;
    }
    private int Optype = 0;

    /**
     * This method initializes jContentPane
     *
     * @return javax.swing.JPanel
     */
    private JPanel getJContentPane() {
        if (jContentPane == null) {
            jRadioVar = new JRadioButton();
//            jLabel2.setHorizontalAlignment(SwingConstants.CENTER);
//            jLabel2.setHorizontalTextPosition(SwingConstants.CENTER);
            jRadioVar.setLocation(new Point(10, 130));
            jRadioVar.setSize(new Dimension(60, 20));
            jRadioVar.setText("变量");
            jRadioVar.addActionListener(new java.awt.event.ActionListener() {

                @Override
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    Optype = 1;
                }
            });
            buttonGroup_oType.add(jRadioVar);
            jRadioConst = new JRadioButton();
//            jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
//            jLabel1.setHorizontalTextPosition(SwingConstants.CENTER);
            jRadioConst.setLocation(new Point(10, 90));
            jRadioConst.setSize(new Dimension(60, 20));
            jRadioConst.setText("常量");
            jRadioConst.setSelected(true);
            jRadioConst.addActionListener(new java.awt.event.ActionListener() {

                @Override
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    Optype = 0;
                }
            });
            buttonGroup_oType.add(jRadioConst);
            jLabel = new JLabel();
            jLabel.setHorizontalAlignment(SwingConstants.CENTER);
            jLabel.setHorizontalTextPosition(SwingConstants.CENTER);
            jLabel.setLocation(new Point(100, 15));
            jLabel.setSize(new Dimension(30, 20));
            jLabel.setText("变量");
            jContentPane = new JPanel();
            jContentPane.setLayout(null);
            jContentPane.add(jLabel, null);
            jContentPane.add(getJComboBox_var1(), null);
            jContentPane.add(getJRadioButton_equal(), null);
            jContentPane.add(getJRadioButton_plus(), null);
            jContentPane.add(getJRadioButton_reduce(), null);
            jContentPane.add(getJRadioButton_multiply(), null);
            jContentPane.add(getJRadioButton_divide(), null);
            jContentPane.add(getJRadioButton_mod(), null);
            jContentPane.add(jRadioVar, null);
            jContentPane.add(jRadioConst, null);
            jContentPane.add(getJTextField_const(), null);
            jContentPane.add(getJComboBox_var2(), null);
        }
        return jContentPane;
    }

    /**
     * This method initializes jComboBox_var1
     *
     * @return javax.swing.JComboBox
     */
    private JComboBox getJComboBox_var1() {
        if (jComboBox_var1 == null) {
            jComboBox_var1 = new JComboBox();
            jComboBox_var1.setLocation(new Point(140, 15));
            jComboBox_var1.setSize(new Dimension(150, 20));
            jComboBox_var1.removeAllItems();
            for (int i = 0; i < 100; i++) {
                jComboBox_var1.addItem(i);
            }
        }
        return jComboBox_var1;
    }

    /**
     * This method initializes jRadioButton_equal
     *
     * @return javax.swing.JRadioButton
     */
    private JRadioButton getJRadioButton_equal() {
        if (jRadioButton_equal == null) {
            jRadioButton_equal = new JRadioButton();
            jRadioButton_equal.setText("代入");
            jRadioButton_equal.setLocation(new Point(10, 50));
            jRadioButton_equal.setSelected(true);
            jRadioButton_equal.setSize(new Dimension(55, 20));
            jRadioButton_equal.addActionListener(new java.awt.event.ActionListener() {

                @Override
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    type = 0;
                }
            });
            buttonGroup_type.add(jRadioButton_equal);
        }
        return jRadioButton_equal;
    }

    /**
     * This method initializes jRadioButton_plus
     *
     * @return javax.swing.JRadioButton
     */
    private JRadioButton getJRadioButton_plus() {
        if (jRadioButton_plus == null) {
            jRadioButton_plus = new JRadioButton();
            jRadioButton_plus.setText("加法");
            jRadioButton_plus.setSize(new Dimension(55, 20));
            jRadioButton_plus.setLocation(new Point(70, 50));
            jRadioButton_plus.addActionListener(new java.awt.event.ActionListener() {

                @Override
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    type = 1;
                }
            });
            buttonGroup_type.add(jRadioButton_plus);
        }
        return jRadioButton_plus;
    }

    /**
     * This method initializes jRadioButton_reduce
     *
     * @return javax.swing.JRadioButton
     */
    private JRadioButton getJRadioButton_reduce() {
        if (jRadioButton_reduce == null) {
            jRadioButton_reduce = new JRadioButton();
            jRadioButton_reduce.setText("减法");
            jRadioButton_reduce.setSize(new Dimension(55, 20));
            jRadioButton_reduce.setLocation(new Point(130, 50));
            jRadioButton_reduce.addActionListener(new java.awt.event.ActionListener() {

                @Override
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    type = 2;
                }
            });
            buttonGroup_type.add(jRadioButton_reduce);
        }
        return jRadioButton_reduce;
    }

    /**
     * This method initializes jRadioButton_multiply
     *
     * @return javax.swing.JRadioButton
     */
    private JRadioButton getJRadioButton_multiply() {
        if (jRadioButton_multiply == null) {
            jRadioButton_multiply = new JRadioButton();
            jRadioButton_multiply.setText("乘法");
            jRadioButton_multiply.setSize(new Dimension(55, 20));
            jRadioButton_multiply.setLocation(new Point(190, 50));
            jRadioButton_multiply.addActionListener(new java.awt.event.ActionListener() {

                @Override
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    type = 3;
                }
            });
            buttonGroup_type.add(jRadioButton_multiply);
        }
        return jRadioButton_multiply;
    }

    /**
     * This method initializes jRadioButton_divide
     *
     * @return javax.swing.JRadioButton
     */
    private JRadioButton getJRadioButton_divide() {
        if (jRadioButton_divide == null) {
            jRadioButton_divide = new JRadioButton();
            jRadioButton_divide.setText("除法");
            jRadioButton_divide.setSize(new Dimension(55, 20));
            jRadioButton_divide.setLocation(new Point(250, 50));
            jRadioButton_divide.addActionListener(new java.awt.event.ActionListener() {

                @Override
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    type = 5;
                }
            });
            buttonGroup_type.add(jRadioButton_divide);
        }
        return jRadioButton_divide;
    }

    /**
     * This method initializes jRadioButton_mod
     *
     * @return javax.swing.JRadioButton
     */
    private JRadioButton getJRadioButton_mod() {
        if (jRadioButton_mod == null) {
            jRadioButton_mod = new JRadioButton();
            jRadioButton_mod.setText("取模");
            jRadioButton_mod.setSize(new Dimension(55, 20));
            jRadioButton_mod.setLocation(new Point(310, 50));
            jRadioButton_mod.addActionListener(new java.awt.event.ActionListener() {

                @Override
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    type = 6;
                }
            });
            buttonGroup_type.add(jRadioButton_mod);
        }
        return jRadioButton_mod;
    }

    /**
     * This method initializes jTextField_const
     *
     * @return javax.swing.JTextField
     */
    private JTextField getJTextField_const() {
        if (jTextField_const == null) {
            jTextField_const = new JTextField();
            jTextField_const.setLocation(new Point(80, 90));
            jTextField_const.setSize(new Dimension(120, 20));
        }
        return jTextField_const;
    }

    /**
     * This method initializes jComboBox_var2
     *
     * @return javax.swing.JComboBox
     */
    private JComboBox getJComboBox_var2() {
        if (jComboBox_var2 == null) {
            jComboBox_var2 = new JComboBox();
            jComboBox_var2.setLocation(new Point(80, 130));
            jComboBox_var2.setSize(new Dimension(120, 20));
            jComboBox_var2.removeAllItems();
            for (int i = 0; i < 100; i++) {
                jComboBox_var2.addItem(i);
            }
        }
        return jComboBox_var2;
    }
}  //  @jve:decl-index=0:visual-constraint="10,10"

