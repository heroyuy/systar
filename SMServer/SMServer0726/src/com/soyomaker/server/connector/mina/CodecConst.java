package com.soyomaker.server.connector.mina;

/**
 * 包含在Encode和Decode时候要用到的常量。
 * 
 * @author zhangjun
 * 
 */
public class CodecConst {
	// public static final long GDOBJECT_TYPE_ID = 4671171336159637860L;
	// public static final long GDARRAY_TYPE_ID = 2478785576150222925L;

	/**
	 * 包的类型
	 */
	public static String PACKAGE_TYPE_NAME = "package";

	/**
	 * 包体的key。注：包是只带一个GUObjectArray的GUObject
	 */
	public static String PACKAGE_ARRAY_KEY = "list";

}
