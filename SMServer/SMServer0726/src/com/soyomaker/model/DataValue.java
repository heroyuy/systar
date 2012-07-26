package com.soyomaker.model;

import java.sql.ResultSet;
import java.util.Collection;
import java.util.Set;

import com.soyomaker.data.GUDataWrapper;
import com.soyomaker.data.GUObject;
import com.soyomaker.data.IGUObject;

public class DataValue implements IGUObject {
	/**
	 * 主键，不要随意设置主键。
	 */
	private Object key;

	/**
	 * 数据内容
	 */
	private IGUObject dataHolder;

	/**
	 * 版本号，每一次修改数据项都会导致版本号增加
	 */
	private int version = 0;

	/**
	 * 最后修改时间，被put方法修改
	 */
	private long lastModifyTime = System.currentTimeMillis();
	/**
	 * 最后访问时间，被get/put方法修改
	 */
	private long lastAccessTime = System.currentTimeMillis();

	/**
	 * 锁定数据的标志，被锁定的数据是不可以修改的。
	 */
	private boolean locked;

	public DataValue() {
		dataHolder = new GUObject();
		version = 1;
		lastModifyTime = System.currentTimeMillis();
	}

	@Override
	public void clear() {
		if (!locked) {
			dataHolder.clear();
			onPutData();
		}
	}

	public GUDataWrapper get(String key) {
		onGetData();
		return dataHolder.get(key);
	}

	public Boolean getBool(String key) {
		onGetData();
		return dataHolder.getBool(key);
	}

	public Collection<Boolean> getBoolArray(String key) {
		onGetData();
		return dataHolder.getBoolArray(key);
	}

	public Byte getByte(String key) {
		onGetData();
		return dataHolder.getByte(key);
	}

	public byte[] getByteArray(String key) {
		onGetData();
		return dataHolder.getByteArray(key);
	}

	public Double getDouble(String key) {
		onGetData();
		return dataHolder.getDouble(key);
	}

	public Collection<Double> getDoubleArray(String key) {
		onGetData();
		return dataHolder.getDoubleArray(key);
	}

	public Float getFloat(String key) {
		onGetData();
		return dataHolder.getFloat(key);
	}

	public Collection<Float> getFloatArray(String key) {
		onGetData();
		return dataHolder.getFloatArray(key);
	}

	public Integer getInt(String key) {
		onGetData();
		return dataHolder.getInt(key);
	}

	public Collection<Integer> getIntArray(String key) {
		onGetData();
		return dataHolder.getIntArray(key);
	}

	public Object getKey() {
		return key;
	}

	/**
	 * 以下部分是从IGUObject的代理方法
	 */
	public Set<String> getKeys() {
		return dataHolder.getKeys();
	}

	public long getLastAccessTime() {
		return lastAccessTime;
	}

	public long getLastModifyTime() {
		return this.lastModifyTime;
	}

	public Long getLong(String key) {
		onGetData();
		return dataHolder.getLong(key);
	}

	public Collection<Long> getLongArray(String key) {
		onGetData();
		return dataHolder.getLongArray(key);
	}

	@Override
	public IGUObject getObject(String key) {
		onGetData();
		return dataHolder.getObject(key);
	}

	@Override
	public Collection<IGUObject> getObjectArray(String key) {
		return dataHolder.getObjectArray(key);
	}

	public Short getShort(String key) {
		onGetData();
		return dataHolder.getShort(key);
	}

	public Collection<Short> getShortArray(String key) {
		onGetData();
		return dataHolder.getShortArray(key);
	}

	public String getString(String key) {
		onGetData();
		return dataHolder.getString(key);
	}

	@Override
	public Collection<String> getStringArray(String key) {
		onGetData();
		return dataHolder.getStringArray(key);
	}

	@Override
	public String getType() {
		onGetData();
		return dataHolder.getType();
	}

	public IGUObject getValue() {
		return dataHolder;
	}

	public int getVersion() {
		return this.version;
	}

	public boolean isChanged() {
		return (version != 0);
	}

	public void loadFromResultSet(ResultSet resultSet) {

	}

