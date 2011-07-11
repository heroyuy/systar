package model;

import java.util.Vector;

//玩家和敌人身上保存一张物品表，用于保存角色、敌人当前所拥有的物品
public class SkillList extends Vector {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public void addSkill(Skill skill) {
        addElement(skill);
    }

    public void delskill(int index) {
        //删除技能列表中指定技能编号的技能
        removeElementAt(index);
    }

    public Skill getSkill(int index) {
        //返回技能列表中指定技能编号的技能
        Skill skill = null;
        if (hasSkill(index)) {
            for (int i = 0; i < size(); i++) {
                skill = (Skill) elementAt(i);
                if (skill.getIndex() == index) {
                    break;
                }
            }
        }
        return skill;

    }

    public boolean hasSkill(int index) {
        //检查是否有指定编号的技能
        boolean judge = false;
        Skill skill = null;
        for (int i = 0; i < size(); i++) {
            skill = (Skill) elementAt(i);
            if (skill.getIndex() == index) {
                judge = true;
                break;
            }
        }
        return judge;
    }

    public Skill skillAt(int num) {
        //获取技能表中指定位置的技能
        return (Skill) elementAt(num);
    }

    public int getMaxIndex() {
        int max = 0;
        Skill skill = null;
        for (int i = 0; i < size(); i++) {
            skill = skillAt(i);
            if (skill.getIndex() > max) {
                max = skill.getIndex();
            }
        }
        return max;
    }
}
