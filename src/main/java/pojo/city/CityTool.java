package pojo.city;

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
import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

/**
 * Created by zhaokai on 16-11-7.
 */
public class CityTool {

    private static Logger logger=Logger.getLogger(CityTool.class);

    public static List<City> getCitiesFromHttp() throws URISyntaxException, IOException {
        URIBuilder uriBuilder= UriBuilder.get();
        uriBuilder.setPath("/loc/list");
        URI uri=uriBuilder.build();

        String citiesResult= HttpUtil.getEntityString(uri);

        String citiesJsonStr= JSON.parseObject(citiesResult).getString("locs");

        List<City> cities=JSON.parseArray(citiesJsonStr,City.class);

        logger.info("获取"+cities.size()+"个城市。");

        return cities;
    }

    public static void storeCities(List<City> cities){
        MongoTemplate mongoTemplate= MongoUtil.getMongoTemplate();
        for(City city:cities){
            mongoTemplate.insert(city);
            logger.info("Insert:"+city);
        }
    }

    public static List<City> getAllCitiesFromDB(){
        MongoTemplate mongoTemplate= MongoUtil.getMongoTemplate();
        return mongoTemplate.findAll(City.class);
    }

    public static City getCityByCid(String cid){
        MongoTemplate mongoTemplate=MongoUtil.getMongoTemplate();
        return mongoTemplate.findOne(query(where("cid").is(cid)),City.class);
    }

}
