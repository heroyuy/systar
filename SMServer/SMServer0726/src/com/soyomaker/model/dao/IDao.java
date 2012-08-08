package com.soyomaker.model.dao;

/**
 * dao基本接口。 TODO 有待扩展
 * 
 * @author wp_g4
 * 
 * @param <T>
 *            类型
 */
public interface IDao<T> {

	/**
	 * 插入记录
	 * 
	 * @param t
	 *            待插入记录
	 * @return 操作结果：成功or失败
	 */
	public boolean insert(T t);

	/**
	 * 根据long型主键删除一条记录
	 * 
	 * @param id
	 *            主键值
	 * @return 操作结果：成功or失败
	 */
	public boolean delete(long id);

	/**
	 * 根据String型主键删除一条记录
	 * 
	 * @param id
	 *            主键值
	 * @return 操作结果：成功or失败
	 */
	public boolean delete(String id);

	/**
	 * 更新记录
	 * 
	 * @param t
	 *            待插入记录
	 * @return 操作结果：成功or失败
	 */
	public boolean update(T t);

	/**
	 * 根据long型主键获取一条记录
	 * 
	 * @param id
	 *            主键值
	 * @return 记录
	 */
	public T get(long id);

	/**
	 * 根据String型主键获取一条记录
	 * 
	 * @param id
	 *            主键值
	 * @return 记录
	 */
	public T get(String id);

}
