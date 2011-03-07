package frame.dm;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.table.DefaultTableModel;
import module.AnimationManager;

import model.Project;
import model.Skill;

public class SkillTab extends JPanel implements MouseListener, ItemListener,
        CaretListener {

    private static final long serialVersionUID = 1L;
    private String[] col = {"编号", "名称"};
    private int index = 0;// 自动生成的技能编号
    private DefaultTableModel skillTM = null; // 定义一个表的模板
    private String[] skillKind = {"单体攻击", "全体攻击", "辅助自身"};
    private JLabel jLabel = null;
    private ImageIcon[] icons = null;
    private JLabel jLabel1 = null;
    private JLabel jLabel2 = null;
    private JTextField jTextField = null;
    private JComboBox jComboBox = null;
    private JScrollPane jScrollPane = null;
    private JTable jTable = null;
    private JLabel jLabel4 = null;
    private JTextField jTextField1 = null;
    private JLabel jLabel5 = null;
    private JComboBox jComboBox1 = null;
    private JComboBox jComboBox2 = null;
    private JLabel jLabel6 = null;
    private JTextField jTextField2 = null;
    private JLabel jLabel7 = null;
    private JTextField jTextField3 = null;
    private JLabel jLabel12 = null;
    private JLabel jLabel13 = null;
    private JLabel jLabel16 = null;
    private JLabel jLabel17 = null;
    private JLabel jLabel18 = null;
    private JLabel jLabel19 = null;
    private JTextField jTextField8 = null;
    private JTextField jTextField9 = null;
    private JTextField jTextField12 = null;
//    private JTextField jTextField13 = null;
    private JTextField jTextField14 = null;
    private JTextField jTextField15 = null;
    private JPopupMenu jPopupMenu = null; // @jve:decl-index=0:visual-constraint="633,166"
    private JMenuItem jMenuItem = null;
    private JMenuItem jMenuItem1 = null;
    public int curIndex = 0;// 当前技能的编号
    private int curRow = 0;// 当前行号
    private JLabel jLabel3 = null;
    private JTextField jTextField16 = null;

    public SkillTab() {
        super();
        skillTM = new DefaultTableModel(col, 0);
        initialize();
    }

    private void addRow() {
        Skill skill = new Skill();
        skill.setIndex(index++);
        Project.getSkillList().addSkill(skill);
        String[] rowData = {skill.getIndex() + "", skill.getName()};
        skillTM.addRow(rowData);
//        curRow = skillTM.getRowCount() - 1;
    }

    public void caretUpdate(CaretEvent e) {
        // TODO 监听名称框的字符变化
        if (curRow <= 0) {
            curRow = 0;
        }
        skillTM.setValueAt(jTextField.getText(), curRow, 1);
    }

    private void changeUI(int kind) {
        if (kind == Skill.RECOVER) {
//            jLabel6.setText("SP回复：");
            jLabel7.setText("HP回复：");
            jLabel12.setText("物理攻击增益：");
            jLabel13.setText("物理防御增益：");
//			jLabel16.setText("消耗金钱：");
//			jLabel17.setText("播放动画：");
            jLabel18.setText("攻击速度增益：");
        } else {
//            jLabel6.setText("SP消耗：");
            jLabel7.setText("HP伤害：");
            jLabel12.setText("物理攻击削弱：");
            jLabel13.setText("物理防御削弱：");
//			jLabel16.setText("消耗金钱：");
//			jLabel17.setText("播放动画：");
            jLabel18.setText("攻击速度削弱：");
        }
    }

    private boolean checkData() {
        boolean judge = true;
        if (jTextField2.getText().trim().equals("")) {
            jTextField2.setText(0 + "");
        }
        if (jTextField3.getText().trim().equals("")) {
            jTextField3.setText(0 + "");
        }
        if (jTextField8.getText().trim().equals("")) {
            jTextField8.setText(0 + "");
        }
        if (jTextField9.getText().trim().equals("")) {
            jTextField9.setText(0 + "");
        }
        if (jTextField12.getText().trim().equals("")) {
            jTextField12.setText(0 + "");
        }
//        if (jTextField13.getText().trim().equals("")) {
//            jTextField13.setText(0 + "");
//        }
        if (jTextField14.getText().trim().equals("")) {
            jTextField14.setText(0 + "");
        }
        if (jTextField15.getText().trim().equals("")) {
            jTextField15.setText(0 + "");
        }
        if (jTextField16.getText().trim().equals("")) {
            jTextField16.setText(0 + "");
        }

        try {
            Integer.parseInt(jTextField2.getText());
            Integer.parseInt(jTextField3.getText());
            Integer.parseInt(jTextField8.getText());
            Integer.parseInt(jTextField9.getText());
            Integer.parseInt(jTextField12.getText());
//            Integer.parseInt(jTextField13.getText());
            Integer.parseInt(jTextField14.getText());
            Integer.parseInt(jTextField15.getText());
            Integer.parseInt(jTextField16.getText());

        } catch (Exception e) {
            judge = false;
        }
        return judge;

    }

    private void delRow() {
        int num = jTable.getSelectedRow();
        if (num != -1) {
            Project.getSkillList().delskill(num);
            skillTM.removeRow(num);
            if (skillTM.getRowCount() == 0) {
                index = 0;
                addRow();
            }
            curRow = 0;
            curIndex = Integer.parseInt(((String) skillTM.getValueAt(curRow, 0)));
            switchSkill(curIndex);
            index--;
        }

    }

    /**
     * This method initializes jComboBox
     *
     * @return javax.swing.JComboBox
     */
    private JComboBox getJComboBox() {
        if (jComboBox == null) {
            jComboBox = new JComboBox();
            jComboBox.setLocation(new Point(450, 10));
            jComboBox.setSize(new Dimension(100, 20));
            icons = listFileName("image\\icon\\skill");
            for (int i = 0; i < icons.length; i++) {
                jComboBox.addItem(icons[i]);
            }
        }
        return jComboBox;
    }

    /**
     * This method initializes jComboBox1
     *
     * @return javax.swing.JComboBox
     */
    private JComboBox getJComboBox1() {
        if (jComboBox1 == null) {
            jComboBox1 = new JComboBox();
            jComboBox1.setLocation(new Point(250, 70));
            jComboBox1.setSize(new Dimension(120, 20));
            jComboBox1.addItemListener(this);
            for (int i = 0; i < skillKind.length; i++) {
                jComboBox1.addItem(skillKind[i]);
            }
        }
        return jComboBox1;
    }
    private AnimationManager aniManager = AnimationManager.getInstance(); // @jve:decl-index=0:

    private JComboBox getJComboBox2() {
        if (jComboBox2 == null) {
            jComboBox2 = new JComboBox();
            jComboBox2.setLocation(new Point(480, 250));
            jComboBox2.setSize(new Dimension(70, 20));
            for (int i = 0; i < aniManager.getAnimationMaxNum(); i++) {
                jComboBox2.addItem(aniManager.getAllAnimations()[i]);
            }
        }
        return jComboBox2;
    }

    /**
     * This method initializes jMenuItem
     *
     * @return javax.swing.JMenuItem
     */
    private JMenuItem getJMenuItem() {
        if (jMenuItem == null) {
            jMenuItem = new JMenuItem();
            jMenuItem.setText("新建");
            jMenuItem.addActionListener(new java.awt.event.ActionListener() {

                public void actionPerformed(java.awt.event.ActionEvent e) {
                    addRow();
                }
            });
        }
        return jMenuItem;
    }

    /**
     * This method initializes jMenuItem1
     *
     * @return javax.swing.JMenuItem
     */
    private JMenuItem getJMenuItem1() {
        if (jMenuItem1 == null) {
            jMenuItem1 = new JMenuItem();
            jMenuItem1.setText("删除");
            jMenuItem1.addActionListener(new java.awt.event.ActionListener() {

                public void actionPerformed(java.awt.event.ActionEvent e) {
                    delRow();
                }
            });
        }
        return jMenuItem1;
    }

    /**
     * This method initializes jPopupMenu
     *
     * @return javax.swing.JPopupMenu
     */
    private JPopupMenu getJPopupMenu() {
        if (jPopupMenu == null) {
            jPopupMenu = new JPopupMenu();
            jPopupMenu.setSize(new Dimension(61, 100));
            jPopupMenu.add(getJMenuItem());
            jPopupMenu.add(getJMenuItem1());
        }
        return jPopupMenu;
    }

    /**
     * This method initializes jScrollPane
     *
     * @return javax.swing.JScrollPane
     */
    private JScrollPane getJScrollPane() {
        if (jScrollPane == null) {
            jScrollPane = new JScrollPane();
            jScrollPane.setSize(new Dimension(160, 270));
            jScrollPane.setLocation(new Point(10, 40));
            jScrollPane.setViewportView(getJTable());
        }
        return jScrollPane;
    }

    /**
     * This method initializes jTable
     *
     * @return javax.swing.JTable
     */
    private JTable getJTable() {
        if (jTable == null) {
            jTable = new JTable();
            jTable.addMouseListener(this);
            jTable.setModel(skillTM);
        }
        return jTable;
    }

    /**
     * This method initializes jTextField
     *
     * @return javax.swing.JTextField
     */
    private JTextField getJTextField() {
        if (jTextField == null) {
            jTextField = new JTextField();
            jTextField.setLocation(new Point(250, 10));
            jTextField.setSize(new Dimension(120, 20));
            jTextField.addCaretListener(this);
        }
        return jTextField;
    }

    /**
     * This method initializes jTextField1
     *
     * @return javax.swing.JTextField
     */
    private JTextField getJTextField1() {
        if (jTextField1 == null) {
            jTextField1 = new JTextField();
            jTextField1.setSize(new Dimension(300, 20));
            jTextField1.setLocation(new Point(250, 38));
        }
        return jTextField1;
    }

    /**
     * This method initializes jTextField12
     *
     * @return javax.swing.JTextField
     */
    private JTextField getJTextField12() {
        if (jTextField12 == null) {
            jTextField12 = new JTextField();
            jTextField12.setLocation(new Point(300, 250));
            jTextField12.setSize(new Dimension(70, 20));
        }
        return jTextField12;
    }

//    /**
//     * This method initializes jTextField13
//     *
//     * @return javax.swing.JTextField
//     */
//    private JTextField getJTextField13() {
//        if (jTextField13 == null) {
//            jTextField13 = new JTextField();
//            jTextField13.setLocation(new Point(480, 250));
//            jTextField13.setSize(new Dimension(70, 20));
//        }
//        return jTextField13;
//    }
    /**
     * This method initializes jTextField14
     *
     * @return javax.swing.JTextField
     */
    private JTextField getJTextField14() {
        if (jTextField14 == null) {
            jTextField14 = new JTextField();
            jTextField14.setLocation(new Point(300, 280));
            jTextField14.setSize(new Dimension(70, 20));
        }
        return jTextField14;
    }

    /**
     * This method initializes jTextField15
     *
     * @return javax.swing.JTextField
     */
    private JTextField getJTextField15() {
        if (jTextField15 == null) {
            jTextField15 = new JTextField();
            jTextField15.setLocation(new Point(480, 280));
            jTextField15.setSize(new Dimension(70, 20));
        }
        return jTextField15;
    }

    /**
     * This method initializes jTextField16
     *
     * @return javax.swing.JTextField
     */
    private JTextField getJTextField16() {
        if (jTextField16 == null) {
            jTextField16 = new JTextField();
            jTextField16.setLocation(new Point(460, 70));
            jTextField16.setSize(new Dimension(90, 20));
        }
        return jTextField16;
    }

    /**
     * This method initializes jTextField2
     *
     * @return javax.swing.JTextField
     */
    private JTextField getJTextField2() {
        if (jTextField2 == null) {
            jTextField2 = new JTextField();
            jTextField2.setSize(new Dimension(100, 20));
            jTextField2.setLocation(new Point(450, 100));
        }
        return jTextField2;
    }

    /**
     * This method initializes jTextField3
     *
     * @return javax.swing.JTextField
     */
    private JTextField getJTextField3() {
        if (jTextField3 == null) {
            jTextField3 = new JTextField();
            jTextField3.setSize(new Dimension(90, 20));
            jTextField3.setLocation(new Point(280, 100));
        }
        return jTextField3;
    }

    /**
     * This method initializes jTextField8
     *
     * @return javax.swing.JTextField
     */
    private JTextField getJTextField8() {
        if (jTextField8 == null) {
            jTextField8 = new JTextField();
            jTextField8.setSize(new Dimension(70, 20));
            jTextField8.setLocation(new Point(300, 190));
        }
        return jTextField8;
    }

    /**
     * This method initializes jTextField9
     *
     * @return javax.swing.JTextField
     */
    private JTextField getJTextField9() {
        if (jTextField9 == null) {
            jTextField9 = new JTextField();
            jTextField9.setLocation(new Point(480, 190));
            jTextField9.setSize(new Dimension(70, 20));
        }
        return jTextField9;
    }

    /**
     * This method initializes this
     *
     */
    private void initialize() {
        jLabel3 = new JLabel();
        jLabel3.setText("技能等级：");
        jLabel3.setSize(new Dimension(70, 20));
        jLabel3.setLocation(new Point(390, 70));
        jLabel19 = new JLabel();
        jLabel19.setText("技能分散度：");
        jLabel19.setSize(new Dimension(100, 20));
        jLabel19.setLocation(new Point(390, 280));
        jLabel18 = new JLabel();
        jLabel18.setText("攻击速度削弱：");
        jLabel18.setSize(new Dimension(100, 20));
        jLabel18.setLocation(new Point(210, 280));
        jLabel17 = new JLabel();
        jLabel17.setText("播放动画：");
        jLabel17.setSize(new Dimension(100, 20));
        jLabel17.setLocation(new Point(390, 250));
        jLabel16 = new JLabel();
        jLabel16.setText("消耗金钱：");
        jLabel16.setSize(new Dimension(100, 20));
        jLabel16.setLocation(new Point(210, 250));
        jLabel13 = new JLabel();
        jLabel13.setText("物理防御削弱：");
        jLabel13.setSize(new Dimension(100, 20));
        jLabel13.setLocation(new Point(390, 190));
        jLabel12 = new JLabel();
        jLabel12.setText("物理攻击削弱：");
        jLabel12.setSize(new Dimension(100, 20));
        jLabel12.setLocation(new Point(210, 190));
        jLabel7 = new JLabel();
        jLabel7.setText("HP回复：");
        jLabel7.setSize(new Dimension(70, 20));
        jLabel7.setLocation(new Point(210, 100));
        jLabel6 = new JLabel();
        jLabel6.setText("SP消耗：");
        jLabel6.setSize(new Dimension(60, 20));
        jLabel6.setLocation(new Point(390, 100));
        jLabel5 = new JLabel();
        jLabel5.setText("类型：");
        jLabel5.setSize(new Dimension(40, 20));
        jLabel5.setLocation(new Point(210, 70));
        jLabel4 = new JLabel();
        jLabel4.setText("说明：");
        jLabel4.setSize(new Dimension(40, 20));
        jLabel4.setLocation(new Point(210, 38));
        jLabel2 = new JLabel();
        jLabel2.setText("图标：");
        jLabel2.setSize(new Dimension(40, 20));
        jLabel2.setDisplayedMnemonic(KeyEvent.VK_UNDEFINED);
        jLabel2.setLocation(new Point(390, 10));
        jLabel1 = new JLabel();
        jLabel1.setText("名称：");
        jLabel1.setSize(new Dimension(40, 20));
        jLabel1.setLocation(new Point(210, 10));
        jLabel = new JLabel();
        jLabel.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel.setLocation(new Point(10, 10));
        jLabel.setSize(new Dimension(160, 20));
        jLabel.setText("技能列表");
        this.setLayout(null);
        this.setSize(new Dimension(566, 320));
        this.add(jLabel, null);
        this.add(jLabel1, null);
        this.add(jLabel2, null);
        this.add(getJTextField(), null);
        this.add(getJComboBox(), null);
        this.add(getJScrollPane(), null);
        this.add(jLabel4, null);
        this.add(getJTextField1(), null);
        this.add(jLabel5, null);
        this.add(getJComboBox1(), null);
        this.add(jLabel6, null);
        this.add(getJTextField2(), null);
        this.add(jLabel7, null);
        this.add(getJTextField3(), null);
//		this.add(jLabel9, null);
//		this.add(getJTextField5(), null);
//		this.add(jLabel10, null);
//		this.add(jLabel11, null);
//		this.add(getJTextField6(), null);
//		this.add(getJTextField7(), null);
        this.add(jLabel12, null);
        this.add(jLabel13, null);
//		this.add(jLabel14, null);
//		this.add(jLabel15, null);
        this.add(jLabel16, null);
        this.add(jLabel17, null);
        this.add(jLabel18, null);
        this.add(jLabel19, null);
        this.add(getJTextField8(), null);
        this.add(getJTextField9(), null);
//		this.add(getJTextField10(), null);
//		this.add(getJTextField11(), null);
        this.add(getJTextField12(), null);
//        this.add(getJTextField13(), null);
        this.add(getJComboBox2(), null);
        this.add(getJTextField14(), null);
        this.add(getJTextField15(), null);

        this.add(jLabel3, null);
        this.add(getJTextField16(), null);
    }

    public void itemStateChanged(ItemEvent e) {
        // TODO 根据技能类型不同修改编辑框的可编辑性
        if (e.getSource().equals(jComboBox1)) {
            if (e.getStateChange() == ItemEvent.DESELECTED) {
                changeUI(jComboBox1.getSelectedIndex());
            }
        }

    }

    private ImageIcon[] listFileName(String path) {
        File f = new File(Project.getProjectPath() + "\\" + path);
        String[] s = f.list();
        ImageIcon[] ii = new ImageIcon[s.length];
        for (int i = 0; i < s.length; i++) {
            ii[i] = new ImageIcon(Project.getProjectPath() + "\\" + path + "\\"
                    + s[i], s[i]);
        }
        return ii;
    }

    public void mouseClicked(MouseEvent e) {
        // TODO 弹出菜单和切换技能
        if (e.getButton() == MouseEvent.BUTTON3) {
            // 右键弹出菜单
            getJPopupMenu();
            jPopupMenu.show(e.getComponent(), e.getX(), e.getY());
        } else if (e.getButton() == MouseEvent.BUTTON1) {
            // 左键进行技能切换
            if (checkData()) {
                saveSkill(curIndex);
                curIndex = Integer.parseInt((String) skillTM.getValueAt(jTable.getSelectedRow(), 0));
                switchSkill(curIndex);
            } else {
                JOptionPane.showMessageDialog(this, "请输入正确的整数");
            }
        }
    }

    public void mouseEntered(MouseEvent e) {
        // TODO 自动生成方法存根
    }

    public void mouseExited(MouseEvent e) {
        // TODO 自动生成方法存根
    }

    public void mousePressed(MouseEvent e) {
        // TODO 自动生成方法存根
    }

    public void mouseReleased(MouseEvent e) {
        // TODO 自动生成方法存根
    }

    public void saveSkill(int curIndex) {
        // TODO 保存技能
        Skill skill = Project.getSkillList().getSkill(curIndex);
        if (skill != null) {
            skill.setName(jTextField.getText());
            skill.setImgName(((ImageIcon) jComboBox.getSelectedItem()).getDescription());
            skill.setRemark(jTextField1.getText());
            skill.setKind(jComboBox1.getSelectedIndex());
            skill.setSp(Integer.parseInt(jTextField2.getText()));
            skill.setHp(Integer.parseInt(jTextField3.getText()));
            skill.setLev(Integer.parseInt(jTextField16.getText()));
            skill.setAtk(Integer.parseInt(jTextField8.getText()));
            skill.setDef(Integer.parseInt(jTextField9.getText()));
            skill.setPrice(Integer.parseInt(jTextField12.getText()));
            skill.setAniIndex(jComboBox2.getSelectedIndex());
            skill.setAspeed(Integer.parseInt(jTextField14.getText()));
            skill.setDpy(Integer.parseInt(jTextField15.getText()));
        }
        curRow = jTable.getSelectedRow();

    }

    private void switchSkill(int curIndex) {
        Skill skill = Project.getSkillList().getSkill(curIndex);
        jTextField.setText(skill.getName());
        int i = 0;
        for (i = 0; i < icons.length; i++) {
            if (skill.getImgName() == null
                    || skill.getImgName().equals(icons[i].getDescription())) {
                break;
            }
        }
        if (i != 0) {
            jComboBox.setSelectedIndex(i);
        }

        jTextField1.setText(skill.getRemark());
        jComboBox1.setSelectedIndex(skill.getKind());
        changeUI(skill.getKind());
        jTextField2.setText(skill.getSp() + "");
        jTextField16.setText(skill.getLev() + "");
        jTextField15.setText(skill.getDpy() + "");
//		if (skill.getKind() == Skill.RECOVER) {
//			jTextField3.setText(skill.getHp() + "");
//			jTextField8.setText(skill.getAtk() + "");
//			jTextField9.setText(skill.getDef() + "");
//			jTextField12.setText(skill.getPrice() + "");
//			jTextField13.setText(skill.getAniIndex() + "");
//			jTextField14.setText(skill.getAspeed() + "");
//		} else {
        jTextField3.setText(skill.getHp() + "");
        jTextField8.setText(skill.getAtk() + "");
        jTextField9.setText(skill.getDef() + "");
        jTextField12.setText(skill.getPrice() + "");
//        jTextField13.setText(skill.getAniIndex() + "");
        if (skill.getAniIndex() > 0) {
            jComboBox2.setSelectedIndex(skill.getAniIndex());
        }

        jTextField14.setText(skill.getAspeed() + "");
//		}

    }

    public void updateList() {
        // TODO 更新列表

        // 清除现在表格中的所有数据
        int num = skillTM.getRowCount();
        for (int i = 0; i < num; i++) {
            skillTM.removeRow(0);
        }

        if (Project.getSkillList().size() == 0) {
            // 如果技能列表中没有技能
            addRow();
        } else {
            // 技能列表中有技能

            // 初始化下一技能的编号
            index = Project.getSkillList().getMaxIndex() + 1;

            // 重新显示数据
            num = Project.getSkillList().size();
            Skill skill = null;
            for (int i = 0; i < num; i++) {
                skill = Project.getSkillList().skillAt(i);
                String[] rowData = {skill.getIndex() + "", skill.getName()};
                skillTM.addRow(rowData);
            }
        }
        switchSkill(curIndex);
    }
} // @jve:decl-index=0:visual-constraint="42,-4"

