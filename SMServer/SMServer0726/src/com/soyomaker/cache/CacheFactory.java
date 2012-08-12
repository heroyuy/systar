package com.soyomaker.cache;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;

public class CacheFactory {

	private final static int MAX_ELEMENTS_IN_MEMORY = 10000;

	private final static boolean OVERFLOW_TO_DISK = false;

	private final static boolean ETERNAL = false;

	private final static int TIME_TO_LIVE_SECONDS = 1200;

	private final static int TIME_TO_IDLE_SECONDS = 1200;

	private static CacheFactory instance = new CacheFactory();

	public static CacheFactory getInstance() {
		return instance;
	}

	private CacheManager ehCacheManager = CacheManager.create();

	private CacheFactory() {

	}

	public Cache newCache(String name, boolean eternal) {
		return new Cache(name, MAX_ELEMENTS_IN_MEMORY, OVERFLOW_TO_DISK, eternal, TIME_TO_LIVE_SECONDS, TIME_TO_IDLE_SECONDS);
	}

	public void addCache(Cache cache) {
		ehCacheManager.addCache(cache);
	}

	public Cache getCache(String name) {
		return ehCacheManager.getCache(name);
	}

	public void removeCache(String name) {
		ehCacheManager.removeCache(name);
	}

}
