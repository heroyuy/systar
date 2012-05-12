/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyomaker.io.animation;

import com.soyomaker.AppData;
import com.soyomaker.lua.LuaNode;
import com.soyomaker.lua.LuaTable;
import com.soyomaker.model.animation.Animation;
import com.soyomaker.model.animation.Clip;
import com.soyomaker.model.animation.Frame;
import com.soyomaker.model.animation.Picture;
import java.io.PrintWriter;
import java.util.ArrayList;
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
        for (int i = 0; i < data.getCurProject().getPictures().size(); i++) {
            Picture pic = data.getCurProject().getPictures().get(i);
            ps.addNode("\n");
            LuaTable lt = new LuaTable();
            lt.addNode("index", (i + 1));
            lt.addNode("path", "/image/animation/" + pic.getSourceImageFile());
            ps.addNode("[" + (i + 1) + "]", lt);
            pn++;
        }
        lts.addNode("imagesets", ps);
        lts.addNode("\n");

        LuaTable clss = new LuaTable();
        for (int j = 0; j < data.getCurProject().getClips().size(); j++) {
            Clip t = data.getCurProject().getClips().get(j);
            clss.addNode("\n");
            LuaTable cl = new LuaTable();
            cl.addNode("index", data.getCurProject().getClips().indexOf(t) + 1);
            cl.addNode("imageId", data.getCurProject().getPictures().indexOf(t.getPicture()) + 1);
            cl.addNode("x", t.getSourcePoint().x);
            cl.addNode("y", t.getSourcePoint().y);
            cl.addNode("width", t.getW());
            cl.addNode("height", t.getH());
            clss.addNode("[" + (data.getCurProject().getClips().indexOf(t) + 1) + "]", cl);
        }
        lts.addNode("clips", clss);
        LuaNode ln = new LuaNode("Dictionary.animationRes", lts);

        LuaTable ans = new LuaTable();
        Iterator it = data.getCurProject().getAnimations().keySet().iterator();
        int n = 0;
        while (it.hasNext()) {
            Integer key = (Integer) it.next();
            Animation ani = data.getCurProject().getAnimations().get(key);
            if (ani != null) {
                ans.addNode("\n");
                LuaTable lt = new LuaTable();
                lt.addNode("\n");
                lt.addNode("index", ani.getIndex() + 1);
                lt.addNode("\n");
                lt.addNode("name", ani.getName());
                lt.addNode("\n");
                LuaTable ltIds = new LuaTable();
                ArrayList<Picture> pics = ani.getUsedPictures();
                int[] ids = new int[pics.size()];
                for (int i = 0; i < ids.length; i++) {
                    ids[i] = data.getCurProject().getPictures().indexOf(pics.get(i)) + 1;
                }
                ltIds.addNode(ids);
                lt.addNode("imageIds", ltIds);
                lt.addNode("\n");
                LuaTable frs = new LuaTable();
                int startTime = 0;
                for (int i = 0; i < ani.getFrames().size(); i++) {
                    Frame frame = ani.getFrame(i);
                    frs.addNode("\n");
                    startTime += frame.getDelay();
                    LuaTable fr = new LuaTable();
                    fr.addNode("startTime", startTime);
                    fr.addNode("\n");
                    LuaTable cls = new LuaTable();
                    for (int j = 0; j < frame.getTiles().size(); j++) {
                        Clip t = frame.getTiles().get(j);
                        cls.addNode("\n");
                        LuaTable cl = new LuaTable();
                        cl.addNode("index", data.getCurProject().getClips().indexOf(t.getOriginal()) + 1);
                        cl.addNode("x", t.getFramePoint().x);
                        cl.addNode("y", t.getFramePoint().y);
                        cl.addNode("alpha", t.getTransparency());
                        cl.addNode("mirror", t.isMirror());
                        cl.addNode("rotation", t.getRotation());
                        cl.addNode("zoom", t.getZoom());
                        cls.addNode(cl);
                    }
                    fr.addNode("clips", cls);
                    frs.addNode(fr);
                }
                lt.addNode("frames", frs);
                ans.addNode("[" + (ani.getIndex() + 1) + "]", lt);
                n++;
            }
        }
        LuaNode ln2 = new LuaNode("Dictionary.animations", ans);

        PrintWriter pw = new PrintWriter(filename, "UTF-8");
        String[] strs = ln.toString().split("\n");
        for (int i = 0; i < strs.length; i++) {
            pw.println(strs[i]);
        }
        pw.println();
        String[] strs2 = ln2.toString().split("\n");
        for (int i = 0; i < strs2.length; i++) {
            pw.println(strs2[i]);
        }
        pw.flush();
        pw.close();
    }
}
