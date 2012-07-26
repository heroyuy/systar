package com.soyomaker.test;

import java.util.ArrayList;
import java.util.Collection;

import com.soyomaker.data.GObject;
import com.soyomaker.data.IGObject;

public class JSONTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// 1
		GObject obj1 = new GObject();
		obj1.putInt("id", 101001);
		System.out.println(obj1.toJson());
		// 2
		GObject obj2 = new GObject();
		obj1.putObject("child", obj2);
		System.out.println(obj1.toJson());
		// 3
		Collection<Integer> c1 = new ArrayList<Integer>();
		c1.add(12);
		c1.add(23);
		obj1.putIntArray("intArray", c1);
		System.out.println(obj1.toJson());
		// 4
		Collection<IGObject> c2 = new ArrayList<IGObject>();
		GObject obj3 = new GObject();
		obj3.putInt("id", 101002);
		GObject obj4 = new GObject();
		obj4.putInt("id", 101003);
		c2.add(obj3);
		c2.add(obj4);
		obj1.putObjectArray("childArray", c2);
		System.out.println(obj1.toJson());
		// ======================================================
		System.out.println("======================================================");
		IGObject obj5 = GObject.createFromJson(obj1.toJson());
		System.out.println(obj5.toJson());
	}

}
