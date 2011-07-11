package converter;

import model.*;

public final class Converter {

    private static FinalScript script = null;
    private static CommandSet commandSet = null;
    private static int curIndex = 0;

    private Converter() {
    }

//    public static void main(String[] args) {
//        FinalScript script = new FinalScript();
//        script.data = new String[]{
//                "//这是注释，把我忽略",
//                " $SWITCH[0]=1",
//                "IF $SWITCH[0]",
//                "$VAR[0]=4",
//                "$VAR[1]=10",
//                "WHILE $VAR[0]<5",
//                "$VAR[0]-=1 ",
//                "IF $VAR[0]=0",
//                "               ",
//                "//这是注释，把我忽略",
//                "  BREAK",
//                " ENDIF",
//                "ENDWHILE",
//                "$VAR[2]=5",
//                "WHILE $VAR[1]>1",
//                "//这是注释，把我忽略",
//                "$VAR[1]-=10",
//                "IF $VAR[1]=5",
//                " $VAR[2]-=1",
//                "//这是注释，把我忽略",
//                "  CONTINUE",
//                " ENDIF",
//                "ENDWHILE",
//                "IF $VAR[2]=4",
//                "  EXIT",
//                " ENDIF",
//                "ENDIF"
//            };
//        script.type = FinalScript.KEY_PAIALLEL;
////        CommandSet cs = Converter.convert(script);
//        System.out.println("***********************************************");
////        System.out.println(cs);
//    }

    private static void creatCommand() {
        int length = script.data.length;
        commandSet.command = new Command[length];
        String temp = null;
        String param = null;
        int nextIndex = -1;
        for (int i = 0; i < length; i++) {
            temp = script.data[i];
            if (temp.startsWith("IF")) {
                param = temp.substring(temp.indexOf(" ") + 1, temp.lastIndexOf(" "));
                nextIndex = Integer.parseInt(temp.substring(temp.lastIndexOf(" ") + 1));
                commandSet.command[i] = Command.creatIfCommand(param, nextIndex);
            } else if (temp.startsWith("ENDIF")) {
                commandSet.command[i] = Command.creatEndIfCommand();
            } else if (temp.startsWith("WHILE")) {
                param = temp.substring(temp.indexOf(" ") + 1, temp.lastIndexOf(" "));
                nextIndex = Integer.parseInt(temp.substring(temp.lastIndexOf(" ") + 1));
                commandSet.command[i] = Command.creatWhileCommand(param, nextIndex);
            } else if (temp.startsWith("ENDWHILE")) {
                nextIndex = Integer.parseInt(temp.substring(temp.indexOf(" ") + 1));
                commandSet.command[i] = Command.creatEndWhileCommand(nextIndex);
            } else if (temp.startsWith("BREAK")) {
                nextIndex = Integer.parseInt(temp.substring(temp.indexOf(" ") + 1));
                commandSet.command[i] = Command.creatBreakCommand(nextIndex);
            } else if (temp.startsWith("CONTINUE")) {
                nextIndex = Integer.parseInt(temp.substring(temp.indexOf(" ") + 1));
                commandSet.command[i] = Command.creatContinueCommand(nextIndex);
            } else if (temp.startsWith("EXIT")) {
                commandSet.command[i] = Command.creatExitCommand();
            } else if (temp.startsWith("DIALOG")) {
                param = temp.substring(temp.indexOf(" ") + 1);
                commandSet.command[i] = Command.creatDialogCommand(param);
            } else if (temp.startsWith("SOUND")) {
                param = temp.substring(temp.indexOf(" ") + 1);
                commandSet.command[i] = Command.creatSoundCommand(param);
            } else if (temp.startsWith("WAIT")) {
                param = temp.substring(temp.indexOf(" ") + 1);
                commandSet.command[i] = Command.creatWaitCommand(param);
            } else if (temp.startsWith("ITEMSHOP")) {
                param = temp.substring(temp.indexOf(" ") + 1);
                commandSet.command[i] = Command.creatItemShopCommand(param);
            } else if (temp.startsWith("EQUIPSHOP")) {
                param = temp.substring(temp.indexOf(" ") + 1);
                commandSet.command[i] = Command.creatEquipShopCommand(param);
            } else if (temp.startsWith("GAMEOVER")) {
                commandSet.command[i] = Command.creatGameOverCommand();
            } else if (temp.startsWith("MAP")) {
                param = temp.substring(temp.indexOf(" ") + 1);
                commandSet.command[i] = Command.creatMapCommand(param);
            } else if (temp.startsWith("FACE")) {
                param = temp.substring(temp.indexOf(" ") + 1);
                commandSet.command[i] = Command.creatFaceCommand(param);
            } else if (temp.startsWith("MOVE")) {

                param = temp.substring(temp.indexOf(" ") + 1);
                commandSet.command[i] = Command.creatMoveCommand(param);
            } else if (temp.startsWith("FIGHT")) {
                param = temp.substring(temp.indexOf(" ") + 1);
                commandSet.command[i] = Command.creatFightCommand(param);
            } else {
                param = temp;
                commandSet.command[i] = Command.creatExpressionCommand(param);
            }
        }

    }

