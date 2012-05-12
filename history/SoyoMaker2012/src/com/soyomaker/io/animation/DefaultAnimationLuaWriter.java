/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyomaker.io.animation;

import com.soyomaker.AppData;
import com.soyomaker.model.animation.Action;
import com.soyomaker.model.animation.Animation;
import com.soyomaker.model.animation.Clip;
import com.soyomaker.model.animation.Frame;
import com.soyomaker.model.animation.Picture;
import com.soyostar.lua.LuaNode;
import com.soyostar.lua.LuaTable;
import com.soyostar.lua.util.LuaFileUtil;
import java.util.Iterator;

/**
 *
 * @author Administrator
 */
public class DefaultAnimationLuaWriter implements IAnimationWriter {

    private AppData data = AppData.getInstance();

    public void writeAnimation(String filename) throws Exception {
        LuaTable lts = new LuaTable();
        int pn = 0;
        lts.addNode("\n");
        LuaTable ps = new LuaTable();
        if (!data.getCurProject().getPictures().isEmpty()) {
            ps.addNode("\n");
        }
        for (int i = 0; i < data.getCurProject().getPictures().size(); i++) {
            Picture pic = data.getCurProject().getPictures().get(i);
            LuaTable lt = new LuaTable();
            lt.addNode("\n");
            lt.addNode("path", "/image/animation/" + pic.getSourceImageFile());
            lt.addNode("\n");
            LuaTable cls = new LuaTable();
            if (!pic.getTiles().isEmpty()) {
                cls.addNode("\n");
            }
            for (int j = 0; j < pic.getTiles().size(); j++) {
                Clip t = pic.getTile(j);
                LuaTable cl = new LuaTable();
                cl.addNode("clipIndex", data.getCurProject().getClips().indexOf(t) + 1);
                cl.addNode("clipX", t.getSourcePoint().x);
                cl.addNode("clipY", t.getSourcePoint().y);
                cl.addNode("clipWidth", t.getW());
                cl.addNode("clipHeight", t.getH());
                cls.addNode(cl);
                if (j != pic.getTiles().size() - 1) {
                    cls.addNode("\n");
                }
            }
            lt.addNode("clips", cls);
            ps.addNode(lt);
            if (pn != data.getCurProject().getPictures().size() - 1) {
                ps.addNode("\n");
            }
            pn++;
        }
        lts.addNode("pictures", ps);
        lts.addNode("\n");
        Iterator it = data.getCurProject().getAnimations().keySet().iterator();
        int n = 0;
        int allFramsNum = 0;
        while (it.hasNext()) {
            Integer key = (Integer) it.next();
            Animation ani = data.getCurProject().getAnimations().get(key);
            if (ani != null) {
                LuaTable lt = new LuaTable();
                lt.addNode("\n");
                lt.addNode("index", ani.getIndex() + 1);
                lt.addNode("\n");
                lt.addNode("name", ani.getName());
                lt.addNode("\n");
                LuaTable frs = new LuaTable();
                if (!ani.getFrames().isEmpty()) {
                    frs.addNode("\n");
                }
                for (int i = 0; i < ani.getFrames().size(); i++) {
                    Frame frame = ani.getFrame(i);
                    LuaTable fr = new LuaTable();
                    fr.addNode("frameIndex", allFramsNum + i + 1);
                    fr.addNode("frameDuration", frame.getDelay());
                    fr.addNode("frameWidth", frame.getW());
                    fr.addNode("frameHeight", frame.getH());
                    fr.addNode("\n");
                    LuaTable cls = new LuaTable();
                    if (!frame.getTiles().isEmpty()) {
                        cls.addNode("\n");
                    }
                    for (int j = 0; j < frame.getTiles().size(); j++) {
                        Clip t = frame.getTiles().get(j);
                        LuaTable cl = new LuaTable();
                        cl.addNode("clipIndex", data.getCurProject().getClips().indexOf(t.getOriginal()) + 1);
                        cl.addNode("frameClipX", t.getFramePoint().x);
                        cl.addNode("frameClipY", t.getFramePoint().y);
                        cl.addNode("transparency", t.getTransparency());
                        cl.addNode("mirror", t.isMirror());
                        cl.addNode("rotation", t.getRotation());
                        cl.addNode("zoom", t.getZoom());
                        cl.addNode("renderType", t.getRenderType());
                        cls.addNode(cl);
                        if (j != frame.getTiles().size() - 1) {
                            cls.addNode("\n");
                        }
                    }
                    fr.addNode("clips", cls);
                    frs.addNode(fr);
                    if (i != ani.getFrames().size() - 1) {
                        frs.addNode("\n");
                    }
                }
                lt.addNode("frames", frs);
                lt.addNode("\n");
                allFramsNum += ani.getFrames().size();
                LuaTable acs = new LuaTable();
                if (!ani.getActions().isEmpty()) {
                    acs.addNode("\n");
                }
                for (int i = 0; i < ani.getActions().size(); i++) {
                    Action action = ani.getActions().get(i);
                    LuaTable ac = new LuaTable();
                    ac.addNode("frameIndex", action.getFrameId() + 1);
                    ac.addNode("sound", "/audio/sound/" + action.getMusicFile());
                    ac.addNode("type", action.getType());
                    ac.addNode("red", action.getRed());
                    ac.addNode("green", action.getGreen());
                    ac.addNode("blue", action.getBlue());
                    ac.addNode("alpha", action.getAlpha());
                    ac.addNode("duration", action.getDuration());
                    acs.addNode(ac);
                    if (i != ani.getActions().size() - 1) {
                        acs.addNode("\n");
                    }
                }
                lt.addNode("actions", acs);
                lts.addNode(lt);
                if (n != data.getCurProject().getAnimationCounts() - 1) {
                    lts.addNode("\n");
                }
                n++;
            }
        }
        LuaNode ln = new LuaNode("globalDictionary.animations", lts);
        LuaFileUtil.writeToFile(ln, filename);
    }
}
