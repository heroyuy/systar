/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyomaker.io.map;

import com.soyomaker.model.map.Command;
import com.soyomaker.model.map.Script;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

/**
 *
 * @author Administrator
 */
public class DefaultSoftScriptBinaryReader implements IScriptReader {

    private void readCommand(DataInputStream dis, Command command) throws IOException {
        command.setScriptId(dis.readInt());
        int nn = dis.readInt();
        for (int j = 0; j < nn; j++) {
            command.addParameter(dis.readUTF());
        }
        int n = dis.readInt();
        for (int j = 0; j < n; j++) {
            Command command2 = new Command();
            readCommand(dis, command2);
            command.addCommand(command2);
        }
    }

    public Script readScript(String filename) throws Exception {
        DataInputStream dis = null;
        FileInputStream fis = null;
        Script script = new Script();
        fis = new FileInputStream(filename);
        dis = new DataInputStream(fis);
        script.setIndex(dis.readInt());
        int n = dis.readInt();
        for (int i = 0; i < n; i++) {
            Command command = new Command();
            readCommand(dis, command);
            script.addCommand(command);
        }
        dis.close();
        fis.close();
        return script;
    }
}
