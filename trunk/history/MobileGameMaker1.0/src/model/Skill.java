package model;

//技能类
public class Skill extends BasicItem {

    public static final int RECOVER = 2;// 辅助自身
    public static final int SATK = 0;// 单体攻击
    public static final int AATK = 1;// 全体攻击
    private int dpy = 0;// 伤害分散度
    private int aniIndex = 0;//技能动画id
    private int price = 0;//

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getAniIndex() {
        return aniIndex;
    }

    public void setAniIndex(int aniIndex) {
        this.aniIndex = aniIndex;
    }

    public int getDpy() {
        return dpy;
    }

    public void setDpy(int dpy) {
        this.dpy = dpy;
    }
}
