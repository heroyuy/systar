/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * SystemConfigPanel.java
 *
 * Created on 2011-9-2, 22:08:32
 */
package com.soyomaker.data.ui;

import com.soyomaker.AppData;
import com.soyomaker.model.map.Map;
import com.soyomaker.data.DataManager;
import com.soyomaker.data.model.Attribute;
import com.soyomaker.data.model.Config;
import com.soyomaker.data.model.ConfigAttributeTableModel;
import com.soyomaker.data.model.ConfigPlayerTableModel;
import com.soyomaker.data.model.Model;
import com.soyomaker.data.model.Player;
import com.soyomaker.model.animation.Animation;
import javax.swing.JComboBox;

/**
 *
 * @author Administrator
 */
public class ConfigPanel extends javax.swing.JPanel {

    /** Creates new form SystemConfigPanel */
    public ConfigPanel() {
        initComponents();
    }

    private void init() {
        if (AppData.getInstance().getCurProject().getDataManager().getModels(Model.CONFIG).length <= 0) {
            return;
        }
        Config config = (Config) AppData.getInstance().getCurProject().getDataManager().getModels(Model.CONFIG)[0];

        configHpTextField.setText(config.term.hp);
        configSpTextField.setText(config.term.sp);
        configStreTextField.setText(config.term.stre);
        configInteTextField.setText(config.term.inte);
        configAgilTextField.setText(config.term.agil);
        configDextTextField.setText(config.term.dext);
        configBodyTextField.setText(config.term.body);
        configLuckTextField.setText(config.term.luck);
        configHelmTextField.setText(config.term.helm);
        configArmourTextField.setText(config.term.armour);
        configWeaponTextField.setText(config.term.weapon);
        configShieldTextField.setText(config.term.shield);
        configBootsTextField.setText(config.term.boots);
        configJewelryTextField.setText(config.term.jewelry);
        configAtkTextField.setText(config.term.atk);
        configDefTextField.setText(config.term.def);
        configMagicAtkTextField.setText(config.term.magicAtk);
        configMagicDefTextField.setText(config.term.magicDef);
        configItemTextField.setText(config.term.item);
        configEquipTextField.setText(config.term.equip);
        configSkillTextField.setText(config.term.skill);
        configMoneyTextField.setText(config.term.money);
        configNameTextField.setText(config.term.name);
        configAboutTextArea.setText(config.term.about);
        configHelpTextArea.setText(config.term.help);
        conf.system.attributes.clear();
        for (int i = 0; i < config.system.attributes.size(); i++) {
            conf.system.attributes.add(config.system.attributes.get(i));
        }
        configAttributeTable.updateUI();
        conf.system.initPlayers.clear();
        for (int i = 0; i < config.system.initPlayers.size(); i++) {
            conf.system.initPlayers.add(config.system.initPlayers.get(i));
        }
        configPlayerTable.updateUI();
        titleMusicComboBox.setSelectedItem(config.system.titleMusic);
        battleStartSoundComboBox.setSelectedItem(config.system.startBattleSound);
        battleBackgroundMusicComboBox.setSelectedItem(config.system.battleMusic);
        battleWinSoundComboBox.setSelectedItem(config.system.winBattleSound);
        escapeSoundComboBox.setSelectedItem(config.system.escapeSound);
        loadSoundComboBox.setSelectedItem(config.system.readSound);
        saveSoundComboBox.setSelectedItem(config.system.saveSound);
        okSoundComboBox.setSelectedItem(config.system.confirmSound);
        cancleSoundComboBox.setSelectedItem(config.system.cancelSound);
        warnSoundComboBox.setSelectedItem(config.system.warnSound);
        equipSoundComboBox.setSelectedItem(config.system.equipSound);
        selectSoundComboBox.setSelectedItem(config.system.selectedSound);
        shopSoundComboBox.setSelectedItem(config.system.shopSound);
        initMapComboBox.setSelectedItem((Map) AppData.getInstance().getCurProject().getMap(config.system.curMapIndex));
        initRowTextField.setText(config.system.row + "");
        initColTextField.setText(config.system.col + "");
        initFaceComboBox.setSelectedIndex(config.system.face);
        gameStartAniComboBox.setSelectedItem((Animation) AppData.getInstance().getCurProject().getAnimation(config.system.startAniIndex));
        gameOverAniComboBox.setSelectedItem((Animation) AppData.getInstance().getCurProject().getAnimation(config.system.endAniIndex));
        skinComboBox.setSelectedItem(config.system.skin);
        titleSceneBackgroundComboBox.setSelectedItem(config.system.titleBackground);
    }

