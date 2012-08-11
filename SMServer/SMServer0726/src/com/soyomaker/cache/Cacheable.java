package com.soyomaker.cache;

import java.util.Collection;
import java.util.Map;

public interface Cacheable<T> {

	public void addElement(String key, T t);

	public void addElement(Map<String, T> maps);

	public T getElement(String key);

	public Map<String, T> getElement(Collection<String> keys);

	public void removeElement(String key);

	public void removeElement(Collection<String> keys);

	public int size();
}
