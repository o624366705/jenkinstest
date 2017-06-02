package com.dongnao.alan;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoClientFactoryBean;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.Mongo;

/**
 * 链接mongodb工厂类
 * @author Alan
 *
 */
@Configuration
public class MongoConfig {

    
    public @Bean MongoClientFactoryBean mongo(){
        MongoClientFactoryBean factoryBean = new MongoClientFactoryBean();
        factoryBean.setHost("192.168.80.108"); // 数据库地址
        factoryBean.setPort(27017); // 端口
        return factoryBean;
    }

    
    public @Bean MongoOperations mongoTemplate(Mongo mongo){

    	
        // 操作Mongo的模板类，提供了非常纯粹的oo操作数据库的api
        return new MongoTemplate(mongo, "dongnao"); // dongnao 为数据库名
    }

}