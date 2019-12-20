package com.wangzi.ehcache;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableCaching
@ComponentScan(basePackages = { "com.wangzi.ehcache"})
@MapperScan(basePackages ="com.wangzi.ehcache.dao")
public class SpringBootEhcacheApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootEhcacheApplication.class, args);
	}
}
