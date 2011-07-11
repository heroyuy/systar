package emulator.engine.script.operation;

import emulator.engine.script.VarList;

/**
 *
 * ·Ç £¡
 */
public class Not implements OperationInterface {

    public String operate(String operand1, String operand2) {
        String result = null;
        if (se.getVarList().getValue(operand1).equals("true")) {
            result = "false";
        } else if (se.getVarList().getValue(operand1).equals("false")) {
            result = "true";
        }
        return result;
    }
}
