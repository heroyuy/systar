package com.soyostar.xml;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * 有序Map
 * 
 * @author wp_g4
 * @since 20111117
 * @param <K>
 *            键类型
 * @param <V>
 *            值类型
 */
public class OrderedMap<K, V> {

	/**键表*/
	private Collection<K> keyList = new LinkedList<K>();

	/**key-value映射*/
	private Map<K, V> dataMap = new HashMap<K, V>();

	/**
	 * 添加映射
	 * 
	 * @param key
	 *            键
	 * @param value
	 *            值
	 * @return 添加成功的值
	 */
	public V put(K key, V value) {
		if (!keyList.contains(key)) {
			keyList.add(key);
		}
		return dataMap.put(key, value);
	}

	/**
	 * 获取指定键对应的值
	 * 
	 * @param key
	 *            键
	 * @return 值
	 */
	public V get(K key) {
		return dataMap.get(key);
	}

	/**
	 * 删除指定的key-value映射
	 * 
	 * @param key
	 *            键
	 * @return 删除的值
	 */
	public V remove(K key) {
		keyList.remove(key);
		return dataMap.remove(key);
	}

	/**
	 * 清空Map
	 */
	public void clear() {
		keyList.clear();
		dataMap.clear();
	}

	/**
	 * 检查Map是否为空
	 * 
	 * @return 若Map为空返回true，否则返回false
	 */
	public boolean isEmpty() {
		return dataMap.isEmpty();
	}

	/**
	 * 检查Map是否包含指定的键
	 * 
	 * @param key
	 *            键
	 * @return 若Map包含指定的键则返回true,否则返回false
	 */
	public boolean containsKey(K key) {
		return dataMap.containsKey(key);
	}

	/**
	 * 检查Map是否包含指定的值
	 * 
	 * @param value
	 *            值
	 * @return 若Map包含指定的值则返回true,否则返回false
	 */
	public boolean containsValue(V value) {
		return dataMap.containsValue(value);
	}

	/**
	 * 获取Map中的键表
	 * 
	 * @return 键表
	 */
	public Collection<K> getAllKeys() {
		return keyList;
	}

	/**
	 * 获取Map中的值表
	 * 
	 * @return 值表
	 */
	public Collection<V> values() {
		Collection<V> results = new LinkedList<V>();
		for (K key : keyList) {
			results.add(dataMap.get(key));
		}
		return results;
	}

	/**
	 * 获取Map中映射的个数
	 * 
	 * @return Map中映射的个数
	 */
	public int size() {
		return dataMap.size();
	}
}
