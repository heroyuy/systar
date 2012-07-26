package com.soyomaker.support;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import net.sf.ehcache.config.CacheConfiguration;
import net.sf.ehcache.event.CacheEventListener;
import net.sf.ehcache.loader.CacheLoader;
import net.sf.ehcache.writer.CacheWriter;

public class CacheService {
	private CacheManager cacheManager;
	private String configFile;

	public void createCache(CacheConfiguration cacheConfig) {
		Cache cache = new Cache(cacheConfig);
		cacheManager.addCache(cache);
	}

	public Object get(String name, Object key) {
		Cache cache = cacheManager.getCache(name);
		if (cache != null) {
			Element e = cache.get(key);
			if (e != null) {
				return e.getValue();
			}
		}
		return null;
	}

	public Cache getCache(String name) {
		return cacheManager.getCache(name);
	}

	public boolean localStart() {
		return true;
	}

	public boolean localStop() {
		cacheManager.shutdown();
		return true;
	}

	public void put(String name, Object key, Object value) {
		Element e = new Element(key, value);
		Cache cache = cacheManager.getCache(name);
		if (cache != null) {
			cache.put(e);
		}
	}

	public void registerCacheEventListener(String cacheName, CacheEventListener listener) {
		Cache cache = getCache(cacheName);
		cache.getCacheEventNotificationService().registerListener(listener);
	}

	public void registerCacheLoader(String cacheName, CacheLoader loader) {
		Cache cache = getCache(cacheName);
		cache.registerCacheLoader(loader);
	}

	public void registerCacheWriter(String cacheName, CacheWriter writer) {
		Cache cache = getCache(cacheName);
		cache.registerCacheWriter(writer);
	}

	public void start() {
		cacheManager = new CacheManager(configFile);
	}

	public void stop() {
	}
}
