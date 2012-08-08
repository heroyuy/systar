package com.soyomaker.model.dao;

import java.util.Collection;

public interface IDao<T> {

	public T insert(int id);
	
	public void put(T t);
	
	public Collection<T> list();
}
