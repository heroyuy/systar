package com.soyomaker.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.impl.NutDao;

public class BasicDao<T> implements IDao<T> {

	private Class<T> entityClass;

	@SuppressWarnings("unchecked")
	public BasicDao() {
		Type genType = getClass().getGenericSuperclass();
		Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
		entityClass = (Class<T>) params[0];
	}

	@Override
	public boolean insert(T t) {
		Dao dao = new NutDao(GameDataSource.getInstance().getDataSource());
		T res = dao.insert(t);
		return res != null;
	}

	@Override
	public boolean delete(long id) {
		Dao dao = new NutDao(GameDataSource.getInstance().getDataSource());
		int num = dao.delete(entityClass, id);
		return num != 0;
	}

	@Override
	public boolean delete(String id) {
		Dao dao = new NutDao(GameDataSource.getInstance().getDataSource());
		int num = dao.delete(entityClass, id);
		return num != 0;
	}

	@Override
	public boolean update(T t) {
		Dao dao = new NutDao(GameDataSource.getInstance().getDataSource());
		int num = dao.update(t);
		return num != 0;
	}

	@Override
	public T get(long id) {
		Dao dao = new NutDao(GameDataSource.getInstance().getDataSource());
		return dao.fetch(entityClass, id);
	}

	@Override
	public T get(String id) {
		Dao dao = new NutDao(GameDataSource.getInstance().getDataSource());
		return dao.fetch(entityClass, id);
	}

	@Override
	public List<T> getAll() {
		Dao dao = new NutDao(GameDataSource.getInstance().getDataSource());
		return dao.query(entityClass, null);
	}

	@Override
	public List<T> get(String name, String op, Object value) {
		Dao dao = new NutDao(GameDataSource.getInstance().getDataSource());
		return dao.query(entityClass, Cnd.where(name, op, value));
	}

}