    private static void analyse(String temp) {
        // 对取出的脚本语句进行分析
        if (temp.startsWith("IF")) {
            doIf(temp);
        } else if (temp.startsWith("ENDIF")) {
            doEndif();
        } else if (temp.startsWith("WHILE")) {
            doWhile(temp);
        } else if (temp.startsWith("ENDWHILE")) {
            doEndwhile();
        } else if (temp.startsWith("BREAK")) {
            doBreak();
        } else if (temp.startsWith("CONTINUE")) {
            doContinue();
        } else if (temp.startsWith("EXIT")) {
            doExit();
        } else if (temp.startsWith("DIALOG")) {
            doDialog();
        } else if (temp.startsWith("SOUND")) {
            doSound();
        } else if (temp.startsWith("WAIT")) {
            doWait();
        } else if (temp.startsWith("ITEMSHOP")) {
            doItemShop();
        } else if (temp.startsWith("EQUIPSHOP")) {
            doEquipShop();
        } else if (temp.startsWith("GAMEOVER")) {
            doGameOver();
        } else if (temp.startsWith("MAP")) {
            doMap();
        } else if (temp.startsWith("FACE")) {
            doFace();
        } else if (temp.startsWith("MOVE")) {
            doMove();
        } else if (temp.startsWith("FIGHT")) {
            doFight();
        } else {
            doExpression(temp);
        }
    }

    private static void doIf(String cmd) {
        // 处理IF语句
        String[] temp = cmd.split(" ");
        int num = 0;// 用于匹配IF与ENDIF时用的变量
        int b = 0;// IF语句条件不成立时出口所在的行号
        String ifExp = "";// 处理后的IF表达式
        // 条件不成立时的出口b
        for (int i = curIndex + 1; i < script.data.length; i++) {
            if (script.data[i].startsWith("IF")) {
                num++;
            } else if (script.data[i].startsWith("ENDIF")) {
                if (num == 0) {
                    // 匹配正确
                    b = i;// 出口在ENDIF这一句
                    break;
                } else {
                    // 匹配不正确
                    num--;
                }
            }
        }
        ifExp = "IF " + ExpParser.getBackExp(temp[1]) + " " + b;
        script.data[curIndex] = ifExp;
        curIndex++;
    }

    private static void doEndif() {
        curIndex++;
    }

    private static void doWhile(String cmd) {
        // 处理WHILE语句
        String[] temp = cmd.split(" ");
        int num = 0;// 用于匹配WHILE与ENDWHILE时用的变量
        int b = 0;// WHILE语句条件不成立时出口所在的行号
        String whileExp = "";// 处理后的WHILE表达式
        // 条件不成立时的出口b
        for (int i = curIndex + 1; i < script.data.length; i++) {
            if (script.data[i].startsWith("WHILE")) {
                num++;
            } else if (script.data[i].startsWith("ENDWHILE")) {
                if (num == 0) {
                    // 匹配正确
                    b = i + 1;// 出口在ENDWHILE的下一句
                    break;
                } else {
                    // 匹配不正确
                    num--;
                }
            }
        }
        whileExp = "WHILE " + ExpParser.getBackExp(temp[1]) + " " + b;
        script.data[curIndex] = whileExp;
        curIndex++;
    }

