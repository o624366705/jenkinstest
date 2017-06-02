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
        // ��������
       // mongoOps.insert(person);
        List<Person> persons = new ArrayList<Person>();
        for (int i = 1; i <= 20; i++) {
            persons.add(new Person("Alan" + i + "��", 20 + i));
        }
        // ��������
        mongoOps.insertAll(persons);
        a.close();
    }
    
    @Test
    public void query() {
        AnnotationConfigApplicationContext a = new AnnotationConfigApplicationContext(
                MongoConfig.class);
       MongoOperations mongoOps = a.getBean(MongoOperations.class);
        // ��ѯƥ�������ĵ�һ������
       /*  Person findOne = mongoOps.findOne(new Query(where("age").is(18)), Person.class);
        System.out.println(findOne);*/
        // ��ѯ���м�¼
        List<Person> all = mongoOps.findAll(Person.class);
        System.out.println(all.size());
        // ��ѯage >= 25 and age < 30
      /*  Query query = new Query(where("age").gte(25).lt(30));
        List<Person> findByCondition = mongoOps.find(query , Person.class);
        System.out.println(findByCondition);*/

        // ԭ�����ʽ
        BasicQuery bq =new BasicQuery("{name:'Alan'}");

        Person findOne2 = mongoOps.findOne(bq, Person.class);
        System.out.println("��ѯ�����������ǣ�---" + findOne2);

        a.close();
    }
    
    @Test
    public void updateTest() {
        AnnotationConfigApplicationContext a = new AnnotationConfigApplicationContext(
                MongoConfig.class);
        MongoOperations mongoOps = a.getBean(MongoOperations.class);
        Person findOne = mongoOps.findOne(new Query(where("age").is(18)), Person.class);
        //�ж϶����Ƿ����
        if(findOne != null){
        	//����ǰ��ʱ���
        	//mongoOps.update({"name","alan"}, {});
        	mongoOps.updateFirst(new Query(where("age").is(18)), update("age",System.currentTimeMillis()+""), Person.class);
        	/*//���û��ִ��
        	mongoOps.updateFirst(new Query(where("age").is(333)), update("name","abcd "), Person.class);
        	//
        	mongoOps.updateFirst(new Query(where("age").is(333)), update("updateTime",""), Person.class);*/
        }
        // ����һ��  
       
    }


}
