package mongodb_spring;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Update.update;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Query;

import com.dongnao.alan.MongoConfig;
import com.dongnao.alan.bean.Person;

public class MongodbTest {
	
    @Test
    public void insert() {
        AnnotationConfigApplicationContext a = new AnnotationConfigApplicationContext(
                MongoConfig.class);
        MongoOperations mongoOps = a.getBean(MongoOperations.class);
       // Person person = new Person("Alan", 18);
        // 单条插入
       // mongoOps.insert(person);
        List<Person> persons = new ArrayList<Person>();
        for (int i = 1; i <= 20; i++) {
            persons.add(new Person("Alan" + i + "号", 20 + i));
        }
        // 批量插入
        mongoOps.insertAll(persons);
        a.close();
    }
    
    @Test
    public void query() {
        AnnotationConfigApplicationContext a = new AnnotationConfigApplicationContext(
                MongoConfig.class);
       MongoOperations mongoOps = a.getBean(MongoOperations.class);
        // 查询匹配条件的第一条数据
       /*  Person findOne = mongoOps.findOne(new Query(where("age").is(18)), Person.class);
        System.out.println(findOne);*/
        // 查询所有记录
        List<Person> all = mongoOps.findAll(Person.class);
        System.out.println(all.size());
        // 查询age >= 25 and age < 30
      /*  Query query = new Query(where("age").gte(25).lt(30));
        List<Person> findByCondition = mongoOps.find(query , Person.class);
        System.out.println(findByCondition);*/

        // 原生命令方式
        BasicQuery bq =new BasicQuery("{name:'Alan'}");

        Person findOne2 = mongoOps.findOne(bq, Person.class);
        System.out.println("查询出来的内容是：---" + findOne2);

        a.close();
    }
    
    @Test
    public void updateTest() {
        AnnotationConfigApplicationContext a = new AnnotationConfigApplicationContext(
                MongoConfig.class);
        MongoOperations mongoOps = a.getBean(MongoOperations.class);
        Person findOne = mongoOps.findOne(new Query(where("age").is(18)), Person.class);
        //判断对象是否存在
        if(findOne != null){
        	//更新前加时间戳
        	//mongoOps.update({"name","alan"}, {});
        	mongoOps.updateFirst(new Query(where("age").is(18)), update("age",System.currentTimeMillis()+""), Person.class);
        	/*//如果没有执行
        	mongoOps.updateFirst(new Query(where("age").is(333)), update("name","abcd "), Person.class);
        	//
        	mongoOps.updateFirst(new Query(where("age").is(333)), update("updateTime",""), Person.class);*/
        }
        // 更新一条  
       
    }


}
