package com.gudigital.model.proxy;

import java.util.List;

import com.gudigital.model.DataValue;
import com.gudigital.model.criteria.ICriteria;

public interface IDataProxy {
	public DataValue create(Object key);

	public void delete(Object key);

	public DataValue get(Object key);

	public void inistialize();

	public List<DataValue> loadAll();

	public List<DataValue> query(ICriteria c);

	public void write(DataValue data);
}
