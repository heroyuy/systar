/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * DataDialog.java
 *
 * Created on 2011-4-26, 23:16:31
 */
package com.soyostar.dialog;

//import com.soyostar.data.Data;
import com.soyostar.data.DataManager;
import com.soyostar.data.dao.ConfigDao;
import com.soyostar.data.dao.Dao;
import com.soyostar.data.dao.EnemyDao;
import com.soyostar.data.dao.EnemyTroopDao;
import com.soyostar.data.dao.EquipDao;
import com.soyostar.data.dao.ItemDao;
import com.soyostar.data.dao.ModelStore;
import com.soyostar.data.dao.PlayerDao;
import com.soyostar.data.dao.SkillDao;
import com.soyostar.listener.DataChangeListener;
import com.soyostar.listener.DataChangedEvent;
import com.soyostar.data.model.Config;
import com.soyostar.data.model.Enemy;
import com.soyostar.data.model.EnemySkillTableModel;
import com.soyostar.data.model.EnemyTableModel;
import com.soyostar.data.model.EnemyTroop;
import com.soyostar.data.model.EnemyTroopItemTableModel;
import com.soyostar.data.model.EnemyTroopTableModel;
import com.soyostar.data.model.Equip;
import com.soyostar.data.model.EquipTableModel;
import com.soyostar.data.model.Item;
import com.soyostar.data.model.ItemTableModel;
import com.soyostar.data.model.Model;
import com.soyostar.data.model.Player;
import com.soyostar.data.model.Skill;
import com.soyostar.data.model.SkillTableModel;
import com.soyostar.project.Project;
import com.soyostar.proxy.SoftProxy;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author Administrator
 */
public class DataEditorDialog extends javax.swing.JDialog implements DataChangeListener {

