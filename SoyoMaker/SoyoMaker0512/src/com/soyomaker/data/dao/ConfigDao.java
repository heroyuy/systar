/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyomaker.data.dao;

import com.soyomaker.AppData;
import com.soyomaker.config.Configuration;
import com.soyomaker.data.model.Attribute;
import com.soyomaker.data.model.Config;
import com.soyomaker.data.model.Model;
import com.soyomaker.data.model.Player;
import com.soyomaker.log.LogPrinter;
import com.soyomaker.log.LogPrinterFactory;
import com.soyomaker.lua.LuaFileUtil;
import com.soyomaker.lua.LuaNode;
import com.soyomaker.lua.LuaTable;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

/**
 *
 * @author Administrator
 */
public class ConfigDao extends Dao<Config> {

    private LogPrinter printer = LogPrinterFactory.getDefaultLogPrinter();

    /**
     *
     * @throws FileNotFoundException 
     * @throws IOException
     */
    @Override
    public void load() throws FileNotFoundException, IOException {
        printer.v("config load");
        if (new File(AppData.getInstance().getCurProject().getPath() + "/softdata/config.xml").exists()) {
            loadXML();
        } else {
            loadBin();
        }
    }

    private void loadBin() throws FileNotFoundException, IOException {
        DataInputStream dis = null;
        FileInputStream fis = null;
        File f = null;
        Config config = null;
//        try {
        f = new File(AppData.getInstance().getCurProject().getPath() + "/softdata/config.gat");
//            if (!f.exists()) {
//                //为了兼容前面版本，以后会去掉
//                f = new File(AppData.getInstance().getCurProject().getPath() + "/softdata/system.gat");
//            }
        fis = new FileInputStream(f);
        dis = new DataInputStream(fis);
        config = new Config();
        config.term.name = dis.readUTF();
        config.term.help = dis.readUTF();
        config.term.about = dis.readUTF();
        config.term.gold = dis.readUTF();
        int playerN = dis.readInt();
        for (int i = 0; i < playerN; i++) {
            config.system.initPlayers.add((Player) AppData.getInstance().getCurProject().getDataManager().getModel(Model.PLAYER, dis.readInt()));
        }
        int attrN = dis.readInt();
        for (int i = 0; i < attrN; i++) {
            Attribute attr = new Attribute();
            attr.id = dis.readInt();
            attr.name = dis.readUTF();
            attr.description = dis.readUTF();
            config.system.attributes.add(attr);
        }
        config.system.curMapIndex = dis.readInt();
        config.system.row = dis.readInt();
        config.system.col = dis.readInt();
        config.system.face = dis.readByte();
        config.term.hp = dis.readUTF();
        config.term.sp = dis.readUTF();
        config.term.atk = dis.readUTF();
        config.term.def = dis.readUTF();
        config.term.matk = dis.readUTF();
        config.term.mdef = dis.readUTF();
        config.term.stre = dis.readUTF();
        config.term.inte = dis.readUTF();
        config.term.agil = dis.readUTF();
        config.term.dext = dis.readUTF();
        config.term.vita = dis.readUTF();
        config.term.luck = dis.readUTF();
        config.term.helm = dis.readUTF();
        config.term.armour = dis.readUTF();
        config.term.weapon = dis.readUTF();
        config.term.shield = dis.readUTF();
        config.term.boots = dis.readUTF();
        config.term.jewelry = dis.readUTF();
        config.term.item = dis.readUTF();
        config.term.equip = dis.readUTF();
        config.term.skill = dis.readUTF();
        config.system.frameSkin = dis.readUTF();
        config.system.startAniIndex = dis.readInt();
        config.system.titleBackground = dis.readUTF();
        config.system.titleMusic = dis.readUTF();
        config.system.startBattleSound = dis.readUTF();
        config.system.failSound = dis.readUTF();
        config.system.winBattleSound = dis.readUTF();
        config.system.escapeSound = dis.readUTF();
        config.system.endAniIndex = dis.readInt();
        config.system.selectedSound = dis.readUTF();
        config.system.confirmSound = dis.readUTF();
        config.system.cancelSound = dis.readUTF();
        config.system.warnSound = dis.readUTF();
        config.system.equipSound = dis.readUTF();
        config.system.shopSound = dis.readUTF();
        config.system.readSound = dis.readUTF();
        config.system.saveSound = dis.readUTF();
        AppData.getInstance().getCurProject().getDataManager().saveModel(Model.CONFIG, config);
        dis.close();
        fis.close();
        f = null;
//        } catch (IOException e) {
////            System.out.println("没有可加载的Config");
//            printer.e("没有可加载的Config");
//        }
    }

