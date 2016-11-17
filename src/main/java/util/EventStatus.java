package util;

/**
 * Created by zhaokai on 16-11-17.
 */
public enum EventStatus {

    CANCELLED("cancelled"),
    UPCOMING("upcoming"),
    PAST("past"),
    PROPOSED("proposed"),
    SUGGESTED("suggested"),
    DRAFT("draft");

    private String value;

    EventStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
