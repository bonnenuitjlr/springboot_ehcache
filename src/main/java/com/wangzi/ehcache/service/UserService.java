package com.wangzi.ehcache.service;

import com.wangzi.ehcache.entity.User;

public interface UserService {
	void delete(String uuid);
	User update(User user);
	User findByUuid(String uuid);
	int save(User user);
}
