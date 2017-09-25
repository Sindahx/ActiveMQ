package com.tgb.SpringActivemq.redis.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.TimeUnit;

@Repository
public class BaseDao {

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	/***
	 * 根据键值保存map数据（有生命周期限制）
	 * @param key
	 * @param map
	 */
	public void putAll(String key, Map map) {
		stringRedisTemplate.opsForHash().putAll(key, map);
		stringRedisTemplate.expire(key, 60*60*24, TimeUnit.SECONDS);
	}

	/***
	 * 根据键值保存map数据（有生命周期限制）
	 * @param key
	 * @param map
	 */
	public void putAll(String key, Map map, long time) {
		stringRedisTemplate.opsForHash().putAll(key, map);
		stringRedisTemplate.expire(key, time, TimeUnit.SECONDS);
	}
	
	
	/***
	 * 根据键值保存map数据(没有生命周期限制)
	 * @param key
	 * @param map
	 */
	public void putAllNoTime(String key, Map<Object,Object> map) {
		stringRedisTemplate.opsForHash().putAll(key, map);
	}
	
	
	/***
	 * 保存key中某个键值对（有生命周期限制）
	 * @param key
	 * @param arg
	 * @param value
	 */
	public void put(String key, Object arg, Object value){
		stringRedisTemplate.opsForHash().put(key, arg, value);
		stringRedisTemplate.expire(key, 60*60*24, TimeUnit.SECONDS);
	}
	
	/***
	 * 保存key中某个键值对（有生命周期限制）
	 * @param key
	 * @param arg
	 * @param value
	 */
	public void put(String key, Object arg, Object value, long time){
		stringRedisTemplate.opsForHash().put(key, arg, value);
		stringRedisTemplate.expire(key, time, TimeUnit.SECONDS);
	}
	
	
	public void putClearTime(String key, Object arg, Object value, Date time){
		stringRedisTemplate.opsForHash().put(key, arg, value);
		stringRedisTemplate.expireAt(key,time);
	}
	
	/***
	 * 保存key中某个键值对(没有生命周期限制)
	 * @param key
	 * @param arg
	 * @param value
	 */
	public void putNoTime(String key, Object arg, Object value){
		stringRedisTemplate.opsForHash().put(key, arg, value);
	}

	/***
	 * 如果 这条数据不存在，才会去put进去（有生命周期）
	 * @param key
	 * @param arg
	 * @param value
	 */
	public void putIfAbsent(String key, Object arg, Object value){
		stringRedisTemplate.opsForHash().putIfAbsent(key, arg, value);
		stringRedisTemplate.expire(key, 60*60*24, TimeUnit.SECONDS);
	}
	
	/***
	 * 如果 这条数据不存在，才会去put进去(没有生命周期限制)
	 * @param key
	 * @param arg
	 * @param value
	 */
	public void putIfAbsentNoTime(String key, Object arg, Object value){
		stringRedisTemplate.opsForHash().putIfAbsent(key, arg, value);
	}
	
	/***
	 * 批量删除某些键值信息
	 * @param keys
	 */
	public void deleteList(Collection<String> keys){
		stringRedisTemplate.delete(keys);
	}
	
	/***
	 * 删除某个键值信息
	 * @param key
	 */
	public void delete(String key){
		stringRedisTemplate.delete(key);
	}
	
	/***
	 * 根据key,和 map key 获删除值
	 * @param key
	 * @param arg
	 */
	public void delete(String key, Object arg) {
		stringRedisTemplate.opsForHash().delete(key, arg);
	}
	
	/***
	 * 根据key 和 键值列表获取列表值
	 * @param key
	 * @param col
	 * @return
	 */
	public List<Object> multiGet(String key, Collection<Object> col){
		return stringRedisTemplate.opsForHash().multiGet(key, col);
	}
	
	/***
	 * 判断key 和 键 存不存在
	 * @param key
	 * @param arg
	 * @return
	 */
	public boolean hasKey(String key, Object arg){
		return stringRedisTemplate.opsForHash().hasKey(key, arg);
	}
	
	/***
	 * 根据key值查找键列表并返回Set集合
	 * @param key
	 * @return
	 */
	public Set<Object> keys(String key){
		return stringRedisTemplate.opsForHash().keys(key);
	}
	
	/***
	 * 根据key,和map key 获取值
	 * @param key
	 * @param arg
	 * @return
	 */
	public Object get(String key, Object arg) {
		
		return stringRedisTemplate.opsForHash().get(key, arg);
	}
	
	/***
	 * 获取key 对应的所有键值对
	 * @param key
	 * @return
	 */
	public Map<Object,Object> readAll(String key){
		
		return stringRedisTemplate.opsForHash().entries(key);
	}
	
	/***
	 * 根据键值获取list
	 * @param key
	 * @return
	 */
	public List<Object> readList(String key){
		
		return stringRedisTemplate.opsForHash().values(key);
	}
	
	/**
	 *  列表长度
	 * @param key
	 * @return
	 */
	public Long lengthObj(String key) {
		return stringRedisTemplate.opsForHash().size(key);
	}

	public void testClass(String key, String value){
		stringRedisTemplate.opsForValue().set(key,value);
	}
	
}
