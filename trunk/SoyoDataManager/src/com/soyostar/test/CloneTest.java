package com.soyostar.test;

import com.soyostar.util.Clone;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Administrator
 */
public class CloneTest implements Serializable {

    public int[] i = {1, 2, 3, 4, 5, 6};
    public String[] s = {"1", "2", "3", "4", "5", "6", "7", "8"};
    public TT t = new TT(10);

    public static void main(String[] args) {
        System.out.println("数组序列化克隆测试-------------------------------------");
        CloneTest[] ct = new CloneTest[2];
        ct[0] = new CloneTest();
        ct[1] = new CloneTest();
        CloneTest[] ct2 = Clone.clone(ct);
        ct[0].i[0] = 100;
        System.out.println("" + ct2[0].i[0]);
        System.out.println("" + (ct[0].t == ct2[0].t));
        ct[0].t.Int = 100;
        System.out.println(ct2[0].t);

        System.out.println("序列化克隆的类型测试-------------------------------------");
        TT t = new T2(100);
        TT t2 = Clone.clone(t);
        System.out.println("t2 instanceof T2 ->" + (t2 instanceof T2));
        System.out.println("t2:" + t2);
        System.out.println("集合序列化克隆测试-------------------------------------");
        ArrayList<TT> al = new ArrayList<TT>();
        al.add(new TT(10));
        al.add(new T2(100));
        ArrayList<TT> al2 = Clone.clone(al);
        al.get(0).Int = 100;
        System.out.println(al.get(0) + " " + al2.get(0));
        System.out.println(al.get(0) == al2.get(0));
        System.out.println(al2.get(0) instanceof TT);
        System.out.println(al2.get(1) instanceof TT);
        System.out.println(al2.get(0) instanceof T2);
        System.out.println(al2.get(1) instanceof T2);
        System.out.println("数组克隆测试2------------------------");
        int[] aa = {0, 1, 2, 3, 4, 5};
        int[] bb = new int[0];
        Clone.clone(aa, bb);
        System.out.println("bb.length->" + bb.length);//0
        System.out.println("<T1, T2> void clone(T1 t1, T2 t2)测试------------------------");
        TT tt = new TT(5);
        TT tt2 = new TT(8);
        tt2.intAl = null;
        System.out.println("原始数据");
        System.out.println(tt.toString(6));
        System.out.println(tt2.toString(6));
        Clone.clone(tt, tt2);
        System.out.println("克隆后");
        System.out.println(tt.toString(6));
        System.out.println(tt2.toString(6));
        System.out.println("修改基本类型后>");
        tt.Int = 12345;
        System.out.println(tt.toString(0));
        System.out.println(tt2.toString(0));
        System.out.println("修改基本类型数组后>");
        tt2.intAA[2] = 76;
        System.out.println(tt.toString(1));
        System.out.println(tt2.toString(1));
        tt.intAA = new int[2];
        tt.intAA[0] = 321;
        System.out.println(tt.toString(1));
        System.out.println(tt2.toString(1));
        System.out.println("修改基本类型集合后>");
        tt.intAl.add(Integer.SIZE);
        tt2.intAl.set(0, 128);
        System.out.println(tt.toString(2));
        System.out.println(tt2.toString(2));
        System.out.println("修改对象后>");
        tt.test.i = 123454321;
        tt2.test = new Test(987);
        System.out.println(tt.toString(3));
        System.out.println(tt2.toString(3));
        System.out.println("修改对象数组后>");
        tt.testAA[3].i = 18;
        tt.testAA[4] = new Test(19);
        tt2.testAA = new Test[10];
        tt2.testAA[7] = new Test(12);
        System.out.println(tt.toString(4));
        System.out.println(tt2.toString(4));
        System.out.println("修改对象集合后>");
        tt.testAl.add(null);
        tt.testAl.add(null);
        tt.testAl.remove(0);
        tt.testAl.get(0).i = 9999;
        tt2.testAl = new ArrayList<Test>();
        tt2.testAl.add(new Test(1982));
        tt2.testAl.add(null);
        System.out.println(tt.toString(5));
        System.out.println(tt2.toString(5));
        System.out.println("两对象为同一对象并克隆>");
        Test test = new Test(102938);
        tt.test = test;
        tt2.test = test;
        Clone.clone(tt2, tt);
        test.i = 1111;
        System.out.println(tt.toString(3));
        System.out.println(tt2.toString(3));
    }
}

class T implements Serializable {

    public int i = 0;
    public int[] ii = {1, 2, 3, 4, 5};
    public ArrayList<Test> testAl = null;
    public Test[] tesAA = {new Test(1), new Test(2)};

    public T(int i) {
        this.i = i;
        testAl = new ArrayList<Test>();
        testAl.add(new Test(10));
        testAl.add(new Test(100));
    }

    @Override
    public String toString() {
        return i + "";
    }
}

class T2 extends TT {

    public T2(int i) {
        super(i);
    }
}
