package com.soyomaker.cache;

import net.sf.ehcache.CacheManager;

import com.soyomaker.application.AbstractBean;
import com.soyomaker.application.IService;
import com.soyomaker.data.IGObject;

public class CacheService extends AbstractBean implements IService {
	private CacheManager cacheManager = new CacheManager();

	@Override
	public void doCommand(IGObject command) {
		// TODO Auto-generated method stub

	}

	@Override
	public IGObject getStatus() {
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
