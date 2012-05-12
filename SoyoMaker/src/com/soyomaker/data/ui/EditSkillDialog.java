/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * EditSkillDialog.java
 *
 * Created on 2011-9-17, 8:57:59
 */
package com.soyomaker.data.ui;

import com.soyomaker.AppData;
import com.soyomaker.data.DataManager;
import com.soyomaker.data.model.Cost;
import com.soyomaker.data.model.Effect;
import com.soyomaker.data.model.Factor;
import com.soyomaker.data.model.ModelAttributeTableModel;
import com.soyomaker.data.model.ModelCostTableModel;
import com.soyomaker.data.model.ModelEffectTableModel;
import com.soyomaker.data.model.ModelFactorTableModel;
import com.soyomaker.data.model.ModelStatusTableModel;
import com.soyomaker.data.model.Skill;
import com.soyomaker.model.animation.Animation;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author Administrator
 */
public class EditSkillDialog extends javax.swing.JDialog {

    /** Creates new form EditSkillDialog
     * @param parent
     * @param modal
     * @param skill
     */
    public EditSkillDialog(SkillPanel parent, boolean modal, Skill skill) {
        //super(parent, modal);
        setModal(modal);
        ictm = new ModelCostTableModel(skill.costs);
        ietm = new ModelEffectTableModel(skill.effects);
        iftm = new ModelFactorTableModel(skill.factors);
        matm = new ModelAttributeTableModel(skill.attributes);
        mstm = new ModelStatusTableModel(skill.status);
        initComponents();
        setLocationRelativeTo(null);
        skillIcons = DataManager.listSkillIconName();
        skillIconComboBox.addItem("");
        for (int i = 0; i < skillIcons.length; i++) {
            skillIconComboBox.addItem(skillIcons[i]);
        }
        skillSounds = DataManager.listSoundName();
        skillMenuSoundComboBox.addItem("");
        for (int i = 0; i < skillSounds.length; i++) {
            skillMenuSoundComboBox.addItem(skillSounds[i]);
        }
        skillUserAniComboBox.addItem("");
        java.util.Iterator it = AppData.getInstance().getCurProject().getAnimations().entrySet().iterator();
        while (it.hasNext()) {
            java.util.Map.Entry entry = (java.util.Map.Entry) it.next();
            skillUserAniComboBox.addItem(((Animation) entry.getValue()));
        }
        java.util.Iterator it1 = AppData.getInstance().getCurProject().getAnimations().entrySet().iterator();
        skillTargetAniComboBox.addItem("");
        while (it1.hasNext()) {
            java.util.Map.Entry entry = (java.util.Map.Entry) it1.next();
            skillTargetAniComboBox.addItem(((Animation) entry.getValue()));
        }
//        TableColumnModel tcm = costTable.getColumnModel();
//        TableColumn iconC = tcm.getColumn(0);
//        JTabelComboBoxRender strI = new JTabelComboBoxRender(Cost.costs);
//        JTableComboBoxEditor steI = new JTableComboBoxEditor(Cost.costs);
//        iconC.setCellRenderer(strI);
//        iconC.setCellEditor(steI);

//        TableColumnModel tcm1 = effectTable.getColumnModel();
//        TableColumn iconC1 = tcm1.getColumn(0);
//        JTabelComboBoxRender strI1 = new JTabelComboBoxRender(Effect.effects);
//        JTableComboBoxEditor steI1 = new JTableComboBoxEditor(Effect.effects);
//        iconC1.setCellRenderer(strI1);
//        iconC1.setCellEditor(steI1);

//        TableColumnModel tcm2 = factorTable.getColumnModel();
//        TableColumn iconC2 = tcm2.getColumn(0);
//        JTabelComboBoxRender strI2 = new JTabelComboBoxRender(Factor.factors);
//        JTableComboBoxEditor steI2 = new JTableComboBoxEditor(Factor.factors);
//        iconC2.setCellRenderer(strI2);
//        iconC2.setCellEditor(steI2);

//        TableColumnModel tcm3 = attributeTable.getColumnModel();
//        TableColumn iconC3 = tcm3.getColumn(0);
//        ArrayList<Attribute> attrs = ((Config) AppData.getInstance().getCurProject().getDataManager().getModels(Model.CONFIG)[0]).system.attributes;
//        String[] ats = new String[attrs.size()];
//        for (int i = 0; i < attrs.size(); i++) {
//            ats[i] = attrs.get(i).name;
//        }
//        JTabelComboBoxRender strI3 = new JTabelComboBoxRender(ats);
//        JTableComboBoxEditor steI3 = new JTableComboBoxEditor(ats);
//        iconC3.setCellRenderer(strI3);
//        iconC3.setCellEditor(steI3);
//
//        TableColumnModel tcm4 = statusTable.getColumnModel();
//        TableColumn iconC4 = tcm4.getColumn(0);
//        JTabelComboBoxRender strI4 = new JTabelComboBoxRender((Status[]) AppData.getInstance().getCurProject().getDataManager().getModels(Model.STATUS));
//        JTableComboBoxEditor steI4 = new JTableComboBoxEditor((Status[]) AppData.getInstance().getCurProject().getDataManager().getModels(Model.STATUS));
//        iconC4.setCellRenderer(strI4);
//        iconC4.setCellEditor(steI4);
        setSkill(skill);
    }
    private ImageIcon[] skillIcons;
    private String[] skillSounds;
    private Skill skill;
    private ModelCostTableModel ictm;
    private ModelEffectTableModel ietm;
    private ModelFactorTableModel iftm;
    private ModelAttributeTableModel matm;
    private ModelStatusTableModel mstm;

