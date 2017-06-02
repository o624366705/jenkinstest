package com.dongnao.alan;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoClientFactoryBean;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.Mongo;

/**
 * ����mongodb������
 * @author Alan
 *
 */
@Configuration
public class MongoConfig {

    
    public @Bean MongoClientFactoryBean mongo(){
        MongoClientFactoryBean factoryBean = new MongoClientFactoryBean();
        factoryBean.setHost("192.168.80.108"); // ���ݿ��ַ
        factoryBean.setPort(27017); // �˿�
        return factoryBean;
    }

    
    public @Bean MongoOperations mongoTemplate(Mongo mongo){

    	
        // ����Mongo��ģ���࣬�ṩ�˷ǳ������oo�������ݿ��api
        return new MongoTemplate(mongo, "dongnao"); // dongnao Ϊ���ݿ���
    }

}