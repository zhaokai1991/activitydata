package meetup.event;

import com.alibaba.fastjson.annotation.JSONField;
import meetup.member.Member;
import meetup.group.Group;
import meetup.venue.Venue;

import java.util.List;

/**
 * Created by zhaokai on 16-11-17.
 *
 * API
 * /2/events:
 */
public class Event {

    @JSONField(name = "id")
    private String eventId;

    private long duration;

    private Group group;

    private String name;

    private long time;

    private Venue venue;

    private String status;

    private List<Member> participators;

    public List<Member> getParticipators() {
        return participators;
    }

    public void setParticipators(List<Member> participators) {
        this.participators = participators;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public Venue getVenue() {
        return venue;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
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

    @Override
    public String toString() {
        return "Event{" +
                "eventId='" + eventId + '\'' +
                ", time=" + time +
                ", status='" + status + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
