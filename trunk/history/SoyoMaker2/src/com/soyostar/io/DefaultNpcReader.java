/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyostar.io;

import com.soyostar.model.map.Npc;
import com.soyostar.model.map.NpcState;
import com.soyostar.model.map.ScriptFile;
import com.soyostar.proxy.SoftProxy;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;

/**
 *
 * @author Administrator
 */
public class DefaultNpcReader implements INpcReader {

    public Npc readNpc(String npcFile) throws Exception {
        DataInputStream dis = null;
        FileInputStream fis = null;
        fis = new FileInputStream(npcFile);
        dis = new DataInputStream(fis);
        Npc npc = new Npc();
        npc.setIndex(dis.readInt());
        System.out.println("npc序号：" + npc.getIndex());
        npc.setMapId(dis.readInt());
        System.out.println("npc地图序号：" + npc.getMapId());
        npc.setRow(dis.readInt());
        System.out.println("npc初始行：" + npc.getRow());
        npc.setCol(dis.readInt());
        System.out.println("npc初始列：" + npc.getCol());
        int n = dis.readInt();
        System.out.println("npc状态数：" + n);
        for (int i = 0; i < n; i++) {
            NpcState state = new NpcState();
            state.setStartType(dis.readByte());
            state.setImgPath(dis.readUTF());
            state.setFace(dis.readByte());
            state.setMove(dis.readByte());
//            System.out.println("npc移动规则：" + state.getMove());
            state.setSpeed(dis.readByte());
//            System.out.println("npc移动速度：" + state.getSpeed());
            state.setCross(dis.readBoolean());
            int index = dis.readInt();
            IScriptReader scriptReader = new DefaultScriptReader();
            ScriptFile script = scriptReader.readScript(SoftProxy.getInstance().getCurProject().getPath() + File.separatorChar
                + "softdata" + File.separatorChar + "script" + File.separatorChar + "script" + index + ".java");
            script.setIndex(index);
            System.out.println("脚本ID：" + script.getIndex());
            state.setScript(script);
            npc.addNpcState(state);
        }
        dis.close();
        fis.close();
        return npc;
    }
}
