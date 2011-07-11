package emulator.model;

import emulator.EmulatorImage;
import emulator.engine.script.ScriptEngine;
import system.*;

/**
 * 主角 cokey2
 */
public final class Player extends Character {

    private ScriptEngine se = ScriptEngine.getInstance();
    public static final int UP = 0;// 面向上
    public static final int DOWN = 1;// 面向下
    public static final int LEFT = 2;// 面向左
    public static final int RIGHT = 3;// 面向右
    public static final int STEP1 = 1;// 第一步
    public static final int STEP2 = 2;// 第二步
    public static final int STEP3 = 3;// 第三步
    public static final int STEP4 = 0;// 第四步
    public int step = 0;// 角色行走的步数
    public int[] levList = null;//经验表
    public int maxLev = 0;//封顶等级
    public int streByLev;// 力量成长
    public int agilByLev;// 敏捷成长
    public int inteByLev;// 智力成长
    public int curMapIndex;// 角色当前所在地图编号
    public int row = 10;// 角色所在行号
    public int col = 4;// 角色当前所在列号
    public int face = DOWN;// 面向
    private EmulatorImage curCharImg = null;
    public int width = 16;// 角色宽度
    public int height = 24;// 角色高度
    private GameData gd = GameData.getGameData();
    public int x, y;// 角色当前在地图上的坐标
    public int startTime = 0;// 角色开始移动的时间
    /********************************************************/
    public int heroBattX = 150, heroBattY = 180;//战斗时坐标
    public int changeHp;
    /********************************************************/
//    public ArrayList skillList;
//    public int[] itemList;
//    public int[] equipList;
    public Bag bag = new Bag();
    /**
     * 头盔
     */
    public int equipHelm = -1;
    /**
     * 铠甲
     */
    public int equipArmour = -1;
    /**
     * 武器
     */
    public int equipWeapon = -1;
    /**
     * 盾牌
     */
    public int equipShield = -1;
    /**
     * 战靴
     */
    public int equipBoots = -1;
    /**
     * 饰品
     */
    public int equipJewelry = -1;

    public Player() {
        super();
        bag = new Bag();
    }
    //      初始化测试

    public void init() {
//        bag.add(Bag.SKILL, 0, 1);
//        bag.add(Bag.SKILL, 1, 1);
//        bag.add(Bag.SKILL, 2, 1);
//        bag.add(Bag.ITEM, 0, 5);
//        bag.add(Bag.ITEM, 1, 2);
//        bag.add(Bag.ITEM, 2, 1);
//        bag.add(Bag.EQUIP, 0, 1);
//        bag.add(Bag.EQUIP, 1, 1);
//        addEquip(0);
        updateProperties();
    }

    public void addExp(int exp) {
        this.exp += exp;
        checkLevelUp();
    }

    private void checkLevelUp() {
        if (exp >= this.levList[lev]) {
            levelUp();
            checkLevelUp();//递归
        }
    }

    public void useItem(int index) {

        if (bag.getList(Bag.ITEM).length > 0) {
            Item item = gd.gameObjectManager.getItem(index);
            if (item.kind == Item.ITEM_ASSIST) {//若物品种类为任务物品，则无法食用
                stre += item.stre;//增加/减少力量
                agil += item.agil;//增加/减少敏捷
                inte += item.inte;//增加/减少智力
                hp += item.hp;//增加/减少血量
                sp += item.sp;//增加/减少魔法值
                maxHp += item.maxHp;//增加/减少最大血量
                maxSp += item.maxSp;//增加/减少最大魔法值
                lev += item.lev;//增加等级
                atk += item.atk;//增加/减少攻击
                def += item.def;//增加/减少防御
                flee += item.flee;//增加/减少闪避
                exp += item.exp;//增加经验值
                if (hp > maxHp) {
                    hp = maxHp;
                }
                if (sp > maxSp) {
                    sp = maxSp;
                }
                bag.del(Bag.ITEM, index, 1);
            }
        }
    }

    public void levelUp() {
        exp -= this.levList[lev];
        this.lev++;
        updateProperties();
    }
    //将装备包中的编号为index的装备 装备在英雄身上

