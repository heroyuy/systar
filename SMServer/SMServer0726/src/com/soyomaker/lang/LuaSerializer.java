package com.soyomaker.lang;

import java.util.Collection;

public class LuaSerializer {

	@SuppressWarnings("unchecked")
	public static String object2Lua(GameObject object) {
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		for (String key : object.getKeys()) {
			sb.append(key + "=");
			GameDataWrapper gdw = object.get(key);
			switch (gdw.getType()) {
			case BOOL:
			case BYTE:
			case SHORT:
			case INT:
			case LONG:
			case FLOAT:
			case DOUBLE:
				sb.append(gdw.getValue());
				break;
			case STRING:
				sb.append("\"" + gdw.getValue() + "\"");
				break;
			case OBJECT:
				sb.append(object2Lua((GameObject) gdw.getValue()));
				break;
			case BOOL_ARRAY:
			case BYTE_ARRAY:
			case SHORT_ARRAY:
			case INT_ARRAY:
			case LONG_ARRAY:
			case FLOAT_ARRAY:
			case DOUBLE_ARRAY:
				Collection<Object> c1 = (Collection<Object>) gdw.getValue();
				sb.append("{");
				for (Object obj : c1) {
					sb.append(obj + ",");
				}
				if (sb.charAt(sb.length() - 1) == ',') {
					sb.deleteCharAt(sb.length() - 1);
				}
				sb.append("}");
				break;
			case STRING_ARRAY:
				Collection<String> c2 = (Collection<String>) gdw.getValue();
				sb.append("{");
				for (String str : c2) {
					sb.append("\"" + str + "\",");
				}
				if (sb.charAt(sb.length() - 1) == ',') {
					sb.deleteCharAt(sb.length() - 1);
				}
				sb.append("}");
				break;
			case OBJECT_ARRAY:
				Collection<GameObject> c3 = (Collection<GameObject>) gdw
						.getValue();
				sb.append("{");
				for (GameObject obj : c3) {
					sb.append(object2Lua(obj) + ",");
				}
				if (sb.charAt(sb.length() - 1) == ',') {
					sb.deleteCharAt(sb.length() - 1);
				}
				sb.append("}");
				break;

			default:
				break;
			}
			sb.append(",");
		}
		if (sb.charAt(sb.length() - 1) == ',') {
			sb.deleteCharAt(sb.length() - 1);
		}
		sb.append("}");
		return sb.toString();
	}

}
