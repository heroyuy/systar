package model;

public class Player extends Role {

    private int maxlev = 0;// 封顶等级
    private int strebylev = 0;// 力量成长
    private int agilbylev = 0;// 敏捷成长
    private int intebylev = 0;// 智力成长
    private int hpbystre = 0;// 力量附加hp
    private int spbyinte = 0;// 智力附加sp
    private int hpbylev = 0;// 等级附加hp
    private int spbylev = 0;// 等级附加hp
    private int[] levList = null;//经验值表
    private String description = "";//介绍
    private int startX = 0;
    private int startY = 0;//起始位置
    private int dir = 0;//朝向
    private int mapIndex = 0;//当前地图

    public int getMapIndex() {
        return mapIndex;
    }

    public void setMapIndex(int mapIndex) {
        this.mapIndex = mapIndex;
    }

    public int getDir() {
        return dir;
    }

    public void setDir(int dir) {
        this.dir = dir;
    }

    public int getStartX() {
        return startX;
    }

    public void setStartX(int startX) {
        this.startX = startX;
    }

    public int getStartY() {
        return startY;
    }

    public void setStartY(int startY) {
        this.startY = startY;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAgilbylev() {
        return agilbylev;
    }

    public void setAgilbylev(int agilbylev) {
        this.agilbylev = agilbylev;
    }

    public int getHpbylev() {
        return hpbylev;
    }

    public void setHpbylev(int hpbylev) {
        this.hpbylev = hpbylev;
    }

    public int getHpbystre() {
        return hpbystre;
    }

    public void setHpbystre(int hpbystre) {
        this.hpbystre = hpbystre;
    }

    public int getIntebylev() {
        return intebylev;
    }

    public void setIntebylev(int intebylev) {
        this.intebylev = intebylev;
    }

    public int getMaxlev() {
        return maxlev;
    }

    public void setMaxlev(int maxlev) {
        this.maxlev = maxlev;
    }

    public int getSpbyinte() {
        return spbyinte;
    }

    public void setSpbyinte(int spbyinte) {
        this.spbyinte = spbyinte;
    }

    public int getSpbylev() {
        return spbylev;
    }

    public void setSpbylev(int spbylev) {
        this.spbylev = spbylev;
    }

    public int getStrebylev() {
        return strebylev;
    }

    public void setStrebylev(int strebylev) {
        this.strebylev = strebylev;
    }

    public int[] getLevList() {
        return levList;
    }

    public void setLevList(int[] levList) {
        this.levList = levList;
    }
}
