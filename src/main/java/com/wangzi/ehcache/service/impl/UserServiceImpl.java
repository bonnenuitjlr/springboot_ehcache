package com.wangzi.ehcache.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.wangzi.ehcache.dao.UserDao;
import com.wangzi.ehcache.entity.User;
import com.wangzi.ehcache.service.UserService;

import net.sf.ehcache.CacheException;

@Service
public class UserServiceImpl implements UserService{

	//这里的单引号不能少，否则会报错，被识别是一个对象
    private static final String CACHE_KEY = "'user'";
    private static final String DEMO_CACHE_NAME = "users";
    
    @Autowired
    private UserDao userDao;
    
	@Override
	@Cacheable(value = DEMO_CACHE_NAME,key = "'user_'+#uuid")//这是清除缓存
	public void delete(String uuid) {
		// TODO Auto-generated method stub
		userDao.delete(uuid);
	}
	@Override
    @CachePut(value = DEMO_CACHE_NAME,key = "'user_'+#user.getUuid()")
    public User update(User user) throws CacheException{
        User user1 = userDao.findByUuid(user.getUuid());
        if (null == user1){
            throw new  CacheException("Not Find");
        }
        user1.setAge(user.getAge());
        user1.setName(user.getName());
        return user1;
    }

	@Override
    @Cacheable(value=DEMO_CACHE_NAME,key="'user_'+#uuid")
    public User findByUuid(String uuid){
        //若找不到缓存将打印出提示语句
        System.err.println("没有走缓存！"+uuid);
        return userDao.findByUuid(uuid);
    }

	@Override
    //保存用户数据
    @CacheEvict(value=DEMO_CACHE_NAME,key=CACHE_KEY)
    public int save(User user){
        return userDao.save(user);
    }

}
