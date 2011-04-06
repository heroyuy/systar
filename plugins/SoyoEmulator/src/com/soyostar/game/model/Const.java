package com.soyostar.game.model;

/**
 * 程序中所有的常量
 */
public final class Const {

    public static final class ImagePath {

        public static final String DIALOG_DEEP = "image/skin/dialog.png";
        public static final String DIALOG_LIGHT = "image/skin/dialog2.png";
        public static final String CELL_LIGHT = "image/skin/dialog3.png";
        public static final String CELL_DEEP = "image/skin/dialog4.png";
        public static final String TRENCH = "image/skin/trench.png";
        public static final String SMALLTRIANGLE = "image/skin/sja.png";
        public static final String BALL = "image/skin/ball.png";
        public static final String NUMS = "image/skin/nums.png";
        public static final String MAPMENU = "image/skin/mapmenu.png";
        public static final String SELECT = "image/skin/select.png";
        public static final String BACK = "image/skin/back.png";
    }

    /**
     * 锚点
     */
    public static final class Anchor {

        public static final int LT = 0;
        public static final int LV = 1;
        public static final int LB = 2;
        public static final int HT = 3;
        public static final int HV = 4;
        public static final int HB = 5;
        public static final int RT = 6;
        public static final int RV = 7;
        public static final int RB = 8;
    }

    /**
     *  键值
     */
    public static final class Key {

        public static final byte KEY_UP = -1;//上键
        public static final byte KEY_DOWN = -2;//下键
        public static final byte KEY_LEFT = -3;//左键
        public static final byte KEY_RIGHT = -4;//右键
        public static final byte KEY_FIRE = -5;//中键
        public static final byte KEY_LS = -6;//左软键
        public static final byte KEY_RS = -7;//右软键
        public static final byte KEY_0 = 48;//0键
        public static final byte KEY_1 = 49;//1键
        public static final byte KEY_2 = 50;//2键
        public static final byte KEY_3 = 51;//3键
        public static final byte KEY_4 = 52;//4键
        public static final byte KEY_5 = 53;//5键
        public static final byte KEY_6 = 54;//6键
        public static final byte KEY_7 = 55;//7键
        public static final byte KEY_8 = 56;//8键
        public static final byte KEY_9 = 57;//9键
        public static final byte KEY_STAR = 42;//*键
        public static final byte KEY_POUND = 35;//#键
    }

    /**
     * 字符串
     */
    public static final class Str {

        /**
         * 菜单
         */
        public static final String[] MENU_MENU = {"开始游戏", "继续游戏", "游戏设置", "游戏帮助", "关于游戏", "退出游戏"};
        public static final String[] MENU_MAP = {"我的状态", "我的背包", "我的装备", "我的技能", "储存进度", "回主菜单"};
        public static final String[] KINDS = {"头盔", "饰品", "武器", "盾牌", "铠甲", "战靴"};
    }

    /**
     * 标志
     */
    public static final class ViewId {

        /**
         * 视图
         */
        public static final byte VIEW_MENU = 0;//菜单
        public static final byte VIEW_MAP = 1;//地图
        public static final byte VIEW_SETTING = 2;//设置
        public static final byte VIEW_HELP = 3;//帮助
        public static final byte VIEW_ABOUT = 4;//游戏
        public static final byte VIEW_BATTLE = 5;//战斗
        public static final byte VIEW_SKILL = 6;//技能
        public static final byte VIEW_BAG = 7;//背包
        public static final byte VIEW_STATE = 8;//状态
        public static final byte VIEW_EQUIP = 9;//装备
        public static final byte VIEW_SHOP = 10;//商店
    }
}
