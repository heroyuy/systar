/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * MainFrame.java
 *
 * Created on 2011-3-13, 13:42:40
 */
package com.soyomaker;

import com.soyomaker.config.Configuration;
import com.soyomaker.config.Preference;
import com.soyomaker.listener.ProjectChangedEvent;
import com.soyomaker.log.ILogDisplayer;
import com.soyomaker.model.animation.Animation;
import com.soyomaker.model.animation.Picture;
import com.soyomaker.model.map.Npc;
import com.soyomaker.model.map.Script;
import com.soyomaker.dialog.AboutDialog;
import com.soyomaker.dialog.EditMapDialog;
import com.soyomaker.dialog.ShortcutDialog;
import com.soyomaker.dialog.DataEditorDialog;
import com.soyomaker.dialog.NewMapDialog;
import com.soyomaker.dialog.NewProjectDialog;
import com.soyomaker.dialog.NewTileSetDialog;
import com.soyomaker.dialog.PluginManagerDialog;
import com.soyomaker.dialog.PreferenceDialog;
import com.soyomaker.dialog.PreviewMapDialog;
import com.soyomaker.dialog.ResourceManagerDialog;
import com.soyomaker.dialog.SpriteEditorDialog;
import com.soyomaker.dialog.TileSetManagerDialog;
import com.soyomaker.infomation.SoftInformation;
import com.soyomaker.io.animation.DefaultAnimationLuaWriter;
import com.soyomaker.io.animation.DefaultSoftAnimationBinaryReader;
import com.soyomaker.io.animation.DefaultSoftAnimationBinaryWriter;
import com.soyomaker.io.map.DefaultSoftMapBinaryReader;
import com.soyomaker.io.map.DefaultSoftMapBinaryWriter;
import com.soyomaker.io.animation.IAnimationReader;
import com.soyomaker.io.animation.IAnimationWriter;
import com.soyomaker.io.map.DefaultMapLuaWriter;
import com.soyomaker.io.map.DefaultNpcLuaWriter;
import com.soyomaker.io.map.DefaultScriptLuaWriter;
import com.soyomaker.io.map.IMapReader;
import com.soyomaker.io.map.IMapWriter;
import com.soyomaker.io.map.INpcWriter;
import com.soyomaker.io.map.IScriptWriter;
import com.soyomaker.listener.LayerChangeListener;
import com.soyomaker.listener.LayerChangedEvent;
import com.soyomaker.listener.MapChangeListener;
import com.soyomaker.listener.MapChangedEvent;
import com.soyomaker.listener.ProjectChangeListener;
import com.soyomaker.log.LogPrinter;
import com.soyomaker.log.LogPrinterFactory;
import com.soyomaker.log.LogPrinterListener;
import com.soyomaker.log.SMLogDisplayer;
import com.soyomaker.model.map.CollideLayer;
import com.soyomaker.model.map.Layer;
import com.soyomaker.observer.Event;
import com.soyomaker.tablemodel.LayerTableModel;
import com.soyomaker.model.map.Map;
import com.soyomaker.model.map.SpriteLayer;
import com.soyomaker.model.map.TileLayer;
import com.soyomaker.model.map.TileSet;
import com.soyomaker.observer.EventIdConst;
import com.soyomaker.observer.Notifier;
import com.soyomaker.observer.Observer;
import com.soyomaker.plugin.SMPluginManager;
import com.soyomaker.project.Project;
import com.soyomaker.project.ProjectManager;
import com.soyomaker.render.MapRender;
import com.soyomaker.render.MapRenderFactory;
import com.soyomaker.render.RenderListener;
import com.soyomaker.script.ScriptEditorDialog;
import com.soyomaker.util.FileUtil;
import com.soyomaker.util.UIUtil;
import com.soyomaker.widget.JInfiniteProgressPanel;
import java.awt.AWTException;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executor;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

/**
 *
 * @author Administrator
 */
public class AppMainFrame extends javax.swing.JFrame implements RenderListener, MapChangeListener, LayerChangeListener, ProjectChangeListener, DropTargetListener, LogPrinterListener, Observer {

    /** Creates new form MainFrame */
    public AppMainFrame() {
        long time = System.currentTimeMillis();

        logPrinter.addLogPrinterListener(this);

        try {
            SMPluginManager.getInstance().readPluginDir(new File("plugin"));
        } catch (Exception ex) {
            ex.printStackTrace();
            logPrinter.e("插件读取异常！" + ex.toString());
        }

        initComponents();
        systemTray();
        initialize();

        SMLogDisplayer tqtld = new SMLogDisplayer();
        tqtld.setName("软件日志");
        logPrinter.addLogDisplayer(tqtld);

        data.setMainFrame(this);

        logPrinter.v("软件启动完成！耗时：" + (System.currentTimeMillis() - time) + "ms");
    }

