/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * MainFrame.java
 *
 * Created on 2011-3-13, 13:42:40
 */
package com.soyostar;

//import com.soyostar.data.DataManager;
//import com.soyostar.data.model.Model;
import com.soyostar.config.Config;
import com.soyostar.data.DataManager;
import com.soyostar.dialog.DataEditorDialog;
import com.soyostar.listener.ProjectChangedEvent;
import com.soyostar.model.map.Npc;
import com.soyostar.model.map.ScriptFile;
import com.soyostar.proxy.SoftProxy;
import com.soyostar.dialog.AboutDialog;
import com.soyostar.dialog.EditMapDialog;
import com.soyostar.dialog.HelpDialog;
import com.soyostar.dialog.NewMapDialog;
import com.soyostar.dialog.NewProjectDialog;
import com.soyostar.dialog.NewTileSetDialog;
import com.soyostar.dialog.PluginManagerDialog;
import com.soyostar.dialog.PreferenceDialog;
//import com.soyostar.dialog.ProgressBarDialog;
import com.soyostar.dialog.PreviewMapDialog;
import com.soyostar.dialog.ScriptEditorDialog;
import com.soyostar.dialog.SoftLogDialog;
import com.soyostar.dialog.SpriteEditorDialog;
import com.soyostar.dialog.TileSetCrossDialog;
import com.soyostar.dialog.TileSetManagerDialog;
import com.soyostar.infomation.SoftInformation;
import com.soyostar.io.DefaultMapWriter;
import com.soyostar.io.DefaultSoftMapReader;
import com.soyostar.io.DefaultSoftMapWriter;
import com.soyostar.io.IMapReader;
import com.soyostar.io.IMapWriter;
import com.soyostar.listener.LayerChangeListener;
import com.soyostar.listener.LayerChangedEvent;
import com.soyostar.listener.MapChangeListener;
import com.soyostar.listener.MapChangedEvent;
import com.soyostar.listener.ProjectChangeListener;
import com.soyostar.model.map.CollideLayer;
import com.soyostar.model.map.Layer;
import com.soyostar.model.LayerTableModel;
import com.soyostar.model.map.Map;
import com.soyostar.model.map.SpriteLayer;
import com.soyostar.model.map.Tile;
import com.soyostar.model.map.TileLayer;
import com.soyostar.model.map.TileSet;
import com.soyostar.plugin.PluginLoader;
import com.soyostar.project.Project;
import com.soyostar.project.ProjectManager;
import com.soyostar.render.MapRender;
//import com.soyostar.render.MapRenderFactory;
//import com.soyostar.undoredo.LayerEdit;
//import com.soyostar.undoredo.UndoRedoHandler;
import com.soyostar.render.MapRenderFactory;
import com.soyostar.render.PreviewRenderFactory;
import com.soyostar.util.Log;
import com.soyostar.util.UIUtil;
//import com.sun.xml.internal.messaging.saaj.util.Base64;
import java.awt.AWTException;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.ProgressMonitor;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.undo.UndoableEditSupport;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

/**
 *
 * @author Administrator
 */
public class MainFrame extends javax.swing.JFrame implements MapChangeListener, LayerChangeListener, ProjectChangeListener, DropTargetListener {

    /** Creates new form MainFrame */
    public MainFrame() {
        long time = System.currentTimeMillis();
        if (PluginLoader.getInstance().readPlugin(this)) {
            //载入插件
            System.out.println("读取插件成功！");
        }
        initComponents();
        systemTray();
        initialize();
        data.setMainFrame(this);
        System.out.println("软件启动完成！耗时：" + (System.currentTimeMillis() - time) + "ms");
    }

