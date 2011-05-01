package game.impl.model;

import emulator.EmulatorFont;
import game.IModel;
import java.util.Vector;
import game.manager.GameObjectManager;

/**
 *
 * 相当于MVC中的模型（M）
 */
public class GameData implements IModel {

    public GameObjectManager gameObjectManager = new GameObjectManager();
    /**
     * 编号0-99的100个开关
     */
    public boolean[] switchs = new boolean[100];
    /**
     * 编号0-99的100个变量
     */
    public int[] vars = new int[100];
    private EmulatorFont font = EmulatorFont.getEmulatorFont(EmulatorFont.FACE_SYSTEM, EmulatorFont.STYLE_PLAIN, EmulatorFont.SIZE_LARGE);
    //公共资源
    public int screenWidth = 240;
    public int screenHeight = 320;
    public int pageIndex = 0;//页面标签
    public int xIndex = 0;//水平选择项编程
    public int yIndex = 0;//竖向选择项编号
    public Player player = null;//玩家
    //游戏设置资源
    public boolean musicOn = false;//音乐开关
    public boolean soundOn = false;//音效开关
    //地图视图资源
    public Map curMap = null;//当前地图
    public int map_menuIndex = 0;//系统菜单的索引
    //技能视图资源
    public int skill_topIndex = 0;
    public int skill_curIndex = 0;
    public int skill_showNum = 0;
    //装备视图资源
    public int equip_pageIndex = 0;
    public int equip_curEquip = 0;
    public int equip_selectIndex = 0;
    //背包视图资源
    public int bag_topIndex = 0;
    public int bag_tabIndex = 0;
    public int bag_curIndex = 0;
    public int bag_showNum = 0;
    public int bag_pageIndex = 0;
    public String[] items = null;
    public String[] equips = null;
    //商店视图资源
    public byte shop_type = 0;//0 物品商量   1 装备商店
    public int[] shop_list = null;//商店物品列表
    public int shop_pageIndex = 0;
    public int shop_tabIndex = 0;
    public int shop_topIndex = 0;
    public int shop_curIndex = 0;
    public int shop_showNum = 0;
    public int shop_itemMaxNum = 0;
    public int shop_itemNum = 0;
    public boolean shop_needRebuild = false;
    public String shop_message = "";
    public BaseItem[] shop_items_buy = null;
    public BaseItem[] shop_items_sell = null;
    //对话框事件
    public String dialog_name;//对话人名
    public String[] dialog_content;//对话内容
    public int dialog_index = 0;//当前要要显示对话内容的第一行的编号
    //等待事件
    public int waitTime = 0;//等待的总时间
    public int waitIndex = 0;//当前已经等待的时间
    //移动事件
    public byte[] moveOrder = null;//移动指令序列
    public int moveIndex = 0;//当前要移动的指令下标
    //战斗视图资源
    public int enemyTroopID = 0;//对战敌人队伍ID
    /************************************************/
    //战斗界面控制变量 11.2
    public int select = 0,//主界面选择标记
            Select_Eny = 0,//普通攻击敌人选择标记
            Select_Good = 0,//物品选择标记
            Top_Good = 0,//物品选择标记
            Select_Magic = 0,//技能选择标记
            Top_Magic = 0;//物品选择标记
//            Select_Equip = 0;//装备选择标记
    public int Select_Magic_Eny = 0,//技能选择敌人界面标记
            Select_Magic_Main = 0;//技能选择主界面标记
    public int Select_Item_Eny = 0,//物品选择敌人界面标记
            Select_Item_Main = 0;//物品选择主界面标记
    public int attackType = 0; //攻击类型
//    public int enemySum = 0;//敌人总数
    public Enemy[] enemy = new Enemy[4];//最多4个敌人
    public int enemyShould;//轮到的敌人id
    public boolean isNotHitEnemy = false;//英雄未击中敌人
    public boolean isChangeHp = false;//飘血开关
    public int upDecreaseHP = 0, upMiss = 0;//飘血和飘miss变量
    public boolean isWin = false;//战斗胜利开关
    public boolean isFail = false;//战斗失败开关
    public boolean isMiss = false;
    public volatile boolean isRuning = false;
    public int allExp = 0;//所有的参加战斗的敌人加起来的经验
    public int allMoney = 0;//同上
    /* -2敌人魔法攻击
     * -1敌人物理攻击
     * 1 普通攻击
     * 2 单体恢复性法术
     * 3 单体攻击性法术
     * 4 全体攻击性法术
     * 5 单体攻击性物品
     * 6 全体攻击性物品
     * 7 单体恢复性物品
     */
    /******************地图界面菜单变量***********************/
    public int menuIndex = -1;
    public int itemMainSelect = 0;
    public int Item_Select_Good = 0,//物品选择标记
            Item_Top_Good = 0,//物品选择标记
            Item_Select_Equip = 0,//技能选择标记
            Item_Top_Equip = 0;//物品选择标记
    public int equipSelect = 0;
    public int skillSelect = 0;
    public int Skill_Top_Magic = 0;
    public int winSelect = 0;

    public void setDialog(String name, String content) {
        //此算法基本是失败的，需要重写
        this.dialog_name = name;
        int num = screenWidth / font.stringWidth("好");//每行能显示的字数
        int row = content.length() / num + 1;//要显示的行数
        Vector v = new Vector();
        int p = 0, q = 0;
        EmulatorFont f = Const.Font.FONTSMALL_PLAIN;
        String temp = null;
        while (p < content.length()) {
            if (q == content.length()) {
                v.addElement(content.substring(p, q));
                p = q;
            } else {
                temp = content.substring(p, q);
                if (f.stringWidth(temp) > screenWidth - 20) {
                    v.addElement(content.substring(p, q - 1));
                    p = q - 1;
                } else {
                    q++;
                }
            }

        }
        dialog_content = new String[v.size()];
        for (int i = 0; i < dialog_content.length; i++) {
            dialog_content[i] = (String) v.elementAt(i);
        }
        v.removeAllElements();
        v = null;
    }

    private GameData() {
        super();
    }

    public void buildItems() {
        items = new String[player.bag.getList(Bag.ITEM).length];
        for (int i = 0; i < items.length; i++) {
            Item tempItem = (Item) player.bag.get(Bag.ITEM, player.bag.getList(Bag.ITEM)[i]);
            items[i] = tempItem.name + "  数量：" + tempItem.num;
        }

        equips = new String[player.bag.getList(Bag.EQUIP).length];
        for (int i = 0; i < equips.length; i++) {
            Equip tempEquip = (Equip) player.bag.get(Bag.EQUIP, player.bag.getList(Bag.EQUIP)[i]);
            equips[i] = Const.Str.KINDS[tempEquip.kind] + "  " + tempEquip.name + "  数量：" + tempEquip.num;
        }
    }

    public void buildList_buy() {
        shop_items_buy = new BaseItem[shop_list.length];
        for (int i = 0; i < shop_items_buy.length; i++) {
            shop_items_buy[i] = gameObjectManager.getBaseItem(shop_type == 0 ? Bag.ITEM : Bag.EQUIP, shop_list[i]);
        }
    }

    public void buildList_sell() {
        shop_items_sell = new BaseItem[player.bag.getList(shop_type == 0 ? Bag.ITEM : Bag.EQUIP).length];
        for (int i = 0; i < shop_items_sell.length; i++) {
            shop_items_sell[i] = player.bag.get(shop_type == 0 ? Bag.ITEM : Bag.EQUIP, player.bag.getList(shop_type == 0 ? Bag.ITEM : Bag.EQUIP)[i]);
        }
    }

    public void update() {
    }
}
