/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * NpcStatePane.java
 *
 * Created on 2011-6-7, 1:45:46
 */
package com.soyomaker.widget;

import com.soyomaker.dialog.NpcHeadImageDialog;
import com.soyomaker.dialog.NpcMoveImageDialog;
import com.soyomaker.dialog.ScriptDialog;
import com.soyomaker.model.map.Command;
import com.soyomaker.model.map.NpcState;
import com.soyomaker.model.map.NpcStateCondition;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

/**
 *
 * @author Administrator
 */
public class NpcStatePane extends javax.swing.JPanel {

    /** Creates new form NpcStatePane
     */
    public NpcStatePane() {
        initComponents();
        //处理命令节点拖动的鼠标事件监听器
        MouseListener ml = new MouseAdapter() {
            // 按下鼠标时候获得被拖动的节点

            @Override
            public void mousePressed(MouseEvent e) {
                // 如果需要唯一确定某个节点，必须通过TreePath来获取。
                TreePath tp = commandTree.getPathForLocation(e.getX(), e.getY());
                if (tp != null) {
                    movePath = tp;
                    NpcStatePane.this.setCursor(new Cursor(Cursor.HAND_CURSOR));
                }
            }

            // 鼠标松开时获得需要拖到哪个父节点  
            @Override
            public void mouseReleased(MouseEvent e) {
                // 根据鼠标松开时的TreePath来获取TreePath
                TreePath tp = commandTree.getPathForLocation(e.getX(), e.getY());
                NpcStatePane.this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                if (tp != null && movePath != null) {
                    // 阻止向子节点拖动
                    if (movePath.isDescendant(tp) && movePath != tp) {
                        return;
                    } // 既不是向子节点移动，而且鼠标按下、松开的不是同一个节点
                    else if (movePath != tp) {
                        System.out.println(tp.getLastPathComponent());
                        DefaultMutableTreeNode lastnode = (DefaultMutableTreeNode) movePath.getLastPathComponent();
                        DefaultMutableTreeNode lastParent = (DefaultMutableTreeNode) lastnode.getParent();
                        DefaultMutableTreeNode node = (DefaultMutableTreeNode) tp.getLastPathComponent();
                        DefaultMutableTreeNode parent = (DefaultMutableTreeNode) node.getParent();
                        //表示不能拖动“命令”这个父节点，或者移动到命令这个节点上下
                        if (parent != null && lastParent != null) {
                            Command lastCommand = (Command) lastnode.getUserObject();
                            //如果拖动的节点不是命令的直接子节点，那么取得该节点的父节点Command，
                            if (lastParent.getUserObject() instanceof Command) {
                                Command lastParentCommand = (Command) lastParent.getUserObject();
                                //如果拖动目的节点不是“命令”的直接子节点，那么取得该目的节点的父节点Command
                                if (parent.getUserObject() instanceof Command) {
                                    Command parentCommand = (Command) parent.getUserObject();
                                    lastParentCommand.removeCommand(lastCommand);
                                    int id = parent.getIndex(node);
                                    parentCommand.addCommand(id, lastCommand);
                                    parent.insert((DefaultMutableTreeNode) movePath.getLastPathComponent(), id);
                                } else {
                                    //如果拖动目的节点是“命令”的直接子节点
                                    lastParentCommand.removeCommand(lastCommand);
                                    int id = parent.getIndex(node);
                                    npcState.getScript().addCommand(id, lastCommand);
                                    parent.insert((DefaultMutableTreeNode) movePath.getLastPathComponent(), id);
                                }
                            } else {
                                //如果拖动目的节点是“命令”的直接子节点
                                if (parent.getUserObject() instanceof Command) {
                                    Command parentCommand = (Command) parent.getUserObject();
                                    npcState.getScript().removeCommand(lastCommand);
                                    int id = parent.getIndex(node);
                                    parentCommand.addCommand(id, lastCommand);
                                    parent.insert((DefaultMutableTreeNode) movePath.getLastPathComponent(), id);
                                } else {
                                    //如果拖动目的节点是“命令”的直接子节点
                                    npcState.getScript().removeCommand(lastCommand);
                                    int id = parent.getIndex(node);
                                    npcState.getScript().addCommand(id, lastCommand);
                                    parent.insert((DefaultMutableTreeNode) movePath.getLastPathComponent(), id);
                                }
                            }
                            movePath = null;
                            commandTree.updateUI();
                        }
                    }
                }
            }
        };
        commandTree.addMouseListener(ml);
    }
    private NpcState npcState = new NpcState();
    private boolean isInitOver = false;
    private ScriptDialog sd = null;
    private TreePath movePath = null; // 定义需要被拖动的TreePath

