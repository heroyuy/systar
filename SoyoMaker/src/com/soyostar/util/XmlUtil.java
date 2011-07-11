/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyostar.util;

import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.dom4j.Attribute;
import org.dom4j.VisitorSupport;
import org.dom4j.io.SAXReader;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Administrator
 */
public class XmlUtil {

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        createConfigXml("mainclass", "configname.xml");
        EngineGame[] eg = new EngineGame[3];
        eg[0] = new EngineGame();
        eg[0].gameConfigName = "gcn0";
        eg[0].gameId = 0;
        eg[0].gameName = "game0";
        eg[0].gameMainClass = "gmc0";
        eg[1] = new EngineGame();
        eg[1].gameConfigName = "gcn1";
        eg[1].gameId = 1;
        eg[1].gameName = "game1";
        eg[1].gameMainClass = "gmc1";
        eg[2] = new EngineGame();
        eg[2].gameConfigName = "gcn2";
        eg[2].gameId = 2;
        eg[2].gameName = "game2";
        eg[2].gameMainClass = "gmc2";
        createEngineXml(eg, 1);
        EngineControl[] ec = new EngineControl[2];
        ec[0] = new EngineControl();
        ec[0].controlClassName = "c1";
        ec[0].controlId = 0;
        ec[0].view = new String[2];
        ec[0].view[0] = "viewa";
        ec[0].view[1] = "viewb";
        ec[1] = new EngineControl();
        ec[1].controlClassName = "c1";
        ec[1].controlId = 0;
        ec[1].view = new String[3];
        ec[1].view[0] = "viewa";
        ec[1].view[1] = "viewb";
        ec[1].view[2] = "viewc";
        createGameXml(ec, 0);
    }

    /**
     * 
     * @param mainClass
     * @param configName
     */
    public static void createConfigXml(String mainClass, String configName) {
        Document doc = DocumentHelper.createDocument();
        Element project = doc.addElement("config");
        project.addElement("EngineMainClass").addText(mainClass);
        project.addElement("EngineConfigName").addText(configName);
        try {
            OutputFormat format = OutputFormat.createPrettyPrint();
            format.setEncoding("UTF-8");
            XMLWriter xmlw = new XMLWriter(new FileWriter("xml\\config.xml"), format);
            xmlw.write(doc);
            xmlw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 
     */
    public static class EngineGame {

        /**
         * 
         */
        public String gameName;
        /**
         * 
         */
        public int gameId;
        /**
         * 
         */
        public String gameConfigName;
        /**
         * 
         */
        public String gameMainClass;
    }

    /**
     * 
     * @param eg
     * @param curId
     */
    public static void createEngineXml(EngineGame[] eg, int curId) {
        Document doc = DocumentHelper.createDocument();
        Element project = doc.addElement("engine");
        for (int i = 0; i < eg.length; i++) {
            Element gameNameE = project.addElement(eg[i].gameName);
            gameNameE.addElement("GameID").addText("" + eg[i].gameId);
            gameNameE.addElement("GameMainClass").addText("" + eg[i].gameMainClass);
            gameNameE.addElement("GameConfigName").addText("" + eg[i].gameConfigName);
        }
        project.addElement("CurrentGame").addText("" + curId);
        try {
            OutputFormat format = OutputFormat.createPrettyPrint();
            format.setEncoding("UTF-8");
            XMLWriter xmlw = new XMLWriter(new FileWriter("xml\\engine.xml"), format);
            xmlw.write(doc);
            xmlw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 
     */
    public static class EngineControl {

        /**
         * 
         */
        public int controlId;
        /**
         * 
         */
        public String controlClassName;
        /**
         * 
         */
        public String[] view;
    }

    /**
     * 
     * @param ec
     * @param curId
     */
    public static void createGameXml(EngineControl[] ec, int curId) {
        Document doc = DocumentHelper.createDocument();
        Element project = doc.addElement("game");
        for (int i = 0; i < ec.length; i++) {
            Element gameNameE = project.addElement("Control" + i);
            gameNameE.addElement("ControlID").addText("" + ec[i].controlId);
            gameNameE.addElement("ControlClass").addText("" + ec[i].controlClassName);
            Element viewE = gameNameE.addElement("View");
            for (int j = 0; j < ec[i].view.length; j++) {
                viewE.addElement(ec[i].view[j]);
            }
        }
        project.addElement("CurrentControl").addText("" + curId);
        try {
            OutputFormat format = OutputFormat.createPrettyPrint();
            format.setEncoding("UTF-8");
            XMLWriter xmlw = new XMLWriter(new FileWriter("xml\\game.xml"), format);
            xmlw.write(doc);
            xmlw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
