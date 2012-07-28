package com.soyomaker.model.dataset;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.soyomaker.application.AbstractBean;
import com.soyomaker.model.DataValue;
import com.soyomaker.model.criteria.ICriteria;
import com.soyomaker.model.proxy.IDataProxy;

/**
 * 一次性载入全部数据，并在内存中进行操作的数据集合。
 * 
 * @author zhangjun
 * 
 */
public class FullCachedDataSet extends AbstractBean implements IDataSet {
	private IDataProxy dataProxy;

	private boolean dataLoaded = false;
	private Map<Object, DataValue> dataHolder = new HashMap<Object, DataValue>();

	@Override
	public void flush() {
		for (DataValue e : dataHolder.values()) {
			if (e.isChanged()) {
				dataProxy.update(e);
				e.resetVersion();
			}
		}
	}

	@Override
	public DataValue get(Object key) {
		return dataHolder.get(key);
	}

	@Override
	public void initialize() {
		dataProxy = (IDataProxy) this.getBeanFactory().getBean(
				this.getParam("dataProxy"));
	}

	@Override
	public void put(Object key, DataValue element) {
		dataHolder.put(key, element);
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