    private void initialize() {
        updateRecent(null);
        new DropTarget(this, DnDConstants.ACTION_COPY_OR_MOVE, this);
        setLocationRelativeTo(null);
        setTitle(SoftInformation.chineseName + " "
            + SoftInformation.majorVersion + "."
            + SoftInformation.minorVersion + "."
            + SoftInformation.modifiedVersion);
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
        buttonGroup1 = new javax.swing.ButtonGroup();
        stateBarPanel = new javax.swing.JPanel();
        scrollLabel = new com.soyostar.widge.JScrollLabel();
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
        chooseButton = new javax.swing.JToggleButton();
        mainSplitPane = new javax.swing.JSplitPane();
        jSplitPane1 = new javax.swing.JSplitPane();
        tilesetScrollPane = new javax.swing.JScrollPane();
        tileSetTabbedPane = new com.soyostar.widge.TileSetTabbedPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        mapPane = new javax.swing.JPanel();
        mapToolBar = new javax.swing.JToolBar();
        newMapButton = new javax.swing.JButton();
        removeMapButton = new javax.swing.JButton();
        editMapButton = new javax.swing.JButton();
        mapPreviewButton = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        mapTree = new com.soyostar.widge.JMapTree();
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
        jScrollPane2 = new javax.swing.JScrollPane();
        functionTabbedPane = new javax.swing.JTabbedPane();
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
        chooseRadioMenuItem = new javax.swing.JRadioButtonMenuItem();
        tilesetMenu = new javax.swing.JMenu();
        addTilesetMenuItem = new javax.swing.JMenuItem();
        TilesetManager = new javax.swing.JMenuItem();
        tilesetCrossMenuItem = new javax.swing.JMenuItem();
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
        setMenuItem = new javax.swing.JMenuItem();
        pluginMenu = new javax.swing.JMenu();
        pluginManagerMenuItem = new javax.swing.JMenuItem();
        simulationMenu = new javax.swing.JMenu();
        gameTestMenuItem = new javax.swing.JMenuItem();
        helpMenu = new javax.swing.JMenu();
        softLogMenuItem = new javax.swing.JMenuItem();
        checkUpdateMenuItem = new javax.swing.JMenuItem();
        UserMenuItem = new javax.swing.JMenuItem();
        sendBugMenuItem = new javax.swing.JMenuItem();
        helpMenuItem = new javax.swing.JMenuItem();
        aboutMenuItem = new javax.swing.JMenuItem();

        trayPopMenu.setLabel("popupMenu1");

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance().getContext().getResourceMap(MainFrame.class);
        openPopMenuItem.setLabel(resourceMap.getString("openPopMenuItem.label")); // NOI18N
        openPopMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openPopMenuItemActionPerformed(evt);
            }
        });
        trayPopMenu.add(openPopMenuItem);

        exitPopMenuItem.setLabel(resourceMap.getString("exitPopMenuItem.label")); // NOI18N
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

        scrollLabel.setText("欢迎使用手游之星游戏编辑器！");
        scrollLabel.setName("scrollLabel"); // NOI18N

        stateLabel.setName("stateLabel"); // NOI18N

        javax.swing.GroupLayout stateBarPanelLayout = new javax.swing.GroupLayout(stateBarPanel);
        stateBarPanel.setLayout(stateBarPanelLayout);
        stateBarPanelLayout.setHorizontalGroup(
            stateBarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, stateBarPanelLayout.createSequentialGroup()
                .addComponent(stateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 560, Short.MAX_VALUE)
                .addComponent(scrollLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        stateBarPanelLayout.setVerticalGroup(
            stateBarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
            .addComponent(stateLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
        );

        toolBar.setFloatable(false);
        toolBar.setRollover(true);
        toolBar.setName("工具栏"); // NOI18N
        for (int i = 0; i<PluginLoader.getInstance().getToolBarPluginNum(); i++) {
            toolBar.add(PluginLoader.getInstance().getToolBarPlugins().get(i));
        }

        newProjectButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/soyostar/resources/newMap.png"))); // NOI18N
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

        openProjectButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/soyostar/resources/openMap.png"))); // NOI18N
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

        saveProjectButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/soyostar/resources/saveMap.png"))); // NOI18N
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

        undoButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/soyostar/resources/undo.png"))); // NOI18N
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

        redoButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/soyostar/resources/redo.png"))); // NOI18N
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
        penButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/soyostar/resources/gimp-tool-pencil-22.png"))); // NOI18N
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
        pourButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/soyostar/resources/gimp-tool-bucket-fill-22.png"))); // NOI18N
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
        eraseButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/soyostar/resources/gimp-tool-eraser-22.png"))); // NOI18N
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

        buttonGroup.add(chooseButton);
        chooseButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/soyostar/resources/gimp-tool-rect-select-22.png"))); // NOI18N
        chooseButton.setToolTipText("选择");
        chooseButton.setFocusable(false);
        chooseButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        chooseButton.setName("chooseButton"); // NOI18N
        chooseButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        chooseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chooseButtonActionPerformed(evt);
            }
        });
        toolBar.add(chooseButton);

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

        newMapButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/soyostar/resources/gnome-new.png"))); // NOI18N
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

        removeMapButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/soyostar/resources/gnome-delete.png"))); // NOI18N
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

        editMapButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/soyostar/resources/gtk-edit.png"))); // NOI18N
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

        mapPreviewButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/soyostar/resources/preview.png"))); // NOI18N
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

        jScrollPane5.setName("jScrollPane5"); // NOI18N

        mapTree.setComponentPopupMenu(mapPopupMenu);
        mapTree.setName("mapTree"); // NOI18N
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
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE))
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
        jSplitPane3.setResizeWeight(0.8);
        jSplitPane3.setName("jSplitPane3"); // NOI18N
        jSplitPane3.setOneTouchExpandable(true);
        jSplitPane3.setPreferredSize(new java.awt.Dimension(200, 381));

        jScrollPane4.setName("jScrollPane4"); // NOI18N

        layerPane.setName("layerPane"); // NOI18N
        layerPane.setPreferredSize(new java.awt.Dimension(200, 170));

        layerToolBar.setFloatable(false);
        layerToolBar.setRollover(true);
        layerToolBar.setName("图层"); // NOI18N

        newLayerButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/soyostar/resources/gnome-new.png"))); // NOI18N
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

        upLayerButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/soyostar/resources/gnome-up.png"))); // NOI18N
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

        downLayerButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/soyostar/resources/gnome-down.png"))); // NOI18N
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

        removeLayerButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/soyostar/resources/gnome-delete.png"))); // NOI18N
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
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE))
        );

        jScrollPane4.setViewportView(layerPane);

        jSplitPane3.setRightComponent(jScrollPane4);

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        functionTabbedPane.setName("functionTabbedPane"); // NOI18N
        jScrollPane2.setViewportView(functionTabbedPane);

        jSplitPane3.setLeftComponent(jScrollPane2);

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
        for (int i = 0; i<PluginLoader.getInstance().getMenuPluginNum(); i++) {
            mainMenuBar.add(PluginLoader.getInstance().getMenuPlugins().get(i));
        }

        fileMenu.setMnemonic('F');
        fileMenu.setText(resourceMap.getString("fileMenu.text")); // NOI18N
        fileMenu.setName("fileMenu"); // NOI18N

        newProjectMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/soyostar/resources/1.PNG"))); // NOI18N
        newProjectMenuItem.setText(resourceMap.getString("newProjectMenuItem.text")); // NOI18N
        newProjectMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newProjectMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(newProjectMenuItem);

        openProjectMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/soyostar/resources/2.PNG"))); // NOI18N
        openProjectMenuItem.setText(resourceMap.getString("openProjectMenuItem.text")); // NOI18N
        openProjectMenuItem.setName("openProjectMenuItem"); // NOI18N
        openProjectMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openProjectMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(openProjectMenuItem);

        saveProjectMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/soyostar/resources/3.PNG"))); // NOI18N
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
        undoMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/soyostar/resources/16.PNG"))); // NOI18N
        undoMenuItem.setText(resourceMap.getString("undoMenuItem.text")); // NOI18N
        undoMenuItem.setName("undoMenuItem"); // NOI18N
        undoMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                undoMenuItemActionPerformed(evt);
            }
        });
        editMenu.add(undoMenuItem);

        redoMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Y, java.awt.event.InputEvent.CTRL_MASK));
        redoMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/soyostar/resources/17.PNG"))); // NOI18N
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

        buttonGroup1.add(fillRadioMenuItem);
        fillRadioMenuItem.setText("填充");
        fillRadioMenuItem.setName("fillRadioMenuItem"); // NOI18N
        fillRadioMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fillRadioMenuItemActionPerformed(evt);
            }
        });
        operationMenu.add(fillRadioMenuItem);

        buttonGroup1.add(eraseRadioMenuItem);
        eraseRadioMenuItem.setText("橡皮");
        eraseRadioMenuItem.setName("eraseRadioMenuItem"); // NOI18N
        eraseRadioMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eraseRadioMenuItemActionPerformed(evt);
            }
        });
        operationMenu.add(eraseRadioMenuItem);

        buttonGroup1.add(chooseRadioMenuItem);
        chooseRadioMenuItem.setText("选择");
        chooseRadioMenuItem.setName("chooseRadioMenuItem"); // NOI18N
        chooseRadioMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chooseRadioMenuItemActionPerformed(evt);
            }
        });
        operationMenu.add(chooseRadioMenuItem);

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

        TilesetManager.setText(resourceMap.getString("TilesetManager.text")); // NOI18N
        TilesetManager.setName("TilesetManager"); // NOI18N
        TilesetManager.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TilesetManagerActionPerformed(evt);
            }
        });
        tilesetMenu.add(TilesetManager);

        tilesetCrossMenuItem.setText("图集设置");
        tilesetCrossMenuItem.setName("tilesetCrossMenuItem"); // NOI18N
        tilesetCrossMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tilesetCrossMenuItemActionPerformed(evt);
            }
        });
        tilesetMenu.add(tilesetCrossMenuItem);

        mainMenuBar.add(tilesetMenu);

        zoomMenu.setMnemonic('Z');
        zoomMenu.setText(resourceMap.getString("zoomMenu.text")); // NOI18N
        zoomMenu.setName("zoomMenu"); // NOI18N

        zoomInMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/soyostar/resources/15.PNG"))); // NOI18N
        zoomInMenuItem.setText(resourceMap.getString("jMenuItem1.text")); // NOI18N
        zoomInMenuItem.setName("zoomInMenuItem"); // NOI18N
        zoomInMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                zoomInMenuItemActionPerformed(evt);
            }
        });
        zoomMenu.add(zoomInMenuItem);

        zoomOutMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/soyostar/resources/14.PNG"))); // NOI18N
        zoomOutMenuItem.setText(resourceMap.getString("zoomOutMenuItem.text")); // NOI18N
        zoomOutMenuItem.setName("zoomOutMenuItem"); // NOI18N
        zoomOutMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                zoomOutMenuItemActionPerformed(evt);
            }
        });
        zoomMenu.add(zoomOutMenuItem);

        zoomReturnMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/soyostar/resources/92.PNG"))); // NOI18N
        zoomReturnMenuItem.setText(resourceMap.getString("zoomReturnMenuItem.text")); // NOI18N
        zoomReturnMenuItem.setName("zoomReturnMenuItem"); // NOI18N
        zoomReturnMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                zoomReturnMenuItemActionPerformed(evt);
            }
        });
        zoomMenu.add(zoomReturnMenuItem);

        mainMenuBar.add(zoomMenu);

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
        for (int i = 0; i<PluginLoader.getInstance().getToolPluginNum(); i++) {
            toolMenu.add(PluginLoader.getInstance().getToolPlugins().get(i));
        }

        dataEditorMenuItem.setText("数据编辑器");
        dataEditorMenuItem.setName("dataEditorMenuItem"); // NOI18N
        dataEditorMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dataEditorMenuItemActionPerformed(evt);
            }
        });
        toolMenu.add(dataEditorMenuItem);

        spriteEditorMenuItem.setText("动画编辑器");
        spriteEditorMenuItem.setName("spriteEditorMenuItem"); // NOI18N
        spriteEditorMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                spriteEditorMenuItemActionPerformed(evt);
            }
        });
        toolMenu.add(spriteEditorMenuItem);

        scriptEditorMenuItem.setText("脚本编辑器");
        scriptEditorMenuItem.setName("scriptEditorMenuItem"); // NOI18N
        scriptEditorMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                scriptEditorMenuItemActionPerformed(evt);
            }
        });
        toolMenu.add(scriptEditorMenuItem);

        setMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/soyostar/resources/34.PNG"))); // NOI18N
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
        for (int i = 0; i<PluginLoader.getInstance().getPluginPluginNum(); i++) {
            pluginMenu.add(PluginLoader.getInstance().getPluginPlugins().get(i));
        }

        pluginManagerMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/soyostar/resources/31.PNG"))); // NOI18N
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
        for (int i = 0; i<PluginLoader.getInstance().getSimPluginNum(); i++) {
            simulationMenu.add(PluginLoader.getInstance().getSimPlugins().get(i));
        }

        gameTestMenuItem.setText("游戏模拟器");
        gameTestMenuItem.setName("gameTestMenuItem"); // NOI18N
        simulationMenu.add(gameTestMenuItem);

        mainMenuBar.add(simulationMenu);

        helpMenu.setMnemonic('H');
        helpMenu.setText(resourceMap.getString("helpMenu.text")); // NOI18N
        helpMenu.setName("helpMenu"); // NOI18N
        for (int i = 0; i<PluginLoader.getInstance().getHelpPluginNum(); i++) {
            helpMenu.add(PluginLoader.getInstance().getHelpPlugins().get(i));
        }

        softLogMenuItem.setText(resourceMap.getString("softLogMenuItem.text")); // NOI18N
        softLogMenuItem.setName("softLogMenuItem"); // NOI18N
        softLogMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                softLogMenuItemActionPerformed(evt);
            }
        });
        helpMenu.add(softLogMenuItem);

        checkUpdateMenuItem.setText(resourceMap.getString("checkUpdateMenuItem.text")); // NOI18N
        checkUpdateMenuItem.setName("checkUpdateMenuItem"); // NOI18N
        checkUpdateMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkUpdateMenuItemActionPerformed(evt);
            }
        });
        helpMenu.add(checkUpdateMenuItem);

        UserMenuItem.setText(resourceMap.getString("userMenuItem.text")); // NOI18N
        UserMenuItem.setName("UserMenuItem"); // NOI18N
        UserMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UserMenuItemActionPerformed(evt);
            }
        });
        helpMenu.add(UserMenuItem);

        sendBugMenuItem.setText(resourceMap.getString("sendBugMenuItem.text")); // NOI18N
        sendBugMenuItem.setName("sendBugMenuItem"); // NOI18N
        sendBugMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendBugMenuItemActionPerformed(evt);
            }
        });
        helpMenu.add(sendBugMenuItem);

        helpMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
        helpMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/soyostar/resources/42.PNG"))); // NOI18N
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
            .addComponent(stateBarPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(mainSplitPane, javax.swing.GroupLayout.DEFAULT_SIZE, 972, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(toolBar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mainSplitPane, javax.swing.GroupLayout.DEFAULT_SIZE, 515, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(stateBarPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuItemActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_exitMenuItemActionPerformed
    private SoftLogDialog sld;
    private void softLogMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_softLogMenuItemActionPerformed
        // TODO add your handling code here:
        if (sld == null) {
            sld = new SoftLogDialog(this, false);
        }
        sld.setVisible(true);
    }//GEN-LAST:event_softLogMenuItemActionPerformed
    private HelpDialog hd;
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
            Desktop.getDesktop().browse(new URL("http://www.soyostar.com/forum.php").toURI());
        } catch (Exception e) {
            Log.getLogger(this.getClass()).error("打开软件首页异常！", e);
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

    private void sendBugMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendBugMenuItemActionPerformed
        // TODO add your handling code here:
        try {
            Desktop.getDesktop().browse(new URL("http://www.soyostar.com/forum.php?mod=forumdisplay&fid=76").toURI());
        } catch (Exception e) {
            Log.getLogger(this.getClass()).error("打开软件首页异常！", e);
        }
    }//GEN-LAST:event_sendBugMenuItemActionPerformed

    private void checkUpdateMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkUpdateMenuItemActionPerformed
        // TODO add your handling code here:
        try {
            Desktop.getDesktop().browse(new URL("http://www.soyostar.com/forum.php?mod=forumdisplay&fid=7").toURI());
        } catch (Exception e) {
            Log.getLogger(this.getClass()).error("打开软件首页异常！", e);
        }
    }//GEN-LAST:event_checkUpdateMenuItemActionPerformed

    private void helpMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_helpMenuItemActionPerformed
        // TODO add your handling code here:
        if (hd == null) {
            hd = new HelpDialog(this, true);
        }
        hd.setVisible(true);
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

    private void newProject() {
        if (npd == null) {
            npd = new NewProjectDialog(this, true);
        }
        npd.setVisible(true);
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
        if (SoftProxy.getInstance().getCurProject() == null) {
            return;
        }
        try {
            // TODO add your handling code here:
            saveProject();
        } catch (Exception ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_saveProjectMenuItemActionPerformed

    /**
     * 
     * @param path
     * @throws Exception
     */
    public void open(String path) throws Exception {
        closeProject();

        Project project = new Project();
        SoftProxy.getInstance().setCurProject(project);
        project.addProjectChangeListener(this);
        project.setPath(path);
//        System.out.println("project path:"+path);
        updateRecent(project.getPath());
        ded = new DataEditorDialog(this, true);//读取数据
        tscd = new TileSetCrossDialog(this, true);
        layerTable.updateUI();
        mapTree.updateUI();
        if (ProjectManager.get()) {
            this.setTitle(SoftInformation.chineseName + " - " + project.getName());
            System.out.println("load map start...");
            File mapDir = new File(project.getPath() + "\\softdata\\map");
            File[] maps = mapDir.listFiles();
            for (int i = 0; i < maps.length; i++) {
                if (maps[i].getName().endsWith(".gat")) {
                    System.out.println("Reading Map: " + maps[i].getName());
                    IMapReader mapReader = new DefaultSoftMapReader();
                    Map map = mapReader.readMap(maps[i].getPath());
                    map.addMapChangeListener(this);
                    MapRenderFactory.createMapRender(map);
                    PreviewRenderFactory.createPreviewRender(map);
                    project.addMap(map, map.getIndex());
                }
            }
            System.out.println("load map finish!");
            System.out.println("map size is :" + project.getMapCounts());
            System.out.println("npc size is :" + project.getNpcCounts());
            System.out.println("script size is :" + project.getScriptCounts());
            if (SoftProxy.getInstance().getCurProject().getMapCounts() != 0) {
                File f = new File(project.getPath() + "\\softdata\\map.xml");
                SAXReader reader = new SAXReader();
                Document doc = reader.read(f);
                Element root = doc.getRootElement();
                loadAllMap(root, (DefaultMutableTreeNode) mapTree.getModel().getRoot());
            }
            JOptionPane.showMessageDialog(this,
                "项目导入成功！");
        } else {
            JOptionPane.showMessageDialog(this, "警告",
                "项目导入失败！", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void openProject() {
        JFileChooser jf = new JFileChooser(new File("."));
        jf.setFileFilter(new Project.Filter());
        jf.showOpenDialog(this);
        File file = jf.getSelectedFile();
        try {
            if (file != null) {
                open(file.getParent());
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.getLogger(this.getClass()).error("openProject: " + file.getPath(), e);
            JOptionPane.showMessageDialog(this, e.getLocalizedMessage(),
                "项目导入失败！", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void loadAllMap(Element root, DefaultMutableTreeNode node) throws Exception {

        List childList = root.elements();
        for (int i = 0; i < childList.size(); i++) {
            //子节点的操作
            Element it = (Element) childList.get(i);
            List attrList = it.attributes();
            DefaultMutableTreeNode newNode = null;
            for (int j = 0; j < attrList.size(); j++) {
                //属性的取得
                Attribute item = (Attribute) attrList.get(j);
                newNode =
                    new DefaultMutableTreeNode(SoftProxy.getInstance().getCurProject().getMap(Integer.parseInt(item.getValue())));
//                if (mapTree.getSelectNode() == null) {
                node.add(newNode);
                ((DefaultTreeModel) mapTree.getModel()).reload(node);
//                } else {
//                    mapTree.getSelectNode().add(newNode);
//                    //更新树的数据模型
//                    ((DefaultTreeModel) mapTree.getModel()).reload(mapTree.getSelectNode());
//                }

                mapTree.expandPath(mapTree.getSelectionPath());
                mapTree.setSelectionPath(new TreePath(((DefaultTreeModel) mapTree.getModel()).getPathToRoot(newNode)));
                //设置维持当前的选择路径
                mapTree.setExpandsSelectedPaths(true);
            }
            if (it.elements().size() > 0) {
                loadAllMap(it, newNode);
            }

            //对子节点进行其它操作...
        }
    }
    private void saveProjectButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveProjectButtonActionPerformed
        try {
            // TODO add your handling code here:
            saveProject();
            JOptionPane.showMessageDialog(this,
                "项目保存成功！");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "警告",
                "项目保存失败！", JOptionPane.WARNING_MESSAGE);
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_saveProjectButtonActionPerformed

    private void saveProject() throws Exception {
        SoftProxy.getInstance().getCurProject().getDataManager().save();
        Iterator it = SoftProxy.getInstance().getCurProject().getMaps().keySet().iterator();
        while (it.hasNext()) {
            Integer key = (Integer) it.next();
            Map map = SoftProxy.getInstance().getCurProject().getMaps().get(key);
            if (map != null) {
                IMapWriter softMapWriter = new DefaultSoftMapWriter();
                softMapWriter.writeMap(map, SoftProxy.getInstance().getCurProject().getPath() + File.separatorChar
                    + "softdata" + File.separatorChar + "map" + File.separatorChar + "map" + map.getIndex() + ".gat");
                IMapWriter mapWriter = new DefaultMapWriter();
                mapWriter.writeMap(map, SoftProxy.getInstance().getCurProject().getPath() + File.separatorChar
                    + "data" + File.separatorChar + "map" + File.separatorChar + "map" + map.getIndex() + ".gat");
            }
        }
        Document doc = DocumentHelper.createDocument();
        Element project = doc.addElement(SoftProxy.getInstance().getCurProject().getName());
        saveAllChildrenMap(project, (DefaultMutableTreeNode) mapTree.getModel().getRoot());
        try {
            OutputFormat format = OutputFormat.createPrettyPrint();
            format.setEncoding("UTF-8");
            XMLWriter xmlw = new XMLWriter(new FileWriter(SoftProxy.getInstance().getCurProject().getPath() + "\\softdata\\map.xml"), format);
            xmlw.write(doc);
            xmlw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveAllChildrenMap(Element em, DefaultMutableTreeNode tn) throws IOException {

        for (int i = 0, n = tn.getChildCount(); i < n; i++) {
            DefaultMutableTreeNode dmt = (DefaultMutableTreeNode) tn.getChildAt(i);

            Map map = (Map) dmt.getUserObject();
            Element elem = em.addElement("Map");
            elem.setAttributeValue("ID", "" + map.getIndex());
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
        System.exit(0);
    }//GEN-LAST:event_exitPopMenuItemActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
        this.setVisible(false);
    }//GEN-LAST:event_formWindowClosed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        trayicon.displayMessage("信息", "程序最小化到托盘", TrayIcon.MessageType.INFO);
    }//GEN-LAST:event_formWindowClosing

    private void penButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_penButtonActionPerformed
        // TODO add your handling code here:
        data.currentPsType = SoftProxy.PS_PEN;
        penRadioMenuItem.setSelected(true);
    }//GEN-LAST:event_penButtonActionPerformed

    private void pourButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pourButtonActionPerformed
        // TODO add your handling code here:
        data.currentPsType = SoftProxy.PS_FILL;
        fillRadioMenuItem.setSelected(true);
    }//GEN-LAST:event_pourButtonActionPerformed

    private void eraseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eraseButtonActionPerformed
        // TODO add your handling code here:
        data.currentPsType = SoftProxy.PS_ERASER;
        eraseRadioMenuItem.setSelected(true);
    }//GEN-LAST:event_eraseButtonActionPerformed

    private void chooseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chooseButtonActionPerformed
        // TODO add your handling code here:
        data.currentPsType = SoftProxy.PS_CHOOSE;
        chooseRadioMenuItem.setSelected(true);
    }//GEN-LAST:event_chooseButtonActionPerformed

    private void addTilesetMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addTilesetMenuItemActionPerformed
        // TODO add your handling code here:
        if (SoftProxy.getInstance().getCurProject() == null) {
            JOptionPane.showMessageDialog(this,
                "请先新建项目！");
            return;
        }
        if (data.getCurrentMap() == null) {
            JOptionPane.showMessageDialog(this,
                "地图为空，请先新建地图！");
            return;
        }
        NewTileSetDialog ntsd = new NewTileSetDialog(this, true);
        ntsd.setVisible(true);
    }//GEN-LAST:event_addTilesetMenuItemActionPerformed

    private void undoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_undoButtonActionPerformed
        // TODO add your handling code here:
        if (SoftProxy.getInstance().getCurProject() == null) {
            return;
        }
        if (data.getCurrentMap() == null) {
            return;
        }
        data.getCurrentMap().getMapRender().getUndoHandler().undo();

    }//GEN-LAST:event_undoButtonActionPerformed

    private void redoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_redoButtonActionPerformed
        // TODO add your handling code here:
        if (SoftProxy.getInstance().getCurProject() == null) {
            return;
        }
        if (data.getCurrentMap() == null) {
            return;
        }
        data.getCurrentMap().getMapRender().getUndoHandler().redo();

    }//GEN-LAST:event_redoButtonActionPerformed
    private void newLayer() {
        if (SoftProxy.getInstance().getCurProject() == null) {
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
        if (SoftProxy.getInstance().getCurProject() == null) {
            JOptionPane.showMessageDialog(this,
                "请先新建项目！");
            return;
        }
        NewMapDialog nmd = new NewMapDialog(this, true);
        nmd.setVisible(true);
    }
    private void editMapMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editMapMenuItemActionPerformed
        // TODO add your handling code here:
        if (SoftProxy.getInstance().getCurProject() == null) {
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

    private void TilesetManagerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TilesetManagerActionPerformed
        // TODO add your handling code here:
        if (SoftProxy.getInstance().getCurProject() == null) {
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
    }//GEN-LAST:event_TilesetManagerActionPerformed

    private void removeMapButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeMapButtonActionPerformed
        // TODO add your handling code here:
        removeMap();
    }//GEN-LAST:event_removeMapButtonActionPerformed
    //删除指定节点下的所有节点地图

    private void removeMap() {
        if (mapTree.getSelectNode() != null && mapTree.getSelectNode().getParent() != null) {
//            ((DefaultMutableTreeNode) data.getMainFrame().mapTree.getModel().getRoot()).add(newNode);
            int id = JOptionPane.showConfirmDialog(this, "你确定要删除该地图吗？", "删除地图", JOptionPane.OK_CANCEL_OPTION);
            if ((id == JOptionPane.OK_OPTION) && (mapTree.getSelectNode().getUserObject() instanceof Map)) {
                ((DefaultTreeModel) mapTree.getModel()).removeNodeFromParent(mapTree.getSelectNode());
                removeAllChildrenMap(mapTree.getSelectNode());
                SoftProxy.getInstance().getCurProject().removeMap(((Map) mapTree.getSelectNode().getUserObject()).getIndex());
                System.out.println("size:" + SoftProxy.getInstance().getCurProject().getMapCounts());
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
            SoftProxy.getInstance().getCurProject().removeMap(((Map) (dmt).getUserObject()).getIndex());
        }
    }

    private void removeLayer() {
        if (SoftProxy.getInstance().getCurProject() == null) {
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
        data.setCurrentLayer(data.getCurrentMap().getLayer(layerTable.getSelectedRow()));
    }//GEN-LAST:event_layerTableMouseClicked

    private void gridMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gridMenuItemActionPerformed
        // TODO add your handling code here:
        if (SoftProxy.getInstance().getCurProject() == null) {
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
        if (SoftProxy.getInstance().getCurProject() == null) {
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
        if (SoftProxy.getInstance().getCurProject() == null) {
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
            System.out.println("放大");
        }
    }//GEN-LAST:event_zoomInMenuItemActionPerformed

    private void zoomOutMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_zoomOutMenuItemActionPerformed
        // TODO add your handling code here:
        if (data.getCurrentMap() == null) {
            return;
        }
        if (data.getCurrentMap().getMapRender().zoomOut()) {
            System.out.println("缩小");
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
        if (SoftProxy.getInstance().getCurProject() == null) {
            return;
        }
        SoftProxy.getInstance().getCurProject().removeProjectChangeListener(this);
        SoftProxy.getInstance().getCurProject().setPath("");
        SoftProxy.getInstance().getCurProject().setName("");
        SoftProxy.getInstance().getCurProject().removeAllMap();
        SoftProxy.getInstance().getCurProject().removeAllNpc();
        SoftProxy.getInstance().getCurProject().getDataManager().clearAllData();

        setTitle(SoftInformation.chineseName + " "
            + SoftInformation.majorVersion + "."
            + SoftInformation.minorVersion + "."
            + SoftInformation.modifiedVersion);
        if (ded != null) {
//            ded.dispose();
            ded = null;//置为null是为了再次打开数据编辑器时重新载入资源
        }
        if (tscd != null) {
//            tscd.dispose();
            tscd = null;
        }

//        updateRecent(SoftProxy.getInstance().getCurProject().getPath());
        ((DefaultMutableTreeNode) mapTree.getModel().getRoot()).removeAllChildren();
        mapScrollPane.setViewportView(null);
        data.setCurrentMap(null);
        data.setCurrentLayer(null);
        layerTable.updateUI();
        mapTree.updateUI();
        stateLabel.setText("");
        System.out.println("close project");
        SoftProxy.getInstance().setCurProject(null);
        System.gc();
    }
    private void penRadioMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_penRadioMenuItemActionPerformed
        // TODO add your handling code here:
        data.currentPsType = SoftProxy.PS_PEN;
        penButton.setSelected(true);
    }//GEN-LAST:event_penRadioMenuItemActionPerformed

    private void fillRadioMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fillRadioMenuItemActionPerformed
        // TODO add your handling code here:
        data.currentPsType = SoftProxy.PS_FILL;
        pourButton.setSelected(true);
    }//GEN-LAST:event_fillRadioMenuItemActionPerformed

    private void eraseRadioMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eraseRadioMenuItemActionPerformed
        // TODO add your handling code here:
        data.currentPsType = SoftProxy.PS_ERASER;
        eraseButton.setSelected(true);
    }//GEN-LAST:event_eraseRadioMenuItemActionPerformed

    private void chooseRadioMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chooseRadioMenuItemActionPerformed
        // TODO add your handling code here:
        data.currentPsType = SoftProxy.PS_CHOOSE;
        chooseButton.setSelected(true);
    }//GEN-LAST:event_chooseRadioMenuItemActionPerformed

    private void redoMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_redoMenuItemActionPerformed
        // TODO add your handling code here:
        if (SoftProxy.getInstance().getCurProject() == null) {
            return;
        }
        if (data.getCurrentMap() == null) {
            return;
        }
        data.getCurrentMap().getMapRender().getUndoHandler().redo();
    }//GEN-LAST:event_redoMenuItemActionPerformed

    private void undoMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_undoMenuItemActionPerformed
        // TODO add your handling code here:
        if (SoftProxy.getInstance().getCurProject() == null) {
            return;
        }
        if (data.getCurrentMap() == null) {
            return;
        }
        data.getCurrentMap().getMapRender().getUndoHandler().undo();
    }//GEN-LAST:event_undoMenuItemActionPerformed

    private void editMapButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editMapButtonActionPerformed
        // TODO add your handling code here:
        if (SoftProxy.getInstance().getCurProject() == null) {
            JOptionPane.showMessageDialog(this,
                "请先新建项目！");
            return;
        }
        if (data.getCurrentMap() == null) {
            JOptionPane.showMessageDialog(this,
                "地图为空，请先新建地图！");
            return;
        }
//        System.out.println("edit map");
        EditMapDialog emd = new EditMapDialog(this, true);
        emd.setVisible(true);
    }//GEN-LAST:event_editMapButtonActionPerformed
    /**
     * 
     */
    public DataEditorDialog ded;
    private void dataEditorMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dataEditorMenuItemActionPerformed
        // TODO add your handling code here:
        if (SoftProxy.getInstance().getCurProject() == null) {
            JOptionPane.showMessageDialog(this,
                "请先新建项目！");
            return;
        }
        if (ded == null) {
            ded = new DataEditorDialog(this, true);
        }
        ded.setVisible(true);
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
        if (sped == null) {
            sped = new SpriteEditorDialog(this, false);
        }
        sped.setVisible(true);
    }//GEN-LAST:event_spriteEditorMenuItemActionPerformed
    /**
     * 
     */
    public TileSetCrossDialog tscd;

    private void tilesetCrossMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tilesetCrossMenuItemActionPerformed
        // TODO add your handling code here:
        if (SoftProxy.getInstance().getCurProject() == null) {
            JOptionPane.showMessageDialog(this,
                "请先新建项目！");
            return;
        }
        if (tscd == null) {
            tscd = new TileSetCrossDialog(this, true);
        }
        tscd.setVisible(true);

    }//GEN-LAST:event_tilesetCrossMenuItemActionPerformed
    private ScriptEditorDialog sed;
    private void scriptEditorMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_scriptEditorMenuItemActionPerformed
        // TODO add your handling code here:
        if (sed == null) {
            sed = new ScriptEditorDialog(this, true);
        }
        sed.setVisible(true);
    }//GEN-LAST:event_scriptEditorMenuItemActionPerformed

    private void mapPreviewButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mapPreviewButtonActionPerformed
        // TODO add your handling code here:
        if (SoftProxy.getInstance().getCurProject() == null) {
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
    private void systemTray() {
        if (SystemTray.isSupported()) {
            SystemTray tray = SystemTray.getSystemTray();
            Image icon = this.getToolkit().getImage(this.getClass().getClassLoader().getResource("com/soyostar/resources/systemtray.png"));
            trayicon = new TrayIcon(icon, "手游之星游戏编辑器 - www.soyostar.com", trayPopMenu);
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
                Log.getLogger(this.getClass()).error("systemTray ", e);
            }
        }
    }

    private void setLAF(String s) {
        try {
            UIManager.setLookAndFeel(s);
            SwingUtilities.updateComponentTreeUI(this);//更新控件的外观
        } catch (Exception e) {
            Log.getLogger(this.getClass()).error("setLAF: " + s, e);
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
            Config.addToRecentFiles(filename);
        }

        java.util.List<String> files = Config.getRecentFiles();
        openRecentMenu.removeAll();
        if (!files.isEmpty()) {
            for (final String file : files) {
                JMenuItem menuItem = new JMenuItem(file);
                menuItem.addActionListener(new java.awt.event.ActionListener() {

                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        try {
                            open(file);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
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
     */
    public static void main(String args[]) {

        JDialog.setDefaultLookAndFeelDecorated(true);
        JFrame.setDefaultLookAndFeelDecorated(true);
        UIUtil.InitGlobalFont(new Font("微软雅黑", Font.PLAIN, 13));//全局字体设置
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                MainFrame thisClass = new MainFrame();
                thisClass.setLAF(Config.getSkin());
                thisClass.setVisible(true);
            }
        });
    }
    private SoftProxy data = SoftProxy.getInstance();
    private LayerTableModel ltm = new LayerTableModel();
//    private static String substance = "org.pushingpixels.substance.api.skin.SubstanceMarinerLookAndFeel";
//    private static String substance = "org.pushingpixels.substance.api.skin.SubstanceBusinessBlackSteelLookAndFeel";//这里修改默认皮肤
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem TilesetManager;
    private javax.swing.JMenuItem UserMenuItem;
    private javax.swing.JMenuItem aboutMenuItem;
    private javax.swing.JMenuItem addTilesetMenuItem;
    private javax.swing.ButtonGroup buttonGroup;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JMenuItem checkUpdateMenuItem;
    private javax.swing.JToggleButton chooseButton;
    private javax.swing.JRadioButtonMenuItem chooseRadioMenuItem;
    private javax.swing.JMenuItem closeProjectMenuItem;
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
    private javax.swing.JTabbedPane functionTabbedPane;
    private javax.swing.JMenuItem gameTestMenuItem;
    private javax.swing.JCheckBoxMenuItem gridMenuItem;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JMenuItem helpMenuItem;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
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
    private javax.swing.JMenuBar mainMenuBar;
    private javax.swing.JSplitPane mainSplitPane;
    private javax.swing.JPanel mapPane;
    private javax.swing.JPopupMenu mapPopupMenu;
    private javax.swing.JButton mapPreviewButton;
    public javax.swing.JScrollPane mapScrollPane;
    private javax.swing.JToolBar mapToolBar;
    public com.soyostar.widge.JMapTree mapTree;
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
    private javax.swing.JToggleButton penButton;
    private javax.swing.JRadioButtonMenuItem penRadioMenuItem;
    private javax.swing.JMenuItem pluginManagerMenuItem;
    private javax.swing.JMenu pluginMenu;
    private javax.swing.JToggleButton pourButton;
    private javax.swing.JButton redoButton;
    private javax.swing.JMenuItem redoMenuItem;
    private javax.swing.JButton removeLayerButton;
    private javax.swing.JMenuItem removeLayerMenuItem;
    private javax.swing.JButton removeMapButton;
    private javax.swing.JMenuItem removeMapMenuItem;
    private javax.swing.JButton saveProjectButton;
    private javax.swing.JMenuItem saveProjectMenuItem;
    private javax.swing.JMenuItem scriptEditorMenuItem;
    private com.soyostar.widge.JScrollLabel scrollLabel;
    private javax.swing.JMenuItem sendBugMenuItem;
    private javax.swing.JMenuItem setMenuItem;
    private javax.swing.JMenu simulationMenu;
    private javax.swing.JMenuItem softLogMenuItem;
    private javax.swing.JMenuItem spriteEditorMenuItem;
    private javax.swing.JCheckBoxMenuItem stateBarMenuItem;
    private javax.swing.JPanel stateBarPanel;
    public javax.swing.JLabel stateLabel;
    public com.soyostar.widge.TileSetTabbedPane tileSetTabbedPane;
    private javax.swing.JMenuItem tilesetCrossMenuItem;
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
//        stateLabel.repaint();
    }

    /**
     * 
     * @param e
     * @param layer
     */
    public void layerAdded(MapChangedEvent e, Layer layer) {
        layerTable.updateUI();
//        stateLabel.repaint();
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
//        stateLabel.repaint();
//        data.getCurrentMap().getPreviewRender().repaint();
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
//        data.getCurrentMap().getPreviewRender().repaint();
    }

    /**
     * 
     * @param event
     */
    public void layerChanged(LayerChangedEvent event) {
//        data.getCurrentMap().getMapRender().repaint();
//        data.getCurrentMap().getPreviewRender().repaint();
    }

    /**
     * 
     * @param event
     * @param nowBool
     */
    public void visibleChanged(LayerChangedEvent event, boolean nowBool) {
        data.getCurrentMap().getMapRender().repaint();
//        System.out.println();
//        data.getCurrentMap().getPreviewRender().repaint();
    }

    /**
     * 
     * @param event
     * @param oldName
     * @param newName
     */
    public void nameChanged(LayerChangedEvent event, String oldName, String newName) {
//        stateLabel.repaint();
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
//        System.out.println("Map add");
//        stateLabel.repaint();
    }

    /**
     * 
     * @param e
     * @param index
     */
    public void mapRemoved(ProjectChangedEvent e, int index) {
//        stateLabel.repaint();
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
                    File f = (File) iterator.next();
                    if (f.getName().equals("Project.xml")) {
                        open(f.getParent());
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

    public void npcAdded(ProjectChangedEvent e, Npc npc) {
    }

    public void npcRemoved(ProjectChangedEvent e, int index) {
    }

    public void scriptAdded(ProjectChangedEvent e, ScriptFile npc) {

    }

    public void scriptRemoved(ProjectChangedEvent e, int index) {

    }
}
