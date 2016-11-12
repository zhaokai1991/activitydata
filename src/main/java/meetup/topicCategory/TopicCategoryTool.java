package meetup.topicCategory;

import com.alibaba.fastjson.JSON;
import org.apache.http.client.utils.URIBuilder;
import org.apache.log4j.Logger;
import org.springframework.data.mongodb.core.MongoTemplate;
import util.HttpUtil;
import util.UriBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

/**
 * Created by zhaokai on 16-11-12.
 */
public class TopicCategoryTool {

    private static Logger logger= Logger.getLogger(TopicCategoryTool.class);

    public static List<TopicCategory> getAllTopicCategoriesFromHttp() throws URISyntaxException, IOException {
        URIBuilder uriBuilder= UriBuilder.get();
        uriBuilder.setPath("/find/topic_categories");
        URI uri=uriBuilder.build();

        logger.info("获取topic_category："+uri.toString());

        List<TopicCategory> topicCategories= JSON.parseArray(HttpUtil.getEntityString(uri),TopicCategory.class);

        logger.info("获取"+topicCategories.size()+"个topic_category。");

        return topicCategories;
    }

    public static void storeTopicCategories(List<TopicCategory> topicCategories,MongoTemplate mongoTemplate){
        mongoTemplate.insertAll(topicCategories);
        logger.info("插入category："+topicCategories);
    }

}
