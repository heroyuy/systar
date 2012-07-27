package com.soyomaker.model.proxy;

import java.util.List;

import com.soyomaker.model.DataValue;
import com.soyomaker.model.criteria.ICriteria;

public interface IDataProxy {
	public DataValue create(Object key);

	public void delete(Object key);

	public DataValue get(Object key);

	public void inistialize();

	public List<DataValue> query(ICriteria c);

	public void write(DataValue data);
}
