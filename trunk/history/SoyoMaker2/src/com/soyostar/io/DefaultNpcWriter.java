/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyostar.io;

import com.soyostar.model.map.Npc;
import com.soyostar.model.map.NpcState;
import com.soyostar.model.map.ScriptFile;
import com.soyostar.proxy.SoftProxy;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;

/**
 *
 * @author Administrator
 */
public class DefaultNpcWriter implements INpcWriter {

    public void writeNpc(Npc npc, String filename) throws Exception {
        FileOutputStream fos = null;
        DataOutputStream dos = null;

        fos = new FileOutputStream(filename);
        dos = new DataOutputStream(fos);
        dos.writeInt(npc.getIndex());
        dos.writeInt(npc.getMapId());
        dos.writeInt(npc.getRow());
        dos.writeInt(npc.getCol());
        int n = npc.getNpcStates().size();
        System.out.println("npc序号：" + npc.getIndex());
//        System.out.println("npc地图序号：" + npc.getMapId());
//        System.out.println("npc初始行：" + npc.getRow());
//        System.out.println("npc初始列：" + npc.getCol());
        System.out.println("npc状态数：" + n);
        dos.writeInt(n);
        for (int i = 0; i < n; i++) {
            NpcState state = npc.getNpcState(i);
            dos.writeByte(state.getStartType());
            dos.writeUTF(state.getImgPath());
            dos.writeByte(state.getFace());
            dos.writeByte(state.getMove());
//            System.out.println("npc移动规则：" + state.getMove());
            dos.writeByte(state.getSpeed());
//            System.out.println("npc移动速度：" + state.getSpeed());
            dos.writeBoolean(state.isCross());
            ScriptFile sFile = state.getScript();
            sFile.link();//脚本文件预处理
            dos.writeInt(sFile.getIndex());
            System.out.println("脚本ID：" + sFile.getIndex());
            IScriptWriter scriptWriter = new DefaultScriptWriter();
            scriptWriter.compileScript(sFile, SoftProxy.getInstance().getCurProject().getPath() + File.separatorChar
                + "softdata" + File.separatorChar + "script" + File.separatorChar + "script" + sFile.getIndex() + ".java");
        }
        dos.close();
        fos.close();
    }
}
