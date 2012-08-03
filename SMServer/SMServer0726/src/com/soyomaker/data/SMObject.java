package com.soyomaker.data;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

public class SMObject {
	public static SMObject createFromBytes(byte[] bytes) {
		try {
			return BinarySerializer.binary2object(bytes);
		} catch (IOException e) {
			Logger.getLogger(SMObject.class).error(
					"Cann't create GUObject from byte array.");
			return null;
		}
	}

	public static SMObject createFromJson(String jsonStr) {
		return JsonSerializer.json2object(jsonStr);
	}

	private String type;

	private Map<String, SMDataWrapper> dataHolder = new HashMap<String, SMDataWrapper>();

	public void clear() {
		dataHolder.clear();
	}

	// 以下为getter方法

	public SMDataWrapper get(String key) {
		return dataHolder.get(key);
	}

	public Boolean getBool(String key) {
		SMDataWrapper o = dataHolder.get(key);

		if (o == null)
			return null;
		else
			return (Boolean) o.getValue();
	}

	@SuppressWarnings("unchecked")
	public Collection<Boolean> getBoolArray(String key) {
		SMDataWrapper o = dataHolder.get(key);

		if (o == null)
			return null;
		else
			return (Collection<Boolean>) o.getValue();
	}

	public Byte getByte(String key) {
		SMDataWrapper o = dataHolder.get(key);

		if (o == null)
			return null;
		else
			return (Byte) o.getValue();
	}

	public byte[] getByteArray(String key) {
		SMDataWrapper o = dataHolder.get(key);

		if (o == null)
			return null;
		else
			return (byte[]) o.getValue();
	}

	public Double getDouble(String key) {
		SMDataWrapper o = dataHolder.get(key);

		if (o == null)
			return null;
		else
			return (Double) o.getValue();
	}

	@SuppressWarnings("unchecked")
	public Collection<Double> getDoubleArray(String key) {
		SMDataWrapper o = dataHolder.get(key);

		if (o == null)
			return null;
		else
			return (Collection<Double>) o.getValue();
	}

	public Float getFloat(String key) {
		SMDataWrapper o = dataHolder.get(key);

		if (o == null)
			return null;
		else
			return (Float) o.getValue();
	}

	@SuppressWarnings("unchecked")
	public Collection<Float> getFloatArray(String key) {
		SMDataWrapper o = dataHolder.get(key);

		if (o == null)
			return null;
		else
			return (Collection<Float>) o.getValue();
	}

	public Integer getInt(String key) {
		SMDataWrapper o = dataHolder.get(key);

		if (o == null)
			return null;
		else
			return (Integer) o.getValue();
	}

	@SuppressWarnings("unchecked")
	public Collection<Integer> getIntArray(String key) {
		SMDataWrapper o = dataHolder.get(key);

		if (o == null)
			return null;
		else
			return (Collection<Integer>) o.getValue();
	}

	public Set<String> getKeys() {
		return dataHolder.keySet();
	}

	public Long getLong(String key) {
		SMDataWrapper o = dataHolder.get(key);

		if (o == null)
			return null;
		else
			return (Long) o.getValue();
	}

	@SuppressWarnings("unchecked")
	public Collection<Long> getLongArray(String key) {
		SMDataWrapper o = dataHolder.get(key);

		if (o == null)
			return null;
		else
			return (Collection<Long>) o.getValue();
	}

	public SMObject getObject(String key) {
		SMDataWrapper o = dataHolder.get(key);

		if (o == null)
			return null;
		else
			return (SMObject) o.getValue();
	}

	@SuppressWarnings("unchecked")
	public Collection<SMObject> getObjectArray(String key) {
		SMDataWrapper o = dataHolder.get(key);

		if (o == null)
			return null;
		else
			return (Collection<SMObject>) o.getValue();
	}

	public Short getShort(String key) {
		SMDataWrapper o = dataHolder.get(key);

		if (o == null)
			return null;
		else
			return (Short) o.getValue();
	}

