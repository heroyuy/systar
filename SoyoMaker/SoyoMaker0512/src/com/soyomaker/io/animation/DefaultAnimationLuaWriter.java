/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyomaker.io.animation;

import com.soyomaker.AppData;
import com.soyomaker.config.Configuration;
import com.soyomaker.lua.LuaFileUtil;
import com.soyomaker.lua.LuaNode;
import com.soyomaker.lua.LuaTable;
import com.soyomaker.model.animation.Action;
import com.soyomaker.model.animation.Animation;
import com.soyomaker.model.animation.Frame;
import java.util.Iterator;

/**
 *
 * @author Administrator
 */
public class DefaultAnimationLuaWriter implements IAnimationWriter {

    private AppData data = AppData.getInstance();

    public void writeAnimation(String filename) throws Exception {
        LuaTable ans = new LuaTable();
        Iterator it = data.getCurProject().getAnimations().keySet().iterator();
        while (it.hasNext()) {
            Integer key = (Integer) it.next();
            Animation ani = data.getCurProject().getAnimations().get(key);
            if (ani != null) {
                ans.addNode("\n");
                LuaTable lt = new LuaTable();
                lt.addNode("\n");
                lt.addNode("index", Configuration.Prefix.ANIMATION_MASK + ani.getIndex() + 1);
                lt.addNode("\n");
                lt.addNode("name", ani.getName());
                lt.addNode("\n");
                lt.addNode("interval", ani.getFrameDelay());
                lt.addNode("\n");
                LuaTable frs = new LuaTable();
                for (int i = 0; i < ani.getFrames().size(); i++) {
                    Frame frame = ani.getFrame(i);
                    frs.addNode("\n");
                    LuaTable fr = new LuaTable();
                    fr.addNode("\n");
                    fr.addNode("x", frame.getPngX());
                    fr.addNode("y", frame.getPngY());
                    fr.addNode("width", frame.getPngWidth());
                    fr.addNode("height", frame.getPngHeight());
                    fr.addNode("offsetX", frame.getOffsetX());
                    fr.addNode("offsetY", frame.getOffsetY());
                    frs.addNode(fr);
                }
                lt.addNode("frames", frs);
                lt.addNode("\n");
                LuaTable sos = new LuaTable();
                for (int i = 0; i < ani.getActions().size(); i++) {
                    Action action = ani.getAction(i);
                    if (action.getMusicName() != null && !action.getMusicName().equals("")) {
                        sos.addNode("\n");
                        LuaTable sr = new LuaTable();
                        sr.addNode("startTime", action.getStartFrameIndex() * action.getAnimation().getFrameDelay());
                        sr.addNode("sound", action.getMusicName());
                        sos.addNode(sr);
                    }
                }
                lt.addNode("sounds", sos);
                lt.addNode("\n");
                LuaTable shs = new LuaTable();
                for (int i = 0; i < ani.getActions().size(); i++) {
                    Action action = ani.getAction(i);
                    if (action.getEffectType() != Action.NOTHING) {
                        shs.addNode("\n");
                        LuaTable sr = new LuaTable();
                        sr.addNode("startTime", action.getStartFrameIndex() * action.getAnimation().getFrameDelay());
                        sr.addNode("type", action.getEffectType());
                        sr.addNode("lastTime", action.getLastFrameCount() * action.getAnimation().getFrameDelay());
                        if (action.getEffectType() == Action.SCREEN_FLICKER || action.getEffectType() == Action.OBJECT_FLICKER) {
                            int[] ps = {action.getSr(), action.getSg(), action.getSb(), action.getTr(), action.getTg(), action.getTb()};
                            LuaTable ltPs = new LuaTable();
                            ltPs.addNode(ps);
                            sr.addNode("paramList", ltPs);
                        }
                        shs.addNode(sr);
                    }
                }
                lt.addNode("shadows", shs);
                ans.addNode("[" + (Configuration.Prefix.ANIMATION_MASK + ani.getIndex() + 1) + "]", lt);
            }
        }
        LuaNode ln = new LuaNode("Dictionary.effects", ans);
        LuaFileUtil.writeToFile(ln, filename);
    }
}