    public void takeOnEquip(int index) {
        if (bag.has(Bag.EQUIP, index)) {
            bag.del(Bag.EQUIP, index, 1);
            switch (gd.gameObjectManager.getEquip(index).kind) {
                case BaseItem.EQUIP_ARMOUR:
                    if (equipArmour != -1) {
                        bag.add(Bag.EQUIP, equipArmour, 1);
                    }
                    equipArmour = index;
                    break;
                case BaseItem.EQUIP_BOOTS:
                    if (equipBoots != -1) {
                        bag.add(Bag.EQUIP, equipBoots, 1);
                    }
                    equipBoots = index;
                    break;
                case BaseItem.EQUIP_HELM:
                    if (equipHelm != -1) {
                        bag.add(Bag.EQUIP, equipHelm, 1);
                    }
                    equipHelm = index;
                    break;
                case BaseItem.EQUIP_JEWELRY:
                    if (equipJewelry != -1) {
                        bag.add(Bag.EQUIP, equipJewelry, 1);
                    }
                    equipJewelry = index;
                    break;
                case BaseItem.EQUIP_SHIELD:
                    if (equipShield != -1) {
                        bag.add(Bag.EQUIP, equipShield, 1);
                    }
                    equipShield = index;
                    break;
                case BaseItem.EQUIP_WEAPON:
                    if (equipWeapon != -1) {
                        bag.add(Bag.EQUIP, equipWeapon, 1);
                    }
                    equipWeapon = index;
                    break;
            }
        }
        updateState();
    }

    //将英雄身上的装备脱下放到装备背包
    public void takeOffEquip(int index) {
        switch (gd.gameObjectManager.getEquip(index).kind) {
            case BaseItem.EQUIP_ARMOUR:
                if (equipArmour != -1) {
                    bag.add(Bag.EQUIP, index, 1);
                    equipArmour = -1;
                }
                break;
            case BaseItem.EQUIP_BOOTS:
                if (equipBoots != -1) {
                    bag.add(Bag.EQUIP, index, 1);
                    equipBoots = -1;
                }
                break;
            case BaseItem.EQUIP_HELM:
                if (equipHelm != -1) {
                    bag.add(Bag.EQUIP, index, 1);
                    equipHelm = -1;
                }
                break;
            case BaseItem.EQUIP_JEWELRY:
                if (equipJewelry != -1) {
                    bag.add(Bag.EQUIP, index, 1);
                    equipJewelry = -1;
                }
                break;
            case BaseItem.EQUIP_SHIELD:
                if (equipShield != -1) {
                    bag.add(Bag.EQUIP, index, 1);
                    equipShield = -1;
                }
                break;
            case BaseItem.EQUIP_WEAPON:
                if (equipWeapon != -1) {
                    bag.add(Bag.EQUIP, index, 1);
                    equipWeapon = -1;
                }
                break;
        }

        updateState();
    }

    /*
     * 相关公式
     * stre=stre(力量初值)+streByLev*lev	//力量
     * agil=agil(敏捷初值)+agilByLev*lev	//敏捷
     * inte=inte(智力初值)+inteByLev*lev    //智力
     * maxHp=stre*19			//生命值
     * maxSp=inte*17			//魔法值
     * atk=stre*3			//攻击力
     * def=agil/3		//防御力
     * flee=agil/10		//闪避值
     *
     */
    //属性更新
    public void updateProperties() {
        updateState();
        hp = maxHp;
        sp = maxSp;

    }

    public Player getClone() {
        Player player = new Player();
        player.name = this.name;
        player.intro = this.intro;
        player.curMapIndex = this.curMapIndex;
        player.row = this.row;
        player.col = this.col;
        player.face = this.face;
        player.headImg = this.headImg;
        player.setCharImg(this.chaImage);
        player.streByLev = this.streByLev;
        player.inteByLev = this.inteByLev;
        player.agilByLev = this.agilByLev;
        player.maxLev = this.maxLev;
        player.levList = this.levList;
        player.agil = this.agil;
        player.atk = this.atk;
        player.def = this.def;
        player.flee = this.flee;
        player.stre = this.stre;
        player.inte = this.inte;
        player.hp = this.hp;
        player.sp = this.sp;
        player.money = this.money;

//        TestData.initPlayer(player);
        return player;
    }
    //装备影响属性函数

