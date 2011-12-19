/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyomaker.model.map;

/**
 * 脚本基类
 * @author wp_g4
 */
public abstract class Script {

    //一、玩家交互类
    /**
     * 1.显示对话框
     * @param name 对话者名称
     * @param content 对话内容
     * @param pos 对话框位置 0 上 1 中 2 下
     */
    public void showDialog(String name, String content, int pos) {
    }

    /**
     * 2.显示选择项
     * @param items 选择项列表
     * @return 选择的序号
     */
    public int showDialog(String[] items) {
        return 0;
    }

    /**
     * 3.显示输入框
     * @param type 输入框类型 0 纯数字  1 不限
     * @return 输入的字符串
     */
    public String showInputDialog(byte type) {
        return null;
    }

    /**
     * 4.显示倒计时
     * @param frameNum 
     */
    public void showClock(int frameNum) {
    }

    //三、游戏功能类
    /**
     * 1.强制等待
     * @param frameNum 等待的帧数
     */
    public void forceWait(int frameNum) {
    }

    /**
     * 2.执行公共事件
     * @param id 公共事件ID
     */
    public void runPublicScript(int id) {
    }

    /**
     * 3.结束游戏
     */
    public void exit() {
    }

    /**
     * 1.更换皮肤
     * @param name
     */
    public void changeSkin(String name) {
    }

    /**
     * 2.切换地图
     * @param id
     * @param row
     * @param col
     * @param face
     */
    public void gotoMap(int id, int row, int col, byte face) {
    }

    /**
     * 
     * @param face
     */
    public void changeFace(byte face) {
    }

    /**
     * 3.设置NPC位置
     * @param npcId
     * @param mapId
     * @param row
     * @param col
     * @param face
     */
    public void setNpcPosition(int npcId, int mapId, int row, int col, byte face) {
    }

    /**
     * 4.播放动画
     * @param id
     * @param x
     * @param y
     * @param times 次数 -1表示持续播放
     */
    public void playAnimation(int id, int x, int y, int times) {
    }

    /**
     * 
     * @param id
     */
    public void stopAnimation(int id) {
    }

    /**
     * 5.播放音乐
     * @param name
     * @param loop
     */
    public void playMusic(String name, boolean loop) {
    }

    /**
     * 6.停止音乐
     * @param name
     */
    public void stopMusic(String name) {
    }

    /**
     * 7.强制移动
     * @param actorId -1表示主角
     * @param paths
     */
    public void forceMove(int actorId, byte[] paths) {
    }

    /**
     * 8.更改画面色调
     * @param a
     * @param r
     * @param g
     * @param b
     * @param frameNum
     */
    public void changeWindowARGB(int a, int r, int g, int b, int frameNum) {
    }

    /**
     * 9.画面震动
     * @param range
     * @param frameNum
     * @param times
     */
    public void shakeWindow(int range, int frameNum, int times) {
    }

    /**
     * 10.进入战斗
     * @param enemyTroopId
     * @param deathModel
     * @return  
     */
    public boolean enterBattle(int enemyTroopId, boolean deathModel) {
        return true;
    }

    /**
     * 11.开启商店
     * @param type
     * @param ids
     */
    public void openShop(byte type, int[] ids) {
    }

    /**
     * 12.结束战斗
     */
    public void exitBattle() {
    }

    /**
     * 13.打开系统菜单
     */
    public void openSystemMenu() {
    }

    /**
     * 14.打开存档菜单
     */
    public void openSaveMenu() {
    }

    /**
     * 15.回主菜单
     */
    public void openMainMenu() {
    }

    //四、图片显示类
    /**
     * 1.显示图片
     * @param name
     * @param x
     * @param y
     */
    public void showImage(String name, int x, int y) {
    }

    /**
     * 2.显示图片
     * @param name
     * @param x
     * @param y
     * @param frameNum
     */
    public void showImage(String name, int x, int y, int frameNum) {
    }

    /**
     * 3.移动图片
     * @param name
     * @param x
     * @param y
     * @param frameNum
     */
    public void moveImage(String name, int x, int y, int frameNum) {
    }

    /**
     * 4.旋转图片
     * @param name
     * @param type 0为顺时针 1为逆时针
     * @param angle 角度
     * @param frameNum
     */
    public void rotateImage(String name, byte type, int angle, int frameNum) {
    }

    /**
     * 5.更改图片色调
     * @param name
     * @param a
     * @param r
     * @param g
     * @param b
     * @param frameNum
     */
    public void changeImageARGB(String name, int a, int r, int g, int b, int frameNum) {
    }

    /**
     * 6.移除图片
     * @param name
     */
    public void removeImage(String name) {
    }

