package com.tgb.SpringActivemq.redis.service;

import java.util.*;


/**
 *
 * @author tongyicheng
 * @date 2015-07-25
 */
public interface BaseService {
	

	/***
	 * 根据键值保存map数据
	 * @param key
	 * @param map
	 */
	public void putAll(String key, Map map);

	/***
	 * 根据键值保存map数据
	 * @param key
	 * @param map
	 */
	public void putAll(String key, Map map, long time);

	/***
	 * 保存key中某个键值对
	 * @param key
	 * @param arg
	 * @param value
	 */
	public void put(String key, Object arg, Object value);

	/***
	 * 保存key中某个键值对（有生命周期限制）
	 * @param key
	 * @param arg
	 * @param value
	 */
	public void put(String key, Object arg, Object value, long time);


	public void putClearTime(String key, Object arg, Object value, Date time);


	/***
	 * 如果 这条数据不存在，才会去put进去
	 * @param key
	 * @param arg
	 * @param value
	 */
	public void putIfAbsent(String key, Object arg, Object value);

	/***
	 * 批量删除某些键值信息
	 * @param keys
	 */
	public void deleteList(Collection<String> keys);

	/***
	 * 删除某个键值信息
	 * @param key
	 */
	public void delete(String key);

	/***
	 * 根据key,和 map key 获删除值
	 * @param key
	 * @param arg
	 */
	public void delete(String key, Object arg);

	/***
	 * 根据key 和 键值列表获取列表值
	 * @param key
	 * @param col
	 * @return
	 */
	public List<Object> multiGet(String key, Collection<Object> col);

	/***
	 * 判断key 和 键 存不存在
	 * @param key
	 * @param arg
	 * @return
	 */
	public boolean hasKey(String key, Object arg);

	/***
	 * 根据key值查找键列表并返回Set集合
	 * @param key
	 * @return
	 */
	public Set<Object> keys(String key);

	/***
	 * 根据key,和map key 获取值
	 * @param key
	 * @param arg
	 * @return
	 */
	public Object get(String key, Object arg);

	/***
	 * 获取key 对应的所有键值对
	 * @param key
	 * @return
	 */
	public Map<Object,Object> readAll(String key);

	/***
	 * 根据键值获取list
	 * @param key
	 * @return
	 */
	public List<Object> readList(String key);

	/**
	 *  列表长度
	 * @param key
	 * @return
	 */
	public Long lengthObj(String key);

	void testClass(String key, String value);
	
}
