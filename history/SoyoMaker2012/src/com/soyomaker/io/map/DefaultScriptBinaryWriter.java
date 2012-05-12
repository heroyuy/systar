/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyomaker.io.map;

import com.soyomaker.model.map.ScriptFile;
import java.io.FileWriter;
import java.io.PrintWriter;

/**
 *
 * @author Administrator
 */
public class DefaultScriptBinaryWriter implements IScriptWriter {

    /**
     * 
     * @param file
     * @param filename
     * @throws Exception
     */
    public void compileScript(ScriptFile file, String filename) throws Exception {
        FileWriter fw = new FileWriter(filename);
        PrintWriter pw = new PrintWriter(fw);
        String[] s = file.getContents().split("\n");
        for (int i = 0, n = s.length; i < n; i++) {
            pw.println(s[i]);
        }
        pw.flush();
        pw.close();
        fw.close();
    }
}