    private void loadXML() throws FileNotFoundException, IOException {
        File f = new File(AppData.getInstance().getCurProject().getPath() + "/softdata/config.xml");
        InputStream ifile = null;
        InputStreamReader ir = null;
        Config config = new Config();
        SAXReader sax = new SAXReader();
//        try {
        ifile = new FileInputStream(f);
        ir = new InputStreamReader(ifile, "UTF-8");
        Document document = null;
        try {
            document = sax.read(ir);
        } catch (DocumentException ex) {
            ex.printStackTrace();
        }
        // 得到根元素
        Element root = document.getRootElement();
        config.term.name = getText(root, "name");
        config.term.help = getText(root, "help");
        config.term.about = getText(root, "about");
        config.term.gold = getText(root, "gold");
        config.term.hp = getText(root, "hp");
        config.term.sp = getText(root, "sp");
        config.term.matk = getText(root, "matk");
        config.term.mdef = getText(root, "mdef");
        config.term.stre = getText(root, "stre");
        config.term.inte = getText(root, "inte");
        config.term.agil = getText(root, "agil");
        config.term.dext = getText(root, "dext");
        config.term.vita = getText(root, "vita");
        config.term.luck = getText(root, "luck");
        config.term.helm = getText(root, "helm");
        config.term.armour = getText(root, "armour");
        config.term.weapon = getText(root, "weapon");
        config.term.shield = getText(root, "shield");
        config.term.boots = getText(root, "boots");
        config.term.jewelry = getText(root, "jewelry");
        config.term.item = getText(root, "item");
        config.term.equip = getText(root, "equip");
        config.term.skill = getText(root, "skill");

        config.system.frameSkin = getText(root, "skin");
        config.system.startAniIndex = Integer.parseInt(getText(root, "startAniIndex"));
        config.system.endAniIndex = Integer.parseInt(getText(root, "endAniIndex"));
        config.system.titleBackground = getText(root, "titleBackground");
        config.system.titleMusic = getText(root, "titleMusic");
        config.system.startBattleSound = getText(root, "startBattleSound");
        config.system.failSound = getText(root, "battleMusic");
        config.system.winBattleSound = getText(root, "winSound");
        config.system.escapeSound = getText(root, "escapeSound");
        config.system.selectedSound = getText(root, "selectedSound");
        config.system.confirmSound = getText(root, "confirmSound");
        config.system.cancelSound = getText(root, "cancelSound");
        config.system.warnSound = getText(root, "warnSound");
        config.system.equipSound = getText(root, "equipSound");
        config.system.shopSound = getText(root, "shopSound");
        config.system.readSound = getText(root, "readSound");
        config.system.saveSound = getText(root, "saveSound");

        config.system.curMapIndex = Integer.parseInt(getAttribute(root, "map", "index"));
        config.system.row = Integer.parseInt(getAttribute(root, "map", "row"));
        config.system.col = Integer.parseInt(getAttribute(root, "map", "col"));
        config.system.face = Byte.parseByte(getAttribute(root, "map", "face"));

        for (Iterator i = root.elementIterator("players"); i.hasNext();) {
            Element players = (Element) i.next();
            for (Iterator ii = players.elementIterator("player"); ii.hasNext();) {
                Element player = (Element) ii.next();
                int playerIndex = Integer.parseInt(player.attributeValue("index"));
                config.system.initPlayers.add((Player) AppData.getInstance().getCurProject().getDataManager().getModel(Model.PLAYER, playerIndex));
            }
        }

        for (Iterator i = root.elementIterator("attributes"); i.hasNext();) {
            Element attributes = (Element) i.next();
            for (Iterator ii = attributes.elementIterator("attribute"); ii.hasNext();) {
                Element attribute = (Element) ii.next();
                Attribute attr = new Attribute();
                int attributeIndex = Integer.parseInt(attribute.attributeValue("index"));
                attr.id = attributeIndex;
                attr.name = attribute.attributeValue("name");
                attr.description = attribute.attributeValue("desc");
                config.system.attributes.add(attr);
            }
        }

        AppData.getInstance().getCurProject().getDataManager().saveModel(Model.CONFIG, config);

        ifile.close();
        ir.close();
    }