    //五、数据处理类
    /**
     * 
     * @param index
     * @return
     */
    public boolean getSwitch(int index) {
        return true;
    }

    /**
     * 
     * @param index
     * @param value
     */
    public void setSwitch(int index, boolean value) {
    }

    /**
     * 
     * @param index
     * @return
     */
    public int getVar(int index) {
        return 0;
    }

    /**
     * 
     * @param index
     * @param value
     */
    public void setVar(int index, int value) {
    }

    /**
     * 
     * @return
     */
    public int getMoney() {
        return 0;
    }

    /**
     * 
     * @param money
     */
    public void setMoney(int money) {
    }

    /**
     * 
     * @param index
     * @return
     */
    public int getItemNum(int index) {
        return 0;
    }

    /**
     * 
     * @param index
     * @param itemNum
     */
    public void setItemNum(int index, int itemNum) {
    }

    /**
     * 
     * @param index
     * @return
     */
    public int getEquipNum(int index) {
        return 0;
    }

    /**
     * 
     * @param index
     * @param equipNum
     */
    public void setEquipNum(int index, int equipNum) {
    }

    /**
     * 
     * @param index
     * @return
     */
    public boolean getSkillState(int index) {
        return true;
    }

    /**
     * 
     * @param index
     * @param state
     */
    public void setSkillState(int index, boolean state) {
    }

    /**
     * 
     * @return
     */
    public int getHp() {
        return 0;
    }

    /**
     * 
     * @param hp
     */
    public void setHp(int hp) {
    }

    /**
     * 
     * @return
     */
    public int getSp() {
        return 0;
    }

    /**
     * 
     * @param sp
     */
    public void setSp(int sp) {
    }

    /**
     * 
     * @return
     */
    public int getExp() {
        return 0;
    }

    /**
     * 
     * @param exp
     */
    public void setExp(int exp) {
    }

    /**
     * 
     * @return
     */
    public int getLev() {
        return 0;
    }

    /**
     * 
     * @param lev
     */
    public void setLev(int lev) {
    }

    /**
     * 
     * @return
     */
    public int getStre() {
        return 0;
    }

    /**
     * 
     * @param stre
     */
    public void setStre(int stre) {
    }

    /**
     * 
     * @return
     */
    public int getStreByLev() {
        return 0;
    }

    /**
     * 
     * @param streByLev
     */
    public void setStreByLev(int streByLev) {
    }

    /**
     * 
     * @return
     */
    public int getAgil() {
        return 0;
    }

    /**
     * 
     * @param agil
     */
    public void setAgil(int agil) {
    }

    /**
     * 
     * @return
     */
    public int getAgilByLev() {
        return 0;
    }

    /**
     * 
     * @param agilByLev
     */
    public void setAgilByLev(int agilByLev) {
    }

    /**
     * 
     * @return
     */
    public int getInte() {
        return 0;
    }

    /**
     * 
     * @param inte
     */
    public void setInte(int inte) {
    }

    /**
     * 
     * @return
     */
    public int getInteByLev() {
        return 0;
    }

    /**
     * 
     * @param inteByLev
     */
    public void setInteByLev(int inteByLev) {
    }

    /**
     * 
     * @param type
     * @param id
     */
    public void operateEquip(byte type, int id) {
    }

    /**
     * 
     */
    public void recoverPlayer() {
    }

    /**
     * 
     * @return
     */
    public String getPlayerName() {
        return null;
    }

    /**
     * 
     * @param name
     */
    public void setPlayerName(String name) {
    }

    /**
     * 
     * @return
     */
    public String getPlayerModel() {
        return null;
    }

    /**
     * 
     * @param name
     */
    public void setPlayerModel(String name) {
    }

    /**
     * 
     * @param index
     * @return
     */
    public int getEnemyHP(int index) {
        return 0;
    }

    /**
     * 
     * @param index
     * @param hp
     */
    public void setEnemyHP(int index, int hp) {
    }

    /**
     * 
     * @param index
     * @return
     */
    public int getEnemySP(int index) {
        return 0;
    }

    /**
     * 
     * @param index
     * @param sp
     */
    public void setEnemySP(int index, int sp) {
    }

    /**
     * 
     */
    public void recoverEnemy() {
    }
    //第一个参数是怪物位置
    //第二个是变身后的怪物ID

    /**
     * 
     * @param index
     * @param id
     */
    public void setEnemy(int index, int id) {
    }
    //第一个参数是动画ID
    //第二个是怪物位置

    /**
     * 
     * @param id
     * @param index
     */
    public void playAnimation(int id, int index) {
    }

    /**
     * 所有脚本实现此方法
     */
    public abstract void execute();
}
