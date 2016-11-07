package util;

import org.apache.http.client.utils.URIBuilder;

/**
 * Created by zhaokai on 16-10-26.
 */
public class UriBuilder {

    public static URIBuilder get(){
        return new URIBuilder()
                .setScheme("https")
                .setHost("api.douban.com/v2");
    }

}
