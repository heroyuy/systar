/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyomaker.io.map;

import com.soyomaker.model.map.Command;
import com.soyomaker.model.map.NpcState;
import com.soyomaker.model.map.NpcStateCondition;
import com.soyomaker.model.map.Script;
import java.io.FileWriter;
import java.io.PrintWriter;

/**
 *
 * @author Administrator
 */
public class DefaultScriptLuaWriter implements IScriptWriter {

    private void printlnCommand(PrintWriter pw, Command command) {
        switch (command.getScriptId()) {
            case Command.IF:
                pw.println(command.getContent());
                for (int j = 0; j < command.commandSize(); j++) {
                    Command command2 = command.getCommand(j);
                    printlnCommand(pw, command2);
                }
                pw.println("end");
                break;
            case Command.WHILE:
                pw.println(command.getContent());
                for (int j = 0; j < command.commandSize(); j++) {
                    Command command2 = command.getCommand(j);
                    printlnCommand(pw, command2);
                }
                pw.println("end");
                break;
            default:
                pw.println(command.getContent());
                break;
        }
    }
    private String[] operateType = {"!=", "<=", "<", ">=", ">", "=="};

    public void writeScript(Script file, String filename) throws Exception {
        FileWriter fw = new FileWriter(filename);
        PrintWriter pw = new PrintWriter(fw);
        NpcState npcState = file.getNpcState();
        NpcStateCondition varCondition = npcState.getVarCondition();
        if (varCondition != null) {
            pw.append("if ").
                    append("Interpreter:doCommand(102003," + varCondition.paramList[0] + ")").
                    append(operateType[varCondition.paramList[1]]).append("" + varCondition.paramList[2]).append(" then");
            pw.println();
            pw.println("return");
            pw.println("end");
        }
        NpcStateCondition switchCondition = npcState.getSwitchCondition();
        if (switchCondition != null) {
            pw.append("if ").append(switchCondition.paramList[1] == 0 ? "not" : "").
                    append("Interpreter:doCommand(102001," + switchCondition.paramList[0] + ")").
                    append(" then");
            pw.println();
            pw.println("return");
            pw.println("end");
        }
        for (int i = 0; i < file.commandsSize(); i++) {
            Command command = file.getCommand(i);
            printlnCommand(pw, command);
        }
        pw.flush();
        pw.close();
        fw.close();
    }
}
