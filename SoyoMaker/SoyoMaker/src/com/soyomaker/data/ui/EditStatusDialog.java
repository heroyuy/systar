/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * EditStatusDialog.java
 *
 * Created on 2011-9-23, 23:15:16
 */
package com.soyomaker.data.ui;

import com.soyomaker.AppData;
import com.soyomaker.data.DataManager;
import com.soyomaker.data.model.Condition;
import com.soyomaker.data.model.ModelAttributeTableModel;
import com.soyomaker.data.model.ModelConditionTableModel;
import com.soyomaker.data.model.ModelParameterTableModel;
import com.soyomaker.data.model.ModelStatusTableModel;
import com.soyomaker.data.model.Parameter;
import com.soyomaker.data.model.Status;
import com.soyomaker.model.animation.Animation;
import com.soyomaker.widget.JTabelComboBoxRender;
import com.soyomaker.widget.JTableComboBoxEditor;
import java.util.Iterator;
import javax.swing.ImageIcon;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author Administrator
 */
public class EditStatusDialog extends javax.swing.JDialog {

    /** Creates new form EditStatusDialog
     * @param parent
     * @param modal
     * @param status
     */
    public EditStatusDialog(StatusPanel parent, boolean modal, Status status) {
        setModal(modal);
        matm = new ModelAttributeTableModel(status.attrs);
        mstm = new ModelStatusTableModel(status.status);
        mctm = new ModelConditionTableModel(status.conds);
        mptm = new ModelParameterTableModel(status.paras);
        initComponents();
        setLocationRelativeTo(null);
        statusIcons = DataManager.listStatusIconName();
        statusIconComboBox.addItem("");
        for (int i = 0; i < statusIcons.length; i++) {
            statusIconComboBox.addItem(statusIcons[i]);
        }
        statusAniComboBox.addItem("");
        java.util.Iterator it = AppData.getInstance().getCurProject().getAnimations().entrySet().iterator();
        while (it.hasNext()) {
            java.util.Map.Entry entry = (java.util.Map.Entry) it.next();
            statusAniComboBox.addItem(((Animation) entry.getValue()));
        }
//        TableColumnModel tcm = parameterTable.getColumnModel();
//        TableColumn iconC = tcm.getColumn(0);
//        JTabelComboBoxRender strI = new JTabelComboBoxRender(Parameter.types);
//        JTableComboBoxEditor steI = new JTableComboBoxEditor(Parameter.types);
//        iconC.setCellRenderer(strI);
//        iconC.setCellEditor(steI);

        TableColumnModel tcm2 = parameterTable.getColumnModel();
        TableColumn iconC2 = tcm2.getColumn(1);
        JTabelComboBoxRender strI2 = new JTabelComboBoxRender(Parameter.rules);
        JTableComboBoxEditor steI2 = new JTableComboBoxEditor(Parameter.rules);
        iconC2.setCellRenderer(strI2);
        iconC2.setCellEditor(steI2);

//        TableColumnModel tcm1 = conditionTable.getColumnModel();
//        TableColumn iconC1 = tcm1.getColumn(0);
//        JTabelComboBoxRender strI1 = new JTabelComboBoxRender(Condition.statusConditionTypes);
//        JTableComboBoxEditor steI1 = new JTableComboBoxEditor(Condition.statusConditionTypes);
//        iconC1.setCellRenderer(strI1);
//        iconC1.setCellEditor(steI1);
//
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

        setStatus(status);
    }
    private Status status;
    private ImageIcon[] statusIcons;
    private ModelAttributeTableModel matm;
    private ModelStatusTableModel mstm;
    private ModelConditionTableModel mctm;
    private ModelParameterTableModel mptm;