    private void initialize() {
        Notifier.getInstance().addObserver(this, EventIdConst.GAME_MAP_SAVE_FAILURE);
        Notifier.getInstance().addObserver(this, EventIdConst.GAME_MAP_SAVE_SUCCESSFUL);
        Notifier.getInstance().addObserver(this, EventIdConst.GAME_DATA_SAVE_FAILURE);
        Notifier.getInstance().addObserver(this, EventIdConst.GAME_DATA_SAVE_SUCCESSFUL);
        Notifier.getInstance().addObserver(this, EventIdConst.GAME_ANIMATION_SAVE_FAILURE);
        Notifier.getInstance().addObserver(this, EventIdConst.GAME_ANIMATION_SAVE_SUCCESSFUL);
        Notifier.getInstance().addObserver(this, EventIdConst.SOFT_MAP_SAVE_FAILURE);
        Notifier.getInstance().addObserver(this, EventIdConst.SOFT_MAP_SAVE_SUCCESSFUL);
        Notifier.getInstance().addObserver(this, EventIdConst.SOFT_DATA_SAVE_FAILURE);
        Notifier.getInstance().addObserver(this, EventIdConst.SOFT_DATA_SAVE_SUCCESSFUL);
        Notifier.getInstance().addObserver(this, EventIdConst.SOFT_ANIMATION_SAVE_FAILURE);
        Notifier.getInstance().addObserver(this, EventIdConst.SOFT_ANIMATION_SAVE_SUCCESSFUL);
        Notifier.getInstance().addObserver(this, EventIdConst.SOFT_MAP_LOAD_FAILURE);
        Notifier.getInstance().addObserver(this, EventIdConst.SOFT_MAP_LOAD_SUCCESSFUL);
        Notifier.getInstance().addObserver(this, EventIdConst.SOFT_DATA_LOAD_FAILURE);
        Notifier.getInstance().addObserver(this, EventIdConst.SOFT_DATA_LOAD_SUCCESSFUL);
        Notifier.getInstance().addObserver(this, EventIdConst.SOFT_ANIMATION_LOAD_FAILURE);
        Notifier.getInstance().addObserver(this, EventIdConst.SOFT_ANIMATION_LOAD_SUCCESSFUL);
        Notifier.getInstance().addObserver(this, EventIdConst.SOFT_NEW_ANIMATION_LOAD_FAILURE);
        Notifier.getInstance().addObserver(this, EventIdConst.SOFT_NEW_ANIMATION_LOAD_SUCCESSFUL);
        Notifier.getInstance().addObserver(this, EventIdConst.SOFT_NEW_DATA_LOAD_FAILURE);
        Notifier.getInstance().addObserver(this, EventIdConst.SOFT_NEW_DATA_LOAD_SUCCESSFUL);
        Notifier.getInstance().addObserver(this, EventIdConst.SOFT_NEW_COPY_RES_FAILURE);
        Notifier.getInstance().addObserver(this, EventIdConst.SOFT_NEW_COPY_RES_SUCCESSFUL);
        this.setGlassPane(gGrassPane);
        updateRecent(null);
        new DropTarget(this, DnDConstants.ACTION_COPY_OR_MOVE, this);
        setLocationRelativeTo(null);
        setTitle(SoftInformation.chineseName + " "
                + SoftInformation.getVersion());

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Rectangle bounds = new Rectangle(screenSize);
        Insets insets = Toolkit.getDefaultToolkit().getScreenInsets(this.getGraphicsConfiguration());
        bounds.x += insets.left;
        bounds.y += insets.top;
        bounds.width -= insets.left + insets.right;
        bounds.height -= insets.top + insets.bottom;
        this.setBounds(bounds);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        trayPopMenu = new java.awt.PopupMenu();
        openPopMenuItem = new java.awt.MenuItem();
        exitPopMenuItem = new java.awt.MenuItem();
        buttonGroup = new javax.swing.ButtonGroup();
        mapPopupMenu = new javax.swing.JPopupMenu();
        newMapMenuItem = new javax.swing.JMenuItem();
        removeMapMenuItem = new javax.swing.JMenuItem();
        editMapMenuItem = new javax.swing.JMenuItem();
        previewMapMenuItem = new javax.swing.JMenuItem();
        copyMapMenuItem = new javax.swing.JMenuItem();
        pasteMapMenuItem = new javax.swing.JMenuItem();
        importMapMenuItem = new javax.swing.JMenuItem();
        saveMapMenuItem = new javax.swing.JMenuItem();
        buttonGroup1 = new javax.swing.ButtonGroup();
        stateBarPanel = new javax.swing.JPanel();
        stateLabel = new javax.swing.JLabel();
        toolBar = new javax.swing.JToolBar();
        newProjectButton = new javax.swing.JButton();
        openProjectButton = new javax.swing.JButton();
        saveProjectButton = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        undoButton = new javax.swing.JButton();
        redoButton = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        penButton = new javax.swing.JToggleButton();
        pourButton = new javax.swing.JToggleButton();
        eraseButton = new javax.swing.JToggleButton();
        mEventButton = new javax.swing.JButton();
        mainSplitPane = new javax.swing.JSplitPane();
        jSplitPane1 = new javax.swing.JSplitPane();
        tilesetScrollPane = new javax.swing.JScrollPane();
        tileSetTabbedPane = new com.soyomaker.widget.TileSetTabbedPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        mapPane = new javax.swing.JPanel();
        mapToolBar = new javax.swing.JToolBar();
        newMapButton = new javax.swing.JButton();
        removeMapButton = new javax.swing.JButton();
        editMapButton = new javax.swing.JButton();
        mapPreviewButton = new javax.swing.JButton();
        copyMapButton = new javax.swing.JButton();
        pasteMapButton = new javax.swing.JButton();
        importMapButton = new javax.swing.JButton();
        saveMapButton = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        mapTree = new com.soyomaker.widget.SoyoMapTree();
        jSplitPane2 = new javax.swing.JSplitPane();
        mapScrollPane = new javax.swing.JScrollPane();
        jSplitPane3 = new javax.swing.JSplitPane();
        jScrollPane4 = new javax.swing.JScrollPane();
        layerPane = new javax.swing.JPanel();
        layerToolBar = new javax.swing.JToolBar();
        newLayerButton = new javax.swing.JButton();
        upLayerButton = new javax.swing.JButton();
        downLayerButton = new javax.swing.JButton();
        removeLayerButton = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        layerTable = new javax.swing.JTable();
        mainTabbedPane = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        mainMenuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        newProjectMenuItem = new javax.swing.JMenuItem();
        openProjectMenuItem = new javax.swing.JMenuItem();
        saveProjectMenuItem = new javax.swing.JMenuItem();
        closeProjectMenuItem = new javax.swing.JMenuItem();
        openRecentMenu = new javax.swing.JMenu();
        exitMenuItem = new javax.swing.JMenuItem();
        editMenu = new javax.swing.JMenu();
        undoMenuItem = new javax.swing.JMenuItem();
        redoMenuItem = new javax.swing.JMenuItem();
        viewMenu = new javax.swing.JMenu();
        toolBarMenuItem = new javax.swing.JCheckBoxMenuItem();
        stateBarMenuItem = new javax.swing.JCheckBoxMenuItem();
        gridMenuItem = new javax.swing.JCheckBoxMenuItem();
        operationMenu = new javax.swing.JMenu();
        penRadioMenuItem = new javax.swing.JRadioButtonMenuItem();
        fillRadioMenuItem = new javax.swing.JRadioButtonMenuItem();
        eraseRadioMenuItem = new javax.swing.JRadioButtonMenuItem();
        tilesetMenu = new javax.swing.JMenu();
        addTilesetMenuItem = new javax.swing.JMenuItem();
        tilesetManagerMenuItem = new javax.swing.JMenuItem();
        zoomMenu = new javax.swing.JMenu();
        zoomInMenuItem = new javax.swing.JMenuItem();
        zoomOutMenuItem = new javax.swing.JMenuItem();
        zoomReturnMenuItem = new javax.swing.JMenuItem();
        layerMenu = new javax.swing.JMenu();
        newLayerMenuItem = new javax.swing.JMenuItem();
        removeLayerMenuItem = new javax.swing.JMenuItem();
        editLayerMenu = new javax.swing.JMenu();
        upLayerMenuItem = new javax.swing.JMenuItem();
        downLayerMenuItem = new javax.swing.JMenuItem();
        toolMenu = new javax.swing.JMenu();
        dataEditorMenuItem = new javax.swing.JMenuItem();
        spriteEditorMenuItem = new javax.swing.JMenuItem();
        scriptEditorMenuItem = new javax.swing.JMenuItem();
        resourceManagerMenuItem = new javax.swing.JMenuItem();
        setMenuItem = new javax.swing.JMenuItem();
        pluginMenu = new javax.swing.JMenu();
        pluginManagerMenuItem = new javax.swing.JMenuItem();
        simulationMenu = new javax.swing.JMenu();
        helpMenu = new javax.swing.JMenu();
        UserMenuItem = new javax.swing.JMenuItem();
        shortcutMenuItem = new javax.swing.JMenuItem();
        checkUpdateMenuItem = new javax.swing.JMenuItem();
        helpMenuItem = new javax.swing.JMenuItem();
        aboutMenuItem = new javax.swing.JMenuItem();

        trayPopMenu.setLabel("popupMenu1");

        openPopMenuItem.setLabel("打开主面板");
        openPopMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openPopMenuItemActionPerformed(evt);
            }
        });
        trayPopMenu.add(openPopMenuItem);

        exitPopMenuItem.setLabel("退出");
        exitPopMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitPopMenuItemActionPerformed(evt);
            }
        });
        trayPopMenu.add(exitPopMenuItem);

        mapPopupMenu.setName("mapPopupMenu"); // NOI18N

        newMapMenuItem.setText("新建地图");
        newMapMenuItem.setName("newMapMenuItem"); // NOI18N
        newMapMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newMapMenuItemActionPerformed(evt);
            }
        });
        mapPopupMenu.add(newMapMenuItem);

        removeMapMenuItem.setText("删除地图");
        removeMapMenuItem.setName("removeMapMenuItem"); // NOI18N
        removeMapMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeMapMenuItemActionPerformed(evt);
            }
        });
        mapPopupMenu.add(removeMapMenuItem);

        editMapMenuItem.setText("地图设置");
        editMapMenuItem.setName("editMapMenuItem"); // NOI18N
        editMapMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editMapMenuItemActionPerformed(evt);
            }
        });
        mapPopupMenu.add(editMapMenuItem);

        previewMapMenuItem.setText("预览地图");
        previewMapMenuItem.setName("previewMapMenuItem"); // NOI18N
        previewMapMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                previewMapMenuItemActionPerformed(evt);
            }
        });
        mapPopupMenu.add(previewMapMenuItem);

        copyMapMenuItem.setText("复制地图");
        copyMapMenuItem.setName("copyMapMenuItem"); // NOI18N
        copyMapMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                copyMapMenuItemActionPerformed(evt);
            }
        });
        mapPopupMenu.add(copyMapMenuItem);

        pasteMapMenuItem.setText("粘贴地图");
        pasteMapMenuItem.setName("pasteMapMenuItem"); // NOI18N
        pasteMapMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pasteMapMenuItemActionPerformed(evt);
            }
        });
        mapPopupMenu.add(pasteMapMenuItem);

        importMapMenuItem.setText("导入地图");
        importMapMenuItem.setName("importMapMenuItem"); // NOI18N
        importMapMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                importMapMenuItemActionPerformed(evt);
            }
        });
        mapPopupMenu.add(importMapMenuItem);

        saveMapMenuItem.setText("保存地图");
        saveMapMenuItem.setName("saveMapMenuItem"); // NOI18N
        saveMapMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveMapMenuItemActionPerformed(evt);
            }
        });
        mapPopupMenu.add(saveMapMenuItem);

        setName("mainFrame"); // NOI18N
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        stateBarPanel.setName("stateBarPanel"); // NOI18N

        stateLabel.setName("stateLabel"); // NOI18N

        javax.swing.GroupLayout stateBarPanelLayout = new javax.swing.GroupLayout(stateBarPanel);
        stateBarPanel.setLayout(stateBarPanelLayout);
        stateBarPanelLayout.setHorizontalGroup(
            stateBarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(stateBarPanelLayout.createSequentialGroup()
                .addComponent(stateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(745, Short.MAX_VALUE))
        );
        stateBarPanelLayout.setVerticalGroup(
            stateBarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(stateLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 21, Short.MAX_VALUE)
        );

        toolBar.setFloatable(false);
        toolBar.setRollover(true);
        toolBar.setName("工具栏"); // NOI18N
        for (int i = 0; i<SMPluginManager.getInstance().getToolBarPluginNum(); i++) {
            toolBar.add(SMPluginManager.getInstance().getToolBarPlugins().get(i));
        }

        newProjectButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/soyomaker/resources/new.png"))); // NOI18N
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance().getContext().getResourceMap(AppMainFrame.class);
        newProjectButton.setText(resourceMap.getString("newProjectButton.text")); // NOI18N
        newProjectButton.setFocusable(false);
        newProjectButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        newProjectButton.setName("newProjectButton"); // NOI18N
        newProjectButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        newProjectButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newProjectButtonActionPerformed(evt);
            }
        });
        toolBar.add(newProjectButton);

        openProjectButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/soyomaker/resources/open.png"))); // NOI18N
        openProjectButton.setText(resourceMap.getString("openProjectButton.text")); // NOI18N
        openProjectButton.setFocusable(false);
        openProjectButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        openProjectButton.setName("openProjectButton"); // NOI18N
        openProjectButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        openProjectButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openProjectButtonActionPerformed(evt);
            }
        });
        toolBar.add(openProjectButton);

        saveProjectButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/soyomaker/resources/save.png"))); // NOI18N
        saveProjectButton.setText(resourceMap.getString("saveProjectButton.text")); // NOI18N
        saveProjectButton.setFocusable(false);
        saveProjectButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        saveProjectButton.setName("saveProjectButton"); // NOI18N
        saveProjectButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        saveProjectButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveProjectButtonActionPerformed(evt);
            }
        });
        toolBar.add(saveProjectButton);

        jSeparator1.setName("jSeparator1"); // NOI18N
        toolBar.add(jSeparator1);

        undoButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/soyomaker/resources/undo.png"))); // NOI18N
        undoButton.setFocusable(false);
        undoButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        undoButton.setName("undoButton"); // NOI18N
        undoButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        undoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                undoButtonActionPerformed(evt);
            }
        });
        toolBar.add(undoButton);

        redoButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/soyomaker/resources/redo.png"))); // NOI18N
        redoButton.setFocusable(false);
        redoButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        redoButton.setName("redoButton"); // NOI18N
        redoButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        redoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                redoButtonActionPerformed(evt);
            }
        });
        toolBar.add(redoButton);

        jSeparator2.setName("jSeparator2"); // NOI18N
        toolBar.add(jSeparator2);

        buttonGroup.add(penButton);
        penButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/soyomaker/resources/pen.png"))); // NOI18N
        penButton.setSelected(true);
        penButton.setToolTipText("画笔");
        penButton.setFocusable(false);
        penButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        penButton.setName("penButton"); // NOI18N
        penButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        penButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                penButtonActionPerformed(evt);
            }
        });
        toolBar.add(penButton);

        buttonGroup.add(pourButton);
        pourButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/soyomaker/resources/pour.png"))); // NOI18N
        pourButton.setToolTipText("填充");
        pourButton.setFocusable(false);
        pourButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        pourButton.setName("pourButton"); // NOI18N
        pourButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        pourButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pourButtonActionPerformed(evt);
            }
        });
        toolBar.add(pourButton);

        buttonGroup.add(eraseButton);
        eraseButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/soyomaker/resources/eraser.png"))); // NOI18N
        eraseButton.setToolTipText("橡皮");
        eraseButton.setFocusable(false);
        eraseButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        eraseButton.setName("eraseButton"); // NOI18N
        eraseButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        eraseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eraseButtonActionPerformed(evt);
            }
        });
        toolBar.add(eraseButton);

        mEventButton.setText("切换到事件层");
        mEventButton.setFocusable(false);
        mEventButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        mEventButton.setName("mEventButton"); // NOI18N
        mEventButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        mEventButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mEventButtonActionPerformed(evt);
            }
        });
        toolBar.add(mEventButton);

        mainSplitPane.setResizeWeight(0.08);
        mainSplitPane.setName("mainSplitPane"); // NOI18N
        mainSplitPane.setOneTouchExpandable(true);

        jSplitPane1.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        jSplitPane1.setResizeWeight(0.8);
        jSplitPane1.setName("jSplitPane1"); // NOI18N
        jSplitPane1.setOneTouchExpandable(true);
        jSplitPane1.setPreferredSize(new java.awt.Dimension(180, 350));

        tilesetScrollPane.setName("tilesetScrollPane"); // NOI18N

        tileSetTabbedPane.setName("tileSetTabbedPane"); // NOI18N
        tilesetScrollPane.setViewportView(tileSetTabbedPane);

        jSplitPane1.setTopComponent(tilesetScrollPane);

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        mapPane.setName("mapPane"); // NOI18N
        mapPane.setPreferredSize(new java.awt.Dimension(180, 139));

        mapToolBar.setFloatable(false);
        mapToolBar.setRollover(true);
        mapToolBar.setName("地图"); // NOI18N

        newMapButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/soyomaker/resources/little_new.png"))); // NOI18N
        newMapButton.setToolTipText("新建地图");
        newMapButton.setFocusable(false);
        newMapButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        newMapButton.setName("newMapButton"); // NOI18N
        newMapButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        newMapButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newMapButtonActionPerformed(evt);
            }
        });
        mapToolBar.add(newMapButton);

        removeMapButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/soyomaker/resources/little_delete.png"))); // NOI18N
        removeMapButton.setToolTipText("删除地图");
        removeMapButton.setFocusable(false);
        removeMapButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        removeMapButton.setName("removeMapButton"); // NOI18N
        removeMapButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        removeMapButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeMapButtonActionPerformed(evt);
            }
        });
        mapToolBar.add(removeMapButton);

        editMapButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/soyomaker/resources/little_edit.png"))); // NOI18N
        editMapButton.setToolTipText("地图设置");
        editMapButton.setFocusable(false);
        editMapButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        editMapButton.setName("editMapButton"); // NOI18N
        editMapButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        editMapButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editMapButtonActionPerformed(evt);
            }
        });
        mapToolBar.add(editMapButton);

        mapPreviewButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/soyomaker/resources/preview.png"))); // NOI18N
        mapPreviewButton.setToolTipText("预览地图");
        mapPreviewButton.setFocusable(false);
        mapPreviewButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        mapPreviewButton.setName("mapPreviewButton"); // NOI18N
        mapPreviewButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        mapPreviewButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mapPreviewButtonActionPerformed(evt);
            }
        });
        mapToolBar.add(mapPreviewButton);

        copyMapButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/soyomaker/resources/copy.png"))); // NOI18N
        copyMapButton.setToolTipText("复制地图");
        copyMapButton.setFocusable(false);
        copyMapButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        copyMapButton.setName("copyMapButton"); // NOI18N
        copyMapButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        copyMapButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                copyMapButtonActionPerformed(evt);
            }
        });
        mapToolBar.add(copyMapButton);

        pasteMapButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/soyomaker/resources/menu_paste.png"))); // NOI18N
        pasteMapButton.setToolTipText("粘贴地图");
        pasteMapButton.setFocusable(false);
        pasteMapButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        pasteMapButton.setName("pasteMapButton"); // NOI18N
        pasteMapButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        pasteMapButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pasteMapButtonActionPerformed(evt);
            }
        });
        mapToolBar.add(pasteMapButton);

        importMapButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/soyomaker/resources/menu_open.PNG"))); // NOI18N
        importMapButton.setToolTipText("导入地图");
        importMapButton.setFocusable(false);
        importMapButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        importMapButton.setName("importMapButton"); // NOI18N
        importMapButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        importMapButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                importMapButtonActionPerformed(evt);
            }
        });
        mapToolBar.add(importMapButton);

        saveMapButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/soyomaker/resources/menu_save.png"))); // NOI18N
        saveMapButton.setToolTipText("保存地图");
        saveMapButton.setFocusable(false);
        saveMapButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        saveMapButton.setName("saveMapButton"); // NOI18N
        saveMapButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        saveMapButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveMapButtonActionPerformed(evt);
            }
        });
        mapToolBar.add(saveMapButton);

        jScrollPane5.setName("jScrollPane5"); // NOI18N

        mapTree.setComponentPopupMenu(mapPopupMenu);
        mapTree.setName("mapTree"); // NOI18N
        mapTree.setSelectNode(null);
        jScrollPane5.setViewportView(mapTree);

        javax.swing.GroupLayout mapPaneLayout = new javax.swing.GroupLayout(mapPane);
        mapPane.setLayout(mapPaneLayout);
        mapPaneLayout.setHorizontalGroup(
            mapPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mapToolBar, javax.swing.GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE)
            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE)
        );
        mapPaneLayout.setVerticalGroup(
            mapPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mapPaneLayout.createSequentialGroup()
                .addComponent(mapToolBar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(mapPane);

        jSplitPane1.setRightComponent(jScrollPane1);

        mainSplitPane.setLeftComponent(jSplitPane1);

        jSplitPane2.setResizeWeight(0.95);
        jSplitPane2.setName("jSplitPane2"); // NOI18N
        jSplitPane2.setOneTouchExpandable(true);

        mapScrollPane.setName("mapScrollPane"); // NOI18N
        jSplitPane2.setLeftComponent(mapScrollPane);

        jSplitPane3.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        jSplitPane3.setResizeWeight(0.55);
        jSplitPane3.setName("jSplitPane3"); // NOI18N
        jSplitPane3.setOneTouchExpandable(true);
        jSplitPane3.setPreferredSize(new java.awt.Dimension(200, 381));

        jScrollPane4.setName("jScrollPane4"); // NOI18N

        layerPane.setName("layerPane"); // NOI18N
        layerPane.setPreferredSize(new java.awt.Dimension(200, 170));

        layerToolBar.setFloatable(false);
        layerToolBar.setRollover(true);
        layerToolBar.setName("图层"); // NOI18N

        newLayerButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/soyomaker/resources/little_new.png"))); // NOI18N
        newLayerButton.setToolTipText("新建图层");
        newLayerButton.setFocusable(false);
        newLayerButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        newLayerButton.setName("newLayerButton"); // NOI18N
        newLayerButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        newLayerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newLayerButtonActionPerformed(evt);
            }
        });
        layerToolBar.add(newLayerButton);

        upLayerButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/soyomaker/resources/little_up.png"))); // NOI18N
        upLayerButton.setToolTipText("图层上移");
        upLayerButton.setFocusable(false);
        upLayerButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        upLayerButton.setName("upLayerButton"); // NOI18N
        upLayerButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        upLayerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                upLayerButtonActionPerformed(evt);
            }
        });
        layerToolBar.add(upLayerButton);

        downLayerButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/soyomaker/resources/little_down.png"))); // NOI18N
        downLayerButton.setToolTipText("图层下移");
        downLayerButton.setFocusable(false);
        downLayerButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        downLayerButton.setName("downLayerButton"); // NOI18N
        downLayerButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        downLayerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                downLayerButtonActionPerformed(evt);
            }
        });
        layerToolBar.add(downLayerButton);

        removeLayerButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/soyomaker/resources/little_delete.png"))); // NOI18N
        removeLayerButton.setToolTipText("删除图层");
        removeLayerButton.setFocusCycleRoot(true);
        removeLayerButton.setFocusable(false);
        removeLayerButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        removeLayerButton.setName("removeLayerButton"); // NOI18N
        removeLayerButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        removeLayerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeLayerButtonActionPerformed(evt);
            }
        });
        layerToolBar.add(removeLayerButton);

        jScrollPane6.setName("jScrollPane6"); // NOI18N

        layerTable.setModel(ltm);
        layerTable.setName("layerTable"); // NOI18N
        layerTable.setRowHeight(20);
        layerTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        layerTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                layerTableMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(layerTable);

        javax.swing.GroupLayout layerPaneLayout = new javax.swing.GroupLayout(layerPane);
        layerPane.setLayout(layerPaneLayout);
        layerPaneLayout.setHorizontalGroup(
            layerPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(layerToolBar, javax.swing.GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
            .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
        );
        layerPaneLayout.setVerticalGroup(
            layerPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layerPaneLayout.createSequentialGroup()
                .addComponent(layerToolBar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 302, Short.MAX_VALUE))
        );

        jScrollPane4.setViewportView(layerPane);

        jSplitPane3.setRightComponent(jScrollPane4);

        mainTabbedPane.setName("mainTabbedPane"); // NOI18N
        jSplitPane3.setLeftComponent(mainTabbedPane);

        jSplitPane2.setRightComponent(jSplitPane3);

        mainSplitPane.setRightComponent(jSplitPane2);

        jPanel1.setName("jPanel1"); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        mainMenuBar.setName("mainMenuBar"); // NOI18N
        for (int i = 0; i<SMPluginManager.getInstance().getMenuPluginNum(); i++) {
            mainMenuBar.add(SMPluginManager.getInstance().getMenuPlugins().get(i));
        }

        fileMenu.setMnemonic('F');
        fileMenu.setText(resourceMap.getString("fileMenu.text")); // NOI18N
        fileMenu.setName("fileMenu"); // NOI18N

        newProjectMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/soyomaker/resources/menu_new.PNG"))); // NOI18N
        newProjectMenuItem.setText(resourceMap.getString("newProjectMenuItem.text")); // NOI18N
        newProjectMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newProjectMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(newProjectMenuItem);

        openProjectMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/soyomaker/resources/menu_open.PNG"))); // NOI18N
        openProjectMenuItem.setText(resourceMap.getString("openProjectMenuItem.text")); // NOI18N
        openProjectMenuItem.setName("openProjectMenuItem"); // NOI18N
        openProjectMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openProjectMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(openProjectMenuItem);

        saveProjectMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/soyomaker/resources/menu_save.png"))); // NOI18N
        saveProjectMenuItem.setText(resourceMap.getString("saveProjectMenuItem.text")); // NOI18N
        saveProjectMenuItem.setName("saveProjectMenuItem"); // NOI18N
        saveProjectMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveProjectMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(saveProjectMenuItem);

        closeProjectMenuItem.setText(resourceMap.getString("closeProjectMenuItem.text")); // NOI18N
        closeProjectMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeProjectMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(closeProjectMenuItem);

        openRecentMenu.setText(resourceMap.getString("openRecentMenu.text")); // NOI18N
        openRecentMenu.setName("openRecentMenu"); // NOI18N
        fileMenu.add(openRecentMenu);

        exitMenuItem.setText(resourceMap.getString("exitMenuItem.text")); // NOI18N
        exitMenuItem.setName("exitMenuItem"); // NOI18N
        exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(exitMenuItem);

        mainMenuBar.add(fileMenu);

        editMenu.setMnemonic('E');
        editMenu.setText(resourceMap.getString("editMenu.text")); // NOI18N
        editMenu.setName("editMenu"); // NOI18N

        undoMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Z, java.awt.event.InputEvent.CTRL_MASK));
        undoMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/soyomaker/resources/menu_undo.png"))); // NOI18N
        undoMenuItem.setText(resourceMap.getString("undoMenuItem.text")); // NOI18N
        undoMenuItem.setName("undoMenuItem"); // NOI18N
        undoMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                undoMenuItemActionPerformed(evt);
            }
        });
        editMenu.add(undoMenuItem);

        redoMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Y, java.awt.event.InputEvent.CTRL_MASK));
        redoMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/soyomaker/resources/menu_redo.png"))); // NOI18N
        redoMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Y, java.awt.event.InputEvent.CTRL_MASK));
        redoMenuItem.setText(resourceMap.getString("redoMenuItem.text")); // NOI18N
        redoMenuItem.setName("redoMenuItem"); // NOI18N
        redoMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                redoMenuItemActionPerformed(evt);
            }
        });
        editMenu.add(redoMenuItem);

        mainMenuBar.add(editMenu);

        viewMenu.setMnemonic('V');
        viewMenu.setText(resourceMap.getString("viewMenu.text")); // NOI18N
        viewMenu.setName("viewMenu"); // NOI18N

        toolBarMenuItem.setSelected(true);
        toolBarMenuItem.setSelected(true);
        toolBarMenuItem.setText(resourceMap.getString("toolBarMenuItem.text")); // NOI18N
        toolBarMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                toolBarMenuItemActionPerformed(evt);
            }
        });
        viewMenu.add(toolBarMenuItem);

        stateBarMenuItem.setSelected(true);
        stateBarMenuItem.setText(resourceMap.getString("stateBarMenuItem.text")); // NOI18N
        stateBarMenuItem.setName("stateBarMenuItem"); // NOI18N
        stateBarMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stateBarMenuItemActionPerformed(evt);
            }
        });
        viewMenu.add(stateBarMenuItem);

        gridMenuItem.setText(resourceMap.getString("gridMenuItem.text")); // NOI18N
        gridMenuItem.setName("gridMenuItem"); // NOI18N
        gridMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gridMenuItemActionPerformed(evt);
            }
        });
        viewMenu.add(gridMenuItem);

        mainMenuBar.add(viewMenu);

        operationMenu.setMnemonic('O');
        operationMenu.setText(resourceMap.getString("operationMenu.text")); // NOI18N
        operationMenu.setName("operationMenu"); // NOI18N

        penRadioMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F2, 0));
        buttonGroup1.add(penRadioMenuItem);
        penRadioMenuItem.setSelected(true);
        penRadioMenuItem.setText("铅笔");
        penRadioMenuItem.setName("penRadioMenuItem"); // NOI18N
        penRadioMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                penRadioMenuItemActionPerformed(evt);
            }
        });
        operationMenu.add(penRadioMenuItem);

        fillRadioMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F3, 0));
        buttonGroup1.add(fillRadioMenuItem);
        fillRadioMenuItem.setText("填充");
        fillRadioMenuItem.setName("fillRadioMenuItem"); // NOI18N
        fillRadioMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fillRadioMenuItemActionPerformed(evt);
            }
        });
        operationMenu.add(fillRadioMenuItem);

        eraseRadioMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, 0));
        buttonGroup1.add(eraseRadioMenuItem);
        eraseRadioMenuItem.setText("橡皮");
        eraseRadioMenuItem.setName("eraseRadioMenuItem"); // NOI18N
        eraseRadioMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eraseRadioMenuItemActionPerformed(evt);
            }
        });
        operationMenu.add(eraseRadioMenuItem);

        mainMenuBar.add(operationMenu);

        tilesetMenu.setMnemonic('I');
        tilesetMenu.setText(resourceMap.getString("tilesetMenu.text")); // NOI18N

        addTilesetMenuItem.setText(resourceMap.getString("addTilesetMenuItem.text")); // NOI18N
        addTilesetMenuItem.setName("addTilesetMenuItem"); // NOI18N
        addTilesetMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addTilesetMenuItemActionPerformed(evt);
            }
        });
        tilesetMenu.add(addTilesetMenuItem);

        tilesetManagerMenuItem.setText("图集管理");
        tilesetManagerMenuItem.setName("tilesetManagerMenuItem"); // NOI18N
        tilesetManagerMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tilesetManagerMenuItemActionPerformed(evt);
            }
        });
        tilesetMenu.add(tilesetManagerMenuItem);

        mainMenuBar.add(tilesetMenu);

        zoomMenu.setMnemonic('Z');
        zoomMenu.setText(resourceMap.getString("zoomMenu.text")); // NOI18N

        zoomInMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/soyomaker/resources/menu_zoomin.png"))); // NOI18N
        zoomInMenuItem.setText(resourceMap.getString("jMenuItem1.text")); // NOI18N
        zoomInMenuItem.setName("zoomInMenuItem"); // NOI18N
        zoomInMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                zoomInMenuItemActionPerformed(evt);
            }
        });
        zoomMenu.add(zoomInMenuItem);

        zoomOutMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/soyomaker/resources/menu_zoomout.png"))); // NOI18N
        zoomOutMenuItem.setText(resourceMap.getString("zoomOutMenuItem.text")); // NOI18N
        zoomOutMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                zoomOutMenuItemActionPerformed(evt);
            }
        });
        zoomMenu.add(zoomOutMenuItem);

        zoomReturnMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.CTRL_MASK));
        zoomReturnMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/soyomaker/resources/menu_return.png"))); // NOI18N
        zoomReturnMenuItem.setText(resourceMap.getString("zoomReturnMenuItem.text")); // NOI18N
        zoomReturnMenuItem.setName("zoomReturnMenuItem"); // NOI18N
        zoomReturnMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                zoomReturnMenuItemActionPerformed(evt);
            }
        });
        zoomMenu.add(zoomReturnMenuItem);

        mainMenuBar.add(zoomMenu);

        layerMenu.setMnemonic('L');
        layerMenu.setText(resourceMap.getString("layerMenu.text")); // NOI18N
        layerMenu.setName("layerMenu"); // NOI18N

        newLayerMenuItem.setText(resourceMap.getString("newLayerMenuItem.text")); // NOI18N
        newLayerMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newLayerMenuItemActionPerformed(evt);
            }
        });
        layerMenu.add(newLayerMenuItem);

        removeLayerMenuItem.setText(resourceMap.getString("removeLayerMenuItem.text")); // NOI18N
        removeLayerMenuItem.setName("removeLayerMenuItem"); // NOI18N
        removeLayerMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeLayerMenuItemActionPerformed(evt);
            }
        });
        layerMenu.add(removeLayerMenuItem);

        editLayerMenu.setText(resourceMap.getString("editLayerMenu.text")); // NOI18N
        editLayerMenu.setName("editLayerMenu"); // NOI18N

        upLayerMenuItem.setText(resourceMap.getString("upLayerMenuItem.text")); // NOI18N
        upLayerMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                upLayerMenuItemActionPerformed(evt);
            }
        });
        editLayerMenu.add(upLayerMenuItem);

        downLayerMenuItem.setText(resourceMap.getString("downLayerMenuItem.text")); // NOI18N
        downLayerMenuItem.setName("downLayerMenuItem"); // NOI18N
        downLayerMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                downLayerMenuItemActionPerformed(evt);
            }
        });
        editLayerMenu.add(downLayerMenuItem);

        layerMenu.add(editLayerMenu);

        mainMenuBar.add(layerMenu);

        toolMenu.setMnemonic('T');
        toolMenu.setText(resourceMap.getString("toolsMenu.text")); // NOI18N
        toolMenu.setName("toolMenu"); // NOI18N
        for (int i = 0; i<SMPluginManager.getInstance().getToolPluginNum(); i++) {
            toolMenu.add(SMPluginManager.getInstance().getToolPlugins().get(i));
        }

        dataEditorMenuItem.setText(resourceMap.getString("dataEditorMenuItem.text")); // NOI18N
        dataEditorMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dataEditorMenuItemActionPerformed1(evt);
            }
        });
        toolMenu.add(dataEditorMenuItem);

        spriteEditorMenuItem.setText(resourceMap.getString("spriteEditorMenuItem.text")); // NOI18N
        spriteEditorMenuItem.setName("spriteEditorMenuItem"); // NOI18N
        spriteEditorMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                spriteEditorMenuItemActionPerformed(evt);
            }
        });
        toolMenu.add(spriteEditorMenuItem);

        scriptEditorMenuItem.setText(resourceMap.getString("scriptEditorMenuItem.text")); // NOI18N
        scriptEditorMenuItem.setName("scriptEditorMenuItem"); // NOI18N
        scriptEditorMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                scriptEditorMenuItemActionPerformed(evt);
            }
        });
        toolMenu.add(scriptEditorMenuItem);

        resourceManagerMenuItem.setText("素材管理器");
        resourceManagerMenuItem.setName("resourceManagerMenuItem"); // NOI18N
        resourceManagerMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resourceManagerMenuItemActionPerformed(evt);
            }
        });
        toolMenu.add(resourceManagerMenuItem);

        setMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/soyomaker/resources/menu_option.png"))); // NOI18N
        setMenuItem.setText(resourceMap.getString("setMenuItem.text")); // NOI18N
        setMenuItem.setName("setMenuItem"); // NOI18N
        setMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setMenuItemActionPerformed(evt);
            }
        });
        toolMenu.add(setMenuItem);

        mainMenuBar.add(toolMenu);

        pluginMenu.setMnemonic('P');
        pluginMenu.setText(resourceMap.getString("pluginMenu.text")); // NOI18N
        pluginMenu.setName("pluginMenu"); // NOI18N
        for (int i = 0; i<SMPluginManager.getInstance().getPluginPluginNum(); i++) {
            pluginMenu.add(SMPluginManager.getInstance().getPluginPlugins().get(i));
        }

        pluginManagerMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/soyomaker/resources/menu_plugin.png"))); // NOI18N
        pluginManagerMenuItem.setText(resourceMap.getString("pluginManagerMenuItem.text")); // NOI18N
        pluginManagerMenuItem.setName("pluginManagerMenuItem"); // NOI18N
        pluginManagerMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pluginManagerMenuItemActionPerformed(evt);
            }
        });
        pluginMenu.add(pluginManagerMenuItem);

        mainMenuBar.add(pluginMenu);

        simulationMenu.setMnemonic('S');
        simulationMenu.setText(resourceMap.getString("simulationMenu.text")); // NOI18N
        simulationMenu.setName("simulationMenu"); // NOI18N
        for (int i = 0; i<SMPluginManager.getInstance().getSimPluginNum(); i++) {
            simulationMenu.add(SMPluginManager.getInstance().getSimPlugins().get(i));
        }
        mainMenuBar.add(simulationMenu);

        helpMenu.setMnemonic('H');
        helpMenu.setText(resourceMap.getString("helpMenu.text")); // NOI18N
        helpMenu.setName("helpMenu"); // NOI18N
        for (int i = 0; i<SMPluginManager.getInstance().getHelpPluginNum(); i++) {
            helpMenu.add(SMPluginManager.getInstance().getHelpPlugins().get(i));
        }

        UserMenuItem.setText(resourceMap.getString("userMenuItem.text")); // NOI18N
        UserMenuItem.setName("UserMenuItem"); // NOI18N
        UserMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UserMenuItemActionPerformed(evt);
            }
        });
        helpMenu.add(UserMenuItem);

        shortcutMenuItem.setText("快捷键向导");
        shortcutMenuItem.setName("shortcutMenuItem"); // NOI18N
        shortcutMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                shortcutMenuItemActionPerformed(evt);
            }
        });
        helpMenu.add(shortcutMenuItem);

        checkUpdateMenuItem.setText("检查更新");
        checkUpdateMenuItem.setName("checkUpdateMenuItem"); // NOI18N
        checkUpdateMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkUpdateMenuItemActionPerformed(evt);
            }
        });
        helpMenu.add(checkUpdateMenuItem);

        helpMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
        helpMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/soyomaker/resources/menu_help.png"))); // NOI18N
        helpMenuItem.setText(resourceMap.getString("helpMenuItem.text")); // NOI18N
        helpMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                helpMenuItemActionPerformed(evt);
            }
        });
        helpMenu.add(helpMenuItem);

        aboutMenuItem.setText(resourceMap.getString("aboutMenuItem.text")); // NOI18N
        aboutMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aboutMenuItemActionPerformed(evt);
            }
        });
        helpMenu.add(aboutMenuItem);

        mainMenuBar.add(helpMenu);

        setJMenuBar(mainMenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(toolBar, javax.swing.GroupLayout.DEFAULT_SIZE, 972, Short.MAX_VALUE)
            .addComponent(stateBarPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(mainSplitPane, javax.swing.GroupLayout.DEFAULT_SIZE, 972, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(toolBar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mainSplitPane, javax.swing.GroupLayout.DEFAULT_SIZE, 528, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(stateBarPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuItemActionPerformed
        // TODO add your handling code here:
        exit();
    }//GEN-LAST:event_exitMenuItemActionPerformed
    private ShortcutDialog hd;
    private AboutDialog ad;
    private void aboutMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aboutMenuItemActionPerformed
        // TODO add your handling code here:
        if (ad == null) {
            ad = new AboutDialog(this, true);
        }
        ad.setVisible(true);
    }//GEN-LAST:event_aboutMenuItemActionPerformed

    private void UserMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UserMenuItemActionPerformed
        // TODO add your handling code here:
        try {
            Desktop.getDesktop().browse(new URL("http://www.soyomaker.com").toURI());
        } catch (Exception e) {
            logPrinter.e("打开软件首页异常！");
        }
    }//GEN-LAST:event_UserMenuItemActionPerformed

    private void stateBarMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stateBarMenuItemActionPerformed
        // TODO add your handling code here:
        if (stateBarMenuItem.isSelected()) {
            stateBarPanel.setVisible(true);
        } else {
            stateBarPanel.setVisible(false);
        }
    }//GEN-LAST:event_stateBarMenuItemActionPerformed

    private void toolBarMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_toolBarMenuItemActionPerformed
        // TODO add your handling code here:
        if (toolBarMenuItem.isSelected()) {
            toolBar.setVisible(true);
        } else {
            toolBar.setVisible(false);
        }
    }//GEN-LAST:event_toolBarMenuItemActionPerformed
    private PluginManagerDialog pmd;
    private void pluginManagerMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pluginManagerMenuItemActionPerformed
        // TODO add your handling code here:
        if (pmd == null) {
            pmd = new PluginManagerDialog(this, true);
        }
        pmd.setVisible(true);
    }//GEN-LAST:event_pluginManagerMenuItemActionPerformed

    private void helpMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_helpMenuItemActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_helpMenuItemActionPerformed

    private void newProjectMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newProjectMenuItemActionPerformed
        // TODO add your handling code here:
        newProject();
    }//GEN-LAST:event_newProjectMenuItemActionPerformed

    private void openProjectMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openProjectMenuItemActionPerformed
        // TODO add your handling code here:
        openProject();
    }//GEN-LAST:event_openProjectMenuItemActionPerformed
    private NewProjectDialog npd;
    private Executor executor = AppData.getInstance().getExecutor();
    private static final Cursor waitCursor = new Cursor(Cursor.WAIT_CURSOR);
    private static final Cursor normalCursor = new Cursor(Cursor.DEFAULT_CURSOR);
    private long newUseTime = 0;

    private void newProject() {
        if (npd == null) {
            npd = new NewProjectDialog(this, true);
        }
        npd.setVisible(true);

        final Project project = npd.getProject();

        if (project != null) {
            project.addProjectChangeListener(this);
            AppMainFrame.this.setCursor(waitCursor);
            AppMainFrame.this.gGrassPane.start();
            AppMainFrame.this.gGrassPane.setText("正在新建项目...");

            newUseTime = System.currentTimeMillis();
            if (AppData.getInstance().getCurProject() != null) {
                data.getMainFrame().closeProject();
            }
            AppData.getInstance().setCurProject(project);
            updateRecent(project.getPath());
            setTitle(SoftInformation.chineseName + " - " + project.getName());
            stateLabel.setText("");
            try {
                FileUtil.copyDirectiory("res/softdata", project.getPath() + "/softdata");
            } catch (IOException ex) {
                logPrinter.e("复制默认资源文件出错！" + ex.toString());
            }
            Runnable task3 = new Runnable() {

                @Override
                public void run() {
                    try {
                        ProjectManager.create();
                    } catch (IOException ex) {
                        logPrinter.e("项目文件生成异常！" + ex.toString());
//                            JOptionPane.showMessageDialog(AppMainFrame.this, "项目文件生成异常！" + ex.toString(), "警告", JOptionPane.WARNING_MESSAGE);//弹出提示
                    }
                    try {
                        AppMainFrame.this.gGrassPane.setText("开始复制默认资源文件...");
                        logPrinter.v("开始复制默认资源文件...");
                        FileUtil.copyDirectiory("res/image", project.getPath() + "/image");
                        FileUtil.copyDirectiory("res/audio", project.getPath() + "/audio");
                        FileUtil.copyDirectiory("res/data", project.getPath() + "/data");
                        FileUtil.copyDirectiory("res/smscript", project.getPath() + "/smscript");
                        Notifier.getInstance().notifyEvent(EventIdConst.SOFT_NEW_COPY_RES_SUCCESSFUL);
                        logPrinter.v("复制默认资源文件成功！");
                    } catch (IOException ex) {
                        logPrinter.e("复制默认资源文件出错！" + ex.toString());
                        Notifier.getInstance().notifyEvent(EventIdConst.SOFT_NEW_COPY_RES_FAILURE);
                    }
                }
            };
            executor.execute(task3);
            Runnable task2 = new Runnable() {

                @Override
                public void run() {
                    sped = new SpriteEditorDialog(AppMainFrame.this, true);//读取动画
                    AppMainFrame.this.gGrassPane.setText("导入默认动画数据开始...");
                    logPrinter.v("导入默认动画数据开始...");
                    File aniFile = new File(project.getPath() + File.separator + "softdata" + File.separator + "animation.gat");
                    if (aniFile.exists()) {
                        IAnimationReader aniReader = new DefaultSoftAnimationBinaryReader();
                        try {
                            aniReader.readAnimation(project.getPath() + File.separator + "softdata" + File.separator + "animation.gat");
                            logPrinter.v("导入默认动画数据成功！");
                            Notifier.getInstance().notifyEvent(EventIdConst.SOFT_NEW_ANIMATION_LOAD_SUCCESSFUL);
                        } catch (Exception ex) {
                            logPrinter.e("导入默认动画数据失败！" + ex.toString());
                            Notifier.getInstance().notifyEvent(EventIdConst.SOFT_NEW_ANIMATION_LOAD_FAILURE);
                        }
                    }
                }
            };
            executor.execute(task2);
            Runnable task1 = new Runnable() {

                @Override
                public void run() {
                    ded = new DataEditorDialog(data.getMainFrame(), true);
                    try {
                        AppMainFrame.this.gGrassPane.setText("导入默认数据编辑器数据开始...");
                        logPrinter.v("导入默认数据编辑器数据开始...");
                        AppData.getInstance().getCurProject().getDataManager().init(AppData.getInstance().getCurProject().getPath());
                        logPrinter.v("导入默认数据编辑器数据成功！");
                        Notifier.getInstance().notifyEvent(EventIdConst.SOFT_NEW_DATA_LOAD_SUCCESSFUL);
                    } catch (FileNotFoundException ex) {
                        logPrinter.e("找不到文件异常：" + ex.toString());
                    } catch (IOException ex) {
                        logPrinter.e("导入默认数据编辑器数据失败！" + ex.toString());
                        Notifier.getInstance().notifyEvent(EventIdConst.SOFT_NEW_DATA_LOAD_FAILURE);
                    }
                }
            };
            executor.execute(task1);
        }
    }
    private void newProjectButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newProjectButtonActionPerformed
        // TODO add your handling code here:
        newProject();
    }//GEN-LAST:event_newProjectButtonActionPerformed

    private void openProjectButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openProjectButtonActionPerformed
        // TODO add your handling code here:
        openProject();
    }//GEN-LAST:event_openProjectButtonActionPerformed

    private void saveProjectMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveProjectMenuItemActionPerformed
        saveProject();
    }//GEN-LAST:event_saveProjectMenuItemActionPerformed
    private long loadUseTime = 0;

    /**
     *
     * @param path
     */
    public void openFile(final String path) {
        AppMainFrame.this.setCursor(waitCursor);
        AppMainFrame.this.gGrassPane.start();
        AppMainFrame.this.gGrassPane.setText("正在打开工程...");
        loadUseTime = System.currentTimeMillis();
        closeProject();
        final Project project = new Project();
        AppData.getInstance().setCurProject(project);
        project.addProjectChangeListener(AppMainFrame.this);
        project.setPath(path);
        updateRecent(project.getPath());
        try {
            ProjectManager.get();
        } catch (DocumentException ex) {
            ex.printStackTrace();
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        } catch (UnsupportedEncodingException ex) {
            ex.printStackTrace();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        this.setTitle(SoftInformation.chineseName + " - " + project.getName());
        Runnable task0 = new Runnable() {

            @Override
            public void run() {
                sped = new SpriteEditorDialog(AppMainFrame.this, true);//读取动画
                AppMainFrame.this.gGrassPane.setText("导入动画数据开始...");
                logPrinter.v("导入动画数据开始...");
                File aniFile = new File(project.getPath() + File.separator + "softdata" + File.separator + "animation.gat");
                if (aniFile.exists()) {
                    IAnimationReader aniReader = new DefaultSoftAnimationBinaryReader();
                    try {
                        aniReader.readAnimation(project.getPath() + File.separator + "softdata" + File.separator + "animation.gat");
                        logPrinter.v("导入动画数据成功！");
                        Notifier.getInstance().notifyEvent(EventIdConst.SOFT_ANIMATION_LOAD_SUCCESSFUL);
                    } catch (Exception ex) {
                        logPrinter.e("导入动画数据失败！" + ex.toString());
                        Notifier.getInstance().notifyEvent(EventIdConst.SOFT_ANIMATION_LOAD_FAILURE);
                    }
                }
            }
        };
        executor.execute(task0);
        Runnable task1 = new Runnable() {

            @Override
            public void run() {
                ded = new DataEditorDialog(AppMainFrame.this, true);//读取数据
                AppMainFrame.this.gGrassPane.setText("导入数据编辑器数据开始...");
                logPrinter.v("导入数据编辑器数据开始...");
                try {
                    AppData.getInstance().getCurProject().getDataManager().init(AppData.getInstance().getCurProject().getPath());
                    logPrinter.v("导入数据编辑器数据成功！");
                    Notifier.getInstance().notifyEvent(EventIdConst.SOFT_DATA_LOAD_SUCCESSFUL);
                } catch (FileNotFoundException ex) {
                    logPrinter.e("找不到文件异常：" + ex.toString());
                } catch (IOException ex) {
                    logPrinter.e("导入数据编辑器数据失败！" + ex.toString());
                    Notifier.getInstance().notifyEvent(EventIdConst.SOFT_DATA_LOAD_FAILURE);
                }
            }
        };
        executor.execute(task1);
        Runnable task2 = new Runnable() {

            @Override
            public void run() {
                try {
                    AppMainFrame.this.gGrassPane.setText("导入地图数据开始...");
                    logPrinter.v("导入地图数据开始...");
                    File f = new File(project.getPath() + File.separator + "softdata" + File.separator + "map.xml");
                    if (f.exists()) {
                        SAXReader reader = new SAXReader();
                        InputStream ifile = new FileInputStream(f);
                        InputStreamReader ir = new InputStreamReader(ifile, "UTF-8");
                        Document doc = reader.read(ir);
                        Element root = doc.getRootElement();
                        loadAllMap(root, (DefaultMutableTreeNode) mapTree.getModel().getRoot());
                    }
                    logPrinter.v("导入地图数据成功！");
                    Notifier.getInstance().notifyEvent(EventIdConst.SOFT_MAP_LOAD_SUCCESSFUL);
                } catch (Exception ex) {
                    logPrinter.e("导入地图数据失败！" + ex.toString());
                    Notifier.getInstance().notifyEvent(EventIdConst.SOFT_MAP_LOAD_FAILURE);
                }
            }
        };
        executor.execute(task2);
    }

    private void openProject() {
        JFileChooser jf = new JFileChooser();
        jf.setFileFilter(new Project.ProjectFilter());
        int ret = jf.showOpenDialog(this);
        if (ret == JFileChooser.APPROVE_OPTION) {
            final File file = jf.getSelectedFile();
            if (file != null) {
                openFile(file.getParent());
            }
        }
    }

    private void loadAllMap(final Element root, final DefaultMutableTreeNode node) throws Exception {
        List childList = root.elements();
        for (int i = 0; i < childList.size(); i++) {
            //子节点的操作
            Element it = (Element) childList.get(i);
            List attrList = it.attributes();
            DefaultMutableTreeNode newNode = null;
            for (int j = 0; j < attrList.size(); j++) {
                //属性的取得
                Attribute item = (Attribute) attrList.get(j);
                if (item.getName().equals("ID")) {
                    IMapReader mapReader = new DefaultSoftMapBinaryReader();
                    final Map map = mapReader.readMap(AppData.getInstance().getCurProject().getPath() + File.separator
                            + "softdata" + File.separator + "map" + File.separator + "map" + item.getValue() + ".gat");
                    AppMainFrame.this.gGrassPane.setText("正在打开地图：" + map);
                    map.addMapChangeListener(this);
                    MapRenderFactory.createMapRender(map).addRenderListener(this);
//                  PreviewRenderFactory.createPreviewRender(map);
                    AppData.getInstance().getCurProject().addMap(map, map.getIndex());

                    newNode = new DefaultMutableTreeNode(map);
                    final DefaultMutableTreeNode nodeTemp = newNode;
                    node.add(newNode);
                    //必须用SwingUtilities.invokeAndWait使刷新在主线程完成，不然会抛异常
                    SwingUtilities.invokeAndWait(new Runnable() {

                        public void run() {
                            ((DefaultTreeModel) mapTree.getModel()).reload(node);
                            mapTree.expandPath(mapTree.getSelectionPath());
                            mapTree.setSelectionPath(new TreePath(((DefaultTreeModel) mapTree.getModel()).getPathToRoot(nodeTemp)));
                            //设置维持当前的选择路径
                            mapTree.setExpandsSelectedPaths(true);
                        }
                    });
                }
            }
            if (it.elements().size() > 0) {
                loadAllMap(it, newNode);
            }
        }
    }
    private void saveProjectButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveProjectButtonActionPerformed
//        saveGameData();
        saveProject();
    }//GEN-LAST:event_saveProjectButtonActionPerformed
    private void clearErrorMapFile(File file, boolean soft) {
        File[] files = file.listFiles();
        for (int i = 0, n = files.length; i < n; i++) {
            if (files[i].getName().endsWith(".gat")
                    && files[i].getName().startsWith("map")) {
                String s = files[i].getName().substring(3, 3 + files[i].getName().length() - "map.gat".length());
                int mapIndex = 0;
                try {
                    mapIndex = Integer.parseInt(s);
                } catch (Exception e) {
                    continue;
                    //当转换出异常时，必然会是map文件夹中有类似 map2a.gat等的错误数据，因为有可能是用户自己的备份，所以不做处理，不进行删除
                }
                if (soft) {
                    mapIndex -= Configuration.Prefix.MAP_MASK;
                }
                if (AppData.getInstance().getCurProject().getMap(mapIndex) == null) {
                    files[i].delete();
//                    System.out.println("删除地图文件成功！");
                }
            }
        }
    }

    private void clearErrorNpcFile(File file, boolean soft) {
        File[] files = file.listFiles();
        for (int i = 0, n = files.length; i < n; i++) {
            if (files[i].getName().endsWith(".gat")
                    && files[i].getName().startsWith("npc")) {
                String s = files[i].getName().substring(3, 3 + files[i].getName().length() - "npc.gat".length());
                int npcIndex = 0;
                try {
                    npcIndex = Integer.parseInt(s);
                } catch (Exception e) {
                    continue;
                    //当转换出异常时，必然会是npc文件夹中有类似 npc2a.gat等的错误数据，因为有可能是用户自己的备份，所以不做处理，不进行删除
                }
                if (soft) {
                    npcIndex -= Configuration.Prefix.NPC_MASK;
                }
                if (AppData.getInstance().getCurProject().getNpc(npcIndex) == null) {
                    files[i].delete();
//                    System.out.println("删除Npc文件成功！");
                }
            }
        }
    }

    private void clearErrorScriptFile(File file, boolean soft) {
        File[] files = file.listFiles();
        for (int i = 0, n = files.length; i < n; i++) {
            if (files[i].getName().endsWith(".gat")
                    && files[i].getName().startsWith("script")) {
                String s = files[i].getName().substring(6, 6 + files[i].getName().length() - "script.gat".length());
                int scriptIndex = 0;
                try {
                    scriptIndex = Integer.parseInt(s);
                } catch (Exception e) {
                    continue;
                    //当转换出异常时，必然会是npc文件夹中有类似 script2a.gat等的错误数据，因为有可能是用户自己的备份，所以不做处理，不进行删除
                }
                if (soft) {
                    scriptIndex -= Configuration.Prefix.SCRIPT_MASK;
                }
                if (AppData.getInstance().getCurProject().getScript(scriptIndex) == null) {
                    files[i].delete();
//                    System.out.println("删除Script文件成功！");
                }
            }
        }
    }

    private void createGameDir() {
        ArrayList<File> files = new ArrayList<File>();
        files.add(new File(AppData.getInstance().getCurProject().getPath() + File.separatorChar
                + "data" + File.separatorChar + "map"));
        files.add(new File(AppData.getInstance().getCurProject().getPath() + File.separatorChar
                + "data" + File.separatorChar + "script"));
        for (int i = 0; i < files.size(); i++) {
            if (!files.get(i).exists()) {
                files.get(i).mkdirs();
            }
        }
    }

    private void createSoftDir() {
        ArrayList<File> files = new ArrayList<File>();
        files.add(new File(AppData.getInstance().getCurProject().getPath() + File.separatorChar
                + "softdata" + File.separatorChar + "map"));
        files.add(new File(AppData.getInstance().getCurProject().getPath() + File.separatorChar
                + "softdata" + File.separatorChar + "npc"));
        files.add(new File(AppData.getInstance().getCurProject().getPath() + File.separatorChar
                + "softdata" + File.separatorChar + "script"));
        for (int i = 0; i < files.size(); i++) {
            if (!files.get(i).exists()) {
                files.get(i).mkdirs();
            }
        }
    }

    void saveGameData() {
        if (AppData.getInstance().getCurProject() == null) {
            return;
        }
        AppMainFrame.this.setCursor(waitCursor);
        AppMainFrame.this.gGrassPane.start();
        AppMainFrame.this.gGrassPane.setText("开始保存工程...");
        saveUseTime = System.currentTimeMillis();
        createGameDir();
        Runnable task1 = new Runnable() {

            @Override
            public void run() {
                AppMainFrame.this.gGrassPane.setText("保存引擎地图开始...");
                logPrinter.v("保存引擎地图开始...");
                try {
                    //保存地图
                    Iterator it = AppData.getInstance().getCurProject().getMaps().keySet().iterator();
                    while (it.hasNext()) {
                        Integer key = (Integer) it.next();
                        Map map = AppData.getInstance().getCurProject().getMaps().get(key);
                        if (map != null) {
                            //AppMainFrame.this.gGrassPane.setText("正在保存地图:" + map);
                            IMapWriter softMapWriter = new DefaultMapLuaWriter();
                            softMapWriter.writeMap(map, AppData.getInstance().getCurProject().getPath() + File.separatorChar
                                    + "data" + File.separatorChar + "map" + File.separatorChar + "map" + map.getIndex() + ".gat");
                        }
                    }
                    //保存NPC表
                    INpcWriter npcWriter = new DefaultNpcLuaWriter();
                    npcWriter.writeNpc(AppData.getInstance().getCurProject().getPath() + File.separatorChar
                            + "data" + File.separatorChar + "npc.gat");
                    //保存Script
                    Iterator it2 = AppData.getInstance().getCurProject().getScripts().keySet().iterator();
                    while (it2.hasNext()) {
                        Integer key = (Integer) it2.next();
                        Script script = AppData.getInstance().getCurProject().getScripts().get(key);
                        if (script != null) {
                            IScriptWriter scriptWriter = new DefaultScriptLuaWriter();
                            scriptWriter.writeScript(script, AppData.getInstance().getCurProject().getPath() + File.separatorChar
                                    + "data" + File.separatorChar + "script" + File.separatorChar + "script" + (Configuration.Prefix.SCRIPT_MASK + script.getIndex() + 1) + ".gat");
                        }
                    }
                    logPrinter.v("保存引擎地图成功！");
                    Notifier.getInstance().notifyEvent(EventIdConst.GAME_MAP_SAVE_SUCCESSFUL);
                } catch (Exception ex) {
                    logPrinter.e("保存引擎地图失败！" + ex.toString());
                    Notifier.getInstance().notifyEvent(EventIdConst.GAME_MAP_SAVE_FAILURE);
                }
            }
        };
        executor.execute(task1);
        Runnable task2 = new Runnable() {

            @Override
            public void run() {
                AppMainFrame.this.gGrassPane.setText("保存引擎动画数据开始...");
                logPrinter.v("保存引擎动画数据开始...");
                try {
                    //保存动画
                    IAnimationWriter aniSoftWriter = new DefaultAnimationLuaWriter();
                    aniSoftWriter.writeAnimation(AppData.getInstance().getCurProject().getPath() + File.separatorChar
                            + "data" + File.separatorChar + "animation.gat");
                    logPrinter.v("保存引擎动画数据成功！");
                    Notifier.getInstance().notifyEvent(EventIdConst.GAME_ANIMATION_SAVE_SUCCESSFUL);
                } catch (Exception e) {
                    logPrinter.e("保存引擎动画数据失败！" + e.toString());
                    Notifier.getInstance().notifyEvent(EventIdConst.GAME_ANIMATION_SAVE_FAILURE);
                }
            }
        };
        executor.execute(task2);
        Runnable task3 = new Runnable() {

            @Override
            public void run() {
                AppMainFrame.this.gGrassPane.setText("保存引擎数据编辑器数据开始...");
                logPrinter.v("保存引擎数据编辑器数据开始...");
                try {
                    AppData.getInstance().getCurProject().getDataManager().save();
                    logPrinter.v("保存引擎数据编辑器数据成功！");
                    Notifier.getInstance().notifyEvent(EventIdConst.GAME_DATA_SAVE_SUCCESSFUL);
                } catch (Exception e) {
                    logPrinter.e("保存引擎数据编辑器数据失败！" + e.toString());
                    Notifier.getInstance().notifyEvent(EventIdConst.GAME_DATA_SAVE_FAILURE);
                }
            }
        };
        executor.execute(task3);
    }
    private long saveUseTime = 0;

    void saveProject() {
        if (AppData.getInstance().getCurProject() == null) {
            return;
        }
        AppMainFrame.this.setCursor(waitCursor);
        AppMainFrame.this.gGrassPane.start();
        AppMainFrame.this.gGrassPane.setText("开始保存工程...");
        saveUseTime = System.currentTimeMillis();
        createSoftDir();
        Runnable task0 = new Runnable() {

            @Override
            public void run() {
                try {
                    ProjectManager.update(); //更新项目文件
                    logPrinter.v("项目文件更新成功！");
                } catch (IOException ex) {
                    logPrinter.e("项目文件更新失败！" + ex.toString());
                }
                Document doc = DocumentHelper.createDocument();
                Element project = doc.addElement(AppData.getInstance().getCurProject().getName());
                saveAllChildrenMap(project, (DefaultMutableTreeNode) mapTree.getModel().getRoot());
                try {
                    OutputFormat format = OutputFormat.createPrettyPrint();
                    format.setEncoding("UTF-8");
                    XMLWriter xmlw = new XMLWriter(new FileOutputStream(AppData.getInstance().getCurProject().getPath() + File.separator + "softdata" + File.separator + "map.xml"), format);
                    xmlw.write(doc);
                    xmlw.close();
                    logPrinter.v("map.xml写出成功！");
                } catch (IOException e) {
                    logPrinter.e("map.xml写出失败！" + e.toString());
                }
            }
        };
        executor.execute(task0);
        Runnable task1 = new Runnable() {

            @Override
            public void run() {
                AppMainFrame.this.gGrassPane.setText("保存软件地图开始...");
                logPrinter.v("保存软件地图开始...");
                try {
                    //保存地图
                    Iterator it = AppData.getInstance().getCurProject().getMaps().keySet().iterator();
                    while (it.hasNext()) {
                        Integer key = (Integer) it.next();
                        Map map = AppData.getInstance().getCurProject().getMaps().get(key);
                        if (map != null) {
                            AppMainFrame.this.gGrassPane.setText("正在保存地图:" + map);
                            IMapWriter softMapWriter = new DefaultSoftMapBinaryWriter();
                            softMapWriter.writeMap(map, AppData.getInstance().getCurProject().getPath() + File.separatorChar
                                    + "softdata" + File.separatorChar + "map" + File.separatorChar + "map" + map.getIndex() + ".gat");
                        }
                    }
                    logPrinter.v("保存软件地图成功！");
                    Notifier.getInstance().notifyEvent(EventIdConst.SOFT_MAP_SAVE_SUCCESSFUL);
                } catch (Exception ex) {
                    logPrinter.e("保存软件地图失败！" + ex.toString());
                    Notifier.getInstance().notifyEvent(EventIdConst.SOFT_MAP_SAVE_FAILURE);
                }
            }
        };
        executor.execute(task1);
        Runnable task2 = new Runnable() {

            @Override
            public void run() {
                AppMainFrame.this.gGrassPane.setText("保存软件动画数据开始...");
                logPrinter.v("保存软件动画数据开始...");
                try {
                    //保存动画
                    IAnimationWriter aniSoftWriter = new DefaultSoftAnimationBinaryWriter();
                    aniSoftWriter.writeAnimation(AppData.getInstance().getCurProject().getPath() + File.separatorChar
                            + "softdata" + File.separatorChar + "animation.gat");
                    logPrinter.v("保存软件动画数据成功！");
                    Notifier.getInstance().notifyEvent(EventIdConst.SOFT_ANIMATION_SAVE_SUCCESSFUL);
                } catch (Exception e) {
                    logPrinter.e("保存软件动画数据失败！" + e.toString());
                    Notifier.getInstance().notifyEvent(EventIdConst.SOFT_ANIMATION_SAVE_FAILURE);
                }
            }
        };
        executor.execute(task2);
        Runnable task3 = new Runnable() {

            @Override
            public void run() {
                AppMainFrame.this.gGrassPane.setText("保存软件数据编辑器数据开始...");
                logPrinter.v("保存软件数据编辑器数据开始...");
                try {
                    AppData.getInstance().getCurProject().getDataManager().saveSoft();
                    logPrinter.v("保存软件数据编辑器数据成功！");
                    Notifier.getInstance().notifyEvent(EventIdConst.SOFT_DATA_SAVE_SUCCESSFUL);
                } catch (Exception e) {
                    logPrinter.e("保存软件数据编辑器数据失败！" + e.toString());
                    Notifier.getInstance().notifyEvent(EventIdConst.SOFT_DATA_SAVE_FAILURE);
                }
            }
        };
        executor.execute(task3);
    }

    private void saveAllChildrenMap(Element em, DefaultMutableTreeNode tn) {
        for (int i = 0, n = tn.getChildCount(); i < n; i++) {
            DefaultMutableTreeNode dmt = (DefaultMutableTreeNode) tn.getChildAt(i);
            Map map = (Map) dmt.getUserObject();
            Element elem = em.addElement("Map");
            Attribute attribute = elem.attribute("ID");
            if (attribute == null) {
                elem.addAttribute("ID", "" + map.getIndex());
            } else {
                attribute.setValue("" + map.getIndex());
                List list = new ArrayList();
                list.add(attribute);
                elem.setAttributes(list);
            }
            Attribute name = elem.attribute("Name");
            if (name == null) {
                elem.addAttribute("Name", map.getName());
            } else {
                name.setValue(map.getName());
                List list = new ArrayList();
                list.add(name);
                elem.setAttributes(list);
            }
            if (dmt.getChildCount() > 0) {
                saveAllChildrenMap(elem, dmt);
            }
        }
    }
    private void openPopMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openPopMenuItemActionPerformed
        // TODO add your handling code here:
        this.setVisible(true);
    }//GEN-LAST:event_openPopMenuItemActionPerformed

    private void exitPopMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitPopMenuItemActionPerformed
        // TODO add your handling code here:
        exit();
    }//GEN-LAST:event_exitPopMenuItemActionPerformed
    //退出软件的方法

    private void exit() {
        System.exit(0);
    }
    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
        this.setVisible(false);
    }//GEN-LAST:event_formWindowClosed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        trayicon.displayMessage("信息", "编辑器最小化到托盘", TrayIcon.MessageType.INFO);
    }//GEN-LAST:event_formWindowClosing

    private void penButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_penButtonActionPerformed
        // TODO add your handling code here:
        data.currentPsType = AppData.PS_PEN;
        penRadioMenuItem.setSelected(true);

    }//GEN-LAST:event_penButtonActionPerformed

    private void pourButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pourButtonActionPerformed
        // TODO add your handling code here:
        data.currentPsType = AppData.PS_FILL;
        fillRadioMenuItem.setSelected(true);

    }//GEN-LAST:event_pourButtonActionPerformed

    private void eraseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eraseButtonActionPerformed
        // TODO add your handling code here:
        data.currentPsType = AppData.PS_ERASER;
        eraseRadioMenuItem.setSelected(true);

    }//GEN-LAST:event_eraseButtonActionPerformed
    private NewTileSetDialog itsd = null;
    private void addTilesetMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addTilesetMenuItemActionPerformed
        // TODO add your handling code here:
        if (AppData.getInstance().getCurProject() == null) {
            JOptionPane.showMessageDialog(this,
                    "请先新建项目！");
            return;
        }
        if (data.getCurrentMap() == null) {
            JOptionPane.showMessageDialog(this,
                    "地图为空，请先新建地图！");
            return;
        }
        if (itsd == null) {
            itsd = new NewTileSetDialog(this, true);
        }
        itsd.setVisible(true);
    }//GEN-LAST:event_addTilesetMenuItemActionPerformed

    private void undoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_undoButtonActionPerformed
        // TODO add your handling code here:
        if (AppData.getInstance().getCurProject() == null) {
            return;
        }
        if (data.getCurrentMap() == null) {
            return;
        }
        data.getCurrentMap().getMapRender().getUndoHandler().undo();
    }//GEN-LAST:event_undoButtonActionPerformed

    private void redoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_redoButtonActionPerformed
        // TODO add your handling code here:
        if (AppData.getInstance().getCurProject() == null) {
            return;
        }
        if (data.getCurrentMap() == null) {
            return;
        }
        data.getCurrentMap().getMapRender().getUndoHandler().redo();
    }//GEN-LAST:event_redoButtonActionPerformed
    private void newLayer() {
        if (AppData.getInstance().getCurProject() == null) {
            JOptionPane.showMessageDialog(this,
                    "请先新建项目！");
            return;
        }
        if (data.getCurrentMap() == null) {
            JOptionPane.showMessageDialog(this,
                    "地图为空，请先新建地图！");
            return;
        }
        if (data.addLayer()) {
            System.out.println("增加图层成功!");
        } else {
            JOptionPane.showMessageDialog(this, "添加失败！");
        }
    }
    private void newLayerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newLayerButtonActionPerformed
        // TODO add your handling code here:
        newLayer();
    }//GEN-LAST:event_newLayerButtonActionPerformed

    private void newMapButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newMapButtonActionPerformed
        // TODO add your handling code here:
        newMap();
    }//GEN-LAST:event_newMapButtonActionPerformed

    private void newMapMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newMapMenuItemActionPerformed
        // TODO add your handling code here:
        newMap();
    }//GEN-LAST:event_newMapMenuItemActionPerformed

    private void removeMapMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeMapMenuItemActionPerformed
        // TODO add your handling code here:
        removeMap();
    }//GEN-LAST:event_removeMapMenuItemActionPerformed
    private void newMap() {
        if (AppData.getInstance().getCurProject() == null) {
            JOptionPane.showMessageDialog(this,
                    "请先新建项目！");
            return;
        }
        NewMapDialog nmd = new NewMapDialog(this, true);
        nmd.setVisible(true);
    }
    private void editMapMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editMapMenuItemActionPerformed
        // TODO add your handling code here:
        if (AppData.getInstance().getCurProject() == null) {
            JOptionPane.showMessageDialog(this,
                    "请先新建项目！");
            return;
        }
        if (data.getCurrentMap() == null) {
            JOptionPane.showMessageDialog(this,
                    "地图为空，请先新建地图！");
            return;
        }
        EditMapDialog emd = new EditMapDialog(this, true);
        emd.setVisible(true);
    }//GEN-LAST:event_editMapMenuItemActionPerformed

    private void removeMapButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeMapButtonActionPerformed
        // TODO add your handling code here:
        removeMap();
    }//GEN-LAST:event_removeMapButtonActionPerformed
    //删除指定节点下的所有节点地图

    private void removeMap() {
        if (mapTree.getSelectNode() != null && mapTree.getSelectNode().getParent() != null) {
            int id = JOptionPane.showConfirmDialog(this, "你确定要删除该地图吗？", "删除地图", JOptionPane.OK_CANCEL_OPTION);
            if ((id == JOptionPane.OK_OPTION) && (mapTree.getSelectNode().getUserObject() instanceof Map)) {
                ((DefaultTreeModel) mapTree.getModel()).removeNodeFromParent(mapTree.getSelectNode());
                removeAllChildrenMap(mapTree.getSelectNode());
                AppData.getInstance().getCurProject().removeMap(((Map) mapTree.getSelectNode().getUserObject()).getIndex());
//                System.out.println("size:" + AppData.getInstance().getCurProject().getMapCounts());
                mapTree.getSelectionModel().setSelectionPath(new TreePath(((DefaultTreeModel) mapTree.getModel()).getRoot()));
                mapScrollPane.setViewportView(null);
                data.setCurrentMap(null);
                data.setCurrentLayer(null);
                layerTable.updateUI();
                mapTree.updateUI();
            }
        }
    }

    private void removeAllChildrenMap(DefaultMutableTreeNode tn) {
        for (int i = 0, n = tn.getChildCount(); i < n; i++) {
            DefaultMutableTreeNode dmt = (DefaultMutableTreeNode) tn.getChildAt(i);
            if (dmt.getChildCount() > 0) {
                removeAllChildrenMap(dmt);
            }
            AppData.getInstance().getCurProject().removeMap(((Map) (dmt).getUserObject()).getIndex());
        }
    }

    private void removeLayer() {
        if (AppData.getInstance().getCurProject() == null) {
            JOptionPane.showMessageDialog(this,
                    "请先新建项目！");
            return;
        }
        if (data.getCurrentMap() == null) {
            JOptionPane.showMessageDialog(this,
                    "地图为空，请先新建地图！");
            return;
        }
        if (data.getCurrentLayer() instanceof TileLayer) {
            if (data.getCurrentMap().getTotalLayers() > 3) {
                Layer layer = (Layer) data.getCurrentLayer();
                layer.removeLayerChangeListener(this);
                data.getCurrentMap().removeLayer(layer);
            } else {
                JOptionPane.showMessageDialog(this,
                        "至少应有一个瓷砖层！");
            }
        } else if (data.getCurrentLayer() instanceof CollideLayer) {
            JOptionPane.showMessageDialog(this,
                    "碰撞层不能删除！");
        } else if (data.getCurrentLayer() instanceof SpriteLayer) {
            JOptionPane.showMessageDialog(this,
                    "精灵层不能删除！");
        }
    }
    private void removeLayerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeLayerButtonActionPerformed
        // TODO add your handling code here:
        removeLayer();
    }//GEN-LAST:event_removeLayerButtonActionPerformed

    private void layerTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_layerTableMouseClicked
        // TODO add your handling code here:
        //如果检测到切换到精灵层或者碰撞层，且当前模式是填充模式，则切换到画笔模式
        Layer layer = data.getCurrentMap().getLayer(layerTable.getSelectedRow());
        if ((layer instanceof SpriteLayer) || (layer instanceof CollideLayer)) {
            if (data.currentPsType == AppData.PS_FILL) {
                //模仿点击，其实这里应该改为事件回调方式更好
                penButton.setSelected(true);
                penButtonActionPerformed(null);
//                data.currentPsType = AppData.PS_PEN;
            }
        }
        data.setCurrentLayer(layer);
    }//GEN-LAST:event_layerTableMouseClicked

    private void gridMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gridMenuItemActionPerformed
        // TODO add your handling code here:
        if (AppData.getInstance().getCurProject() == null) {
            return;
        }
        if (data.getCurrentMap() == null) {
            return;
        }
        if (gridMenuItem.isSelected()) {
            data.getCurrentMap().getMapRender().setShowGrid(true);
        } else {
            data.getCurrentMap().getMapRender().setShowGrid(false);
        }
        data.getCurrentMap().getMapRender().repaint();
    }//GEN-LAST:event_gridMenuItemActionPerformed
    private void upLayer() {
        if (AppData.getInstance().getCurProject() == null) {
            JOptionPane.showMessageDialog(this,
                    "请先新建项目！");
            return;
        }
        if (data.getCurrentMap() == null) {
            JOptionPane.showMessageDialog(this,
                    "地图为空，请先新建地图！");
            return;
        }
        int layerIndex = data.getCurrentMap().getLayerArrayList().indexOf(data.getCurrentLayer());
        if (layerIndex > 0) {
            data.getCurrentMap().swapLayerUp(layerIndex);
            data.setCurrentLayer(data.getCurrentMap().getLayerArrayList().get(layerIndex - 1));
            data.getCurrentMap().getMapRender().repaint();
        }
    }
    private void upLayerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_upLayerButtonActionPerformed
        // TODO add your handling code here:
        upLayer();
    }//GEN-LAST:event_upLayerButtonActionPerformed
    private void downLayer() {
        if (AppData.getInstance().getCurProject() == null) {
            JOptionPane.showMessageDialog(this,
                    "请先新建项目！");
            return;
        }
        if (data.getCurrentMap() == null) {
            JOptionPane.showMessageDialog(this,
                    "地图为空，请先新建地图！");
            return;
        }
        int layerIndex = data.getCurrentMap().getLayerArrayList().indexOf(data.getCurrentLayer());
        int totalLayers = data.getCurrentMap().getTotalLayers();
        if (layerIndex < totalLayers - 1) {
            data.getCurrentMap().swapLayerDown(layerIndex);
            data.setCurrentLayer(data.getCurrentMap().getLayerArrayList().get(layerIndex + 1));
            data.getCurrentMap().getMapRender().repaint();
        }
    }
    private void downLayerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_downLayerButtonActionPerformed
        // TODO add your handling code here:
        downLayer();
    }//GEN-LAST:event_downLayerButtonActionPerformed

    private void zoomInMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_zoomInMenuItemActionPerformed
        // TODO add your handling code here:
        if (data.getCurrentMap() == null) {
            return;
        }
        if (data.getCurrentMap().getMapRender().zoomIn()) {
//            System.out.println("放大");
        }
    }//GEN-LAST:event_zoomInMenuItemActionPerformed

    private void zoomOutMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_zoomOutMenuItemActionPerformed
        // TODO add your handling code here:
        if (data.getCurrentMap() == null) {
            return;
        }
        if (data.getCurrentMap().getMapRender().zoomOut()) {
//            System.out.println("缩小");
        }
    }//GEN-LAST:event_zoomOutMenuItemActionPerformed

    private void zoomReturnMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_zoomReturnMenuItemActionPerformed
        // TODO add your handling code here:
        if (data.getCurrentMap() == null) {
            return;
        }
        data.getCurrentMap().getMapRender().setZoomLevel(MapRender.ZOOM_NORMALSIZE);
    }//GEN-LAST:event_zoomReturnMenuItemActionPerformed

    private void newLayerMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newLayerMenuItemActionPerformed
        // TODO add your handling code here:
        newLayer();
    }//GEN-LAST:event_newLayerMenuItemActionPerformed

    private void removeLayerMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeLayerMenuItemActionPerformed
        // TODO add your handling code here:
        removeLayer();
    }//GEN-LAST:event_removeLayerMenuItemActionPerformed

    private void upLayerMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_upLayerMenuItemActionPerformed
        // TODO add your handling code here:
        upLayer();
    }//GEN-LAST:event_upLayerMenuItemActionPerformed

    private void downLayerMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_downLayerMenuItemActionPerformed
        // TODO add your handling code here:
        downLayer();
    }//GEN-LAST:event_downLayerMenuItemActionPerformed

    private void closeProjectMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeProjectMenuItemActionPerformed
        // TODO add your handling code here:
        closeProject();
    }//GEN-LAST:event_closeProjectMenuItemActionPerformed
    /**
     * 
     */
    public void closeProject() {
        if (AppData.getInstance().getCurProject() == null) {
            return;
        }
        AppData.getInstance().getCurProject().removeProjectChangeListener(this);
        AppData.getInstance().getCurProject().setPath("");
        AppData.getInstance().getCurProject().setName("");
        AppData.getInstance().getCurProject().removeAllMap();
        AppData.getInstance().getCurProject().removeAllNpc();
        AppData.getInstance().getCurProject().removeAllAnimation();
        AppData.getInstance().getCurProject().removeAllSript();
        AppData.getInstance().getCurProject().removeAllTileSet();
        AppData.getInstance().getCurProject().getDataManager().clearAllData();
        mapTree.setSelectNode(null);//重置maptree的选择项
        setTitle(SoftInformation.chineseName + " "
                + SoftInformation.getVersion());
        if (sped != null) {
            sped.removeProjectChangeListener();
            sped = null;
        }
        if (ded != null) {
            ded = null;//置为null是为了再次打开数据编辑器时重新载入资源
        }
//        if (tscd != null) {
//            tscd = null;
//        }
        if (sed != null) {
            sed = null;
        }
        ((DefaultMutableTreeNode) mapTree.getModel().getRoot()).removeAllChildren();
        mapScrollPane.setViewportView(null);
        data.setCurrentMap(null);
        data.setCurrentLayer(null);
        layerTable.updateUI();
        mapTree.updateUI();
        stateLabel.setText("");
        logPrinter.v("close project");
        AppData.getInstance().setCurProject(null);
        System.gc();
    }
    private void penRadioMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_penRadioMenuItemActionPerformed
        // TODO add your handling code here:
        data.currentPsType = AppData.PS_PEN;
        penButton.setSelected(true);

    }//GEN-LAST:event_penRadioMenuItemActionPerformed

    private void fillRadioMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fillRadioMenuItemActionPerformed
        // TODO add your handling code here:
        data.currentPsType = AppData.PS_FILL;
        pourButton.setSelected(true);

    }//GEN-LAST:event_fillRadioMenuItemActionPerformed

    private void eraseRadioMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eraseRadioMenuItemActionPerformed
        // TODO add your handling code here:
        data.currentPsType = AppData.PS_ERASER;
        eraseButton.setSelected(true);

    }//GEN-LAST:event_eraseRadioMenuItemActionPerformed

    private void redoMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_redoMenuItemActionPerformed
        // TODO add your handling code here:
        if (AppData.getInstance().getCurProject() == null) {
            return;
        }
        if (data.getCurrentMap() == null) {
            return;
        }
        data.getCurrentMap().getMapRender().getUndoHandler().redo();
    }//GEN-LAST:event_redoMenuItemActionPerformed

    private void undoMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_undoMenuItemActionPerformed
        // TODO add your handling code here:
        if (AppData.getInstance().getCurProject() == null) {
            return;
        }
        if (data.getCurrentMap() == null) {
            return;
        }
        data.getCurrentMap().getMapRender().getUndoHandler().undo();
    }//GEN-LAST:event_undoMenuItemActionPerformed

    private void editMapButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editMapButtonActionPerformed
        // TODO add your handling code here:
        if (AppData.getInstance().getCurProject() == null) {
            JOptionPane.showMessageDialog(this,
                    "请先新建项目！");
            return;
        }
        if (data.getCurrentMap() == null) {
            JOptionPane.showMessageDialog(this,
                    "地图为空，请先新建地图！");
            return;
        }
        EditMapDialog emd = new EditMapDialog(this, true);
        emd.setVisible(true);
    }//GEN-LAST:event_editMapButtonActionPerformed
    /**
     * 
     */
    public DataEditorDialog ded;
    private void dataEditorMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dataEditorMenuItemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dataEditorMenuItemActionPerformed
    private PreferenceDialog pf;

    private void setMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_setMenuItemActionPerformed
        // TODO add your handling code here:
        if (pf == null) {
            pf = new PreferenceDialog(this, true);
        }
        pf.setVisible(true);
    }//GEN-LAST:event_setMenuItemActionPerformed
    private SpriteEditorDialog sped;
    private void spriteEditorMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_spriteEditorMenuItemActionPerformed
        // TODO add your handling code here:
        if (AppData.getInstance().getCurProject() == null) {
            JOptionPane.showMessageDialog(this,
                    "请先新建项目！");
            return;
        }
        if (sped == null) {
            sped = new SpriteEditorDialog(this, true);
        }
        sped.setVisible(true);
    }//GEN-LAST:event_spriteEditorMenuItemActionPerformed
    private ScriptEditorDialog sed;
    private void scriptEditorMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_scriptEditorMenuItemActionPerformed
        // TODO add your handling code here:
        if (AppData.getInstance().getCurProject() == null) {
            JOptionPane.showMessageDialog(this,
                    "请先新建项目！");
            return;
        }
        if (sed == null) {
            sed = new ScriptEditorDialog(true);
        }
        sed.setVisible(true);
    }//GEN-LAST:event_scriptEditorMenuItemActionPerformed

    private void mapPreviewButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mapPreviewButtonActionPerformed
        // TODO add your handling code here:
        if (AppData.getInstance().getCurProject() == null) {
            JOptionPane.showMessageDialog(this,
                    "请先新建项目！");
            return;
        }
        if (data.getCurrentMap() == null) {
            JOptionPane.showMessageDialog(this,
                    "地图为空，请先新建地图！");
            return;
        }
        PreviewMapDialog prmd = new PreviewMapDialog(this, true);
        prmd.setVisible(true);
    }//GEN-LAST:event_mapPreviewButtonActionPerformed

    private void previewMapMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_previewMapMenuItemActionPerformed
        // TODO add your handling code here:
        if (AppData.getInstance().getCurProject() == null) {
            JOptionPane.showMessageDialog(this,
                    "请先新建项目！");
            return;
        }
        if (data.getCurrentMap() == null) {
            JOptionPane.showMessageDialog(this,
                    "地图为空，请先新建地图！");
            return;
        }
        PreviewMapDialog prmd = new PreviewMapDialog(this, true);
        prmd.setVisible(true);
    }//GEN-LAST:event_previewMapMenuItemActionPerformed

    private void dataEditorMenuItemActionPerformed1(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dataEditorMenuItemActionPerformed1
        // TODO add your handling code here:
        if (AppData.getInstance().getCurProject() == null) {
            JOptionPane.showMessageDialog(this,
                    "请先新建项目！");
            return;
        }
        if (ded == null) {
            ded = new DataEditorDialog(this, true);
            try {
                AppData.getInstance().getCurProject().getDataManager().init(AppData.getInstance().getCurProject().getPath());
            } catch (FileNotFoundException ex) {
                logPrinter.e("找不到文件异常：" + ex.toString());
            } catch (IOException ex) {
                logPrinter.e("导入数据编辑器数据失败！" + ex.toString());
                Notifier.getInstance().notifyEvent(EventIdConst.SOFT_DATA_LOAD_FAILURE);
            }
        }
        ded.refresh();
        ded.setVisible(true);
    }//GEN-LAST:event_dataEditorMenuItemActionPerformed1

    private void shortcutMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_shortcutMenuItemActionPerformed
        // TODO add your handling code here:
        if (hd == null) {
            hd = new ShortcutDialog(this, true);
        }
        hd.setVisible(true);
    }//GEN-LAST:event_shortcutMenuItemActionPerformed
    private ResourceManagerDialog rm;
    private void resourceManagerMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resourceManagerMenuItemActionPerformed
        // TODO add your handling code here:
        if (AppData.getInstance().getCurProject() == null) {
            JOptionPane.showMessageDialog(this,
                    "请先新建项目！");
            return;
        }
        if (rm == null) {
            rm = new ResourceManagerDialog(this, true);
        }
        rm.refresh();
        rm.setVisible(true);
    }//GEN-LAST:event_resourceManagerMenuItemActionPerformed

    private void checkUpdateMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkUpdateMenuItemActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(this, "本功能暂不可用");
