package com.soyostar.game.model;


/**
 * 敌人
 */
public class Enemy extends Character {

    public int index = 0;//编号
//    private String Property; //怪物属性 元素相克 金木水火土
    private boolean isDead = false;//生存状态
    public EmulatorImage BattImg = null;//战斗图
    public int[] skillList;
    /**********************************************/
    public int BattX, BattY;//战斗时坐标
    public int changeHp;

    /**********************************************/
    public Enemy() {
    }

    public int AI() {
        //怪物ai函数， 怪物智力影响ai
//        int cur = -1;//当前动作
        if ((this.sp <= 0) || (this.skillList.length == 0)) {
            return -1;
        }
        int temp = Tools.GetRandom(100);
        temp += this.inte / 10;//智力高的 放技能的概率加成，100智力加成10%
        if (temp < 90) {// 基础 物理攻击概率 受智力影响
            return -1;
        } else {
            return -2;
        }

        /**
         * -1 普通攻击
         * -2 魔法攻击
         * 没有魔法或者魔法值为0直接返回-1
         */
    }
    //实现对象克隆

    public Enemy getClone() {
        Enemy enemy = new Enemy();
        enemy.BattImg = this.BattImg;
        enemy.BattX = this.BattX;
        enemy.BattY = this.BattY;
        enemy.agil = this.agil;
        enemy.atk = this.atk;
        enemy.def = this.def;
        enemy.exp = this.exp;
        enemy.flee = this.flee;
        enemy.headImg = this.headImg;
        enemy.hp = this.hp;
        enemy.index = this.index;
        enemy.inte = this.inte;
        enemy.intro = this.intro;
        enemy.isDead = this.isDead;
        enemy.lev = this.lev;
        enemy.maxHp = this.maxHp;
        enemy.maxSp = this.maxSp;
        enemy.money = this.money;
        enemy.name = this.name;
        enemy.sp = this.sp;
        enemy.stre = this.sp;
        enemy.waitTime = this.waitTime;
        enemy.skillList = this.skillList;
        return enemy;
    }

    public boolean isDead() {
        return isDead;
    }

    public void setIsDead(boolean isDead) {
        this.isDead = isDead;
    }
}
