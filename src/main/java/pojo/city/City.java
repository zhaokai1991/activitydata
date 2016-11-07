package pojo.city;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by zhaokai on 16-11-7.
 */
public class City {

    @JSONField(name = "id")
    private String cid;
    private String name;
    private String uid;

    public City(){}

    public City(String cid, String name, String uid) {
        this.cid = cid;
        this.name = name;
        this.uid = uid;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    @Override
    public String toString() {
        return "City{" +
                "cid='" + cid + '\'' +
                ", name='" + name + '\'' +
                ", uid='" + uid + '\'' +
                '}';
    }
}
