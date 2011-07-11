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
import java.awt.Rectangle;
import java.awt.Toolkit;
import javax.swing.JTextArea;
import java.io.*;
import model.*;

public class Dialog_Music extends Event_Dialog {

    private JPanel jContentPane = null;
    private JLabel jLabel = null;
    private ButtonGroup buttonGroup_type = null;  //  @jve:decl-index=0:
    private JComboBox jComboBox_music = null;
    private Dialog_Main p;

    public Dialog_Music(Dialog_Main parent) {
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
        this.setSize(new Dimension(254, 141));
        this.setContentPane(getJContentPane());
         this.setTitle("开启音乐");
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
        String sc = "PLAYMUSIC " + jComboBox_music.getSelectedItem().toString();
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
            jContentPane.add(getJComboBox_music(), null);
        }
        return jContentPane;
    }

    /**
     * This method initializes jComboBox_music
     *
     * @return javax.swing.JComboBox
     */
    private JComboBox getJComboBox_music() {
        if (jComboBox_music == null) {
            jComboBox_music = new JComboBox();
            jComboBox_music.setSize(new Dimension(180, 20));
            jComboBox_music.setLocation(new Point(50, 15));
            File[] files = new File(Project.getProjectPath() + "\\" + "audio\\music").listFiles();
            for (int i = 0; i < files.length; i++) {
                if (files[i].getName().endsWith(".mid")) {

                    jComboBox_music.addItem(files[i].getName());
                }

            }
        }
        return jComboBox_music;
    }
}  //  @jve:decl-index=0:visual-constraint="10,10"