    public void updateState() {
        stre = gd.gameObjectManager.getPlayer().stre + streByLev * lev + 1;//初始值为
        agil = gd.gameObjectManager.getPlayer().agil + agilByLev * lev + 1;//初始值为
        inte = gd.gameObjectManager.getPlayer().inte + inteByLev * lev + 1;//初始值为
        maxHp = gd.gameObjectManager.getPlayer().hp + stre * 20;//初始值为
        maxSp = gd.gameObjectManager.getPlayer().sp + inte * 15;//初始值为
        atk = stre * 3 + gd.gameObjectManager.getPlayer().atk;//初始值为
        def = agil / 3 + gd.gameObjectManager.getPlayer().def;//初始值为
        flee = agil / 10 + gd.gameObjectManager.getPlayer().flee;//初始值为
        int equipList[] = new int[6];
        equipList[0] = equipHelm;
        equipList[1] = equipArmour;
        equipList[2] = equipWeapon;
        equipList[3] = equipShield;
        equipList[4] = equipBoots;
        equipList[5] = equipJewelry;
        for (int i = 0; i < 6; i++) {
            if (equipList[i] == -1) {
                continue;
            }
            this.maxHp += gd.gameObjectManager.getEquip(equipList[i]).maxHp;
            this.agil += gd.gameObjectManager.getEquip(equipList[i]).agil;
            this.def += gd.gameObjectManager.getEquip(equipList[i]).def;
            this.atk += gd.gameObjectManager.getEquip(equipList[i]).atk;
            this.flee += gd.gameObjectManager.getEquip(equipList[i]).flee;
            this.inte += gd.gameObjectManager.getEquip(equipList[i]).inte;
            this.stre += gd.gameObjectManager.getEquip(equipList[i]).stre;
            this.maxSp += gd.gameObjectManager.getEquip(equipList[i]).maxSp;
            this.maxHp += gd.gameObjectManager.getEquip(equipList[i]).maxHp;
        }
    }

