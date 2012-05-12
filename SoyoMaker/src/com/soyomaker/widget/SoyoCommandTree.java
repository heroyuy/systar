/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyomaker.widget;

import javax.swing.JTree;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeExpansionListener;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

/**
 *
 * @author Administrator
 */
public class SoyoCommandTree extends JTree {

    private DefaultMutableTreeNode selectNode = null;
    //创建根节点
    private DefaultMutableTreeNode dmtnRoot = new DefaultMutableTreeNode("命令");
    //创建树的数据模型
    private DefaultTreeModel dtm = new DefaultTreeModel(dmtnRoot);

    /**
     *
     * @return
     */
    public DefaultMutableTreeNode getSelectNode() {
        return selectNode;
    }

    /**
     *
     * @param selectNode
     */
    public void setSelectNode(DefaultMutableTreeNode selectNode) {
        this.selectNode = selectNode;
    }

    /**
     *
     */
    public SoyoCommandTree() {
        super();
        init();
    }

    private void init() {
        CommandTreeListener mtl = new CommandTreeListener();
        //为JTree注册数据模型更改事件的监听器
        dtm.addTreeModelListener(mtl);
        //为JTree注册选择事件的监听器
        this.addTreeSelectionListener(mtl);
        //为JTree注册选择展开以及折叠事件的监听器
        this.addTreeExpansionListener(mtl);
        this.setModel(dtm);
        this.setShowsRootHandles(true);
    }

    private class CommandTreeListener implements TreeExpansionListener,
            TreeModelListener, TreeSelectionListener {

        //声明用来记录路径TreePath
        private TreePath tp;
        //处理选中某节点后发生事件的方法
//        private AppData data = AppData.getInstance();

        public void valueChanged(TreeSelectionEvent tse) {
            //获得根节点到选中节点的路径
            tp = tse.getNewLeadSelectionPath();
            if (tp != null) {
                //记录选中的节点
                selectNode = (DefaultMutableTreeNode) tp.getLastPathComponent();
//                if ((selectNode.getUserObject() instanceof Command) && (selectNode.getUserObject() != null)) {
//                    System.out.println(selectNode.getUserObject());
//                }
            }
        }
        //处理节点折叠后发生事件的方法

        public void treeCollapsed(TreeExpansionEvent tee) {
            //获得根节点到选中节点的路径
            tp = tee.getPath();
        }
        //处理节点展开后发生事件的方法

        public void treeExpanded(TreeExpansionEvent tee) {
            //获得根节点到选中节点的路径
            tp = tee.getPath();
        }
        //处理当节点名称更改之后发生事件的方法

        public void treeNodesChanged(TreeModelEvent tme) {
            //设置文本框显示节点名称更改信息
        }
        //以下三个方法由于本例中没有使用，所以均为空实现

        public void treeStructureChanged(TreeModelEvent tme) {
        }

        public void treeNodesRemoved(TreeModelEvent tme) {
        }

        public void treeNodesInserted(TreeModelEvent tme) {
        }
    }
}
