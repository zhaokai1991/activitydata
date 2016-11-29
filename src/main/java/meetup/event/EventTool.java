package meetup.event;

import com.alibaba.fastjson.JSON;
import meetup.group.Group;
import org.apache.http.client.utils.URIBuilder;
import org.apache.log4j.Logger;
import org.springframework.data.mongodb.core.MongoTemplate;
import util.*;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

/**
 * Created by zhaokai on 16-11-17.
 */
public class EventTool {

    private static Logger logger=Logger.getLogger(EventTool.class);


    //获取某个特定group在特定时间段组织的活动
    public static List<Event> getEventsHostedByAGroup(Group group, EventStatus eventStatus) throws URISyntaxException, IOException {
        URIBuilder uriBuilder= UriBuilder.get();
        uriBuilder.setPath("/"+group.getUrlname()+"/events")
                .addParameter("status", eventStatus.getValue());

        URI uri=uriBuilder.build();

        logger.info("获取event："+uri.toString());

        List<Event> events= JSON.parseArray(HttpUtil.getEntityString(uri),Event.class);

        logger.info("获取"+events.size()+"个event。");

        return events;
    }

    //获取某个特定group的过去和未来的所有活动
    public static List<Event> getPastAndUpcomingEventsHostedByAGroup(Group group) throws IOException, URISyntaxException {
        List<Event> pastEvents=getEventsHostedByAGroup(group,EventStatus.PAST);
        List<Event> upcomingEvents=getEventsHostedByAGroup(group,EventStatus.UPCOMING);

        pastEvents.addAll(upcomingEvents);

        return pastEvents;
    }

    public static void storeEvents(List<Event> events, MongoTemplate mongoTemplate){
        for(Event event:events){
            mongoTemplate.remove(query(where(PojoID.EVENT_ID).is(event.getEventId())),Event.class);
            mongoTemplate.insert(event);
            logger.info("插入event:"+event);
        }
    }

    //获取纽约所有group组织的活动
    public static void getAndStoreEventsInNewYork(MongoTemplate mongoTemplate) throws IOException, URISyntaxException {
        List<Group> groupList=mongoTemplate.findAll(Group.class);
        for(Group group:groupList){
            if(mongoTemplate.exists(query(where("group").is(group)),Event.class)) {
                logger.info("跳过group::"+group.getName());
                continue;
            }
            try {
                storeEvents(getPastAndUpcomingEventsHostedByAGroup(group),mongoTemplate);
                Thread.sleep(5* Constants.SECOND);
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }
        }
    }

}
