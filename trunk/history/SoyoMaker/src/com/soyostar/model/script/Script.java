/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyostar.model.script;

import java.util.ArrayList;

/**
 *
 * @author Administrator
 */
public class Script {

    private ArrayList<String> codes = new ArrayList<String>();

    public void addCode(String str) {
        codes.add(str);
    }

    public void removeCode(int id) {
        codes.remove(id);
    }
}