    private void setSkill(Skill skill) {
        this.skill = skill;
        skillNameTextField.setText(skill.name);
        skillIntroTextArea.setText(skill.intro);
        for (int i = 0; i < skillIcons.length; i++) {
            if (skill.icon.equals(skillIcons[i].getDescription())) {
                skillIconComboBox.setSelectedItem(skillIcons[i]);
                break;
            }
        }
        skillLevTextField.setText(skill.lev + "");
        skillTargetComboBox.setSelectedIndex(skill.target);
        skillLimitComboBox.setSelectedIndex(skill.limit);
        skillUserAniComboBox.setSelectedItem((Animation) AppData.getInstance().getCurProject().getAnimation(skill.userAniIndex));
        skillTargetAniComboBox.setSelectedItem((Animation) AppData.getInstance().getCurProject().getAnimation(skill.targetAniIndex));
        skillEventComboBox.setSelectedIndex(skill.eventIndex);
        skillMenuSoundComboBox.setSelectedItem(skill.menuUseSound);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        skillNameTextField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        skillIconComboBox = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        skillLevTextField = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        skillTargetComboBox = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        skillUserAniComboBox = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        skillLimitComboBox = new javax.swing.JComboBox();
        jLabel8 = new javax.swing.JLabel();
        skillTargetAniComboBox = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();
        skillMenuSoundComboBox = new javax.swing.JComboBox();
        jLabel10 = new javax.swing.JLabel();
        skillEventComboBox = new javax.swing.JComboBox();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        factorTable = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        statusTable = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        effectTable = new javax.swing.JTable();
        jScrollPane5 = new javax.swing.JScrollPane();
        attributeTable = new javax.swing.JTable();
        jScrollPane6 = new javax.swing.JScrollPane();
        costTable = new javax.swing.JTable();
        jToolBar1 = new javax.swing.JToolBar();
        addCostButton = new javax.swing.JButton();
        removeCostButton = new javax.swing.JButton();
        jToolBar2 = new javax.swing.JToolBar();
        addEffectButton = new javax.swing.JButton();
        removeEffectButton = new javax.swing.JButton();
        jToolBar3 = new javax.swing.JToolBar();
        addFactorButton = new javax.swing.JButton();
        removeFactorButton = new javax.swing.JButton();
        jToolBar4 = new javax.swing.JToolBar();
        addAttributeButton = new javax.swing.JButton();
        removeAttributeButton = new javax.swing.JButton();
        jToolBar5 = new javax.swing.JToolBar();
        addStatusButton = new javax.swing.JButton();
        removeStatusButton = new javax.swing.JButton();
        jScrollPane7 = new javax.swing.JScrollPane();
        skillIntroTextArea = new javax.swing.JTextArea();
        closeButton = new javax.swing.JButton();
        okButton = new javax.swing.JButton();

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable2.setName("jTable2"); // NOI18N
        jScrollPane2.setViewportView(jTable2);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("编辑技能");
        setResizable(false);

        jLabel1.setText("名称");
        jLabel1.setName("jLabel1"); // NOI18N

        skillNameTextField.setName("skillNameTextField"); // NOI18N

        jLabel2.setText("图标");
        jLabel2.setName("jLabel2"); // NOI18N

        skillIconComboBox.setName("skillIconComboBox"); // NOI18N

        jLabel3.setText("说明");
        jLabel3.setName("jLabel3"); // NOI18N

        jLabel4.setText("限制等级");
        jLabel4.setName("jLabel4"); // NOI18N

        skillLevTextField.setName("skillLevTextField"); // NOI18N

        jLabel5.setText("使用目标");
        jLabel5.setName("jLabel5"); // NOI18N

        skillTargetComboBox.setModel(new javax.swing.DefaultComboBoxModel(Skill.targets));
        skillTargetComboBox.setName("skillTargetComboBox"); // NOI18N

        jLabel6.setText("目标动画");
        jLabel6.setName("jLabel6"); // NOI18N

        skillUserAniComboBox.setName("skillUserAniComboBox"); // NOI18N

        jLabel7.setText("使用动画");
        jLabel7.setName("jLabel7"); // NOI18N

        skillLimitComboBox.setModel(new javax.swing.DefaultComboBoxModel(Skill.limits));
        skillLimitComboBox.setName("skillLimitComboBox"); // NOI18N

        jLabel8.setText("使用限制");
        jLabel8.setName("jLabel8"); // NOI18N

        skillTargetAniComboBox.setName("skillTargetAniComboBox"); // NOI18N

        jLabel9.setText("菜单使用音效");
        jLabel9.setName("jLabel9"); // NOI18N

        skillMenuSoundComboBox.setName("skillMenuSoundComboBox"); // NOI18N

        jLabel10.setText("公共事件编号");
        jLabel10.setName("jLabel10"); // NOI18N

        skillEventComboBox.setName("skillEventComboBox"); // NOI18N

        jPanel1.setName("jPanel1"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        factorTable.setModel(iftm);
        factorTable.setName("factorTable"); // NOI18N
        factorTable.setRowHeight(20);
        jScrollPane1.setViewportView(factorTable);

        jScrollPane3.setName("jScrollPane3"); // NOI18N

        statusTable.setModel(mstm);
        statusTable.setName("statusTable"); // NOI18N
        statusTable.setRowHeight(20);
        jScrollPane3.setViewportView(statusTable);

        jScrollPane4.setName("jScrollPane4"); // NOI18N

        effectTable.setModel(ietm);
        effectTable.setName("effectTable"); // NOI18N
        effectTable.setRowHeight(20);
        jScrollPane4.setViewportView(effectTable);

        jScrollPane5.setName("jScrollPane5"); // NOI18N

        attributeTable.setModel(matm);
        attributeTable.setName("attributeTable"); // NOI18N
        attributeTable.setRowHeight(20);
        jScrollPane5.setViewportView(attributeTable);

        jScrollPane6.setName("jScrollPane6"); // NOI18N

        costTable.setModel(ictm);
        costTable.setName("costTable"); // NOI18N
        costTable.setRowHeight(20);
        jScrollPane6.setViewportView(costTable);

        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);
        jToolBar1.setName("jToolBar1"); // NOI18N

        addCostButton.setText("添加消耗");
        addCostButton.setFocusable(false);
        addCostButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        addCostButton.setName("addCostButton"); // NOI18N
        addCostButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        addCostButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addCostButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(addCostButton);

        removeCostButton.setText("删除消耗");
        removeCostButton.setFocusable(false);
        removeCostButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        removeCostButton.setName("removeCostButton"); // NOI18N
        removeCostButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        removeCostButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeCostButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(removeCostButton);

        jToolBar2.setFloatable(false);
        jToolBar2.setRollover(true);
        jToolBar2.setName("jToolBar2"); // NOI18N

        addEffectButton.setText("添加效果");
        addEffectButton.setFocusable(false);
        addEffectButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        addEffectButton.setName("addEffectButton"); // NOI18N
        addEffectButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        addEffectButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addEffectButtonActionPerformed(evt);
            }
        });
        jToolBar2.add(addEffectButton);

        removeEffectButton.setText("删除效果");
        removeEffectButton.setFocusable(false);
        removeEffectButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        removeEffectButton.setName("removeEffectButton"); // NOI18N
        removeEffectButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        removeEffectButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeEffectButtonActionPerformed(evt);
            }
        });
        jToolBar2.add(removeEffectButton);

        jToolBar3.setFloatable(false);
        jToolBar3.setRollover(true);
        jToolBar3.setName("jToolBar3"); // NOI18N

        addFactorButton.setText("添加因数");
        addFactorButton.setFocusable(false);
        addFactorButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        addFactorButton.setName("addFactorButton"); // NOI18N
        addFactorButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        addFactorButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addFactorButtonActionPerformed(evt);
            }
        });
        jToolBar3.add(addFactorButton);

        removeFactorButton.setText("删除因数");
        removeFactorButton.setFocusable(false);
        removeFactorButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        removeFactorButton.setName("removeFactorButton"); // NOI18N
        removeFactorButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        removeFactorButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeFactorButtonActionPerformed(evt);
            }
        });
        jToolBar3.add(removeFactorButton);

        jToolBar4.setFloatable(false);
        jToolBar4.setRollover(true);
        jToolBar4.setName("jToolBar4"); // NOI18N

        addAttributeButton.setText("添加属性");
        addAttributeButton.setFocusable(false);
        addAttributeButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        addAttributeButton.setName("addAttributeButton"); // NOI18N
        addAttributeButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        addAttributeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addAttributeButtonActionPerformed(evt);
            }
        });
        jToolBar4.add(addAttributeButton);

        removeAttributeButton.setText("删除属性");
        removeAttributeButton.setFocusable(false);
        removeAttributeButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        removeAttributeButton.setName("removeAttributeButton"); // NOI18N
        removeAttributeButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        removeAttributeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeAttributeButtonActionPerformed(evt);
            }
        });
        jToolBar4.add(removeAttributeButton);

        jToolBar5.setFloatable(false);
        jToolBar5.setRollover(true);
        jToolBar5.setName("jToolBar5"); // NOI18N

        addStatusButton.setText("添加状态");
        addStatusButton.setFocusable(false);
        addStatusButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        addStatusButton.setName("addStatusButton"); // NOI18N
        addStatusButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        addStatusButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addStatusButtonActionPerformed(evt);
            }
        });
        jToolBar5.add(addStatusButton);

        removeStatusButton.setText("删除状态");
        removeStatusButton.setFocusable(false);
        removeStatusButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        removeStatusButton.setName("removeStatusButton"); // NOI18N
        removeStatusButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        removeStatusButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeStatusButtonActionPerformed(evt);
            }
        });
        jToolBar5.add(removeStatusButton);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jToolBar1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jToolBar2, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToolBar3, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToolBar4, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jToolBar5, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jScrollPane1, jScrollPane3, jScrollPane4, jScrollPane5, jScrollPane6, jToolBar1, jToolBar2, jToolBar3, jToolBar4, jToolBar5});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jToolBar2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane4, 0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jToolBar3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jToolBar4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane5, 0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jToolBar5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jScrollPane1, jScrollPane3, jScrollPane4, jScrollPane5, jScrollPane6});

        jScrollPane7.setName("jScrollPane7"); // NOI18N

        skillIntroTextArea.setColumns(20);
        skillIntroTextArea.setLineWrap(true);
        skillIntroTextArea.setRows(5);
        skillIntroTextArea.setName("skillIntroTextArea"); // NOI18N
        jScrollPane7.setViewportView(skillIntroTextArea);

        closeButton.setText("关闭");
        closeButton.setName("closeButton"); // NOI18N
        closeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeButtonActionPerformed(evt);
            }
        });

        okButton.setText("确定");
        okButton.setName("okButton"); // NOI18N
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel3))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(skillNameTextField)
                                    .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel7))
                                .addGap(26, 26, 26)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(skillEventComboBox, 0, 151, Short.MAX_VALUE)
                                    .addComponent(skillTargetComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(skillIconComboBox, 0, 140, Short.MAX_VALUE)
                                    .addComponent(skillUserAniComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel4))
                                .addGap(29, 29, 29)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(skillMenuSoundComboBox, 0, 166, Short.MAX_VALUE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(skillLimitComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(skillTargetAniComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(skillLevTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(322, 322, 322)
                        .addComponent(okButton, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(closeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {skillEventComboBox, skillIconComboBox, skillLevTextField, skillLimitComboBox, skillMenuSoundComboBox, skillTargetAniComboBox, skillTargetComboBox, skillUserAniComboBox});

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {closeButton, okButton});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(skillNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane7, 0, 0, Short.MAX_VALUE)
                            .addComponent(jLabel3)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(skillIconComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(skillTargetComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel8))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(skillUserAniComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel6)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(skillLevTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(skillLimitComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(skillTargetAniComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                            .addComponent(skillEventComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10)
                            .addComponent(skillMenuSoundComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(okButton)
                    .addComponent(closeButton))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {skillEventComboBox, skillIconComboBox, skillLevTextField, skillLimitComboBox, skillMenuSoundComboBox, skillTargetAniComboBox, skillTargetComboBox, skillUserAniComboBox});

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
        // TODO add your handling code here:
        skill.name = skillNameTextField.getText();
        skill.intro = skillIntroTextArea.getText();
        skill.icon = skillIconComboBox.getSelectedItem().toString();
        skill.lev = Integer.parseInt(skillLevTextField.getText());
        skill.target = skillTargetComboBox.getSelectedIndex();
        skill.limit = skillLimitComboBox.getSelectedIndex();
        if (skillUserAniComboBox.getSelectedItem() == null || skillUserAniComboBox.getSelectedItem().toString().equals("")) {
            skill.userAniIndex = -1;
        } else {
            skill.userAniIndex = ((Animation) skillUserAniComboBox.getSelectedItem()).getIndex();
        }
        if (skillTargetAniComboBox.getSelectedItem() == null || skillTargetAniComboBox.getSelectedItem().toString().equals("")) {
            skill.targetAniIndex = -1;
        } else {
            skill.targetAniIndex = ((Animation) skillTargetAniComboBox.getSelectedItem()).getIndex();
        }
        skill.eventIndex = skillEventComboBox.getSelectedIndex();
        skill.menuUseSound = skillMenuSoundComboBox.getSelectedItem().toString();
        dispose();
    }//GEN-LAST:event_okButtonActionPerformed

    private void closeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeButtonActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_closeButtonActionPerformed

    private void addCostButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addCostButtonActionPerformed
        // TODO add your handling code here:
        Cost cost = new Cost();
        cost.costType = skill.costs.size();
        skill.costs.add(cost);
        costTable.updateUI();
    }//GEN-LAST:event_addCostButtonActionPerformed

    private void removeCostButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeCostButtonActionPerformed
        // TODO add your handling code here:
//        if (costTable.getSelectedRow() == 0) {
//            JOptionPane.showMessageDialog(this,
//                    "默认数据不能删除！");
//            return;
//        }
        if (costTable.getSelectedRow() >= 0 && costTable.getSelectedRow() < skill.costs.size()) {
            skill.costs.remove(costTable.getSelectedRow());
            costTable.updateUI();
        }

    }//GEN-LAST:event_removeCostButtonActionPerformed

    private void addEffectButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addEffectButtonActionPerformed
        // TODO add your handling code here:
        Effect effect = new Effect();
        effect.effectType = skill.effects.size();
        skill.effects.add(effect);
        effectTable.updateUI();
    }//GEN-LAST:event_addEffectButtonActionPerformed

    private void removeEffectButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeEffectButtonActionPerformed
        // TODO add your handling code here:
//        if (effectTable.getSelectedRow() == 0) {
//            JOptionPane.showMessageDialog(this,
//                    "默认数据不能删除！");
//            return;
//        }
        if (effectTable.getSelectedRow() >= 0 && effectTable.getSelectedRow() < skill.effects.size()) {
            skill.effects.remove(effectTable.getSelectedRow());
        }
        effectTable.updateUI();
    }//GEN-LAST:event_removeEffectButtonActionPerformed

    private void addFactorButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addFactorButtonActionPerformed
        // TODO add your handling code here:
        Factor factor = new Factor();
        factor.factorType = skill.factors.size();
        skill.factors.add(factor);
        factorTable.updateUI();
    }//GEN-LAST:event_addFactorButtonActionPerformed

    private void removeFactorButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeFactorButtonActionPerformed
        // TODO add your handling code here:
