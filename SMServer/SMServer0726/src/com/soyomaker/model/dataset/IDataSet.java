package com.soyomaker.model.dataset;

import java.util.List;

import com.soyomaker.model.DataValue;
import com.soyomaker.model.criteria.ICriteria;

public interface IDataSet {
	/**
	 * 同步数据
	 */
	public void flush();

	/**
	 * 获得数据
	 * 
	 * @param key
	 * @return
	 */
	public DataValue get(Object key);

	/**
	 * 初始化数据
	 */
	public void initialize();

	/**
	 * 添加一个新的数据
	 * 
	 * @param key
	 * @param element
	 */
	public void put(Object key, DataValue element);

	/**
	 * 查询数据
	 * 
	 * @param criteria
	 * @return
	 */
	public List<DataValue> query(ICriteria criteria);

	/**
	 * 查询全部数据
	 */
	public List<DataValue> queryAll();

	/**
	 * 从数据集中删除数据，是永久删除
	 * 
	 * @param key
	 *            从数据集中删除数据
	 * @param key
	 */
	public void remove(Object key);
}