    private void setStatus(Status status) {
        this.status = status;
        statusNameTextField.setText(status.name);
        statusIntroTextArea.setText(status.description);
        for (int i = 0; i < statusIcons.length; i++) {
            if (status.icon.equals(statusIcons[i].getDescription())) {
                statusIconComboBox.setSelectedItem(statusIcons[i]);
                break;
            }
        }
        statusLevTextField.setText(status.lev + "");
        statusAniComboBox.setSelectedItem((Animation) AppData.getInstance().getCurProject().getAnimation(status.aniIndex));
        statusKindComboBox.setSelectedIndex(status.type);
        statusLastTypeComboBox.setSelectedIndex(status.lastType);
        statusLastValueTextField.setText(status.lastValue + "");
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        statusNameTextField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        statusIntroTextArea = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        statusIconComboBox = new javax.swing.JComboBox();
        statusKindComboBox = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        statusLevTextField = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        statusAniComboBox = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        statusLastTypeComboBox = new javax.swing.JComboBox();
        jLabel8 = new javax.swing.JLabel();
        statusLastValueTextField = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jToolBar1 = new javax.swing.JToolBar();
        addParameterButton = new javax.swing.JButton();
        removeParameterButton = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        parameterTable = new javax.swing.JTable();
        jToolBar2 = new javax.swing.JToolBar();
        addConditionButton = new javax.swing.JButton();
        removeConditionButton = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        conditionTable = new javax.swing.JTable();
        jToolBar3 = new javax.swing.JToolBar();
        addAttributeButton = new javax.swing.JButton();
        removeAttributeButton = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        attributeTable = new javax.swing.JTable();
        jToolBar4 = new javax.swing.JToolBar();
        addStatusButton = new javax.swing.JButton();
        removeStatusButton = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        statusTable = new javax.swing.JTable();
        okButton = new javax.swing.JButton();
        closeButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance().getContext().getResourceMap(EditStatusDialog.class);
        setTitle(resourceMap.getString("title")); // NOI18N
        setResizable(false);

        jLabel1.setText("名称");
        jLabel1.setName("jLabel1"); // NOI18N

        statusNameTextField.setName("statusNameTextField"); // NOI18N

        jLabel2.setText("说明");
        jLabel2.setName("jLabel2"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        statusIntroTextArea.setColumns(20);
        statusIntroTextArea.setLineWrap(true);
        statusIntroTextArea.setRows(5);
        statusIntroTextArea.setName("statusIntroTextArea"); // NOI18N
        jScrollPane1.setViewportView(statusIntroTextArea);

        jLabel3.setText("图标");
        jLabel3.setName("jLabel3"); // NOI18N

        jLabel4.setText("类型");
        jLabel4.setName("jLabel4"); // NOI18N

        statusIconComboBox.setName("statusIconComboBox"); // NOI18N

        statusKindComboBox.setModel(new javax.swing.DefaultComboBoxModel(Status.kinds));
        statusKindComboBox.setName("statusKindComboBox"); // NOI18N
        statusKindComboBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                statusKindComboBoxItemStateChanged(evt);
            }
        });

        jLabel5.setText("等级");
        jLabel5.setName("jLabel5"); // NOI18N

        statusLevTextField.setText("0");
        statusLevTextField.setName("statusLevTextField"); // NOI18N

        jLabel6.setText("动画");
        jLabel6.setName("jLabel6"); // NOI18N

        statusAniComboBox.setName("statusAniComboBox"); // NOI18N

        jLabel7.setText("持续类型");
        jLabel7.setName("jLabel7"); // NOI18N

        switch(statusKindComboBox.getSelectedIndex()){
            case Status.LIMIT:
            statusLastTypeComboBox.setModel(new javax.swing.DefaultComboBoxModel(Status.limitKinds));
            break;
            case Status.LAST:
            statusLastTypeComboBox.setModel(new javax.swing.DefaultComboBoxModel(Status.lastKinds));
            break;
        }
        statusLastTypeComboBox.setName("statusLastTypeComboBox"); // NOI18N

        jLabel8.setText("持续值");
        jLabel8.setName("jLabel8"); // NOI18N

        statusLastValueTextField.setText("0");
        statusLastValueTextField.setName("statusLastValueTextField"); // NOI18N

        jPanel1.setName("jPanel1"); // NOI18N

        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);
        jToolBar1.setName("jToolBar1"); // NOI18N

        addParameterButton.setText("添加参数");
        addParameterButton.setFocusable(false);
        addParameterButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        addParameterButton.setName("addParameterButton"); // NOI18N
        addParameterButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        addParameterButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addParameterButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(addParameterButton);

        removeParameterButton.setText("删除参数");
        removeParameterButton.setFocusable(false);
        removeParameterButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        removeParameterButton.setName("removeParameterButton"); // NOI18N
        removeParameterButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        removeParameterButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeParameterButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(removeParameterButton);

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        parameterTable.setModel(mptm);
        parameterTable.setName("parameterTable"); // NOI18N
        jScrollPane2.setViewportView(parameterTable);

        jToolBar2.setFloatable(false);
        jToolBar2.setRollover(true);
        jToolBar2.setName("jToolBar2"); // NOI18N

        addConditionButton.setText("添加条件");
        addConditionButton.setFocusable(false);
        addConditionButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        addConditionButton.setName("addConditionButton"); // NOI18N
        addConditionButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        addConditionButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addConditionButtonActionPerformed(evt);
            }
        });
        jToolBar2.add(addConditionButton);

        removeConditionButton.setText("删除条件");
        removeConditionButton.setFocusable(false);
        removeConditionButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        removeConditionButton.setName("removeConditionButton"); // NOI18N
        removeConditionButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        removeConditionButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeConditionButtonActionPerformed(evt);
            }
        });
        jToolBar2.add(removeConditionButton);

        jScrollPane3.setName("jScrollPane3"); // NOI18N

        conditionTable.setModel(mctm);
        conditionTable.setName("conditionTable"); // NOI18N
        jScrollPane3.setViewportView(conditionTable);

        jToolBar3.setFloatable(false);
        jToolBar3.setRollover(true);
        jToolBar3.setName("jToolBar3"); // NOI18N

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
        jToolBar3.add(addAttributeButton);

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
        jToolBar3.add(removeAttributeButton);

        jScrollPane4.setName("jScrollPane4"); // NOI18N

        attributeTable.setModel(matm);
        attributeTable.setName("attributeTable"); // NOI18N
        jScrollPane4.setViewportView(attributeTable);

        jToolBar4.setFloatable(false);
        jToolBar4.setRollover(true);
        jToolBar4.setName("jToolBar4"); // NOI18N

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
        jToolBar4.add(addStatusButton);

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
        jToolBar4.add(removeStatusButton);

        jScrollPane5.setName("jScrollPane5"); // NOI18N

        statusTable.setModel(mstm);
        statusTable.setName("statusTable"); // NOI18N
        jScrollPane5.setViewportView(statusTable);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jToolBar2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jToolBar3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jToolBar4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jScrollPane2, jScrollPane3, jScrollPane4, jScrollPane5, jToolBar1, jToolBar2, jToolBar3, jToolBar4});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jToolBar4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane5, 0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jToolBar3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane4, 0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jToolBar2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, 0, 0, Short.MAX_VALUE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        okButton.setText("确定");
        okButton.setName("okButton"); // NOI18N
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });

        closeButton.setText("关闭");
        closeButton.setName("closeButton"); // NOI18N
        closeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeButtonActionPerformed(evt);
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
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(statusNameTextField))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel7))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(statusLastTypeComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(statusIconComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(statusKindComboBox, 0, 197, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel8)
                            .addComponent(jLabel5))
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(statusLastValueTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
                            .addComponent(statusAniComboBox, 0, 184, Short.MAX_VALUE)
                            .addComponent(statusLevTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(300, 300, 300)
                        .addComponent(okButton, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(closeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(statusNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(statusIconComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5)
                        .addComponent(statusLevTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(statusKindComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)
                            .addComponent(statusAniComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(statusLastTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(statusLastValueTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel2)
                    .addComponent(jScrollPane1, 0, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(closeButton)
                    .addComponent(okButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
        // TODO add your handling code here:
        status.name = statusNameTextField.getText();
        status.description = statusIntroTextArea.getText();
        status.icon = statusIconComboBox.getSelectedItem().toString();
        status.lev = Integer.parseInt(statusLevTextField.getText());
        if (statusAniComboBox.getSelectedItem() == null || statusAniComboBox.getSelectedItem().toString().equals("")) {
            status.aniIndex = -1;
        } else {
            status.aniIndex = ((Animation) statusAniComboBox.getSelectedItem()).getIndex();
        }
        status.type = statusKindComboBox.getSelectedIndex();
        status.lastType = statusLastTypeComboBox.getSelectedIndex();
        status.lastValue = Integer.parseInt(statusLastValueTextField.getText());
        dispose();
}//GEN-LAST:event_okButtonActionPerformed

    private void closeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeButtonActionPerformed
        // TODO add your handling code here:
        dispose();
}//GEN-LAST:event_closeButtonActionPerformed

    private void statusKindComboBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_statusKindComboBoxItemStateChanged
        // TODO add your handling code here:
        switch (statusKindComboBox.getSelectedIndex()) {
            case Status.LIMIT:
                statusLastTypeComboBox.setModel(new javax.swing.DefaultComboBoxModel(Status.limitKinds));
                break;
            case Status.LAST:
                statusLastTypeComboBox.setModel(new javax.swing.DefaultComboBoxModel(Status.lastKinds));
                break;
        }
    }//GEN-LAST:event_statusKindComboBoxItemStateChanged

    private void addAttributeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addAttributeButtonActionPerformed
        // TODO add your handling code here:
        AddModelAttributeDialog etd = new AddModelAttributeDialog(this, true);
        etd.setVisible(true);
        if (etd.getSelectAttribute() != null) {
            status.attrs.add(etd.getSelectAttribute());
            attributeTable.updateUI();
        }
    }//GEN-LAST:event_addAttributeButtonActionPerformed

    private void removeAttributeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeAttributeButtonActionPerformed
        // TODO add your handling code here:
        if (attributeTable.getSelectedRow() >= 0 && attributeTable.getSelectedRow() < status.attrs.size()) {
            status.attrs.remove(attributeTable.getSelectedRow());
        }
        attributeTable.updateUI();
    }//GEN-LAST:event_removeAttributeButtonActionPerformed

    private void addStatusButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addStatusButtonActionPerformed
        // TODO add your handling code here:
        AddModelStatusDialog amsd = new AddModelStatusDialog(this, true);
        amsd.setVisible(true);
        if (amsd.getSelectStatus() != null) {
            status.status.add(amsd.getSelectStatus());
            statusTable.updateUI();
        }
    }//GEN-LAST:event_addStatusButtonActionPerformed

    private void removeStatusButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeStatusButtonActionPerformed
        // TODO add your handling code here:
        if (statusTable.getSelectedRow() >= 0 && statusTable.getSelectedRow() < status.status.size()) {
            status.status.remove(statusTable.getSelectedRow());
        }
        statusTable.updateUI();
    }//GEN-LAST:event_removeStatusButtonActionPerformed

    private void addParameterButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addParameterButtonActionPerformed
        // TODO add your handling code here:
        Parameter para = new Parameter();
//        int max = -1;
//        Iterator<Parameter> it = status.paras.iterator();
//        while (it.hasNext()) {
//            Parameter m = (Parameter) it.next();
//            if (m.type > max) {
//                max = m.type;
//            }
//        }
//        para.type = max + 1;
        para.type = status.paras.size();
        status.paras.add(para);
        parameterTable.updateUI();
    }//GEN-LAST:event_addParameterButtonActionPerformed

    private void removeParameterButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeParameterButtonActionPerformed
        // TODO add your handling code here:
        if (parameterTable.getSelectedRow() >= 0 && parameterTable.getSelectedRow() < status.paras.size()) {
            status.paras.remove(parameterTable.getSelectedRow());
        }
        parameterTable.updateUI();
    }//GEN-LAST:event_removeParameterButtonActionPerformed

    private void addConditionButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addConditionButtonActionPerformed
        // TODO add your handling code here:
        Condition cond = new Condition();
        cond.conditionType = status.conds.size();
        status.conds.add(cond);
        conditionTable.updateUI();
    }//GEN-LAST:event_addConditionButtonActionPerformed

    private void removeConditionButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeConditionButtonActionPerformed
        // TODO add your handling code here:
        if (conditionTable.getSelectedRow() >= 0 && conditionTable.getSelectedRow() < status.conds.size()) {
            status.conds.remove(conditionTable.getSelectedRow());
        }
        conditionTable.updateUI();
    }//GEN-LAST:event_removeConditionButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                EditStatusDialog dialog = new EditStatusDialog(null, true, null);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {

                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addAttributeButton;
    private javax.swing.JButton addConditionButton;
    private javax.swing.JButton addParameterButton;
    private javax.swing.JButton addStatusButton;
    private javax.swing.JTable attributeTable;
    private javax.swing.JButton closeButton;
    private javax.swing.JTable conditionTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JToolBar jToolBar2;
    private javax.swing.JToolBar jToolBar3;
    private javax.swing.JToolBar jToolBar4;
    private javax.swing.JButton okButton;
    private javax.swing.JTable parameterTable;
    private javax.swing.JButton removeAttributeButton;
    private javax.swing.JButton removeConditionButton;
    private javax.swing.JButton removeParameterButton;
    private javax.swing.JButton removeStatusButton;
    private javax.swing.JComboBox statusAniComboBox;
    private javax.swing.JComboBox statusIconComboBox;
    private javax.swing.JTextArea statusIntroTextArea;
    private javax.swing.JComboBox statusKindComboBox;
    private javax.swing.JComboBox statusLastTypeComboBox;
    private javax.swing.JTextField statusLastValueTextField;
    private javax.swing.JTextField statusLevTextField;
    private javax.swing.JTextField statusNameTextField;
    private javax.swing.JTable statusTable;
    // End of variables declaration//GEN-END:variables
}
