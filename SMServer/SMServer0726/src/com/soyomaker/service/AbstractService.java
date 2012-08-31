/**
 * Copyright (c) 2011-20012 
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * 
 * Id AbstractService.java 下午11:44:24 chenwentao
 */
package com.soyomaker.service;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.soyomaker.orm.HibernateRepository;
import com.soyomaker.util.ReflectionUtils;

/**
 * 
 * @author chenwentao
 * 
 */
@Service("abstractService")
@Transactional
public abstract class AbstractService<T> implements
		com.soyomaker.service.Service<T> {

	protected Logger logger = Logger.getLogger(getClass());

	protected Class<T> entityClass;

	public AbstractService() {
		this.entityClass = ReflectionUtils.getSuperClassGenricType(getClass());
	}

	@Autowired
	protected HibernateRepository hibernateRepository;

	public void setHibernateRepository(HibernateRepository hibernateRepository) {
		this.hibernateRepository = hibernateRepository;
	}

	public SessionFactory getSessionFactory() {
		return this.hibernateRepository.getSessionFactory();
	}

	public Session getSession() {
		return this.hibernateRepository.getSession();
	}

	// --------------------------CRUD----------------------------------------------
	@Override
	public boolean saveOrUpdate(T t) {
		try {
			hibernateRepository.saveOrUpdate(t);
			return true;
		} catch (Exception e) {
			logger.error("save 错误", e);
			return false;
		}
	}

	@Override
	public boolean save(T t) {
		try {
			hibernateRepository.save(t);
			return true;
		} catch (Exception e) {
			logger.error("save 错误", e);
			return false;
		}
	}

	@Override
	public boolean update(T t) {
		try {
			hibernateRepository.update(t);
			return true;
		} catch (Exception e) {
			logger.error("save 错误", e);
			return false;
		}
	}

	@Override
	public boolean delete(Serializable id) {
		try {
			hibernateRepository.delete(entityClass, id);
			return true;
		} catch (Exception e) {
			logger.error("save 错误", e);
			return false;
		}
	}

	@Override
	public boolean delete(T t) {
		try {
			hibernateRepository.delete(t);
			return true;
		} catch (Exception e) {
			logger.error("save 错误", e);
			return false;
		}
	}

	@Override
	public boolean delete(String deleteHql, Object... values) {
		try {
			hibernateRepository.delete(deleteHql, values);
			return true;
		} catch (Exception e) {
			logger.error("save 错误", e);
			return false;
		}
	}

	// =============================Get Load find
	// ===================================================//
	@Override
	public T load(Serializable id) {
		return hibernateRepository.load(entityClass, id);
	}

	@Override
	public T get(Serializable id) {
		return hibernateRepository.get(entityClass, id);
	}

	@Override
	public List<T> find(String hql, Object... values) {
		return hibernateRepository.find(hql, values);
	}

	@Override
	public List<T> findAll() {
		return hibernateRepository.findAll(entityClass);
	}

	@Override
	public T findUnique(String hql, Object... values) {
		List<T> results = hibernateRepository.find(hql, values);
		if (CollectionUtils.isEmpty(results)) {
			return null;
		}

		return results.get(0);
	}
}
