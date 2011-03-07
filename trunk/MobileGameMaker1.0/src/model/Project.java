package model;

public class Project {

    private static String projectPath = ".";// 保存游戏目录
    private static String projectName = "";//项目名称
    private static String projectDirName = "";//文件夹名称
//    private static MapList maplist = new MapList();
    private static SkillList skillList = new SkillList();
    private static GoodsList itemList = new GoodsList();
    private static EquipList equipList = new EquipList();
    private static Player player = new Player();
    private static String name = "游戏名称";//游戏名称
    private static String stre = "力量";//
    private static String hp = "hp";//
    private static String sp = "sp";//
    private static String agil = "敏捷";//
    private static String inte = "智力";//
    private static String atk = "攻击力";//
    private static String def = "防御力";//
    private static String money = "金钱";//
    private static String helm = "头盔";//头盔
    private static String weapon = "武器";//武器
    private static String shield = "护盾";//护盾
    private static String armour = "铠甲";//铠甲
    private static String trinket = "饰品";//饰品
    private static String caliga = "战靴";//战靴
    private static String help = "游戏帮助";//游戏帮助
    private static String introduction = "游戏说明";//游戏说明
    private static String flee = "闪避";//闪避
    private static EnemyList enemyList = new EnemyList();
    private static EnemyTroopList enemyTroopList = new EnemyTroopList();

    public static EnemyTroopList getEnemyTroopList() {
        return enemyTroopList;
    }

    public static void setEnemyTroopList(EnemyTroopList enemyTroopList) {
        Project.enemyTroopList = enemyTroopList;
    }

    public static String getFlee() {
        return flee;
    }

    public static void setFlee(String flee) {
        Project.flee = flee;
    }

    public static String getAgil() {
        return agil;
    }

    public static void setAgil(String agil) {
        Project.agil = agil;
    }

    public static String getArmour() {
        return armour;
    }

    public static void setArmour(String armour) {
        Project.armour = armour;
    }

    public static String getAtk() {
        return atk;
    }

    public static void setAtk(String atk) {
        Project.atk = atk;
    }

    public static String getCaliga() {
        return caliga;
    }

    public static void setCaliga(String caliga) {
        Project.caliga = caliga;
    }

    public static String getDef() {
        return def;
    }

    public static void setDef(String def) {
        Project.def = def;
    }

    public static String getHelm() {
        return helm;
    }

    public static void setHelm(String helm) {
        Project.helm = helm;
    }

    public static String getInte() {
        return inte;
    }

    public static void setInte(String inte) {
        Project.inte = inte;
    }

    public static String getMoney() {
        return money;
    }

    public static void setMoney(String money) {
        Project.money = money;
    }

    public static String getShield() {
        return shield;
    }

    public static void setShield(String shield) {
        Project.shield = shield;
    }

    public static String getTrinket() {
        return trinket;
    }

    public static void setTrinket(String trinket) {
        Project.trinket = trinket;
    }

    public static String getWeapon() {
        return weapon;
    }

    public static void setWeapon(String weapon) {
        Project.weapon = weapon;
    }

    public static EnemyList getEnemyList() {
        return enemyList;
    }

    public static String getProjectName() {
        return projectName;
    }

    public static String getProjectDirName() {
        return projectDirName;
    }

    public static void setProjectDirName(String projectDirName) {
        Project.projectDirName = projectDirName;
    }

    public static void setProjectName(String projectName) {
        Project.projectName = projectName;
    }

    public static void setEnemyList(EnemyList enemyList) {
        Project.enemyList = enemyList;
    }

    public static GoodsList getItemList() {
        return itemList;
    }

    public static void setItemList(GoodsList itemList) {
        Project.itemList = itemList;
    }

    public static String getProjectPath() {
        return projectPath;
    }

    public static void setProjectPath(String projectPath) {
        Project.projectPath = projectPath;
    }

    public static SkillList getSkillList() {
        return skillList;
    }

    public static void setSkillList(SkillList skillList) {
        Project.skillList = skillList;
    }

    public static EquipList getEquipList() {
        return equipList;
    }

    public static void setEquipList(EquipList equipList) {
        Project.equipList = equipList;
    }

    public static Player getPlayer() {
        return player;
    }

    public static void setPlayer(Player player) {
        Project.player = player;
    }

    public static String getHp() {
        return hp;
    }

    public static void setHp(String acknowledgement) {
        Project.hp = acknowledgement;
    }

    public static String getStre() {
        return stre;
    }

    public static void setStre(String author) {
        Project.stre = author;
    }

    public static String getHelp() {
        return help;
    }

    public static void setHelp(String help) {
        Project.help = help;
    }

    public static String getIntroduction() {
        return introduction;
    }

    public static void setIntroduction(String introduction) {
        Project.introduction = introduction;
    }

    public static String getGameName() {
        return name;
    }

    public static void setName(String name) {
        Project.name = name;
    }

    public static String getSp() {
        return sp;
    }

    public static void setSp(String remark) {
        Project.sp = remark;
    }
}
