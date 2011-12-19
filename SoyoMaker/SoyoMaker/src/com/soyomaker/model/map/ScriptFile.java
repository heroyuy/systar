/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyomaker.model.map;

import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author Administrator
 */
public class ScriptFile {

    private int ID = -1;        //脚本的id
    private String contents = "";    //内容
    private String func = "";   //类方法

    /**
     * 
     * @return
     */
    public String getFunc() {
        return func;
    }

    /**
     * 
     * @param func
     */
    public void setFunc(String func) {
        this.func = func;
    }

    //组合类文件内容

    /**
     * 
     * @return
     */
    public String link() {
        contents = "package engine.script;\n"
            + "public class Script" + ID + " extends Script{\n"
            + "  public void execute(){\n" + func + "  }\n"
            + "}";
        return contents;
    }

    /**
     * 
     * @return
     */
    public String getContents() {
        return contents;
    }

    /**
     * 
     * @param contents
     */
    public void setContents(String contents) {
        this.contents = contents;
    }

    /**
     * 
     * @return
     */
    public int getIndex() {
        return ID;
    }

    /**
     * 
     * @param id
     */
    public void setIndex(int id) {
        this.ID = id;
    }
}
