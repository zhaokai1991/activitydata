import douban.City;
import douban.CityTool;
import douban.Event;
import douban.EventTool;
import meetup.group.Group;
import meetup.group.GroupTool;
import meetup.topic.TopicTool;
import meetup.topicCategory.TopicCategory;
import meetup.topicCategory.TopicCategoryTool;
import org.springframework.data.mongodb.core.MongoTemplate;
import meetup.category.Category;
import meetup.category.CategoryTool;
import util.MongoUtil;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Set;

/**
 * Created by zhaokai on 16-11-9.
 */
public class ActivityDataDriver {

    public static void run() throws IOException, URISyntaxException, InterruptedException {

        MongoTemplate mongoTemplate= MongoUtil.getMongoTemplate();

        //先获取category
        if(!mongoTemplate.collectionExists(Category.class)){
            List<Category> categories= CategoryTool.getAllCategoriesFromHttp();
            CategoryTool.storeCategories(categories,mongoTemplate);
        }

        //获取topic_category
        if(!mongoTemplate.collectionExists(TopicCategory.class)){
            List<TopicCategory> topicCategories= TopicCategoryTool.getAllTopicCategoriesFromHttp();
            TopicCategoryTool.storeTopicCategories(topicCategories,mongoTemplate);
        }

        //获取group
        List<Group> groups=GroupTool.getAllGroupsInNewYorkFromHttp();
        GroupTool.storeGroups(groups,mongoTemplate);

        //从group中获取所有topic
        TopicTool.getAndStoreAllTopicsInNewYork(mongoTemplate);
    }

}
