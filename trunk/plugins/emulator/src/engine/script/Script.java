package engine.script;

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
     * @param seconds
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
     */
    public void playAnimation(int id, int x, int y) {
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
     * @param actorId
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
     */
    public void enterBattle(int enemyTroopId, boolean deathModel) {
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
     * @param type
     * @param frameNum
     */
    public void rotateImage(String name, byte type, int frameNum) {
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
    public boolean getSwitch(int index) {
        return true;
    }

    public void setSwitch(int index, boolean value) {
    }

    public int getVar(int index) {
        return 0;
    }

    public void setVar(int index, int value) {
    }

    public int getMoney() {
        return 0;
    }

    public void setMoney(int money) {
    }

    public int getItemNum(int index) {
        return 0;
    }

    public void setItemNum(int index, int itemNum) {
    }

    public int getEquipNum(int index) {
        return 0;
    }

    public void setEquipNum(int index, int equipNum) {
    }

    public boolean getSkillState(int index) {
        return true;
    }

    public void setSkillState(int index, boolean state) {
    }

    public int getHp() {
        return 0;
    }

    public void setHp(int hp) {
    }

    public int getSp() {
        return 0;
    }

    public void setSp(int sp) {
    }

    public int getExp() {
        return 0;
    }

    public void setExp(int exp) {
    }

    public int getLev() {
        return 0;
    }

    public void setLev(int lev) {
    }

    public int getStre() {
        return 0;
    }

    public void setStre(int stre) {
    }

    public int getStreByLev() {
        return 0;
    }

    public void setStreByLev(int streByLev) {
    }

    public int getAgil() {
        return 0;
    }

    public void setAgil(int agil) {
    }

    public int getAgilByLev() {
        return 0;
    }

    public void setAgilByLev(int agilByLev) {
    }

    public int getInte() {
        return 0;
    }

    public void setInte(int inte) {
    }

    public int getInteByLev() {
        return 0;
    }

    public void setInteByLev(int inteByLev) {
    }

    public void operateEquip(byte type, int id) {
    }

    public void recoverPlayer() {
    }

    public String getPlayerName() {
        return null;
    }

    public void setPlayerName(String name) {
    }

    public String getPlayerModel() {
        return null;
    }

    public void setPlayerModel(String name) {
    }

    public int getEnemyHP(int index) {
        return 0;
    }

    public void setEnemyHP(int index, int hp) {
    }

    public int getEnemySP(int index) {
        return 0;
    }

    public void setEnemySP(int index, int sp) {
    }

    public void recoverEnemy() {
    }

    public void setEnemy(int index, int id) {
    }

    public void playAnimation(int id, int index) {
    }

    /**
     * 所有脚本实现此方法
     */
    public abstract void execute();
}