//        try {
//            Runtime.getRuntime().exec("java -jar update.jar "
//                    + SoftInformation.getVersion());
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
    }//GEN-LAST:event_checkUpdateMenuItemActionPerformed
    private Map copyMap = null;
    private void copyMapMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_copyMapMenuItemActionPerformed
        copyMap();
    }//GEN-LAST:event_copyMapMenuItemActionPerformed

    private void pasteMapMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pasteMapMenuItemActionPerformed
        // TODO add your handling code here:
        pasteMap();
    }//GEN-LAST:event_pasteMapMenuItemActionPerformed
    private void copyMap() {
        if (data.getCurrentMap() != null) {
            try {
                copyMap = (Map) data.getCurrentMap().clone();
                copyMap.addMapChangeListener(this);
                MapRenderFactory.createMapRender(copyMap).addRenderListener(this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(this, "当前地图为空，请先新建地图", "警告", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void pasteMap() {
        if (copyMap != null) {
            AppData.getInstance().getCurProject().addMap(copyMap);

            DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(copyMap);
            //将新节点插入到指定位置
            if (mapTree.getSelectNode() == null) {
                ((DefaultMutableTreeNode) mapTree.getModel().getRoot()).add(newNode);
                ((DefaultTreeModel) mapTree.getModel()).reload((DefaultMutableTreeNode) mapTree.getModel().getRoot());
            } else {
                mapTree.getSelectNode().add(newNode);
                ((DefaultTreeModel) mapTree.getModel()).reload(mapTree.getSelectNode());
            }
            mapTree.expandPath(mapTree.getSelectionPath());
            mapTree.setSelectionPath(new TreePath(((DefaultTreeModel) mapTree.getModel()).getPathToRoot(newNode)));
            //设置维持当前的选择路径
            mapTree.setExpandsSelectedPaths(true);
            copyMap = null;
        } else {
            JOptionPane.showMessageDialog(this, "请先复制地图", "警告", JOptionPane.WARNING_MESSAGE);
        }
    }
    private void pasteMapButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pasteMapButtonActionPerformed
        // TODO add your handling code here:
        pasteMap();
    }//GEN-LAST:event_pasteMapButtonActionPerformed

    private void copyMapButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_copyMapButtonActionPerformed
        // TODO add your handling code here:
        copyMap();
    }//GEN-LAST:event_copyMapButtonActionPerformed
    private void importMap() {
        JFileChooser jf = new JFileChooser(new File("."));
        jf.setFileFilter(new Project.MapFilter());
        int ret = jf.showOpenDialog(this);
        if (ret == JFileChooser.APPROVE_OPTION) {
            File file = jf.getSelectedFile();
            if (file != null) {
                IMapReader mapReader = new DefaultSoftMapBinaryReader();
                Map map = null;
                try {
                    map = mapReader.readMap(file.getPath());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                map.addMapChangeListener(this);
                MapRenderFactory.createMapRender(map).addRenderListener(this);
//                  PreviewRenderFactory.createPreviewRender(map);
                AppData.getInstance().getCurProject().addMap(map);

                DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(map);
                //将新节点插入到指定位置
                if (mapTree.getSelectNode() == null) {
                    ((DefaultMutableTreeNode) mapTree.getModel().getRoot()).add(newNode);
                    ((DefaultTreeModel) mapTree.getModel()).reload((DefaultMutableTreeNode) mapTree.getModel().getRoot());
                } else {
                    mapTree.getSelectNode().add(newNode);
                    ((DefaultTreeModel) mapTree.getModel()).reload(mapTree.getSelectNode());
                }
                mapTree.expandPath(mapTree.getSelectionPath());
                mapTree.setSelectionPath(new TreePath(((DefaultTreeModel) mapTree.getModel()).getPathToRoot(newNode)));
                //设置维持当前的选择路径
                mapTree.setExpandsSelectedPaths(true);
            }
        }
    }
    private void importMapButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_importMapButtonActionPerformed
        // TODO add your handling code here:
        importMap();
    }//GEN-LAST:event_importMapButtonActionPerformed

    private void importMapMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_importMapMenuItemActionPerformed
        // TODO add your handling code here:
        importMap();
    }//GEN-LAST:event_importMapMenuItemActionPerformed

    private void mEventButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mEventButtonActionPerformed
        // TODO add your handling code here:
        Map map = data.getCurrentMap();
        if (map != null) {
            data.setCurrentLayerIndex(map.getLayerArrayList().indexOf(map.getSpriteLayer()));
        }
    }//GEN-LAST:event_mEventButtonActionPerformed

    private void tilesetManagerMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tilesetManagerMenuItemActionPerformed
        // TODO add your handling code here:
        if (AppData.getInstance().getCurProject() == null) {
            JOptionPane.showMessageDialog(this,
                    "请先新建项目！");
            return;
        }
        if (data.getCurrentMap() == null) {
            JOptionPane.showMessageDialog(this,
                    "地图为空，请先新建地图！");
            return;
        }
        TileSetManagerDialog tsmd = new TileSetManagerDialog(this, true);
        tsmd.setVisible(true);
    }//GEN-LAST:event_tilesetManagerMenuItemActionPerformed

    private void saveMap() {
        if (AppData.getInstance().getCurProject() == null) {
            JOptionPane.showMessageDialog(this,
                    "请先新建项目！");
            return;
        }
        if (data.getCurrentMap() == null) {
            JOptionPane.showMessageDialog(this,
                    "地图为空，请先新建地图！");
            return;
        }
        IMapWriter softMapWriter = new DefaultSoftMapBinaryWriter();
        Map map = AppData.getInstance().getCurrentMap();
        try {
            softMapWriter.writeMap(map, AppData.getInstance().getCurProject().getPath() + File.separatorChar + "softdata" + File.separatorChar + "map" + File.separatorChar + "map" + map.getIndex() + ".gat");
            JOptionPane.showMessageDialog(AppMainFrame.this,
                    "地图保存成功！");
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(AppMainFrame.this,
                    "地图保存失败！");
        }
        Document doc = DocumentHelper.createDocument();
        Element project = doc.addElement(AppData.getInstance().getCurProject().getName());
        saveAllChildrenMap(project, (DefaultMutableTreeNode) mapTree.getModel().getRoot());
        try {
            OutputFormat format = OutputFormat.createPrettyPrint();
            format.setEncoding("UTF-8");
            XMLWriter xmlw = new XMLWriter(new FileOutputStream(AppData.getInstance().getCurProject().getPath() + File.separator + "softdata" + File.separator + "map.xml"), format);
            xmlw.write(doc);
            xmlw.close();
        } catch (IOException e) {
            logPrinter.e("map.xml写出失败！" + e.toString());
        }
    }
    private void saveMapButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveMapButtonActionPerformed
        // TODO add your handling code here:
        saveMap();
    }//GEN-LAST:event_saveMapButtonActionPerformed

    private void saveMapMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveMapMenuItemActionPerformed
        // TODO add your handling code here:
        saveMap();
    }//GEN-LAST:event_saveMapMenuItemActionPerformed

    private void systemTray() {
        if (SystemTray.isSupported()) {
            SystemTray tray = SystemTray.getSystemTray();
            Image icon = this.getToolkit().getImage(this.getClass().getClassLoader().getResource("com/soyomaker/resources/systemtray.png"));
            trayicon = new TrayIcon(icon, "SoyoMaker游戏编辑器 - www.soyomaker.com", trayPopMenu);
            trayicon.addMouseListener(new MouseListener() {

                public void mouseClicked(MouseEvent e) {
                    // TODO Auto-generated method stub
                    if (e.getClickCount() == 2) {
                        setVisible(true);
                    }
                }

                public void mouseEntered(MouseEvent e) {
                    // TODO Auto-generated method stub
                }

                public void mouseExited(MouseEvent e) {
                    // TODO Auto-generated method stub
                }

                public void mousePressed(MouseEvent e) {
                    // TODO Auto-generated method stub
                }

                public void mouseReleased(MouseEvent e) {
                    // TODO Auto-generated method stub
                }
            });

            try {
                tray.add(trayicon);
            } catch (AWTException e) {
                // TODO Auto-generated catch block
                logPrinter.e("系统托盘注册异常！");
            }
        }
    }

    private void setLAF(String s) {
        try {
            UIManager.setLookAndFeel(s);
            SwingUtilities.updateComponentTreeUI(this);//更新控件的外观
        } catch (Exception e) {
            logPrinter.e("软件皮肤设置异常！");
        }
    }
    private TrayIcon trayicon;

    /**
     * 
     * @param filename
     */
    public void updateRecent(String filename) {
        // If a filename is given, add it to the recent files
        if (filename != null) {
            Preference.addToRecentFiles(filename);
        }

        java.util.List<String> files = Preference.getRecentFiles();
        openRecentMenu.removeAll();
        if (!files.isEmpty()) {
            for (final String file : files) {
                JMenuItem menuItem = new JMenuItem(file);
                menuItem.addActionListener(new java.awt.event.ActionListener() {

                    public void actionPerformed(java.awt.event.ActionEvent evt) {

                        openFile(file);

                    }
                });
                openRecentMenu.add(menuItem);
            }
        } else {
            openRecentMenu.add(new JMenuItem("无最近的项目"));
        }
    }

    /**
     * @param args the command line arguments
     * @throws Exception
     */
    public static void main(String args[]) throws Exception {
        for (int i = 0; i < args.length; i++) {
            System.out.println(args[0]);
        }

//        RandomAccessFile r = new RandomAccessFile("soyomaker.lock", "rw");
//        FileChannel fc = r.getChannel();
//        AppMainFrame.fileChannel = fc;
//        FileLock lf = fc.tryLock();
//
//        if (null != lf) {
//        JDialog.setDefaultLookAndFeelDecorated(true);
//        JFrame.setDefaultLookAndFeelDecorated(true);
        UIUtil.initGlobalFont(new Font("微软雅黑", Font.PLAIN, 13));//全局字体设置
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                AppMainFrame thisClass = new AppMainFrame();
                thisClass.setLAF("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
//                thisClass.setLAF(Preference.getSkin());
                thisClass.setVisible(true);
            }
        });