	@SuppressWarnings("unchecked")
	public Collection<Short> getShortArray(String key) {
		SMDataWrapper o = dataHolder.get(key);

		if (o == null)
			return null;
		else
			return (Collection<Short>) o.getValue();
	}

	public String getString(String key) {
		SMDataWrapper o = dataHolder.get(key);

		if (o == null)
			return null;
		else
			return (String) o.getValue();
	}

	@SuppressWarnings("unchecked")
	public Collection<String> getStringArray(String key) {
		SMDataWrapper o = dataHolder.get(key);

		if (o == null)
			return null;
		else
			return (Collection<String>) o.getValue();
	}

	public String getType() {
		return type;
	}

	// 以下为setter方法

	public void put(String key, SMDataWrapper wrappedObject) {
		putObj(key, wrappedObject, null);
	}

	public void putBool(String key, boolean value) {
		putObj(key, value, SMDataType.BOOL);
	}

	public void putBoolArray(String key, Collection<Boolean> value) {
		putObj(key, value, SMDataType.BOOL_ARRAY);
	}

	public void putByte(String key, byte value) {
		putObj(key, value, SMDataType.BYTE);
	}

	public void putByteArray(String key, byte[] value) {
		putObj(key, value, SMDataType.BYTE_ARRAY);
	}

	public void putDouble(String key, double value) {
		putObj(key, value, SMDataType.DOUBLE);
	}

	public void putDoubleArray(String key, Collection<Double> value) {
		putObj(key, value, SMDataType.DOUBLE_ARRAY);
	}

	public void putFloat(String key, float value) {
		putObj(key, value, SMDataType.FLOAT);
	}

	public void putFloatArray(String key, Collection<Float> value) {
		putObj(key, value, SMDataType.FLOAT_ARRAY);
	}

	public void putInt(String key, int value) {
		putObj(key, value, SMDataType.INT);
	}

	public void putIntArray(String key, Collection<Integer> value) {
		putObj(key, value, SMDataType.INT_ARRAY);
	}

	public void putLong(String key, long value) {
		putObj(key, value, SMDataType.LONG);
	}

	public void putLongArray(String key, Collection<Long> value) {
		putObj(key, value, SMDataType.LONG_ARRAY);
	}

	public void putObject(String key, SMObject value) {
		putObj(key, value, SMDataType.OBJECT);
	}

	public void putObjectArray(String key, Collection<SMObject> value) {
		putObj(key, value, SMDataType.OBJECT_ARRAY);
	}

	public void putShort(String key, short value) {
		putObj(key, value, SMDataType.SHORT);
	}

	public void putShortArray(String key, Collection<Short> value) {
		putObj(key, value, SMDataType.SHORT_ARRAY);
	}

	// 以下为其他方法

	public void putString(String key, String value) {
		putObj(key, value, SMDataType.STRING);
	}

	public void putStringArray(String key, Collection<String> value) {
		putObj(key, value, SMDataType.STRING_ARRAY);
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
			Logger.getLogger(getClass()).error(
					"Cann't serialize GUObject to byte array.");
			return new byte[0];
		}
	}

	public String toJson() {
		return JsonSerializer.object2json(this);
	}

	public String toString() {
		StringBuffer buf = new StringBuffer();
		buf.append(getType() + "{");
		for (String key : getKeys()) {
			buf.append(key + ":" + dataHolder.get(key) + ";");
		}
		buf.append("}");
		return buf.toString();
	}

	private void putObj(String key, Object value, SMDataType typeId) {
		if (key == null)
			throw new IllegalArgumentException(
					"GUObject requires a non-null key for a 'put' operation!");

		if (key.length() > 255)
			throw new IllegalArgumentException(
					"GUObject keys must be less than 255 characters!");

		if (value == null)
			throw new IllegalArgumentException(
					"GUObject requires a non-null value!");

		if (value instanceof SMDataWrapper)
			dataHolder.put(key, (SMDataWrapper) value);
		else
			dataHolder.put(key, new SMDataWrapper(typeId, value));
	}
}
