package com.soyomaker.util;

import java.io.File;
import java.util.Hashtable;
import javax.swing.Icon;
import javax.swing.filechooser.FileView;

/**
 * 图片文件视图类
 * 
 * @author MaoJianWei
 * @created 2012-4-6
 */
@SuppressWarnings("rawtypes")
public class ImageFileView extends FileView {
    private Hashtable icons = new Hashtable(5);
    private Hashtable fileDescriptions = new Hashtable(5);
    private Hashtable typeDescriptions = new Hashtable(5);

    /**
     * 添加文件描述
     * 
     * @param file 文件
     * @param fileDescription 描述
     */
    @SuppressWarnings("unchecked")
    public void putDescription(File file, String fileDescription) {
        fileDescriptions.put(fileDescription, file);
    }

    /**
     * 添加类型描述
     * 
     * @param extension 后缀
     * @param typeDescription 类型描述
     */
    @SuppressWarnings("unchecked")
    public void putTypeDescription(String extension, String typeDescription) {
        typeDescriptions.put(typeDescription, extension);
    }

    /**
     * 添加文件类型描述
     * 
     * @param file 文件
     * @param typeDescription 类型描述
     */
    public void putTypeDescription(File file, String typeDescription) {
        putTypeDescription(getExtension(file), typeDescription);
    }

    /**
     * 获取文件后缀
     * 
     * @param file 文件
     * @return 后缀名
     */
    public String getExtension(File file) {
        String name = file.getName();
        if (name != null) {
            int extensionIndex = name.lastIndexOf('.');
            if (extensionIndex < 0) {
                return null;
            }
            return name.substring(extensionIndex + 1).toLowerCase();
        }
        return null;
    }

    /**
     * 添加图标
     * 
     * @param extension 后缀
     * @param icon 图标
     */
    @SuppressWarnings("unchecked")
    public void putIcon(String extension, Icon icon) {
        icons.put(extension, icon);
    }

    /**
     * 是否隐藏
     * 
     * @param file 文件
     * @return 若是则返回true，否则返回false
     */
    public Boolean isHidden(File file) {
        String name = file.getName();
        if (name != null && !name.equals("") && name.charAt(0) == '.') {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    @Override
    public String getName(File file) {
        return null == file ? null : file.getName();
    }
    
    @Override
    public String getDescription(File f) {
        return (String) fileDescriptions.get(f);
    }
    
    @Override
    public String getTypeDescription(File f) {
        return (String) typeDescriptions.get(getExtension(f));
    }
    
    @Override
    public Icon getIcon(File f) {
        Icon icon = null;
        String extension = getExtension(f);
        if (extension != null) {
            icon = (Icon) icons.get(extension);
        }
        return icon;
    }
    
    @Override
    public Boolean isTraversable(File f) {
        return null; // Use default from FileSystemView
    }
}
