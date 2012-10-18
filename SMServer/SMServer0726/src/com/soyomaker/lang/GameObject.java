package com.soyomaker.lang;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class GameObject {

	public static GameObject createFromBytes(byte[] bytes) {
		try {
			return BinarySerializer.binary2object(bytes);
		} catch (IOException e) {
			System.out.println("Cann't create GUObject from byte array.");
			return null;
		}
	}

	public static GameObject createFromJson(String jsonStr) {
		return JsonSerializer.json2object(jsonStr);
	}

	private String type = "";

	private Map<String, GameDataWrapper> dataHolder = new HashMap<String, GameDataWrapper>();

	public GameObject() {
	}

	public GameObject(String type) {
		super();
		this.type = type;
	}

	public void clear() {
		dataHolder.clear();
	}

	public boolean containsKey(String key) {
		return dataHolder.containsKey(key);
	}

	// 以下为getter方法

	public GameDataWrapper get(String key) {
		return dataHolder.get(key);
	}

	public Boolean getBool(String key) {
		GameDataWrapper o = dataHolder.get(key);

		if (o == null)
			return null;
		else
			return (Boolean) o.getValue();
	}

	@SuppressWarnings("unchecked")
	public Collection<Boolean> getBoolArray(String key) {
		GameDataWrapper o = dataHolder.get(key);

		if (o == null)
			return null;
		else
			return (Collection<Boolean>) o.getValue();
	}

	public Byte getByte(String key) {
		GameDataWrapper o = dataHolder.get(key);

		if (o == null)
			return null;
		else
			return (Byte) o.getValue();
	}

	public byte[] getByteArray(String key) {
		GameDataWrapper o = dataHolder.get(key);

		if (o == null)
			return null;
		else
			return (byte[]) o.getValue();
	}

	public Double getDouble(String key) {
		GameDataWrapper o = dataHolder.get(key);

		if (o == null)
			return null;
		else
			return (Double) o.getValue();
	}

	@SuppressWarnings("unchecked")
	public Collection<Double> getDoubleArray(String key) {
		GameDataWrapper o = dataHolder.get(key);

		if (o == null)
			return null;
		else
			return (Collection<Double>) o.getValue();
	}

	public Float getFloat(String key) {
		GameDataWrapper o = dataHolder.get(key);

		if (o == null)
			return null;
		else
			return (Float) o.getValue();
	}

	@SuppressWarnings("unchecked")
	public Collection<Float> getFloatArray(String key) {
		GameDataWrapper o = dataHolder.get(key);

		if (o == null)
			return null;
		else
			return (Collection<Float>) o.getValue();
	}

	public Integer getInt(String key) {
		GameDataWrapper o = dataHolder.get(key);

		if (o == null)
			return null;
		else
			return (Integer) o.getValue();
	}

	@SuppressWarnings("unchecked")
	public Collection<Integer> getIntArray(String key) {
		GameDataWrapper o = dataHolder.get(key);

		if (o == null)
			return null;
		else
			return (Collection<Integer>) o.getValue();
	}

	public Set<String> getKeys() {
		return dataHolder.keySet();
	}

	public Long getLong(String key) {
		GameDataWrapper o = dataHolder.get(key);

		if (o == null)
			return null;
		else
			return (Long) o.getValue();
	}

	@SuppressWarnings("unchecked")
	public Collection<Long> getLongArray(String key) {
		GameDataWrapper o = dataHolder.get(key);

		if (o == null)
			return null;
		else
			return (Collection<Long>) o.getValue();
	}

	public GameObject getObject(String key) {
		GameDataWrapper o = dataHolder.get(key);

		if (o == null)
			return null;
		else
			return (GameObject) o.getValue();
	}

	@SuppressWarnings("unchecked")
	public Collection<GameObject> getObjectArray(String key) {
		GameDataWrapper o = dataHolder.get(key);

		if (o == null)
			return null;
		else
			return (Collection<GameObject>) o.getValue();
	}

	public Short getShort(String key) {
		GameDataWrapper o = dataHolder.get(key);

		if (o == null)
			return null;
		else
			return (Short) o.getValue();
	}

	@SuppressWarnings("unchecked")
	public Collection<Short> getShortArray(String key) {
		GameDataWrapper o = dataHolder.get(key);

		if (o == null)
			return null;
		else
			return (Collection<Short>) o.getValue();
	}

	public String getString(String key) {
		GameDataWrapper o = dataHolder.get(key);

		if (o == null)
			return null;
		else
			return (String) o.getValue();
	}

	@SuppressWarnings("unchecked")
	public Collection<String> getStringArray(String key) {
		GameDataWrapper o = dataHolder.get(key);

		if (o == null)
			return null;
		else
			return (Collection<String>) o.getValue();
	}

	public String getType() {
		return type;
	}

	// 以下为setter方法

	public void put(String key, GameDataWrapper wrappedObject) {
		putObj(key, wrappedObject, null);
	}

	public void putBool(String key, boolean value) {
		putObj(key, value, GameDataType.BOOL);
	}

	public void putBoolArray(String key, Collection<Boolean> value) {
		putObj(key, value, GameDataType.BOOL_ARRAY);
	}

	public void putByte(String key, byte value) {
		putObj(key, value, GameDataType.BYTE);
	}

	public void putByteArray(String key, byte[] value) {
		putObj(key, value, GameDataType.BYTE_ARRAY);
	}

	public void putDouble(String key, double value) {
		putObj(key, value, GameDataType.DOUBLE);
	}

	public void putDoubleArray(String key, Collection<Double> value) {
		putObj(key, value, GameDataType.DOUBLE_ARRAY);
	}

	public void putFloat(String key, float value) {
		putObj(key, value, GameDataType.FLOAT);
	}

	public void putFloatArray(String key, Collection<Float> value) {
		putObj(key, value, GameDataType.FLOAT_ARRAY);
	}

	public void putInt(String key, int value) {
		putObj(key, value, GameDataType.INT);
	}

	public void putIntArray(String key, Collection<Integer> value) {
		putObj(key, value, GameDataType.INT_ARRAY);
	}

	public void putLong(String key, long value) {
		putObj(key, value, GameDataType.LONG);
	}

	public void putLongArray(String key, Collection<Long> value) {
		putObj(key, value, GameDataType.LONG_ARRAY);
	}

	private void putObj(String key, Object value, GameDataType typeId) {
		if (key == null)
			throw new IllegalArgumentException(
					"GUObject requires a non-null key for a 'put' operation!");

		if (key.length() > 255)
			throw new IllegalArgumentException(
					"GUObject keys must be less than 255 characters!");

		if (value == null)
			throw new IllegalArgumentException(
					"GUObject requires a non-null value!");

		if (value instanceof GameDataWrapper)
			dataHolder.put(key, (GameDataWrapper) value);
		else
			dataHolder.put(key, new GameDataWrapper(typeId, value));
	}

	public void putObject(String key, GameObject value) {
		putObj(key, value, GameDataType.OBJECT);
	}

	public void putObjectArray(String key, Collection<GameObject> value) {
		putObj(key, value, GameDataType.OBJECT_ARRAY);
	}

	public void putShort(String key, short value) {
		putObj(key, value, GameDataType.SHORT);
	}

	// 以下为其他方法

	public void putShortArray(String key, Collection<Short> value) {
		putObj(key, value, GameDataType.SHORT_ARRAY);
	}

	public void putString(String key, String value) {
		putObj(key, value, GameDataType.STRING);
	}

	public void putStringArray(String key, Collection<String> value) {
		putObj(key, value, GameDataType.STRING_ARRAY);
	}

	public void remove(String key) {
		dataHolder.remove(key);
	}

	public void setType(String type) {
		this.type = type;
	}

	public int size() {
		return dataHolder.size();
	}

	public byte[] toBinary() {
		try {
			return BinarySerializer.object2binary(this);
		} catch (IOException e) {
			System.out.println("Cann't serialize GUObject to byte array.");
			return new byte[0];
		}
	}

	public String toJson() {
		return JsonSerializer.object2json(this);
	}

	public String toLua() {
		return LuaSerializer.object2Lua(this);
	}

	public String toString() {
		StringBuffer buf = new StringBuffer();
		String type = this.getType();
		if (type != null) {
			buf.append(type);
		}
		buf.append("{");
		for (String key : getKeys()) {
			buf.append(key + ":" + dataHolder.get(key) + ";");
		}
		buf.append("}");
		return buf.toString();
	}
}
