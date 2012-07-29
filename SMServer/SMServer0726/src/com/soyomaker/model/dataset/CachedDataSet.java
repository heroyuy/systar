package com.soyomaker.model.dataset;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.soyomaker.common.AbstractBean;
import com.soyomaker.model.DataValue;
import com.soyomaker.model.criteria.ICriteria;
import com.soyomaker.model.proxy.IDataProxy;

public class CachedDataSet extends AbstractBean implements IDataSet {
	private IDataProxy dataProxy;
	private int expireTime = Integer.MAX_VALUE;

	private Map<Object, DataValue> dataHolder = new HashMap<Object, DataValue>();

	@Override
	public void flush() {
		for (Entry<Object, DataValue> e : dataHolder.entrySet()) {
			DataValue value = e.getValue();
			if (value.isChanged()) {
				dataProxy.update(value);
				value.resetVersion();
			}

			if (System.currentTimeMillis() - value.getLastAccessTime() > expireTime) {
				dataHolder.remove(e.getKey());
			}
		}
	}

	@Override
	public DataValue get(Object key) {
		DataValue e = dataHolder.get(key);
		if (e == null) {
			e = dataProxy.get(key);
			if (e != null) {
				dataHolder.put(e.getKey(), e);
			}
		}
		return e;
	}

	@Override
	public void initialize() {
	}

	@Override
	public void put(Object key, DataValue element) {
		dataProxy.update(element);
		dataHolder.put(element.getKey(), element);
	}

	@Override
	public List<DataValue> query(ICriteria criteria) {
		List<DataValue> r = new ArrayList<DataValue>();
		for (DataValue e : dataHolder.values()) {
			if (criteria.execute(e)) {
				r.add(e);
			}
		}
		return r;
	}

	@Override
	public void remove(Object key) {
		dataProxy.remove(key);
		dataHolder.remove(key);
	}

}
