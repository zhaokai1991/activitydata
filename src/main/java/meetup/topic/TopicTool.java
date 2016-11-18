package meetup.topic;

import meetup.group.Group;
import org.apache.log4j.Logger;
import org.springframework.data.mongodb.core.MongoTemplate;
import util.PojoID;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import java.util.List;

/**
 * Created by zhaokai on 16-11-16.
 */
public class TopicTool {

    private static Logger logger=Logger.getLogger(TopicTool.class);

    public static List<Topic> getTopicsFromAnExistedGroup(Group group){
        List<Topic> topicList=group.getTopics();
        logger.info("从"+group.getGroupId()+" group获取"+topicList.size()+"个topic:"+topicList);
        return group.getTopics();
    }

    public static void storeTopics(List<Topic> topics,MongoTemplate mongoTemplate){
        for(Topic topic:topics){
            if(!mongoTemplate.exists(query(where(PojoID.TOPIC_ID).is(topic.getTopicId())),Topic.class)){
                mongoTemplate.insert(topic);
                logger.info("插入topic:"+topic);
            }
        }
    }

    public static void getAndStoreAllTopicsInNewYork(MongoTemplate mongoTemplate){
        List<Group> groupList=mongoTemplate.findAll(Group.class);
        for(Group group:groupList){
            storeTopics(group.getTopics(),mongoTemplate);
        }
    }

}
