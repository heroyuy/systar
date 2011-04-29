/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyostar.test;

import com.soyostar.util.Clone;
import java.util.ArrayList;

/**
 *
 * @author Administrator
 */
public class ArrayListTest {

    public static void main(String[] args) {
        ArrayList<Test> al = new ArrayList<Test>();
        al.add(new Test(1));
        ArrayList<Test> al2 = (ArrayList<Test>) al.clone();
        al.add(new Test(2));
        Test t1 = al.get(0);
        Test t2 = al2.get(0);
        System.out.println(t1 == t2);



        ArrayList<Integer> all = new ArrayList<Integer>();
        all.add(1);
        ArrayList<Integer> all2 = (ArrayList<Integer>) all.clone();
        all.add(2);
        all.set(0, 10);
        System.out.println(all2.get(0));




    }
}

class Test {

    int test = 0;

    public Test(int test) {
        this.test = test;
    }

}