//        if (factorTable.getSelectedRow() >= 0 && factorTable.getSelectedRow() < 6) {
//            JOptionPane.showMessageDialog(this,
//                    "默认数据不能删除！");
//            return;
//        }
        if (factorTable.getSelectedRow() >= 0 && factorTable.getSelectedRow() < skill.factors.size()) {
            skill.factors.remove(factorTable.getSelectedRow());
        }
        factorTable.updateUI();
    }//GEN-LAST:event_removeFactorButtonActionPerformed

    private void addAttributeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addAttributeButtonActionPerformed
        // TODO add your handling code here:
        AddModelAttributeDialog etd = new AddModelAttributeDialog(this, true);
        etd.setVisible(true);
        if (etd.getSelectAttribute() != null) {
            skill.attributes.add(etd.getSelectAttribute());
            attributeTable.updateUI();
        }
    }//GEN-LAST:event_addAttributeButtonActionPerformed

    private void removeAttributeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeAttributeButtonActionPerformed
        // TODO add your handling code here:
        if (attributeTable.getSelectedRow() >= 0 && attributeTable.getSelectedRow() < skill.attributes.size()) {
            skill.attributes.remove(attributeTable.getSelectedRow());
        }
        attributeTable.updateUI();
    }//GEN-LAST:event_removeAttributeButtonActionPerformed

    private void addStatusButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addStatusButtonActionPerformed
        // TODO add your handling code here:
        AddModelStatusDialog etd = new AddModelStatusDialog(this, true);
        etd.setVisible(true);
        if (etd.getSelectStatus() != null) {
            skill.status.add(etd.getSelectStatus());
            statusTable.updateUI();
        }
    }//GEN-LAST:event_addStatusButtonActionPerformed

    private void removeStatusButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeStatusButtonActionPerformed
        // TODO add your handling code here:
        if (statusTable.getSelectedRow() >= 0 && statusTable.getSelectedRow() < skill.status.size()) {
            skill.status.remove(statusTable.getSelectedRow());
        }
        statusTable.updateUI();
    }//GEN-LAST:event_removeStatusButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addAttributeButton;
    private javax.swing.JButton addCostButton;
    private javax.swing.JButton addEffectButton;
    private javax.swing.JButton addFactorButton;
    private javax.swing.JButton addStatusButton;
    private javax.swing.JTable attributeTable;
    private javax.swing.JButton closeButton;
    private javax.swing.JTable costTable;
    private javax.swing.JTable effectTable;
    private javax.swing.JTable factorTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTable jTable2;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JToolBar jToolBar2;
    private javax.swing.JToolBar jToolBar3;
    private javax.swing.JToolBar jToolBar4;
    private javax.swing.JToolBar jToolBar5;
    private javax.swing.JButton okButton;
    private javax.swing.JButton removeAttributeButton;
    private javax.swing.JButton removeCostButton;
    private javax.swing.JButton removeEffectButton;
    private javax.swing.JButton removeFactorButton;
    private javax.swing.JButton removeStatusButton;
    private javax.swing.JComboBox skillEventComboBox;
    private javax.swing.JComboBox skillIconComboBox;
    private javax.swing.JTextArea skillIntroTextArea;
    private javax.swing.JTextField skillLevTextField;
    private javax.swing.JComboBox skillLimitComboBox;
    private javax.swing.JComboBox skillMenuSoundComboBox;
    private javax.swing.JTextField skillNameTextField;
    private javax.swing.JComboBox skillTargetAniComboBox;
    private javax.swing.JComboBox skillTargetComboBox;
    private javax.swing.JComboBox skillUserAniComboBox;
    private javax.swing.JTable statusTable;
    // End of variables declaration//GEN-END:variables
}
