package com.gudigital.core;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

public class GUObject implements IGUObject {
	public static IGUObject createFromBytes(byte[] bytes) {
		try {
			return BinarySerializer.binary2object(bytes);
		} catch (IOException e) {
			Logger.getLogger(GUObject.class).error("Cann't create GUObject from byte array.");
			return null;
		}
	}

	public static IGUObject createFromJson(String jsonStr) {
		return JsonSerializer.json2object(jsonStr);
	}

	private String type;

	private Map<String, GUDataWrapper> dataHolder = new HashMap<String, GUDataWrapper>();

	/*
	 * @see com.gudigital.core.IGUObject#clear()
	 */
	@Override
	public void clear() {
		dataHolder.clear();
	}

	// 以下为getter方法
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gudigital.core.IGUObject#get(java.lang.String)
	 */
	@Override
	public GUDataWrapper get(String key) {
		return dataHolder.get(key);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gudigital.core.IGUObject#getBool(java.lang.String)
	 */
	@Override
	public Boolean getBool(String key) {
		GUDataWrapper o = dataHolder.get(key);

		if (o == null)
			return null;
		else
			return (Boolean) o.getObject();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gudigital.core.IGUObject#getBoolArray(java.lang.String)
	 */
	@Override
	public Collection<Boolean> getBoolArray(String key) {
		GUDataWrapper o = dataHolder.get(key);

		if (o == null)
			return null;
		else
			return (Collection<Boolean>) o.getObject();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gudigital.core.IGUObject#getByte(java.lang.String)
	 */
	@Override
	public Byte getByte(String key) {
		GUDataWrapper o = dataHolder.get(key);

		if (o == null)
			return null;
		else
			return (Byte) o.getObject();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gudigital.core.IGUObject#getByteArray(java.lang.String)
	 */
	@Override
	public byte[] getByteArray(String key) {
		GUDataWrapper o = dataHolder.get(key);

		if (o == null)
			return null;
		else
			return (byte[]) o.getObject();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gudigital.core.IGUObject#getDouble(java.lang.String)
	 */
	@Override
	public Double getDouble(String key) {
		GUDataWrapper o = dataHolder.get(key);

		if (o == null)
			return null;
		else
			return (Double) o.getObject();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gudigital.core.IGUObject#getDoubleArray(java.lang.String)
	 */
	@Override
	public Collection<Double> getDoubleArray(String key) {
		GUDataWrapper o = dataHolder.get(key);

		if (o == null)
			return null;
		else
			return (Collection<Double>) o.getObject();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gudigital.core.IGUObject#getFloat(java.lang.String)
	 */
	@Override
	public Float getFloat(String key) {
		GUDataWrapper o = dataHolder.get(key);

		if (o == null)
			return null;
		else
			return (Float) o.getObject();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gudigital.core.IGUObject#getFloatArray(java.lang.String)
	 */
	@Override
	public Collection<Float> getFloatArray(String key) {
		GUDataWrapper o = dataHolder.get(key);

		if (o == null)
			return null;
		else
			return (Collection<Float>) o.getObject();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gudigital.core.IGUObject#getInt(java.lang.String)
	 */
	@Override
	public Integer getInt(String key) {
		GUDataWrapper o = dataHolder.get(key);

		if (o == null)
			return null;
		else
			return (Integer) o.getObject();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gudigital.core.IGUObject#getIntArray(java.lang.String)
	 */
	@Override
	public Collection<Integer> getIntArray(String key) {
		GUDataWrapper o = dataHolder.get(key);

		if (o == null)
			return null;
		else
			return (Collection<Integer>) o.getObject();
	}

	/*
	 * @see com.gudigital.core.IGUObject#getKeys()
	 */
	@Override
	public Set<String> getKeys() {
		return dataHolder.keySet();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gudigital.core.IGUObject#getLong(java.lang.String)
	 */
	@Override
	public Long getLong(String key) {
		GUDataWrapper o = dataHolder.get(key);

		if (o == null)
			return null;
		else
			return (Long) o.getObject();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gudigital.core.IGUObject#getLongArray(java.lang.String)
	 */
	@Override
	public Collection<Long> getLongArray(String key) {
		GUDataWrapper o = dataHolder.get(key);

		if (o == null)
			return null;
		else
			return (Collection<Long>) o.getObject();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gudigital.core.IGUObject#getObject(java.lang.String)
	 */
	@Override
	public IGUObject getObject(String key) {
		GUDataWrapper o = dataHolder.get(key);

		if (o == null)
			return null;
		else
			return (IGUObject) o.getObject();
	}

	@Override
	public Collection<IGUObject> getObjectArray(String key) {
		GUDataWrapper o = dataHolder.get(key);

		if (o == null)
			return null;
		else
			return (Collection<IGUObject>) o.getObject();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gudigital.core.IGUObject#getShort(java.lang.String)
	 */
	@Override
	public Short getShort(String key) {
		GUDataWrapper o = dataHolder.get(key);

		if (o == null)
			return null;
		else
			return (Short) o.getObject();
	}

	/*
	 * @see com.gudigital.core.IGUObject#getShortArray(java.lang.String)
	 */
	@Override
	public Collection<Short> getShortArray(String key) {
		GUDataWrapper o = dataHolder.get(key);

		if (o == null)
			return null;
		else
			return (Collection<Short>) o.getObject();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gudigital.core.IGUObject#getString(java.lang.String)
	 */
	@Override
	public String getString(String key) {
		GUDataWrapper o = dataHolder.get(key);

		if (o == null)
			return null;
		else
			return (String) o.getObject();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gudigital.core.IGUObject#getStringArray(java.lang.String)
	 */
	@Override
	public Collection<String> getStringArray(String key) {
		GUDataWrapper o = dataHolder.get(key);

		if (o == null)
			return null;
		else
			return (Collection<String>) o.getObject();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gudigital.core.IGUObject#getType()
	 */
	@Override
	public String getType() {
		return type;
	}

	// 以下为setter方法
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gudigital.core.IGUObject#put(java.lang.String,
	 * com.gudigital.core.GUDataWrapper)
	 */
	@Override
	public void put(String key, GUDataWrapper wrappedObject) {
		putObj(key, wrappedObject, null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gudigital.core.IGUObject#putBool(java.lang.String, boolean)
	 */
	@Override
	public void putBool(String key, boolean value) {
		putObj(key, value, GUDataType.BOOL);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gudigital.core.IGUObject#putBoolArray(java.lang.String,
	 * java.util.Collection)
	 */
	@Override
	public void putBoolArray(String key, Collection<Boolean> value) {
		putObj(key, value, GUDataType.BOOL_ARRAY);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gudigital.core.IGUObject#putByte(java.lang.String, byte)
	 */
	@Override
	public void putByte(String key, byte value) {
		putObj(key, value, GUDataType.BYTE);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gudigital.core.IGUObject#putByteArray(java.lang.String, byte[])
	 */
	@Override
	public void putByteArray(String key, byte[] value) {
		putObj(key, value, GUDataType.BYTE_ARRAY);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gudigital.core.IGUObject#putDouble(java.lang.String, double)
	 */
	@Override
	public void putDouble(String key, double value) {
		putObj(key, value, GUDataType.DOUBLE);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gudigital.core.IGUObject#putDoubleArray(java.lang.String,
	 * java.util.Collection)
	 */
	@Override
	public void putDoubleArray(String key, Collection<Double> value) {
		putObj(key, value, GUDataType.DOUBLE_ARRAY);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gudigital.core.IGUObject#putFloat(java.lang.String, float)
	 */
	@Override
	public void putFloat(String key, float value) {
		putObj(key, value, GUDataType.FLOAT);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gudigital.core.IGUObject#putFloatArray(java.lang.String,
	 * java.util.Collection)
	 */
	@Override
	public void putFloatArray(String key, Collection<Float> value) {
		putObj(key, value, GUDataType.FLOAT_ARRAY);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gudigital.core.IGUObject#putInt(java.lang.String, int)
	 */
	@Override
	public void putInt(String key, int value) {
		putObj(key, value, GUDataType.INT);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gudigital.core.IGUObject#putIntArray(java.lang.String,
	 * java.util.Collection)
	 */
	@Override
	public void putIntArray(String key, Collection<Integer> value) {
		putObj(key, value, GUDataType.INT_ARRAY);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gudigital.core.IGUObject#putLong(java.lang.String, long)
	 */
	@Override
	public void putLong(String key, long value) {
		putObj(key, value, GUDataType.LONG);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gudigital.core.IGUObject#putLongArray(java.lang.String,
	 * java.util.Collection)
	 */
	@Override
	public void putLongArray(String key, Collection<Long> value) {
		putObj(key, value, GUDataType.LONG_ARRAY);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gudigital.core.IGUObject#putObject(java.lang.String,
	 * com.gudigital.core.IGUObject)
	 */
	@Override
	public void putObject(String key, IGUObject value) {
		putObj(key, value, GUDataType.OBJECT);
	}

	public void putObjectArray(String key, Collection<IGUObject> value) {
		putObj(key, value, GUDataType.OBJECT_ARRAY);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gudigital.core.IGUObject#putShort(java.lang.String, short)
	 */
	@Override
	public void putShort(String key, short value) {
		putObj(key, value, GUDataType.SHORT);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gudigital.core.IGUObject#putShortArray(java.lang.String,
	 * java.util.Collection)
	 */
	@Override
	public void putShortArray(String key, Collection<Short> value) {
		putObj(key, value, GUDataType.SHORT_ARRAY);
	}

	// 以下为其他方法

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gudigital.core.IGUObject#putString(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public void putString(String key, String value) {
		putObj(key, value, GUDataType.STRING);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gudigital.core.IGUObject#putStringArray(java.lang.String,
	 * java.util.Collection)
	 */
	@Override
	public void putStringArray(String key, Collection<String> value) {
		putObj(key, value, GUDataType.STRING_ARRAY);
	}

	@Override
	public void putValues(IGUObject obj) {
		for (String key : obj.getKeys()) {
			GUDataWrapper wrapper = obj.get(key);
			this.put(key, wrapper);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gudigital.core.IGUObject#remove(java.lang.String)
	 */
	@Override
	public void remove(String key) {
		dataHolder.remove(key);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gudigital.core.IGUObject#setType(java.lang.String)
	 */
	@Override
	public void setType(String type) {
		this.type = type;
	}

	/*
	 * @see com.gudigital.core.IGUObject#size()
	 */
	@Override
	public int size() {
		return dataHolder.size();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gudigital.core.IGUObject#toBinary()
	 */
	@Override
	public byte[] toBinary() {
		try {
			return BinarySerializer.object2binary(this);
		} catch (IOException e) {
			Logger.getLogger(getClass()).error("Cann't serialize GUObject to byte array.");
			return new byte[0];
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gudigital.core.IGUObject#toJson()
	 */
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

	private void putObj(String key, Object value, GUDataType typeId) {
		if (key == null)
			throw new IllegalArgumentException("GUObject requires a non-null key for a 'put' operation!");

		if (key.length() > 255)
			throw new IllegalArgumentException("GUObject keys must be less than 255 characters!");

		if (value == null)
			throw new IllegalArgumentException("GUObject requires a non-null value!");

		if (value instanceof GUDataWrapper)
			dataHolder.put(key, (GUDataWrapper) value);
		else
			dataHolder.put(key, new GUDataWrapper(typeId, value));
	}
}
