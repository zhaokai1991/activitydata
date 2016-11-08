import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;
import pojo.city.CityTool;
import pojo.event.Event;
import pojo.event.EventTool;
import util.DayType;
import util.EventCatetory;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

/**
 * Created by zhaokai on 16-10-26.
 */
public class Main {

    public static void main(String[] args) throws IOException, URISyntaxException {
        List<Event> events= EventTool.getEventsFromHttp("108288", DayType.future, EventCatetory.all);

        for(Event event:events){
            System.out.println("********************************");
            System.out.println(event);
        }
    }

}
