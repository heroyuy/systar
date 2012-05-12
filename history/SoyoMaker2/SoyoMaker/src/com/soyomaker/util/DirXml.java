/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyomaker.util;

import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

/**
 *
 * @author Administrator
 */
public class DirXml {

    /**
     *
     * @param args
     */
    public static void main(String args[]) {
        DirXml test = new DirXml();
        test.create();
    }

    /**
     *
     */
    public static void create() {
        Document doc = DocumentHelper.createDocument();
        Element dir = doc.addElement("Dir");

        Element audio = dir.addElement("audio");
        Element music = audio.addElement("music");
        Element sound = audio.addElement("sound");

        Element image = dir.addElement("image");
        Element animation = image.addElement("animation");
        Element battle = image.addElement("battle");
        Element battler = image.addElement("battler");
        Element character = image.addElement("character");
        Element face = image.addElement("face");
        
        Element icon = image.addElement("icon");
        Element skillicon = icon.addElement("skill");
        Element itemicon = icon.addElement("item");
        Element equipicon = icon.addElement("equip");
        Element statusicon = icon.addElement("status");

        Element skin = image.addElement("skin");
        Element tileset = image.addElement("tileset");
        Element title = image.addElement("title");

        Element data = dir.addElement("data");
        Element datamap = data.addElement("map");
        Element datanpc = data.addElement("npc");
        Element datascript = data.addElement("script");

        Element softdata = dir.addElement("softdata");
        Element softdatamap = softdata.addElement("map");
        Element softdatanpc = softdata.addElement("npc");
        Element softdatascript = softdata.addElement("script");
//        Element softdatasnapshot = softdata.addElement("snapshot");
//        Element softdatatileset = softdata.addElement("tileset");

        Element smscript = dir.addElement("smscript");
        try {
            OutputFormat format = OutputFormat.createPrettyPrint();
            format.setEncoding("UTF-8");
            XMLWriter xmlw = new XMLWriter(new FileWriter("res" + File.separator + "softdata" + File.separator + "Dir.xml"), format);
            xmlw.write(doc);
            xmlw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
