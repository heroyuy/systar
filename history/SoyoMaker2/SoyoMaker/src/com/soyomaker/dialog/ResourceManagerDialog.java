/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ResourceManagerDialog.java
 *
 * Created on 2011-12-17, 1:29:13
 */
package com.soyomaker.dialog;

import com.soyomaker.AppData;
import com.soyomaker.util.FileUtil;
import com.soyomaker.widget.JFileChooserImagePreview;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;

/**
 *
 * @author Administrator
 */
public class ResourceManagerDialog extends javax.swing.JDialog {

    /** Creates new form ResourceManagerDialog
     * @param parent
     * @param modal
     */
    public ResourceManagerDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
    }
    private ArrayList<String> dirList = new ArrayList<String>();
    private HashMap<String, ArrayList<File>> fileMap = new HashMap<String, ArrayList<File>>();

    private void addFile(String key, File dir) {
        if (dir.isDirectory()) {
            ArrayList<File> files = new ArrayList<File>();
            files.addAll(Arrays.asList(dir.listFiles()));
            fileMap.put(key, files);
        }
    }

    private ArrayList<File> childDir(File dir) {
        ArrayList<File> files = new ArrayList<File>();
        if (dir.isDirectory()) {
            for (int i = 0; i < dir.listFiles().length; i++) {
                if (dir.listFiles()[i].isDirectory()) {
                    files.add(dir.listFiles()[i]);
                }
            }
        }
        return files;
    }

    private void addDir(File dir) {
        for (int i = 0; i < dir.listFiles().length; i++) {
            File fDir = dir.listFiles()[i];
            if (fDir.isDirectory()) {
                if (!childDir(fDir).isEmpty()) {
                    for (int j = 0; j < childDir(fDir).size(); j++) {
                        File sDir = childDir(fDir).get(j);
                        dirList.add(dir.getName() + File.separator + fDir.getName() + File.separator + sDir.getName());
                        addFile(dir.getName() + File.separator + fDir.getName() + File.separator + sDir.getName(), sDir);
                    }
                } else {
                    dirList.add(dir.getName() + File.separator + fDir.getName());
                    addFile(dir.getName() + File.separator + fDir.getName(), fDir);
                }
            }
        }
    }

    /**
     *
     */
    public void refresh() {
        dirList.clear();
        fileMap.clear();
        File audioDir = new File(AppData.getInstance().getCurProject().getPath() + File.separator + "audio");
        File imageDir = new File(AppData.getInstance().getCurProject().getPath() + File.separator + "image");
        addDir(imageDir);
        addDir(audioDir);
        dirNameList.setModel(new javax.swing.AbstractListModel() {

            public int getSize() {
                return dirList.size();
            }

            public Object getElementAt(int i) {
                return "●" + dirList.get(i);
            }
        });
        dirNameList.setSelectionInterval(0, 0);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSplitPane1 = new javax.swing.JSplitPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        dirNameList = new javax.swing.JList();
        jScrollPane2 = new javax.swing.JScrollPane();
        fileNameList = new javax.swing.JList();
        importButton = new javax.swing.JButton();
        previewButton = new javax.swing.JButton();
        closeButton = new javax.swing.JButton();
        removeButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("素材管理器");

        jSplitPane1.setResizeWeight(0.5);
        jSplitPane1.setName("jSplitPane1"); // NOI18N
        jSplitPane1.setOneTouchExpandable(true);

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        dirNameList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        dirNameList.setName("dirNameList"); // NOI18N
        dirNameList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                dirNameListValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(dirNameList);

        jSplitPane1.setLeftComponent(jScrollPane1);

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        fileNameList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        fileNameList.setName("fileNameList"); // NOI18N
        jScrollPane2.setViewportView(fileNameList);

        jSplitPane1.setRightComponent(jScrollPane2);

        importButton.setText("导入");
        importButton.setName("importButton"); // NOI18N
        importButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                importButtonActionPerformed(evt);
            }
        });

        previewButton.setText("预览");
        previewButton.setName("previewButton"); // NOI18N
        previewButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                previewButtonActionPerformed(evt);
            }
        });

        closeButton.setText("关闭");
        closeButton.setName("closeButton"); // NOI18N
        closeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeButtonActionPerformed(evt);
            }
        });

        removeButton.setText("删除");
        removeButton.setName("removeButton"); // NOI18N
        removeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 420, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(importButton)
                        .addComponent(closeButton))
                    .addComponent(removeButton)
                    .addComponent(previewButton))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(importButton)
                .addGap(18, 18, 18)
                .addComponent(removeButton)
                .addGap(18, 18, 18)
                .addComponent(previewButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 244, Short.MAX_VALUE)
                .addComponent(closeButton)
                .addContainerGap())
            .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 392, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void closeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeButtonActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_closeButtonActionPerformed

    private void removeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeButtonActionPerformed
        // TODO add your handling code here:
        try {
            FileUtil.removeFile(AppData.getInstance().getCurProject().getPath()
                    + File.separator + dirList.get(dirNameList.getSelectedIndex())
                    + File.separator + fileMap.get(dirList.get(dirNameList.getSelectedIndex())).get(fileNameList.getSelectedIndex()).getName());
            refresh();
        } catch (IOException ex) {
            AppData.getInstance().getLogger().e("删除文件失败！" + AppData.getInstance().getCurProject().getPath() + File.separator + dirList.get(dirNameList.getSelectedIndex()) + File.separator + fileNameList.getSelectedValue());
        }
    }//GEN-LAST:event_removeButtonActionPerformed

    private void importButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_importButtonActionPerformed
        // TODO add your handling code here:
        JFileChooser ch = new JFileChooser(".");
        JFileChooserImagePreview preview = new JFileChooserImagePreview(ch);
        ch.setFileSelectionMode(JFileChooser.FILES_ONLY);
        ch.addPropertyChangeListener(preview);
        ch.setAccessory(preview);
        int ret = ch.showOpenDialog(this);
        if (ret == JFileChooser.APPROVE_OPTION) {
            try {
                FileUtil.copyFile(ch.getSelectedFile(),
                        new File(AppData.getInstance().getCurProject().getPath()
                        + File.separator
                        + dirList.get(dirNameList.getSelectedIndex())
                        + File.separator
                        + ch.getSelectedFile().getName()));
                refresh();
            } catch (IOException ex) {
                AppData.getInstance().getLogger().e("复制文件失败！" + ch.getSelectedFile().getPath());
            }
        }
    }//GEN-LAST:event_importButtonActionPerformed
    private PreviewResourceDialog prd = new PreviewResourceDialog(this, true);
    private void previewButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_previewButtonActionPerformed
        // TODO add your handling code here:
        if (dirNameList.getSelectedIndex() != -1 && fileNameList.getSelectedIndex() != -1) {
            prd.setFile(new File(AppData.getInstance().getCurProject().getPath()
                    + File.separator + dirList.get(dirNameList.getSelectedIndex())
                    + File.separator + fileMap.get(dirList.get(dirNameList.getSelectedIndex())).get(fileNameList.getSelectedIndex()).getName()));
            prd.setVisible(true);
        }
    }//GEN-LAST:event_previewButtonActionPerformed

    private void dirNameListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_dirNameListValueChanged
        // TODO add your handling code here:
        fileNameList.setModel(new javax.swing.AbstractListModel() {

            public int getSize() {
                if (dirNameList.getSelectedIndex() < 0 || dirNameList.getSelectedIndex() > dirList.size() - 1) {
                    return 0;
                }
                return fileMap.get(dirList.get(dirNameList.getSelectedIndex())).size();
            }

            public Object getElementAt(int i) {
                return fileMap.get(dirList.get(dirNameList.getSelectedIndex())).get(i).getName();
            }
        });
    }//GEN-LAST:event_dirNameListValueChanged

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                ResourceManagerDialog dialog = new ResourceManagerDialog(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton closeButton;
    private javax.swing.JList dirNameList;
    private javax.swing.JList fileNameList;
    private javax.swing.JButton importButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JButton previewButton;
    private javax.swing.JButton removeButton;
    // End of variables declaration//GEN-END:variables
}
