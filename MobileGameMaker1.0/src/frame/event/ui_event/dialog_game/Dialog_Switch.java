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

public class Dialog_Switch extends Event_Dialog {

    private JPanel jContentPane = null;
    private JLabel jLabel = null;
    private JComboBox jComboBox_switch = null;
    private JRadioButton jRadioButton_on = null;
    private JRadioButton jRadioButton_off = null;
    private ButtonGroup buttonGroup_type = null;  //  @jve:decl-index=0:
    private Dialog_Main p;

    public Dialog_Switch(Dialog_Main parent) {
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
        this.setSize(new Dimension(254, 166));
        this.setContentPane(getJContentPane());
        this.getContentPane().setLayout(null);
         this.setTitle("开关控制");
        addDefaultButton();
        Dimension screenSize =
            Toolkit.getDefaultToolkit().getScreenSize();
        Dimension labelSize = this.getSize();
        this.setLocation(screenSize.width / 2 - (labelSize.width / 2),
            screenSize.height / 2 - (labelSize.height / 2));//设置位置屏幕中部
    }

    public boolean confirm() {
        // TODO 自动生成方法存根
        String sc;
        if (type) {
            sc = "$SWITCH[" + jComboBox_switch.getSelectedIndex() + "]=" + "true";


        } else {
            sc = "$SWITCH[" + jComboBox_switch.getSelectedIndex() + "]=" + "false";

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
            jLabel.setHorizontalAlignment(SwingConstants.CENTER);
            jLabel.setHorizontalTextPosition(SwingConstants.CENTER);
            jLabel.setLocation(new Point(15, 15));
            jLabel.setSize(new Dimension(30, 20));
            jLabel.setText("开关");
            jContentPane = new JPanel();
            jContentPane.setLayout(null);
            jContentPane.add(jLabel, null);
            jContentPane.add(getJComboBox_switch(), null);
            jContentPane.add(getJRadioButton_on(), null);
            jContentPane.add(getJRadioButton_off(), null);
        }
        return jContentPane;
    }

    /**
     * This method initializes jComboBox_switch
     *
     * @return javax.swing.JComboBox
     */
    private JComboBox getJComboBox_switch() {
        if (jComboBox_switch == null) {
            jComboBox_switch = new JComboBox();
            jComboBox_switch.setLocation(new Point(50, 15));
            jComboBox_switch.setSize(new Dimension(150, 20));
            jComboBox_switch.removeAllItems();
            for (int i = 0; i < 100; i++) {
                jComboBox_switch.addItem(i);
            }
        }
        return jComboBox_switch;
    }

    /**
     * This method initializes jRadioButton_on
     *
     * @return javax.swing.JRadioButton
     */
    private JRadioButton getJRadioButton_on() {
        if (jRadioButton_on == null) {
            jRadioButton_on = new JRadioButton();
            jRadioButton_on.setText("ON");
            jRadioButton_on.setSelected(true);
            jRadioButton_on.setLocation(new Point(40, 50));
            jRadioButton_on.setSize(new Dimension(55, 20));
            jRadioButton_on.addActionListener(new java.awt.event.ActionListener() {

                @Override
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    type = true;
                }
            });
            buttonGroup_type.add(jRadioButton_on);
        }
        return jRadioButton_on;
    }
    private boolean type = true;

    /**
     * This method initializes jRadioButton_off
     *
     * @return javax.swing.JRadioButton
     */
    private JRadioButton getJRadioButton_off() {
        if (jRadioButton_off == null) {
            jRadioButton_off = new JRadioButton();
            jRadioButton_off.setText("OFF");
            jRadioButton_off.setSize(new Dimension(55, 20));
            jRadioButton_off.setLocation(new Point(120, 50));
            jRadioButton_off.addActionListener(new java.awt.event.ActionListener() {

                @Override
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    type = false;
                }
            });
            buttonGroup_type.add(jRadioButton_off);
        }
        return jRadioButton_off;
    }
}  //  @jve:decl-index=0:visual-constraint="10,10"

