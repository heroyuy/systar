/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyomaker.io.map;

import com.soyomaker.AppData;
import com.soyomaker.model.map.Npc;
import com.soyomaker.model.map.NpcState;
import com.soyomaker.model.map.NpcStateCondition;
import com.soyomaker.model.map.Script;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;

/**
 *
 * @author Administrator
 */
public class DefaultSoftNpcBinaryWriter implements INpcWriter {

    /**
     *
     * @param npc
     * @param filename
     * @throws Exception
     */
    public void writeNpc(Npc npc, String filename) throws Exception {
        FileOutputStream fos = null;
        DataOutputStream dos = null;

        fos = new FileOutputStream(filename);
        dos = new DataOutputStream(fos);
        dos.writeInt(npc.getIndex());
        dos.writeInt(npc.getMapId());
        dos.writeInt(npc.getRow());
        dos.writeInt(npc.getCol());
        dos.writeUTF(npc.getName());
//        int n = npc.getNpcStates().size();
////        System.out.println("npc序号：" + npc.getIndex());
////        System.out.println("npc地图序号：" + npc.getMapId());
////        System.out.println("npc初始行：" + npc.getRow());
////        System.out.println("npc初始列：" + npc.getCol());
////        System.out.println("npc状态数：" + n);
//        dos.writeInt(n);
//        for (int i = 0; i < n; i++) {
        NpcState state = npc.getNpcState();
        dos.writeByte(state.getStartType());
//            System.out.println("start type:" + state.getStartType());
        dos.writeUTF(File.separator + "image" + File.separator + "character" + File.separator + state.getImgPath());
        dos.writeUTF(File.separator + "image" + File.separator + "battler" + File.separator + state.getBattlerPath());
        dos.writeByte(state.getFace());
        dos.writeByte(state.getMove());
//            System.out.println("npc移动规则：" + state.getMove());
        dos.writeByte(state.getSpeed());
//            System.out.println("npc移动速度：" + state.getSpeed());
        dos.writeBoolean(state.isCross());

        NpcStateCondition sc = state.getSwitchCondition();
        if (sc != null) {
            dos.writeInt(sc.conditionType);
            dos.writeInt(sc.paramList.length);
            for (int m = 0; m < sc.paramList.length; m++) {
                dos.writeInt(sc.paramList[m]);
            }
        } else {
            dos.writeInt(-1);
        }

        NpcStateCondition vc = state.getVarCondition();
        if (vc != null) {
            dos.writeInt(vc.conditionType);
            dos.writeInt(vc.paramList.length);
            for (int m = 0; m < vc.paramList.length; m++) {
                dos.writeInt(vc.paramList[m]);
            }
        } else {
            dos.writeInt(-1);
        }

        Script sFile = state.getScript();
        dos.writeInt(sFile.getIndex());
//            System.out.println("脚本ID：" + sFile.getIndex());
//        if (sFile.getIndex() != -1) {
        IScriptWriter scriptWriter = new DefaultSoftScriptBinaryWriter();
        scriptWriter.writeScript(sFile, AppData.getInstance().getCurProject().getPath() + File.separatorChar
                + "softdata" + File.separatorChar + "script" + File.separatorChar + "script" + sFile.getIndex() + ".gat");
//        }
//        }
        dos.close();
        fos.close();
    }

    /**
     *
     * @param filename
     * @throws Exception
     */
    public void writeNpc(String filename) throws Exception {
    }
}
