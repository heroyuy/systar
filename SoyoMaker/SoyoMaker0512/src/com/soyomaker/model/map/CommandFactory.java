/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyomaker.model.map;

/**
 *
 * @author Administrator
 */
public class CommandFactory {

    /**
     * 命令工厂类，根据传入的命令id，返回相应的Command
     * @param type
     * @return
     */
    public static Command createCommand(int type) {
        Command command = new Command();
        command.setScriptId(type);
        return command;
    }
}
