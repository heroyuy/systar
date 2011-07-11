package com.soyostar.util;

import java.io.File;
import java.io.FileFilter;

public class IOFileFilterCreater implements FileFilter {

    private String ends; // 文件后缀

    public IOFileFilterCreater(String ends) { // 构造函数
        this.ends = ends; // 设置文件后缀
    }
    // 只显示符合扩展名的文件，目录全部显示

    public boolean accept(File file) {
        if (file.isDirectory()) {
            return true;
        }
        String fileName = file.getName();
        if (fileName.toUpperCase().endsWith(this.ends.toUpperCase())) {
            return true;
        }
        return false;
    }

    // 返回这个扩展名过滤器的扩展名
    public String getEnds() {
        return this.ends;
    }
}
