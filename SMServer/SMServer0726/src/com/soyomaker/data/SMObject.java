package com.soyomaker.data;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

public class SMObject implements ISMObject {
	public static ISMObject createFromBytes(byte[] bytes) {
		try {
			return BinarySerializer.binary2object(bytes);
		} catch (IOException e) {
			Logger.getLogger(SMObject.class).error(
					"Cann't create GUObject from byte array.");
			return null;
		}
	}

	public static ISMObject createFromJson(String jsonStr) {
		return JsonSerializer.json2object(jsonStr);
	}

	private String type;

	private Map<String, SMDataWrapper> dataHolder = new HashMap<String, SMDataWrapper>();

	@Override
	public void clear() {
		dataHolder.clear();
	}

	// 以下为getter方法

	@Override
	public SMDataWrapper get(String key) {
		return dataHolder.get(key);
	}

	@Override
	public Boolean getBool(String key) {
		SMDataWrapper o = dataHolder.get(key);

		if (o == null)
			return null;
		else
			return (Boolean) o.getValue();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Boolean> getBoolArray(String key) {
		SMDataWrapper o = dataHolder.get(key);

		if (o == null)
			return null;
		else
			return (Collection<Boolean>) o.getValue();
	}

	@Override
	public Byte getByte(String key) {
		SMDataWrapper o = dataHolder.get(key);

		if (o == null)
			return null;
		else
			return (Byte) o.getValue();
	}

	@Override
	public byte[] getByteArray(String key) {
		SMDataWrapper o = dataHolder.get(key);

		if (o == null)
			return null;
		else
			return (byte[]) o.getValue();
	}

	@Override
	public Double getDouble(String key) {
		SMDataWrapper o = dataHolder.get(key);

		if (o == null)
			return null;
		else
			return (Double) o.getValue();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Double> getDoubleArray(String key) {
		SMDataWrapper o = dataHolder.get(key);

		if (o == null)
			return null;
		else
			return (Collection<Double>) o.getValue();
	}

	@Override
	public Float getFloat(String key) {
		SMDataWrapper o = dataHolder.get(key);

		if (o == null)
			return null;
		else
			return (Float) o.getValue();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Float> getFloatArray(String key) {
		SMDataWrapper o = dataHolder.get(key);

		if (o == null)
			return null;
		else
			return (Collection<Float>) o.getValue();
	}

	@Override
	public Integer getInt(String key) {
		SMDataWrapper o = dataHolder.get(key);

		if (o == null)
			return null;
		else
			return (Integer) o.getValue();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Integer> getIntArray(String key) {
		SMDataWrapper o = dataHolder.get(key);

		if (o == null)
			return null;
		else
			return (Collection<Integer>) o.getValue();
	}

	@Override
	public Set<String> getKeys() {
		return dataHolder.keySet();
	}

	@Override
	public Long getLong(String key) {
		SMDataWrapper o = dataHolder.get(key);

		if (o == null)
			return null;
		else
			return (Long) o.getValue();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Long> getLongArray(String key) {
		SMDataWrapper o = dataHolder.get(key);

		if (o == null)
			return null;
		else
			return (Collection<Long>) o.getValue();
	}

	@Override
	public ISMObject getObject(String key) {
		SMDataWrapper o = dataHolder.get(key);

		if (o == null)
			return null;
		else
			return (ISMObject) o.getValue();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<ISMObject> getObjectArray(String key) {
		SMDataWrapper o = dataHolder.get(key);

		if (o == null)
			return null;
		else
			return (Collection<ISMObject>) o.getValue();
	}

	@Override
	public Short getShort(String key) {
		SMDataWrapper o = dataHolder.get(key);

		if (o == null)
			return null;
		else
			return (Short) o.getValue();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Short> getShortArray(String key) {
		SMDataWrapper o = dataHolder.get(key);

		if (o == null)
			return null;
		else
			return (Collection<Short>) o.getValue();
	}

	@Override
	public String getString(String key) {
		SMDataWrapper o = dataHolder.get(key);

		if (o == null)
			return null;
		else
			return (String) o.getValue();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<String> getStringArray(String key) {
		SMDataWrapper o = dataHolder.get(key);

		if (o == null)
			return null;
		else
			return (Collection<String>) o.getValue();
	}

	@Override
	public String getType() {
		return type;
	}

	// 以下为setter方法

	@Override
	public void put(String key, SMDataWrapper wrappedObject) {
		putObj(key, wrappedObject, null);
	}

	@Override
	public void putBool(String key, boolean value) {
		putObj(key, value, SMDataType.BOOL);
	}

	@Override
	public void putBoolArray(String key, Collection<Boolean> value) {
		putObj(key, value, SMDataType.BOOL_ARRAY);
	}

	@Override
	public void putByte(String key, byte value) {
		putObj(key, value, SMDataType.BYTE);
	}

	@Override
	public void putByteArray(String key, byte[] value) {
		putObj(key, value, SMDataType.BYTE_ARRAY);
	}

	@Override
	public void putDouble(String key, double value) {
		putObj(key, value, SMDataType.DOUBLE);
	}

	@Override
	public void putDoubleArray(String key, Collection<Double> value) {
		putObj(key, value, SMDataType.DOUBLE_ARRAY);
	}

	@Override
	public void putFloat(String key, float value) {
		putObj(key, value, SMDataType.FLOAT);
	}

	@Override
	public void putFloatArray(String key, Collection<Float> value) {
		putObj(key, value, SMDataType.FLOAT_ARRAY);
	}

	@Override
	public void putInt(String key, int value) {
		putObj(key, value, SMDataType.INT);
	}

	@Override
	public void putIntArray(String key, Collection<Integer> value) {
		putObj(key, value, SMDataType.INT_ARRAY);
	}

	@Override
	public void putLong(String key, long value) {
		putObj(key, value, SMDataType.LONG);
	}

	@Override
	public void putLongArray(String key, Collection<Long> value) {
		putObj(key, value, SMDataType.LONG_ARRAY);
	}

	@Override
	public void putObject(String key, ISMObject value) {
		putObj(key, value, SMDataType.OBJECT);
	}

	public void putObjectArray(String key, Collection<ISMObject> value) {
		putObj(key, value, SMDataType.OBJECT_ARRAY);
	}

	@Override
	public void putShort(String key, short value) {
		putObj(key, value, SMDataType.SHORT);
	}

	@Override
	public void putShortArray(String key, Collection<Short> value) {
		putObj(key, value, SMDataType.SHORT_ARRAY);
	}

	// 以下为其他方法

	@Override
	public void putString(String key, String value) {
		putObj(key, value, SMDataType.STRING);
	}

	@Override
	public void putStringArray(String key, Collection<String> value) {
		putObj(key, value, SMDataType.STRING_ARRAY);
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