    private String getAttribute(Element root, String key, String key2) {
        String value = "";
        for (Iterator i = root.elementIterator(key); i.hasNext();) {
            Element version = (Element) i.next();
            value = version.attributeValue(key2);
        }
        return value;
    }

    private String getText(Element root, String key) {
        String value = "";
        for (Iterator i = root.elementIterator(key); i.hasNext();) {
            Element version = (Element) i.next();
            value = version.getText();
        }
        return value;
    }

    private boolean saveXML() {
        try {
            Config config = null;
            if (AppData.getInstance().getCurProject().getDataManager().getModels(Model.CONFIG).length <= 0) {
                config = new Config();
            } else {
                config = (Config) AppData.getInstance().getCurProject().getDataManager().getModels(Model.CONFIG)[0];
            }
            Document doc = DocumentHelper.createDocument();
            Element root = doc.addElement("Config");

            Element name = root.addElement("name");
            name.setText(config.term.name);

            Element help = root.addElement("help");
            help.setText(config.term.help);

            Element about = root.addElement("about");
            about.setText(config.term.about);

            Element gold = root.addElement("gold");
            gold.setText(config.term.gold);

            Element hp = root.addElement("hp");
            hp.setText(config.term.hp);

            Element sp = root.addElement("sp");
            sp.setText(config.term.sp);

            Element atk = root.addElement("atk");
            atk.setText(config.term.atk);

            Element def = root.addElement("def");
            def.setText(config.term.def);

            Element matk = root.addElement("matk");
            matk.setText(config.term.matk);

            Element mdef = root.addElement("mdef");
            mdef.setText(config.term.mdef);

            Element stre = root.addElement("stre");
            stre.setText(config.term.stre);

            Element inte = root.addElement("inte");
            inte.setText(config.term.inte);

            Element agil = root.addElement("agil");
            agil.setText(config.term.agil);

            Element dext = root.addElement("dext");
            dext.setText(config.term.dext);

            Element vita = root.addElement("vita");
            vita.setText(config.term.vita);

            Element luck = root.addElement("luck");
            luck.setText(config.term.luck);

            Element helm = root.addElement("helm");
            helm.setText(config.term.helm);

            Element armour = root.addElement("armour");
            armour.setText(config.term.armour);

            Element weapon = root.addElement("weapon");
            weapon.setText(config.term.weapon);

            Element shield = root.addElement("shield");
            shield.setText(config.term.shield);

            Element boots = root.addElement("boots");
            boots.setText(config.term.boots);

            Element jewelry = root.addElement("jewelry");
            jewelry.setText(config.term.jewelry);

            Element item = root.addElement("item");
            item.setText(config.term.item);

            Element equip = root.addElement("equip");
            equip.setText(config.term.equip);

            Element skill = root.addElement("skill");
            skill.setText(config.term.skill);

            Element skin = root.addElement("skin");
            skin.setText(config.system.frameSkin);

            Element startAniIndex = root.addElement("startAniIndex");
            startAniIndex.setText("" + config.system.startAniIndex);

            Element endAniIndex = root.addElement("endAniIndex");
            endAniIndex.setText("" + config.system.endAniIndex);

            Element titleBackground = root.addElement("titleBackground");
            titleBackground.setText(config.system.titleBackground);

            Element titleMusic = root.addElement("titleMusic");
            titleMusic.setText(config.system.titleMusic);

            Element startBattleSound = root.addElement("startBattleSound");
            startBattleSound.setText(config.system.startBattleSound);

            Element battleMusic = root.addElement("failSound");
            battleMusic.setText(config.system.failSound);

            Element winSound = root.addElement("winSound");
            winSound.setText(config.system.winBattleSound);

            Element escapeSound = root.addElement("escapeSound");
            escapeSound.setText(config.system.escapeSound);

            Element selectedSound = root.addElement("selectedSound");
            selectedSound.setText(config.system.selectedSound);

            Element confirmSound = root.addElement("confirmSound");
            confirmSound.setText(config.system.confirmSound);

            Element cancelSound = root.addElement("cancelSound");
            cancelSound.setText(config.system.cancelSound);

            Element warnSound = root.addElement("warnSound");
            warnSound.setText(config.system.warnSound);

            Element equipSound = root.addElement("equipSound");
            equipSound.setText(config.system.equipSound);

            Element shopSound = root.addElement("shopSound");
            shopSound.setText(config.system.shopSound);

            Element readSound = root.addElement("readSound");
            readSound.setText(config.system.readSound);

            Element saveSound = root.addElement("saveSound");
            saveSound.setText(config.system.saveSound);

            Element map = root.addElement("map");
            map.addAttribute("index", "" + config.system.curMapIndex);
            map.addAttribute("row", "" + config.system.row);
            map.addAttribute("col", "" + config.system.col);
            map.addAttribute("face", "" + config.system.face);

            Element players = root.addElement("players");
            for (int i = 0; i < config.system.initPlayers.size(); i++) {
                Player p = config.system.initPlayers.get(i);
                Element pla = players.addElement("player");
                pla.addAttribute("index", "" + p.getIndex());
                pla.addAttribute("name", "" + p.name);
                pla.addAttribute("desc", "" + p.intro);
            }

            Element attrs = root.addElement("attributes");
            for (int i = 0; i < config.system.attributes.size(); i++) {
                Attribute att = config.system.attributes.get(i);
                Element attr = attrs.addElement("attribute");
                attr.addAttribute("index", "" + att.id);
                attr.addAttribute("name", att.name);
                attr.addAttribute("desc", att.description);
            }

            OutputFormat format = OutputFormat.createPrettyPrint();
            format.setEncoding("UTF-8");
            XMLWriter xmlw = new XMLWriter(new FileOutputStream(AppData.getInstance().getCurProject().getPath() + "/softdata/config.xml"), format);
            xmlw.write(doc);
            xmlw.close();
            return true;
        } catch (IOException ex) {
            printer.e("config.gat 写入失败");
            return false;
        }
    }

