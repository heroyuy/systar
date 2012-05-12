/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyomaker.io.map;

import com.soyomaker.model.map.ScriptFile;
import java.io.BufferedReader;
import java.io.FileReader;

/**
 *
 * @author Administrator
 */
public class DefaultScriptBinaryReader implements IScriptReader {

    /**
     * 
     * @param filename
     * @return
     * @throws Exception
     */
    public ScriptFile readScript(String filename) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String str = "";
        String r = br.readLine();
        while (r != null) {
            str += r;
            r = br.readLine();
        }
        ScriptFile script = new ScriptFile();
        script.setContents(str);
        return script;
    }
}
