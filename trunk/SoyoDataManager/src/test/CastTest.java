/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

/**
 *
 * @author Administrator
 */
public class CastTest {

    public static void main(String[] arges) {
        B[] bs = new B[10];
        A[] as = bs;
        A a=null;
    }
}

class A {
}

class B extends A {
}
