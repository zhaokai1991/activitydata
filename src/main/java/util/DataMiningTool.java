package util;

import com.alibaba.fastjson.JSON;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import pojo.Category;
import util.UriBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

/**
 * Created by zhaokai on 16-10-26.
 */
public class DataMiningTool {

    private static CloseableHttpClient httpClient= HttpClients.createDefault();

    public static List<Category> getCategory() throws URISyntaxException, IOException {
        URIBuilder uriBuilder= UriBuilder.get();
        uriBuilder.setPath("/2/categories");
        URI uri=uriBuilder.build();
        HttpGet httpGet=new HttpGet(uri);

        CloseableHttpResponse httpResponse=httpClient.execute(httpGet);

        HttpEntity httpEntity=httpResponse.getEntity();

        String categoryResult= EntityUtils.toString(httpEntity);

        String categoryStr= JSON.parseObject(categoryResult).getString("results");
        List<Category> categoryList=JSON.parseArray(categoryStr,Category.class);

        return categoryList;
    }

}