    /**
     *
     */
    public void refresh() {
        gameStartAniComboBox.removeAllItems();
        gameOverAniComboBox.removeAllItems();
        skinComboBox.removeAllItems();
        titleSceneBackgroundComboBox.removeAllItems();
        titleMusicComboBox.removeAllItems();
        battleStartSoundComboBox.removeAllItems();
        battleBackgroundMusicComboBox.removeAllItems();
        battleWinSoundComboBox.removeAllItems();
        escapeSoundComboBox.removeAllItems();
        loadSoundComboBox.removeAllItems();
        saveSoundComboBox.removeAllItems();
        okSoundComboBox.removeAllItems();
        cancleSoundComboBox.removeAllItems();
        warnSoundComboBox.removeAllItems();
        equipSoundComboBox.removeAllItems();
        selectSoundComboBox.removeAllItems();
        shopSoundComboBox.removeAllItems();
        initMapComboBox.removeAllItems();
        skinComboBox.addItem("");
        for (int i = 0; i < DataManager.listSkinImageName().length; i++) {
            skinComboBox.addItem(DataManager.listSkinImageName()[i]);
        }
        titleSceneBackgroundComboBox.addItem("");
        for (int i = 0; i < DataManager.listTitleBackgroundName().length; i++) {
            titleSceneBackgroundComboBox.addItem(DataManager.listTitleBackgroundName()[i]);
        }
        titleMusicComboBox.addItem("");
        for (int i = 0; i < DataManager.listMusicName().length; i++) {
            titleMusicComboBox.addItem(DataManager.listMusicName()[i]);
        }
        battleStartSoundComboBox.addItem("");
        for (int i = 0; i < DataManager.listSoundName().length; i++) {
            battleStartSoundComboBox.addItem(DataManager.listSoundName()[i]);
        }
        battleBackgroundMusicComboBox.addItem("");
        for (int i = 0; i < DataManager.listMusicName().length; i++) {
            battleBackgroundMusicComboBox.addItem(DataManager.listMusicName()[i]);
        }
        battleWinSoundComboBox.addItem("");
        for (int i = 0; i < DataManager.listSoundName().length; i++) {
            battleWinSoundComboBox.addItem(DataManager.listSoundName()[i]);
        }
        escapeSoundComboBox.addItem("");
        for (int i = 0; i < DataManager.listSoundName().length; i++) {
            escapeSoundComboBox.addItem(DataManager.listSoundName()[i]);
        }
        loadSoundComboBox.addItem("");
        for (int i = 0; i < DataManager.listSoundName().length; i++) {
            loadSoundComboBox.addItem(DataManager.listSoundName()[i]);
        }
        saveSoundComboBox.addItem("");
        for (int i = 0; i < DataManager.listSoundName().length; i++) {
            saveSoundComboBox.addItem(DataManager.listSoundName()[i]);
        }
        okSoundComboBox.addItem("");
        for (int i = 0; i < DataManager.listSoundName().length; i++) {
            okSoundComboBox.addItem(DataManager.listSoundName()[i]);
        }
        cancleSoundComboBox.addItem("");
        for (int i = 0; i < DataManager.listSoundName().length; i++) {
            cancleSoundComboBox.addItem(DataManager.listSoundName()[i]);
        }
        warnSoundComboBox.addItem("");
        for (int i = 0; i < DataManager.listSoundName().length; i++) {
            warnSoundComboBox.addItem(DataManager.listSoundName()[i]);
        }
        equipSoundComboBox.addItem("");
        for (int i = 0; i < DataManager.listSoundName().length; i++) {
            equipSoundComboBox.addItem(DataManager.listSoundName()[i]);
        }
        selectSoundComboBox.addItem("");
        for (int i = 0; i < DataManager.listSoundName().length; i++) {
            selectSoundComboBox.addItem(DataManager.listSoundName()[i]);
        }
        shopSoundComboBox.addItem("");
        for (int i = 0; i < DataManager.listSoundName().length; i++) {
            shopSoundComboBox.addItem(DataManager.listSoundName()[i]);
        }

        gameStartAniComboBox.addItem("");
        java.util.Iterator it = AppData.getInstance().getCurProject().getAnimations().entrySet().iterator();
        while (it.hasNext()) {
            java.util.Map.Entry entry = (java.util.Map.Entry) it.next();
            gameStartAniComboBox.addItem(((Animation) entry.getValue()));
        }
        gameOverAniComboBox.addItem("");
        java.util.Iterator it2 = AppData.getInstance().getCurProject().getAnimations().entrySet().iterator();
        while (it2.hasNext()) {
            java.util.Map.Entry entry = (java.util.Map.Entry) it2.next();
            gameOverAniComboBox.addItem(((Animation) entry.getValue()));
        }
        initMapComboBox.addItem("");
        java.util.Iterator it3 = AppData.getInstance().getCurProject().getMaps().entrySet().iterator();
        while (it3.hasNext()) {
            java.util.Map.Entry entry = (java.util.Map.Entry) it3.next();
            initMapComboBox.addItem(((Map) entry.getValue()));
        }
        init();
    }
    /**
     *
     */
    public Config conf = new Config();
    private ConfigPlayerTableModel cptm = new ConfigPlayerTableModel(conf);
    private ConfigAttributeTableModel catm = new ConfigAttributeTableModel(conf);

