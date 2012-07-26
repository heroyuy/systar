package com.soyomaker.data;

import java.util.Collection;
import java.util.Set;

public interface IGObject {

	// 以下为getter方法
	public abstract GUDataWrapper get(String key);

	public abstract Boolean getBool(String key);

	public abstract Collection<Boolean> getBoolArray(String key);

	public abstract Byte getByte(String key);

	public abstract byte[] getByteArray(String key);

	public abstract Double getDouble(String key);

	public abstract Collection<Double> getDoubleArray(String key);

	public abstract Float getFloat(String key);

	public abstract Collection<Float> getFloatArray(String key);

	public abstract Integer getInt(String key);

	public abstract Collection<Integer> getIntArray(String key);

	public abstract Set<String> getKeys();

	public abstract Long getLong(String key);

	public abstract Collection<Long> getLongArray(String key);

	public abstract IGObject getObject(String key);

	public abstract Collection<IGObject> getObjectArray(String key);

	public abstract Short getShort(String key);

	public abstract Collection<Short> getShortArray(String key);

	public abstract String getString(String key);

	public abstract Collection<String> getStringArray(String key);

	public abstract String getType();

	// 以下为setter方法
	public abstract void put(String key, GUDataWrapper wrappedObject);

	public abstract void putBool(String key, boolean value);

	public abstract void putBoolArray(String key, Collection<Boolean> value);

	public abstract void putByte(String key, byte value);

	public abstract void putByteArray(String key, byte[] value);

	public abstract void putDouble(String key, double value);

	public abstract void putDoubleArray(String key, Collection<Double> value);

	public abstract void putFloat(String key, float value);

	public abstract void putFloatArray(String key, Collection<Float> value);

	public abstract void putInt(String key, int value);

	public abstract void putIntArray(String key, Collection<Integer> value);

	public abstract void putLong(String key, long value);

	public abstract void putLongArray(String key, Collection<Long> value);

	public abstract void putObject(String key, IGObject value);

	public abstract void putObjectArray(String key, Collection<IGObject> value);

	public abstract void putShort(String key, short value);

	public abstract void putShortArray(String key, Collection<Short> value);

	public abstract void putString(String key, String value);

	public abstract void putStringArray(String key, Collection<String> value);

	public abstract void remove(String key);

	public abstract void setType(String type);

	public abstract int size();

	public abstract void clear();

	public abstract byte[] toBinary();

	public abstract String toJson();

}