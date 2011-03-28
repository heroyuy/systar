/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyostar.util;

import org.apache.log4j.Logger;

/**
 *
 * @author 日志类
 */
public class Log {

    public static Logger getLogger(Class cla) {
        return Logger.getLogger(cla.getName());
    }
}