    /**
     *
     */
    public void save() {
        if (AppData.getInstance().getCurProject().getDataManager().getModels(Model.CONFIG).length <= 0) {
            return;
        }
        Config config = (Config) AppData.getInstance().getCurProject().getDataManager().getModels(Model.CONFIG)[0];
        config.system.initPlayers.clear();
        for (int i = 0; i < configPlayerTable.getRowCount(); i++) {
            config.system.initPlayers.add((Player) AppData.getInstance().getCurProject().getDataManager().getModel(Model.PLAYER, Integer.parseInt(configPlayerTable.getModel().getValueAt(i, 0).toString())));
        }
        config.system.attributes.clear();
        for (int i = 0; i < configAttributeTable.getRowCount(); i++) {
            Attribute attr = new Attribute();
            attr.id = i;
            attr.name = configAttributeTable.getModel().getValueAt(i, 1).toString();
            attr.description = configAttributeTable.getModel().getValueAt(i, 2).toString();
            config.system.attributes.add(attr);
        }
        config.term.about = configAboutTextArea.getText();
        config.term.help = configHelpTextArea.getText();
        config.term.name = configNameTextField.getText();
        config.term.hp = configHpTextField.getText();
        config.term.sp = configSpTextField.getText();
        config.term.stre = configStreTextField.getText();
        config.term.inte = configInteTextField.getText();
        config.term.agil = configAgilTextField.getText();
        config.term.dext = configDextTextField.getText();
        config.term.body = configBodyTextField.getText();
        config.term.luck = configLuckTextField.getText();
        config.term.helm = configHelmTextField.getText();
        config.term.armour = configArmourTextField.getText();
        config.term.weapon = configWeaponTextField.getText();
        config.term.shield = configShieldTextField.getText();
        config.term.boots = configBootsTextField.getText();
        config.term.jewelry = configJewelryTextField.getText();
        config.term.atk = configAtkTextField.getText();
        config.term.def = configDefTextField.getText();
        config.term.magicAtk = configMagicAtkTextField.getText();
        config.term.magicDef = configMagicDefTextField.getText();
        config.term.item = configItemTextField.getText();
        config.term.equip = configEquipTextField.getText();
        config.term.skill = configSkillTextField.getText();
        config.term.money = configMoneyTextField.getText();
        config.system.titleMusic = getSelectedItem(titleMusicComboBox);
        config.system.startBattleSound = getSelectedItem(battleStartSoundComboBox);
        config.system.battleMusic = getSelectedItem(battleBackgroundMusicComboBox);
        config.system.winBattleSound = getSelectedItem(battleWinSoundComboBox);
        config.system.escapeSound = getSelectedItem(escapeSoundComboBox);
        config.system.readSound = getSelectedItem(loadSoundComboBox);
        config.system.saveSound = getSelectedItem(saveSoundComboBox);
        config.system.confirmSound = getSelectedItem(okSoundComboBox);
        config.system.cancelSound = getSelectedItem(cancleSoundComboBox);
        config.system.warnSound = getSelectedItem(warnSoundComboBox);
        config.system.equipSound = getSelectedItem(equipSoundComboBox);
        config.system.selectedSound = getSelectedItem(selectSoundComboBox);
        config.system.shopSound = getSelectedItem(shopSoundComboBox);
        config.system.startAniIndex = gameStartAniComboBox.getSelectedIndex();
        if (gameStartAniComboBox.getSelectedItem() == null||gameStartAniComboBox.getSelectedItem().toString().equals("")) {
            config.system.startAniIndex = -1;
        } else {
            config.system.startAniIndex = ((Animation) gameStartAniComboBox.getSelectedItem()).getIndex();
        }
        if (gameOverAniComboBox.getSelectedItem() == null||gameOverAniComboBox.getSelectedItem().toString().equals("")) {
            config.system.endAniIndex = -1;
        } else {
            config.system.endAniIndex = ((Animation) gameOverAniComboBox.getSelectedItem()).getIndex();
        }
        if (initMapComboBox.getSelectedItem() == null||initMapComboBox.getSelectedItem().toString().equals("")) {
            config.system.curMapIndex = -1;
        } else {
            config.system.curMapIndex = ((Map) initMapComboBox.getSelectedItem()).getIndex();
        }
        config.system.skin = getSelectedItem(skinComboBox);
        config.system.titleBackground = getSelectedItem(titleSceneBackgroundComboBox);
        config.system.row = Integer.parseInt(initRowTextField.getText());
        config.system.col = Integer.parseInt(initColTextField.getText());
        config.system.face = (byte) initFaceComboBox.getSelectedIndex();
    }

