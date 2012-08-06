package com.soyomaker.cache;

import net.sf.ehcache.CacheManager;

import com.soyomaker.lang.AbstractBean;
import com.soyomaker.lang.GameObject;
import com.soyomaker.lang.IService;

public class CacheService extends AbstractBean implements IService {

	private final CacheManager cacheManager = new CacheManager();

	@Override
	public void initialize() {
		// TODO Auto-generated method stub

	}

	@Override
	public void start() {
		// TODO Auto-generated method stub

	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub

	}

}
