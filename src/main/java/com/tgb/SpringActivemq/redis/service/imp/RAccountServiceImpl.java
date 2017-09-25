package com.tgb.SpringActivemq.redis.service.imp;

import com.tgb.SpringActivemq.redis.dao.BaseDao;
import com.tgb.SpringActivemq.redis.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


/**  
 *
 * @author tongyicheng
 * @date 2015-06-25
 */
@Service
public class RAccountServiceImpl implements BaseService {
    
    @Autowired
    protected BaseDao baseDao;

	public void delete(String key) {
		// TODO Auto-generated method stub
		
		baseDao.delete(key);
	}

	public void delete(String key, Object arg) {
		// TODO Auto-generated method stub
		
		baseDao.delete(key, arg);
	}

	public void deleteList(Collection<String> keys) {
		// TODO Auto-generated method stub
		
		baseDao.deleteList(keys);
	}

	public Object get(String key, Object arg) {
		// TODO Auto-generated method stub
		
		return baseDao.get(key, arg);
	}

	public boolean hasKey(String key, Object arg) {
		// TODO Auto-generated method stub
		
		return baseDao.hasKey(key, arg);
	}

	public Set<Object> keys(String key) {
		// TODO Auto-generated method stub
		
		return baseDao.keys(key);
	}

	public Long lengthObj(String key) {
		// TODO Auto-generated method stub
		
		return baseDao.lengthObj(key);
	}

	public List<Object> multiGet(String key, Collection<Object> col) {
		// TODO Auto-generated method stub
		
		return baseDao.multiGet(key, col);
	}

	public void put(String key, Object arg, Object value) {
		// TODO Auto-generated method stub
		
		baseDao.put(key, arg, value);
	}

	public void putAll(String key, Map map) {
		// TODO Auto-generated method stub
		
		baseDao.putAll(key, map);
	}

	public void putAll(String key, Map map, long time) {
		// TODO Auto-generated method stub

		baseDao.putAll(key, map,time);
	}

	public void putIfAbsent(String key, Object arg, Object value) {
		// TODO Auto-generated method stub
		
		baseDao.putIfAbsent(key, arg, value);
	}

	public Map<Object, Object> readAll(String key) {
		// TODO Auto-generated method stub
		
		return baseDao.readAll(key);
	}

	public List<Object> readList(String key) {
		// TODO Auto-generated method stub
		
		return baseDao.readList(key);
	}

	public void put(String key, Object arg, Object value, long time) {
		// TODO Auto-generated method stub
		
		baseDao.put(key, arg, value, time);
	}

	public void putClearTime(String key, Object arg, Object value, Date time){
		baseDao.putClearTime(key, arg, value, time);
	}

	public void testClass(String key, String value){
		baseDao.testClass(key,value);
	}
}
