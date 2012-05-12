/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyomaker.log;

/**
 *
 * @author like
 */
public class DefaultLogDisplayer implements ILogDisplayer {

    @Override
    public void displayLog(Log log) {
        System.out.println(log.getContent().toString());
    }
}
