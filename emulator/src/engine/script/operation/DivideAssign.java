package engine.script.operation;

import engine.script.VarList;

/**
 *
 * 赋值 /=
 */
public class DivideAssign implements OperationInterface {

    public String operate(String operand1, String operand2) {
        String result = Integer.parseInt(se.getVarList().getValue(operand1)) / Integer.parseInt(se.getVarList().getValue(operand2)) + "";
        se.getVarList().setValue(operand1, result);
        return result;
    }
}
