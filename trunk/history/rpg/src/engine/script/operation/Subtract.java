package engine.script.operation;

import engine.script.VarList;

/**
 *
 * 减 -
 */
public class Subtract implements OperationInterface{

    public String operate(String operand1, String operand2) {
        return Integer.parseInt(se.getVarList().getValue(operand1)) - Integer.parseInt(se.getVarList().getValue(operand2)) + "";
    }

}
