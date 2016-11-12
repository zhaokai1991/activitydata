package douban.user;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
 * Created by zhaokai on 16-11-8.
 */
public class User {

    @JSONField(name="id")
    private String userId;
    private String uid;
    private String name;
    private String loc_id;
    private String desc;
    private List<String> friends;

    public User(){}

    public User(String userId, String uid, String name) {
        this.userId = userId;
        this.uid = uid;
        this.name = name;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLoc_id() {
        return loc_id;
    }

    public void setLoc_id(String loc_id) {
        this.loc_id = loc_id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public List<String> getFriends() {
        return friends;
    }

    public void setFriends(List<String> friends) {
        this.friends = friends;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", uid='" + uid + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
