package pojo.city;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by zhaokai on 16-11-7.
 */
public class City {

    @JSONField(name = "id")
    private String cityId;
    private String name;
    private String uid;

    public City(){}

    public City(String cityId, String name, String uid) {
        this.cityId = cityId;
        this.name = name;
        this.uid = uid;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
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
                "cityId='" + cityId + '\'' +
                ", name='" + name + '\'' +
                ", uid='" + uid + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        City city = (City) o;

        return cityId.equals(city.cityId);

    }

    @Override
    public int hashCode() {
        return cityId.hashCode();
    }
}
