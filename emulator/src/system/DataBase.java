/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package system;

import model.Bag;
import model.GameData;

/**
 *
 * @author Administrator
 */
public class DataBase {

    private GameData gd = GameData.getGameData();
    private Configuration config = new Configuration();

//    /**
//     *
//     * @param args 测试
//     */
//    public static void main(String[] args) {
//        DataBase db = new DataBase();
//        db.saveDB();
//        db.loadDB();
//    }//end main()

    public boolean saveDB() {
        config.setValue("gd.curMap.index", "" + gd.curMap.index);
        config.setValue("gd.player.lev", "" + gd.player.lev);
        config.setValue("gd.player.face", "" + gd.player.face);
        config.setValue("gd.player.row", "" + gd.player.row);
        config.setValue("gd.player.col", "" + gd.player.col);
        config.setValue("gd.player.money", "" + gd.player.money);
        config.setValue("gd.player.exp", "" + gd.player.exp);
        config.setValue("gd.player.agil", "" + gd.player.agil);
        config.setValue("gd.player.atk", "" + gd.player.atk);
        config.setValue("gd.player.def", "" + gd.player.def);
        config.setValue("gd.player.flee", "" + gd.player.flee);
        config.setValue("gd.player.inte", "" + gd.player.inte);
        config.setValue("gd.player.stre", "" + gd.player.stre);
        config.setValue("gd.player.maxHp", "" + gd.player.maxHp);
        config.setValue("gd.player.maxSp", "" + gd.player.maxSp);
        config.setValue("gd.player.equipArmour", "" + gd.player.equipArmour);
        config.setValue("gd.player.equipBoots", "" + gd.player.equipBoots);
        config.setValue("gd.player.equipHelm", "" + gd.player.equipHelm);
        config.setValue("gd.player.equipJewelry", "" + gd.player.equipJewelry);
        config.setValue("gd.player.equipShield", "" + gd.player.equipShield);
        config.setValue("gd.player.equipWeapon", "" + gd.player.equipWeapon);
        config.setValue("gd.player.bag.getList(Bag.EQUIP).length", "" + gd.player.bag.getList(Bag.EQUIP).length);
        for (int i = 0, n = gd.player.bag.getList(Bag.EQUIP).length; i < n; i++) {
            config.setValue("gd.player.bag.getList(Bag.EQUIP)[" + i + "]", "" + gd.player.bag.getList(Bag.EQUIP)[i]);
            config.setValue("gd.player.bag.getNum(Bag.EQUIP, gd.player.bag.getList(Bag.EQUIP)[" + i + "])",
                "" + gd.player.bag.getNum(Bag.EQUIP, gd.player.bag.getList(Bag.EQUIP)[i]));
        }
        config.setValue("gd.player.bag.getList(Bag.ITEM).length", "" + gd.player.bag.getList(Bag.ITEM).length);
        for (int i = 0, n = gd.player.bag.getList(Bag.ITEM).length; i < n; i++) {
            config.setValue("gd.player.bag.getList(Bag.ITEM)[" + i + "]", "" + gd.player.bag.getList(Bag.EQUIP)[i]);
            config.setValue("gd.player.bag.getNum(Bag.ITEM, gd.player.bag.getList(Bag.ITEM)[" + i + "])",
                "" + gd.player.bag.getNum(Bag.ITEM, gd.player.bag.getList(Bag.ITEM)[i]));
        }
        config.setValue("gd.player.bag.getList(Bag.SKILL).length", "" + gd.player.bag.getList(Bag.EQUIP).length);
        for (int i = 0, n = gd.player.bag.getList(Bag.SKILL).length; i < n; i++) {
            config.setValue("gd.player.bag.getList(Bag.SKILL)[" + i + "]", "" + gd.player.bag.getList(Bag.EQUIP)[i]);
            config.setValue("gd.player.bag.getNum(Bag.SKILL, gd.player.bag.getList(Bag.SKILL)[" + i + "])",
                "" + gd.player.bag.getNum(Bag.SKILL, gd.player.bag.getList(Bag.SKILL)[i]));
        }
        config.setValue("gd.player.hp", "" + gd.player.hp);
        config.setValue("gd.player.sp", "" + gd.player.sp);
        for (int i = 0; i < 100; i++) {
            config.setValue("gd.vars[" + i + "]", "" + gd.vars[i]);
        }

        for (int i = 0; i < 100; i++) {
            config.setValue("gd.switchs[" + i + "]", "" + gd.switchs[i]);
        }
        return true;
    }

