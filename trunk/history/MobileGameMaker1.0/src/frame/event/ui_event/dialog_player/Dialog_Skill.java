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
import model.*;

public class Dialog_Skill extends Event_Dialog {

    private JPanel jContentPane = null;
    private JRadioButton jRadioButton_reduce = null;
    private JRadioButton jRadioButton_plus = null;
    private ButtonGroup buttonGroup_type = null;  //  @jve:decl-index=0:
    private ButtonGroup buttonGroup_oper = null;  //  @jve:decl-index=0:
    private JLabel jLabel = null;
    private JComboBox jComboBox_skill = null;
//	private String[] attributeItems={"MaxHp","MaxSp","力量","敏捷","智力"};
    private Dialog_Main p;

    public Dialog_Skill(Dialog_Main parent) {
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
        buttonGroup_oper = new ButtonGroup();
        this.setSize(new Dimension(262, 177));
         this.setTitle("技能调整");
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
        String sc;
        if (type == 0) {
            sc = "$SKILL[" + jComboBox_skill.getSelectedIndex() + "]=true";
        } else {
            sc = "$SKILL[" + jComboBox_skill.getSelectedIndex() + "]=false";
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
            jLabel.setText("技能");
            jLabel.setLocation(new Point(15, 15));
            jLabel.setSize(new Dimension(40, 20));
            jContentPane = new JPanel();
            jContentPane.setLayout(null);
            jContentPane.add(getJRadioButton_reduce(), null);
            jContentPane.add(getJRadioButton_plus(), null);
            jContentPane.add(jLabel, null);
            jContentPane.add(getJComboBox_skill(), null);
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
            jRadioButton_reduce.setText("遗忘");
            jRadioButton_reduce.setLocation(new Point(50, 50));
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

    /**
     * This method initializes jRadioButton_plus
     *
     * @return javax.swing.JRadioButton
     */
    private JRadioButton getJRadioButton_plus() {
        if (jRadioButton_plus == null) {
            jRadioButton_plus = new JRadioButton();
            jRadioButton_plus.setText("领悟");
            jRadioButton_plus.setSelected(true);
            jRadioButton_plus.setSize(new Dimension(60, 20));
            jRadioButton_plus.setLocation(new Point(120, 50));
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
    private int type = 0;

    /**
     * This method initializes jComboBox_skill
     * 	
     * @return javax.swing.JComboBox
     */
    private JComboBox getJComboBox_skill() {
        if (jComboBox_skill == null) {
            jComboBox_skill = new JComboBox();
            jComboBox_skill.setSize(new Dimension(170, 20));
            jComboBox_skill.setLocation(new Point(60, 15));
            jComboBox_skill.removeAllItems();
            int n = Project.getSkillList().size();
            for (int i = 0; i < n; i++) {
                jComboBox_skill.addItem(Project.getSkillList().skillAt(i).getName());
            }
//            for (int i = 0; i < attributeItems.length; i++) {
//
//                jComboBox_skill.addItem(attributeItems[i]);
//            }
        }
        return jComboBox_skill;
    }
}  //  @jve:decl-index=0:visual-constraint="10,10"

