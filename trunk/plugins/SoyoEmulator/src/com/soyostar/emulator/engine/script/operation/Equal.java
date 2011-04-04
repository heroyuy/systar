package com.soyostar.emulator.engine.script.operation;


/**
 *
 * 等于 ==
 */
public class Equal  implements OperationInterface {

    public String operate(String operand1, String operand2) {
        return (Integer.parseInt(se.getVarList().getValue(operand1)) == Integer.parseInt(se.getVarList().getValue(operand2))) + "";
    }
}