//        } else {
//            //FIX ME 这里以后改成弹出自定义的对话框，会有依旧打开，暂不打开等按钮
//            JOptionPane.showMessageDialog(null,
//                    "已经有一个软件的实例在运行！");
//        }

    }
    private AppData data = AppData.getInstance();
    private LayerTableModel ltm = new LayerTableModel();
    private LogPrinter logPrinter = LogPrinterFactory.getDefaultLogPrinter();
    private JInfiniteProgressPanel gGrassPane = new JInfiniteProgressPanel();
//    private static String substance = "org.pushingpixels.substance.api.skin.SubstanceMarinerLookAndFeel";
//    private static String substance = "org.pushingpixels.substance.api.skin.SubstanceBusinessBlackSteelLookAndFeel";//这里修改默认皮肤
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem UserMenuItem;
    private javax.swing.JMenuItem aboutMenuItem;
    private javax.swing.JMenuItem addTilesetMenuItem;
    private javax.swing.ButtonGroup buttonGroup;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JMenuItem checkUpdateMenuItem;
    private javax.swing.JMenuItem closeProjectMenuItem;
    private javax.swing.JButton copyMapButton;
    private javax.swing.JMenuItem copyMapMenuItem;
    private javax.swing.JMenuItem dataEditorMenuItem;
    private javax.swing.JButton downLayerButton;
    private javax.swing.JMenuItem downLayerMenuItem;
    private javax.swing.JMenu editLayerMenu;
    private javax.swing.JButton editMapButton;
    private javax.swing.JMenuItem editMapMenuItem;
    private javax.swing.JMenu editMenu;
    private javax.swing.JToggleButton eraseButton;
    private javax.swing.JRadioButtonMenuItem eraseRadioMenuItem;
    private javax.swing.JMenuItem exitMenuItem;
    private java.awt.MenuItem exitPopMenuItem;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JRadioButtonMenuItem fillRadioMenuItem;
    private javax.swing.JCheckBoxMenuItem gridMenuItem;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JMenuItem helpMenuItem;
    private javax.swing.JButton importMapButton;
    private javax.swing.JMenuItem importMapMenuItem;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JSplitPane jSplitPane2;
    private javax.swing.JSplitPane jSplitPane3;
    private javax.swing.JMenu layerMenu;
    private javax.swing.JPanel layerPane;
    public javax.swing.JTable layerTable;
    private javax.swing.JToolBar layerToolBar;
    private javax.swing.JButton mEventButton;
    private javax.swing.JMenuBar mainMenuBar;
    private javax.swing.JSplitPane mainSplitPane;
    private javax.swing.JTabbedPane mainTabbedPane;
    private javax.swing.JPanel mapPane;
    private javax.swing.JPopupMenu mapPopupMenu;
    private javax.swing.JButton mapPreviewButton;
    public javax.swing.JScrollPane mapScrollPane;
    private javax.swing.JToolBar mapToolBar;
    public com.soyomaker.widget.SoyoMapTree mapTree;
    private javax.swing.JButton newLayerButton;
    private javax.swing.JMenuItem newLayerMenuItem;
    private javax.swing.JButton newMapButton;
    private javax.swing.JMenuItem newMapMenuItem;
    private javax.swing.JButton newProjectButton;
    private javax.swing.JMenuItem newProjectMenuItem;
    private java.awt.MenuItem openPopMenuItem;
    private javax.swing.JButton openProjectButton;
    private javax.swing.JMenuItem openProjectMenuItem;
    private javax.swing.JMenu openRecentMenu;
    private javax.swing.JMenu operationMenu;
    private javax.swing.JButton pasteMapButton;
    private javax.swing.JMenuItem pasteMapMenuItem;
    private javax.swing.JToggleButton penButton;
    private javax.swing.JRadioButtonMenuItem penRadioMenuItem;
    private javax.swing.JMenuItem pluginManagerMenuItem;
    private javax.swing.JMenu pluginMenu;
    private javax.swing.JToggleButton pourButton;
    private javax.swing.JMenuItem previewMapMenuItem;
    private javax.swing.JButton redoButton;
    private javax.swing.JMenuItem redoMenuItem;
    private javax.swing.JButton removeLayerButton;
    private javax.swing.JMenuItem removeLayerMenuItem;
    private javax.swing.JButton removeMapButton;
    private javax.swing.JMenuItem removeMapMenuItem;
    private javax.swing.JMenuItem resourceManagerMenuItem;
    private javax.swing.JButton saveMapButton;
    private javax.swing.JMenuItem saveMapMenuItem;
    private javax.swing.JButton saveProjectButton;
    private javax.swing.JMenuItem saveProjectMenuItem;
    private javax.swing.JMenuItem scriptEditorMenuItem;
    private javax.swing.JMenuItem setMenuItem;
    private javax.swing.JMenuItem shortcutMenuItem;
    private javax.swing.JMenu simulationMenu;
    private javax.swing.JMenuItem spriteEditorMenuItem;
    private javax.swing.JCheckBoxMenuItem stateBarMenuItem;
    private javax.swing.JPanel stateBarPanel;
    public javax.swing.JLabel stateLabel;
    public com.soyomaker.widget.TileSetTabbedPane tileSetTabbedPane;
    private javax.swing.JMenuItem tilesetManagerMenuItem;
    private javax.swing.JMenu tilesetMenu;
    private javax.swing.JScrollPane tilesetScrollPane;
    private javax.swing.JToolBar toolBar;
    private javax.swing.JCheckBoxMenuItem toolBarMenuItem;
    private javax.swing.JMenu toolMenu;
    private java.awt.PopupMenu trayPopMenu;
    private javax.swing.JButton undoButton;
    private javax.swing.JMenuItem undoMenuItem;
    private javax.swing.JButton upLayerButton;
    private javax.swing.JMenuItem upLayerMenuItem;
    private javax.swing.JMenu viewMenu;
    private javax.swing.JMenuItem zoomInMenuItem;
    private javax.swing.JMenu zoomMenu;
    private javax.swing.JMenuItem zoomOutMenuItem;
    private javax.swing.JMenuItem zoomReturnMenuItem;
    // End of variables declaration//GEN-END:variables

    /**
     * 
     * @param e
     */
    public void mapChanged(MapChangedEvent e) {
    }

    /**
     * 
     * @param e
     * @param layer
     */
    public void layerAdded(MapChangedEvent e, Layer layer) {
        layerTable.updateUI();
    }

    /**
     * 
     * @param e
     * @param index
     */
    public void layerRemoved(MapChangedEvent e, int index) {
        layerTable.getSelectionModel().setSelectionInterval(0, 0);
        layerTable.updateUI();
        data.setCurrentLayer(data.getCurrentMap().getLayer(0));
        data.getCurrentMap().getMapRender().repaint();
    }

    /**
     * 
     * @param e
     * @param tileset
     */
    public void tilesetAdded(MapChangedEvent e, TileSet tileset) {
    }

    /**
     * 
     * @param e
     * @param index
     */
    public void tilesetRemoved(MapChangedEvent e, int index) {
        data.getCurrentMap().getMapRender().repaint();
    }

    /**
     * 
     * @param event
     */
    public void layerChanged(LayerChangedEvent event) {
    }

    /**
     * 
     * @param event
     * @param nowBool
     */
    public void visibleChanged(LayerChangedEvent event, boolean nowBool) {
        data.getCurrentMap().getMapRender().repaint();
    }

    /**
     * 
     * @param event
     * @param oldName
     * @param newName
     */
    public void nameChanged(LayerChangedEvent event, String oldName, String newName) {
    }

    /**
     * 
     * @param e
     */
    public void projectChanged(ProjectChangedEvent e) {
    }

    /**
     * 
     * @param e
     * @param map
     */
    public void mapAdded(ProjectChangedEvent e, Map map) {
    }

    /**
     * 
     * @param e
     * @param index
     */
    public void mapRemoved(ProjectChangedEvent e, int index) {
    }

    public void npcAdded(ProjectChangedEvent e, Npc npc) {
    }

    public void npcRemoved(ProjectChangedEvent e, int index) {
    }

    public void scriptAdded(ProjectChangedEvent e, Script npc) {
    }

    public void scriptRemoved(ProjectChangedEvent e, int index) {
    }

    public void animationAdded(ProjectChangedEvent e, Animation ani) {
    }

    public void animationRemoved(ProjectChangedEvent e, int index) {
    }

    public void pictureAdded(ProjectChangedEvent e, Picture pic) {
    }

    public void pictureRemoved(ProjectChangedEvent e, int index) {
    }

    public void dragEnter(DropTargetDragEvent dtde) {
    }

    public void dragOver(DropTargetDragEvent dtde) {
    }

    public void dropActionChanged(DropTargetDragEvent dtde) {
    }

    public void dragExit(DropTargetEvent dte) {
    }

    public void drop(DropTargetDropEvent dtde) {
        try {
            if (dtde.isDataFlavorSupported(DataFlavor.javaFileListFlavor)) {
                dtde.acceptDrop(DnDConstants.ACTION_COPY_OR_MOVE);
                List list = (List) (dtde.getTransferable().getTransferData(DataFlavor.javaFileListFlavor));
                Iterator iterator = list.iterator();
                while (iterator.hasNext()) {
                    final File f = (File) iterator.next();
                    if (f.getName().endsWith(Project.ProjectFilter.END)) {
                        openFile(f.getParent());
                    }
                }
                dtde.dropComplete(true);
            } else {
                dtde.rejectDrop();
            }
        } catch (Exception ioe) {
            ioe.printStackTrace();
        }
    }

    /**
     *
     * @param ild
     */
    public void logDisplayerAdded(ILogDisplayer ild) {
        if (ild instanceof SMLogDisplayer) {
            mainTabbedPane.add(((SMLogDisplayer) ild).getName(), ((SMLogDisplayer) ild).getJScrollPane());
        }
    }

    /**
     *
     * @param ild
     */
    public void logDisplayerRemoved(ILogDisplayer ild) {
    }

    /**
     *
     * @param p
     */
    public void focusPointChanged(Point p) {
        stateLabel.setText(
                " 当前位置: " + p.y + "行," + p.x + "列");
    }

    /**
     *
     * @param p
     */
    public void pressPointChanged(Point p) {
    }
    private int saveGameMapFinish = 0;
    private int saveGameDataFinish = 0;
    private int saveGameAnimationFinish = 0;
    private int saveMapFinish = 0;
    private int saveDataFinish = 0;
    private int saveAnimationFinish = 0;
    private int loadMapFinish = 0;
    private int loadDataFinish = 0;
    private int loadAnimationFinish = 0;
    private int newLoadDataFinish = 0;
    private int newLoadAnimationFinish = 0;
    private int newCopyResFinish = 0;

    public void handleEvent(Event event) {
        if (event.getCommand().equals(EventIdConst.GAME_ANIMATION_SAVE_FAILURE)) {
            AppMainFrame.this.gGrassPane.setText("动画保存失败");
            saveGameAnimationFinish = -1;
        } else if (event.getCommand().equals(EventIdConst.GAME_ANIMATION_SAVE_SUCCESSFUL)) {
            AppMainFrame.this.gGrassPane.setText("动画保存成功");
            saveGameAnimationFinish = 1;
        } else if (event.getCommand().equals(EventIdConst.GAME_DATA_SAVE_FAILURE)) {
            AppMainFrame.this.gGrassPane.setText("数据保存失败");
            saveGameDataFinish = -1;
        } else if (event.getCommand().equals(EventIdConst.GAME_DATA_SAVE_SUCCESSFUL)) {
            AppMainFrame.this.gGrassPane.setText("数据保存成功");
            saveGameDataFinish = 1;
        } else if (event.getCommand().equals(EventIdConst.GAME_MAP_SAVE_FAILURE)) {
            AppMainFrame.this.gGrassPane.setText("地图保存失败");
            saveGameMapFinish = -1;
        } else if (event.getCommand().equals(EventIdConst.GAME_MAP_SAVE_SUCCESSFUL)) {
            AppMainFrame.this.gGrassPane.setText("地图保存成功");
            saveGameMapFinish = 1;
        } else if (event.getCommand().equals(EventIdConst.SOFT_ANIMATION_SAVE_FAILURE)) {
            AppMainFrame.this.gGrassPane.setText("动画保存失败");
            saveAnimationFinish = -1;
        } else if (event.getCommand().equals(EventIdConst.SOFT_ANIMATION_SAVE_SUCCESSFUL)) {
            AppMainFrame.this.gGrassPane.setText("动画保存成功");
            saveAnimationFinish = 1;
        } else if (event.getCommand().equals(EventIdConst.SOFT_DATA_SAVE_FAILURE)) {
            AppMainFrame.this.gGrassPane.setText("数据保存失败");
            saveDataFinish = -1;
        } else if (event.getCommand().equals(EventIdConst.SOFT_DATA_SAVE_SUCCESSFUL)) {
            AppMainFrame.this.gGrassPane.setText("数据保存成功");
            saveDataFinish = 1;
        } else if (event.getCommand().equals(EventIdConst.SOFT_MAP_SAVE_FAILURE)) {
            AppMainFrame.this.gGrassPane.setText("地图保存失败");
            saveMapFinish = -1;
        } else if (event.getCommand().equals(EventIdConst.SOFT_MAP_SAVE_SUCCESSFUL)) {
            AppMainFrame.this.gGrassPane.setText("地图保存成功");
            saveMapFinish = 1;
        } else if (event.getCommand().equals(EventIdConst.SOFT_ANIMATION_LOAD_FAILURE)) {
            AppMainFrame.this.gGrassPane.setText("动画导入失败");
            loadAnimationFinish = -1;
        } else if (event.getCommand().equals(EventIdConst.SOFT_ANIMATION_LOAD_SUCCESSFUL)) {
            AppMainFrame.this.gGrassPane.setText("动画导入成功");
            loadAnimationFinish = 1;
        } else if (event.getCommand().equals(EventIdConst.SOFT_DATA_LOAD_FAILURE)) {
            AppMainFrame.this.gGrassPane.setText("数据导入失败");
            loadDataFinish = -1;
        } else if (event.getCommand().equals(EventIdConst.SOFT_DATA_LOAD_SUCCESSFUL)) {
            AppMainFrame.this.gGrassPane.setText("数据导入成功");
            loadDataFinish = 1;
        } else if (event.getCommand().equals(EventIdConst.SOFT_MAP_LOAD_FAILURE)) {
            AppMainFrame.this.gGrassPane.setText("地图导入失败");
            loadMapFinish = -1;
        } else if (event.getCommand().equals(EventIdConst.SOFT_MAP_LOAD_SUCCESSFUL)) {
            AppMainFrame.this.gGrassPane.setText("地图导入成功");
            loadMapFinish = 1;
        } else if (event.getCommand().equals(EventIdConst.SOFT_NEW_ANIMATION_LOAD_FAILURE)) {
            AppMainFrame.this.gGrassPane.setText("读取默认动画数据失败");
            newLoadAnimationFinish = -1;
        } else if (event.getCommand().equals(EventIdConst.SOFT_NEW_ANIMATION_LOAD_SUCCESSFUL)) {
            AppMainFrame.this.gGrassPane.setText("读取默认动画数据成功");
            newLoadAnimationFinish = 1;
        } else if (event.getCommand().equals(EventIdConst.SOFT_NEW_DATA_LOAD_FAILURE)) {
            AppMainFrame.this.gGrassPane.setText("读取默认数据编辑器数据失败");
            newLoadDataFinish = -1;
        } else if (event.getCommand().equals(EventIdConst.SOFT_NEW_DATA_LOAD_SUCCESSFUL)) {
            AppMainFrame.this.gGrassPane.setText("读取默认数据编辑器数据成功");
            newLoadDataFinish = 1;
        } else if (event.getCommand().equals(EventIdConst.SOFT_NEW_COPY_RES_SUCCESSFUL)) {
            AppMainFrame.this.gGrassPane.setText("复制默认资源文件成功");
            newCopyResFinish = 1;
        } else if (event.getCommand().equals(EventIdConst.SOFT_NEW_COPY_RES_FAILURE)) {
            AppMainFrame.this.gGrassPane.setText("复制默认资源文件失败");
            newCopyResFinish = -1;
        }
        if (saveGameMapFinish != 0 && saveGameDataFinish != 0 && saveGameAnimationFinish != 0) {
            saveUseTime = System.currentTimeMillis() - saveUseTime;
            AppMainFrame.this.gGrassPane.setText("保存工程成功！");
            AppMainFrame.this.gGrassPane.stop();
            AppMainFrame.this.setCursor(normalCursor);
            //小于0表示失败，大于0表示成功，0表示正在保存
            if (saveGameMapFinish < 0) {
                Notifier.getInstance().notifyEvent(EventIdConst.GAME_BUILD_FAILURE);
//                JOptionPane.showMessageDialog(this, "地图数据保存失败", "错误", JOptionPane.ERROR_MESSAGE);
            }
            if (saveGameDataFinish < 0) {
                Notifier.getInstance().notifyEvent(EventIdConst.GAME_BUILD_FAILURE);
//                JOptionPane.showMessageDialog(this, "数据编辑器数据保存失败", "错误", JOptionPane.ERROR_MESSAGE);
            }
            if (saveGameAnimationFinish < 0) {
                Notifier.getInstance().notifyEvent(EventIdConst.GAME_BUILD_FAILURE);
//                JOptionPane.showMessageDialog(this, "动画数据保存失败", "错误", JOptionPane.ERROR_MESSAGE);
            }
            if (saveGameMapFinish > 0 && saveGameDataFinish > 0 && saveGameAnimationFinish > 0) {
                Notifier.getInstance().notifyEvent(EventIdConst.GAME_BUILD_SUCCESSFUL);
                logPrinter.v("工程保存成功！用时：" + (saveUseTime * 1.0 / 1000.0) + "s");
//                JOptionPane.showMessageDialog(this, "工程保存成功！用时：" + (saveUseTime * 1.0 / 1000.0) + "s", "提示", JOptionPane.INFORMATION_MESSAGE);
            }
            saveGameMapFinish = 0;
            saveGameDataFinish = 0;
            saveGameAnimationFinish = 0;
        }
        if (saveMapFinish != 0 && saveDataFinish != 0 && saveAnimationFinish != 0) {
            saveUseTime = System.currentTimeMillis() - saveUseTime;
            AppMainFrame.this.gGrassPane.setText("保存工程成功！");
            AppMainFrame.this.gGrassPane.stop();
            AppMainFrame.this.setCursor(normalCursor);
            //小于0表示失败，大于0表示成功，0表示正在保存
            if (saveMapFinish < 0) {
                JOptionPane.showMessageDialog(this, "地图数据保存失败", "错误", JOptionPane.ERROR_MESSAGE);
            }
            if (saveDataFinish < 0) {
                JOptionPane.showMessageDialog(this, "数据编辑器数据保存失败", "错误", JOptionPane.ERROR_MESSAGE);
            }
            if (saveAnimationFinish < 0) {
                JOptionPane.showMessageDialog(this, "动画数据保存失败", "错误", JOptionPane.ERROR_MESSAGE);
            }
            if (saveMapFinish > 0 && saveDataFinish > 0 && saveAnimationFinish > 0) {
                JOptionPane.showMessageDialog(this, "工程保存成功！用时：" + (saveUseTime * 1.0 / 1000.0) + "s", "提示", JOptionPane.INFORMATION_MESSAGE);
            }
            saveMapFinish = 0;
            saveDataFinish = 0;
            saveAnimationFinish = 0;
        }
        if (loadMapFinish != 0 && loadDataFinish != 0 && loadAnimationFinish != 0) {
            loadUseTime = System.currentTimeMillis() - loadUseTime;
            AppMainFrame.this.gGrassPane.setText("打开工程成功！");
            AppMainFrame.this.gGrassPane.stop();
            AppMainFrame.this.setCursor(normalCursor);
            //小于0表示失败，大于0表示成功，0表示正在保存
            if (loadMapFinish < 0) {
                JOptionPane.showMessageDialog(this, "地图数据导入失败", "错误", JOptionPane.ERROR_MESSAGE);
            }
            if (loadDataFinish < 0) {
                JOptionPane.showMessageDialog(this, "数据编辑器数据导入失败", "错误", JOptionPane.ERROR_MESSAGE);
            }
            if (loadAnimationFinish < 0) {
                JOptionPane.showMessageDialog(this, "动画数据导入失败", "错误", JOptionPane.ERROR_MESSAGE);
            }
            if (loadMapFinish > 0 && loadDataFinish > 0 && loadAnimationFinish > 0) {
                JOptionPane.showMessageDialog(this, "工程导入成功！用时：" + (loadUseTime * 1.0 / 1000.0) + "s", "提示", JOptionPane.INFORMATION_MESSAGE);
            }
            loadMapFinish = 0;
            loadDataFinish = 0;
            loadAnimationFinish = 0;
        }
        if (newCopyResFinish != 0 && newLoadDataFinish != 0 && newLoadAnimationFinish != 0) {
            newUseTime = System.currentTimeMillis() - newUseTime;
            AppMainFrame.this.gGrassPane.setText("新建工程成功！");
            AppMainFrame.this.gGrassPane.stop();
            AppMainFrame.this.setCursor(normalCursor);
            //小于0表示失败，大于0表示成功，0表示正在保存
            if (newLoadDataFinish < 0) {
                JOptionPane.showMessageDialog(this, "默认数据编辑器数据导入失败", "错误", JOptionPane.ERROR_MESSAGE);
            }
            if (newLoadAnimationFinish < 0) {
                JOptionPane.showMessageDialog(this, "默认动画数据导入失败", "错误", JOptionPane.ERROR_MESSAGE);
            }
            if (newCopyResFinish < 0) {
                JOptionPane.showMessageDialog(this, "复制默认资源文件失败", "错误", JOptionPane.ERROR_MESSAGE);
            }
            if (newCopyResFinish > 0 && newLoadDataFinish > 0 && newLoadAnimationFinish > 0) {
                JOptionPane.showMessageDialog(this, "工程新建成功！用时：" + (newUseTime * 1.0 / 1000.0) + "s", "提示", JOptionPane.INFORMATION_MESSAGE);
            }
            newCopyResFinish = 0;
            newLoadDataFinish = 0;
            newLoadAnimationFinish = 0;
        }
    }
}