    /**
     * 
     * @return
     */
    public NpcState getNpcState() {
        return npcState;
    }

    /**
     * 
     * @param state
     */
    public void setNpcState(NpcState state) {
        isInitOver = false;
        npcState = state;
        typeComboBox.setSelectedIndex(state.getMove());
        speedComboBox.setSelectedIndex(state.getSpeed() - 1);
        crossCheckBox.setSelected(state.isCross());
        npcMoveImgPane.repaint();
        npcHeadImgPane.repaint();
        for (int i = 0; i < state.getScript().commandsSize(); i++) {
            addTreeNode((DefaultMutableTreeNode) commandTree.getModel().getRoot(), state.getScript().getCommand(i));
        }
        if (state.getSwitchCondition() != null) {
            switchCheckBox.setSelected(true);
            switchComboBox.setEnabled(true);
            onComboBox.setEnabled(true);
            switchComboBox.setSelectedIndex(state.getSwitchCondition().paramList[0]);
            onComboBox.setSelectedIndex(state.getSwitchCondition().paramList[1]);
        } else {
            switchCheckBox.setSelected(false);
            switchComboBox.setEnabled(false);
            onComboBox.setEnabled(false);
        }
        if (state.getVarCondition() != null) {
            varCheckBox.setSelected(true);
            varComboBox.setEnabled(true);
            varOperationTypeComboBox.setEnabled(true);
            varTextField.setEnabled(true);
            varComboBox.setSelectedIndex(state.getVarCondition().paramList[0]);
            varOperationTypeComboBox.setSelectedIndex(state.getVarCondition().paramList[1]);
            varTextField.setText("" + state.getVarCondition().paramList[2]);
        } else {
            varCheckBox.setSelected(false);
            varComboBox.setEnabled(false);
            varOperationTypeComboBox.setEnabled(false);
            varTextField.setEnabled(false);
        }
        isInitOver = true;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        switchCheckBox = new javax.swing.JCheckBox();
        varCheckBox = new javax.swing.JCheckBox();
        switchComboBox = new javax.swing.JComboBox();
        varComboBox = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        varTextField = new javax.swing.JTextField();
        varOperationTypeComboBox = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        onComboBox = new javax.swing.JComboBox();
        jToolBar1 = new javax.swing.JToolBar();
        addScriptButton = new javax.swing.JButton();
        removeScriptButton = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        npcMoveImgPane = new javax.swing.JPanel(){
            public void paint(Graphics g){
                super.paint(g);
                if(npcState.getImg()!=null){
                    Image img = null;
                    switch(npcState.getFace()){
                        case NpcState.DOWN:
                        img = npcState.getImg().getSubimage(0, 0,
                            npcState.getImg().getWidth(null)/4,
                            npcState.getImg().getHeight(null)/4);
                        break;
                        case NpcState.LEFT:
                        img = npcState.getImg().getSubimage(0, npcState.getImg().getHeight(null)/4,
                            npcState.getImg().getWidth(null)/4,
                            npcState.getImg().getHeight(null)/4);
                        break;
                        case NpcState.RIGHT:
                        img = npcState.getImg().getSubimage(0, npcState.getImg().getHeight(null)/2,
                            npcState.getImg().getWidth(null)/4,
                            npcState.getImg().getHeight(null)/4);
                        break;
                        case NpcState.UP:
                        img = npcState.getImg().getSubimage(0, npcState.getImg().getHeight(null)*3/4,
                            npcState.getImg().getWidth(null)/4,
                            npcState.getImg().getHeight(null)/4);
                        break;
                    }
                    g.drawImage(img,this.getWidth()/2-img.getWidth(null)/2, this.getHeight()/2-img.getHeight(null)/2,null);
                }
            }
        }
        ;
        jLabel4 = new javax.swing.JLabel();
        npcHeadImgPane = new javax.swing.JPanel(){
            public void paint(Graphics g){
                super.paint(g);
                if(npcState.getHeadImg()!=null){
                    g.drawImage(npcState.getHeadImg(), 0, 0,null);
                }
            }
        }
        ;
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        typeComboBox = new javax.swing.JComboBox();
        speedComboBox = new javax.swing.JComboBox();
        jPanel6 = new javax.swing.JPanel();
        crossCheckBox = new javax.swing.JCheckBox();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        commandTree = new com.soyomaker.widget.SoyoCommandTree();

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("事件出现条件"));
        jPanel1.setName("jPanel1"); // NOI18N

