/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyostar.test;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Administrator
 */
public class TT implements Serializable {

    public int Int = -1;
    public int[] intAA = null;
    public List<Integer> intAl = null;
    public Test test = null;
    public Test[] testAA = null;
    public ArrayList<Test> testAl = null;

    public TT(int num) {
        this.Int = num;
        intAA = new int[num];
        for (int i = 0; i < num; i++) {
            intAA[i] = i;
        }
        intAl = new ArrayList<Integer>();
        for (int i = 0; i < num; i++) {
            intAl.add(i);
        }
        test = new Test(num);
        testAA = new Test[num];
        for (int i = 0; i < num; i++) {
            testAA[i] = new Test(i);
        }
        testAl = new ArrayList<Test>();
        for (int i = 0; i < num; i++) {
            testAl.add(new Test(i));
        }

    }

    public String toString(int index) {
        StringBuffer sb = new StringBuffer();
        switch (index) {
            case 0: {
                sb.append("基本类型(int):i->" + Int);
            }
            break;
            case 1: {
                if (intAA == null) {
                    sb.append("基本类型数组:intAA 值:" + intAA);
                } else {
                    sb.append("基本类型数组(" + intAA.getClass().getName() + "):intAA 长度:" + intAA.length + "->");
                    sb.append("[");
                    for (int i = 0; i < intAA.length; i++) {
                        sb.append(intAA[i]);
                        if (i < intAA.length - 1) {
                            sb.append(",");
                        }
                    }
                    sb.append("]");
                }
            }
            break;
            case 2: {
                if (intAl == null) {
                    sb.append("基本类型集合:intAl 值:" + intAl);
                } else {
                    sb.append("基本类型集合(" + intAl.getClass().getName() + "):intAl 长度:" + intAl.size() + "->");
                    sb.append("[");
                    for (int i = 0; i < intAl.size(); i++) {
                        sb.append(intAl.get(i));
                        if (i < intAl.size() - 1) {
                            sb.append(",");
                        }
                    }
                    sb.append("]");
                }
            }
            break;
            case 3: {
                if (test == null) {
                    sb.append("对象:test 值:" + test);
                } else {
                    sb.append("对象(" + test.getClass().getName() + "):test->" + test);
                }
            }
            break;
            case 4: {
                if (testAA == null) {
                    sb.append("对象数组:testAA 值:" + testAA);
                } else {
                    sb.append("对象数组(" + testAA.getClass().getName() + "):testAA 长度:" + testAA.length + "->");
                    sb.append("[");
                    for (int i = 0; i < testAA.length; i++) {
                        sb.append(testAA[i]);
                        if (i < testAA.length - 1) {
                            sb.append(",");
                        }
                    }
                    sb.append("]");
                }
            }
            break;
            case 5: {
                if (testAl == null) {
                    sb.append("对象集合:testAl 值:" + testAl);
                } else {
                    sb.append("对象集合(" + testAl.getClass().getName() + "):testAl 长度:" + testAl.size() + "->");
                    sb.append("[");
                    for (int i = 0; i < testAl.size(); i++) {
                        sb.append(testAl.get(i));
                        if (i < testAl.size() - 1) {
                            sb.append(",");
                        }
                    }
                    sb.append("]");
                }
            }
            break;

            case 6: {
                for (int i = 0; i < 6; i++) {
                    sb.append(toString(i) + "\n");
                }
            }
            break;
        }
        return sb.toString();
    }
}
