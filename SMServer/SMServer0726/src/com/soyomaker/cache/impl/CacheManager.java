package com.soyomaker.cache.impl;

import net.sf.ehcache.Cache;

public class CacheManager {

	/**
	 * 被包装的EhCacheManager
	 */
	private final net.sf.ehcache.CacheManager ehCacheManager = net.sf.ehcache.CacheManager
			.create();

	private CacheManager instance = new CacheManager();

	public CacheManager getInstance() {
		return instance;
	}

	private CacheManager() {

	}

	/**
	 * 根据默认配置添加cache
	 * 
	 * @param name
	 *            cache名称
	 */
	public void addCache(String name) {
		ehCacheManager.addCache(name);
	}

	/**
	 * 根据默认配置添加cache
	 * 
	 * @param c
	 *            cache存储的类型
	 */
	public void addCache(Class<?> c) {
		this.addCache(c.getName());
	}

	public void addCache(String name, int maxElementsInMemory,
			boolean overflowToDisk, boolean eternal, int timeToLiveSeconds,
			int timeToIdleSeconds) {
		ehCacheManager.addCache(new Cache(name, maxElementsInMemory,
				overflowToDisk, eternal, timeToLiveSeconds, timeToIdleSeconds));
	}

	/**
	 * 获取cache
	 * 
	 * @param name
	 *            cache名称
	 * @return cache
	 */
	public Cache getCache(String name) {
		return ehCacheManager.getCache(name);
	}

	public Cache getCache(Class<?> c) {
		return this.getCache(c.getName());
	}

	public void removeCache(String name) {

	}

	public void removeCache(Class<?> c) {
		this.removeCache(c.getName());
	}

}
