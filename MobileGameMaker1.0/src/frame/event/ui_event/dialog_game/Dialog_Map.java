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
import java.awt.Rectangle;
import javax.swing.JComboBox;
import engine.MapEditor;
import java.awt.Toolkit;

public class Dialog_Map extends Event_Dialog {

    private JPanel jContentPane = null;
    private JLabel jLabel = null;
    private ButtonGroup buttonGroup_type = null;  //  @jve:decl-index=0:
    private JTextField jTextField_x = null;
    private JLabel jLabel1 = null;
    private JLabel jLabel2 = null;
    private JComboBox jComboBox_map = null;
    private JTextField jTextField_y = null;
    private Dialog_Main p;

    public Dialog_Map(Dialog_Main parent) {
        super(parent);
        p = parent;
        initialize();
        initJComboBox();
    }

    /**
     * This method initializes this
     *
     */
    private void initialize() {
        buttonGroup_type = new ButtonGroup();
        this.setSize(new Dimension(250, 205));
         this.setTitle("切换场景");
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
        String sc = "MAP " + jComboBox_map.getSelectedIndex() + " " + getJTextField_y().getText() + " " + getJTextField_x().getText();
        p.insertEventTM(p.getEventDialog().curRow, sc);
        return false;
    }

    private void initJComboBox() {
        jComboBox_map.removeAllItems();
        int ii = MapEditor.getInstance().getMapVector().size();
        for (int i = 0; i
            < ii; i++) {
            //限制文件格式
            jComboBox_map.addItem(MapEditor.getInstance().getMapVector().mapAt(i).getName());
        }
    }

    /**
     * This method initializes jContentPane
     *
     * @return javax.swing.JPanel
     */
    private JPanel getJContentPane() {
        if (jContentPane == null) {
            jLabel2 = new JLabel();
            jLabel2.setText("行");
            jLabel2.setLocation(new Point(15, 50));
            jLabel2.setHorizontalAlignment(SwingConstants.CENTER);
            jLabel2.setSize(new Dimension(60, 20));
            jLabel1 = new JLabel();
            jLabel1.setText("列");
            jLabel1.setLocation(new Point(15, 85));
            jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
            jLabel1.setSize(new Dimension(60, 20));
            jLabel = new JLabel();
            jLabel.setHorizontalAlignment(SwingConstants.CENTER);
            jLabel.setHorizontalTextPosition(SwingConstants.CENTER);
            jLabel.setLocation(new Point(15, 15));
            jLabel.setSize(new Dimension(60, 20));
            jLabel.setText("地图ID");
            jContentPane = new JPanel();
            jContentPane.setLayout(null);
            jContentPane.add(jLabel, null);
            jContentPane.add(getJTextField_x(), null);
            jContentPane.add(jLabel1, null);
            jContentPane.add(jLabel2, null);
            jContentPane.add(getJComboBox_map(), null);
            jContentPane.add(getJTextField_y(), null);
        }
        return jContentPane;
    }

    /**
     * This method initializes jTextField_x
     *
     * @return javax.swing.JTextField
     */
    private JTextField getJTextField_x() {
        if (jTextField_x == null) {
            jTextField_x = new JTextField();
            jTextField_x.setLocation(new Point(80, 85));
            jTextField_x.setSize(new Dimension(120, 20));
        }
        return jTextField_x;
    }

    /**
     * This method initializes jComboBox_map
     *
     * @return javax.swing.JComboBox
     */
    private JComboBox getJComboBox_map() {
        if (jComboBox_map == null) {
            jComboBox_map = new JComboBox();
            jComboBox_map.setLocation(new Point(80, 15));
            jComboBox_map.setSize(new Dimension(120, 20));
        }
        return jComboBox_map;
    }

    /**
     * This method initializes jTextField_y
     *
     * @return javax.swing.JTextField
     */
    private JTextField getJTextField_y() {
        if (jTextField_y == null) {
            jTextField_y = new JTextField();
            jTextField_y.setLocation(new Point(80, 50));
            jTextField_y.setSize(new Dimension(120, 20));
        }
        return jTextField_y;
    }
}  //  @jve:decl-index=0:visual-constraint="10,10"

