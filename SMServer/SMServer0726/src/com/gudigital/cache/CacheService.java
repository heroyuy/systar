package com.gudigital.cache;

import net.sf.ehcache.CacheManager;

import com.gudigital.application.AbstractBean;
import com.gudigital.application.IService;
import com.gudigital.core.IGUObject;

public class CacheService extends AbstractBean implements IService {
	private CacheManager cacheManager = new CacheManager();

	@Override
	public void doCommand(IGUObject command) {
		// TODO Auto-generated method stub

	}

	@Override
	public IGUObject getStatus() {
		// TODO Auto-generated method stub
		return null;
	}

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
