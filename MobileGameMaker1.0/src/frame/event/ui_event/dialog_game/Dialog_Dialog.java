package frame.event.ui_event.dialog_game;

import frame.event.ui_event.Dialog_Main;
import frame.event.ui_event.Event_Dialog;
import java.awt.Dimension;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Point;
import java.awt.Rectangle;
import javax.swing.JTextArea;
import engine.MapEditor;
import java.awt.Toolkit;

public class Dialog_Dialog extends Event_Dialog {

    private JPanel jContentPane = null;
    private JLabel jLabel = null;
    private ButtonGroup buttonGroup_type = null;  //  @jve:decl-index=0:
    private JTextField jTextField_name = null;
    private JTextArea jTextArea_content = null;
    private JScrollPane jsp;
    private Dialog_Main p;

    public Dialog_Dialog(Dialog_Main parent) {
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
        this.setSize(new Dimension(280, 285));
        this.setContentPane(getJContentPane());
        this.getContentPane().setLayout(null);
        this.setTitle("对话框");
        addDefaultButton();
        Dimension screenSize =
            Toolkit.getDefaultToolkit().getScreenSize();
        Dimension labelSize = this.getSize();
        this.setLocation(screenSize.width / 2 - (labelSize.width / 2),
            screenSize.height / 2 - (labelSize.height / 2));//设置位置屏幕中部
    }

    public boolean confirm() {
        // TODO 自动生成方法存根
        String sc = "DIALOG " + getJTextField_name().getText() + " " + getJTextArea_content().getText();
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
            jLabel.setText("名称");
            jContentPane = new JPanel();
            jContentPane.setLayout(null);
            jContentPane.add(jLabel, null);
            jContentPane.add(getJTextField_name(), null);
            jContentPane.add(getJSP(), null);
        }
        return jContentPane;
    }

    /**
     * This method initializes jTextField_name
     *
     * @return javax.swing.JTextField
     */
    private JTextField getJTextField_name() {
        if (jTextField_name == null) {
            jTextField_name = new JTextField();
            jTextField_name.setSize(new Dimension(200, 20));
            jTextField_name.setLocation(new Point(50, 15));
        }
        return jTextField_name;
    }

    private JScrollPane getJSP() {
        if (jsp == null) {
            jsp = new JScrollPane(getJTextArea_content());
//            jsp.setViewportView(jTextArea_content);
            jsp.setLocation(new Point(18, 52));
            jsp.setSize(new Dimension(230, 120));
        }
        return jsp;
    }

    /**
     * This method initializes jTextArea_content
     *
     * @return javax.swing.JTextArea
     */
    private JTextArea getJTextArea_content() {
        if (jTextArea_content == null) {
            jTextArea_content = new JTextArea();
            jTextArea_content.setLineWrap(true);
//            jTextArea_content.setLocation(new Point(18, 52));
            jTextArea_content.setSize(new Dimension(230, 120));
        }
        return jTextArea_content;
    }
}  //  @jve:decl-index=0:visual-constraint="10,10"

