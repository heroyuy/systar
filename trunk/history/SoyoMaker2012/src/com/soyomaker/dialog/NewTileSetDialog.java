/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * NewTileSetDialog.java
 *
 * Created on 2011-3-19, 22:15:09
 */
package com.soyomaker.dialog;

import com.soyomaker.AppData;
import com.soyomaker.config.Preference;
import com.soyomaker.model.map.Pass;
import com.soyomaker.model.map.TileSet;
import com.soyomaker.util.TileCutter;
import com.soyomaker.widget.JFileChooserImagePreview;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author Administrator
 */
public class NewTileSetDialog extends javax.swing.JDialog {

    /** Creates new form NewTileSetDialog
     * @param parent 
     * @param modal 
     */
    public NewTileSetDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        initialize();
    }

    private void initialize() {
        setLocationRelativeTo(null);
        getRootPane().setDefaultButton(okButton);
        if (!Preference.getIsAutoTile()) {
            autoTileCheckBox.setEnabled(false);
            autoTileLabel.setText("<html> <body> 此功能暂时关闭 </body> </html>");
        } else {
            autoTileCheckBox.setEnabled(true);
            autoTileLabel.setText("<html> <body> 注意：暂时只支持RMXP格式的自动元件，选中表示此图集为自动图集，非自动图集请勿选中! </body> </html>");
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

        jLabel1 = new javax.swing.JLabel();
        tilesetNameTextField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        tilesetPathTextField = new javax.swing.JTextField();
        okButton = new javax.swing.JButton();
        cancleButton = new javax.swing.JButton();
        pathButton = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        autoTileLabel = new javax.swing.JLabel();
        autoTileCheckBox = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance().getContext().getResourceMap(NewTileSetDialog.class);
        setTitle(resourceMap.getString("title")); // NOI18N
        setResizable(false);

        jLabel1.setText("图集名称");
        jLabel1.setName("jLabel1"); // NOI18N

        tilesetNameTextField.setName("tilesetNameTextField"); // NOI18N

        jLabel2.setText("图集路径");
        jLabel2.setName("jLabel2"); // NOI18N

        tilesetPathTextField.setName("tilesetPathTextField"); // NOI18N

        okButton.setText("确定");
        okButton.setName("okButton"); // NOI18N
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });

        cancleButton.setText("取消");
        cancleButton.setName("cancleButton"); // NOI18N
        cancleButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancleButtonActionPerformed(evt);
            }
        });

        pathButton.setText("...");
        pathButton.setName("pathButton"); // NOI18N
        pathButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pathButtonActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("设置"));
        jPanel1.setName("jPanel1"); // NOI18N

        autoTileLabel.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        autoTileLabel.setText("<html> <body> 注意：暂时只支持RMXP格式的自动元件，选中表示此图集为自动图集，非自动图集请勿选中! </body> </html>"); // NOI18N
        autoTileLabel.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        autoTileLabel.setName("autoTileLabel"); // NOI18N
        autoTileLabel.setVerticalTextPosition(javax.swing.SwingConstants.TOP);

        autoTileCheckBox.setText("自动元件");
        autoTileCheckBox.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        autoTileCheckBox.setName("autoTileCheckBox"); // NOI18N
        autoTileCheckBox.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        autoTileCheckBox.setVerticalTextPosition(javax.swing.SwingConstants.TOP);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(autoTileCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(autoTileLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 271, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(autoTileCheckBox)
                    .addComponent(autoTileLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(tilesetPathTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(pathButton, javax.swing.GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE))
                            .addComponent(tilesetNameTextField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 314, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(okButton, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cancleButton, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {cancleButton, okButton});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(tilesetNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(tilesetPathTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pathButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancleButton)
                    .addComponent(okButton))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
        // TODO add your handling code here:
        String[] names = tilesetNameTextField.getText().split("\\|");
        String[] paths = tilesetPathTextField.getText().split("\\|");
        for (int n = 0; n < paths.length; n++) {
            TileSet newTileset = new TileSet();
            String name, path;
            if (names[n].equals("")) {
                name = "未命名";
            } else {
                name = names[n];
            }
            if (paths[n].equals("")) {
                JOptionPane.showMessageDialog(this, "路径不能为空！");
                return;
            } else {
                path = paths[n];
            }
            newTileset.setName(name);
            newTileset.setAutoTile(autoTileCheckBox.isSelected());
            newTileset.setMap(data.getCurrentMap());
            try {
                newTileset.importTileBitmap(path, new TileCutter(data.getCurrentMap().getTileWidth(), data.getCurrentMap().getTileHeight()));
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, ex.getLocalizedMessage(),
                        "加载图集失败！" + paths[n], JOptionPane.WARNING_MESSAGE);
            }
            Pass pic = data.getMainFrame().tscd.initCrossFile(path.substring(path.lastIndexOf(File.separatorChar) + 1),
                    new Rectangle(data.getCurrentMap().getTileWidth(), data.getCurrentMap().getTileHeight()));
            if (pic != null) {
                for (int i = 0; i < pic.collides.length; i++) {
                    for (int j = 0; j < pic.collides[0].length; j++) {
                        newTileset.getTile(i + pic.collides.length * j).setCross(!pic.collides[i][j]);
                    }
                }
            }
            if (data.addTileSet(newTileset)) {
                System.out.println("加载图元" + newTileset.getName() + "成功!");
            } else {
                JOptionPane.showMessageDialog(this, "添加图集失败！" + paths[n]);
            }
        }
        dispose();
    }//GEN-LAST:event_okButtonActionPerformed
    private AppData data = AppData.getInstance();
    private void cancleButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancleButtonActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_cancleButtonActionPerformed

    private void pathButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pathButtonActionPerformed
        // TODO add your handling code here:
        JFileChooser ch = new JFileChooser(AppData.getInstance().getCurProject().getPath() + File.separatorChar + "image" + File.separatorChar + "tileset");
        ch.setMultiSelectionEnabled(true);
        JFileChooserImagePreview preview = new JFileChooserImagePreview(ch);
        ch.addPropertyChangeListener(preview);
        ch.setAccessory(preview);
        int ret = ch.showOpenDialog(this);
        if (ret == JFileChooser.APPROVE_OPTION) {
            File[] files = ch.getSelectedFiles();
            StringBuilder sbFileName = new StringBuilder();
            for (int i = 0; i < files.length; i++) {
                sbFileName.append(files[i].getName());
                if (i != files.length - 1) {
                    sbFileName.append("|");
                }
            }
            StringBuilder sbFilePath = new StringBuilder();
            for (int i = 0; i < files.length; i++) {
                sbFilePath.append(files[i].getAbsolutePath());
                if (i != files.length - 1) {
                    sbFilePath.append("|");
                }
            }
            tilesetPathTextField.setText(sbFilePath.toString());
            tilesetNameTextField.setText(sbFileName.toString());
        }
//        JFileChooser ch = new JFileChooser(AppData.getInstance().getCurProject().getPath() + File.separatorChar + "image" + File.separatorChar + "tileset");
//        JFileChooserImagePreview preview = new JFileChooserImagePreview(ch);
//        ch.addPropertyChangeListener(preview);
//        ch.setAccessory(preview);
//        int ret = ch.showOpenDialog(this);
//        if (ret == JFileChooser.APPROVE_OPTION) {
//            tilesetPathTextField.setText(ch.getSelectedFile().getAbsolutePath());
//            int i = ch.getSelectedFile().getAbsolutePath().lastIndexOf(File.separator);
//            tilesetNameTextField.setText(ch.getSelectedFile().getAbsolutePath().substring(i + 1));
//        }
    }//GEN-LAST:event_pathButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                NewTileSetDialog dialog = new NewTileSetDialog(new javax.swing.JFrame(), true);
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
    private javax.swing.JCheckBox autoTileCheckBox;
    private javax.swing.JLabel autoTileLabel;
    private javax.swing.JButton cancleButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton okButton;
    private javax.swing.JButton pathButton;
    private javax.swing.JTextField tilesetNameTextField;
    private javax.swing.JTextField tilesetPathTextField;
    // End of variables declaration//GEN-END:variables
}