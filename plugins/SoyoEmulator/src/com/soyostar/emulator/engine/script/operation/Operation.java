package com.soyostar.emulator.engine.script.operation;

import java.util.Hashtable;

/**
 *
 * 运算
 */
public class Operation {

    private Hashtable operationList = null;
    private OperationInterface oi = null;

    public Operation() {
        operationList = new Hashtable();
        init();
    }

    private void init() {
        //以后可以考虑改为读取文件+反射
        operationList.put("!", new Not());
        operationList.put("*", new Multiply());
        operationList.put("/", new Divide());
        operationList.put("%", new Mod());
        operationList.put("+", new Plus());
        operationList.put("-", new Subtract());
        operationList.put(">", new Greater());
        operationList.put(">=", new NotLesser());
        operationList.put("<", new Lesser());
        operationList.put("<=", new NotGreater());
        operationList.put("==", new Equal());
        operationList.put("!=", new NotEqual());
        operationList.put("&&", new And());
        operationList.put("||", new Or());
        operationList.put("=", new Assign());
        operationList.put("+=", new PlusAssign());
        operationList.put("-=", new SubtractAssign());
        operationList.put("*=", new MultiplyAssign());
        operationList.put("/=", new DivideAssign());
        operationList.put("%=", new ModAssign());

    }

    public String operate(String operator, String operand1, String operand2) {
        String result = null;
        oi = (OperationInterface) operationList.get(operator);
        result = oi.operate(operand1, operand2);
        return result;
    }
}
