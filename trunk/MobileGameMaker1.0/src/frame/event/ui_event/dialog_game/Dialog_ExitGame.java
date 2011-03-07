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
import javax.swing.JComboBox;
import java.awt.Rectangle;
import java.awt.Toolkit;

public class Dialog_ExitGame extends Event_Dialog {

    private JPanel jContentPane = null;
    private JLabel jLabel = null;
    private Dialog_Main p;

    public Dialog_ExitGame(Dialog_Main parent) {
        super(parent);
        p = parent;
        initialize();
    }

    /**
     * This method initializes this
     *
     */
    private void initialize() {
        this.setSize(new Dimension(276, 146));
        this.setContentPane(getJContentPane());
        this.getContentPane().setLayout(null);
         this.setTitle("退出游戏");
        addDefaultButton();
        Dimension screenSize =
            Toolkit.getDefaultToolkit().getScreenSize();
        Dimension labelSize = this.getSize();
        this.setLocation(screenSize.width / 2 - (labelSize.width / 2),
            screenSize.height / 2 - (labelSize.height / 2));//设置位置屏幕中部
    }

    public boolean confirm() {
        // TODO 自动生成方法存根
        String sc = "GAMEOVER";
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
            jLabel.setSize(new Dimension(229, 20));
            jLabel.setText("是否确定插入\"退出游戏\"命令？");
            jContentPane = new JPanel();
            jContentPane.setLayout(null);
            jContentPane.add(jLabel, null);
        }
        return jContentPane;
    }
}  //  @jve:decl-index=0:visual-constraint="10,10"

