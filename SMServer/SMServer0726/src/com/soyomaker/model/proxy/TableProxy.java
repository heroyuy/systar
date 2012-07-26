package com.soyomaker.model.proxy;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.soyomaker.application.AbstractBean;
import com.soyomaker.data.GDataType;
import com.soyomaker.data.GDataWrapper;
import com.soyomaker.data.GObject;
import com.soyomaker.model.DataValue;
import com.soyomaker.model.criteria.ICriteria;
import com.soyomaker.model.dataSource.GUDataSource;
import com.soyomaker.model.typeHelper.PropertyHelper;
import com.soyomaker.model.typeHelper.TypeHelper;
import com.soyomaker.model.typeHelper.TypeHelperFactory;
import com.soyomaker.util.IResultSetProcessor;
import com.soyomaker.util.Log4JUtil;
import com.soyomaker.util.SQLUtil;

public class TableProxy extends AbstractBean implements IDataProxy {
	private GUDataSource dataSource;
	private String tableName;
	private TypeHelper typeHelper;

	@Override
	public DataValue create(Object key) {
		String insertStatement = "insert into" + tableName + "(" + typeHelper.getKeyColumn() + ")" + " values (?)";

		DataValue e = new DataValue();
		e.setKey(key);
		e.setValue(new GObject());

		QueryParams params = (QueryParams) key;
		int i = SQLUtil.execute(dataSource, insertStatement, params);
		if (i == 1) {
			return e;
		}
		return null;
	}

	@Override
	public void delete(Object key) {
		if (key instanceof QueryParams) {
			QueryParams params = (QueryParams) key;
			String deleteStatement = "delete from " + tableName + " where " + typeHelper.getKeyColumn() + "= ?";
			SQLUtil.execute(dataSource, deleteStatement, params);
		}
	}

	/**
	 * 获得key对应的一条数据
	 */
	@Override
	public DataValue get(Object key) {
		final DataValue e = typeHelper.createDataElement();

		if (key instanceof QueryParams) {
			IResultSetProcessor proc = new IResultSetProcessor() {
				@Override
				public void proceResultSet(ResultSet rs) {
					try {
						ResultSetMetaData meta = rs.getMetaData();
						if (rs.next()) {
							for (int i = 1; i <= meta.getColumnCount(); i++) {
								String colName = meta.getColumnName(i);

								if (colName.equalsIgnoreCase(typeHelper.getKeyColumn())) {
									GDataType type = typeHelper.getKeyType();
									e.setKey(type.getValueFromResultSet(rs, i));
								} else {
									PropertyHelper ph = typeHelper.getPropertyHelperByColumnName(colName);
									if (ph != null) {
										GDataType type = ph.getType();
										e.put(ph.getFieldName(), new GDataWrapper(type, type.getValueFromResultSet(rs, i)));
									}
								}
							}
						}
					} catch (SQLException e) {
						Log4JUtil.error(this, e);
					}
				}
			};

			QueryParams params = (QueryParams) key;
			if (key instanceof QueryParams) {
				String selectStatement = "select * from " + tableName + " where " + typeHelper.getKeyColumn() + "= ?";
				SQLUtil.query(dataSource, selectStatement, params, proc);
			}
		}
		return e;
	}

	@Override
	public void inistialize() {
		// TODO Auto-generated method stub

	}

	/**
	 * TableProxy Bean初始化
	 */
	@Override
	public void initialize() {
		dataSource = (GUDataSource) this.getBeanFactory().getBean(this.getParam("dataSource"));
		tableName = this.getParam("tableName");

		TypeHelperFactory helpFac = (TypeHelperFactory) this.getBeanFactory().getBean(this.getParam("typeFactory"));
		typeHelper = helpFac.getTypeHelper(this.getParam("typeName"));
	}

	@Override
	public List<DataValue> loadAll() {
		final List<DataValue> dataElements = new ArrayList<DataValue>();
		IResultSetProcessor proc = new IResultSetProcessor() {
			@Override
			public void proceResultSet(ResultSet rs) {
				try {
					ResultSetMetaData meta = rs.getMetaData();
					while (rs.next()) {
						DataValue e = typeHelper.createDataElement();
						for (int i = 1; i <= meta.getColumnCount(); i++) {
							String colName = meta.getColumnName(i);
							if (colName.equalsIgnoreCase(typeHelper.getKeyColumn())) {
								GDataType type = typeHelper.getKeyType();
								e.setKey(type.getValueFromResultSet(rs, i));
							} else {
								PropertyHelper ph = typeHelper.getPropertyHelperByColumnName(colName);
								if (ph != null) {
									GDataType type = ph.getType();
									e.put(ph.getFieldName(), new GDataWrapper(type, type.getValueFromResultSet(rs, i)));
								}
							}
						}
						dataElements.add(e);
					}
				} catch (SQLException e) {
					Log4JUtil.error(this, e);
				}
			}
		};

		String selectStatement = "select * from " + tableName;
		SQLUtil.query(dataSource, selectStatement, null, proc);

		return dataElements;
	}

	@Override
	public List<DataValue> query(ICriteria c) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void write(DataValue data) {
		String updateStatement = SQLUtil.getUpdateSqlFromType(tableName, typeHelper, data);
		SQLUtil.execute(dataSource, updateStatement, null);
	}

}
