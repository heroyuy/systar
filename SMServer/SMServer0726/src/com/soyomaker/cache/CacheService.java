package com.soyomaker.cache;

import net.sf.ehcache.CacheManager;

import com.soyomaker.common.AbstractBean;
import com.soyomaker.common.GameObject;
import com.soyomaker.common.IService;

public class CacheService extends AbstractBean implements IService {
	private CacheManager cacheManager = new CacheManager();

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