    private String getSelectedItem(JComboBox combo) {
        if (combo.getSelectedItem() == null) {
            return "";
        } else {
            return combo.getSelectedItem().toString();
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        configPlayerTable = new javax.swing.JTable();
        jToolBar2 = new javax.swing.JToolBar();
        addPlayerButton = new javax.swing.JButton();
        removePlayerButton = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        initMapComboBox = new javax.swing.JComboBox();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        initRowTextField = new javax.swing.JTextField();
        initColTextField = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        initFaceComboBox = new javax.swing.JComboBox();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        configAttributeTable = new javax.swing.JTable();
        jToolBar1 = new javax.swing.JToolBar();
        addAttributeButton = new javax.swing.JButton();
        removeAttributeButton = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        jLabel35 = new javax.swing.JLabel();
        titleMusicComboBox = new javax.swing.JComboBox();
        jLabel36 = new javax.swing.JLabel();
        battleStartSoundComboBox = new javax.swing.JComboBox();
        jLabel37 = new javax.swing.JLabel();
        battleBackgroundMusicComboBox = new javax.swing.JComboBox();
        jLabel38 = new javax.swing.JLabel();
        battleWinSoundComboBox = new javax.swing.JComboBox();
        jLabel39 = new javax.swing.JLabel();
        escapeSoundComboBox = new javax.swing.JComboBox();
        jLabel40 = new javax.swing.JLabel();
        selectSoundComboBox = new javax.swing.JComboBox();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        okSoundComboBox = new javax.swing.JComboBox();
        cancleSoundComboBox = new javax.swing.JComboBox();
        jLabel43 = new javax.swing.JLabel();
        warnSoundComboBox = new javax.swing.JComboBox();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        equipSoundComboBox = new javax.swing.JComboBox();
        loadSoundComboBox = new javax.swing.JComboBox();
        jLabel46 = new javax.swing.JLabel();
        shopSoundComboBox = new javax.swing.JComboBox();
        jLabel47 = new javax.swing.JLabel();
        saveSoundComboBox = new javax.swing.JComboBox();
        jPanel11 = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();
        gameStartAniComboBox = new javax.swing.JComboBox();
        jLabel32 = new javax.swing.JLabel();
        gameOverAniComboBox = new javax.swing.JComboBox();
        jLabel33 = new javax.swing.JLabel();
        skinComboBox = new javax.swing.JComboBox();
        jLabel34 = new javax.swing.JLabel();
        titleSceneBackgroundComboBox = new javax.swing.JComboBox();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        configHpTextField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        configSpTextField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        configStreTextField = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        configInteTextField = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        configAgilTextField = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        configDextTextField = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        configBodyTextField = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        configLuckTextField = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        configHelmTextField = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        configArmourTextField = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        configWeaponTextField = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        configShieldTextField = new javax.swing.JTextField();
        configBootsTextField = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        configJewelryTextField = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        configAtkTextField = new javax.swing.JTextField();
        configDefTextField = new javax.swing.JTextField();
        configMagicAtkTextField = new javax.swing.JTextField();
        configMagicDefTextField = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        configItemTextField = new javax.swing.JTextField();
        configEquipTextField = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        configSkillTextField = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        configMoneyTextField = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        configNameTextField = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        configAboutTextArea = new javax.swing.JTextArea();
        jLabel25 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        configHelpTextArea = new javax.swing.JTextArea();

        jTabbedPane1.setName("jTabbedPane1"); // NOI18N

        jPanel1.setName("jPanel1"); // NOI18N

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("初期角色"));
        jPanel7.setName("jPanel7"); // NOI18N

        jScrollPane3.setName("jScrollPane3"); // NOI18N

        configPlayerTable.setModel(cptm);
        configPlayerTable.setName("configPlayerTable"); // NOI18N
        jScrollPane3.setViewportView(configPlayerTable);

        jToolBar2.setFloatable(false);
        jToolBar2.setRollover(true);
        jToolBar2.setName("jToolBar2"); // NOI18N

        addPlayerButton.setText("添加角色");
        addPlayerButton.setFocusable(false);
        addPlayerButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        addPlayerButton.setName("addPlayerButton"); // NOI18N
        addPlayerButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        addPlayerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addPlayerButtonActionPerformed(evt);
            }
        });
        jToolBar2.add(addPlayerButton);

        removePlayerButton.setText("删除角色");
        removePlayerButton.setFocusable(false);
        removePlayerButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        removePlayerButton.setName("removePlayerButton"); // NOI18N
        removePlayerButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        removePlayerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removePlayerButtonActionPerformed(evt);
            }
        });
        jToolBar2.add(removePlayerButton);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar2, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jToolBar2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE))
        );

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder("初期位置"));
        jPanel8.setName("jPanel8"); // NOI18N

        jLabel27.setText("初始地图");
        jLabel27.setName("jLabel27"); // NOI18N

        initMapComboBox.setName("initMapComboBox"); // NOI18N

        jLabel28.setText("初始行号");
        jLabel28.setName("jLabel28"); // NOI18N

        jLabel29.setText("初始列号");
        jLabel29.setName("jLabel29"); // NOI18N

        initRowTextField.setText("0");
        initRowTextField.setName("initRowTextField"); // NOI18N

        initColTextField.setText("0");
        initColTextField.setName("initColTextField"); // NOI18N

        jLabel30.setText("初始面向");
        jLabel30.setName("jLabel30"); // NOI18N

        initFaceComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "上", "下", "左", "右" }));
        initFaceComboBox.setName("initFaceComboBox"); // NOI18N

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel27)
                        .addGap(18, 18, 18)
                        .addComponent(initMapComboBox, 0, 87, Short.MAX_VALUE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel28)
                        .addGap(18, 18, 18)
                        .addComponent(initRowTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel29)
                        .addGap(18, 18, 18)
                        .addComponent(initColTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel30)
                        .addGap(18, 18, 18)
                        .addComponent(initFaceComboBox, 0, 87, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(initMapComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(initRowTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(initColTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30)
                    .addComponent(initFaceComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder("属性"));
        jPanel9.setName("jPanel9"); // NOI18N

        jScrollPane4.setName("jScrollPane4"); // NOI18N

        configAttributeTable.setModel(catm);
        configAttributeTable.setName("configAttributeTable"); // NOI18N
        jScrollPane4.setViewportView(configAttributeTable);

        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);
        jToolBar1.setName("jToolBar1"); // NOI18N

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
        jToolBar1.add(addAttributeButton);

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
        jToolBar1.add(removeAttributeButton);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE))
        );

        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder("音效"));
        jPanel10.setName("jPanel10"); // NOI18N

        jLabel35.setText("标题界面音乐");
        jLabel35.setName("jLabel35"); // NOI18N

        titleMusicComboBox.setName("titleMusicComboBox"); // NOI18N

        jLabel36.setText("战斗开始音效");
        jLabel36.setName("jLabel36"); // NOI18N

        battleStartSoundComboBox.setName("battleStartSoundComboBox"); // NOI18N

        jLabel37.setText("战斗背景音乐");
        jLabel37.setName("jLabel37"); // NOI18N

        battleBackgroundMusicComboBox.setName("battleBackgroundMusicComboBox"); // NOI18N

        jLabel38.setText("战斗胜利音效");
        jLabel38.setName("jLabel38"); // NOI18N

        battleWinSoundComboBox.setName("battleWinSoundComboBox"); // NOI18N

        jLabel39.setText("逃跑音效");
        jLabel39.setName("jLabel39"); // NOI18N

        escapeSoundComboBox.setName("escapeSoundComboBox"); // NOI18N

        jLabel40.setText("选中音效");
        jLabel40.setName("jLabel40"); // NOI18N

        selectSoundComboBox.setName("selectSoundComboBox"); // NOI18N

        jLabel41.setText("确定音效");
        jLabel41.setName("jLabel41"); // NOI18N

        jLabel42.setText("取消音效");
        jLabel42.setName("jLabel42"); // NOI18N

        okSoundComboBox.setName("okSoundComboBox"); // NOI18N

        cancleSoundComboBox.setName("cancleSoundComboBox"); // NOI18N

        jLabel43.setText("警告音效");
        jLabel43.setName("jLabel43"); // NOI18N

        warnSoundComboBox.setName("warnSoundComboBox"); // NOI18N

        jLabel44.setText("装备音效");
        jLabel44.setName("jLabel44"); // NOI18N

        jLabel45.setText("商店音效");
        jLabel45.setName("jLabel45"); // NOI18N

        equipSoundComboBox.setName("equipSoundComboBox"); // NOI18N

        loadSoundComboBox.setName("loadSoundComboBox"); // NOI18N

        jLabel46.setText("读档音效");
        jLabel46.setName("jLabel46"); // NOI18N

        shopSoundComboBox.setName("shopSoundComboBox"); // NOI18N

        jLabel47.setText("存档音效");
        jLabel47.setName("jLabel47"); // NOI18N

        saveSoundComboBox.setName("saveSoundComboBox"); // NOI18N

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel37)
                    .addComponent(jLabel38)
                    .addComponent(jLabel39)
                    .addComponent(jLabel46)
                    .addComponent(jLabel47)
                    .addComponent(jLabel36)
                    .addComponent(jLabel35))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(titleMusicComboBox, 0, 108, Short.MAX_VALUE)
                    .addComponent(battleStartSoundComboBox, 0, 108, Short.MAX_VALUE)
                    .addComponent(saveSoundComboBox, 0, 108, Short.MAX_VALUE)
                    .addComponent(loadSoundComboBox, 0, 108, Short.MAX_VALUE)
                    .addComponent(escapeSoundComboBox, 0, 108, Short.MAX_VALUE)
                    .addComponent(battleWinSoundComboBox, 0, 108, Short.MAX_VALUE)
                    .addComponent(battleBackgroundMusicComboBox, 0, 108, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel45)
                        .addGap(18, 18, 18)
                        .addComponent(shopSoundComboBox, 0, 108, Short.MAX_VALUE))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel44)
                        .addGap(18, 18, 18)
                        .addComponent(equipSoundComboBox, 0, 108, Short.MAX_VALUE))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel43)
                        .addGap(18, 18, 18)
                        .addComponent(warnSoundComboBox, 0, 108, Short.MAX_VALUE))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel41)
                        .addGap(18, 18, 18)
                        .addComponent(okSoundComboBox, 0, 108, Short.MAX_VALUE))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel42)
                        .addGap(18, 18, 18)
                        .addComponent(cancleSoundComboBox, 0, 108, Short.MAX_VALUE))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel40)
                        .addGap(18, 18, 18)
                        .addComponent(selectSoundComboBox, 0, 108, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel35)
                    .addComponent(titleMusicComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel41)
                    .addComponent(okSoundComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(battleStartSoundComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel42)
                    .addComponent(cancleSoundComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel37)
                    .addComponent(battleBackgroundMusicComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel43)
                    .addComponent(warnSoundComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel38)
                    .addComponent(battleWinSoundComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel44)
                    .addComponent(equipSoundComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel39)
                    .addComponent(escapeSoundComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel40)
                    .addComponent(selectSoundComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel45)
                        .addComponent(jLabel46)
                        .addComponent(shopSoundComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(loadSoundComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel47)
                    .addComponent(saveSoundComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(171, Short.MAX_VALUE))
        );

        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder("图像"));
        jPanel11.setName("jPanel11"); // NOI18N

        jLabel31.setText("开场动画");
        jLabel31.setName("jLabel31"); // NOI18N

        gameStartAniComboBox.setName("gameStartAniComboBox"); // NOI18N

        jLabel32.setText("游戏结束动画");
        jLabel32.setName("jLabel32"); // NOI18N

        gameOverAniComboBox.setName("gameOverAniComboBox"); // NOI18N

        jLabel33.setText("皮肤");
        jLabel33.setName("jLabel33"); // NOI18N

        skinComboBox.setName("skinComboBox"); // NOI18N

        jLabel34.setText("标题界面背景");
        jLabel34.setName("jLabel34"); // NOI18N

        titleSceneBackgroundComboBox.setName("titleSceneBackgroundComboBox"); // NOI18N

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(gameStartAniComboBox, 0, 153, Short.MAX_VALUE)
                    .addComponent(jLabel31)
                    .addComponent(jLabel32)
                    .addComponent(gameOverAniComboBox, 0, 153, Short.MAX_VALUE)
                    .addComponent(jLabel33)
                    .addComponent(skinComboBox, 0, 153, Short.MAX_VALUE)
                    .addComponent(jLabel34)
                    .addComponent(titleSceneBackgroundComboBox, 0, 153, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel31)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(gameStartAniComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel32)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(gameOverAniComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel33)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(skinComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel34)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(titleSceneBackgroundComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jTabbedPane1.addTab("系统", jPanel1);

        jPanel2.setName("jPanel2"); // NOI18N

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("设置"));
        jPanel3.setName("jPanel3"); // NOI18N

        jLabel2.setText("HP");
        jLabel2.setName("jLabel2"); // NOI18N

        configHpTextField.setText("HP");
        configHpTextField.setName("configHpTextField"); // NOI18N

        jLabel3.setText("SP");
        jLabel3.setName("jLabel3"); // NOI18N

        configSpTextField.setText("SP");
        configSpTextField.setName("configSpTextField"); // NOI18N

        jLabel4.setText("力量");
        jLabel4.setName("jLabel4"); // NOI18N

        configStreTextField.setText("力量");
        configStreTextField.setName("configStreTextField"); // NOI18N

        jLabel5.setText("智力");
        jLabel5.setName("jLabel5"); // NOI18N

        configInteTextField.setText("智力");
        configInteTextField.setName("configInteTextField"); // NOI18N

        jLabel6.setText("敏捷");
        jLabel6.setName("jLabel6"); // NOI18N

        configAgilTextField.setText("敏捷");
        configAgilTextField.setName("configAgilTextField"); // NOI18N

        jLabel7.setText("灵巧");
        jLabel7.setName("jLabel7"); // NOI18N

        configDextTextField.setText("灵巧");
        configDextTextField.setName("configDextTextField"); // NOI18N

        jLabel8.setText("体力");
        jLabel8.setName("jLabel8"); // NOI18N

        configBodyTextField.setText("体力");
        configBodyTextField.setName("configBodyTextField"); // NOI18N

        jLabel9.setText("幸运");
        jLabel9.setName("jLabel9"); // NOI18N

        configLuckTextField.setText("幸运");
        configLuckTextField.setName("configLuckTextField"); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(22, 22, 22)
                        .addComponent(configHpTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                        .addComponent(configSpTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                        .addComponent(configStreTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(configInteTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                        .addComponent(configDextTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                        .addComponent(configAgilTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(configBodyTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(configLuckTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {configAgilTextField, configBodyTextField, configDextTextField, configHpTextField, configInteTextField, configLuckTextField, configSpTextField, configStreTextField});

        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(configHpTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(configSpTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(configStreTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(configInteTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(configAgilTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(configDextTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(configBodyTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(configLuckTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(132, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("装备"));
        jPanel4.setName("jPanel4"); // NOI18N

        jLabel10.setText("头盔");
        jLabel10.setName("jLabel10"); // NOI18N

        configHelmTextField.setText("头盔");
        configHelmTextField.setName("configHelmTextField"); // NOI18N

        jLabel11.setText("铠甲");
        jLabel11.setName("jLabel11"); // NOI18N

        configArmourTextField.setText("铠甲");
        configArmourTextField.setName("configArmourTextField"); // NOI18N

        jLabel12.setText("武器");
        jLabel12.setName("jLabel12"); // NOI18N

        configWeaponTextField.setText("武器");
        configWeaponTextField.setName("configWeaponTextField"); // NOI18N

        jLabel13.setText("盾牌");
        jLabel13.setName("jLabel13"); // NOI18N

        jLabel14.setText("战靴");
        jLabel14.setName("jLabel14"); // NOI18N

        configShieldTextField.setText("盾牌");
        configShieldTextField.setName("configShieldTextField"); // NOI18N

        configBootsTextField.setText("战靴");
        configBootsTextField.setName("configBootsTextField"); // NOI18N

        jLabel15.setText("饰品");
        jLabel15.setName("jLabel15"); // NOI18N

        configJewelryTextField.setText("饰品");
        configJewelryTextField.setName("configJewelryTextField"); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(configHelmTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(configArmourTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(configWeaponTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(configBootsTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                        .addComponent(configShieldTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(configJewelryTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jPanel4Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {configArmourTextField, configBootsTextField, configJewelryTextField, configShieldTextField, configWeaponTextField});

        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(configHelmTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(configArmourTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(configWeaponTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(configBootsTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(configShieldTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel13)))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(configJewelryTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(208, Short.MAX_VALUE))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("指令"));
        jPanel5.setName("jPanel5"); // NOI18N

        jLabel16.setText("物理攻击");
        jLabel16.setName("jLabel16"); // NOI18N

        jLabel17.setText("物理防御");
        jLabel17.setName("jLabel17"); // NOI18N

        jLabel18.setText("魔法攻击");
        jLabel18.setName("jLabel18"); // NOI18N

        jLabel19.setText("魔法防御");
        jLabel19.setName("jLabel19"); // NOI18N

        configAtkTextField.setText("物理攻击");
        configAtkTextField.setName("configAtkTextField"); // NOI18N

        configDefTextField.setText("物理防御");
        configDefTextField.setName("configDefTextField"); // NOI18N

        configMagicAtkTextField.setText("魔法攻击");
        configMagicAtkTextField.setName("configMagicAtkTextField"); // NOI18N

        configMagicDefTextField.setText("魔法防御");
        configMagicDefTextField.setName("configMagicDefTextField"); // NOI18N

        jLabel20.setText("物品");
        jLabel20.setName("jLabel20"); // NOI18N

        jLabel21.setText("装备");
        jLabel21.setName("jLabel21"); // NOI18N

        configItemTextField.setText("物品");
        configItemTextField.setName("configItemTextField"); // NOI18N

        configEquipTextField.setText("装备");
        configEquipTextField.setName("configEquipTextField"); // NOI18N

        jLabel22.setText("技能");
        jLabel22.setName("jLabel22"); // NOI18N

        configSkillTextField.setText("技能");
        configSkillTextField.setName("configSkillTextField"); // NOI18N

        jLabel26.setText("货币单位");
        jLabel26.setName("jLabel26"); // NOI18N

        configMoneyTextField.setText("金币");
        configMoneyTextField.setName("configMoneyTextField"); // NOI18N

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16)
                    .addComponent(jLabel17)
                    .addComponent(jLabel18)
                    .addComponent(jLabel19)
                    .addComponent(jLabel20)
                    .addComponent(jLabel21)
                    .addComponent(jLabel22)
                    .addComponent(jLabel26))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(configMoneyTextField)
                    .addComponent(configSkillTextField)
                    .addComponent(configEquipTextField, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(configItemTextField, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(configAtkTextField, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(configDefTextField, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(configMagicAtkTextField, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(configMagicDefTextField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel5Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {configDefTextField, configEquipTextField, configItemTextField, configMagicAtkTextField, configMagicDefTextField, configMoneyTextField, configSkillTextField});

        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(configAtkTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(configDefTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(configMagicAtkTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(configMagicDefTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(configItemTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(configEquipTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(configSkillTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(configMoneyTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(128, Short.MAX_VALUE))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("游戏"));
        jPanel6.setName("jPanel6"); // NOI18N

        jLabel23.setText("游戏名称");
        jLabel23.setName("jLabel23"); // NOI18N

        configNameTextField.setText("游戏名称");
        configNameTextField.setName("configNameTextField"); // NOI18N

        jLabel24.setText("游戏说明");
        jLabel24.setName("jLabel24"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        configAboutTextArea.setColumns(20);
        configAboutTextArea.setLineWrap(true);
        configAboutTextArea.setRows(5);
        configAboutTextArea.setText("游戏说明\n");
        configAboutTextArea.setName("configAboutTextArea"); // NOI18N
        jScrollPane1.setViewportView(configAboutTextArea);

        jLabel25.setText("游戏帮助");
        jLabel25.setName("jLabel25"); // NOI18N

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        configHelpTextArea.setColumns(20);
        configHelpTextArea.setLineWrap(true);
        configHelpTextArea.setRows(5);
        configHelpTextArea.setText("游戏帮助");
        configHelpTextArea.setName("configHelpTextArea"); // NOI18N
        jScrollPane2.setViewportView(configHelpTextArea);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel23)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(configNameTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE))
                    .addComponent(jLabel24, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel25, javax.swing.GroupLayout.Alignment.LEADING))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(configNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel24)
                .addGap(10, 10, 10)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel25)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(56, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("用语", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 825, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 511, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void removeAttributeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeAttributeButtonActionPerformed
        // TODO add your handling code here:
        if (configAttributeTable.getSelectedRow() >= 0 && configAttributeTable.getSelectedRow() < conf.system.attributes.size()) {
            conf.system.attributes.remove(configAttributeTable.getSelectedRow());
        }
        configAttributeTable.updateUI();
    }//GEN-LAST:event_removeAttributeButtonActionPerformed

    private void addAttributeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addAttributeButtonActionPerformed
        // TODO add your handling code here:
        ConfigAttributeDialog cad = new ConfigAttributeDialog(this, true);
        cad.setVisible(true);
        if (cad.getSelectAttribute() != null) {
            Attribute attr = cad.getSelectAttribute();
            attr.id = conf.system.attributes.size();
            conf.system.attributes.add(cad.getSelectAttribute());
            configAttributeTable.updateUI();
        }
    }//GEN-LAST:event_addAttributeButtonActionPerformed

    private void addPlayerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addPlayerButtonActionPerformed
        // TODO add your handling code here:
        ConfigInitPlayerDialog ccpd = new ConfigInitPlayerDialog(this, true);
        ccpd.setVisible(true);
        if (ccpd.getSelectPlayer() != null) {
            conf.system.initPlayers.add(ccpd.getSelectPlayer());
            configPlayerTable.updateUI();
        }
    }//GEN-LAST:event_addPlayerButtonActionPerformed

    private void removePlayerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removePlayerButtonActionPerformed
        // TODO add your handling code here:
        if (configPlayerTable.getSelectedRow() >= 0 && configPlayerTable.getSelectedRow() < conf.system.initPlayers.size()) {
            conf.system.initPlayers.remove(configPlayerTable.getSelectedRow());
        }
        configPlayerTable.updateUI();
    }//GEN-LAST:event_removePlayerButtonActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addAttributeButton;
    private javax.swing.JButton addPlayerButton;
    private javax.swing.JComboBox battleBackgroundMusicComboBox;
    private javax.swing.JComboBox battleStartSoundComboBox;
    private javax.swing.JComboBox battleWinSoundComboBox;
    private javax.swing.JComboBox cancleSoundComboBox;
    private javax.swing.JTextArea configAboutTextArea;
    private javax.swing.JTextField configAgilTextField;
    private javax.swing.JTextField configArmourTextField;
    private javax.swing.JTextField configAtkTextField;
    public javax.swing.JTable configAttributeTable;
    private javax.swing.JTextField configBodyTextField;
    private javax.swing.JTextField configBootsTextField;
    private javax.swing.JTextField configDefTextField;
    private javax.swing.JTextField configDextTextField;
    private javax.swing.JTextField configEquipTextField;
    private javax.swing.JTextField configHelmTextField;
    private javax.swing.JTextArea configHelpTextArea;
    private javax.swing.JTextField configHpTextField;
    private javax.swing.JTextField configInteTextField;
    private javax.swing.JTextField configItemTextField;
    private javax.swing.JTextField configJewelryTextField;
    private javax.swing.JTextField configLuckTextField;
    private javax.swing.JTextField configMagicAtkTextField;
    private javax.swing.JTextField configMagicDefTextField;
    private javax.swing.JTextField configMoneyTextField;
    private javax.swing.JTextField configNameTextField;
    public javax.swing.JTable configPlayerTable;
    private javax.swing.JTextField configShieldTextField;
    private javax.swing.JTextField configSkillTextField;
    private javax.swing.JTextField configSpTextField;
    private javax.swing.JTextField configStreTextField;
    private javax.swing.JTextField configWeaponTextField;
    private javax.swing.JComboBox equipSoundComboBox;
    private javax.swing.JComboBox escapeSoundComboBox;
    private javax.swing.JComboBox gameOverAniComboBox;
    private javax.swing.JComboBox gameStartAniComboBox;
    private javax.swing.JTextField initColTextField;
    private javax.swing.JComboBox initFaceComboBox;
    private javax.swing.JComboBox initMapComboBox;
    private javax.swing.JTextField initRowTextField;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JToolBar jToolBar2;
    private javax.swing.JComboBox loadSoundComboBox;
    private javax.swing.JComboBox okSoundComboBox;
    private javax.swing.JButton removeAttributeButton;
    private javax.swing.JButton removePlayerButton;
    private javax.swing.JComboBox saveSoundComboBox;
    private javax.swing.JComboBox selectSoundComboBox;
    private javax.swing.JComboBox shopSoundComboBox;
    private javax.swing.JComboBox skinComboBox;
    private javax.swing.JComboBox titleMusicComboBox;
    private javax.swing.JComboBox titleSceneBackgroundComboBox;
    private javax.swing.JComboBox warnSoundComboBox;
    // End of variables declaration//GEN-END:variables
}
