import pojo.city.City;
import pojo.city.CityTool;
import pojo.event.Event;
import pojo.event.EventTool;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Set;

/**
 * Created by zhaokai on 16-11-9.
 */
public class ActivityDataDriver {

    public void run() throws IOException, URISyntaxException {
        //先获取并存储城市信息
        List<City> cities= CityTool.getCitiesFromHttp();
        CityTool.storeCities(cities);

        //获取所有活动信息并存储
        Set<Event> events= EventTool.getAllEventsFromHttp();
        EventTool.storeEvents(events);
    }

}
