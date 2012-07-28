package com.soyomaker.cache;

import java.util.Collection;
import java.util.Map;

import net.sf.ehcache.CacheException;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Status;
import net.sf.ehcache.loader.CacheLoader;

public class SimpleTableLoader implements CacheLoader {

	@Override
	public CacheLoader clone(Ehcache arg0) throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void dispose() throws CacheException {
		// TODO Auto-generated method stub

	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Status getStatus() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

	@Override
	public Object load(Object arg0) throws CacheException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object load(Object arg0, Object arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map loadAll(Collection arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map loadAll(Collection arg0, Object arg1) {
		// TODO Auto-generated method stub
		return null;
	}

}
