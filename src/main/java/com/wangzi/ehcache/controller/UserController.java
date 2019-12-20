package com.wangzi.ehcache.controller;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wangzi.ehcache.entity.User;
import com.wangzi.ehcache.service.UserService;

import net.sf.ehcache.CacheException;

@RestController
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping("/encache")
    public String EhcacheTest() throws Exception{
        logger.debug("进行Encache缓存测试");
        System.out.println("====生成第一个用户====");
        User user1 = new User();
        //生成第一个用户的唯一标识符 UUID
        String u1_uuid = UUID.randomUUID().toString();
        //去掉 UUID 的 - 符号
        String uuid1 = u1_uuid.substring(0,8)+u1_uuid.substring(9,13)+u1_uuid.substring(14,18)+u1_uuid.substring(19,23)+u1_uuid.substring(24);
        user1.setName("张三");
        user1.setAge(18);
        user1.setUuid(uuid1);
        if (userService.save(user1) == 0){
            throw new Exception("用户对象插入数据库失败");
        }

        //第一次查询
        System.out.println(userService.findByUuid(user1.getUuid()));
        //通过缓存查询
        System.out.println(userService.findByUuid(user1.getUuid()));

        System.out.println("====修改数据====");
        User user2 = new User();
        user2.setName("李四-update");
        user2.setAge(22);
        user2.setId(user1.getId());
        user2.setUuid(user1.getUuid());
        try {
            System.out.println(userService.update(user2));
        } catch (CacheException e){
            e.printStackTrace();
        }

        System.out.println(userService.findByUuid(user2.getUuid()));
        return "success";
    }
}
