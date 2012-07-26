package com.soyomaker.data;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

public class GObject implements IGObject {
	public static IGObject createFromBytes(byte[] bytes) {
		try {
			return BinarySerializer.binary2object(bytes);
		} catch (IOException e) {
			Logger.getLogger(GObject.class).error(
					"Cann't create GUObject from byte array.");
			return null;
		}
	}

	public static IGObject createFromJson(String jsonStr) {
		return JsonSerializer.json2object(jsonStr);
	}

	private String type;

	private Map<String, GDataWrapper> dataHolder = new HashMap<String, GDataWrapper>();

	@Override
	public void clear() {
		dataHolder.clear();
	}

	// 以下为getter方法

	@Override
	public GDataWrapper get(String key) {
		return dataHolder.get(key);
	}

	@Override
	public Boolean getBool(String key) {
		GDataWrapper o = dataHolder.get(key);

		if (o == null)
			return null;
		else
			return (Boolean) o.getObject();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Boolean> getBoolArray(String key) {
		GDataWrapper o = dataHolder.get(key);

		if (o == null)
			return null;
		else
			return (Collection<Boolean>) o.getObject();
	}

	@Override
	public Byte getByte(String key) {
		GDataWrapper o = dataHolder.get(key);

		if (o == null)
			return null;
		else
			return (Byte) o.getObject();
	}

	@Override
	public byte[] getByteArray(String key) {
		GDataWrapper o = dataHolder.get(key);

		if (o == null)
			return null;
		else
			return (byte[]) o.getObject();
	}

	@Override
	public Double getDouble(String key) {
		GDataWrapper o = dataHolder.get(key);

		if (o == null)
			return null;
		else
			return (Double) o.getObject();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Double> getDoubleArray(String key) {
		GDataWrapper o = dataHolder.get(key);

		if (o == null)
			return null;
		else
			return (Collection<Double>) o.getObject();
	}

	@Override
	public Float getFloat(String key) {
		GDataWrapper o = dataHolder.get(key);

		if (o == null)
			return null;
		else
			return (Float) o.getObject();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Float> getFloatArray(String key) {
		GDataWrapper o = dataHolder.get(key);

		if (o == null)
			return null;
		else
			return (Collection<Float>) o.getObject();
	}

	@Override
	public Integer getInt(String key) {
		GDataWrapper o = dataHolder.get(key);

		if (o == null)
			return null;
		else
			return (Integer) o.getObject();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Integer> getIntArray(String key) {
		GDataWrapper o = dataHolder.get(key);

		if (o == null)
			return null;
		else
			return (Collection<Integer>) o.getObject();
	}

	@Override
	public Set<String> getKeys() {
		return dataHolder.keySet();
	}

	@Override
	public Long getLong(String key) {
		GDataWrapper o = dataHolder.get(key);

		if (o == null)
			return null;
		else
			return (Long) o.getObject();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Long> getLongArray(String key) {
		GDataWrapper o = dataHolder.get(key);

		if (o == null)
			return null;
		else
			return (Collection<Long>) o.getObject();
	}

	@Override
	public IGObject getObject(String key) {
		GDataWrapper o = dataHolder.get(key);

		if (o == null)
			return null;
		else
			return (IGObject) o.getObject();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<IGObject> getObjectArray(String key) {
		GDataWrapper o = dataHolder.get(key);

		if (o == null)
			return null;
		else
			return (Collection<IGObject>) o.getObject();
	}

	@Override
	public Short getShort(String key) {
		GDataWrapper o = dataHolder.get(key);

		if (o == null)
			return null;
		else
			return (Short) o.getObject();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Short> getShortArray(String key) {
		GDataWrapper o = dataHolder.get(key);

		if (o == null)
			return null;
		else
			return (Collection<Short>) o.getObject();
	}

	@Override
	public String getString(String key) {
		GDataWrapper o = dataHolder.get(key);

		if (o == null)
			return null;
		else
			return (String) o.getObject();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<String> getStringArray(String key) {
		GDataWrapper o = dataHolder.get(key);

		if (o == null)
			return null;
		else
			return (Collection<String>) o.getObject();
	}

	@Override
	public String getType() {
		return type;
	}

	// 以下为setter方法

	@Override
	public void put(String key, GDataWrapper wrappedObject) {
		putObj(key, wrappedObject, null);
	}

	@Override
	public void putBool(String key, boolean value) {
		putObj(key, value, GDataType.BOOL);
	}

	@Override
	public void putBoolArray(String key, Collection<Boolean> value) {
		putObj(key, value, GDataType.BOOL_ARRAY);
	}

	@Override
	public void putByte(String key, byte value) {
		putObj(key, value, GDataType.BYTE);
	}

	@Override
	public void putByteArray(String key, byte[] value) {
		putObj(key, value, GDataType.BYTE_ARRAY);
	}

	@Override
	public void putDouble(String key, double value) {
		putObj(key, value, GDataType.DOUBLE);
	}

	@Override
	public void putDoubleArray(String key, Collection<Double> value) {
		putObj(key, value, GDataType.DOUBLE_ARRAY);
	}

	@Override
	public void putFloat(String key, float value) {
		putObj(key, value, GDataType.FLOAT);
	}

	@Override
	public void putFloatArray(String key, Collection<Float> value) {
		putObj(key, value, GDataType.FLOAT_ARRAY);
	}

	@Override
	public void putInt(String key, int value) {
		putObj(key, value, GDataType.INT);
	}

	@Override
	public void putIntArray(String key, Collection<Integer> value) {
		putObj(key, value, GDataType.INT_ARRAY);
	}

	@Override
	public void putLong(String key, long value) {
		putObj(key, value, GDataType.LONG);
	}

	@Override
	public void putLongArray(String key, Collection<Long> value) {
		putObj(key, value, GDataType.LONG_ARRAY);
	}

	@Override
	public void putObject(String key, IGObject value) {
		putObj(key, value, GDataType.OBJECT);
	}

	public void putObjectArray(String key, Collection<IGObject> value) {
		putObj(key, value, GDataType.OBJECT_ARRAY);
	}

	@Override
	public void putShort(String key, short value) {
		putObj(key, value, GDataType.SHORT);
	}

	@Override
	public void putShortArray(String key, Collection<Short> value) {
		putObj(key, value, GDataType.SHORT_ARRAY);
	}

	// 以下为其他方法

	@Override
	public void putString(String key, String value) {
		putObj(key, value, GDataType.STRING);
	}

	@Override
	public void putStringArray(String key, Collection<String> value) {
		putObj(key, value, GDataType.STRING_ARRAY);
	}

	@Override
	public void remove(String key) {
		dataHolder.remove(key);
	}

	@Override
	public void setType(String type) {
		this.type = type;
	}

	@Override
	public int size() {
		return dataHolder.size();
	}

	@Override
	public byte[] toBinary() {
		try {
			return BinarySerializer.object2binary(this);
		} catch (IOException e) {
			Logger.getLogger(getClass()).error(
					"Cann't serialize GUObject to byte array.");
			return new byte[0];
		}
	}

	@Override
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

	private void putObj(String key, Object value, GDataType typeId) {
		if (key == null)
			throw new IllegalArgumentException(
					"GUObject requires a non-null key for a 'put' operation!");

		if (key.length() > 255)
			throw new IllegalArgumentException(
					"GUObject keys must be less than 255 characters!");

		if (value == null)
			throw new IllegalArgumentException(
					"GUObject requires a non-null value!");

		if (value instanceof GDataWrapper)
			dataHolder.put(key, (GDataWrapper) value);
		else
			dataHolder.put(key, new GDataWrapper(typeId, value));
	}
}