    private boolean saveLua() {
        Config config = null;
        if (AppData.getInstance().getCurProject().getDataManager().getModels(Model.CONFIG).length <= 0) {
            config = new Config();
        } else {
            config = (Config) AppData.getInstance().getCurProject().getDataManager().getModels(Model.CONFIG)[0];
        }
        LuaTable lt = new LuaTable();
        lt.addNode("\n");
        lt.addNode("name", config.term.name);
        lt.addNode("\n");
        lt.addNode("help", config.term.help);
        lt.addNode("\n");
        lt.addNode("about", config.term.about);
        lt.addNode("\n");
        lt.addNode("gold", config.term.gold);
        lt.addNode("\n");
        LuaTable ltSkins = new LuaTable();
        if (config.system.frameSkin == null || config.system.frameSkin.equals("")) {
            ltSkins.addNode("frame", "nil");
        } else {
            ltSkins.addNode("frame", "/image/skin/" + config.system.frameSkin);
        }
        if (config.system.playerInfoSkin == null || config.system.playerInfoSkin.equals("")) {
            ltSkins.addNode("playerInfo", "nil");
        } else {
            ltSkins.addNode("playerInfo", "/image/skin/" + config.system.playerInfoSkin);
        }
        if (config.system.inputDialogSkin == null || config.system.inputDialogSkin.equals("")) {
            ltSkins.addNode("inputDialog", "nil");
        } else {
            ltSkins.addNode("inputDialog", "/image/skin/" + config.system.inputDialogSkin);
        }
        lt.addNode("skins", ltSkins);
        if (config.system.startAniIndex == -1) {
            lt.addNode("startAniId", -1);
        } else {
            lt.addNode("startAniId", Configuration.Prefix.ANIMATION_MASK + config.system.startAniIndex + 1);
        }
        lt.addNode("\n");
        if (config.system.titleBackground == null || config.system.titleBackground.equals("")) {
            lt.addNode("titleImg", "nil");
        } else {
            lt.addNode("titleImg", "/image/title/" + config.system.titleBackground);
        }
        lt.addNode("\n");
        if (config.system.titleMusic == null || config.system.titleMusic.equals("")) {
            lt.addNode("titleMusic", "nil");
        } else {
            lt.addNode("titleMusic", "/audio/music/" + config.system.titleMusic);
        }
        lt.addNode("\n");
        lt.addNode("cellWidth", config.system.cellWidth);
        lt.addNode("\n");
        lt.addNode("cellHeight", config.system.cellHeight);
        lt.addNode("\n");
        LuaTable ltId = new LuaTable();
        for (int i = 0; i < config.system.initPlayers.size(); i++) {
            ltId.addNode(Configuration.Prefix.PLAYER_MASK + config.system.initPlayers.get(i).getIndex() + 1);
        }
        lt.addNode("playerList", ltId);
        lt.addNode("\n");
        if (config.system.curMapIndex == -1) {
            lt.addNode("mapId", -1);
        } else {
            lt.addNode("mapId", Configuration.Prefix.MAP_MASK + config.system.curMapIndex + 1);
        }
        lt.addNode("\n");
        lt.addNode("row", config.system.row);
        lt.addNode("\n");
        lt.addNode("col", config.system.col);
        lt.addNode("\n");
        lt.addNode("face", config.system.face);
        lt.addNode("\n");
        lt.addNode("\n");
        lt.addNode("str", config.term.stre);
        lt.addNode("\n");
        lt.addNode("int", config.term.inte);
        lt.addNode("\n");
        lt.addNode("agi", config.term.agil);
        lt.addNode("\n");
        lt.addNode("dex", config.term.dext);
        lt.addNode("\n");
        lt.addNode("vit", config.term.vita);
        lt.addNode("\n");
        lt.addNode("luck", config.term.luck);
        lt.addNode("\n");

        lt.addNode("hp", config.term.hp);
        lt.addNode("\n");
        lt.addNode("sp", config.term.sp);
        lt.addNode("\n");
        lt.addNode("atk", config.term.atk);
        lt.addNode("\n");
        lt.addNode("def", config.term.def);
        lt.addNode("\n");
        lt.addNode("matk", config.term.matk);
        lt.addNode("\n");
        lt.addNode("mdef", config.term.mdef);
        lt.addNode("\n");

        lt.addNode("item", config.term.item);
        lt.addNode("\n");
        lt.addNode("equip", config.term.equip);
        lt.addNode("\n");
        lt.addNode("skill", config.term.skill);
        lt.addNode("\n");

        lt.addNode("helm", config.term.helm);
        lt.addNode("\n");
        lt.addNode("armour", config.term.armour);
        lt.addNode("\n");
        lt.addNode("weapon", config.term.weapon);
        lt.addNode("\n");
        lt.addNode("shield", config.term.shield);
        lt.addNode("\n");
        lt.addNode("boots", config.term.boots);
        lt.addNode("\n");
        lt.addNode("jewelry", config.term.jewelry);
        lt.addNode("\n");

        if (config.system.startBattleSound == null || config.system.startBattleSound.equals("")) {
            lt.addNode("startBattleSound", "nil");
        } else {
            lt.addNode("startBattleSound", "/audio/sound/" + config.system.startBattleSound);
        }
        lt.addNode("\n");
        if (config.system.winBattleSound == null || config.system.winBattleSound.equals("")) {
            lt.addNode("winSound", "nil");
        } else {
            lt.addNode("winSound", "/audio/sound/" + config.system.winBattleSound);
        }
        lt.addNode("\n");
        if (config.system.failSound == null || config.system.failSound.equals("")) {
            lt.addNode("failSound", "nil");
        } else {
            lt.addNode("failSound", "/audio/sound/" + config.system.failSound);
        }
        lt.addNode("\n");
        if (config.system.endAniIndex == -1) {
            lt.addNode("endAniId", -1);
        } else {
            lt.addNode("endAniId", Configuration.Prefix.ANIMATION_MASK + config.system.endAniIndex + 1);
        }
        lt.addNode("\n");
        if (config.system.escapeSound == null || config.system.escapeSound.equals("")) {
            lt.addNode("escapeSound", "nil");
        } else {
            lt.addNode("escapeSound", "/audio/sound/" + config.system.escapeSound);
        }
        lt.addNode("\n");
        if (config.system.selectedSound == null || config.system.selectedSound.equals("")) {
            lt.addNode("selectedSound", "nil");
        } else {
            lt.addNode("selectedSound", "/audio/sound/" + config.system.selectedSound);
        }
        lt.addNode("\n");
        if (config.system.confirmSound == null || config.system.confirmSound.equals("")) {
            lt.addNode("confirmSound", "nil");
        } else {
            lt.addNode("confirmSound", "/audio/sound/" + config.system.confirmSound);
        }
        lt.addNode("\n");
        if (config.system.cancelSound == null || config.system.cancelSound.equals("")) {
            lt.addNode("cancelSound", "nil");
        } else {
            lt.addNode("cancelSound", "/audio/sound/" + config.system.cancelSound);
        }
        lt.addNode("\n");
        if (config.system.warnSound == null || config.system.warnSound.equals("")) {
            lt.addNode("warnSound", "nil");
        } else {
            lt.addNode("warnSound", "/audio/sound/" + config.system.warnSound);
        }
        lt.addNode("\n");
        if (config.system.equipSound == null || config.system.equipSound.equals("")) {
            lt.addNode("equipSound", "nil");
        } else {
            lt.addNode("equipSound", "/audio/sound/" + config.system.equipSound);
        }
        lt.addNode("\n");
        if (config.system.shopSound == null || config.system.shopSound.equals("")) {
            lt.addNode("shopSound", "nil");
        } else {
            lt.addNode("shopSound", "/audio/sound/" + config.system.shopSound);
        }
        lt.addNode("\n");
        if (config.system.readSound == null || config.system.readSound.equals("")) {
            lt.addNode("loadSound", "nil");
        } else {
            lt.addNode("loadSound", "/audio/sound/" + config.system.readSound);
        }
        lt.addNode("\n");
        if (config.system.saveSound == null || config.system.saveSound.equals("")) {
            lt.addNode("saveSound", "nil");
        } else {
            lt.addNode("saveSound", "/audio/sound/" + config.system.saveSound);
        }
        lt.addNode("\n");

//        LuaTable ltPrefixList = new LuaTable();
//
//        ltPrefixList.addNode("[" + Configuration.Prefix.VOCATION + "]", 0);
//        ltPrefixList.addNode("[" + Configuration.Prefix.PLAYER + "]", 1);
//        ltPrefixList.addNode("[" + Configuration.Prefix.SKILL + "]", 2);
//        ltPrefixList.addNode("[" + Configuration.Prefix.ITEM + "]", 3);
//        ltPrefixList.addNode("[" + Configuration.Prefix.EQUIP + "]", 4);
//        ltPrefixList.addNode("[" + Configuration.Prefix.ENEMY + "]", 5);
//        ltPrefixList.addNode("[" + Configuration.Prefix.ENEMYTROOP + "]", 6);
//        ltPrefixList.addNode("[" + Configuration.Prefix.STATUS + "]", 7);
//        ltPrefixList.addNode("[" + Configuration.Prefix.MAP + "]", 8);
//        ltPrefixList.addNode("[" + Configuration.Prefix.NPC + "]", 9);
//        ltPrefixList.addNode("[" + Configuration.Prefix.ANIMATION + "]", 10);
//        ltPrefixList.addNode("[" + Configuration.Prefix.SCRIPT + "]", 11);
//        ltPrefixList.addNode("[" + Configuration.Prefix.ATTRIBUTE + "]", 12);
//        lt.addNode("idPrefixList", ltPrefixList);

        lt.addNode("\n");
        LuaTable ltAttrs = new LuaTable();
        ltAttrs.addNode("\n");
        for (int i = 0; i < config.system.attributes.size(); i++) {
            LuaTable ltAttr = new LuaTable();
            ltAttr.addNode("index", Configuration.Prefix.ATTRIBUTE_MASK + config.system.attributes.get(i).id + 1);
            ltAttr.addNode("name", config.system.attributes.get(i).name);
            ltAttr.addNode("desc", config.system.attributes.get(i).description);
            ltAttrs.addNode("[" + (Configuration.Prefix.ATTRIBUTE_MASK + config.system.attributes.get(i).id + 1) + "]", ltAttr);
            if (i != config.system.attributes.size() - 1) {
                ltAttrs.addNode("\n");
            }
        }
        lt.addNode("attributes", ltAttrs);
        LuaTable ltFormulas = new LuaTable();
        ltFormulas.addNode("str", config.system.strFormula);
        ltFormulas.addNode("\n");
        ltFormulas.addNode("agi", config.system.agiFormula);
        ltFormulas.addNode("\n");
        ltFormulas.addNode("int", config.system.intFormula);
        ltFormulas.addNode("\n");
        ltFormulas.addNode("dex", config.system.dexFormula);
        ltFormulas.addNode("\n");
        ltFormulas.addNode("vit", config.system.vitFormula);
        ltFormulas.addNode("\n");
        ltFormulas.addNode("luck", config.system.luckFormula);
        ltFormulas.addNode("\n");
        ltFormulas.addNode("HP", config.system.HPFormula);
        ltFormulas.addNode("\n");
        ltFormulas.addNode("SP", config.system.SPFormula);
        ltFormulas.addNode("\n");
        ltFormulas.addNode("hpr", config.system.hprFormula);
        ltFormulas.addNode("\n");
        ltFormulas.addNode("spr", config.system.sprFormula);
        ltFormulas.addNode("\n");
        ltFormulas.addNode("atk", config.system.atkFormula);
        ltFormulas.addNode("\n");
        ltFormulas.addNode("def", config.system.defFormula);
        ltFormulas.addNode("\n");
        ltFormulas.addNode("matk", config.system.matkFormula);
        ltFormulas.addNode("\n");
        ltFormulas.addNode("mdef", config.system.mdefFormula);
        ltFormulas.addNode("\n");
        ltFormulas.addNode("hit", config.system.hitFormula);
        ltFormulas.addNode("\n");
        ltFormulas.addNode("flee", config.system.fleeFormula);
        ltFormulas.addNode("\n");
        ltFormulas.addNode("aspd", config.system.aspdFormula);
        ltFormulas.addNode("\n");
        ltFormulas.addNode("mspd", config.system.mspdFormula);
        ltFormulas.addNode("\n");
        ltFormulas.addNode("cri", config.system.criFormula);
        ltFormulas.addNode("\n");
        ltFormulas.addNode("sta", config.system.staFormula);
        ltFormulas.addNode("\n");
        lt.addNode("formulas", ltFormulas);
        LuaNode ln = new LuaNode("Dictionary.config", lt);
        try {
            LuaFileUtil.writeToFile(ln, AppData.getInstance().getCurProject().getPath() + "/data/config.gat");
            return true;
        } catch (IOException ex) {
//            System.out.println("system.gat 写入失败");
            printer.e("config.gat 写入失败");
            return false;
        }
    }

