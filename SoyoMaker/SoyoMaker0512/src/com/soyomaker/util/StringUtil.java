/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyomaker.util;

/**
 *
 * @author Administrator
 */
public class StringUtil {

    /**
     *   中文字符检验
     *   @param   s   String   
     *   @return   包含中文字符返回true,否则返回false
     */
    public static boolean chechChinese(String s) {
        int length = s.length();
        byte[] b;
        for (int i = 0; i < length; i++) {
            b = s.substring(i).getBytes();
            if ((b[0] & 0xff) > 128) {
                return true;
            }
        }
        return false;
    }
}
