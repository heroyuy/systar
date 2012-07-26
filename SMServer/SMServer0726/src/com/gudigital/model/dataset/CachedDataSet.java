package com.gudigital.model.dataset;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.gudigital.application.AbstractBean;
import com.gudigital.model.DataValue;
import com.gudigital.model.criteria.ICriteria;
import com.gudigital.model.proxy.IDataProxy;

public class CachedDataSet extends AbstractBean implements IDataSet {
	private IDataProxy dataProxy;
	private int expireTime = Integer.MAX_VALUE;

	private Map<Object, DataValue> dataHolder = new HashMap<Object, DataValue>();

	@Override
	public void flush() {
		for (Entry<Object, DataValue> e : dataHolder.entrySet()) {
			DataValue value = e.getValue();
			if (value.isChanged()) {
				dataProxy.write(value);
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
		dataProxy = (IDataProxy) this.getBeanFactory().getBean(this.getParam("dataProxy"));
	}

	@Override
	public void put(Object key, DataValue element) {
		dataProxy.write(element);
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
	public List<DataValue> queryAll() {
		return null;
	}

	@Override
	public void remove(Object key) {
		dataProxy.delete(key);
		dataHolder.remove(key);
	}

}
