package com.soyomaker.test;

import java.util.ArrayList;
import java.util.Collection;

import com.soyomaker.lang.GameObject;

public class LuaTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// 1
		GameObject obj1 = new GameObject();
		obj1.putInt("id", 101001);
		System.out.println(obj1.toLua());
		// 2
		GameObject obj2 = new GameObject();
		obj1.putObject("child", obj2);
		System.out.println(obj1.toLua());
		// 3
		Collection<Integer> c1 = new ArrayList<Integer>();
		c1.add(12);
		c1.add(23);
		obj1.putIntArray("intArray", c1);
		System.out.println(obj1.toLua());
		// 4
		Collection<GameObject> c2 = new ArrayList<GameObject>();
		GameObject obj3 = new GameObject();
		obj3.putInt("id", 101002);
		GameObject obj4 = new GameObject();
		obj4.putInt("id", 101003);
		c2.add(obj3);
		c2.add(obj4);
		obj1.putObjectArray("childArray", c2);
		System.out.println(obj1.toLua());
	}

}
