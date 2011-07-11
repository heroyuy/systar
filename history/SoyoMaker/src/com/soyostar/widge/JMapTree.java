/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyostar.widge;

import com.soyostar.proxy.Proxy;
import com.soyostar.model.map.Map;
import java.io.File;
import java.util.Enumeration;
import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTree;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeExpansionListener;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

/**
 *
 * @author Administrator
 */
public class JMapTree extends JTree {
    //用来记录当前选中节点的成员变量
    //创建根节点

    private DefaultMutableTreeNode dmtnRoot = new DefaultMutableTreeNode("项目地图");
    //创建树的数据模型
    private DefaultTreeModel dtm = new DefaultTreeModel(dmtnRoot);
    private DefaultMutableTreeNode selectNode;

    public JMapTree() {
        super();
        init();
    }

    public DefaultMutableTreeNode getSelectNode() {
        return selectNode;
    }

    public void setSelectNode(DefaultMutableTreeNode selectNode) {
        this.selectNode = selectNode;
    }

    public DefaultMutableTreeNode findUserObject(Map obj) {
        // find the node containing a user object
        Enumeration e = ((DefaultMutableTreeNode) getModel().getRoot()).breadthFirstEnumeration();
        while (e.hasMoreElements()) {
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) e.nextElement();
            if (node.getUserObject().equals(obj)) {
                return node;
            }
        }
        return null;
    }

    private void init() {
//        DefaultTreeCellRenderer renderer = new DefaultTreeCellRenderer();
//        renderer.setLeafIcon(new ImageIcon(this.getClass().getResource("/com/soyostar/resources/1.PNG")));
//        renderer.setClosedIcon(new ImageIcon(this.getClass().getResource("/com/soyostar/resources/1.PNG")));
//        renderer.setOpenIcon(new ImageIcon(this.getClass().getResource("/com/soyostar/resources/1.PNG")));
//        setCellRenderer(renderer);
        MapTreeListener mtl = new MapTreeListener();
        //为JTree注册数据模型更改事件的监听器
        dtm.addTreeModelListener(mtl);
        //为JTree注册选择事件的监听器
        this.addTreeSelectionListener(mtl);
        //为JTree注册选择展开以及折叠事件的监听器
        this.addTreeExpansionListener(mtl);

        this.setModel(dtm);
//        this.setRootVisible(false);
        this.setShowsRootHandles(true);
    }

    private class MapTreeListener implements TreeExpansionListener,
        TreeModelListener, TreeSelectionListener {
        //定义用来记录路径的节点数组

        private Object[] tempNodes;
        //创建StringBuffer对象
        private StringBuffer tempMsg = new StringBuffer();
        //声明用来记录路径TreePath
        private TreePath tp;
        //处理选中某节点后发生事件的方法
        private Proxy data = Proxy.getInstance();

        public void valueChanged(TreeSelectionEvent tse) {
            //获得根节点到选中节点的路径
            tp = tse.getNewLeadSelectionPath();
            if (tp != null) {
                //从路径中获得所有路径中的节点
                tempNodes = tp.getPath();
                //循环对路径中的每个节点进行处理
                for (int i = 0; i < tempNodes.length; i++) {
                    //将各个节点的内容连接起来并添加到StringBuffer中
                    tempMsg.append(tempNodes[i]);
                    if (i != tempNodes.length - 1) {//在各个节点中间添加“>>”符号
                        tempMsg.append(">>");
                    }
                }
                //在文本框中显示相应的信息
                System.out.println("您选择了：“" + tempMsg.toString() + "”节点！！！");
                //删除StringBuffer中的所有内容
                tempMsg.delete(0, tempMsg.length());
                //记录选中的节点
                selectNode = (DefaultMutableTreeNode) tp.getLastPathComponent();
                System.out.println("ChildCount:" + selectNode.getChildCount());
                if (selectNode.getUserObject() instanceof Map) {
                    data.setCurrentMap((Map) selectNode.getUserObject());
                    data.setCurrentLayerIndex(0);
                    data.getMainFrame().mapScrollPane.setViewportView(data.getCurrentMap().getMapRender());
//                    data.getMainFrame().previewScrollPane.setViewportView(data.getCurrentMap().getPreviewRender());

                    data.getCurrentMap().getMapRender().repaint();
//                    data.getCurrentMap().getPreviewRender().repaint();
                }

            }
        }
        //处理节点折叠后发生事件的方法

        public void treeCollapsed(TreeExpansionEvent tee) {
            //获得根节点到选中节点的路径
            tp = tee.getPath();
            //设置文本框显示相应的信息
            System.out.println("您将" + tp.getLastPathComponent() + "节点折叠了！！！");
        }
        //处理节点展开后发生事件的方法

        public void treeExpanded(TreeExpansionEvent tee) {
            //获得根节点到选中节点的路径
            tp = tee.getPath();
            //设置文本框显示相应的信息
            System.out.println("您将“" + tp.getLastPathComponent() + "”节点展开了！！！");
        }
        //处理当节点名称更改之后发生事件的方法

        public void treeNodesChanged(TreeModelEvent tme) {
            //设置文本框显示节点名称更改信息
            System.out.println("您将节点的标题修改了！！！");
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
