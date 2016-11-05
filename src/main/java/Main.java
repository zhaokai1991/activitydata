import com.mongodb.Mongo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import util.DataMiningTool;
import util.DeserializationTool;
import util.SerializationTool;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Update.update;
import static org.springframework.data.mongodb.core.query.Query.query;

/**
 * Created by zhaokai on 16-10-26.
 */
public class Main {

    @Autowired
    public static MongoTemplate mongoTemplate;

    private static Logger logger=Logger.getLogger(Main.class);

    public static void main(String[] args) throws IOException, URISyntaxException {

//        MongoOperations mongoTemplate=new MongoTemplate(new SimpleMongoDbFactory(new Mongo(),"activitydata"));

        ApplicationContext context=new ClassPathXmlApplicationContext("Config.xml");

        MongoTemplate mongoTemplate=(MongoTemplate)context.getBean("mongoTemplate");

        Person p=new Person("Lily",23,new City("Beijing","China"));

        mongoTemplate.insert(p);
        logger.info("Insert:"+p);

        p=mongoTemplate.findById(p.getId(),Person.class);
        logger.info("Found:"+p);

        mongoTemplate.updateFirst(query(where("name").is("Lily")),update("age",35),Person.class);
        p=mongoTemplate.findOne(query(where("name").is("Lily")),Person.class);
        logger.info("Update:"+p);

//        mongoTemplate.remove(p);

//        mongoTemplate.dropCollection(Person.class);
    }

}