    /** Creates new form DataDialog
     * @param parent 
     * @param modal 
     */
    public DataEditorDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        etitm = new EnemyTroopItemTableModel(this);
        estm = new EnemySkillTableModel(this);
        stm = new SkillTableModel();
        itm = new ItemTableModel();
        etm = new EnemyTableModel();
        ettm = new EnemyTroopTableModel();
        eqtm = new EquipTableModel();
        initComponents();
        initialize();
    }

    private void initialize() {
//        this.setModal(true);
        setLocationRelativeTo(null);
        SoftProxy.getInstance().getCurProject().getDataManager().addDataChangeListener(this);
//        System.out.println("curPath:" + SoftProxy.getInstance().getCurProject().getPath());
        SoftProxy.getInstance().getCurProject().getDataManager().init(SoftProxy.getInstance().getCurProject().getPath());
        initConfig();
        initPlayer();
    }

    private void initPlayer() {
        if (SoftProxy.getInstance().getCurProject().getDataManager().getModels(Model.PLAYER).length == 0) {
            return;
        }
        Player player = (Player) SoftProxy.getInstance().getCurProject().getDataManager().getModels(Model.PLAYER)[0];
//        if (player == null) {
//            return;
//        }
        playerNameTextField.setText(player.name);
        playerDesTextField.setText(player.intro);
        playerLevelTextField.setText(player.lev + "");
        playerStreTextField.setText(player.stre + "");
        playerAgilTextField.setText(player.agil + "");
        playerInteTextField.setText(player.inte + "");
        playerStreAddTextField.setText(player.streByLev + "");
        playerAgilAddTextField.setText(player.agilByLev + "");
        playerInteAddTextField.setText(player.inteByLev + "");
        playerHpTextField.setText(player.hp + "");
        playerSpTextField.setText(player.sp + "");
        playerAttTextField.setText(player.atk + "");
        playerDefTextField.setText(player.def + "");
        playerExpTextArea.setText("");
        for (int i = 0; i < player.lev; i++) {
            playerExpTextArea.append(player.levList[i] + "\n");
        }
    }

    private void initConfig() {
        if (SoftProxy.getInstance().getCurProject().getDataManager().getModels(Model.CONFIG).length == 0) {
            return;
        }
        Config config = (Config) SoftProxy.getInstance().getCurProject().getDataManager().getModel(Model.CONFIG, 0);
//        if (config == null) {
//            return;
//        }
        gameAboutTextArea.setText(config.about);
        gameHelpTextArea.setText(config.help);
        agileTermTextField.setText(config.agil);
        armorTermTextField.setText(config.armour);
        attackTermTextField.setText(config.atk);
        bootsTermTextField.setText(config.boots);
        defendTermTextField.setText(config.def);
        helmetTermTextField.setText(config.helm);
        hpTermTextField.setText(config.hp);
        spTermTextField.setText(config.sp);
        inteTermTextField.setText(config.inte);
        jewelryTermTextField.setText(config.jewelry);
        moneyTermTextField.setText(config.money);
        gameNameTextField.setText(config.name);
        shieldTermTextField.setText(config.shield);
        forceTermTextField.setText(config.stre);
        weaponsTermTextField.setText(config.weapon);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel22 = new javax.swing.JPanel();
        dataTabbedPane = new javax.swing.JTabbedPane();
        systemPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        gameNameTextField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        moneyTermTextField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        spTermTextField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        hpTermTextField = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        attackTermTextField = new javax.swing.JTextField();
        defendTermTextField = new javax.swing.JTextField();
        agileTermTextField = new javax.swing.JTextField();
        inteTermTextField = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        helmetTermTextField = new javax.swing.JTextField();
        armorTermTextField = new javax.swing.JTextField();
        shieldTermTextField = new javax.swing.JTextField();
        weaponsTermTextField = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        forceTermTextField = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jewelryTermTextField = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        bootsTermTextField = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        gameHelpTextArea = new javax.swing.JTextArea();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        gameAboutTextArea = new javax.swing.JTextArea();
        playerPanel = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        playerNameTextField = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        playerLevelTextField = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        playerDesTextField = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        playerStreTextField = new javax.swing.JTextField();
        playerAgilTextField = new javax.swing.JTextField();
        playerInteTextField = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        playerStreAddTextField = new javax.swing.JTextField();
        playerAgilAddTextField = new javax.swing.JTextField();
        playerInteAddTextField = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        playerHpTextField = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        playerDefTextField = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        playerAttTextField = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        playerSpTextField = new javax.swing.JTextField();
        jPanel10 = new javax.swing.JPanel();
        playerExpSetButton = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        playerExpTextArea = new javax.swing.JTextArea();
        jPanel11 = new javax.swing.JPanel();
        playerMoveImgSetButton = new javax.swing.JButton();
        playerMoveImgPanel = new javax.swing.JPanel(){

            public void paint(Graphics g){
                g.setColor(new Color(255,153,153));
                g.fillRect(0, 0, this.getWidth(), this.getHeight());
                if(SoftProxy.getInstance().getCurProject().getDataManager().
                    getModels(Model.PLAYER).length==0){
                    return;
                }
                if(!((Player)SoftProxy.getInstance().getCurProject().getDataManager().
                    getModels(Model.PLAYER)[0]).charImg.equals("")){
                Image img;
                try{
                    img = ImageIO.
                    read(new File(SoftProxy.getInstance().getCurProject().getPath()+"\\image\\character\\"+
                        ((Player)SoftProxy.getInstance().getCurProject().getDataManager().getModels(Model.PLAYER)[0]).charImg));
                g.drawImage(img, this.getWidth()/2-img.getWidth(null)/2,
                    this.getHeight()/2-img.getHeight(null)/2, null);
            }catch(IOException e){
            }

        }
    }
    };
    jPanel12 = new javax.swing.JPanel();
    playerBattImgSetButton = new javax.swing.JButton();
    playerBattImgPanel = new javax.swing.JPanel(){

        public void paint(Graphics g){
            g.setColor(new Color(255,153,153));
            g.fillRect(0, 0, this.getWidth(), this.getHeight());
            if(SoftProxy.getInstance().getCurProject().getDataManager().
                getModels(Model.PLAYER).length==0){
                return;
            }
            if(!((Player)SoftProxy.getInstance().getCurProject().getDataManager().
                getModels(Model.PLAYER)[0]).headImg.equals("")){
            Image img;
            try{
                img = ImageIO.
                read(new File(SoftProxy.getInstance().getCurProject().getPath()+"\\image\\battler\\"+
                    ((Player)SoftProxy.getInstance().getCurProject().getDataManager().getModels(Model.PLAYER)[0]).headImg));
            g.drawImage(img, this.getWidth()/2-img.getWidth(null)/2,
                this.getHeight()/2-img.getHeight(null)/2, null);
        }catch(IOException e){
        }

    }
    }
    };
    enemyPanel = new javax.swing.JPanel();
    jPanel15 = new javax.swing.JPanel();
    jLabel71 = new javax.swing.JLabel();
    jLabel72 = new javax.swing.JLabel();
    enemyNameTextField = new javax.swing.JTextField();
    enemyDesTextField = new javax.swing.JTextField();
    jLabel73 = new javax.swing.JLabel();
    enemyLevelTextField = new javax.swing.JTextField();
    jPanel16 = new javax.swing.JPanel();
    enemyBattImgButton = new javax.swing.JButton();
    enemyBattImgPanel = new javax.swing.JPanel(){

        public void paint(Graphics g){
            g.setColor(new Color(255,153,153));
            g.fillRect(0, 0, this.getWidth(), this.getHeight());
            if(curSelectEnemyIndex<0
                ||curSelectEnemyIndex>=SoftProxy.getInstance().getCurProject().getDataManager().getModels(Model.ENEMY).length){
                return;
            }
            //if(((Enemy)DataManager.getInstance().
                //getModels(Model.ENEMY)[curSelectEnemyIndex])==null){
            //System.out.println("enemy null id:"+curSelectEnemyIndex);
            //return;
            //}
        if(!((Enemy)SoftProxy.getInstance().getCurProject().getDataManager().
            getModels(Model.ENEMY)[curSelectEnemyIndex]).headImg.equals("")
        &&enemyTable.getSelectedRow()>-1){
        Image img;
        try{
            img = ImageIO.
            read(new File(SoftProxy.getInstance().getCurProject().getPath()+"\\image\\battler\\"+
                ((Enemy)SoftProxy.getInstance().getCurProject().getDataManager().getModels(Model.ENEMY)[enemyTable.getSelectedRow()]).headImg));
        g.drawImage(img, this.getWidth()/2-img.getWidth(null)/2,
            this.getHeight()/2-img.getHeight(null)/2, null);
    }catch(IOException e){
    }
    }
    }
    };
    jLabel74 = new javax.swing.JLabel();
    enemyHpTextField = new javax.swing.JTextField();
    jLabel75 = new javax.swing.JLabel();
    enemySpTextField = new javax.swing.JTextField();
    jLabel76 = new javax.swing.JLabel();
    enemyAgilTextField = new javax.swing.JTextField();
    jLabel77 = new javax.swing.JLabel();
    enemyAttackTextField = new javax.swing.JTextField();
    jLabel78 = new javax.swing.JLabel();
    enemyStreTextField = new javax.swing.JTextField();
    jLabel79 = new javax.swing.JLabel();
    enemyDefendTextField = new javax.swing.JTextField();
    jLabel80 = new javax.swing.JLabel();
    enemyInteTextField = new javax.swing.JTextField();
    jPanel18 = new javax.swing.JPanel();
    jScrollPane8 = new javax.swing.JScrollPane();
    enemySkillTable = new javax.swing.JTable();
    jToolBar1 = new javax.swing.JToolBar();
    enemyAddSkillButton = new javax.swing.JButton();
    enemyRemoveSkillButton = new javax.swing.JButton();
    jPanel2 = new javax.swing.JPanel();
    jScrollPane7 = new javax.swing.JScrollPane();
    enemyTable = new javax.swing.JTable();
    jToolBar5 = new javax.swing.JToolBar();
    addEnemyButton = new javax.swing.JButton();
    removeEnemyButton = new javax.swing.JButton();
    enemyTroopPanel = new javax.swing.JPanel();
    jPanel19 = new javax.swing.JPanel();
    jLabel81 = new javax.swing.JLabel();
    enemyTroopNameTextField = new javax.swing.JTextField();
    jLabel82 = new javax.swing.JLabel();
    placeOneComboBox = new javax.swing.JComboBox();
    jLabel83 = new javax.swing.JLabel();
    placeTwoComboBox = new javax.swing.JComboBox();
    jLabel84 = new javax.swing.JLabel();
    placeFourComboBox = new javax.swing.JComboBox();
    jLabel85 = new javax.swing.JLabel();
    placeThreeComboBox = new javax.swing.JComboBox();
    jPanel20 = new javax.swing.JPanel();
    jScrollPane11 = new javax.swing.JScrollPane();
    enemyTroopEquipTable = new javax.swing.JTable();
    jToolBar2 = new javax.swing.JToolBar();
    enemyTroopAddEquipButton = new javax.swing.JButton();
    enemyTroopRemoveEquipButton = new javax.swing.JButton();
    jPanel21 = new javax.swing.JPanel();
    jScrollPane10 = new javax.swing.JScrollPane();
    enemyTroopItemTable = new javax.swing.JTable();
    jToolBar3 = new javax.swing.JToolBar();
    enemyTroopAddItemButton = new javax.swing.JButton();
    enemyTroopRemoveItemButton = new javax.swing.JButton();
    jPanel3 = new javax.swing.JPanel();
    jScrollPane9 = new javax.swing.JScrollPane();
    enemyTroopTable = new javax.swing.JTable();
    jToolBar6 = new javax.swing.JToolBar();
    addEnemyTroopButton = new javax.swing.JButton();
    removeEnemyTroopButton = new javax.swing.JButton();
    equipPanel = new javax.swing.JPanel();
    jLabel45 = new javax.swing.JLabel();
    equipNameTextField = new javax.swing.JTextField();
    jLabel46 = new javax.swing.JLabel();
    equipIconComboBox = new javax.swing.JComboBox();
    jLabel47 = new javax.swing.JLabel();
    equipDesTextField = new javax.swing.JTextField();
    jLabel48 = new javax.swing.JLabel();
    equipTypeComboBox = new javax.swing.JComboBox();
    jLabel49 = new javax.swing.JLabel();
    equipLevelTextField = new javax.swing.JTextField();
    jLabel50 = new javax.swing.JLabel();
    equipMaxHpTextField = new javax.swing.JTextField();
    jLabel51 = new javax.swing.JLabel();
    equipMaxSpTextField = new javax.swing.JTextField();
    jLabel52 = new javax.swing.JLabel();
    equipStreTextField = new javax.swing.JTextField();
    jLabel53 = new javax.swing.JLabel();
    equipAgilTextField = new javax.swing.JTextField();
    jLabel54 = new javax.swing.JLabel();
    equipInteTextField = new javax.swing.JTextField();
    jLabel55 = new javax.swing.JLabel();
    equipAttackTextField = new javax.swing.JTextField();
    jLabel56 = new javax.swing.JLabel();
    equipDefendTextField = new javax.swing.JTextField();
    jLabel57 = new javax.swing.JLabel();
    equipPriceTextField = new javax.swing.JTextField();
    jPanel4 = new javax.swing.JPanel();
    jScrollPane5 = new javax.swing.JScrollPane();
    equipTable = new javax.swing.JTable();
    jToolBar7 = new javax.swing.JToolBar();
    addEquipButton = new javax.swing.JButton();
    removeEquipButton = new javax.swing.JButton();
    itemPanel = new javax.swing.JPanel();
    jLabel14 = new javax.swing.JLabel();
    itemNameTextField = new javax.swing.JTextField();
    jLabel15 = new javax.swing.JLabel();
    itemDesTextField = new javax.swing.JTextField();
    jLabel16 = new javax.swing.JLabel();
    jLabel32 = new javax.swing.JLabel();
    itemHpTextField = new javax.swing.JTextField();
    itemTypeComboBox = new javax.swing.JComboBox();
    jLabel33 = new javax.swing.JLabel();
    itemIconComboBox = new javax.swing.JComboBox();
    jLabel34 = new javax.swing.JLabel();
    itemLevelTextField = new javax.swing.JTextField();
    jLabel35 = new javax.swing.JLabel();
    itemSpTextField = new javax.swing.JTextField();
    jLabel36 = new javax.swing.JLabel();
    itemStreTextField = new javax.swing.JTextField();
    jLabel37 = new javax.swing.JLabel();
    itemAgilTextField = new javax.swing.JTextField();
    jLabel38 = new javax.swing.JLabel();
    itemInteTextField = new javax.swing.JTextField();
    jLabel39 = new javax.swing.JLabel();
    itemAttackTextField = new javax.swing.JTextField();
    jLabel40 = new javax.swing.JLabel();
    itemDefendTextField = new javax.swing.JTextField();
    jLabel41 = new javax.swing.JLabel();
    itemExpTextField = new javax.swing.JTextField();
    jLabel42 = new javax.swing.JLabel();
    itemMaxHpTextField = new javax.swing.JTextField();
    jLabel43 = new javax.swing.JLabel();
    itemMaxSpTextField = new javax.swing.JTextField();
    jLabel44 = new javax.swing.JLabel();
    itemPriceTextField = new javax.swing.JTextField();
    jPanel5 = new javax.swing.JPanel();
    jScrollPane4 = new javax.swing.JScrollPane();
    itemTable = new javax.swing.JTable();
    jToolBar8 = new javax.swing.JToolBar();
    addItemButton = new javax.swing.JButton();
    removeItemButton = new javax.swing.JButton();
    skillPanel = new javax.swing.JPanel();
    jLabel58 = new javax.swing.JLabel();
    skillNameTextField = new javax.swing.JTextField();
    jLabel59 = new javax.swing.JLabel();
    skillIconComboBox = new javax.swing.JComboBox();
    jLabel60 = new javax.swing.JLabel();
    skillDesTextField = new javax.swing.JTextField();
    jLabel61 = new javax.swing.JLabel();
    skillTypeComboBox = new javax.swing.JComboBox();
    jLabel62 = new javax.swing.JLabel();
    skillLevelTextField = new javax.swing.JTextField();
    jLabel63 = new javax.swing.JLabel();
    skillHPTextField = new javax.swing.JTextField();
    jLabel64 = new javax.swing.JLabel();
    skillSPTextField = new javax.swing.JTextField();
    jLabel65 = new javax.swing.JLabel();
    skillAttackTextField = new javax.swing.JTextField();
    jLabel66 = new javax.swing.JLabel();
    skillDefendTextField = new javax.swing.JTextField();
    jLabel67 = new javax.swing.JLabel();
    skillMoneyTextField = new javax.swing.JTextField();
    jLabel68 = new javax.swing.JLabel();
    skillSpeedTextField = new javax.swing.JTextField();
    jLabel69 = new javax.swing.JLabel();
    skillAniComboBox = new javax.swing.JComboBox();
    jLabel70 = new javax.swing.JLabel();
    skillDpyTextField = new javax.swing.JTextField();
    jPanel1 = new javax.swing.JPanel();
    jScrollPane6 = new javax.swing.JScrollPane();
    skillTable = new javax.swing.JTable();
    jToolBar4 = new javax.swing.JToolBar();
    addSkillButton = new javax.swing.JButton();
    removeSkillButton = new javax.swing.JButton();
    okButton = new javax.swing.JButton();
    cancleButton = new javax.swing.JButton();
    applyButton = new javax.swing.JButton();

    setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    setTitle("数据编辑器");
    setResizable(false);

    dataTabbedPane.setPreferredSize(new java.awt.Dimension(750, 433));
    dataTabbedPane.addChangeListener(new javax.swing.event.ChangeListener() {
        public void stateChanged(javax.swing.event.ChangeEvent evt) {
            dataTabbedPaneStateChanged(evt);
        }
    });

    jLabel1.setText("游戏名称");

    jLabel2.setText("金钱用语");

    jLabel3.setText("sp用语");

    jLabel4.setText("hp用语");

    jLabel5.setText("攻击力用语");

    jLabel6.setText("防御力用语");

    jLabel7.setText("敏捷用语");

    jLabel8.setText("智力用语");

    jLabel9.setText("头盔用语");

    jLabel10.setText("铠甲用语");

    jLabel11.setText("盾牌用语");

    jLabel12.setText("武器用语");

    jLabel13.setText("力量用语");

    jLabel17.setText("饰品用语");

    jLabel18.setText("战靴用语");

    jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder("游戏帮助"));

    gameHelpTextArea.setColumns(20);
    gameHelpTextArea.setLineWrap(true);
    gameHelpTextArea.setRows(5);
    jScrollPane1.setViewportView(gameHelpTextArea);

    javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
    jPanel8.setLayout(jPanel8Layout);
    jPanel8Layout.setHorizontalGroup(
        jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 271, Short.MAX_VALUE)
    );
    jPanel8Layout.setVerticalGroup(
        jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE)
    );

    jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder("游戏说明"));

    gameAboutTextArea.setColumns(20);
    gameAboutTextArea.setLineWrap(true);
    gameAboutTextArea.setRows(5);
    jScrollPane2.setViewportView(gameAboutTextArea);

    javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
    jPanel9.setLayout(jPanel9Layout);
    jPanel9Layout.setHorizontalGroup(
        jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 271, Short.MAX_VALUE)
    );
    jPanel9Layout.setVerticalGroup(
        jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
    );

    javax.swing.GroupLayout systemPanelLayout = new javax.swing.GroupLayout(systemPanel);
    systemPanel.setLayout(systemPanelLayout);
    systemPanelLayout.setHorizontalGroup(
        systemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(systemPanelLayout.createSequentialGroup()
            .addContainerGap()
            .addGroup(systemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(systemPanelLayout.createSequentialGroup()
                    .addGroup(systemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel1)
                        .addComponent(jLabel2)
                        .addComponent(jLabel12)
                        .addComponent(jLabel11)
                        .addComponent(jLabel10)
                        .addComponent(jLabel3))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(systemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(systemPanelLayout.createSequentialGroup()
                            .addGroup(systemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(shieldTermTextField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                                .addComponent(moneyTermTextField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                                .addComponent(gameNameTextField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                                .addComponent(armorTermTextField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                                .addComponent(spTermTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(systemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel17)
                                .addComponent(jLabel5)
                                .addComponent(jLabel6)
                                .addComponent(jLabel7)
                                .addComponent(jLabel18)))
                        .addComponent(weaponsTermTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)))
                .addGroup(systemPanelLayout.createSequentialGroup()
                    .addGroup(systemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel9)
                        .addComponent(jLabel4))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(systemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(systemPanelLayout.createSequentialGroup()
                            .addComponent(hpTermTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel8))
                        .addGroup(systemPanelLayout.createSequentialGroup()
                            .addComponent(helmetTermTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel13)))
                    .addGap(22, 22, 22)))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(systemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(bootsTermTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
                .addComponent(forceTermTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
                .addComponent(inteTermTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
                .addComponent(agileTermTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
                .addComponent(defendTermTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
                .addComponent(attackTermTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
                .addComponent(jewelryTermTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(systemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addContainerGap(42, Short.MAX_VALUE))
    );

    systemPanelLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {armorTermTextField, gameNameTextField, helmetTermTextField, hpTermTextField, moneyTermTextField, shieldTermTextField, spTermTextField, weaponsTermTextField});

    systemPanelLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {agileTermTextField, attackTermTextField, bootsTermTextField, defendTermTextField, forceTermTextField, inteTermTextField, jewelryTermTextField});

    systemPanelLayout.setVerticalGroup(
        systemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(systemPanelLayout.createSequentialGroup()
            .addContainerGap()
            .addGroup(systemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(systemPanelLayout.createSequentialGroup()
                    .addGroup(systemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                        .addComponent(jLabel1)
                        .addComponent(gameNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(attackTermTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(systemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                        .addComponent(jLabel2)
                        .addComponent(moneyTermTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(defendTermTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(systemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(spTermTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7)
                        .addComponent(agileTermTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(systemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(systemPanelLayout.createSequentialGroup()
                            .addGroup(systemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(hpTermTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel8)
                                .addComponent(inteTermTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(9, 9, 9))
                        .addGroup(systemPanelLayout.createSequentialGroup()
                            .addComponent(jLabel4)
                            .addGap(14, 14, 14)))
                    .addGap(14, 14, 14))
                .addGroup(systemPanelLayout.createSequentialGroup()
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
            .addGroup(systemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(systemPanelLayout.createSequentialGroup()
                    .addGap(1, 1, 1)
                    .addGroup(systemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel9)
                        .addComponent(helmetTermTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel13)
                        .addComponent(forceTermTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(systemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(armorTermTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel10)
                        .addComponent(jLabel18)
                        .addComponent(bootsTermTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(systemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
                        .addComponent(shieldTermTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel17)
                        .addComponent(jewelryTermTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(systemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel12)
                        .addComponent(weaponsTermTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(116, 116, 116))
                .addGroup(systemPanelLayout.createSequentialGroup()
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap())))
    );

    systemPanelLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {armorTermTextField, gameNameTextField, helmetTermTextField, hpTermTextField, jewelryTermTextField, moneyTermTextField, shieldTermTextField, spTermTextField, weaponsTermTextField});

    systemPanelLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {agileTermTextField, attackTermTextField, bootsTermTextField, defendTermTextField, forceTermTextField, inteTermTextField});

    dataTabbedPane.addTab("系统", systemPanel);

    jLabel19.setText("角色名称");

    jLabel20.setText("封顶等级");

    jLabel21.setText("角色介绍");

    jLabel22.setText("力量初值");

    jLabel23.setText("敏捷初值");

    jLabel24.setText("智力初值");

    jLabel25.setText("力量成长");

    jLabel26.setText("敏捷成长");

    jLabel27.setText("智力成长");

    jLabel28.setText("HP初值");

    jLabel29.setText("SP初值");

    jLabel30.setText("攻击力");

    jLabel31.setText("防御力");

    jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder("经验值列表"));

    playerExpSetButton.setText("自动");
    playerExpSetButton.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            playerExpSetButtonActionPerformed(evt);
        }
    });

    playerExpTextArea.setColumns(20);
    playerExpTextArea.setRows(5);
    jScrollPane3.setViewportView(playerExpTextArea);

    javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
    jPanel10.setLayout(jPanel10Layout);
    jPanel10Layout.setHorizontalGroup(
        jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel10Layout.createSequentialGroup()
            .addComponent(playerExpSetButton, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 276, Short.MAX_VALUE))
    );
    jPanel10Layout.setVerticalGroup(
        jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
        .addComponent(playerExpSetButton, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
    );

    jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder("行走图"));

    playerMoveImgSetButton.setText("设置");
    playerMoveImgSetButton.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            playerMoveImgSetButtonActionPerformed(evt);
        }
    });

    playerMoveImgPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

    javax.swing.GroupLayout playerMoveImgPanelLayout = new javax.swing.GroupLayout(playerMoveImgPanel);
    playerMoveImgPanel.setLayout(playerMoveImgPanelLayout);
    playerMoveImgPanelLayout.setHorizontalGroup(
        playerMoveImgPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGap(0, 278, Short.MAX_VALUE)
    );
    playerMoveImgPanelLayout.setVerticalGroup(
        playerMoveImgPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGap(0, 83, Short.MAX_VALUE)
    );

    javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
    jPanel11.setLayout(jPanel11Layout);
    jPanel11Layout.setHorizontalGroup(
        jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel11Layout.createSequentialGroup()
            .addComponent(playerMoveImgSetButton, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(playerMoveImgPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );
    jPanel11Layout.setVerticalGroup(
        jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(playerMoveImgSetButton, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
        .addComponent(playerMoveImgPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    );

    jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder("战斗图"));

    playerBattImgSetButton.setText("设置");
    playerBattImgSetButton.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            playerBattImgSetButtonActionPerformed(evt);
        }
    });

    playerBattImgPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

    javax.swing.GroupLayout playerBattImgPanelLayout = new javax.swing.GroupLayout(playerBattImgPanel);
    playerBattImgPanel.setLayout(playerBattImgPanelLayout);
    playerBattImgPanelLayout.setHorizontalGroup(
        playerBattImgPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGap(0, 278, Short.MAX_VALUE)
    );
    playerBattImgPanelLayout.setVerticalGroup(
        playerBattImgPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGap(0, 83, Short.MAX_VALUE)
    );

    javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
    jPanel12.setLayout(jPanel12Layout);
    jPanel12Layout.setHorizontalGroup(
        jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel12Layout.createSequentialGroup()
            .addComponent(playerBattImgSetButton, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(playerBattImgPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );
    jPanel12Layout.setVerticalGroup(
        jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(playerBattImgPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addComponent(playerBattImgSetButton, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
    );

    javax.swing.GroupLayout playerPanelLayout = new javax.swing.GroupLayout(playerPanel);
    playerPanel.setLayout(playerPanelLayout);
    playerPanelLayout.setHorizontalGroup(
        playerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, playerPanelLayout.createSequentialGroup()
            .addContainerGap()
            .addGroup(playerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel19)
                .addComponent(jLabel21)
                .addComponent(jLabel22)
                .addComponent(jLabel24)
                .addComponent(jLabel23)
                .addComponent(jLabel28)
                .addComponent(jLabel30))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(playerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(playerPanelLayout.createSequentialGroup()
                    .addComponent(playerAttTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
                    .addGap(18, 18, 18)
                    .addComponent(jLabel31)
                    .addGap(17, 17, 17)
                    .addComponent(playerDefTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE))
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, playerPanelLayout.createSequentialGroup()
                    .addGroup(playerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(playerNameTextField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(playerStreTextField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(playerAgilTextField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
                        .addComponent(playerInteTextField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
                        .addComponent(playerHpTextField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE))
                    .addGap(18, 18, 18)
                    .addGroup(playerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel20)
                        .addComponent(jLabel25)
                        .addComponent(jLabel26)
                        .addComponent(jLabel27)
                        .addComponent(jLabel29))
                    .addGap(5, 5, 5)
                    .addGroup(playerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(playerLevelTextField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
                        .addComponent(playerStreAddTextField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
                        .addComponent(playerAgilAddTextField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
                        .addComponent(playerInteAddTextField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
                        .addComponent(playerSpTextField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addComponent(playerDesTextField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(10, 10, 10)
            .addGroup(playerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addContainerGap())
    );

    playerPanelLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {playerAgilAddTextField, playerAgilTextField, playerAttTextField, playerDefTextField, playerHpTextField, playerInteAddTextField, playerInteTextField, playerLevelTextField, playerNameTextField, playerSpTextField, playerStreAddTextField});

    playerPanelLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jPanel10, jPanel11, jPanel12});

    playerPanelLayout.setVerticalGroup(
        playerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(playerPanelLayout.createSequentialGroup()
            .addGroup(playerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(playerPanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(playerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(playerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel19)
                            .addComponent(playerNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(playerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(playerLevelTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel20)))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(playerDesTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(playerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(playerStreAddTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(playerStreTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel22)
                        .addComponent(jLabel25))
                    .addGap(18, 18, 18)
                    .addGroup(playerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(playerAgilAddTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel26)
                        .addComponent(playerAgilTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel23))
                    .addGap(18, 18, 18)
                    .addGroup(playerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(playerInteAddTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(playerInteTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel24)
                        .addComponent(jLabel27)))
                .addGroup(playerPanelLayout.createSequentialGroup()
                    .addGap(41, 41, 41)
                    .addComponent(jLabel21)))
            .addGap(18, 18, 18)
            .addGroup(playerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel29)
                .addComponent(playerSpTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(playerHpTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel28))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(playerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel30)
                .addComponent(playerAttTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel31)
                .addComponent(playerDefTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(117, 117, 117))
        .addGroup(playerPanelLayout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap())
    );

    playerPanelLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {playerAgilAddTextField, playerAgilTextField, playerAttTextField, playerDefTextField, playerDesTextField, playerHpTextField, playerInteAddTextField, playerInteTextField, playerLevelTextField, playerNameTextField, playerSpTextField, playerStreAddTextField, playerStreTextField});

    playerPanelLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jPanel10, jPanel11, jPanel12});

    dataTabbedPane.addTab("角色", playerPanel);

    jLabel71.setText("名称");

    jLabel72.setText("说明");

    enemyNameTextField.addCaretListener(new javax.swing.event.CaretListener() {
        public void caretUpdate(javax.swing.event.CaretEvent evt) {
            enemyNameTextFieldCaretUpdate(evt);
        }
    });

    jLabel73.setText("等级");

    jPanel16.setBorder(javax.swing.BorderFactory.createTitledBorder("战斗图"));

    enemyBattImgButton.setText("设置");
    enemyBattImgButton.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            enemyBattImgButtonActionPerformed(evt);
        }
    });

    enemyBattImgPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

    javax.swing.GroupLayout enemyBattImgPanelLayout = new javax.swing.GroupLayout(enemyBattImgPanel);
    enemyBattImgPanel.setLayout(enemyBattImgPanelLayout);
    enemyBattImgPanelLayout.setHorizontalGroup(
        enemyBattImgPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGap(0, 160, Short.MAX_VALUE)
    );
    enemyBattImgPanelLayout.setVerticalGroup(
        enemyBattImgPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGap(0, 83, Short.MAX_VALUE)
    );

    javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
    jPanel16.setLayout(jPanel16Layout);
    jPanel16Layout.setHorizontalGroup(
        jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel16Layout.createSequentialGroup()
            .addComponent(enemyBattImgButton, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(enemyBattImgPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );
    jPanel16Layout.setVerticalGroup(
        jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(enemyBattImgButton, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
        .addComponent(enemyBattImgPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    );

    jLabel74.setText("HP");

    jLabel75.setText("SP");

    jLabel76.setText("防御");

    jLabel77.setText("攻击");

    jLabel78.setText("力量");

    jLabel79.setText("敏捷");

    jLabel80.setText("智力");

    jPanel18.setBorder(javax.swing.BorderFactory.createTitledBorder("拥有技能"));

    enemySkillTable.setModel(estm);
    enemySkillTable.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            enemySkillTableMouseClicked(evt);
        }
    });
    jScrollPane8.setViewportView(enemySkillTable);

    jToolBar1.setFloatable(false);
    jToolBar1.setRollover(true);

    enemyAddSkillButton.setText("添加");
    enemyAddSkillButton.setFocusable(false);
    enemyAddSkillButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    enemyAddSkillButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
    enemyAddSkillButton.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            enemyAddSkillButtonActionPerformed(evt);
        }
    });
    jToolBar1.add(enemyAddSkillButton);

    enemyRemoveSkillButton.setText("删除");
    enemyRemoveSkillButton.setFocusable(false);
    enemyRemoveSkillButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    enemyRemoveSkillButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
    enemyRemoveSkillButton.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            enemyRemoveSkillButtonActionPerformed(evt);
        }
    });
    jToolBar1.add(enemyRemoveSkillButton);

    javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
    jPanel18.setLayout(jPanel18Layout);
    jPanel18Layout.setHorizontalGroup(
        jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 233, Short.MAX_VALUE)
        .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 233, Short.MAX_VALUE)
    );
    jPanel18Layout.setVerticalGroup(
        jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel18Layout.createSequentialGroup()
            .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE))
    );

    javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
    jPanel15.setLayout(jPanel15Layout);
    jPanel15Layout.setHorizontalGroup(
        jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel15Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel71)
                .addComponent(jLabel72)
                .addComponent(jLabel74)
                .addComponent(jLabel75)
                .addComponent(jLabel77)
                .addComponent(jLabel76)
                .addComponent(jLabel78)
                .addComponent(jLabel79)
                .addComponent(jLabel80)
                .addComponent(jLabel73))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(enemyNameTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
                .addComponent(enemyDesTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(enemyHpTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
                .addComponent(enemySpTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
                .addComponent(enemyAttackTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
                .addComponent(enemyDefendTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
                .addComponent(enemyStreTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(enemyAgilTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
                .addComponent(enemyInteTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
                .addComponent(enemyLevelTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    jPanel15Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {enemyAgilTextField, enemyAttackTextField, enemyDefendTextField, enemyDesTextField, enemyHpTextField, enemyInteTextField, enemyLevelTextField, enemyNameTextField, enemySpTextField, enemyStreTextField});

    jPanel15Layout.setVerticalGroup(
        jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel15Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                    .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(enemyNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel71))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(enemyDesTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel72))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel74)
                        .addComponent(enemyHpTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(10, 10, 10)
                    .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel75)
                        .addComponent(enemySpTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addComponent(jPanel16, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel15Layout.createSequentialGroup()
                    .addGap(10, 10, 10)
                    .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel77)
                        .addComponent(enemyAttackTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(9, 9, 9)
                    .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel76)
                        .addComponent(enemyDefendTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(13, 13, 13)
                    .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(enemyStreTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel78))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel79)
                        .addComponent(enemyAgilTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel80)
                        .addComponent(enemyInteTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel73)
                        .addComponent(enemyLevelTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addComponent(jPanel18, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addContainerGap(49, Short.MAX_VALUE))
    );

    jPanel15Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {enemyAgilTextField, enemyAttackTextField, enemyDefendTextField, enemyDesTextField, enemyHpTextField, enemyInteTextField, enemyLevelTextField, enemyNameTextField, enemySpTextField, enemyStreTextField});

    enemyTable.setModel(etm);
    enemyTable.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            enemyTableMouseClicked(evt);
        }
    });
    jScrollPane7.setViewportView(enemyTable);

    jToolBar5.setFloatable(false);
    jToolBar5.setRollover(true);

    addEnemyButton.setText("添加敌人");
    addEnemyButton.setFocusable(false);
    addEnemyButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    addEnemyButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
    addEnemyButton.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            addEnemyButtonActionPerformed(evt);
        }
    });
    jToolBar5.add(addEnemyButton);

    removeEnemyButton.setText("删除敌人");
    removeEnemyButton.setFocusable(false);
    removeEnemyButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    removeEnemyButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
    removeEnemyButton.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            removeEnemyButtonActionPerformed(evt);
        }
    });
    jToolBar5.add(removeEnemyButton);

    javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
    jPanel2.setLayout(jPanel2Layout);
    jPanel2Layout.setHorizontalGroup(
        jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jToolBar5, javax.swing.GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE)
        .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE)
    );
    jPanel2Layout.setVerticalGroup(
        jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel2Layout.createSequentialGroup()
            .addComponent(jToolBar5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 346, Short.MAX_VALUE))
    );

    javax.swing.GroupLayout enemyPanelLayout = new javax.swing.GroupLayout(enemyPanel);
    enemyPanel.setLayout(enemyPanelLayout);
    enemyPanelLayout.setHorizontalGroup(
        enemyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(enemyPanelLayout.createSequentialGroup()
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGap(24, 24, 24))
    );
    enemyPanelLayout.setVerticalGroup(
        enemyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(enemyPanelLayout.createSequentialGroup()
            .addGroup(enemyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addContainerGap())
    );

    dataTabbedPane.addTab("敌人", enemyPanel);

    jLabel81.setText("名称");

    enemyTroopNameTextField.setText(" ");
    enemyTroopNameTextField.addCaretListener(new javax.swing.event.CaretListener() {
        public void caretUpdate(javax.swing.event.CaretEvent evt) {
            enemyTroopNameTextFieldCaretUpdate(evt);
        }
    });

    jLabel82.setText("位置1");

    jLabel83.setText("位置2");

    jLabel84.setText("位置3");

    jLabel85.setText("位置4");

    jPanel20.setBorder(javax.swing.BorderFactory.createTitledBorder("掉落装备"));
    jPanel20.setVisible(false);

    enemyTroopEquipTable.setModel(new javax.swing.table.DefaultTableModel(
        new Object [][] {
            {null, null},
            {null, null},
            {null, null},
            {null, null}
        },
        new String [] {
            "ID", "名称"
        }
    ) {
        Class[] types = new Class [] {
            java.lang.Integer.class, java.lang.String.class
        };
        boolean[] canEdit = new boolean [] {
            false, false
        };

        public Class getColumnClass(int columnIndex) {
            return types [columnIndex];
        }

        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return canEdit [columnIndex];
        }
    });
    jScrollPane11.setViewportView(enemyTroopEquipTable);

    jToolBar2.setFloatable(false);
    jToolBar2.setRollover(true);

    enemyTroopAddEquipButton.setText("添加");
    enemyTroopAddEquipButton.setFocusable(false);
    enemyTroopAddEquipButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    enemyTroopAddEquipButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
    jToolBar2.add(enemyTroopAddEquipButton);

    enemyTroopRemoveEquipButton.setText("删除");
    enemyTroopRemoveEquipButton.setFocusable(false);
    enemyTroopRemoveEquipButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    enemyTroopRemoveEquipButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
    jToolBar2.add(enemyTroopRemoveEquipButton);

    javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
    jPanel20.setLayout(jPanel20Layout);
    jPanel20Layout.setHorizontalGroup(
        jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jToolBar2, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
        .addComponent(jScrollPane11, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
    );
    jPanel20Layout.setVerticalGroup(
        jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel20Layout.createSequentialGroup()
            .addComponent(jToolBar2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jScrollPane11, javax.swing.GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE))
    );

    jPanel21.setBorder(javax.swing.BorderFactory.createTitledBorder("掉落物品"));

    enemyTroopItemTable.setModel(etitm);
    enemyTroopItemTable.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            enemyTroopItemTableMouseClicked(evt);
        }
    });
    jScrollPane10.setViewportView(enemyTroopItemTable);

    jToolBar3.setFloatable(false);
    jToolBar3.setRollover(true);

    enemyTroopAddItemButton.setText("添加");
    enemyTroopAddItemButton.setFocusable(false);
    enemyTroopAddItemButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    enemyTroopAddItemButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
    enemyTroopAddItemButton.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            enemyTroopAddItemButtonActionPerformed(evt);
        }
    });
    jToolBar3.add(enemyTroopAddItemButton);

    enemyTroopRemoveItemButton.setText("删除");
    enemyTroopRemoveItemButton.setFocusable(false);
    enemyTroopRemoveItemButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    enemyTroopRemoveItemButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
    enemyTroopRemoveItemButton.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            enemyTroopRemoveItemButtonActionPerformed(evt);
        }
    });
    jToolBar3.add(enemyTroopRemoveItemButton);

    javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
    jPanel21.setLayout(jPanel21Layout);
    jPanel21Layout.setHorizontalGroup(
        jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
        .addComponent(jToolBar3, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
    );
    jPanel21Layout.setVerticalGroup(
        jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
            .addComponent(jToolBar3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
            .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))
    );

    javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
    jPanel19.setLayout(jPanel19Layout);
    jPanel19Layout.setHorizontalGroup(
        jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel19Layout.createSequentialGroup()
            .addGap(14, 14, 14)
            .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel19Layout.createSequentialGroup()
                    .addComponent(jLabel81)
                    .addGap(18, 18, 18)
                    .addComponent(enemyTroopNameTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 372, Short.MAX_VALUE))
                .addGroup(jPanel19Layout.createSequentialGroup()
                    .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(jPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(jPanel19Layout.createSequentialGroup()
                    .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel82)
                        .addComponent(placeOneComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel19Layout.createSequentialGroup()
                            .addGap(9, 9, 9)
                            .addComponent(jLabel83))
                        .addGroup(jPanel19Layout.createSequentialGroup()
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(placeTwoComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGap(18, 18, 18)
                    .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel84)
                        .addComponent(placeThreeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel85)
                        .addComponent(placeFourComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))))
            .addGap(44, 44, 44))
    );

    jPanel19Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {placeFourComboBox, placeOneComboBox, placeThreeComboBox, placeTwoComboBox});

    jPanel19Layout.setVerticalGroup(
        jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel81)
                .addComponent(enemyTroopNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(18, 18, 18)
            .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel82)
                .addComponent(jLabel84)
                .addComponent(jLabel85)
                .addComponent(jLabel83))
            .addGap(18, 18, 18)
            .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(placeOneComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(placeThreeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(placeFourComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(placeTwoComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addComponent(jPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addContainerGap())
    );

    jPanel19Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {placeFourComboBox, placeOneComboBox, placeThreeComboBox, placeTwoComboBox});

    enemyTroopTable.setModel(ettm);
    enemyTroopTable.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            enemyTroopTableMouseClicked(evt);
        }
    });
    jScrollPane9.setViewportView(enemyTroopTable);

    jToolBar6.setFloatable(false);
    jToolBar6.setRollover(true);

    addEnemyTroopButton.setText("添加队伍");
    addEnemyTroopButton.setFocusable(false);
    addEnemyTroopButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    addEnemyTroopButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
    addEnemyTroopButton.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            addEnemyTroopButtonActionPerformed(evt);
        }
    });
    jToolBar6.add(addEnemyTroopButton);

    removeEnemyTroopButton.setText("删除队伍");
    removeEnemyTroopButton.setFocusable(false);
    removeEnemyTroopButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    removeEnemyTroopButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
    removeEnemyTroopButton.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            removeEnemyTroopButtonActionPerformed(evt);
        }
    });
    jToolBar6.add(removeEnemyTroopButton);

    javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
    jPanel3.setLayout(jPanel3Layout);
    jPanel3Layout.setHorizontalGroup(
        jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
        .addComponent(jToolBar6, javax.swing.GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
    );
    jPanel3Layout.setVerticalGroup(
        jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel3Layout.createSequentialGroup()
            .addComponent(jToolBar6, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 345, Short.MAX_VALUE))
    );

    javax.swing.GroupLayout enemyTroopPanelLayout = new javax.swing.GroupLayout(enemyTroopPanel);
    enemyTroopPanel.setLayout(enemyTroopPanelLayout);
    enemyTroopPanelLayout.setHorizontalGroup(
        enemyTroopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(enemyTroopPanelLayout.createSequentialGroup()
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );
    enemyTroopPanelLayout.setVerticalGroup(
        enemyTroopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(enemyTroopPanelLayout.createSequentialGroup()
            .addGroup(enemyTroopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addContainerGap())
    );

    dataTabbedPane.addTab("队伍", enemyTroopPanel);

    jLabel45.setText("名称");

    equipNameTextField.setText(" ");
    equipNameTextField.addCaretListener(new javax.swing.event.CaretListener() {
        public void caretUpdate(javax.swing.event.CaretEvent evt) {
            equipNameTextFieldCaretUpdate(evt);
        }
    });

    jLabel46.setText("图标");

    equipIcons = listFileName("image\\icon\\equip");
    for (int i = 0; i < equipIcons.length ; i++) {
        equipIconComboBox.addItem(equipIcons[i]);
    }

    jLabel47.setText("说明");

    equipDesTextField.setText(" ");

    jLabel48.setText("类型");

    equipTypeComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "头盔", "饰品", "武器", "护盾", "铠甲", "战靴" }));

    jLabel49.setText("装备等级");

    jLabel50.setText("MaxHP增益");

    equipMaxHpTextField.setText(" ");

    jLabel51.setText("MaxSP增益");

    equipMaxSpTextField.setText(" ");

    jLabel52.setText("力量增益");

    equipStreTextField.setText(" ");

    jLabel53.setText("敏捷增益");

    equipAgilTextField.setText(" ");

    jLabel54.setText("智力增益");

    equipInteTextField.setText(" ");

    jLabel55.setText("攻击增益");

    equipAttackTextField.setText(" ");

    jLabel56.setText("防御增益");

    equipDefendTextField.setText(" ");

    jLabel57.setText("装备价格");

    equipPriceTextField.setText(" ");

    equipTable.setModel(eqtm);
    equipTable.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            equipTableMouseClicked(evt);
        }
    });
    jScrollPane5.setViewportView(equipTable);

    jToolBar7.setFloatable(false);
    jToolBar7.setRollover(true);

    addEquipButton.setText("添加装备");
    addEquipButton.setFocusable(false);
    addEquipButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    addEquipButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
    addEquipButton.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            addEquipButtonActionPerformed(evt);
        }
    });
    jToolBar7.add(addEquipButton);

    removeEquipButton.setText("删除装备");
    removeEquipButton.setFocusable(false);
    removeEquipButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    removeEquipButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
    removeEquipButton.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            removeEquipButtonActionPerformed(evt);
        }
    });
    jToolBar7.add(removeEquipButton);

    javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
    jPanel4.setLayout(jPanel4Layout);
    jPanel4Layout.setHorizontalGroup(
        jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jToolBar7, javax.swing.GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE)
        .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE)
    );
    jPanel4Layout.setVerticalGroup(
        jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel4Layout.createSequentialGroup()
            .addComponent(jToolBar7, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 337, Short.MAX_VALUE)
            .addContainerGap())
    );

    javax.swing.GroupLayout equipPanelLayout = new javax.swing.GroupLayout(equipPanel);
    equipPanel.setLayout(equipPanelLayout);
    equipPanelLayout.setHorizontalGroup(
        equipPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(equipPanelLayout.createSequentialGroup()
            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(18, 18, 18)
            .addGroup(equipPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel45)
                .addComponent(jLabel47)
                .addComponent(jLabel48)
                .addComponent(jLabel50)
                .addComponent(jLabel52)
                .addComponent(jLabel53)
                .addComponent(jLabel54))
            .addGap(16, 16, 16)
            .addGroup(equipPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(equipPanelLayout.createSequentialGroup()
                    .addGroup(equipPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(equipNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(equipTypeComboBox, 0, 131, Short.MAX_VALUE)
                        .addComponent(equipMaxHpTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
                        .addComponent(equipStreTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
                        .addComponent(equipAgilTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
                        .addComponent(equipInteTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(equipPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel51)
                        .addComponent(jLabel49)
                        .addGroup(equipPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel57)
                            .addComponent(jLabel56))
                        .addComponent(jLabel46)
                        .addComponent(jLabel55, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(equipPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(equipIconComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(equipLevelTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
                        .addComponent(equipMaxSpTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(equipAttackTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(equipDefendTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(equipPriceTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addComponent(equipDesTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE))
            .addGap(54, 54, 54))
    );

    equipPanelLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {equipAgilTextField, equipAttackTextField, equipDefendTextField, equipIconComboBox, equipInteTextField, equipLevelTextField, equipMaxHpTextField, equipMaxSpTextField, equipNameTextField, equipPriceTextField, equipStreTextField, equipTypeComboBox});

    equipPanelLayout.setVerticalGroup(
        equipPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addGroup(equipPanelLayout.createSequentialGroup()
            .addContainerGap()
            .addGroup(equipPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(equipIconComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel45)
                .addComponent(equipNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel46))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(equipPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(equipDesTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel47))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(equipPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(equipLevelTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel48)
                .addComponent(equipTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel49))
            .addGap(18, 18, 18)
            .addGroup(equipPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(equipMaxHpTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(equipMaxSpTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel50)
                .addComponent(jLabel51))
            .addGap(18, 18, 18)
            .addGroup(equipPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(equipAttackTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel52)
                .addComponent(equipStreTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel55))
            .addGap(18, 18, 18)
            .addGroup(equipPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(equipPanelLayout.createSequentialGroup()
                    .addGroup(equipPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(equipAgilTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel53))
                    .addGap(18, 18, 18)
                    .addGroup(equipPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(equipInteTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel54)))
                .addGroup(equipPanelLayout.createSequentialGroup()
                    .addGroup(equipPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(equipDefendTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel56))
                    .addGap(18, 18, 18)
                    .addGroup(equipPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(equipPriceTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel57))))
            .addContainerGap(129, Short.MAX_VALUE))
    );

    equipPanelLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {equipAgilTextField, equipAttackTextField, equipDefendTextField, equipIconComboBox, equipInteTextField, equipLevelTextField, equipMaxHpTextField, equipMaxSpTextField, equipNameTextField, equipPriceTextField, equipStreTextField, equipTypeComboBox});

    dataTabbedPane.addTab("装备", equipPanel);

    jLabel14.setText("名称");

    itemNameTextField.addCaretListener(new javax.swing.event.CaretListener() {
        public void caretUpdate(javax.swing.event.CaretEvent evt) {
            itemNameTextFieldCaretUpdate(evt);
        }
    });

    jLabel15.setText("说明");

    itemDesTextField.setText(" ");

    jLabel16.setText("作用");

    jLabel32.setText("HP回复");

    itemHpTextField.setText(" ");

    itemTypeComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "敌方单体", "敌方全体", "已方单体", "其他" }));

    jLabel33.setText("图标");

    itemIcons = listFileName("image\\icon\\item");
    for (int i = 0; i < itemIcons.length ; i++) {
        itemIconComboBox.addItem(itemIcons[i]);
    }

    jLabel34.setText("物品等级");

    jLabel35.setText("SP回复");

    jLabel36.setText("力量增益");

    jLabel37.setText("敏捷增益");

    itemAgilTextField.setText(" ");

    jLabel38.setText("智力增益");

    jLabel39.setText("攻击增益");

    jLabel40.setText("防御增益");

    jLabel41.setText("经验增益");

    jLabel42.setText("MaxHP增益");

    jLabel43.setText("MaxSP增益");

    jLabel44.setText("物品价格");

    itemTable.setModel(itm);
    itemTable.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            itemTableMouseClicked(evt);
        }
    });
    jScrollPane4.setViewportView(itemTable);

    jToolBar8.setFloatable(false);
    jToolBar8.setRollover(true);

    addItemButton.setText("添加物品");
    addItemButton.setFocusable(false);
    addItemButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    addItemButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
    addItemButton.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            addItemButtonActionPerformed(evt);
        }
    });
    jToolBar8.add(addItemButton);

    removeItemButton.setText("删除物品");
    removeItemButton.setFocusable(false);
    removeItemButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    removeItemButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
    removeItemButton.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            removeItemButtonActionPerformed(evt);
        }
    });
    jToolBar8.add(removeItemButton);

    javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
    jPanel5.setLayout(jPanel5Layout);
    jPanel5Layout.setHorizontalGroup(
        jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jToolBar8, javax.swing.GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE)
        .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE)
    );
    jPanel5Layout.setVerticalGroup(
        jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel5Layout.createSequentialGroup()
            .addComponent(jToolBar8, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 347, Short.MAX_VALUE))
    );

    javax.swing.GroupLayout itemPanelLayout = new javax.swing.GroupLayout(itemPanel);
    itemPanel.setLayout(itemPanelLayout);
    itemPanelLayout.setHorizontalGroup(
        itemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(itemPanelLayout.createSequentialGroup()
            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(itemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel44)
                .addComponent(jLabel42)
                .addComponent(jLabel38)
                .addComponent(jLabel37)
                .addComponent(jLabel36)
                .addComponent(jLabel32)
                .addComponent(jLabel16)
                .addComponent(jLabel15)
                .addComponent(jLabel14))
            .addGap(12, 12, 12)
            .addGroup(itemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(itemPanelLayout.createSequentialGroup()
                    .addGroup(itemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(itemNameTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
                        .addComponent(itemTypeComboBox, 0, 137, Short.MAX_VALUE)
                        .addComponent(itemHpTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
                        .addComponent(itemStreTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(itemAgilTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(itemInteTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(itemMaxHpTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(itemPriceTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(itemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel43)
                        .addComponent(jLabel41)
                        .addComponent(jLabel40)
                        .addComponent(jLabel39)
                        .addComponent(jLabel33)
                        .addComponent(jLabel34)
                        .addComponent(jLabel35))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(itemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(itemIconComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(itemLevelTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(itemSpTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(itemAttackTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(itemDefendTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(itemExpTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(itemMaxSpTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addComponent(itemDesTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE))
            .addGap(45, 45, 45))
    );

    itemPanelLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {itemAgilTextField, itemAttackTextField, itemDefendTextField, itemExpTextField, itemHpTextField, itemIconComboBox, itemInteTextField, itemLevelTextField, itemMaxHpTextField, itemMaxSpTextField, itemNameTextField, itemPriceTextField, itemSpTextField, itemStreTextField, itemTypeComboBox});

    itemPanelLayout.setVerticalGroup(
        itemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addGroup(itemPanelLayout.createSequentialGroup()
            .addContainerGap()
            .addGroup(itemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel33)
                .addComponent(itemNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(itemIconComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel14))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(itemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addComponent(itemDesTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel15))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(itemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(itemTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel34)
                .addComponent(itemLevelTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel16))
            .addGap(18, 18, 18)
            .addGroup(itemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(itemHpTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel35)
                .addComponent(itemSpTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel32))
            .addGap(18, 18, 18)
            .addGroup(itemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(itemStreTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel39)
                .addComponent(itemAttackTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel36))
            .addGap(18, 18, 18)
            .addGroup(itemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(itemAgilTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel40)
                .addComponent(itemDefendTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel37))
            .addGap(18, 18, 18)
            .addGroup(itemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(itemInteTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel41)
                .addComponent(itemExpTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel38))
            .addGap(18, 18, 18)
            .addGroup(itemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(itemMaxHpTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel43)
                .addComponent(itemMaxSpTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel42))
            .addGap(18, 18, 18)
            .addGroup(itemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(itemPriceTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel44))
            .addContainerGap(51, Short.MAX_VALUE))
    );

    itemPanelLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {itemAgilTextField, itemAttackTextField, itemDefendTextField, itemExpTextField, itemHpTextField, itemIconComboBox, itemInteTextField, itemLevelTextField, itemMaxHpTextField, itemMaxSpTextField, itemNameTextField, itemPriceTextField, itemSpTextField, itemStreTextField, itemTypeComboBox});

    dataTabbedPane.addTab("物品", itemPanel);

    jLabel58.setText("名称");

    skillNameTextField.addCaretListener(new javax.swing.event.CaretListener() {
        public void caretUpdate(javax.swing.event.CaretEvent evt) {
            skillNameTextFieldCaretUpdate(evt);
        }
    });

    jLabel59.setText("图标");

    skillIcons = listFileName("image\\icon\\skill");

    for (int i = 0; i < skillIcons.length ; i++) {
        //System.out.println("skillicon");
        skillIconComboBox.addItem(skillIcons[i]);
    }

    jLabel60.setText("说明");

    jLabel61.setText("作用");

    skillTypeComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "敌方单体", "敌方全体", "已方单体" }));

    jLabel62.setText("技能等级");

    jLabel63.setText("HP伤害");

    jLabel64.setText("SP消耗");

    jLabel65.setText("攻击削弱");

    jLabel66.setText("防御削弱");

    jLabel67.setText("消耗金钱");

    jLabel68.setText("攻速削弱");

    jLabel69.setText("播放动画");

    jLabel70.setText("分散度");

    skillTable.setModel(stm);
    skillTable.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            skillTableMouseClicked(evt);
        }
    });
    jScrollPane6.setViewportView(skillTable);

    jToolBar4.setFloatable(false);
    jToolBar4.setRollover(true);

    addSkillButton.setText("添加技能");
    addSkillButton.setFocusable(false);
    addSkillButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    addSkillButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
    addSkillButton.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            addSkillButtonActionPerformed(evt);
        }
    });
    jToolBar4.add(addSkillButton);

    removeSkillButton.setText("删除技能");
    removeSkillButton.setFocusable(false);
    removeSkillButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    removeSkillButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
    removeSkillButton.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            removeSkillButtonActionPerformed(evt);
        }
    });
    jToolBar4.add(removeSkillButton);

    javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
    jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jToolBar4, javax.swing.GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE)
        .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE)
    );
    jPanel1Layout.setVerticalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel1Layout.createSequentialGroup()
            .addComponent(jToolBar4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 347, Short.MAX_VALUE))
    );

    javax.swing.GroupLayout skillPanelLayout = new javax.swing.GroupLayout(skillPanel);
    skillPanel.setLayout(skillPanelLayout);
    skillPanelLayout.setHorizontalGroup(
        skillPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(skillPanelLayout.createSequentialGroup()
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(skillPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel69)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, skillPanelLayout.createSequentialGroup()
                    .addGroup(skillPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel58)
                        .addComponent(jLabel67)
                        .addComponent(jLabel65)
                        .addComponent(jLabel63)
                        .addComponent(jLabel61)
                        .addComponent(jLabel60))
                    .addGap(9, 9, 9)
                    .addGroup(skillPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(skillPanelLayout.createSequentialGroup()
                            .addGroup(skillPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(skillNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(skillMoneyTextField)
                                .addComponent(skillAttackTextField)
                                .addComponent(skillHPTextField)
                                .addComponent(skillTypeComboBox, 0, 144, Short.MAX_VALUE)
                                .addComponent(skillAniComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(skillPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel59)
                                .addComponent(jLabel64)
                                .addComponent(jLabel62)
                                .addComponent(jLabel66)
                                .addComponent(jLabel68)
                                .addComponent(jLabel70))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(skillPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(skillIconComboBox, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(skillPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(skillLevelTextField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, skillPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(skillDpyTextField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
                                        .addComponent(skillSpeedTextField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
                                        .addComponent(skillDefendTextField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
                                        .addComponent(skillSPTextField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)))))
                        .addComponent(skillDesTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 364, Short.MAX_VALUE))))
            .addGap(41, 41, 41))
    );

    skillPanelLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {skillAniComboBox, skillAttackTextField, skillHPTextField, skillIconComboBox, skillLevelTextField, skillMoneyTextField, skillNameTextField, skillTypeComboBox});

    skillPanelLayout.setVerticalGroup(
        skillPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addGroup(skillPanelLayout.createSequentialGroup()
            .addContainerGap()
            .addGroup(skillPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel58)
                .addComponent(skillNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel59)
                .addComponent(skillIconComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(18, 18, 18)
            .addGroup(skillPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel60)
                .addComponent(skillDesTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(20, 20, 20)
            .addGroup(skillPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addComponent(jLabel61)
                .addGroup(skillPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(skillTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel62)
                    .addComponent(skillLevelTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGap(18, 18, 18)
            .addGroup(skillPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel63)
                .addComponent(skillHPTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel64)
                .addComponent(skillSPTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(18, 18, 18)
            .addGroup(skillPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel65)
                .addComponent(skillAttackTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel66)
                .addComponent(skillDefendTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(18, 18, 18)
            .addGroup(skillPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel67)
                .addComponent(skillMoneyTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel68)
                .addComponent(skillSpeedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(18, 18, 18)
            .addGroup(skillPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(skillPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(skillAniComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel70)
                    .addComponent(skillDpyTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addComponent(jLabel69))
            .addContainerGap(111, Short.MAX_VALUE))
    );

    skillPanelLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {skillAniComboBox, skillAttackTextField, skillDefendTextField, skillDesTextField, skillDpyTextField, skillHPTextField, skillIconComboBox, skillLevelTextField, skillMoneyTextField, skillNameTextField, skillSPTextField, skillSpeedTextField, skillTypeComboBox});

    dataTabbedPane.addTab("技能", skillPanel);

    okButton.setText("确定");
    okButton.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            okButtonActionPerformed(evt);
        }
    });

    cancleButton.setText("取消");
    cancleButton.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            cancleButtonActionPerformed(evt);
        }
    });

    applyButton.setText("应用");
    applyButton.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            applyButtonActionPerformed(evt);
        }
    });

    javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
    jPanel22.setLayout(jPanel22Layout);
    jPanel22Layout.setHorizontalGroup(
        jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(okButton)
                .addGap(18, 18, 18)
                .addComponent(cancleButton)
                .addGap(18, 18, 18)
                .addComponent(applyButton)
                .addGap(12, 12, 12))
            .addComponent(dataTabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, 734, javax.swing.GroupLayout.PREFERRED_SIZE))
    );
    jPanel22Layout.setVerticalGroup(
        jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel22Layout.createSequentialGroup()
            .addComponent(dataTabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, 407, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(18, 18, 18)
            .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(applyButton)
                .addComponent(cancleButton)
                .addComponent(okButton))
            .addContainerGap(10, Short.MAX_VALUE))
    );

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jPanel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jPanel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    );

    pack();
    }// </editor-fold>//GEN-END:initComponents

    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
        // TODO add your handling code here:
        save();
        this.setVisible(false);
    }//GEN-LAST:event_okButtonActionPerformed

    private void save() {
        int num = dataTabbedPane.getSelectedIndex();
        switch (num) {
            case Model.SKILL:
                saveSkill();
                break;
            case Model.ENEMY:
                saveEnemy();
                break;
            case Model.ENEMYTROOP:
                saveEnemyTroop();
                break;
            case Model.CONFIG:
                saveConfig();
                break;
            case Model.PLAYER:
                savePlayer();
                break;
            case Model.EQUIP:
//                System.out.println("EQUIP");
                saveEquip();
                break;
            case Model.ITEM:
//                System.out.println("ITEM");
                saveItem();
                break;
        }
    }

    private void saveConfig() {
        Config config = (Config) SoftProxy.getInstance().getCurProject().getDataManager().getModels(Model.CONFIG)[0];
        config.about = gameAboutTextArea.getText();
        config.help = gameHelpTextArea.getText();
        config.agil = agileTermTextField.getText();
        config.armour = armorTermTextField.getText();
        config.atk = attackTermTextField.getText();
        config.boots = bootsTermTextField.getText();
        config.def = defendTermTextField.getText();
        config.helm = helmetTermTextField.getText();
        config.hp = hpTermTextField.getText();
        config.sp = spTermTextField.getText();
        config.inte = inteTermTextField.getText();
        config.jewelry = jewelryTermTextField.getText();
        config.money = moneyTermTextField.getText();
        config.name = gameNameTextField.getText();
        config.shield = shieldTermTextField.getText();
        config.stre = forceTermTextField.getText();
        config.weapon = weaponsTermTextField.getText();
//        SoftProxy.getInstance().getCurProject().getDataManager().saveModel(Model.CONFIG, config);
    }

    private void savePlayer() {
//        SoftProxy.getInstance().getCurProject().getDataManager().removeAllModel(Model.PLAYER);
        Player player = (Player) SoftProxy.getInstance().getCurProject().getDataManager().getModels(Model.PLAYER)[0];
//        player.setIndex(0);
        player.name = playerNameTextField.getText();
        System.out.println("player.name:" + player.name);
        player.intro = playerDesTextField.getText();
        player.lev = String2Int(playerLevelTextField.getText());
        player.stre = String2Int(playerStreTextField.getText());
        player.agil = String2Int(playerAgilTextField.getText());
        player.inte = String2Int(playerInteTextField.getText());
        player.streByLev = String2Int(playerStreAddTextField.getText());
        player.agilByLev = String2Int(playerAgilAddTextField.getText());
        player.inteByLev = String2Int(playerInteAddTextField.getText());
        player.hp = String2Int(playerHpTextField.getText());
        player.sp = String2Int(playerSpTextField.getText());
        player.atk = String2Int(playerAttTextField.getText());
        player.def = String2Int(playerDefTextField.getText());
        String[] str = playerExpTextArea.getText().trim().replace('\n', 'A').split("A");
        if (str.length != player.lev) {
            JOptionPane.showMessageDialog(this, "经验表不正确", "提醒",
                JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        player.levList = new int[player.lev];
        try {
            for (int i = 0; i < str.length; i++) {
                player.levList[i] = Integer.parseInt(str[i]);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "经验表不正确", "提醒",
                JOptionPane.INFORMATION_MESSAGE);
            return;
        }
//        SoftProxy.getInstance().getCurProject().getDataManager().saveModel(Model.PLAYER, player);
//        System.out.println("length:"+SoftProxy.getInstance().getCurProject().getDataManager().getModels(Model.PLAYER).length);
//        System.out.println("headimg:"+((Player) SoftProxy.getInstance().getCurProject().getDataManager().getModels(Model.PLAYER)[0]).headImg);
    }

    private void saveEnemy() {
//        System.out.println();
        if (curSelectEnemyIndex < 0 || curSelectEnemyIndex >= SoftProxy.getInstance().getCurProject().getDataManager().size(Model.ENEMY)) {
//            curSelectEnemyIndex = 0;
            return;
        }
        Enemy enemy = (Enemy) SoftProxy.getInstance().getCurProject().getDataManager().getModels(Model.ENEMY)[curSelectEnemyIndex];
        enemy.agil = String2Int(enemyAgilTextField.getText());
        enemy.atk = String2Int(enemyAttackTextField.getText());
        enemy.def = String2Int(enemyDefendTextField.getText());
        enemy.maxHp = String2Int(enemyHpTextField.getText());
        enemy.inte = String2Int(enemyInteTextField.getText());
        enemy.lev = String2Int(enemyLevelTextField.getText());
        enemy.maxSp = String2Int(enemySpTextField.getText());
        enemy.stre = String2Int(enemyStreTextField.getText());
        enemy.name = enemyNameTextField.getText();
        enemy.intro = enemyDesTextField.getText();
    }

    private void saveEnemyTroop() {
        if (curSelectEnemyTroopIndex < 0 || curSelectEnemyTroopIndex >= SoftProxy.getInstance().getCurProject().getDataManager().size(Model.ENEMYTROOP)) {
//            curSelectEnemyTroopIndex = 0;
            return;
        }
        EnemyTroop enemyTroop = (EnemyTroop) SoftProxy.getInstance().getCurProject().getDataManager().getModels(Model.ENEMYTROOP)[curSelectEnemyTroopIndex];
        enemyTroop.name = enemyTroopNameTextField.getText();
        if (placeOneComboBox.getSelectedIndex() == SoftProxy.getInstance().getCurProject().getDataManager().getModels(Model.ENEMY).length) {
            enemyTroop.siteA = -1;
        } else {
            enemyTroop.siteA = placeOneComboBox.getSelectedIndex();

        }
//        System.out.println("save siteA" + enemyTroop.siteA);
        if (placeTwoComboBox.getSelectedIndex() == SoftProxy.getInstance().getCurProject().getDataManager().getModels(Model.ENEMY).length) {
            enemyTroop.siteB = -1;
        } else {
            enemyTroop.siteB = placeTwoComboBox.getSelectedIndex();

        }
//        System.out.println("save siteB" + enemyTroop.siteB);
        if (placeThreeComboBox.getSelectedIndex() == SoftProxy.getInstance().getCurProject().getDataManager().getModels(Model.ENEMY).length) {
            enemyTroop.siteC = -1;
        } else {
            enemyTroop.siteC = placeThreeComboBox.getSelectedIndex();
        }
        if (placeFourComboBox.getSelectedIndex() == SoftProxy.getInstance().getCurProject().getDataManager().getModels(Model.ENEMY).length) {
            enemyTroop.siteD = -1;
        } else {
            enemyTroop.siteD = placeFourComboBox.getSelectedIndex();
        }
    }

    private void saveSkill() {
        if (curSelectSkillIndex < 0 || curSelectSkillIndex >= SoftProxy.getInstance().getCurProject().getDataManager().size(Model.SKILL)) {
//            curSelectSkillIndex = 0;
            return;
        }
        Skill skill = (Skill) SoftProxy.getInstance().getCurProject().getDataManager().getModels(Model.SKILL)[curSelectSkillIndex];
        skill.intro = skillDesTextField.getText();
        skill.aniIndex = skillAniComboBox.getSelectedIndex();
//        System.out.println("aniIndex:" + skill.aniIndex);
        skill.atk = String2Int(skillAttackTextField.getText());
        skill.def = String2Int(skillDefendTextField.getText());
        skill.dpy = String2Int(skillDpyTextField.getText());
        skill.hp = String2Int(skillHPTextField.getText());
        skill.kind = skillTypeComboBox.getSelectedIndex();
//        System.out.println("kind:" + skill.kind);
        skill.icon = ((ImageIcon) skillIconComboBox.getSelectedItem()).getDescription();
        System.out.println("skill icon:" + skill.icon);
        skill.lev = String2Int(skillLevelTextField.getText());
        skill.price = String2Int(skillMoneyTextField.getText());
        skill.sp = String2Int(skillSPTextField.getText());
        skill.speed = String2Int(skillSpeedTextField.getText());
    }

    private void saveEquip() {
        if (curSelectEquipIndex < 0 || curSelectEquipIndex >= SoftProxy.getInstance().getCurProject().getDataManager().size(Model.EQUIP)) {
//            curSelectEquipIndex = 0;
            return;
        }
//        System.out.println("equip");
        Equip equip = (Equip) SoftProxy.getInstance().getCurProject().getDataManager().getModels(Model.EQUIP)[curSelectEquipIndex];
        equip.agil = String2Int(equipAgilTextField.getText());
        equip.atk = String2Int(equipAttackTextField.getText());
        equip.def = String2Int(equipDefendTextField.getText());
        equip.inte = String2Int(equipInteTextField.getText());
        equip.kind = equipTypeComboBox.getSelectedIndex();
        equip.lev = String2Int(equipLevelTextField.getText());
        equip.maxHp = String2Int(equipMaxHpTextField.getText());
        equip.maxSp = String2Int(equipMaxSpTextField.getText());
        equip.price = String2Int(equipPriceTextField.getText());
        equip.stre = String2Int(equipStreTextField.getText());
        equip.name = equipNameTextField.getText();
        equip.intro = equipDesTextField.getText();
        equip.icon = ((ImageIcon) equipIconComboBox.getSelectedItem()).getDescription();
    }

    private void saveItem() {
        if (curSelectItemIndex < 0 || curSelectItemIndex >= SoftProxy.getInstance().getCurProject().getDataManager().size(Model.ITEM)) {
//            curSelectItemIndex = 0;
            return;
        }
        Item item = (Item) SoftProxy.getInstance().getCurProject().getDataManager().getModels(Model.ITEM)[curSelectItemIndex];
        item.agil = String2Int(itemAgilTextField.getText());
        item.atk = String2Int(itemAttackTextField.getText());
        item.def = String2Int(itemDefendTextField.getText());
        item.exp = String2Int(itemExpTextField.getText());
        item.hp = String2Int(itemHpTextField.getText());
        item.inte = String2Int(itemInteTextField.getText());
        item.kind = itemTypeComboBox.getSelectedIndex();
        item.lev = String2Int(itemLevelTextField.getText());
        item.maxHp = String2Int(itemMaxHpTextField.getText());
        item.maxSp = String2Int(itemMaxSpTextField.getText());
        item.price = String2Int(itemPriceTextField.getText());
        item.sp = String2Int(itemSpTextField.getText());
        item.stre = String2Int(itemStreTextField.getText());
        item.name = itemNameTextField.getText();
        item.intro = itemDesTextField.getText();
        item.icon = ((ImageIcon) itemIconComboBox.getSelectedItem()).getDescription();
    }
    private void cancleButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancleButtonActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
    }//GEN-LAST:event_cancleButtonActionPerformed

    private void applyButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_applyButtonActionPerformed
        // TODO add your handling code here:
        save();
    }//GEN-LAST:event_applyButtonActionPerformed
