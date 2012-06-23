/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyomaker.infomation;

/**
 *
 * @author 软件信息
 */
public class SoftInformation {

    /**
     * 
     */
    public static final String chineseName = "SoyoMaker游戏编辑器";//软件中文名
    /**
     * 
     */
    public static final String author = "SM工作室";//软件作者
    /**
     * 
     */
    public static final int majorVersion = 0;//主版本号：主版本号1位，只有当系统在结构和功能上有重大突破改进后才发生变化
    /**
     * 
     */
    public static final int minorVersion = 9;//次版本号：次版本号有2位，偶数表示稳定版，奇数表示开发版，数据结构或者较大修改，增加此版本数
    /**
     *
     */
    public static final int lastVersion = 0;//尾版本号：尾版本号有2位，一般修改，增加此版本数
    /**
     * 
     */
    public static final int modifiedVersion = 20120624;//修改号：修改号8位，采用提交时的日期，当系统进行任何修改后，修改号都要随之改变
    /**
     *
     */
    public static final String extraVersion = "";//额外版本号：比如圣诞特别版，内部测试版，公测版等

    /**
     *
     * @return
     */
    public static String getVersion() {
        return SoftInformation.majorVersion + "."
                + SoftInformation.minorVersion + "."
                + SoftInformation.lastVersion + "."
                + SoftInformation.modifiedVersion + SoftInformation.extraVersion;
    }
}
