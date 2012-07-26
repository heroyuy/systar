package com.soyomaker.model.dataset;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.soyomaker.application.AbstractBean;
import com.soyomaker.model.DataValue;
import com.soyomaker.model.criteria.ICriteria;
import com.soyomaker.model.proxy.QueryProxy;

/**
 * 只读数据集合。可以用于查询结果缓存或者数据字典类数据存储。
 * 
 * @author zhangjun
 * 
 */
public class ReadonlyDataset extends AbstractBean implements IDataSet {
	private QueryProxy proxy = null;
	private int expireTime = Integer.MAX_VALUE; // 默认五分钟

	private Map<Object, DataValue> queryCache = new HashMap<Object, DataValue>();
	private Map<Object, Long> queryTimes = new HashMap<Object, Long>();

	@Override
	public void flush() {
	}

	@Override
	public DataValue get(Object key) {
		DataValue result = queryCache.get(key);
		if (result != null) {
			Long queryTime = queryTimes.get(key);
			if (queryTime + expireTime > System.currentTimeMillis()) {
				return result;
			}
		}

		result = proxy.get(key);
		if (result != null) {
			result.lock();
			queryCache.put(key, result);
			queryTimes.put(key, System.currentTimeMillis());
		}
		return result;
	}

	@Override
	public void initialize() {
		expireTime = this.getIntParam("expireTime", expireTime);
		proxy = (QueryProxy) this.getBeanFactory().getBean("dataProxy");
	}

	/**
	 * 不能手工向其中添加数据
	 */
	public void put(Object key, DataValue element) {
	}

	/**
	 * 不能查询
	 */
	public List<DataValue> query(ICriteria criteria) {
		return null;
	}

	/**
	 * 不能查询
	 */
	public List<DataValue> queryAll() {
		return null;
	}

	/**
	 * 不能手工删除
	 */
	public void remove(Object key) {
		// 不能手工删除数据
	}

}
