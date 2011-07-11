package frame.event.ui_event.dialog_game;

import java.awt.Dimension;
import java.awt.Point;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import frame.event.ui_event.Dialog_Main;
import frame.event.ui_event.Event_Dialog;
import java.awt.Toolkit;

public class Dialog_Wait extends Event_Dialog {

    private JPanel jContentPane = null;
    private JLabel jLabel = null;
    private ButtonGroup buttonGroup_type = null;  //  @jve:decl-index=0:
    private JTextField jTextField_time = null;
    private Dialog_Main p;

    public Dialog_Wait(Dialog_Main parent) {
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
        this.setSize(new Dimension(276, 146));
        this.setContentPane(getJContentPane());
        this.setTitle("延时等待");
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
        String sc = "WAIT " + getJTextField_time().getText();
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
            jLabel.setText("时间");
            jContentPane = new JPanel();
            jContentPane.setLayout(null);
            jContentPane.add(jLabel, null);
            jContentPane.add(getJTextField_time(), null);
        }
        return jContentPane;
    }

    /**
     * This method initializes jTextField_time
     *
     * @return javax.swing.JTextField
     */
    private JTextField getJTextField_time() {
        if (jTextField_time == null) {
            jTextField_time = new JTextField();
            jTextField_time.setLocation(new Point(50, 15));
            jTextField_time.setSize(new Dimension(200, 20));
        }
        return jTextField_time;
    }
}  //  @jve:decl-index=0:visual-constraint="10,10"

