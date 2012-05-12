package com.soyomaker.util;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFileChooser;

/**
 * 图片预览类<br>
 * 主要用于选择图片文件时在文件选择对话框进行图片预览
 * 
 * @author MaoJianWei
 * @created 2012-4-6
 * @version 1.0
 */
public class ImagePreviewer extends JComponent implements PropertyChangeListener {
    private ImageIcon mThumbnail = null;

    /**
     * 生成一个 ImagePreviewer新对象
     * 
     * @param fileChooser 文件选择器
     */
    public ImagePreviewer(JFileChooser fileChooser) {
        setPreferredSize(new Dimension(120, 50));
        fileChooser.addPropertyChangeListener(this);
    }

    /**
     * 加载图片文件
     * 
     * @param file 文件
     */
    public void loadImage(File file) {
        if (null == file) {
            mThumbnail = null;
        } else {
            ImageIcon tmpIcon = null;
            try {
                tmpIcon = new ImageIcon(ImageIO.read(file));
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (null == tmpIcon) {
                return;
            }
            if (tmpIcon.getIconWidth() > 120) {
                mThumbnail = new ImageIcon(tmpIcon.getImage().getScaledInstance(120, -1, Image.SCALE_FAST));
            } else {
                mThumbnail = tmpIcon;
            }
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent event) {
        String prop = event.getPropertyName();
        if (JFileChooser.SELECTED_FILE_CHANGED_PROPERTY.equals(prop)) {
            if (isShowing()) {
                loadImage((File) event.getNewValue());
                repaint();
            }
        }
    }

    @Override
    public void paint(Graphics graphics) {
        if (null != mThumbnail) {
            int iconWidth = getWidth() / 2 - mThumbnail.getIconWidth() / 2;
            int iconHeight = getHeight() / 2 - mThumbnail.getIconHeight() / 2;
            if (iconHeight < 0) {
                iconHeight = 0;
            }

            if (iconWidth < 5) {
                iconWidth = 5;
            }
            mThumbnail.paintIcon(this, graphics, iconWidth, iconHeight);
        }
    }
}
