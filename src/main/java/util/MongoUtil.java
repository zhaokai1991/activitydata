package util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 * Created by zhaokai on 16-11-7.
 */
public class MongoUtil {

    private static MongoTemplate mongoTemplate=null;

    public static MongoTemplate getMongoTemplate(){
        if(mongoTemplate==null){
            ApplicationContext context=new ClassPathXmlApplicationContext("Config.xml");
            mongoTemplate=(MongoTemplate)context.getBean("mongoTemplate");
        }

        return mongoTemplate;
    }

}
