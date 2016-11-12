package douban;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;
import java.util.List;

/**
 * Created by zhaokai on 16-11-8.
 */
public class Event {

    @JSONField(name="id")
    private String eventId;
    private String ownerId;
    private String category;
    private String title;
//    private String content;
//    private String participant_count;
//    private String wisher_count;
    private List<String> tags;
    private Date begin_time;
    private Date end_time;
    private String loc_id;
    private String address;
    private double latitude;
    private double longitude;

    private List<String> wishers;
    private List<String> participants;

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

//    public String getContent() {
//        return content;
//    }
//
//    public void setContent(String content) {
//        this.content = content;
//    }

//    public String getParticipant_count() {
//        return participant_count;
//    }
//
//    public void setParticipant_count(String participant_count) {
//        this.participant_count = participant_count;
//    }
//
//    public String getWisher_count() {
//        return wisher_count;
//    }
//
//    public void setWisher_count(String wisher_count) {
//        this.wisher_count = wisher_count;
//    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public List<String> getWishers() {
        return wishers;
    }

    public void setWishers(List<String> wishers) {
        this.wishers = wishers;
    }

    public List<String> getParticipants() {
        return participants;
    }

    public void setParticipants(List<String> participants) {
        this.participants = participants;
    }

    public Date getBegin_time() {
        return begin_time;
    }

    public void setBegin_time(Date begin_time) {
        this.begin_time = begin_time;
    }

    public Date getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Date end_time) {
        this.end_time = end_time;
    }

    public String getLoc_id() {
        return loc_id;
    }

    public void setLoc_id(String loc_id) {
        this.loc_id = loc_id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "Event{" +
                "eventId='" + eventId + '\'' +
                ", ownerId='" + ownerId + '\'' +
                ", category='" + category + '\'' +
                ", title='" + title + '\'' +
                ", tags=" + tags +
                ", begin_time=" + begin_time +
                ", end_time=" + end_time +
                ", loc_id='" + loc_id + '\'' +
                ", address='" + address + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", wishers=" + wishers +
                ", participants=" + participants +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Event event = (Event) o;

        return eventId.equals(event.eventId);

    }

    @Override
    public int hashCode() {
        return eventId.hashCode();
    }
}
