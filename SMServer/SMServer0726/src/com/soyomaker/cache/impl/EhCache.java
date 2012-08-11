package com.soyomaker.cache.impl;

import java.util.Collection;
import java.util.Map;

import com.soyomaker.cache.Cacheable;

import net.sf.ehcache.Cache;

public class EhCache<T> implements Cacheable<T> {

	private Cache cache;

	public EhCache() {
		
	}

	@Override
	public void addElement(String key, T t) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addElement(Map<String, T> maps) {
		// TODO Auto-generated method stub

	}

	@Override
	public T getElement(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, T> getElement(Collection<String> keys) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeElement(String key) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeElement(Collection<String> keys) {
		// TODO Auto-generated method stub

	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

}
