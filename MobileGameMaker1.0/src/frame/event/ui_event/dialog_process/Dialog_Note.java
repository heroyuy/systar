package frame.event.ui_event.dialog_process;

import frame.event.ui_event.Dialog_Main;
import frame.event.ui_event.Event_Dialog;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Point;
import java.awt.Toolkit;
import javax.swing.JTextArea;

public class Dialog_Note extends Event_Dialog {

    private JPanel jContentPane = null;
    private JLabel jLabel = null;
    private JTextArea jTextArea_note = null;
    private Dialog_Main p;

    public Dialog_Note(Dialog_Main parent) {
        super(parent);
        p = parent;
        initialize();
    }

    /**
     * This method initializes this
     *
     */
    private void initialize() {
        this.setSize(new Dimension(280, 285));
        this.setContentPane(getJContentPane());
         this.setTitle("脚本注释");
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
        String sc = "//"+jTextArea_note.getText();
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
            jLabel.setText("注释");
            jContentPane = new JPanel();
            jContentPane.setLayout(null);
            jContentPane.add(jLabel, null);
            jContentPane.add(getJTextArea_note(), null);
        }
        return jContentPane;
    }

    /**
     * This method initializes jTextArea_note
     *
     * @return javax.swing.JTextArea
     */
    private JTextArea getJTextArea_note() {
        if (jTextArea_note == null) {
            jTextArea_note = new JTextArea();
            jTextArea_note.setLocation(new Point(18, 52));
            jTextArea_note.setSize(new Dimension(230, 120));
        }
        return jTextArea_note;
    }
}  //  @jve:decl-index=0:visual-constraint="10,10"

