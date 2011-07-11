package model;

public class Enemy extends Role implements Cloneable {

    private int dpy = 0;// 分散度
    public Skill[] skillList;
    private String description = "";//介绍

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDpy() {
        return dpy;
    }

    public void setDpy(int dpy) {
        this.dpy = dpy;
    }

    public void hit(Role c) {
        //使用物品、物理攻击、技能攻击的AI在这里设计
    }

    @Override
    public Enemy clone() {
        try {
            return (Enemy) super.clone();
        } catch (Exception e) {
        }
        return null;
    }
}