    /**
     * 判断英雄是否死亡
     *
     * @return
     */
    public boolean isDead() {
        if (hp <= 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 是否闪避
     *
     * @return
     */
    public boolean isMiss() {
        int random = Tools.GetRandom(100);
        if (random > -1 && random < flee) {
            return true;
        } else {
            return false;
        }
    }

    public EmulatorImage getCurCharImg() {
        return curCharImg;
    }
    /**
     * 上 00立正 01左脚 02右脚 下 10立正 11左脚 12右脚 左 20立正 21左脚 22右脚 右 30立正 31左脚 32右脚
     */
    private EmulatorImage[][] steps = new EmulatorImage[4][3];

    public void setCharImg(EmulatorImage img) {
        chaImage = img;
        width = img.getWidth() / 7;
        height = img.getHeight();
        steps[0][0] = EmulatorImage.createImage(img, 0, 0, width, height, 0);// 上立正
        steps[0][1] = EmulatorImage.createImage(img, width, 0, width, height, 2);// 上左脚
        steps[0][2] = EmulatorImage.createImage(img, width, 0, width, height, 0);// 上右脚

        steps[1][0] = EmulatorImage.createImage(img, width * 2, 0, width, height, 0);// 下立正
        steps[1][1] = EmulatorImage.createImage(img, width * 3, 0, width, height, 2);// 下左脚
        steps[1][2] = EmulatorImage.createImage(img, width * 3, 0, width, height, 0);// 下右脚

        steps[2][0] = EmulatorImage.createImage(img, width * 4, 0, width, height, 0);// 左立正
        steps[2][1] = EmulatorImage.createImage(img, width * 6, 0, width, height, 0);// 左左脚
        steps[2][2] = EmulatorImage.createImage(img, width * 5, 0, width, height, 0);// 左右脚

        steps[3][0] = EmulatorImage.createImage(img, width * 4, 0, width, height, 2);// 右立正
        steps[3][1] = EmulatorImage.createImage(img, width * 6, 0, width, height, 2);// 右左脚
        steps[3][2] = EmulatorImage.createImage(img, width * 5, 0, width, height, 2);// 右右脚

        curCharImg = steps[face][0];
    }
    public EmulatorImage chaImage = null;

    /**
     * 向上行走
     */
    public void moveUp() {
        if (row == 0 || gd.curMap.way[row - 1][col] == 1) {
            // 如果角色已经在地图的最上面则不能再向上
            return;
        }
        step++;
        switch (step % 4) {
            case STEP1:
                y -= gd.curMap.cellHeight / 4;
                curCharImg = steps[UP][1];
                break;
            case STEP2:
                y -= gd.curMap.cellHeight / 4;
                curCharImg = steps[UP][0];
                break;
            case STEP3:
                y -= gd.curMap.cellHeight / 4;
                curCharImg = steps[UP][2];
                break;
            case STEP4:
                y -= gd.curMap.cellHeight / 4;
                curCharImg = steps[UP][0];
                row--;
                checkTouchScript();
                break;
        }
    }

    /**
     * 向下行走
     */
    public void moveDown() {
        if (row == gd.curMap.rowNum - 1 || gd.curMap.way[row + 1][col] == 1) {
            // 如果角色已经在地图的最下面则不能再向下
            return;
        }
        step++;
        switch (step % 4) {
            case STEP1:
                y += gd.curMap.cellHeight / 4;
                curCharImg = steps[DOWN][1];
                break;
            case STEP2:
                y += gd.curMap.cellHeight / 4;
                curCharImg = steps[DOWN][0];
                break;
            case STEP3:
                y += gd.curMap.cellHeight / 4;
                curCharImg = steps[DOWN][2];
                break;
            case STEP4:
                y += gd.curMap.cellHeight / 4;
                curCharImg = steps[DOWN][0];
                row++;
                checkTouchScript();
                break;
        }
    }

    /**
     * 向左行走
     */
    public void moveLeft() {
        if (col == 0 || gd.curMap.way[row][col - 1] == 1) {
            // 如果角色已经在地图的最左面则不能再向左
            return;
        }
        step++;
        switch (step % 4) {
            case STEP1:
                x -= gd.curMap.cellWidth / 4;
                curCharImg = steps[LEFT][1];
                break;
            case STEP2:
                x -= gd.curMap.cellWidth / 4;
                curCharImg = steps[LEFT][0];
                break;
            case STEP3:
                x -= gd.curMap.cellWidth / 4;
                curCharImg = steps[LEFT][2];
                break;
            case STEP4:
                x -= gd.curMap.cellWidth / 4;
                curCharImg = steps[LEFT][0];
                col--;
                checkTouchScript();
                break;
        }
    }

    /**
     * 向右行走
     */
    public void moveRight() {
        if (col == gd.curMap.colNum - 1 || gd.curMap.way[row][col + 1] == 1) {
            // 如果角色已经在地图的最左面则不能再向左
            return;
        }
        step++;
        switch (step % 4) {
            case STEP1:
                x += gd.curMap.cellWidth / 4;
                curCharImg = steps[RIGHT][1];
                break;
            case STEP2:
                x += gd.curMap.cellWidth / 4;
                curCharImg = steps[RIGHT][0];
                break;
            case STEP3:
                x += gd.curMap.cellWidth / 4;
                curCharImg = steps[RIGHT][2];
                break;
            case STEP4:
                x += gd.curMap.cellWidth / 4;
                curCharImg = steps[RIGHT][0];
                col++;
                checkTouchScript();
                break;
        }
    }

    public void move(int face) {
        this.face = face;
        curCharImg = steps[face][0];
        switch (face) {
            case UP:
                moveUp();
                break;
            case DOWN:
                moveDown();
                break;
            case LEFT:
                moveLeft();
                break;
            case RIGHT:
                moveRight();
                break;

        }
        gd.curMap.resetRegion(this);
    }

    public void setLocation() {
//        this.row = row;
//        this.col = col;
        x = col * gd.curMap.cellWidth + (gd.curMap.cellWidth - width) / 2;
        y = row * gd.curMap.cellHeight - (height - gd.curMap.cellHeight);
    }

    /**
     * 停歇，角色在行走时两步之间的停歇
     */
    public void dwell() {
        try {
            Thread.sleep(50);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    public void changeToward(byte toward) {
        switch (toward) {
            case UP:
                curCharImg = steps[UP][0];
                break;
            case DOWN:
                curCharImg = steps[DOWN][0];
                break;
            case LEFT:
                curCharImg = steps[LEFT][0];
                break;
            case RIGHT:
                curCharImg = steps[RIGHT][0];
                break;
        }

    }

    private void checkTouchScript() {
        if (gd.curMap.scriptType[gd.player.row][gd.player.col] == 1 || gd.curMap.scriptType[gd.player.row][gd.player.col] == 2) {
            se.addScript(gd.curMap.getScript(gd.player.row, gd.player.col));
        }
    }
}
