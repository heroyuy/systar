package frame.event.ui_event.dialog_player;

import java.awt.Dimension;
import java.awt.Point;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import frame.event.ui_event.Dialog_Main;
import frame.event.ui_event.Event_Dialog;
import javax.swing.JRadioButton;
import java.awt.Rectangle;
import java.awt.Toolkit;
import javax.swing.JComboBox;

public class Dialog_Attribute extends Event_Dialog {

    private JPanel jContentPane = null;
    private JRadioButton jRadioButton_reduce = null;
    private JRadioButton jRadioButton_plus = null;
    private JRadioButton jRadioButton_const = null;
    private JRadioButton jRadioButton_var = null;
    private JTextField jTextField_const = null;
    private JComboBox jComboBox_var = null;
    private ButtonGroup buttonGroup_type = null;  //  @jve:decl-index=0:
    private ButtonGroup buttonGroup_oper = null;  //  @jve:decl-index=0:
    private JLabel jLabel = null;
    private JComboBox jComboBox_attribute = null;
    private String[] attributeItems = {"MaxHp", "MaxSp", "力量", "敏捷", "智力"};
    private Dialog_Main p;

    public Dialog_Attribute(Dialog_Main parent) {
        super(parent);
        initialize();
        p = parent;
    }

    /**
     * This method initializes this
     *
     */
    private void initialize() {
        buttonGroup_type = new ButtonGroup();
        buttonGroup_oper = new ButtonGroup();
         this.setTitle("属性修改");
        this.setSize(new Dimension(260, 277));
        this.setContentPane(getJContentPane());
        this.getContentPane().setLayout(null);
        addDefaultButton();
         Dimension screenSize =
                Toolkit.getDefaultToolkit().getScreenSize();
        Dimension labelSize = this.getSize();
        this.setLocation(screenSize.width / 2 - (labelSize.width / 2),
                screenSize.height / 2 - (labelSize.height / 2));//设置位置屏幕中部
    }

    public boolean confirm() {
        // TODO 自动生成方法存根
        String sc = "";
        String[] sss = {"$MAXHP", "$MAXSP", "$STRE", "$AGIL", "$INTE"};
        if (Optype == 0) {
            if (type == 0) {
                sc = sss[getJComboBox_attribute().getSelectedIndex()] + "+=" + getJTextField_const().getText();
            } else {
                sc = sss[getJComboBox_attribute().getSelectedIndex()] + "-=" + getJTextField_const().getText();
            }
        } else {
            if (type == 0) {
                sc = sss[getJComboBox_attribute().getSelectedIndex()] + "+=$VAR[" + getJComboBox_var().getSelectedIndex() + "]";
            } else {
                sc = sss[getJComboBox_attribute().getSelectedIndex()] + "-=$VAR[" + getJComboBox_var().getSelectedIndex() + "]";
            }
        }
        p.insertEventTM(p.getEventDialog().curRow, sc);
        return false;
    }

    /**
     * This method initializes jContentPane
     *
     * @return javax.swing.JPanel
     */
    private JPanel getJContentPane() {
        if (jContentPane == null) {
            jLabel = new JLabel();
            jLabel.setText("能力");
            jLabel.setLocation(new Point(15, 15));
            jLabel.setSize(new Dimension(40, 20));
            jContentPane = new JPanel();
            jContentPane.setLayout(null);
            jContentPane.add(getJRadioButton_reduce(), null);
            jContentPane.add(getJRadioButton_plus(), null);
            jContentPane.add(getJRadioButton_const(), null);
            jContentPane.add(getJRadioButton_var(), null);
            jContentPane.add(getJTextField_const(), null);
            jContentPane.add(getJComboBox_var(), null);
            jContentPane.add(jLabel, null);
            jContentPane.add(getJComboBox_attribute(), null);
        }
        return jContentPane;
    }

