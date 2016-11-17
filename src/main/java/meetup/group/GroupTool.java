package meetup.group;

import com.alibaba.fastjson.JSON;
import org.apache.http.client.utils.URIBuilder;
import org.apache.log4j.Logger;
import org.springframework.data.mongodb.core.MongoTemplate;
import util.Constants;
import util.HttpUtil;
import util.UriBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;


import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

/**
 * Created by zhaokai on 16-11-16.
 */
public class GroupTool {

    private static Logger logger=Logger.getLogger(GroupTool.class);

    public static List<Group> getAllGroupsInNewYorkFromHttp() throws URISyntaxException, IOException {
        URIBuilder uriBuilder= UriBuilder.get();
        uriBuilder.setPath("/2/groups")
//                .addParameter("lat", Double.toString(Constants.NEW_YORK.LAT))
//                .addParameter("lon",Double.toString(Constants.NEW_YORK.LON))
//                .addParameter("radius",Integer.toString(Constants.SEARCH_RADIUS))
                .addParameter("zip",Constants.NEW_YORK.ZIP_CODE);

        URI uri=uriBuilder.build();

        logger.info("获取group："+uri.toString());

        String groupsJsonStr= JSON.parseObject(HttpUtil.getEntityString(uri)).getString("results");
        List<Group> groups=JSON.parseArray(groupsJsonStr,Group.class);

        logger.info("获取"+groups.size()+"个在纽约的group。");

        return groups;
    }

    public static void storeGroups(List<Group> groups, MongoTemplate mongoTemplate){
        for(Group group:groups){
            mongoTemplate.findAndRemove(query(where("groupId").is(group.getGroupId())),Group.class);
            mongoTemplate.insert(group);
            logger.info("插入group:" + group);
        }
    }

}
