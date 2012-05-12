/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyomaker.io.map;

import com.soyomaker.model.map.Npc;
import com.soyomaker.model.map.NpcState;
import com.soyomaker.model.map.Script;
import com.soyomaker.AppData;
import com.soyomaker.model.map.NpcStateCondition;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;

/**
 *
 * @author Administrator
 */
public class DefaultSoftNpcBinaryReader implements INpcReader {

    public Npc readNpc(String npcFile) throws Exception {
        DataInputStream dis = null;
        FileInputStream fis = null;
        fis = new FileInputStream(npcFile);
        dis = new DataInputStream(fis);
        Npc npc = new Npc();
        npc.setIndex(dis.readInt());
//        System.out.println("npc序号：" + npc.getIndex());
        npc.setMapId(dis.readInt());
//        System.out.println("npc地图序号：" + npc.getMapId());
        npc.setRow(dis.readInt());
//        System.out.println("npc初始行：" + npc.getRow());
        npc.setCol(dis.readInt());
//        System.out.println("npc初始列：" + npc.getCol());
        npc.setName(dis.readUTF());

        NpcState state = new NpcState();
        state.setStartType(dis.readByte());
        String s = dis.readUTF();
        int temp = s.lastIndexOf(File.separator);
        state.setImgPath(s.substring(temp + 1));
        String s2 = dis.readUTF();
        int temp2 = s2.lastIndexOf(File.separator);
        state.setBattlerPath(s2.substring(temp2 + 1));
        state.setFace(dis.readByte());
        state.setMove(dis.readByte());
//            System.out.println("npc移动规则：" + state.getMove());
        state.setSpeed(dis.readByte());
//            System.out.println("npc移动速度：" + state.getSpeed());
        state.setCross(dis.readBoolean());

        int temp3 = dis.readInt();
        if (temp3 != -1) {
            NpcStateCondition sc = new NpcStateCondition();
            sc.conditionType = temp3;
            sc.paramList = new int[dis.readInt()];
            for (int m = 0; m < sc.paramList.length; m++) {
                sc.paramList[m] = dis.readInt();
            }
            state.setSwitchCondition(sc);
        } else {
            state.setSwitchCondition(null);
        }

        int temp4 = dis.readInt();
        if (temp4 != -1) {
            NpcStateCondition vc = new NpcStateCondition();
            vc.conditionType = temp4;
            vc.paramList = new int[dis.readInt()];
            for (int m = 0; m < vc.paramList.length; m++) {
                vc.paramList[m] = dis.readInt();
            }
            state.setVarCondition(vc);
        } else {
            state.setVarCondition(null);
        }

        int index = dis.readInt();
//        if (index != -1) {
        IScriptReader scriptReader = new DefaultSoftScriptBinaryReader();
        
        Script script = scriptReader.readScript(AppData.getInstance().getCurProject().getPath() + File.separatorChar
                + "softdata" + File.separatorChar + "script" + File.separatorChar + "script" + index + ".gat");
        script.setIndex(index);

        state.setScript(script);
//        }
        AppData.getInstance().getCurProject().addScript(state.getScript());//给script file分配一个全局id
        npc.setNpcState(state);
        dis.close();
        fis.close();
        return npc;
    }
}