//    Dao skillDao = new SkillDao();
    private void addSkillButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addSkillButtonActionPerformed
        // TODO add your handling code here:
        Skill skill = new Skill();
        skill.setIndex(SoftProxy.getInstance().getCurProject().getDataManager().allocateIndex(Model.SKILL));
        SoftProxy.getInstance().getCurProject().getDataManager().saveModel(Model.SKILL, skill);

    }//GEN-LAST:event_addSkillButtonActionPerformed

    private void removeSkillButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeSkillButtonActionPerformed
        // TODO add your handling code here:
        int id = skillTable.getSelectedRow();
        if (id < 0 || id >= SoftProxy.getInstance().getCurProject().getDataManager().size(Model.SKILL)) {
            return;
        }
//        curSelectSkillIndex = -1;
        SoftProxy.getInstance().getCurProject().getDataManager().removeModel(Model.SKILL, SoftProxy.getInstance().getCurProject().getDataManager().getModels(Model.SKILL)[id].getIndex());
    }//GEN-LAST:event_removeSkillButtonActionPerformed

    private void addEnemyButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addEnemyButtonActionPerformed
        // TODO add your handling code here:
        Enemy enemy = new Enemy();
        enemy.setIndex(SoftProxy.getInstance().getCurProject().getDataManager().allocateIndex(Model.ENEMY));
        SoftProxy.getInstance().getCurProject().getDataManager().saveModel(Model.ENEMY, enemy);

    }//GEN-LAST:event_addEnemyButtonActionPerformed

    private void removeEnemyButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeEnemyButtonActionPerformed
        // TODO add your handling code here:
        int id = enemyTable.getSelectedRow();
        if (id < 0 || id >= SoftProxy.getInstance().getCurProject().getDataManager().size(Model.ENEMY)) {
            return;
        }
        SoftProxy.getInstance().getCurProject().getDataManager().removeModel(Model.ENEMY, SoftProxy.getInstance().getCurProject().getDataManager().getModels(Model.ENEMY)[id].getIndex());

    }//GEN-LAST:event_removeEnemyButtonActionPerformed

    private void addEnemyTroopButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addEnemyTroopButtonActionPerformed
        // TODO add your handling code here:
        EnemyTroop enemytroop = new EnemyTroop();
        enemytroop.setIndex(SoftProxy.getInstance().getCurProject().getDataManager().allocateIndex(Model.ENEMYTROOP));
        SoftProxy.getInstance().getCurProject().getDataManager().saveModel(Model.ENEMYTROOP, enemytroop);

    }//GEN-LAST:event_addEnemyTroopButtonActionPerformed

    private void removeEnemyTroopButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeEnemyTroopButtonActionPerformed
        // TODO add your handling code here:
        int id = enemyTroopTable.getSelectedRow();
        if (id < 0 || id >= SoftProxy.getInstance().getCurProject().getDataManager().size(Model.ENEMYTROOP)) {
            return;
        }

        SoftProxy.getInstance().getCurProject().getDataManager().removeModel(Model.ENEMYTROOP, SoftProxy.getInstance().getCurProject().getDataManager().getModels(Model.ENEMYTROOP)[id].getIndex());
    }//GEN-LAST:event_removeEnemyTroopButtonActionPerformed

    private void addItemButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addItemButtonActionPerformed
        // TODO add your handling code here:
        Item item = new Item();
        item.setIndex(SoftProxy.getInstance().getCurProject().getDataManager().allocateIndex(Model.ITEM));
        SoftProxy.getInstance().getCurProject().getDataManager().saveModel(Model.ITEM, item);

    }//GEN-LAST:event_addItemButtonActionPerformed

    private void removeItemButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeItemButtonActionPerformed
        // TODO add your handling code here:
        int id = itemTable.getSelectedRow();
        if (id < 0 || id >= SoftProxy.getInstance().getCurProject().getDataManager().size(Model.ITEM)) {
            return;
        }

        SoftProxy.getInstance().getCurProject().getDataManager().removeModel(Model.ITEM, SoftProxy.getInstance().getCurProject().getDataManager().getModels(Model.ITEM)[id].getIndex());
    }//GEN-LAST:event_removeItemButtonActionPerformed

    private void addEquipButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addEquipButtonActionPerformed
        // TODO add your handling code here:
        Equip equip = new Equip();
        equip.setIndex(SoftProxy.getInstance().getCurProject().getDataManager().allocateIndex(Model.EQUIP));
        SoftProxy.getInstance().getCurProject().getDataManager().saveModel(Model.EQUIP, equip);

    }//GEN-LAST:event_addEquipButtonActionPerformed

    private void removeEquipButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeEquipButtonActionPerformed
        // TODO add your handling code here:
        int id = equipTable.getSelectedRow();
        if (id < 0 || id >= SoftProxy.getInstance().getCurProject().getDataManager().size(Model.EQUIP)) {
            return;
        }

        SoftProxy.getInstance().getCurProject().getDataManager().removeModel(Model.EQUIP, SoftProxy.getInstance().getCurProject().getDataManager().getModels(Model.EQUIP)[id].getIndex());

    }//GEN-LAST:event_removeEquipButtonActionPerformed

    private void skillNameTextFieldCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_skillNameTextFieldCaretUpdate
        // TODO add your handling code here:
        int curSelectRow = skillTable.getSelectedRow();
        if (curSelectRow < 0 || curSelectRow >= SoftProxy.getInstance().getCurProject().getDataManager().getModels(Model.SKILL).length) {
            return;
        }
        ((Skill) SoftProxy.getInstance().getCurProject().getDataManager().getModels(Model.SKILL)[curSelectRow]).name = skillNameTextField.getText();
        skillTable.repaint();
    }//GEN-LAST:event_skillNameTextFieldCaretUpdate

    private void enemyNameTextFieldCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_enemyNameTextFieldCaretUpdate
        // TODO add your handling code here:
        int curSelectRow = enemyTable.getSelectedRow();
        if (curSelectRow < 0 || curSelectRow >= SoftProxy.getInstance().getCurProject().getDataManager().getModels(Model.ENEMY).length) {
            return;
        }
        ((Enemy) SoftProxy.getInstance().getCurProject().getDataManager().getModels(Model.ENEMY)[curSelectRow]).name = enemyNameTextField.getText();
        enemyTable.repaint();
    }//GEN-LAST:event_enemyNameTextFieldCaretUpdate

    private void enemyTroopNameTextFieldCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_enemyTroopNameTextFieldCaretUpdate
        // TODO add your handling code here:
        int curSelectRow = enemyTroopTable.getSelectedRow();
        if (curSelectRow < 0 || curSelectRow >= SoftProxy.getInstance().getCurProject().getDataManager().getModels(Model.ENEMYTROOP).length) {
            return;
        }
        ((EnemyTroop) SoftProxy.getInstance().getCurProject().getDataManager().getModels(Model.ENEMYTROOP)[curSelectRow]).name = enemyTroopNameTextField.getText();
        enemyTroopTable.repaint();
    }//GEN-LAST:event_enemyTroopNameTextFieldCaretUpdate

    private void equipNameTextFieldCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_equipNameTextFieldCaretUpdate
        // TODO add your handling code here:
        int curSelectRow = equipTable.getSelectedRow();
        if (curSelectRow < 0 || curSelectRow >= SoftProxy.getInstance().getCurProject().getDataManager().getModels(Model.EQUIP).length) {
            return;
        }
        ((Equip) SoftProxy.getInstance().getCurProject().getDataManager().getModels(Model.EQUIP)[curSelectRow]).name = equipNameTextField.getText();
        equipTable.repaint();
    }//GEN-LAST:event_equipNameTextFieldCaretUpdate

    private void itemNameTextFieldCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_itemNameTextFieldCaretUpdate
        // TODO add your handling code here:
        int curSelectRow = itemTable.getSelectedRow();
        if (curSelectRow < 0 || curSelectRow >= SoftProxy.getInstance().getCurProject().getDataManager().getModels(Model.ITEM).length) {
            return;
        }
        ((Item) SoftProxy.getInstance().getCurProject().getDataManager().getModels(Model.ITEM)[curSelectRow]).name = itemNameTextField.getText();
        itemTable.repaint();
    }//GEN-LAST:event_itemNameTextFieldCaretUpdate
    private int curSelectSkillIndex = -1;

    private void skillTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_skillTableMouseClicked
        // TODO add your handling code here:
        saveSkill();
        curSelectSkillIndex = skillTable.getSelectedRow();
        updateSkillPane();
    }//GEN-LAST:event_skillTableMouseClicked
    /**
     * 
     * @param s
     * @return
     */
    public int String2Int(String s) {
        int temp = 0;
        try {
            temp = Integer.parseInt(s.trim());
        } catch (Exception e) {
            temp = 0;
        }
        return temp;
    }
    private void dataTabbedPaneStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_dataTabbedPaneStateChanged
        // TODO add your handling code here:
        switch (dataTabbedPane.getSelectedIndex()) {
            case Model.ENEMYTROOP:
//                System.out.println("enemy troop update");;
                updateEnemyTroopPane();
                break;
        }
    }//GEN-LAST:event_dataTabbedPaneStateChanged
    private int curSelectEnemyIndex = -1;

    /**
     * 
     * @return
     */
    public int getCurSelectEnemyIndex() {
        return curSelectEnemyIndex;
    }
    private void enemyTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_enemyTableMouseClicked
        // TODO add your handling code here:
        saveEnemy();
        curSelectEnemyIndex = enemyTable.getSelectedRow();
        updateEnemyPane();
    }//GEN-LAST:event_enemyTableMouseClicked
    private int curSelectEquipIndex = -1;
    private void equipTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_equipTableMouseClicked
        // TODO add your handling code here:
        saveEquip();
        curSelectEquipIndex = equipTable.getSelectedRow();
        updateEquipPane();
    }//GEN-LAST:event_equipTableMouseClicked
    private void updateEquipPane() {
//        curSelectEquipIndex = equipTable.getSelectedRow();
        if (curSelectEquipIndex < 0 || curSelectEquipIndex >= SoftProxy.getInstance().getCurProject().getDataManager().getModels(Model.EQUIP).length) {
            return;
        }
        Equip equip = (Equip) SoftProxy.getInstance().getCurProject().getDataManager().getModels(Model.EQUIP)[curSelectEquipIndex];
        equipMaxHpTextField.setText("" + equip.maxHp);
        equipMaxSpTextField.setText("" + equip.maxSp);
        equipStreTextField.setText("" + equip.stre);
        equipInteTextField.setText("" + equip.inte);
        equipAgilTextField.setText("" + equip.agil);
        equipNameTextField.setText(equip.name);
        equipDesTextField.setText(equip.intro);
        equipLevelTextField.setText(equip.lev + "");
        equipAttackTextField.setText(equip.atk + "");
        equipDefendTextField.setText(equip.def + "");
        equipPriceTextField.setText(equip.price + "");
        equipTypeComboBox.setSelectedIndex(equip.kind);
        for (int i = 0; i < equipIcons.length; i++) {
            if (equip.icon.equals(equipIcons[i].getDescription())) {
                equipIconComboBox.setSelectedItem(equipIcons[i]);
                break;
            }
        }
    }
    private int curSelectItemIndex = -1;
    private void itemTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_itemTableMouseClicked
        // TODO add your handling code here:
        saveItem();
        curSelectItemIndex = itemTable.getSelectedRow();
        updateItemPane();
    }//GEN-LAST:event_itemTableMouseClicked

    private void createLevList() {
        if (playerLevelTextField.getText().equals("") || playerLevelTextField.getText() == null) {
            playerLevelTextField.setText("0");
        }
        int levMax = 0;
        try {
            levMax = Integer.parseInt(playerLevelTextField.getText());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "请输入正确的封顶等级", "提醒",
                JOptionPane.INFORMATION_MESSAGE);
        }

        if (levMax < 1) {
            JOptionPane.showMessageDialog(this, "封顶等级不能小于1", "提醒",
                JOptionPane.INFORMATION_MESSAGE);
        } else {
            playerExpTextArea.setText("");
            int[] levList = new int[levMax];
            for (int i = 1; i <= levMax; i++) {
                levList[i - 1] = i * i * 10;
                playerExpTextArea.append(levList[i - 1] + "\n");
            }
        }
    }
    private void playerExpSetButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_playerExpSetButtonActionPerformed
        // TODO add your handling code here:
        createLevList();
    }//GEN-LAST:event_playerExpSetButtonActionPerformed
    private PlayerMoveImageDialog pmid;
    private void playerMoveImgSetButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_playerMoveImgSetButtonActionPerformed
        // TODO add your handling code here:
        if (pmid == null) {
            pmid = new PlayerMoveImageDialog(this, true);
        }
        pmid.setVisible(true);
    }//GEN-LAST:event_playerMoveImgSetButtonActionPerformed
    private PlayerBattlerDialog pbd;
    private void playerBattImgSetButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_playerBattImgSetButtonActionPerformed
        // TODO add your handling code here:
        if (pbd == null) {
            pbd = new PlayerBattlerDialog(this, true);
        }
        pbd.setVisible(true);
    }//GEN-LAST:event_playerBattImgSetButtonActionPerformed

    private void enemyTroopTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_enemyTroopTableMouseClicked
        // TODO add your handling code here:
        saveEnemyTroop();
        curSelectEnemyTroopIndex = enemyTroopTable.getSelectedRow();
        updateEnemyTroopPane();
    }//GEN-LAST:event_enemyTroopTableMouseClicked
    private EnemyBattlerDialog ebd;
    private void enemyBattImgButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enemyBattImgButtonActionPerformed
        // TODO add your handling code here:
        if (ebd == null) {
            ebd = new EnemyBattlerDialog(this, true);
        }
        ebd.setVisible(true);
    }//GEN-LAST:event_enemyBattImgButtonActionPerformed

    private void enemyAddSkillButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enemyAddSkillButtonActionPerformed
        // TODO add your handling code here:
        EnemyChooseSkillDialog ecsd = new EnemyChooseSkillDialog(this, true);
        ecsd.setVisible(true);
    }//GEN-LAST:event_enemyAddSkillButtonActionPerformed

    private void enemyRemoveSkillButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enemyRemoveSkillButtonActionPerformed
        // TODO add your handling code here:
        Enemy enemy = (Enemy) SoftProxy.getInstance().getCurProject().getDataManager().getModels(Model.ENEMY)[curSelectEnemyIndex];
        curSelectEnemySkillIndex = enemySkillTable.getSelectedRow();
        if (curSelectEnemySkillIndex < 0 || curSelectEnemySkillIndex >= enemy.skillList.size()) {
            return;
        }
        enemy.skillList.remove(curSelectEnemySkillIndex);
        enemySkillTable.updateUI();
    }//GEN-LAST:event_enemyRemoveSkillButtonActionPerformed

    private void enemyTroopAddItemButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enemyTroopAddItemButtonActionPerformed
        // TODO add your handling code here:
        EnemyTroopChooseItemDialog etcid = new EnemyTroopChooseItemDialog(this, true);
        etcid.setVisible(true);
    }//GEN-LAST:event_enemyTroopAddItemButtonActionPerformed

    private void enemyTroopRemoveItemButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enemyTroopRemoveItemButtonActionPerformed
        // TODO add your handling code here:
        EnemyTroop enemyTroop = (EnemyTroop) SoftProxy.getInstance().getCurProject().getDataManager().getModels(Model.ENEMYTROOP)[curSelectEnemyTroopIndex];
        curSelectEnemyTroopItemIndex = enemyTroopItemTable.getSelectedRow();
        if (curSelectEnemyTroopItemIndex < 0 || curSelectEnemyTroopItemIndex >= enemyTroop.itemList.size()) {
            return;
        }
        enemyTroop.itemList.remove(curSelectEnemyTroopItemIndex);
        enemyTroopItemTable.updateUI();
    }//GEN-LAST:event_enemyTroopRemoveItemButtonActionPerformed

    private void enemySkillTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_enemySkillTableMouseClicked
        // TODO add your handling code here:
        curSelectEnemySkillIndex = enemySkillTable.getSelectedRow();
    }//GEN-LAST:event_enemySkillTableMouseClicked

    private void enemyTroopItemTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_enemyTroopItemTableMouseClicked
        // TODO add your handling code here:
        curSelectEnemyTroopItemIndex = enemyTroopItemTable.getSelectedRow();
    }//GEN-LAST:event_enemyTroopItemTableMouseClicked
    private int curSelectEnemyTroopItemIndex = -1;

    /**
     * 
     * @return
     */
    public int getCurSelectEnemyTroopItemIndex() {
        return curSelectEnemyTroopItemIndex;
    }

    /**
     * 
     * @param curSelectEnemyTroopItemIndex
     */
    public void setCurSelectEnemyTroopItemIndex(int curSelectEnemyTroopItemIndex) {
        this.curSelectEnemyTroopItemIndex = curSelectEnemyTroopItemIndex;
    }
    private int curSelectEnemyTroopIndex = -1;

    /**
     * 
     * @return
     */
    public int getCurSelectEnemyTroopIndex() {
        return curSelectEnemyTroopIndex;
    }

    /**
     * 
     * @param curSelectEnemyTroopIndex
     */
    public void setCurSelectEnemyTroopIndex(int curSelectEnemyTroopIndex) {
        this.curSelectEnemyTroopIndex = curSelectEnemyTroopIndex;
    }
    private int curSelectEnemySkillIndex = -1;

    /**
     * 
     * @param cur
     */
    public void setCurSelectEnemySkillIndex(int cur) {
        curSelectEnemySkillIndex = cur;
    }

    /**
     * 
     * @return
     */
    public int getCurSelectEnemySkillIndex() {
        return curSelectEnemySkillIndex;
    }

    private void updateEnemyTroopPane() {
//        curSelectEnemyTroopIndex = enemyTroopTable.getSelectedRow();
        if (curSelectEnemyTroopIndex < 0 || curSelectEnemyTroopIndex >= SoftProxy.getInstance().getCurProject().getDataManager().getModels(Model.ENEMYTROOP).length) {
            return;
        }
        placeOneComboBox.removeAllItems();
        placeTwoComboBox.removeAllItems();
        placeThreeComboBox.removeAllItems();
        placeFourComboBox.removeAllItems();
        for (int i = 0; i < SoftProxy.getInstance().getCurProject().getDataManager().getModels(Model.ENEMY).length; i++) {
            Enemy enemy = (Enemy) SoftProxy.getInstance().getCurProject().getDataManager().getModels(Model.ENEMY)[i];
            placeOneComboBox.addItem(enemy.name);
            placeTwoComboBox.addItem(enemy.name);
            placeThreeComboBox.addItem(enemy.name);
            placeFourComboBox.addItem(enemy.name);
        }
        placeOneComboBox.addItem("无");
        placeTwoComboBox.addItem("无");
        placeThreeComboBox.addItem("无");
        placeFourComboBox.addItem("无");
        EnemyTroop enemyTroop = (EnemyTroop) SoftProxy.getInstance().getCurProject().getDataManager().getModels(Model.ENEMYTROOP)[curSelectEnemyTroopIndex];
        enemyTroopNameTextField.setText(enemyTroop.name);
        enemyTroopItemTable.updateUI();
        if (enemyTroop.siteA > -1 && enemyTroop.siteA != SoftProxy.getInstance().getCurProject().getDataManager().getModels(Model.ENEMY).length) {
            placeOneComboBox.setSelectedIndex(enemyTroop.siteA);
        } else {
            placeOneComboBox.setSelectedIndex(SoftProxy.getInstance().getCurProject().getDataManager().getModels(Model.ENEMY).length);
        }
        if (enemyTroop.siteB > -1 && enemyTroop.siteA != SoftProxy.getInstance().getCurProject().getDataManager().getModels(Model.ENEMY).length) {
            placeTwoComboBox.setSelectedIndex(enemyTroop.siteB);
        } else {
            placeTwoComboBox.setSelectedIndex(SoftProxy.getInstance().getCurProject().getDataManager().getModels(Model.ENEMY).length);
        }
        if (enemyTroop.siteC > -1 && enemyTroop.siteA != SoftProxy.getInstance().getCurProject().getDataManager().getModels(Model.ENEMY).length) {
            placeThreeComboBox.setSelectedIndex(enemyTroop.siteC);
        } else {
            placeThreeComboBox.setSelectedIndex(SoftProxy.getInstance().getCurProject().getDataManager().getModels(Model.ENEMY).length);
        }
//        System.err.println("siteD:" + enemyTroop.siteD);
        if (enemyTroop.siteD > -1 && enemyTroop.siteA != SoftProxy.getInstance().getCurProject().getDataManager().getModels(Model.ENEMY).length) {
            placeFourComboBox.setSelectedIndex(enemyTroop.siteD);
        } else {
            placeFourComboBox.setSelectedIndex(SoftProxy.getInstance().getCurProject().getDataManager().getModels(Model.ENEMY).length);
        }
    }

    private void updateItemPane() {
//        curSelectItemIndex = itemTable.getSelectedRow();
        if (curSelectItemIndex < 0 || curSelectItemIndex >= SoftProxy.getInstance().getCurProject().getDataManager().getModels(Model.ITEM).length) {
            return;
        }
        Item item = (Item) SoftProxy.getInstance().getCurProject().getDataManager().getModels(Model.ITEM)[curSelectItemIndex];
        itemMaxHpTextField.setText("" + item.maxHp);
        itemMaxSpTextField.setText("" + item.maxSp);
        itemStreTextField.setText("" + item.stre);
        itemInteTextField.setText("" + item.inte);
        itemExpTextField.setText("" + item.exp);
        itemAgilTextField.setText("" + item.agil);
        itemNameTextField.setText(item.name);
        itemDesTextField.setText(item.intro);
        itemLevelTextField.setText(item.lev + "");
        itemHpTextField.setText(item.hp + "");
        itemSpTextField.setText(item.sp + "");
        itemAttackTextField.setText(item.atk + "");
        itemDefendTextField.setText(item.def + "");
        itemPriceTextField.setText(item.price + "");
        itemTypeComboBox.setSelectedIndex(item.kind);
        for (int i = 0; i < itemIcons.length; i++) {
            if (item.icon.equals(itemIcons[i].getDescription())) {
                itemIconComboBox.setSelectedItem(itemIcons[i]);
                break;
            }
        }
    }
    private ImageIcon skillIcons[];
    private ImageIcon itemIcons[];
    private ImageIcon equipIcons[];

    private ImageIcon[] listFileName(String path) {

        File f = new File(SoftProxy.getInstance().getCurProject().getPath() + "\\" + path);
//        System.out.println("listfilename:" + SoftProxy.getInstance().getCurProject().getPath() + "\\" + path);
        String[] s = f.list();
        ImageIcon[] ii = new ImageIcon[s.length];
        for (int i = 0; i < s.length; i++) {
            ii[i] = new ImageIcon(SoftProxy.getInstance().getCurProject().getPath() + "\\" + path + "\\"
                + s[i], s[i]);
        }
        return ii;
    }

    private void updateEnemyPane() {

//        System.out.println("enemy remove id:" + curSelectEnemyIndex);
        if (curSelectEnemyIndex < 0 || curSelectEnemyIndex >= SoftProxy.getInstance().getCurProject().getDataManager().getModels(Model.ENEMY).length) {
            return;
        }
        Enemy enemy = (Enemy) SoftProxy.getInstance().getCurProject().getDataManager().getModels(Model.ENEMY)[curSelectEnemyIndex];
        enemyAgilTextField.setText("" + enemy.agil);
        enemyAttackTextField.setText("" + enemy.atk);
        enemyDefendTextField.setText("" + enemy.def);
        enemyHpTextField.setText("" + enemy.maxHp);
        enemyInteTextField.setText("" + enemy.inte);
        enemyLevelTextField.setText("" + enemy.lev);
        enemySpTextField.setText("" + enemy.maxSp);
        enemyStreTextField.setText("" + enemy.stre);
        enemyNameTextField.setText(enemy.name);
        enemyDesTextField.setText(enemy.intro);
        enemyBattImgPanel.repaint();
        enemySkillTable.updateUI();
    }

    private void updateSkillPane() {
//        curSelectSkillIndex = skillTable.getSelectedRow();
        if (curSelectSkillIndex < 0 || curSelectSkillIndex >= SoftProxy.getInstance().getCurProject().getDataManager().getModels(Model.SKILL).length) {
            return;
        }
        Skill skill = (Skill) SoftProxy.getInstance().getCurProject().getDataManager().getModels(Model.SKILL)[curSelectSkillIndex];
        skillNameTextField.setText(skill.name);
        skillDesTextField.setText(skill.intro);
        skillLevelTextField.setText(skill.lev + "");
        skillHPTextField.setText(skill.hp + "");
        skillSPTextField.setText(skill.sp + "");
        skillAttackTextField.setText(skill.atk + "");
        skillDefendTextField.setText(skill.def + "");
        skillMoneyTextField.setText(skill.price + "");
        skillSpeedTextField.setText(skill.speed + "");
        skillDpyTextField.setText(skill.dpy + "");
        skillTypeComboBox.setSelectedIndex(skill.kind);
        for (int i = 0; i < skillIcons.length; i++) {
            if (skill.icon.equals(skillIcons[i].getDescription())) {
                skillIconComboBox.setSelectedItem(skillIcons[i]);
                break;
            }
        }
        if (skill.aniIndex > -1) {
            skillAniComboBox.setSelectedIndex(skill.aniIndex);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                DataEditorDialog dialog = new DataEditorDialog(null, true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {

                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
    private EnemyTroopItemTableModel etitm;
    private EnemySkillTableModel estm;
    private SkillTableModel stm;
    private ItemTableModel itm;
    private EnemyTableModel etm;
    private EnemyTroopTableModel ettm;
    private EquipTableModel eqtm;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addEnemyButton;
    private javax.swing.JButton addEnemyTroopButton;
    private javax.swing.JButton addEquipButton;
    private javax.swing.JButton addItemButton;
    private javax.swing.JButton addSkillButton;
    private javax.swing.JTextField agileTermTextField;
    private javax.swing.JButton applyButton;
    private javax.swing.JTextField armorTermTextField;
    private javax.swing.JTextField attackTermTextField;
    private javax.swing.JTextField bootsTermTextField;
    private javax.swing.JButton cancleButton;
    private javax.swing.JTabbedPane dataTabbedPane;
    private javax.swing.JTextField defendTermTextField;
    private javax.swing.JButton enemyAddSkillButton;
    private javax.swing.JTextField enemyAgilTextField;
    private javax.swing.JTextField enemyAttackTextField;
    private javax.swing.JButton enemyBattImgButton;
    public javax.swing.JPanel enemyBattImgPanel;
    private javax.swing.JTextField enemyDefendTextField;
    private javax.swing.JTextField enemyDesTextField;
    private javax.swing.JTextField enemyHpTextField;
    private javax.swing.JTextField enemyInteTextField;
    private javax.swing.JTextField enemyLevelTextField;
    private javax.swing.JTextField enemyNameTextField;
    private javax.swing.JPanel enemyPanel;
    private javax.swing.JButton enemyRemoveSkillButton;
    public javax.swing.JTable enemySkillTable;
    private javax.swing.JTextField enemySpTextField;
    private javax.swing.JTextField enemyStreTextField;
    private javax.swing.JTable enemyTable;
    private javax.swing.JButton enemyTroopAddEquipButton;
    private javax.swing.JButton enemyTroopAddItemButton;
    private javax.swing.JTable enemyTroopEquipTable;
    public javax.swing.JTable enemyTroopItemTable;
    private javax.swing.JTextField enemyTroopNameTextField;
    private javax.swing.JPanel enemyTroopPanel;
    private javax.swing.JButton enemyTroopRemoveEquipButton;
    private javax.swing.JButton enemyTroopRemoveItemButton;
    private javax.swing.JTable enemyTroopTable;
    private javax.swing.JTextField equipAgilTextField;
    private javax.swing.JTextField equipAttackTextField;
    private javax.swing.JTextField equipDefendTextField;
    private javax.swing.JTextField equipDesTextField;
    private javax.swing.JComboBox equipIconComboBox;
    private javax.swing.JTextField equipInteTextField;
    private javax.swing.JTextField equipLevelTextField;
    private javax.swing.JTextField equipMaxHpTextField;
    private javax.swing.JTextField equipMaxSpTextField;
    private javax.swing.JTextField equipNameTextField;
    private javax.swing.JPanel equipPanel;
    private javax.swing.JTextField equipPriceTextField;
    private javax.swing.JTextField equipStreTextField;
    private javax.swing.JTable equipTable;
    private javax.swing.JComboBox equipTypeComboBox;
    private javax.swing.JTextField forceTermTextField;
    private javax.swing.JTextArea gameAboutTextArea;
    private javax.swing.JTextArea gameHelpTextArea;
    private javax.swing.JTextField gameNameTextField;
    private javax.swing.JTextField helmetTermTextField;
    private javax.swing.JTextField hpTermTextField;
    private javax.swing.JTextField inteTermTextField;
    private javax.swing.JTextField itemAgilTextField;
    private javax.swing.JTextField itemAttackTextField;
    private javax.swing.JTextField itemDefendTextField;
    private javax.swing.JTextField itemDesTextField;
    private javax.swing.JTextField itemExpTextField;
    private javax.swing.JTextField itemHpTextField;
    private javax.swing.JComboBox itemIconComboBox;
    private javax.swing.JTextField itemInteTextField;
    private javax.swing.JTextField itemLevelTextField;
    private javax.swing.JTextField itemMaxHpTextField;
    private javax.swing.JTextField itemMaxSpTextField;
    private javax.swing.JTextField itemNameTextField;
    private javax.swing.JPanel itemPanel;
    private javax.swing.JTextField itemPriceTextField;
    private javax.swing.JTextField itemSpTextField;
    private javax.swing.JTextField itemStreTextField;
    private javax.swing.JTable itemTable;
    private javax.swing.JComboBox itemTypeComboBox;
    private javax.swing.JLabel jLabel1;
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
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JToolBar jToolBar2;
    private javax.swing.JToolBar jToolBar3;
    private javax.swing.JToolBar jToolBar4;
    private javax.swing.JToolBar jToolBar5;
    private javax.swing.JToolBar jToolBar6;
    private javax.swing.JToolBar jToolBar7;
    private javax.swing.JToolBar jToolBar8;
    private javax.swing.JTextField jewelryTermTextField;
    private javax.swing.JTextField moneyTermTextField;
    private javax.swing.JButton okButton;
    private javax.swing.JComboBox placeFourComboBox;
    private javax.swing.JComboBox placeOneComboBox;
    private javax.swing.JComboBox placeThreeComboBox;
    private javax.swing.JComboBox placeTwoComboBox;
    private javax.swing.JTextField playerAgilAddTextField;
    private javax.swing.JTextField playerAgilTextField;
    private javax.swing.JTextField playerAttTextField;
    public javax.swing.JPanel playerBattImgPanel;
    private javax.swing.JButton playerBattImgSetButton;
    private javax.swing.JTextField playerDefTextField;
    private javax.swing.JTextField playerDesTextField;
    private javax.swing.JButton playerExpSetButton;
    private javax.swing.JTextArea playerExpTextArea;
    private javax.swing.JTextField playerHpTextField;
    private javax.swing.JTextField playerInteAddTextField;
    private javax.swing.JTextField playerInteTextField;
    private javax.swing.JTextField playerLevelTextField;
    public javax.swing.JPanel playerMoveImgPanel;
    private javax.swing.JButton playerMoveImgSetButton;
    private javax.swing.JTextField playerNameTextField;
    private javax.swing.JPanel playerPanel;
    private javax.swing.JTextField playerSpTextField;
    private javax.swing.JTextField playerStreAddTextField;
    private javax.swing.JTextField playerStreTextField;
    private javax.swing.JButton removeEnemyButton;
    private javax.swing.JButton removeEnemyTroopButton;
    private javax.swing.JButton removeEquipButton;
    private javax.swing.JButton removeItemButton;
    private javax.swing.JButton removeSkillButton;
    private javax.swing.JTextField shieldTermTextField;
    private javax.swing.JComboBox skillAniComboBox;
    private javax.swing.JTextField skillAttackTextField;
    private javax.swing.JTextField skillDefendTextField;
    private javax.swing.JTextField skillDesTextField;
    private javax.swing.JTextField skillDpyTextField;
    private javax.swing.JTextField skillHPTextField;
    private javax.swing.JComboBox skillIconComboBox;
    private javax.swing.JTextField skillLevelTextField;
    private javax.swing.JTextField skillMoneyTextField;
    private javax.swing.JTextField skillNameTextField;
    private javax.swing.JPanel skillPanel;
    private javax.swing.JTextField skillSPTextField;
    private javax.swing.JTextField skillSpeedTextField;
    private javax.swing.JTable skillTable;
    private javax.swing.JComboBox skillTypeComboBox;
    private javax.swing.JTextField spTermTextField;
    private javax.swing.JPanel systemPanel;
    private javax.swing.JTextField weaponsTermTextField;
    // End of variables declaration//GEN-END:variables

    /**
     * 
     * @param sce
     * @param skill
     */
    @Override
    public void skillAdded(DataChangedEvent sce, Skill skill) {
//        System.out.println("skill add");
        skillTable.getSelectionModel().setSelectionInterval(stm.getRowCount() - 1, stm.getRowCount() - 1);
        saveSkill();
        curSelectSkillIndex = stm.getRowCount() - 1;
        updateSkillPane();
        skillTable.updateUI();
    }

    /**
     * 
     * @param sce
     * @param id
     */
    @Override
    public void skillRemoved(DataChangedEvent sce, int id) {
        System.out.println("skill remove");
        skillTable.getSelectionModel().setSelectionInterval(stm.getRowCount() - 1, stm.getRowCount() - 1);
        curSelectSkillIndex = stm.getRowCount() - 1;
        updateSkillPane();
        skillTable.updateUI();
    }

    /**
     * 
     * @param sce
     * @param enemy
     */
    @Override
    public void enemyAdded(DataChangedEvent sce, Enemy enemy) {
//        System.out.println("enemy add " + enemy.headImg);
        enemyTable.getSelectionModel().setSelectionInterval(etm.getRowCount() - 1, etm.getRowCount() - 1);
        saveEnemy();
        curSelectEnemyIndex = etm.getRowCount() - 1;
        updateEnemyPane();
        enemyTable.updateUI();
    }

    /**
     * 
     * @param sce
     * @param id
     */
    @Override
    public void enemyRemoved(DataChangedEvent sce, int id) {
        System.out.println("enemy remove");
        enemyTable.getSelectionModel().setSelectionInterval(etm.getRowCount() - 1, etm.getRowCount() - 1);
//        saveEnemy();
        curSelectEnemyIndex = etm.getRowCount() - 1;
//        System.out.println("enemy remove id:" + curSelectEnemyIndex);
        updateEnemyPane();
        enemyTable.updateUI();

    }

    /**
     * 
     * @param sce
     * @param enemytroop
     */
    @Override
    public void enemyTroopAdded(DataChangedEvent sce, EnemyTroop enemytroop) {
//        System.out.println("enemytroop add ");
        enemyTroopTable.getSelectionModel().setSelectionInterval(ettm.getRowCount() - 1, ettm.getRowCount() - 1);
        saveEnemyTroop();
        curSelectEnemyTroopIndex = ettm.getRowCount() - 1;
        updateEnemyTroopPane();
        enemyTroopTable.updateUI();
    }

    /**
     * 
     * @param sce
     * @param id
     */
    @Override
    public void enemyTroopRemoved(DataChangedEvent sce, int id) {
        System.out.println("enemyTroop remove");
        enemyTroopTable.getSelectionModel().setSelectionInterval(ettm.getRowCount() - 1, ettm.getRowCount() - 1);
        curSelectEnemyTroopIndex = ettm.getRowCount() - 1;
        updateEnemyTroopPane();
        enemyTroopTable.updateUI();
    }

    /**
     * 
     * @param sce
     * @param equip
     */
    @Override
    public void equipAdded(DataChangedEvent sce, Equip equip) {
        equipTable.getSelectionModel().setSelectionInterval(eqtm.getRowCount() - 1, eqtm.getRowCount() - 1);
        saveEquip();
        curSelectEquipIndex = eqtm.getRowCount() - 1;
        updateEquipPane();
        equipTable.updateUI();
    }

    /**
     * 
     * @param sce
     * @param id
     */
    @Override
    public void equipRemoved(DataChangedEvent sce, int id) {
        System.out.println("equip remove");
        equipTable.getSelectionModel().setSelectionInterval(eqtm.getRowCount() - 1, eqtm.getRowCount() - 1);
        curSelectEquipIndex = eqtm.getRowCount() - 1;
        updateEquipPane();
        equipTable.updateUI();
    }

    /**
     * 
     * @param sce
     * @param item
     */
    @Override
    public void itemAdded(DataChangedEvent sce, Item item) {
//        System.out.println("item add");
        itemTable.getSelectionModel().setSelectionInterval(itm.getRowCount() - 1, itm.getRowCount() - 1);
        saveItem();
        curSelectItemIndex = itm.getRowCount() - 1;
        updateItemPane();
        itemTable.updateUI();
    }

    /**
     * 
     * @param sce
     * @param id
     */
    @Override
    public void itemRemoved(DataChangedEvent sce, int id) {
        System.out.println("item remove");
        itemTable.getSelectionModel().setSelectionInterval(itm.getRowCount() - 1, itm.getRowCount() - 1);
        curSelectItemIndex = itm.getRowCount() - 1;
        updateItemPane();
        itemTable.updateUI();
    }
}
