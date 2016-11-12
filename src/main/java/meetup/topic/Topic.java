package meetup.topic;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by zhaokai on 16-11-12.
 */
public class Topic {

    @JSONField(name = "id")
    private String topicId;
    private String name;
    private String urlkey;

    public String getTopicId() {
        return topicId;
    }

    public void setTopicId(String topicId) {
        this.topicId = topicId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrlkey() {
        return urlkey;
    }

    public void setUrlkey(String urlkey) {
        this.urlkey = urlkey;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Topic topic = (Topic) o;

        return topicId.equals(topic.topicId);

    }

    @Override
    public int hashCode() {
        return topicId.hashCode();
    }

    @Override
    public String toString() {
        return "Topic{" +
                "topicId='" + topicId + '\'' +
                ", name='" + name + '\'' +
                ", urlkey='" + urlkey + '\'' +
                '}';
    }
}
