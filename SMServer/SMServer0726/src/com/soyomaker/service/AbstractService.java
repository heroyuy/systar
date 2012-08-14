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

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.soyomaker.model.Player;
import com.soyomaker.model.User;
import com.soyomaker.orm.HibernateRepository;

/**
 *
 * @author chenwentao
 *
 */
@Service("abstractService")
@Transactional
public abstract class AbstractService {
	
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
	public void saveOrUpdate(Object entity) {
		hibernateRepository.saveOrUpdate(entity);
	}

	public Serializable save(Object entity) {
		return hibernateRepository.save(entity);
	}

	public void update(Object entity) {
		hibernateRepository.update(entity);
	}

	@SuppressWarnings("unchecked")
	public void delete(Class entityClass, Serializable id) {
		hibernateRepository.delete(entityClass, id);
	}

	public void delete(Object entity) {
		hibernateRepository.delete(entity);
	}

	@SuppressWarnings("unchecked")
	public <T> T load(Class<?> entityClass, Serializable id) {
		return (T) hibernateRepository.load(entityClass, id);
	}

	@SuppressWarnings("unchecked")
	public <T> T get(Class<?> entityClass, Serializable id) {
		return (T) hibernateRepository.get(entityClass, id);
	}

	public void delete(String deleteHql, Object... values) {
		hibernateRepository.delete(deleteHql, values);
	}

	public <T> List<T> find(String hql, Object... values) {
		return hibernateRepository.find(hql, values);
	}

	public <T> T findUnique(String hql, Object... values) {
		List<T> results = hibernateRepository.find(hql, values);
		if (CollectionUtils.isEmpty(results)) {
			return null;
		}

		return results.get(0);
	}
}