    private boolean saveBin() {
        DataOutputStream dos = null;
        FileOutputStream fos = null;
        File f = null;
        Config config = null;
        if (AppData.getInstance().getCurProject().getDataManager().getModels(Model.CONFIG).length <= 0) {
            config = new Config();
        } else {
            config = (Config) AppData.getInstance().getCurProject().getDataManager().getModels(Model.CONFIG)[0];
        }
//        if (config == null) {
//            return false;
//        }
        try {
            f = new File(AppData.getInstance().getCurProject().getPath() + "/softdata/config.gat");
            fos = new FileOutputStream(f);
            dos = new DataOutputStream(fos);
            dos.writeUTF(config.term.name);
            dos.writeUTF(config.term.help);
            dos.writeUTF(config.term.about);
            dos.writeUTF(config.term.gold);
            dos.writeInt(config.system.initPlayers.size());
            for (int i = 0; i < config.system.initPlayers.size(); i++) {
                dos.writeInt(config.system.initPlayers.get(i).getIndex());
            }
            dos.writeInt(config.system.attributes.size());
            for (int i = 0; i < config.system.attributes.size(); i++) {
                dos.writeInt(config.system.attributes.get(i).id);
                dos.writeUTF(config.system.attributes.get(i).name);
                dos.writeUTF(config.system.attributes.get(i).description);
            }
            dos.writeInt(config.system.curMapIndex);
            dos.writeInt(config.system.row);
            dos.writeInt(config.system.col);
            dos.writeByte(config.system.face);
            dos.writeUTF(config.term.hp);
            dos.writeUTF(config.term.sp);
            dos.writeUTF(config.term.atk);
            dos.writeUTF(config.term.def);
            dos.writeUTF(config.term.matk);
            dos.writeUTF(config.term.mdef);
            dos.writeUTF(config.term.stre);
            dos.writeUTF(config.term.inte);
            dos.writeUTF(config.term.agil);
            dos.writeUTF(config.term.dext);
            dos.writeUTF(config.term.vita);
            dos.writeUTF(config.term.luck);
            dos.writeUTF(config.term.helm);
            dos.writeUTF(config.term.armour);
            dos.writeUTF(config.term.weapon);
            dos.writeUTF(config.term.shield);
            dos.writeUTF(config.term.boots);
            dos.writeUTF(config.term.jewelry);
            dos.writeUTF(config.term.item);
            dos.writeUTF(config.term.equip);
            dos.writeUTF(config.term.skill);
            dos.writeUTF(config.system.frameSkin);
            dos.writeInt(config.system.startAniIndex);
            dos.writeUTF(config.system.titleBackground);
            dos.writeUTF(config.system.titleMusic);
            dos.writeUTF(config.system.startBattleSound);
            dos.writeUTF(config.system.failSound);
            dos.writeUTF(config.system.winBattleSound);
            dos.writeUTF(config.system.escapeSound);
            dos.writeInt(config.system.endAniIndex);
            dos.writeUTF(config.system.selectedSound);
            dos.writeUTF(config.system.confirmSound);
            dos.writeUTF(config.system.cancelSound);
            dos.writeUTF(config.system.warnSound);
            dos.writeUTF(config.system.equipSound);
            dos.writeUTF(config.system.shopSound);
            dos.writeUTF(config.system.readSound);
            dos.writeUTF(config.system.saveSound);
            dos.close();
            fos.close();
            f = null;
            return true;
        } catch (IOException e) {
//            System.out.println("system.gat 写入失败");
            printer.e("config.gat 写入失败");
            return false;
        }
    }

    /**
     *
     * @return
     */
    public boolean save() {
//        System.out.println("config save");
        printer.v("config save");
        boolean value = false;
        if (saveLua()) {
            value = true;
        }
        return value;
    }

    @Override
    public boolean saveSoft() {
        printer.v("config save");
        boolean value = false;
        if (saveXML() && saveBin()) {
            value = true;
        }
        return value;
    }
}
