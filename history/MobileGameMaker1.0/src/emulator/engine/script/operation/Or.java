package emulator.engine.script.operation;

import emulator.engine.script.VarList;

/**
 *
 * »ò ||
 */
public class Or implements OperationInterface {

    public String operate(String operand1, String operand2) {
        String result = null;

        boolean oper1 = false;
        boolean oper2 = false;
        if (se.getVarList().getValue(operand1).equals("true")) {
            oper1 = true;
        } else if (se.getVarList().getValue(operand1).equals("false")) {
            oper1 = false;
        } else {
            return null;
        }

        if (se.getVarList().getValue(operand2).equals("true")) {
            oper2 = true;
        } else if (se.getVarList().getValue(operand2).equals("false")) {
            oper2 = false;
        } else {
            return null;
        }
        result = (oper1 || oper2) + "";
        return result;
    }
}