    private static void doEndwhile() {
        int num = 0;
        String endwhileExp = "";// 处理后的ENDWHILE语句
        int a = 0;// ENDWHILE语句的出口
        for (int i = curIndex - 1; i >= 0; i--) {
            if (script.data[i].startsWith("ENDWHILE")) {
                num++;
            } else if (script.data[i].startsWith("WHILE")) {
                if (num == 0) {
                    // 匹配正确
                    a = i;
                    break;
                } else {
                    // 匹配不正确
                    num--;
                }
            }
        }
        endwhileExp = "ENDWHILE " + a;
        script.data[curIndex] = endwhileExp;
        curIndex++;
    }

    private static void doBreak() {
        String breakExp = "";// 处理后的BREAK语句
        int a = 0;// BREAK语句的出口
        for (int i = curIndex + 1; i < script.data.length; i++) {
            if (script.data[i].startsWith("ENDWHILE")) {
                a = i + 1;// 循环语句的下一句
                break;
            }
        }
        breakExp = "BREAK " + a;
        script.data[curIndex] = breakExp;
        curIndex++;
    }

    private static void doContinue() {
        String continueExp = "";// 处理后的CONTINUE语句
        int a = 0;// CONTINUE语句的出口
        for (int i = curIndex + 1; i < script.data.length; i++) {
            if (script.data[i].startsWith("ENDWHILE")) {
                a = i;// ENDWHILE语句这一句
                break;
            }
        }
        continueExp = "CONTINUE " + a;
        script.data[curIndex] = continueExp;
        curIndex++;
    }

    private static void doExit() {
        curIndex++;
    }

    private static void doDialog() {
        curIndex++;
    }

    private static void doSound() {
        curIndex++;
    }

    private static void doWait() {
        curIndex++;
    }

    private static void doItemShop() {
        curIndex++;
    }

    private static void doEquipShop() {
        curIndex++;
    }

    private static void doGameOver() {
        curIndex++;
    }

    private static void doMap() {
        curIndex++;
    }

    private static void doFace() {
        curIndex++;
    }

    private static void doMove() {
        curIndex++;
    }

    private static void doFight() {
        curIndex++;
    }

    private static void doExpression(String temp) {
        script.data[curIndex] = ExpParser.getBackExp(temp);
        curIndex++;
    }

    private static void clean() {
        // 清除脚本语句中的空行和所有注释
        int num = script.data.length;//用于保存脚本语句新的长度
        String temp = "";
        for (int i = 0; i < num; i++) {
            temp = script.data[i];
            if (temp.startsWith("//") || temp.equals("") || temp == null) {
                //空行或注释后的语句前移
                for (int j = i + 1; j < num; j++) {
                    script.data[j - 1] = script.data[j];
                }
                num--;//脚本语句数减少1
                i--;//移除一个注释或空行后原注释或空行的下一句就到了原来注释或空行的位置，要从这里继续检查
            }
        }
        String[] newData = new String[num];//新的脚本语句数组
        System.arraycopy(script.data, 0, newData, 0, num);
        script.data = newData;
    }

    public static CommandSet convert(Map map, int y, int x) {
        Script s = map.getScriptList()[y][x];
        FinalScript fs = new FinalScript();
        fs.type = map.getScriptType()[y][x];
        fs.row = s.getRow();
        fs.col = s.getCol();
        fs.data = new String[s.size()];
        for (int i = 0; i < s.size(); i++) {
            fs.data[i] = s.getString(i);
        }
        script = fs;
        commandSet = new CommandSet();

        commandSet.type = script.type;
        commandSet.row = script.row;
        commandSet.col = script.col;
//        printScript();
        format();//格式化脚本，去掉语句首尾的空格
//        printScript();
        clean();//去掉脚本中所有的注释和空行
//        printScript();
        curIndex = 0;
        int length = script.data.length;
        while (curIndex < length) {
            analyse(script.data[curIndex]);
        }
//        printScript();
        creatCommand();
        return commandSet;
    }

    private static void format() {
        // 脚本格式化，去掉脚本每行开头和结尾的空格
        String temp = "";
        for (int i = 0; i < script.data.length; i++) {
            temp = script.data[i];
            if (temp.startsWith(" ") || temp.endsWith(" ")) {
                script.data[i] = temp.trim();
            }
        }
    }

    private static void printScript() {
        for (int i = 0; i < script.data.length; i++) {
            System.out.println(i + ":" + script.data[i]);
        }
        System.out.println("\n\n");
    }
}
