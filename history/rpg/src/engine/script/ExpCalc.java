package engine.script;

import engine.script.operation.Operation;
import java.util.Vector;
import system.Painter;

public class ExpCalc {

    private ScriptEngine se = null;
    private Vector expVector = new Vector();
    private Operation operation = new Operation();
    private boolean judge = false;
    private String[] singleSign = new String[]{"|", "&", "!", ">", "<", "=", "+",
        "-", "*", "/", "(", ")", "%"};
    private String[] doubleSign = new String[]{"||", "!=", "==", "&&", ">=", "<=",
        "+=", "-=", "*=", "/=", "%="};

    protected ExpCalc(ScriptEngine se) {
        this.se = se;
    }

    public String calculate(String exp) {
        String res = null;
        init(exp);
        calculate();
        res = (String) expVector.elementAt(0);
        return se.getVarList().getValue(res);
    }

    private void init(String exp) {
        // 将表达式的各元素存入向量中
        expVector.removeAllElements();
        String[] temp = Painter.split(exp, " ");
        for (int i = 0; i
                < temp.length; i++) {
            expVector.addElement(temp[i]);
        }
    }

    private void calculate() {
        // 计算表达式
        String res = "";
        String res1 = "";
        String res2 = "";
        int index = 0;// 要运算的操作符所在的位置
        int size = expVector.size();
        String temp = "";// 保存运算符
        while (index < size) {
            temp = (String) expVector.elementAt(index);
            if (isSingleSign(temp) || isDoubleSign(temp)) {
                // 如果检测到运算符则要进行运算
                if (temp.equals("!")) {
                    res1 = (String) expVector.elementAt(index - 1);
                    res = operation.operate(temp, res1, "");
                    expVector.setElementAt(res, index - 1);
                    expVector.removeElementAt(index);
                } else {
                    res1 = (String) expVector.elementAt(index - 2);
                    res2 = (String) expVector.elementAt(index - 1);
                    res = operation.operate(temp, res1, res2);
                    expVector.setElementAt(res, index - 2);
                    expVector.removeElementAt(index - 1);
                    expVector.removeElementAt(index - 1);
                }
                calculate();
                break;
            } else {
                index++;
            }
        }

    }

    private boolean isDoubleSign(String s) {
        // 若s是双字母运算符则返回true，否则返回false
        judge = false;
        for (int i = 0; i
                < doubleSign.length; i++) {
            if (s.equals(doubleSign[i])) {
                judge = true;
                break;
            }
        }
        return judge;
    }

    private boolean isSingleSign(String s) {
        // 若s是单字母运算符则返回true，否则返回false
        judge = false;
        for (int i = 0; i
                < singleSign.length; i++) {
            if (s.equals(singleSign[i])) {
                judge = true;
                break;
            }
        }
        return judge;


    }

//    private byte getType(String res) {
//        byte type = ERROR;//默认是错误
//        if (res.startsWith("$")) {
//            type = VAR;
//        } else if (res.startsWith("\"") && res.endsWith("\"")) {
//            type = STRING;
//        } else if (res.equals("true") || res.equals("false")) {
//            type = BOOLEAN;
//        } else {
//            try {
//                Integer.parseInt(res);
//                type = INT;
//            } catch (Exception e) {
//                type = ERROR;
//            }
//        }
//        return type;
//
//    }
//
//    private String getValueOfVar(String name) {
//        String value = null;
//        if (name.startsWith("$SWITCH[") && name.endsWith("]")) {
//            value = interpreter.getDataHandler().getSwitch(Integer.parseInt(name.substring(8, name.length() - 1))) + "";
//        } else if (name.startsWith("$VAR[") && name.endsWith("]")) {
//            value = interpreter.getDataHandler().getVar(Integer.parseInt(name.substring(5, name.length() - 1))) + "";
//        } else if (name.equals("$EXP")) {
//            value = interpreter.getDataHandler().getExp() + "";
//        } else if (name.equals("$MONEY")) {
//            value = interpreter.getDataHandler().getMoney() + "";
//        } else if (name.equals("$LEV")) {
//            value = interpreter.getDataHandler().getLevel() + "";
//        } else if (name.equals("$MAXHP")) {
//            value = interpreter.getDataHandler().getMaxHp() + "";
//        } else if (name.equals("$HP")) {
//            value = interpreter.getDataHandler().getHp() + "";
//        } else if (name.equals("$MAXSP")) {
//            value = interpreter.getDataHandler().getMaxSp() + "";
//        } else if (name.equals("$SP")) {
//            value = interpreter.getDataHandler().getSp() + "";
//        } else if (name.equals("$STRE")) {
//            value = interpreter.getDataHandler().getStre() + "";
//        } else if (name.equals("$AGIL")) {
//            value = interpreter.getDataHandler().getAgil() + "";
//        } else if (name.equals("$INTE")) {
//            value = interpreter.getDataHandler().getInte() + "";
//        } else if (name.startsWith("$ITEM[") && name.endsWith("]")) {
//            value = interpreter.getDataHandler().getItemNum(Integer.parseInt(name.substring(6, name.length() - 1))) + "";
//        } else if (name.startsWith("$EQUIP[") && name.endsWith("]")) {
//            value = interpreter.getDataHandler().getEquipNum(Integer.parseInt(name.substring(7, name.length() - 1))) + "";
//        } else if (name.startsWith("$SKILL[") && name.endsWith("]")) {
//            value = interpreter.getDataHandler().getSkillStatus(Integer.parseInt(name.substring(7, name.length() - 1))) + "";
//        }
//        return value;
//    }
    private void printExpVector() {
        System.out.println("*********************************************");
        for (int i = 0; i < expVector.size(); i++) {
            System.out.print(expVector.elementAt(i) + " ");
        }
        System.out.println("#############################################");
    }
}
