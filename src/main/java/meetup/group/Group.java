package meetup.group;

import com.alibaba.fastjson.annotation.JSONField;
import meetup.category.Category;
import meetup.topic.Topic;

import java.util.List;

/**
 * Created by zhaokai on 16-11-15.
 */
public class Group {

    private Category category;

    @JSONField(name = "created")
    private long creationTime;

    private String description;

    @JSONField(name = "id")
    private String groupId;

    private double lat;
    private double lon;

    @JSONField(name = "members")
    private int memberCount;

    private String name;

    @JSONField(name = "primary_topic")
    private Topic primaryTopic;

    private List<Topic> topics;
    private String urlname;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public long getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(long creationTime) {
        this.creationTime = creationTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
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

    public int getMemberCount() {
        return memberCount;
    }

    public void setMemberCount(int memberCount) {
        this.memberCount = memberCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Topic getPrimaryTopic() {
        return primaryTopic;
    }

    public void setPrimaryTopic(Topic primaryTopic) {
        this.primaryTopic = primaryTopic;
    }

    public List<Topic> getTopics() {
        return topics;
    }

    public void setTopics(List<Topic> topics) {
        this.topics = topics;
    }

    public String getUrlname() {
        return urlname;
    }

    public void setUrlname(String urlname) {
        this.urlname = urlname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Group group = (Group) o;

        if (!groupId.equals(group.groupId)) return false;
        return urlname.equals(group.urlname);

    }

    @Override
    public int hashCode() {
        int result = groupId.hashCode();
        result = 31 * result + urlname.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Group{" +
                "creationTime=" + creationTime +
                ", groupId='" + groupId + '\'' +
                ", lat=" + lat +
                ", lon=" + lon +
                ", memberCount=" + memberCount +
                ", name='" + name + '\'' +
                ", urlname='" + urlname + '\'' +
                '}';
    }
}
