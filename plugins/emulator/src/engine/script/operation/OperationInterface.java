package engine.script.operation;

import engine.script.ScriptEngine;

/**
 *
 * 运算接口，所有运算符需要实现
 */
public interface OperationInterface {

    public static final ScriptEngine se = ScriptEngine.getInstance();

    /**
     * 双目运算符
     * @param operand1 操作数1
     * @param operand2 操作数2
     * @return 返回运算结果
     */
    public String operate(String operand1, String operand2);
}
