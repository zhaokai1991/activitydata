package pojo.event;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import pojo.city.City;
import util.DayType;
import util.EventCatetory;
import util.UriBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by zhaokai on 16-11-8.
 */
public class EventTool {
    private static Logger logger=Logger.getLogger(EventTool.class);

    public static List<Event> getEventsFromHttp(String locId, DayType dayType, EventCatetory eventCatetory)
            throws URISyntaxException, IOException {
        URIBuilder uriBuilder= UriBuilder.get();
        uriBuilder.setPath("/event/list")
                .addParameter("loc",locId)
                .addParameter("day_type",dayType.toString())
                .addParameter("type",eventCatetory.toString());
        URI uri=uriBuilder.build();

        HttpGet httpGet=new HttpGet(uri);

        CloseableHttpClient httpClient= HttpClients.createDefault();
        CloseableHttpResponse httpResponse=httpClient.execute(httpGet);
        HttpEntity httpEntity=httpResponse.getEntity();
        String eventsResult= EntityUtils.toString(httpEntity);

        httpClient.close();

        String eventsJsonStr= JSON.parseObject(eventsResult).getString("events");
        JSONArray eventsJsonArray=JSON.parseArray(eventsJsonStr);

        List<Event> events=new ArrayList<Event>();

        ListIterator<Object> iterator=eventsJsonArray.listIterator();
        while(iterator.hasNext()){
            Object obj=iterator.next();
            JSONObject jsonObject=JSON.parseObject(obj.toString());

            JSONObject ownerJsonObject=jsonObject.getJSONObject("owner");
            jsonObject.put("ownerId",ownerJsonObject.get("id"));

            String geo=jsonObject.getString("geo");
            String[] geos=geo.split(" ");
            double latitude=Double.parseDouble(geos[0]);
            double longitude=Double.parseDouble(geos[1]);
            jsonObject.put("latitude",latitude);
            jsonObject.put("longitude",longitude);

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

        logger.info("获取"+events.size()+"个活动。");

        return events;
    }
}
