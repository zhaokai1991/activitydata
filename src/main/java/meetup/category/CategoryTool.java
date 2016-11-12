package meetup.category;

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
public class CategoryTool {

    private static Logger logger=Logger.getLogger(CategoryTool.class);

    public static List<Category> getAllCategoriesFromHttp() throws URISyntaxException, IOException {
        URIBuilder uriBuilder= UriBuilder.get();
        uriBuilder.setPath("/2/categories");
        URI uri=uriBuilder.build();

        logger.info("获取category："+uri.toString());

        String categoriesJsonStr= JSON.parseObject(HttpUtil.getEntityString(uri)).getString("results");
        List<Category> categories=JSON.parseArray(categoriesJsonStr,Category.class);

        logger.info("获取"+categories.size()+"个category。");

        return categories;
    }

    public static void storeCategories(List<Category> categories,MongoTemplate mongoTemplate){
        mongoTemplate.insertAll(categories);
        logger.info("插入category："+categories);
    }

}
