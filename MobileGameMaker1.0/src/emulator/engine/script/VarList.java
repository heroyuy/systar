package emulator.engine.script;

import java.util.Hashtable;

/**
 *
 * 变量空间
 * boolean型：值为true或false的字符串
 * int型：可被转换为JAVA整型的字符串
 * string：以"开头并以"结尾的字符串
 */
public class VarList {

    private ScriptEngine se = null;
    private Hashtable list = null;

    VarList(ScriptEngine se) {
        this.se = se;
        list = new Hashtable();
    }

    /**
     * 检查变量空间中是否存在名称为name的变量
     * @param name 变量名称
     * @return 如果名称为name的变量存在则返回true 否则返回false
     */
    public boolean hasVar(String name) {
        return list.containsKey(name);
    }

    /**
     * 向变量空间中添加变量
     * @param name 变量名
     * @param value 变量值
     */
    public void addVar(String name, String value) {
        list.put(name, value);
    }

    /**
     * 获取名称为name的变量的值 （也有可能是常量）
     * @param name 变量名或常量
     * @return 返回变量的值
     */
    public String getValue(String name) {
        String value = null;
        System.out.println("name:" + name);
        if (name.startsWith("$SWITCH[") && name.endsWith("]")) {
            value = se.getDataHandler().getSwitch(Integer.parseInt(name.substring(8, name.length() - 1))) + "";
        } else if (name.startsWith("$VAR[") && name.endsWith("]")) {
            value = se.getDataHandler().getVar(Integer.parseInt(name.substring(5, name.length() - 1))) + "";
        } else if (name.equals("$EXP")) {
            value = se.getDataHandler().getExp() + "";
        } else if (name.equals("$MONEY")) {
            value = se.getDataHandler().getMoney() + "";
        } else if (name.equals("$LEV")) {
            System.out.println("se:" + se);
            System.out.println("se.getDataHandler():" + se.getDataHandler());
            value = se.getDataHandler().getLevel() + "";
        } else if (name.equals("$MAXHP")) {
            value = se.getDataHandler().getMaxHp() + "";
        } else if (name.equals("$HP")) {
            value = se.getDataHandler().getHp() + "";
        } else if (name.equals("$MAXSP")) {
            value = se.getDataHandler().getMaxSp() + "";
        } else if (name.equals("$SP")) {
            value = se.getDataHandler().getSp() + "";
        } else if (name.equals("$STRE")) {
            value = se.getDataHandler().getStre() + "";
        } else if (name.equals("$AGIL")) {
            value = se.getDataHandler().getAgil() + "";
        } else if (name.equals("$INTE")) {
            value = se.getDataHandler().getInte() + "";
        } else if (name.startsWith("$ITEM[") && name.endsWith("]")) {
            value = se.getDataHandler().getItemNum(Integer.parseInt(name.substring(6, name.length() - 1))) + "";
        } else if (name.startsWith("$EQUIP[") && name.endsWith("]")) {
            value = se.getDataHandler().getEquipNum(Integer.parseInt(name.substring(7, name.length() - 1))) + "";
        } else if (name.startsWith("$SKILL[") && name.endsWith("]")) {
            value = se.getDataHandler().getSkillStatus(Integer.parseInt(name.substring(7, name.length() - 1))) + "";
        } else if (name.startsWith("$")) {
            if (!hasVar(name)) {
                addVar(name, "0");
            }
            value = (String) list.get(name);
        } else if (name.startsWith("\"") && name.endsWith("\"")) {
            value = name;
        } else if (name.equals("true") || name.equals("false")) {
            value = name;
        } else {
            try {
                Integer.parseInt(name);
                value = name;
            } catch (Exception e) {
                value = null;
            }
        }
        return value;
    }

    /**
     * 设置变量的值
     * @param name 变量名
     * @param value 值
     */
    public void setValue(String name, String value) {
        if (name.startsWith("$SWITCH[") && name.endsWith("]")) {
            if (value.equals("true")) {
                se.getDataHandler().setSwitch(Integer.parseInt(name.substring(8, name.length() - 1)), true);
            } else if (value.equals("false")) {
                se.getDataHandler().setSwitch(Integer.parseInt(name.substring(8, name.length() - 1)), false);
            }
        } else if (name.startsWith("$VAR[") && name.endsWith("]")) {
            se.getDataHandler().setVar(Integer.parseInt(name.substring(5, name.length() - 1)), Integer.parseInt(value));
        } else if (name.equals("$EXP")) {
            se.getDataHandler().setExp(Integer.parseInt(value));
        } else if (name.equals("$MONEY")) {
            se.getDataHandler().setMoney(Integer.parseInt(value));
        } else if (name.equals("$LEV")) {
            se.getDataHandler().setLevel(Integer.parseInt(value));
        } else if (name.equals("$MAXHP")) {
            se.getDataHandler().setMaxHp(Integer.parseInt(value));
        } else if (name.equals("$HP")) {
            se.getDataHandler().setHp(Integer.parseInt(value));
        } else if (name.equals("$MAXSP")) {
            se.getDataHandler().setMaxSp(Integer.parseInt(value));
        } else if (name.equals("$SP")) {
            se.getDataHandler().setSp(Integer.parseInt(value));
        } else if (name.equals("$STRE")) {
            se.getDataHandler().setStre(Integer.parseInt(value));
        } else if (name.equals("$AGIL")) {
            se.getDataHandler().setAgil(Integer.parseInt(value));
        } else if (name.equals("$INTE")) {
            se.getDataHandler().setInte(Integer.parseInt(value));
        } else if (name.startsWith("$ITEM[") && name.endsWith("]")) {
            se.getDataHandler().setItemNum(Integer.parseInt(name.substring(6, name.length() - 1)), Integer.parseInt(value));
        } else if (name.startsWith("$EQUIP[") && name.endsWith("]")) {
            //这里以前有bug，已修正 2.24
            se.getDataHandler().setEquipNum(Integer.parseInt(name.substring(7, name.length() - 1)), Integer.parseInt(value));
        } else if (name.startsWith("$SKILL[") && name.endsWith("]")) {
            //这里以前有bug，已修正 2.24
            if (value.equals("false")) {
                se.getDataHandler().setSkillStatus(Integer.parseInt(name.substring(7, name.length() - 1)), false);
            } else {
                se.getDataHandler().setSkillStatus(Integer.parseInt(name.substring(7, name.length() - 1)), true);
            }
        } else if (name.startsWith("$")) {
            list.put(name, value);
        }
    }
    public static final byte INT = 0;
    public static final byte BOOLEAN = 1;
    public static final byte STRING = 2;
    public static final byte ERROR = 3;

    /**
     * 判断一个值的类型
     * @param value 值
     * @return 返回这个值的类型
     */
    public static byte getType(String value) {
        byte type = 3;
        if (value.equals("true") || value.equals("false")) {
            type = BOOLEAN;
        } else if (value.startsWith("\"") && value.endsWith("\"")) {
            type = STRING;
        } else {
            try {
                Integer.parseInt(value);
                type = INT;
            } catch (Exception e) {
                type = ERROR;
            }
        }
        return type;
    }
}
