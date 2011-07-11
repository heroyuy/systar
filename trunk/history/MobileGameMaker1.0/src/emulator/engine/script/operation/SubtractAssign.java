package emulator.engine.script.operation;

import emulator.engine.script.VarList;

/**
 *
 * ¸³Öµ -=
 */
public class SubtractAssign implements OperationInterface {

    public String operate(String operand1, String operand2) {
        String result = Integer.parseInt(se.getVarList().getValue(operand1)) - Integer.parseInt(se.getVarList().getValue(operand2)) + "";
        se.getVarList().setValue(operand1, result);
        return result;
    }
}