        switchCheckBox.setText("开关");
        switchCheckBox.setName("switchCheckBox"); // NOI18N
        switchCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                switchCheckBoxActionPerformed(evt);
            }
        });

        varCheckBox.setText("变量");
        varCheckBox.setName("varCheckBox"); // NOI18N
        varCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                varCheckBoxActionPerformed(evt);
            }
        });

        switchComboBox.setEnabled(false);
        switchComboBox.setFocusTraversalPolicyProvider(true);
        switchComboBox.setName("switchComboBox"); // NOI18N
        for(int i =0;i<100;i++){
            switchComboBox.addItem(i);
        }
        switchComboBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                switchComboBoxItemStateChanged(evt);
            }
        });

        varComboBox.setEnabled(false);
        varComboBox.setName("varComboBox"); // NOI18N
        for(int i =0;i<100;i++){
            varComboBox.addItem(i);
        }
        varComboBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                varComboBoxItemStateChanged(evt);
            }
        });

        jLabel1.setText("值为");
        jLabel1.setName("jLabel1"); // NOI18N

        varTextField.setText("0");
        varTextField.setEnabled(false);
        varTextField.setName("varTextField"); // NOI18N
        varTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                varTextFieldKeyReleased(evt);
            }
        });

        varOperationTypeComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "等于", "大于", "大于等于", "小于", "小于等于", "不等于" }));
        varOperationTypeComboBox.setEnabled(false);
        varOperationTypeComboBox.setName("varOperationTypeComboBox"); // NOI18N
        varOperationTypeComboBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                varOperationTypeComboBoxItemStateChanged(evt);
            }
        });

        jLabel3.setText("时");
        jLabel3.setName("jLabel3"); // NOI18N

        jLabel2.setText("为");
        jLabel2.setName("jLabel2"); // NOI18N

        onComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "ON", "OFF" }));
        onComboBox.setEnabled(false);
        onComboBox.setName("onComboBox"); // NOI18N
        onComboBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                onComboBoxItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(switchCheckBox)
                        .addGap(18, 18, 18)
                        .addComponent(switchComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(varCheckBox)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(varOperationTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(varTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(varComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(onComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel3)
                    .addComponent(jLabel1))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {switchComboBox, varComboBox});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(switchCheckBox)
                    .addComponent(switchComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(onComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(varCheckBox)
                    .addComponent(varComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(varTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(varOperationTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);
        jToolBar1.setName("jToolBar1"); // NOI18N

        addScriptButton.setText("新建命令");
        addScriptButton.setFocusable(false);
        addScriptButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        addScriptButton.setName("addScriptButton"); // NOI18N
        addScriptButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        addScriptButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addScriptButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(addScriptButton);

        removeScriptButton.setText("删除命令");
        removeScriptButton.setFocusable(false);
        removeScriptButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        removeScriptButton.setName("removeScriptButton"); // NOI18N
        removeScriptButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        removeScriptButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeScriptButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(removeScriptButton);

        jPanel3.setName("jPanel3"); // NOI18N

        npcMoveImgPane.setBackground(new java.awt.Color(255, 204, 255));
        npcMoveImgPane.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        npcMoveImgPane.setName("npcMoveImgPane"); // NOI18N
        npcMoveImgPane.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                npcMoveImgPaneMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout npcMoveImgPaneLayout = new javax.swing.GroupLayout(npcMoveImgPane);
        npcMoveImgPane.setLayout(npcMoveImgPaneLayout);
        npcMoveImgPaneLayout.setHorizontalGroup(
            npcMoveImgPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 146, Short.MAX_VALUE)
        );
        npcMoveImgPaneLayout.setVerticalGroup(
            npcMoveImgPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 106, Short.MAX_VALUE)
        );

        jLabel4.setText("Npc行走图：");
        jLabel4.setName("jLabel4"); // NOI18N

        npcHeadImgPane.setBackground(new java.awt.Color(255, 204, 255));
        npcHeadImgPane.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        npcHeadImgPane.setName("npcHeadImgPane"); // NOI18N
        npcHeadImgPane.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                npcHeadImgPaneMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout npcHeadImgPaneLayout = new javax.swing.GroupLayout(npcHeadImgPane);
        npcHeadImgPane.setLayout(npcHeadImgPaneLayout);
        npcHeadImgPaneLayout.setHorizontalGroup(
            npcHeadImgPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 146, Short.MAX_VALUE)
        );
        npcHeadImgPaneLayout.setVerticalGroup(
            npcHeadImgPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 106, Short.MAX_VALUE)
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("移动规则"));
        jPanel2.setName("jPanel2"); // NOI18N

        jLabel5.setText("类型:");
        jLabel5.setName("jLabel5"); // NOI18N

        jLabel6.setText("速度:");
        jLabel6.setName("jLabel6"); // NOI18N

        typeComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "固定", "随机", "接近" }));
        typeComboBox.setName("typeComboBox"); // NOI18N
        typeComboBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                typeComboBoxItemStateChanged(evt);
            }
        });

        speedComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "慢", "中", "快" }));
        speedComboBox.setName("speedComboBox"); // NOI18N
        speedComboBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                speedComboBoxItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addComponent(typeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addComponent(speedComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {speedComboBox, typeComboBox});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(typeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(speedComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("选项"));
        jPanel6.setName("jPanel6"); // NOI18N

        crossCheckBox.setText("允许穿透");
        crossCheckBox.setName("crossCheckBox"); // NOI18N
        crossCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                crossCheckBoxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(crossCheckBox)
                .addContainerGap(249, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(crossCheckBox)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel7.setText("Npc头像图");
        jLabel7.setName("jLabel7"); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(npcMoveImgPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(npcHeadImgPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {npcHeadImgPane, npcMoveImgPane});

        jPanel3Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jPanel2, jPanel6});

        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(npcMoveImgPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(npcHeadImgPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {npcHeadImgPane, npcMoveImgPane});

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        commandTree.setName("commandTree"); // NOI18N
        commandTree.setSelectNode(null);
        jScrollPane1.setViewportView(commandTree);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, 0, 341, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 441, Short.MAX_VALUE)
                    .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 441, Short.MAX_VALUE))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jPanel1, jPanel3});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 403, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void saveSwitchCondition() {
        if (switchCheckBox.isSelected()) {
            NpcStateCondition nsc = new NpcStateCondition();
            nsc.conditionType = NpcStateCondition.SWITCH;
            nsc.paramList = new int[]{switchComboBox.getSelectedIndex(), onComboBox.getSelectedIndex()};
            npcState.setSwitchCondition(nsc);
        } else {
            npcState.setSwitchCondition(null);
        }
    }

    private void saveVarCondition() {
        if (varCheckBox.isSelected()) {
            NpcStateCondition nsc = new NpcStateCondition();
            nsc.conditionType = NpcStateCondition.VAR;
            nsc.paramList = new int[]{varComboBox.getSelectedIndex(), varOperationTypeComboBox.getSelectedIndex(), Integer.parseInt(varTextField.getText())};
            npcState.setVarCondition(nsc);
        } else {
            npcState.setVarCondition(null);
        }
    }
    private void npcMoveImgPaneMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_npcMoveImgPaneMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() >= 2) {
            NpcMoveImageDialog cid = new NpcMoveImageDialog(this, true);
            cid.setVisible(true);
        }
    }//GEN-LAST:event_npcMoveImgPaneMouseClicked

    private void speedComboBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_speedComboBoxItemStateChanged
        // TODO add your handling code here:
        npcState.setSpeed(Byte.parseByte("" + (1 + speedComboBox.getSelectedIndex())));
    }//GEN-LAST:event_speedComboBoxItemStateChanged

    private void typeComboBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_typeComboBoxItemStateChanged
        // TODO add your handling code here:
        npcState.setMove(Byte.parseByte("" + typeComboBox.getSelectedIndex()));
    }//GEN-LAST:event_typeComboBoxItemStateChanged

    private void insertScript() {
        if (sd == null) {
            sd = new ScriptDialog(this, true);
        }
        sd.setScript(npcState.getScript());
        sd.setVisible(true);
        commandTree.updateUI();
    }

    private void addTreeNode(DefaultMutableTreeNode node, Command c) {
        if (c == null || node == null) {
            return;
        }
        DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(c);
        node.add(newNode);
        commandTree.expandPath(commandTree.getSelectionPath());
        commandTree.setSelectionPath(new TreePath(((DefaultTreeModel) commandTree.getModel()).getPathToRoot(newNode)));
        //设置维持当前的选择路径
        commandTree.setExpandsSelectedPaths(true);
        commandTree.updateUI();
        for (int i = 0; i < c.commandSize(); i++) {
            addTreeNode(newNode, c.getCommand(i));
        }
    }
    private void addScriptButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addScriptButtonActionPerformed
        // TODO add your handling code here:
        insertScript();
    }//GEN-LAST:event_addScriptButtonActionPerformed

    private void removeScriptButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeScriptButtonActionPerformed
        // TODO add your handling code here:
        removeScript();
    }//GEN-LAST:event_removeScriptButtonActionPerformed
    private void removeScript() {
        DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) commandTree.getSelectNode();
        if (selectedNode != null && selectedNode.getParent() != null && selectedNode.getUserObject() instanceof Command) {
            int id = JOptionPane.showConfirmDialog(this, "你确定要删除该命令吗？", "删除命令", JOptionPane.OK_CANCEL_OPTION);
            if (id == JOptionPane.OK_OPTION) {
                DefaultMutableTreeNode parentNode = (DefaultMutableTreeNode) selectedNode.getParent();
                ((DefaultTreeModel) commandTree.getModel()).removeNodeFromParent(selectedNode);
                removeAllChildrenMap(selectedNode);
                Command selectedCommand = (Command) selectedNode.getUserObject();
                if (parentNode.getUserObject() != null && parentNode.getUserObject() instanceof Command
                        && (((Command) parentNode.getUserObject()).getScriptId() == Command.IF
                        || ((Command) parentNode.getUserObject()).getScriptId() == Command.WHILE)) {
                    ((Command) parentNode.getUserObject()).removeCommand(selectedCommand);
                } else {
                    npcState.getScript().removeCommand(selectedCommand);
                }
                commandTree.getSelectionModel().setSelectionPath(new TreePath(((DefaultTreeModel) commandTree.getModel()).getRoot()));
                commandTree.updateUI();
            }
        }
    }

    private void removeAllChildrenMap(DefaultMutableTreeNode tn) {
        for (int i = 0, n = tn.getChildCount(); i < n; i++) {
            DefaultMutableTreeNode dmt = (DefaultMutableTreeNode) tn.getChildAt(i);
            if (dmt.getChildCount() > 0) {
                removeAllChildrenMap(dmt);
            }
            npcState.getScript().removeCommand((Command) dmt.getUserObject());
        }
    }

    private void npcHeadImgPaneMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_npcHeadImgPaneMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() >= 2) {
            NpcHeadImageDialog cid = new NpcHeadImageDialog(this, true);
            cid.setVisible(true);
        }
    }//GEN-LAST:event_npcHeadImgPaneMouseClicked

    private void crossCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_crossCheckBoxActionPerformed
        // TODO add your handling code here:
        npcState.setCross(crossCheckBox.isSelected());
    }//GEN-LAST:event_crossCheckBoxActionPerformed

    private void switchComboBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_switchComboBoxItemStateChanged
        // TODO add your handling code here:
        if (isInitOver) {
            saveSwitchCondition();
        }
    }//GEN-LAST:event_switchComboBoxItemStateChanged

    private void onComboBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_onComboBoxItemStateChanged
        // TODO add your handling code here:
        if (isInitOver) {
            saveSwitchCondition();
        }
    }//GEN-LAST:event_onComboBoxItemStateChanged

    private void varComboBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_varComboBoxItemStateChanged
        // TODO add your handling code here:
        if (isInitOver) {
            saveVarCondition();
        }
    }//GEN-LAST:event_varComboBoxItemStateChanged

    private void varOperationTypeComboBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_varOperationTypeComboBoxItemStateChanged
        // TODO add your handling code here:
        if (isInitOver) {
            saveVarCondition();
        }
    }//GEN-LAST:event_varOperationTypeComboBoxItemStateChanged

    private void switchCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_switchCheckBoxActionPerformed
        // TODO add your handling code here:
        saveSwitchCondition();
        if (switchCheckBox.isSelected()) {
            switchComboBox.setEnabled(true);
            onComboBox.setEnabled(true);
        } else {
            switchComboBox.setEnabled(false);
            onComboBox.setEnabled(false);
        }
    }//GEN-LAST:event_switchCheckBoxActionPerformed

    private void varCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_varCheckBoxActionPerformed
        // TODO add your handling code here:
        saveVarCondition();
        if (varCheckBox.isSelected()) {
            varComboBox.setEnabled(true);
            varOperationTypeComboBox.setEnabled(true);
            varTextField.setEnabled(true);
        } else {
            varComboBox.setEnabled(false);
            varOperationTypeComboBox.setEnabled(false);
            varTextField.setEnabled(false);
        }
    }//GEN-LAST:event_varCheckBoxActionPerformed

    private void varTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_varTextFieldKeyReleased
        // TODO add your handling code here:
        String str = varTextField.getText();
        Matcher m = Pattern.compile("\\d*").matcher(str);
        if (!m.matches()) {
            getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(null, "请输入一个数字！");
            str = str.substring(0, str.length() - 1);
            varTextField.setText(str);
        }
        saveVarCondition();
    }//GEN-LAST:event_varTextFieldKeyReleased
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addScriptButton;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    public com.soyomaker.widget.SoyoCommandTree commandTree;
    private javax.swing.JCheckBox crossCheckBox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar jToolBar1;
    public javax.swing.JPanel npcHeadImgPane;
    public javax.swing.JPanel npcMoveImgPane;
    private javax.swing.JComboBox onComboBox;
    private javax.swing.JButton removeScriptButton;
    private javax.swing.JComboBox speedComboBox;
    private javax.swing.JCheckBox switchCheckBox;
    private javax.swing.JComboBox switchComboBox;
    private javax.swing.JComboBox typeComboBox;
    private javax.swing.JCheckBox varCheckBox;
    private javax.swing.JComboBox varComboBox;
    private javax.swing.JComboBox varOperationTypeComboBox;
    private javax.swing.JTextField varTextField;
    // End of variables declaration//GEN-END:variables
}
