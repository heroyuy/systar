package com.soyomaker.model.dataset;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.soyomaker.application.AbstractBean;
import com.soyomaker.model.DataValue;
import com.soyomaker.model.criteria.ICriteria;
import com.soyomaker.model.typeHelper.TypeHelper;
import com.soyomaker.model.typeHelper.TypeHelperFactory;

public class MemoryDataSet extends AbstractBean implements IDataSet {
	private TypeHelperFactory typeFac;
	private String typeName;
	private boolean autoCreate = false;

	private TypeHelper typeHelper;

	private Map<Object, DataValue> dataHolder = new HashMap<Object, DataValue>();

	@Override
	public void flush() {
		// TODO Auto-generated method stub

	}

	@Override
	public DataValue get(Object key) {
		DataValue r = dataHolder.get(key);
		if (r == null && autoCreate) {
			if (getTypeHelper() != null) {
				r = typeHelper.createDataElement();
				dataHolder.put(key, r);
			}
		}
		return r;
	}

	@Override
	public void initialize() {
		// TODO Auto-generated method stub

	}

	@Override
	public void put(Object key, DataValue element) {
		dataHolder.put(key, element);
	}

	@Override
	public List<DataValue> query(ICriteria criteria) {
		List<DataValue> r = new ArrayList<DataValue>();

		for (DataValue e : dataHolder.values()) {

		}
		return null;
	}

	@Override
	public List<DataValue> queryAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void remove(Object key) {
		dataHolder.remove(key);
	}

	private Object getTypeHelper() {
		if (typeHelper == null) {
			typeHelper = typeFac.getTypeHelper(typeName);
		}
		return typeHelper;
	}

}
