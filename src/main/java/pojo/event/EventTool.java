package pojo.event;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.client.utils.URIBuilder;
import org.apache.log4j.Logger;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.util.StringUtils;
import pojo.city.City;
import pojo.city.CityTool;
import util.*;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

/**
 * Created by zhaokai on 16-11-8.
 */
public class EventTool {
    private static Logger logger=Logger.getLogger(EventTool.class);

    public static Set<Event> getAllEventsFromHttp() throws IOException, URISyntaxException {
        Set<Event> allEvents=new HashSet<Event>();
        List<City> allCities= CityTool.getAllCitiesFromDB();

        for(City city:allCities){
            for(DayType dayType:DayType.values()){
                allEvents.addAll(getEventsFromHttp(city.getCityId(),dayType,EventCatetory.all));
            }
        }

        return allEvents;
    }

    public static Set<Event> getEventsFromHttp(String locId, DayType dayType, EventCatetory eventCatetory)
            throws URISyntaxException, IOException {
        URIBuilder uriBuilder= UriBuilder.get();
        uriBuilder.setPath("/event/list")
                .addParameter("loc",locId)
                .addParameter("day_type",dayType.toString())
                .addParameter("type",eventCatetory.toString());
        URI uri=uriBuilder.build();

        logger.info("获取活动："+uri.toString());

        String eventsResult= HttpUtil.getEntityString(uri);

        Set<Event> events=new HashSet<Event>();

        String eventsJsonStr= JSON.parseObject(eventsResult).getString("events");

        if(!StringUtils.isEmpty(eventsJsonStr)) {
            JSONArray eventsJsonArray = JSON.parseArray(eventsJsonStr);

            ListIterator<Object> iterator = eventsJsonArray.listIterator();
            while (iterator.hasNext()) {
                Object obj = iterator.next();
                JSONObject jsonObject = JSON.parseObject(obj.toString());

                JSONObject ownerJsonObject = jsonObject.getJSONObject("owner");
                jsonObject.put("ownerId", ownerJsonObject.get("id"));

                String geo = jsonObject.getString("geo");
                String[] geos = geo.split(" ");
                double latitude = Double.parseDouble(geos[0]);
                double longitude = Double.parseDouble(geos[1]);
                jsonObject.put("latitude", latitude);
                jsonObject.put("longitude", longitude);

                String tags = jsonObject.getString("tags");
                String[] tagsArray = tags.split(",");
                List<String> tagsList = Arrays.asList(tagsArray);
                jsonObject.put("tags", tagsList);

                String eventId = jsonObject.getString("id");
                List<String> participantsList = getParticipantsOrWishersFromHttp(eventId, EventUserType.participants);
                List<String> wishersList = getParticipantsOrWishersFromHttp(eventId, EventUserType.wishers);
                jsonObject.put("participants", participantsList);
                jsonObject.put("wishers", wishersList);

                jsonObject.remove("image");
                jsonObject.remove("adalt_url");
                jsonObject.remove("owner");
                jsonObject.remove("alt");
                jsonObject.remove("geo");
                jsonObject.remove("price_range");
                jsonObject.remove("has_ticket");
                jsonObject.remove("image_lmobile");
                jsonObject.remove("image_hlarge");

                events.add(jsonObject.toJavaObject(Event.class));
            }
        }

        logger.info("获取"+events.size()+"个活动。");

        return events;
    }

    public static List<String> getParticipantsOrWishersFromHttp(String eventId,EventUserType eventUserType)
            throws URISyntaxException, IOException {
        URIBuilder uriBuilder= UriBuilder.get();
        uriBuilder.setPath("/event/"+eventId+"/"+eventUserType.toString());

        URI uri=uriBuilder.build();

        String participantsResult= HttpUtil.getEntityString(uri);

        List<String> users = new ArrayList<String>();
        String usersJsonStr= JSON.parseObject(participantsResult).getString("users");

        if(!StringUtils.isEmpty(usersJsonStr)) {
            JSONArray usersJsonArray = JSON.parseArray(usersJsonStr);

            ListIterator<Object> iterator = usersJsonArray.listIterator();
            while (iterator.hasNext()) {
                Object obj = iterator.next();
                JSONObject jsonObject = JSON.parseObject(obj.toString());

                String participantId = jsonObject.getString("id");
                users.add(participantId);
            }
        }

        return users;
    }

    public static void storeEvents(Set<Event> events){
        MongoTemplate mongoTemplate= MongoUtil.getMongoTemplate();
        for(Event event:events){
            if(!mongoTemplate.exists(query(where("eventId").is(event.getEventId())),Event.class)) {
                mongoTemplate.insert(event);
                logger.info("Insert:" + event);
            }
        }
    }

}

enum EventUserType{
    participants,wishers
}
