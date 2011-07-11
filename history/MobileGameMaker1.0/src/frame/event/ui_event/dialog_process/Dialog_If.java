package frame.event.ui_event.dialog_process;

import java.awt.Dimension;
import java.awt.Point;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import frame.event.ui_event.Dialog_Main;
import frame.event.ui_event.Event_Dialog;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import java.awt.Rectangle;
import java.awt.Toolkit;
import javax.swing.JLabel;

public class Dialog_If extends Event_Dialog {

    private JPanel jContentPane = null;
    private JRadioButton jRadioButton_switch = null;
    private JRadioButton jRadioButton_var = null;
    private JComboBox jComboBox_var = null;
    private ButtonGroup buttonGroup_type = null;  //  @jve:decl-index=0:
    private JComboBox jComboBox_switch = null;
    private JLabel jLabel = null;
    private JTextField jTextField_condition = null;
    private Dialog_Main p;

    public Dialog_If(Dialog_Main parent) {
        super(parent);
        p = parent;
        initialize();
    }
    private int type = 0;

    /**
     * This method initializes this
     *
     */
    private void initialize() {
        buttonGroup_type = new ButtonGroup();
        this.setSize(new Dimension(261, 210));
        this.setContentPane(getJContentPane());
        this.getContentPane().setLayout(null);
        this.setTitle("条件结构");
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
        switch (type) {
            case 0:
                sc = "IF $SWITCH[" + getJComboBox_switch().getSelectedIndex() + "]";
                break;
            case 1:
                sc = "IF $VAR[" + getJComboBox_var().getSelectedIndex() + "]" + getJTextField_condition().getText();
                break;
        }
        Dialog_Main.insertEventTM(Dialog_Main.getEventDialog().curRow, "ENDIF");
//        Dialog_Main.getEventDialog().curRow++;
        Dialog_Main.insertEventTM(Dialog_Main.getEventDialog().curRow, "");
//        Dialog_Main.getEventDialog().curRow++;
        Dialog_Main.insertEventTM(Dialog_Main.getEventDialog().curRow, sc);
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
            jLabel.setText("条件");
            jLabel.setLocation(new Point(15, 85));
            jLabel.setHorizontalAlignment(SwingConstants.CENTER);
            jLabel.setSize(new Dimension(40, 20));
            jContentPane = new JPanel();
            jContentPane.setLayout(null);
            jContentPane.add(getJRadioButton_switch(), null);
            jContentPane.add(getJRadioButton_var(), null);
            jContentPane.add(getJComboBox_var(), null);
            jContentPane.add(getJComboBox_switch(), null);
            jContentPane.add(jLabel, null);
            jContentPane.add(getJTextField_condition(), null);
        }
        return jContentPane;
    }

    /**
     * This method initializes jRadioButton_switch
     *
     * @return javax.swing.JRadioButton
     */
    private JRadioButton getJRadioButton_switch() {
        if (jRadioButton_switch == null) {
            jRadioButton_switch = new JRadioButton();
            jRadioButton_switch.setText("开关");
            jRadioButton_switch.setSelected(true);
            jRadioButton_switch.setSize(new Dimension(60, 20));
            jRadioButton_switch.setLocation(new Point(15, 15));
            jRadioButton_switch.addActionListener(new java.awt.event.ActionListener() {

                @Override
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    type = 0;
                }
            });
            buttonGroup_type.add(jRadioButton_switch);
        }
        return jRadioButton_switch;
    }

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
            jRadioButton_var.setLocation(new Point(15, 50));
            jRadioButton_var.addActionListener(new java.awt.event.ActionListener() {

                @Override
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    type = 1;
                }
            });
            buttonGroup_type.add(jRadioButton_var);
        }
        return jRadioButton_var;
    }

    /**
     * This method initializes jComboBox_var
     *
     * @return javax.swing.JComboBox
     */
    private JComboBox getJComboBox_var() {
        if (jComboBox_var == null) {
            jComboBox_var = new JComboBox();
            jComboBox_var.setLocation(new Point(80, 50));
            jComboBox_var.setSize(new Dimension(150, 20));
            for (int i = 0; i < 100; i++) {
                jComboBox_var.addItem(i);
            }
        }
        return jComboBox_var;
    }

    /**
     * This method initializes jComboBox_switch
     *
     * @return javax.swing.JComboBox
     */
    private JComboBox getJComboBox_switch() {
        if (jComboBox_switch == null) {
            jComboBox_switch = new JComboBox();
            jComboBox_switch.setLocation(new Point(80, 15));
            jComboBox_switch.setSize(new Dimension(150, 20));
            for (int i = 0; i < 100; i++) {
                jComboBox_switch.addItem(i);
            }
        }
        return jComboBox_switch;
    }

    /**
     * This method initializes jTextField_condition
     *
     * @return javax.swing.JTextField
     */
    private JTextField getJTextField_condition() {
        if (jTextField_condition == null) {
            jTextField_condition = new JTextField();
            jTextField_condition.setLocation(new Point(80, 85));
            jTextField_condition.setSize(new Dimension(150, 20));
        }
        return jTextField_condition;
    }
}  //  @jve:decl-index=0:visual-constraint="10,10"

