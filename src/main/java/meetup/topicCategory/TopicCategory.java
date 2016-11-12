package meetup.topicCategory;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
 * Created by zhaokai on 16-11-12.
 */
public class TopicCategory {

    @JSONField(name = "id")
    private String topicCategoryId;

    @JSONField(name="category_ids")
    private List<String> categoryIds;//List of numeric category ids associated with this topic category

    private String name;
    private String shortname;

    public String getTopicCategoryId() {
        return topicCategoryId;
    }

    public void setTopicCategoryId(String topicCategoryId) {
        this.topicCategoryId = topicCategoryId;
    }

    public List<String> getCategoryIds() {
        return categoryIds;
    }

    public void setCategoryIds(List<String> categoryIds) {
        this.categoryIds = categoryIds;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortname() {
        return shortname;
    }

    public void setShortname(String shortname) {
        this.shortname = shortname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TopicCategory that = (TopicCategory) o;

        return topicCategoryId.equals(that.topicCategoryId);

    }

    @Override
    public int hashCode() {
        return topicCategoryId.hashCode();
    }

    @Override
    public String toString() {
        return "TopicCategory{" +
                "topicCategoryId='" + topicCategoryId + '\'' +
                ", categoryIds=" + categoryIds +
                ", name='" + name + '\'' +
                ", shortname='" + shortname + '\'' +
                '}';
    }
}


