package com.soyomaker.orm;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.CacheMode;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Session.LockRequest;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.metadata.ClassMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

@Scope("singleton")
@Repository("hibernateRepository")
@SuppressWarnings("unchecked")
public class HibernateRepository {

	@Autowired
	protected SessionFactory sessionFactory;

	/**
	 * 默认的 HibernateRepository 构造方法
	 */
	public HibernateRepository() {
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * 取得sessionFactory.
	 */
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	/**
	 * 取得当前Session.
	 */
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	/**
	 * @param entity
	 */
	public <T> Serializable save(T entity) {
		return getSession().save(entity);
	}


	/**
	 * @param entity
	 */
	public <T> void saveOrUpdate(T entity) {
		getSession().saveOrUpdate(entity);
	}

	/**
	 * @param entity
	 */
	public <T> void update(T entity) {
		getSession().update(entity);
	}

	/**
	 * @param object
	 * @return
	 */
	public <T> T merge(T entity) {
		return (T) getSession().merge(entity);
	}

	/**
	 * @param persistentEntity
	 */
	public <T> void delete(T entity) {
		Assert.notNull(entity, "entity不能为空");
		getSession().delete(entity);
	}

	/**
	 * 按id删除对象.
	 * 
	 * @param entityClass
	 * @param id
	 */
	public void delete(Class entityClass, Serializable id) {
		delete(get(entityClass, id));
	}

	/**
	 * 调用batchExecute构造HQL,进行删除
	 * 
	 * @param deleteHql
	 * @param id
	 */
	public void delete(String deleteHql, Object... values) {
		batchExecute(deleteHql, values);
	}

	/**
	 * @param <T>
	 * @param entityClass
	 * @param id
	 * @return
	 */
	public <T> T get(Class entityClass, final Serializable id) {
		return (T) getSession().get(entityClass, id);
	}

	/**
	 * 按id列表获取对象列表.
	 */
	public <T> List<T> get(final Collection<?> ids, Class entityClass) {
		return find(entityClass, Restrictions.in(getIdName(entityClass), ids));
	}

	/**
	 * @param <T>
	 * @param entityClass
	 * @param id
	 * @param lockMode
	 * @return
	 */
	public <T> T get(Class entityClass, Serializable id, LockOptions lockMode) {
		return (T) getSession().get(entityClass, id, lockMode);
	}

	/**
	 * @param <T>
	 * @param entityClass
	 * @param id
	 * @return
	 */
	public <T> T load(Class entityClass, Serializable id) {
		return (T) getSession().load(entityClass, id);
	}

	/**
	 * @param <T>
	 * @param entityClass
	 * @param id
	 * @param lockMode
	 * @return
	 */
	public <T> T load(Class entityClass, Serializable id, LockOptions lockMode) {
		return (T) getSession().load(entityClass, id, lockMode);
	}


	/**
	 * @param <T>
	 * @param hql
	 * @param values
	 * @return
	 */
	public <T> List<T> find(final String hql, final Object... values) {
		return createQuery(null, false, null, null, hql, values).list();
	}

	/**
	 * @param <T>
	 * @param lockMode
	 * @param hql
	 * @param values
	 * @return
	 */
	public <T> List<T> find(final LockOptions lockMode, final String hql,
			final Object... values) {
		return createQuery(lockMode, false, null, null, hql, values).list();
	}

	/**
	 * 按HQL查询对象列表.
	 * 
	 * @param values
	 *            命名参数,按名称绑定.
	 */
	public <T> List<T> find(final String hql, final Map<String, ?> values) {
		return createQuery(hql, values).list();
	}

	/**
	 * @param <T>
	 * @param needQueryCache
	 * @param cacheRegion
	 * @param chacheMode
	 * @param hql
	 * @param values
	 * @return
	 */
	public <T> List<T> find(final boolean needQueryCache,
			final String cacheRegion, final CacheMode chacheMode, String hql,
			Object... values) {
		return createQuery(null, needQueryCache, cacheRegion, chacheMode, hql,
				values).list();
	}

	/**
	 * @param <T>
	 * @param needQueryCache
	 * @param cacheRegion
	 * @param chacheMode
	 * @param hql
	 * @param values
	 * @return
	 */
	public <T> List<T> find(final LockOptions lockMode,
			final boolean needQueryCache, final String cacheRegion,
			final CacheMode chacheMode, String hql, Object... values) {
		return createQuery(lockMode, needQueryCache, cacheRegion, chacheMode,
				hql, values).list();
	}

	/**
	 * 按Criteria查询对象列表.
	 * 
	 * @param criterions
	 *            数量可变的Criterion.
	 */
	public <T> List<T> find(final Class entityClass,
			final Criterion... criterions) {
		return createCriteria(entityClass, criterions).list();
	}

	/**
	 * 按属性查找对象列表,匹配方式为相等.
	 */
	public <T> List<T> findBy(final Class entityClass,
			final String propertyName, final Object value) {
		Criterion criterion = Restrictions.eq(propertyName, value);
		return find(entityClass, criterion);
	}

	/**
	 * 按id列表获取对象.
	 */
	public <T> List<T> findByIds(Class entityClass, List ids) {
		return find(entityClass, Restrictions.in(getIdName(entityClass), ids));
	}

	/**
	 * 按HQL查询唯一对象.
	 * 
	 * @param values
	 *            命名参数,按名称绑定.
	 */
	public <T> T findUnique(final String hql, final Map<String, ?> values) {
		return (T) createQuery(null, hql, values, false, null, null)
				.uniqueResult();
	}

	/**
	 * 按Criteria查询唯一对象.
	 * 
	 * @param criterions
	 *            数量可变的Criterion.
	 */
	public <T> T findUnique(Class entityClass, final Criterion... criterions) {
		return (T) createCriteria(entityClass, criterions).uniqueResult();
	}

	/**
	 * 按HQL查询唯一对象.
	 * 
	 * @param values
	 *            命名参数,按名称绑定.
	 */
	public <T> T findUnique(final String hql, final Object... values) {
		return (T) createQuery(null, false, null, null, hql, values)
				.uniqueResult();
	}

	/**
	 * 按属性查找唯一对象, 匹配方式为相等.
	 */
	public <T> T findUniqueBy(Class entityClass, final String propertyName,
			final Object value) {
		Assert.hasText(propertyName, "propertyName不能为空");
		Criterion criterion = Restrictions.eq(propertyName, value);
		return (T) createCriteria(entityClass, criterion).uniqueResult();
	}
	
	public void executeSql(final String sql,final Object... values){
		SQLQuery queryObject = getSession().createSQLQuery(sql);
		
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				queryObject.setParameter(i, values[i]);
			}
		}
		
