package util;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;

/**
 * Created by zhaokai on 16-11-10.
 */
public class HttpUtil {

    public static String getEntityString(URI uri) throws IOException {
        HttpGet httpGet=new HttpGet(uri);

        CloseableHttpClient httpClient= HttpClients.createDefault();
        CloseableHttpResponse httpResponse=httpClient.execute(httpGet);
        HttpEntity httpEntity=httpResponse.getEntity();

        String result=EntityUtils.toString(httpEntity);

        System.out.println("HttpUtil:"+result);

        httpClient.close();

        return result;
    }

}
