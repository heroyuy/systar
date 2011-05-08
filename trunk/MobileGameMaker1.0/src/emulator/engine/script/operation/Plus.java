package emulator.engine.script.operation;

import emulator.engine.script.VarList;

/**
 *
 * �� +
 */
public class Plus implements OperationInterface {

    public String operate(String operand1, String operand12) {
        String result = null;
        byte type1 = VarList.getType(se.getVarList().getValue(operand1));
        byte type2 = VarList.getType(se.getVarList().getValue(operand12));
        if (type1 == VarList.INT) {
            //����+
            if (type2 == VarList.INT) {
                result = Integer.parseInt(se.getVarList().getValue(operand1)) + Integer.parseInt(se.getVarList().getValue(operand12)) + "";
            } else if (type2 == VarList.STRING) {
                result = "\"" + se.getVarList().getValue(operand1) + se.getVarList().getValue(operand12).substring(1);
            }

        } else if (type1 == VarList.STRING) {
            //�ַ���+
            if (type2 == VarList.INT) {
                result = se.getVarList().getValue(operand1).substring(0, se.getVarList().getValue(operand1).length() - 1) + se.getVarList().getValue(operand12) + "\"";
            } else if (type2 == VarList.STRING) {
                result = se.getVarList().getValue(operand1).substring(0, se.getVarList().getValue(operand1).length() - 1) + se.getVarList().getValue(operand12).substring(1);
            } else if (type2 == VarList.BOOLEAN) {
                result = se.getVarList().getValue(operand1).substring(0, se.getVarList().getValue(operand1).length() - 1) + se.getVarList().getValue(operand12) + "\"";
            }

        } else if (type1 == VarList.BOOLEAN) {
            //������+
            if (type2 == VarList.STRING) {
                result = "\"" + se.getVarList().getValue(operand1) + se.getVarList().getValue(operand12).substring(1);
            }
        }
        return result;
    }
}