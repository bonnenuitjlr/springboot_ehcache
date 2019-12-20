package com.wangzi.ehcache.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.wangzi.ehcache.entity.User;

@Repository
public interface UserDao {
	void delete(String uuid);
	User update(User user);
	User findByUuid(String uuid);
	int save(@Param("user") User user);
}
