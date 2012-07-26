package com.soyomaker.model.proxy;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.soyomaker.application.AbstractBean;
import com.soyomaker.data.IGUObject;
import com.soyomaker.model.DataValue;
import com.soyomaker.model.criteria.ICriteria;
import com.soyomaker.model.dataSource.IGUDataSource;
import com.soyomaker.util.IResultSetProcessor;
import com.soyomaker.util.SQLUtil;

public class QueryProxy extends AbstractBean implements IDataProxy {
	private String queryStatement;

	private IGUDataSource dataSource;

	@Override
	public DataValue create(Object key) {
		return null;
	}

	@Override
	public void delete(Object key) {
	}

	@Override
	public DataValue get(Object key) {
		final List<IGUObject> rows = new ArrayList<IGUObject>();

		if (key instanceof QueryParams) {
			QueryParams params = (QueryParams) key;
			IResultSetProcessor resProcessor = new IResultSetProcessor() {
				@Override
				public void proceResultSet(ResultSet rs) {
					addElement(rows, rs);
				}
			};

			if (key instanceof QueryParams) {
				SQLUtil.query(dataSource, queryStatement, params, resProcessor);
			}
		}

		DataValue result = new DataValue();
		result.putObjectArray("results", rows);
		result.setKey(key);
		return result;
	}

	@Override
	public void inistialize() {
	}

	@Override
	public void initialize() {
		dataSource = (IGUDataSource) this.getBeanFactory().getBean(this.getParam("dataSource"));
		queryStatement = this.getParam("queryStatement");
	}

	@Override
	public List<DataValue> loadAll() {
		return null;
	}

	@Override
	public List<DataValue> query(ICriteria c) {
		return null;
	}

	@Override
	public void write(DataValue data) {
	}

	private void addElement(List<IGUObject> elements, ResultSet resultSet) {
		DataValue element = new DataValue();
		element.loadFromResultSet(resultSet);
		elements.add(element);
	}

}
