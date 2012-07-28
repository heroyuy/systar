package com.soyomaker.model.dataset;

import java.util.List;

import com.soyomaker.application.AbstractBean;
import com.soyomaker.model.DataValue;
import com.soyomaker.model.criteria.ICriteria;
import com.soyomaker.model.proxy.IDataProxy;

public class DirectDataset extends AbstractBean implements IDataSet {
	private IDataProxy proxy;

	@Override
	public void flush() {
	}

	@Override
	public DataValue get(Object key) {
		return proxy.get(key);
	}

	@Override
	public void initialize() {
	}

	@Override
	public void put(Object key, DataValue element) {
		proxy.update(element);
	}

	@Override
	public List<DataValue> query(ICriteria criteria) {
		return proxy.query(criteria);
	}

	@Override
	public void remove(Object key) {
		proxy.remove(key);
	}
}
