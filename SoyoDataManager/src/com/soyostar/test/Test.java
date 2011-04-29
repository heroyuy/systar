/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.soyostar.test;

import java.io.Serializable;

/**
 *
 * @author Administrator
 */
public class Test implements Serializable{

   public int i=0;
   
   public Test(int i){
       this.i=i;
   }

    @Override
   public String toString(){
       return i+"";
   }
}
