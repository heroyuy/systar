/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyomaker.io.map;

import com.soyomaker.AppData;
import com.soyostar.lua.LuaNode;
import com.soyostar.lua.LuaTable;
import com.soyostar.lua.util.LuaFileUtil;
import com.soyomaker.model.map.Npc;
import com.soyomaker.model.map.NpcState;
import com.soyomaker.model.map.NpcStateCondition;
import com.soyomaker.model.map.ScriptFile;
import java.util.Iterator;

/**
 *
 * @author Administrator
 */
public class DefaultNpcLuaWriter implements INpcWriter {

    private AppData data = AppData.getInstance();

    public void writeNpc(String filename) throws Exception {
        LuaTable ltss = new LuaTable();
        int nn = 0;
        Iterator it = data.getCurProject().getNpcs().keySet().iterator();
        while (it.hasNext()) {
            Integer key = (Integer) it.next();
            Npc npc = data.getCurProject().getNpcs().get(key);
            if (npc != null) {
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
                    LuaTable nscs = new LuaTable();
                    
                    NpcStateCondition nsCondition1 = state.getSwitchCondition();
                    if (nsCondition1 != null) {
                        LuaTable nsc1 = new LuaTable();
                        nsc1.addNode("conditionType", nsCondition1.conditionType);
                        LuaTable paras1 = new LuaTable();
                        for (int m = 0; m < nsCondition1.paramList.length; m++) {
                            paras1.addNode(nsCondition1.paramList[m]);
                        }
                        nsc1.addNode("parameters", paras1);
                        nscs.addNode(nsc1);
                    }
                    
                    NpcStateCondition nsCondition2 = state.getVarCondition();
                    if (nsCondition2 != null) {
                        LuaTable nsc2 = new LuaTable();
                        nsc2.addNode("conditionType", nsCondition2.conditionType);
                        LuaTable paras2 = new LuaTable();
                        for (int m = 0; m < nsCondition2.paramList.length; m++) {
                            paras2.addNode(nsCondition2.paramList[m]);
                        }
                        nsc2.addNode("parameters", paras2);
                        nscs.addNode(nsc2);
                    }

                    st.addNode("conditions", nscs);
                    ScriptFile sFile = state.getScript();
                    sFile.link();//脚本文件预处理
                    st.addNode("scriptIndex", sFile.getIndex());
                    sts.addNode(st);
                }
                lts.addNode("states", sts);
                ltss.addNode(lts);
                if (nn != data.getCurProject().getNpcCounts() - 1) {
                    ltss.addNode("\n");
                }
                nn++;
            }
        }

        LuaNode ln = new LuaNode("globalDictionary.npcs", ltss);
        LuaFileUtil.writeToFile(ln, filename);
    }

    public void writeNpc(Npc npc, String filename) throws Exception {
    }
}
