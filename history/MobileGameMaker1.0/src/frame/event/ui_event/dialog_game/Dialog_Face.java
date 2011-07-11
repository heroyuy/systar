package frame.event.ui_event.dialog_game;

import java.awt.Dimension;
import java.awt.Point;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import frame.event.ui_event.Dialog_Main;
import frame.event.ui_event.Event_Dialog;
import java.awt.Toolkit;

public class Dialog_Face extends Event_Dialog {

    private JPanel jContentPane = null;
    private JRadioButton jRadioButton_up = null;
    private JRadioButton jRadioButton_down = null;
    private JRadioButton jRadioButton_left = null;
    private JRadioButton jRadioButton_right = null;
    private ButtonGroup buttonGroup_type = null;  //  @jve:decl-index=0:
    private Dialog_Main p;

    public Dialog_Face(Dialog_Main parent) {
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
        this.setSize(new Dimension(252, 147));
        this.setContentPane(getJContentPane());
        this.setTitle("改变面向");
        this.getContentPane().setLayout(null);
        addDefaultButton();
        Dimension screenSize =
            Toolkit.getDefaultToolkit().getScreenSize();
        Dimension labelSize = this.getSize();
        this.setLocation(screenSize.width / 2 - (labelSize.width / 2),
            screenSize.height / 2 - (labelSize.height / 2));//设置位置屏幕中部
    }

    public boolean confirm() {
        // TODO 自动生成方法存根]

        String sc = "";
        if (jRadioButton_up.isSelected()) {
            sc = "FACE 0";
        }
        if (jRadioButton_down.isSelected()) {
            sc = "FACE 1";
        }
        if (jRadioButton_left.isSelected()) {
            sc = "FACE 2";
        }
        if (jRadioButton_right.isSelected()) {
            sc = "FACE 3";
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
            jContentPane = new JPanel();
            jContentPane.setLayout(null);
            jContentPane.add(getJRadioButton_up(), null);
            jContentPane.add(getJRadioButton_down(), null);
            jContentPane.add(getJRadioButton_left(), null);
            jContentPane.add(getJRadioButton_right(), null);
        }
        return jContentPane;
    }

    /**
     * This method initializes jRadioButton_up
     *
     * @return javax.swing.JRadioButton
     */
    private JRadioButton getJRadioButton_up() {
        if (jRadioButton_up == null) {
            jRadioButton_up = new JRadioButton();
            jRadioButton_up.setText("上");
            jRadioButton_up.setSelected(true);
            jRadioButton_up.setSize(new Dimension(40, 20));
            jRadioButton_up.setLocation(new Point(15, 15));
            buttonGroup_type.add(jRadioButton_up);
        }
        return jRadioButton_up;
    }

    /**
     * This method initializes jRadioButton_down
     *
     * @return javax.swing.JRadioButton
     */
    private JRadioButton getJRadioButton_down() {
        if (jRadioButton_down == null) {
            jRadioButton_down = new JRadioButton();
            jRadioButton_down.setText("下");
            jRadioButton_down.setSize(new Dimension(40, 20));
            jRadioButton_down.setLocation(new Point(60, 15));
            buttonGroup_type.add(jRadioButton_down);
        }
        return jRadioButton_down;
    }

    /**
     * This method initializes jRadioButton_left
     *
     * @return javax.swing.JRadioButton
     */
    private JRadioButton getJRadioButton_left() {
        if (jRadioButton_left == null) {
            jRadioButton_left = new JRadioButton();
            jRadioButton_left.setText("左");
            jRadioButton_left.setSize(new Dimension(40, 20));
            jRadioButton_left.setLocation(new Point(105, 15));
            buttonGroup_type.add(jRadioButton_left);
        }
        return jRadioButton_left;
    }

    /**
     * This method initializes jRadioButton_right
     *
     * @return javax.swing.JRadioButton
     */
    private JRadioButton getJRadioButton_right() {
        if (jRadioButton_right == null) {
            jRadioButton_right = new JRadioButton();
            jRadioButton_right.setText("右");
            jRadioButton_right.setSize(new Dimension(40, 20));
            jRadioButton_right.setLocation(new Point(150, 15));
            buttonGroup_type.add(jRadioButton_right);
        }
        return jRadioButton_right;
    }
}  //  @jve:decl-index=0:visual-constraint="10,10"

