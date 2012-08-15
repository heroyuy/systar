package com.soyomaker.service;

import java.util.List;

public interface Service<T> {

	public boolean save(T t);

	public boolean delete(T t);

	public boolean delete(long id);

	public int delete(String condition);

	public boolean update(T t);

	public T get(long id);

	public List<T> find(String condition);

	public T findUnique(String condition);

	public boolean saveOrUpdate(T t);

}