    /**
     * This method initializes jRadioButton_reduce
     *
     * @return javax.swing.JRadioButton
     */
    private JRadioButton getJRadioButton_reduce() {
        if (jRadioButton_reduce == null) {
            jRadioButton_reduce = new JRadioButton();
            jRadioButton_reduce.setText("减少");
            jRadioButton_reduce.setLocation(new Point(15, 50));
            jRadioButton_reduce.setSize(new Dimension(60, 20));
            jRadioButton_reduce.addActionListener(new java.awt.event.ActionListener() {

                @Override
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    type = 1;
                }
            });
            buttonGroup_type.add(jRadioButton_reduce);
        }
        return jRadioButton_reduce;
    }
    private int type = 0;

    /**
     * This method initializes jRadioButton_plus
     *
     * @return javax.swing.JRadioButton
     */
    private JRadioButton getJRadioButton_plus() {
        if (jRadioButton_plus == null) {
            jRadioButton_plus = new JRadioButton();
            jRadioButton_plus.setText("增加");
            jRadioButton_plus.setSelected(true);
            jRadioButton_plus.setSize(new Dimension(60, 20));
            jRadioButton_plus.setLocation(new Point(80, 50));
            jRadioButton_plus.addActionListener(new java.awt.event.ActionListener() {

                @Override
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    type = 0;
                }
            });
            buttonGroup_type.add(jRadioButton_plus);
        }
        return jRadioButton_plus;
    }

    /**
     * This method initializes jRadioButton_const
     *
     * @return javax.swing.JRadioButton
     */
    private JRadioButton getJRadioButton_const() {
        if (jRadioButton_const == null) {
            jRadioButton_const = new JRadioButton();
            jRadioButton_const.setText("常量");
            jRadioButton_const.setSelected(true);
            jRadioButton_const.setSize(new Dimension(60, 20));
            jRadioButton_const.setLocation(new Point(15, 100));
            jRadioButton_const.addActionListener(new java.awt.event.ActionListener() {

                @Override
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    Optype = 0;
                }
            });
            buttonGroup_oper.add(jRadioButton_const);
        }
        return jRadioButton_const;
    }
    private int Optype = 0;

    /**
     * This method initializes jRadioButton_var
     *
     * @return javax.swing.JRadioButton
     */
    private JRadioButton getJRadioButton_var() {
        if (jRadioButton_var == null) {
            jRadioButton_var = new JRadioButton();
            jRadioButton_var.setText("变量");
            jRadioButton_var.setSize(new Dimension(60, 20));
            jRadioButton_var.setLocation(new Point(15, 140));
            jRadioButton_var.addActionListener(new java.awt.event.ActionListener() {

                @Override
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    Optype = 1;
                }
            });
            buttonGroup_oper.add(jRadioButton_var);
        }
        return jRadioButton_var;
    }

    /**
     * This method initializes jTextField_const
     *
     * @return javax.swing.JTextField
     */
    private JTextField getJTextField_const() {
        if (jTextField_const == null) {
            jTextField_const = new JTextField();
            jTextField_const.setLocation(new Point(80, 100));
            jTextField_const.setSize(new Dimension(150, 20));
        }
        return jTextField_const;
    }

    /**
     * This method initializes jComboBox_var
     *
     * @return javax.swing.JComboBox
     */
    private JComboBox getJComboBox_var() {
        if (jComboBox_var == null) {
            jComboBox_var = new JComboBox();
            jComboBox_var.setLocation(new Point(80, 140));
            jComboBox_var.setSize(new Dimension(150, 20));
            for (int i = 0; i < 100; i++) {
                jComboBox_var.addItem(i);
            }
        }
        return jComboBox_var;
    }

    /**
     * This method initializes jComboBox_attribute
     *
     * @return javax.swing.JComboBox
     */
    private JComboBox getJComboBox_attribute() {
        if (jComboBox_attribute == null) {
            jComboBox_attribute = new JComboBox();
            jComboBox_attribute.setSize(new Dimension(170, 20));
            jComboBox_attribute.setLocation(new Point(60, 15));
            for (int i = 0; i < attributeItems.length; i++) {

                jComboBox_attribute.addItem(attributeItems[i]);
            }
        }
        return jComboBox_attribute;
    }
}  //  @jve:decl-index=0:visual-constraint="10,10"

