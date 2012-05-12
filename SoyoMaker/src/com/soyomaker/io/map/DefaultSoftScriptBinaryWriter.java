/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyomaker.io.map;

import com.soyomaker.model.map.Command;
import com.soyomaker.model.map.Script;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * @author Administrator
 */
public class DefaultSoftScriptBinaryWriter implements IScriptWriter {

    private void writeCommand(DataOutputStream dos, Command command) throws IOException {
        dos.writeInt(command.getScriptId());
        dos.writeInt(command.parametersSize());
        for (int j = 0; j < command.parametersSize(); j++) {
            dos.writeUTF(command.getParameter(j));
        }
        dos.writeInt(command.commandSize());
        for (int j = 0; j < command.commandSize(); j++) {
            writeCommand(dos, command.getCommand(j));
        }
    }

    public void writeScript(Script file, String filename) throws Exception {
        FileOutputStream fos = null;
        DataOutputStream dos = null;

        fos = new FileOutputStream(filename);
        dos = new DataOutputStream(fos);
        dos.writeInt(file.getIndex());
        dos.writeInt(file.commandsSize());
        for (int i = 0; i < file.commandsSize(); i++) {
            Command command = file.getCommand(i);
            writeCommand(dos, command);
        }
        dos.close();
        fos.close();
    }
}
