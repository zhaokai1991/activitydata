package meetup.venue;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by zhaokai on 16-11-17.
 */
public class Venue {

    @JSONField(name = "id")
    private String venueId;

    private String name;

    private double lat;

    private double lon;

    private String city;

    public String getVenueId() {
        return venueId;
    }

    public void setVenueId(String venueId) {
        this.venueId = venueId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Venue venue = (Venue) o;

        return venueId.equals(venue.venueId);

    }

    @Override
    public int hashCode() {
        return venueId.hashCode();
    }

    @Override
    public String toString() {
        return "Venue{" +
                "venueId='" + venueId + '\'' +
                ", name='" + name + '\'' +
                ", lat=" + lat +
                ", lon=" + lon +
                ", city='" + city + '\'' +
                '}';
    }
}