    public boolean loadDB() {

        int mapIndex = Integer.parseInt(config.getValue("gd.curMap.index"));
        System.out.println("mapIndex:" + mapIndex);
        gd.curMap = gd.gameObjectManager.getMap(mapIndex);
        gd.player = gd.gameObjectManager.getPlayer().getClone();
        int lev = Integer.parseInt(config.getValue("gd.player.lev"));
        System.out.println("lev:" + lev);
        gd.player.lev = lev;
        byte face = Byte.parseByte(config.getValue("gd.player.face"));
        System.out.println("face:" + face);
        gd.player.face = face;
        gd.player.changeToward(face);
        int row = Integer.parseInt(config.getValue("gd.player.row"));
        System.out.println("row:" + row);
        gd.player.row = row;
        int col = Integer.parseInt(config.getValue("gd.player.col"));
        System.out.println("col:" + col);
        gd.player.col = col;
        gd.player.money = Integer.parseInt(config.getValue("gd.player.money"));
        System.out.println("money:" + gd.player.money);
        gd.player.exp = Integer.parseInt(config.getValue("gd.player.exp"));
        System.out.println("exp:" + gd.player.exp);

        gd.player.agil = Integer.parseInt(config.getValue("gd.player.agil"));
        gd.player.atk = Integer.parseInt(config.getValue("gd.player.atk"));
        gd.player.def = Integer.parseInt(config.getValue("gd.player.def"));
        gd.player.flee = Integer.parseInt(config.getValue("gd.player.flee"));
        gd.player.inte = Integer.parseInt(config.getValue("gd.player.inte"));
        gd.player.stre = Integer.parseInt(config.getValue("gd.player.stre"));
        gd.player.maxHp = Integer.parseInt(config.getValue("gd.player.maxHp"));
        gd.player.maxSp = Integer.parseInt(config.getValue("gd.player.maxSp"));

        gd.player.equipArmour = Integer.parseInt(config.getValue("gd.player.equipArmour"));
        System.out.println("equipArmour:" + gd.player.equipArmour);
        gd.player.equipBoots = Integer.parseInt(config.getValue("gd.player.equipBoots"));
        System.out.println("equipBoots:" + gd.player.equipBoots);
        gd.player.equipHelm = Integer.parseInt(config.getValue("gd.player.equipHelm"));
        System.out.println("equipHelm:" + gd.player.equipHelm);
        gd.player.equipJewelry = Integer.parseInt(config.getValue("gd.player.equipJewelry"));
        System.out.println("equipJewelry:" + gd.player.equipJewelry);
        gd.player.equipShield = Integer.parseInt(config.getValue("gd.player.equipShield"));
        System.out.println("equipShield:" + gd.player.equipShield);
        gd.player.equipWeapon = Integer.parseInt(config.getValue("gd.player.equipWeapon"));
        System.out.println("equipWeapon:" + gd.player.equipWeapon);
        int equipNum = Integer.parseInt(config.getValue("gd.player.bag.getList(Bag.EQUIP).length"));
        for (int i = 0; i < equipNum; i++) {
            gd.player.bag.add(Bag.EQUIP, Integer.parseInt(config.getValue("gd.player.bag.getList(Bag.EQUIP)[" + i + "]")),
                Integer.parseInt(config.getValue("gd.player.bag.getNum(Bag.EQUIP, gd.player.bag.getList(Bag.EQUIP)[" + i + "])")));
        }
        int itemNum = Integer.parseInt(config.getValue("gd.player.bag.getList(Bag.ITEM).length"));
        for (int i = 0; i < itemNum; i++) {
            gd.player.bag.add(Bag.EQUIP, Integer.parseInt(config.getValue("gd.player.bag.getList(Bag.ITEM)[" + i + "]")),
                Integer.parseInt(config.getValue("gd.player.bag.getNum(Bag.ITEM, gd.player.bag.getList(Bag.ITEM)[" + i + "])")));
        }
        int skillNum = Integer.parseInt(config.getValue("gd.player.bag.getList(Bag.SKILL).length"));
        for (int i = 0; i < skillNum; i++) {
            gd.player.bag.add(Bag.EQUIP, Integer.parseInt(config.getValue("gd.player.bag.getList(Bag.SKILL)[" + i + "]")),
                Integer.parseInt(config.getValue("gd.player.bag.getNum(Bag.SKILL, gd.player.bag.getList(Bag.SKILL)[" + i + "])")));
        }
        gd.player.setLocation();
        gd.player.hp = Integer.parseInt(config.getValue("gd.player.hp"));
        System.out.println("hp:" + gd.player.hp);
        gd.player.sp = Integer.parseInt(config.getValue("gd.player.sp"));
        System.out.println("sp:" + gd.player.sp);
        for (int i = 0; i < 100; i++) {
            gd.vars[i] = Integer.parseInt(config.getValue("gd.vars[" + i + "]"));
        }
        for (int i = 0; i < 100; i++) {
            gd.switchs[i] = Boolean.parseBoolean(config.getValue("gd.switchs[" + i + "]"));
        }
        gd.curMap.resetRegion(gd.player);

        return true;
    }
}
