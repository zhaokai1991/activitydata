package douban.user;

import com.alibaba.fastjson.JSON;
import org.apache.http.client.utils.URIBuilder;
import org.apache.log4j.Logger;
import org.springframework.data.mongodb.core.MongoTemplate;
import util.HttpUtil;
import util.MongoUtil;
import util.UriBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

/**
 * Created by zhaokai on 16-11-10.
 */
public class UserTool {

    private static Logger logger=Logger.getLogger(UserTool.class);

    public static User getUserFromHttp(String id) throws URISyntaxException, IOException {
        URIBuilder uriBuilder= UriBuilder.get();
        uriBuilder.setPath("/user/"+id);
        URI uri=uriBuilder.build();

        String userResult= HttpUtil.getEntityString(uri);

        User user= JSON.parseObject(userResult,User.class);

        logger.info("获取用户："+user);

        return user;
    }

    public static void storeUser(User user){
        MongoTemplate mongoTemplate= MongoUtil.getMongoTemplate();
        if(!mongoTemplate.exists(query(where("userId").is(user.getUserId())),User.class)){
            mongoTemplate.insert(user);
        }
    }

}