		queryObject.executeUpdate();
	}
 
	
	public <T> List<T> findBySQLSimple(final Class entityClass, final String sql, final Object... values) {
		SQLQuery queryObject = getSession().createSQLQuery(sql);

		if (entityClass != null) {
			queryObject.addEntity(entityClass);
		}

		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				queryObject.setParameter(i, values[i]);
			}
		}

		return queryObject.list();
	}


	/**
	 * @param <T>
	 * @param entityClass
	 * @return
	 */
	public <T> List<T> findAll(Class entityClass) {
		String queryString = new StringBuilder().append("from ").append(
				entityClass.getName()).toString();

		return this.<T> find(queryString);
	}

	/**
	 * 获取全部对象, 支持按属性行序.
	 */
	public <T> List<T> findAll(Class entityClass, String orderByProperty,
			boolean isAsc) {
		Criteria c = createCriteria(entityClass);
		if (isAsc) {
			c.addOrder(Order.asc(orderByProperty));
		}
		else {
			c.addOrder(Order.desc(orderByProperty));
		}

		return c.list();
	}




	/**
	 * 根据查询HQL与参数列表创建Query对象. 与find()函数可进行更加灵活的操作.
	 * 
	 * @param values
	 *            数量可变的参数,按顺序绑定.
	 */
	public Query createQuery(final String queryString, final Object... values) {
		return createQuery(null, false, null, null, queryString, values);
	}

	/**
	 * 根据查询HQL与参数列表创建Query对象. 与find()函数可进行更加灵活的操作.
	 * 
	 * @param values
	 *            命名参数,按名称绑定.
	 */
	public Query createQuery(final String queryString,
			final Map<String, ?> values) {
		Assert.hasText(queryString, "queryString不能为空");
		Query query = getSession().createQuery(queryString);
		if (values != null) {
			query.setProperties(values);
		}
		return query;
	}

	/**
	 * 根据查询HQL与参数列表创建Query对象. .
	 * 
	 * @param lockMode
	 * @param queryString
	 * @param values
	 *            命名参数,按名称绑定
	 * @param needQueryCache
	 * @param cacheRegion
	 * @param chacheMode
	 * 
	 * @return
	 */
	public Query createQuery(final LockOptions lockMode,
			final String queryString, final Map<String, ?> values,
			final boolean needQueryCache, final String cacheRegion,
			final CacheMode chacheMode) {
		Query query = getSession().createQuery(queryString);
		// 锁配置
		if (lockMode != null) {
			query.setLockOptions(lockMode);
		}
		// 缓存配置
		if (needQueryCache) {
			query.setCacheable(needQueryCache);

			if (cacheRegion != null) {
				query.setCacheRegion(cacheRegion);
			}
			if (chacheMode != null) {
				query.setCacheMode(chacheMode);
			}
		}

		if (values != null) {
			query.setProperties(values);
		}
		return query;
	}

	/**
	 * 根据查询HQL与参数列表创建Query对象.
	 * 
	 * 本类封装的find()函数全部默认返回对象类型为T,当不为T时使用本函数.
	 * 
	 * @param values
	 *            数量可变的参数,按顺序绑定.
	 */
	public Query createQuery(final LockOptions lockMode,
			final boolean needQueryCache, final String cacheRegion,
			final CacheMode chacheMode, final String queryString,
			final Object... values) {
		Query query = getSession().createQuery(queryString);
		// 是否加锁
		if (lockMode != null) {
			query.setLockOptions(lockMode);
		}

		// 缓存配置
		if (needQueryCache) {
			query.setCacheable(needQueryCache);

			if (cacheRegion != null) {
				query.setCacheRegion(cacheRegion);
			}
			if (chacheMode != null) {
				query.setCacheMode(chacheMode);
			}
		}
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
			}
		}
		return query;
	}

	/**
	 * 根据Criterion条件创建Criteria.
	 * 
	 * 本类封装的find()函数全部默认返回对象类型为T,当不为T时使用本函数.
	 * 
	 * @param criterions
	 *            数量可变的Criterion.
	 */
	public Criteria createCriteria(final Class entityClass,
			final Criterion... criterions) {
		Criteria criteria = getSession().createCriteria(entityClass);
		for (Criterion c : criterions) {
			criteria.add(c);
		}

		return criteria;
	}

	/**
	 * 执行HQL进行批量修改/删除操作.
	 */
	public int batchExecute(final String hql, final Object... values) {
		return createQuery(null, false, null, null, hql, values)
				.executeUpdate();
	}

	/**
	 * 执行HQL进行批量修改/删除操作.
	 * 
	 * @return 更新记录数.
	 */
	public int batchExecute(final String hql, final Map<String, ?> values) {
		return createQuery(null, hql, values, false, null, null)
				.executeUpdate();
	}

	/**
	 * 判断对象的属性值在数据库内是否唯一.
	 * 
	 * 在修改对象的情景下,如果属性新修改的值(value)等于属性原来的值(orgValue)则不作比较.
	 */
	public boolean isPropertyUnique(Class entityClass,
			final String propertyName, final Object newValue,
			final Object oldValue) {
		if (newValue == null || newValue.equals(oldValue)) {
			return true;
		}
		Object object = findUniqueBy(entityClass, propertyName, newValue);
		return (object == null);
	}

	/**
	 * 初始化对象. 使用load()方法得到的仅是对象Proxy, 在传到View层前需要进行初始化.
	 * 只初始化entity的直接属性,但不会初始化延迟加载的关联集合和属性. 如需初始化关联属性,可实现新的函数,执行:
	 * Hibernate.initialize(user.getRoles())，初始化User的直接属性和关联集合.
	 * Hibernate.initialize
	 * (user.getDescription())，初始化User的直接属性和延迟加载的Description属性.
	 */
	public void initProxyProperty(Object proxyProperty) {
		Hibernate.initialize(proxyProperty);
	}

	/**
	 * @param entity
	 * @param lockMode
	 * @param timeOut
	 * @param isLockScope
	 */
	public void lock(Object entity, LockOptions lockMode, int timeOut,
			boolean isLockScope) {
		LockRequest lockRequest = getSession().buildLockRequest(lockMode);
		lockRequest.setTimeOut(timeOut).setScope(isLockScope).lock(entity);
	}

	/**
	 * @param entity
	 * @param locikMode
	 */
	public void refresh(Object entity, LockOptions lockMode) {
		getSession().refresh(entity, lockMode);
	}

	/**
	 * 
	 */
	public void clear() {
		getSession().clear();
	}

	/**
	 * Flush当前Session.
	 */
	public void flush() {
		getSession().flush();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.team.cmc.infrastructure.persistence.hibernate.I#contains(java.lang
	 * .Object)
	 */
	public boolean contains(Object entity) {
		return getSession().contains(entity);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.team.cmc.infrastructure.persistence.hibernate.I#evict(java.lang.Object
	 * )
	 */
	public void evict(Object entity) {
		getSession().evict(entity);
	}

	/**
	 * 为Query添加distinct transformer.
	 */
	public Query distinct(Query query) {
		query.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		return query;
	}

	/**
	 * 为Criteria添加distinct transformer.
	 */
	public Criteria distinct(Criteria criteria) {
		criteria
				.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		return criteria;
	}

	/**
	 * 取得对象的主键名.
	 */
	public String getIdName(Class entityClass) {
		ClassMetadata meta = getSessionFactory().getClassMetadata(entityClass);
		return meta.getIdentifierPropertyName();
	}

	/**
	 * 执行count查询获得本次Hql查询所能获得的对象总数.
	 * 
	 * 本函数只能自动处理简单的hql语句,复杂的hql查询请另行编写count语句查询.
	 */
	protected long countHqlResult(final String hql, final Map<String, ?> values) {
		String countHql = prepareCountHql(hql);

		try {
			Long count = findUnique(countHql, values);
			return count;
		}
		catch (Exception e) {
			throw new RuntimeException("hql can't be auto count, hql is:"
					+ countHql, e);
		}
	}

	/**
	 * 执行count查询获得本次Hql查询所能获得的对象总数.
	 * 
	 * 本函数只能自动处理简单的hql语句,复杂的hql查询请另行编写count语句查询.
	 */
	protected long countHqlResult(final String hql, final Object... values) {
		String countHql = prepareCountHql(hql);

		try {
			Long count = findUnique(countHql, values);
			return count;
		}
		catch (Exception e) {
			throw new RuntimeException("hql can't be auto count, hql is:"
					+ countHql, e);
		}
	}

	private String prepareCountHql(String orgHql) {
		String fromHql = orgHql;
		// select子句与order by子句会影响count查询,进行简单的排除.
		fromHql = "from " + StringUtils.substringAfter(fromHql, "from");
		fromHql = StringUtils.substringBefore(fromHql, "order by");
		String countHql = "select count(*) " + fromHql;

		return countHql;
	}
}
