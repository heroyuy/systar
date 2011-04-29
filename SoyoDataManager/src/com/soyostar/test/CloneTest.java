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
    public T t = new T(10);

    public static void main(String[] args) {
        System.out.println("数组序列化克隆测试-------------------------------------");
        CloneTest[] ct = new CloneTest[2];
        ct[0] = new CloneTest();
        ct[1] = new CloneTest();
        CloneTest[] ct2 = Clone.clone(ct);
        ct[0].i[0] = 100;
        System.out.println("" + ct2[0].i[0]);
        System.out.println("" + (ct[0].t == ct2[0].t));
        ct[0].t.i = 100;
        System.out.println(ct2[0].t);

        System.out.println("序列化克隆的类型测试-------------------------------------");
        T t = new T2(100);
        T t2 = Clone.clone(t);
        System.out.println("t2 instanceof T2 ->" + (t2 instanceof T2));
        System.out.println("t2:" + t2);
        System.out.println("集合序列化克隆测试-------------------------------------");
        ArrayList<T> al = new ArrayList<T>();
        al.add(new T(10));
        al.add(new T2(100));
        ArrayList<T> al2 = Clone.clone(al);
        al.get(0).i = 100;
        System.out.println(al.get(0) + " " + al2.get(0));
        System.out.println(al.get(0) == al2.get(0));
        System.out.println(al2.get(0) instanceof T);
        System.out.println(al2.get(1) instanceof T);
        System.out.println(al2.get(0) instanceof T2);
        System.out.println(al2.get(1) instanceof T2);
        System.out.println("数组克隆测试2------------------------");
        int[] aa={0,1,2,3,4,5};
        int[] bb=new int[0];
        Clone.clone(aa, bb);
        System.out.println("bb.length->"+bb.length);//0
    }
}

class T implements Serializable {

    public int i = 0;

    public T(int i) {
        this.i = i;
    }

    public String toString() {
        return i + "";
    }
}

class T2 extends T {

    public T2(int i) {
        super(i);
    }
}
