package emulator.engine.script.operation;

import emulator.engine.script.VarList;

/**
 * ³Ë *
 */
public class Multiply implements OperationInterface {

    public String operate(String operand1, String operand2) {
        return Integer.parseInt(se.getVarList().getValue(operand1)) * Integer.parseInt(se.getVarList().getValue(operand2)) + "";
    }
}
