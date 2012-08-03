package com.soyomaker.common;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JsonSerializer {

	public static GameObject json2object(String jsonStr) {
		JSONObject jsonObject = JSONObject.fromObject(jsonStr);
		return jsonObject2GUObject(jsonObject);

	}

	public static String object2json(GameObject object) {
		Map<String, Object> map = object2map(object);
		return JSONObject.fromObject(map).toString();
	}

	/**
	 * 辅助方法 JSONObject->GUObject
	 * 
	 * @param jsnObj
	 * @return
	 */
	private static GameObject jsonObject2GUObject(JSONObject jsonObject) {
		GameObject resObj = new GameObject();
		Iterator<?> iterator = jsonObject.keys();
		while (iterator.hasNext()) {
			String key = (String) iterator.next();
			Object obj = jsonObject.get(key);
			// Regular int, which covers also: byte, short, int
			if (obj instanceof Integer) {
				resObj.putInt(key, (Integer) obj);
				// Long int
			} else if (obj instanceof Long) {
				resObj.putLong(key, (Long) obj);
				// Floating point as double
			} else if (obj instanceof Double) {
				resObj.putDouble(key, (Double) obj);
				// Bool
			} else if (obj instanceof Boolean) {
				resObj.putBool(key, (Boolean) obj);
				// UTF-String
			} else if (obj instanceof String) {
				resObj.putString(key, (String) obj);
				// JSON Object
			} else if (obj instanceof JSONObject) {
				resObj.putObject(key, jsonObject2GUObject((JSONObject) obj));
			}
			// JSON Array
			else if (obj instanceof JSONArray) {
				putJSONArrayInGUObject(key, resObj, (JSONArray) obj);
			}
			// Unknown type
			else {
				throw new IllegalArgumentException(
						String.format(
								"Unrecognized DataType while converting JSONObject 2 GUObject. Object: %s, Type: %s",
								obj, (obj == null) ? "null" : obj.getClass()));
			}
		}
		return resObj;
	}

	/**
	 * 辅助方法，IGUobject->Map
	 * 
	 * @param object
	 * @return
	 */
	private static Map<String, Object> object2map(GameObject object) {
		Map<String, Object> map = new HashMap<String, Object>();
		Iterator<String> iterator = object.getKeys().iterator();
		while (iterator.hasNext()) {
			String key = (String) iterator.next();
			GameDataWrapper dataWrapper = object.get(key);
			GameDataType dataType = dataWrapper.getType();
			if (dataType == GameDataType.OBJECT) {
				map.put(key, object2map((GameObject) dataWrapper.getValue()));
			} else if (dataType == GameDataType.OBJECT_ARRAY) {
				Collection<Map<String, Object>> c = new ArrayList<Map<String, Object>>();
				@SuppressWarnings("unchecked")
				Collection<GameObject> data = (Collection<GameObject>) dataWrapper
						.getValue();
				for (GameObject iguObj : data) {
					c.add(object2map(iguObj));
				}
				map.put(key, c);
			} else {
				map.put(key, dataWrapper.getValue());
			}
		}
		return map;
	}

	/**
	 * 辅助方法 JSONArray to GUObject
	 * 
	 * @param key
	 * @param resObj
	 * @param jsonArray
	 */
	@SuppressWarnings("unchecked")
	private static void putJSONArrayInGUObject(String key, GameObject resObj,
			JSONArray jsonArray) {
		if (!jsonArray.isEmpty()) {
			Object value = jsonArray.get(0);
			if (value instanceof Integer) {
				resObj.putIntArray(key, JSONArray.toCollection(jsonArray));
				// Long int
			} else if (value instanceof Long) {
				resObj.putLongArray(key, JSONArray.toCollection(jsonArray));
				// Floating point as double
			} else if (value instanceof Double) {
				resObj.putDoubleArray(key, JSONArray.toCollection(jsonArray));
				// Bool
			} else if (value instanceof Boolean) {
				resObj.putBoolArray(key, JSONArray.toCollection(jsonArray));
				// UTF-String
			} else if (value instanceof String) {
				resObj.putStringArray(key, JSONArray.toCollection(jsonArray));
				// JSON Object
			} else if (value instanceof JSONObject) {
				Collection<GameObject> c = new ArrayList<GameObject>();
				int size = jsonArray.size();
				for (int i = 0; i < size; i++) {
					c.add(jsonObject2GUObject((JSONObject) jsonArray.get(i)));
				}
				resObj.putObjectArray(key, c);
			}
			// JSON Array
			else if (value instanceof JSONArray) {
				// 不存在这种情况
			}
			// Unknown type
			else {
				throw new IllegalArgumentException(
						String.format(
								"Unrecognized DataType while converting JSONObject 2 GUObject. Object: %s, Type: %s",
								value,
								(value == null) ? "null" : value.getClass()));
			}
		} else {
			resObj.putIntArray(key, new ArrayList<Integer>());
		}
	}
}
