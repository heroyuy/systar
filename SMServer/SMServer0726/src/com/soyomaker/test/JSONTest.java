package com.soyomaker.test;

import java.util.ArrayList;
import java.util.Collection;

import com.soyomaker.data.SMObject;
import com.soyomaker.data.SMObject;

public class JSONTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// 1
		SMObject obj1 = new SMObject();
		obj1.putInt("id", 101001);
		System.out.println(obj1.toJson());
		// 2
		SMObject obj2 = new SMObject();
		obj1.putObject("child", obj2);
		System.out.println(obj1.toJson());
		// 3
		Collection<Integer> c1 = new ArrayList<Integer>();
		c1.add(12);
		c1.add(23);
		obj1.putIntArray("intArray", c1);
		System.out.println(obj1.toJson());
		// 4
		Collection<SMObject> c2 = new ArrayList<SMObject>();
		SMObject obj3 = new SMObject();
		obj3.putInt("id", 101002);
		SMObject obj4 = new SMObject();
		obj4.putInt("id", 101003);
		c2.add(obj3);
		c2.add(obj4);
		obj1.putObjectArray("childArray", c2);
		System.out.println(obj1.toJson());
		// ======================================================
		System.out.println("======================================================");
		SMObject obj5 = SMObject.createFromJson(obj1.toJson());
		System.out.println(obj5.toJson());
	}

}
