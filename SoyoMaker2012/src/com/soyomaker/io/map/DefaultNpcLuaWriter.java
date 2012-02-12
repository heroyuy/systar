/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyomaker.io.map;

import com.soyostar.lua.LuaNode;
import com.soyostar.lua.LuaTable;
import com.soyostar.lua.util.LuaFileUtil;
import com.soyomaker.model.map.Npc;
import com.soyomaker.model.map.NpcState;
import com.soyomaker.model.map.ScriptFile;

/**
 *
 * @author Administrator
 */
public class DefaultNpcLuaWriter implements INpcWriter {

    public void writeNpc(Npc npc, String filename) throws Exception {
        LuaTable lts = new LuaTable();
        lts.addNode("\n");
        lts.addNode("index", npc.getIndex() + 1);
        lts.addNode("\n");
        lts.addNode("mapIndex", npc.getMapId() + 1);
        lts.addNode("\n");
        lts.addNode("row", npc.getRow());
        lts.addNode("\n");
        lts.addNode("col", npc.getCol());
        lts.addNode("\n");
        int n = npc.getNpcStates().size();
        LuaTable sts = new LuaTable();
        for (int i = 0; i < n; i++) {
            NpcState state = npc.getNpcState(i);
            LuaTable st = new LuaTable();
            st.addNode("stateType", state.getStartType());
            st.addNode("path", state.getImgPath());
            if (state.getBattlerPath() == null || state.getBattlerPath().equals("")) {
                st.addNode("headImg", "nil");
            } else {
                st.addNode("headImg", "/image/battler/" + state.getBattlerPath());
            }
            st.addNode("face", state.getFace());
            st.addNode("moveType", state.getMove());
            st.addNode("speed", state.getSpeed());
            st.addNode("penetrable", state.isCross());
            ScriptFile sFile = state.getScript();
            sFile.link();//脚本文件预处理
            st.addNode("scriptIndex", sFile.getIndex());
            sts.addNode(st);
        }
        lts.addNode("states", sts);
        LuaNode ln = new LuaNode("globalDictionary.npcs[" + (npc.getIndex() + 1) + "]", lts);
        LuaFileUtil.writeToFile(ln, filename);
    }
}