	public void lock() {
		locked = true;
	}

	public void put(String key, GUDataWrapper wrappedObject) {
		if (!locked) {
			dataHolder.put(key, wrappedObject);
			onPutData();
		}
	}

	public void putBool(String key, boolean v) {
		if (!locked) {
			dataHolder.putBool(key, v);
			onPutData();
		}
	}

	public void putBoolArray(String key, Collection<Boolean> v) {
		if (!locked) {
			dataHolder.putBoolArray(key, v);
			onPutData();
		}
	}

	public void putByte(String key, byte v) {
		if (!locked) {
			dataHolder.putByte(key, v);
			onPutData();
		}
	}

	public void putByteArray(String key, byte[] v) {
		if (!locked) {
			dataHolder.putByteArray(key, v);
			onPutData();
		}
	}

	public void putDouble(String key, double v) {
		if (!locked) {
			dataHolder.putDouble(key, v);
			onPutData();
		}
	}

	public void putDoubleArray(String key, Collection<Double> v) {
		if (!locked) {
			dataHolder.putDoubleArray(key, v);
			onPutData();
		}
	}

	public void putFloat(String key, float v) {
		if (!locked) {
			dataHolder.putFloat(key, v);
			onPutData();
		}
	}

	public void putFloatArray(String key, Collection<Float> v) {
		if (!locked) {
			dataHolder.putFloatArray(key, v);
			onPutData();
		}
	}

	public void putInt(String key, int v) {
		if (!locked) {
			dataHolder.putInt(key, v);
			onPutData();
		}
	}

	public void putIntArray(String key, Collection<Integer> v) {
		if (!locked) {
			dataHolder.putIntArray(key, v);
			onPutData();
		}
	}

	public void putLong(String key, long v) {
		if (!locked) {
			dataHolder.putLong(key, v);
			onPutData();
		}
	}

	public void putLongArray(String key, Collection<Long> v) {
		if (!locked) {
			dataHolder.putLongArray(key, v);
			onPutData();
		}
	}

	public void putObject(String key, IGUObject v) {
		if (!locked) {
			dataHolder.putObject(key, v);
			onPutData();
		}
	}

	@Override
	public void putObjectArray(String key, Collection<IGUObject> value) {
		dataHolder.putObjectArray(key, value);
	}

	/**
	 * 以上为集成IGUObject的方法
	 */

	public void putShort(String key, short v) {
		if (!locked) {
			dataHolder.putShort(key, v);
			onPutData();
		}
	}

	public void putShortArray(String key, Collection<Short> v) {
		if (!locked) {
			dataHolder.putShortArray(key, v);
			onPutData();
		}
	}

	public void putString(String key, String v) {
		if (!locked) {
			dataHolder.putString(key, v);
			onPutData();
		}
	}

	public void putStringArray(String key, Collection<String> v) {
		if (!locked) {
			dataHolder.putStringArray(key, v);
			onPutData();
		}
	}

	@Override
	public void putValues(IGUObject obj) {
		if (!locked) {
			dataHolder.putValues(obj);
			onPutData();
		}
	}

	@Override
	public void remove(String key) {
		if (!locked) {
			dataHolder.remove(key);
			onPutData();
		}
	}

	public void resetVersion() {
		version = 0;
		lastModifyTime = System.currentTimeMillis();
	}

	public void setKey(Object key) {
		this.key = key;
	}

	@Override
	public void setType(String type) {
		dataHolder.setType(type);
	}

	public void setValue(IGUObject value) {
		onPutData();
		this.dataHolder = value;
	}

	public int size() {
		return dataHolder.size();
	}

	public byte[] toBinary() {
		return dataHolder.toBinary();
	}

	public String toJson() {
		return dataHolder.toJson();
	}

	public void unlock() {
		locked = false;
	}

	private void onGetData() {
		this.lastAccessTime = System.currentTimeMillis();
	}

	/**
	 * 对象修改后调用
	 * 
	 * @param key
	 */
	private void onPutData() {
		this.lastAccessTime = System.currentTimeMillis();
		this.lastModifyTime = System.currentTimeMillis();
		version++;
	}
}
