package com.soyomaker.test;

import java.util.ArrayList;
import java.util.Collection;

import com.soyomaker.core.GUObject;
import com.soyomaker.core.IGUObject;

public class JSONTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// 1
		GUObject obj1 = new GUObject();
		obj1.putInt("id", 101001);
		System.out.println(obj1.toJson());
		// 2
		GUObject obj2 = new GUObject();
		obj1.putObject("child", obj2);
		System.out.println(obj1.toJson());
		// 3
		Collection<Integer> c1 = new ArrayList<Integer>();
		c1.add(12);
		c1.add(23);
		obj1.putIntArray("intArray", c1);
		System.out.println(obj1.toJson());
		// 4
		Collection<IGUObject> c2 = new ArrayList<IGUObject>();
		GUObject obj3 = new GUObject();
		obj3.putInt("id", 101002);
		GUObject obj4 = new GUObject();
		obj4.putInt("id", 101003);
		c2.add(obj3);
		c2.add(obj4);
		obj1.putObjectArray("childArray", c2);
		System.out.println(obj1.toJson());
		// ======================================================
		System.out.println("======================================================");
		IGUObject obj5 = GUObject.createFromJson(obj1.toJson());
		System.out.println(obj5.toJson());
	}

}
