/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyomaker.io.map;

import com.soyomaker.AppData;
import com.soyomaker.config.Configuration;
import com.soyomaker.lua.LuaFileUtil;
import com.soyomaker.lua.LuaNode;
import com.soyomaker.lua.LuaTable;
import com.soyomaker.model.map.Npc;
import com.soyomaker.model.map.NpcState;
import com.soyomaker.model.map.Script;
import java.util.Iterator;

/**
 *
 * @author Administrator
 */
public class DefaultNpcLuaWriter implements INpcWriter {

    private AppData data = AppData.getInstance();

    /**
     *
     * @param filename
     * @throws Exception
     */
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
                lts.addNode("index", Configuration.Prefix.NPC_MASK + npc.getIndex() + 1);
                lts.addNode("\n");
                lts.addNode("mapIndex", Configuration.Prefix.MAP_MASK + npc.getMapId() + 1);
                lts.addNode("\n");
                lts.addNode("row", npc.getRow());
                lts.addNode("\n");
                lts.addNode("col", npc.getCol());
                lts.addNode("\n");
                lts.addNode("name", npc.getName());
                lts.addNode("\n");
                NpcState state = npc.getNpcState();
                if (state.getImgPath() == null || state.getImgPath().equals("")) {
                    lts.addNode("charImg", "nil");
                } else {
                    lts.addNode("charImg", "/image/character/" + state.getImgPath());
                }
                lts.addNode("\n");
                if (state.getBattlerPath() == null || state.getBattlerPath().equals("")) {
                    lts.addNode("headImg", "nil");
                } else {
                    lts.addNode("headImg", "/image/battler/" + state.getBattlerPath());
                }
                lts.addNode("\n");
                lts.addNode("face", state.getFace());
                lts.addNode("\n");
                lts.addNode("moveType", state.getMove());
                lts.addNode("\n");
                lts.addNode("speedLevel", state.getSpeed());
                lts.addNode("\n");
                lts.addNode("penetrable", state.isCross());
//                lts.addNode("\n");
//                lts.addNode("scriptType", state.getStartType());
                lts.addNode("\n");
//                LuaTable nscs = new LuaTable();
//                NpcStateCondition nsCondition1 = state.getSwitchCondition();
//                if (nsCondition1 != null) {
//                    LuaTable nsc1 = new LuaTable();
//                    nsc1.addNode("conditionType", nsCondition1.conditionType);
//                    LuaTable paras1 = new LuaTable();
//                    for (int m = 0; m < nsCondition1.paramList.length; m++) {
//                        paras1.addNode(nsCondition1.paramList[m]);
//                    }
//                    nsc1.addNode("parameters", paras1);
//                    nscs.addNode(nsc1);
//                }
//                NpcStateCondition nsCondition2 = state.getVarCondition();
//                if (nsCondition2 != null) {
//                    LuaTable nsc2 = new LuaTable();
//                    nsc2.addNode("conditionType", nsCondition2.conditionType);
//                    LuaTable paras2 = new LuaTable();
//                    for (int m = 0; m < nsCondition2.paramList.length; m++) {
//                        paras2.addNode(nsCondition2.paramList[m]);
//                    }
//                    nsc2.addNode("parameters", paras2);
//                    nscs.addNode(nsc2);
//                }
//
//                lts.addNode("conditions", nscs);
//                lts.addNode("\n");
                Script sFile = state.getScript();
                if (sFile.getIndex() == -1) {
                    lts.addNode("scriptIndex", -1);
                } else {
                    lts.addNode("scriptIndex", Configuration.Prefix.SCRIPT_MASK + sFile.getIndex() + 1);
                }
                ltss.addNode("[" + (Configuration.Prefix.NPC_MASK + npc.getIndex() + 1) + "]", lts);
                if (nn != data.getCurProject().getNpcCounts() - 1) {
                    ltss.addNode("\n");
                }
                nn++;
            }
        }

        LuaNode ln = new LuaNode("Dictionary.npcs", ltss);
        LuaFileUtil.writeToFile(ln, filename);
    }

    /**
     *
     * @param npc
     * @param filename
     * @throws Exception
     */
    public void writeNpc(Npc npc, String filename) throws Exception {
    }
}
