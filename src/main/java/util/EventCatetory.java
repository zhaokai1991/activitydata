package util;

/**
 * Created by zhaokai on 16-11-8.
 */
public enum EventCatetory {

    music(10),drama(11),exhibition(12),salon(13),
    party(14),sports(15),travel(16),commonweal(17),film(18),
    all(0),others(1);

    private int code;

    private EventCatetory(int code){
        this.code=code;
    }

    public int getCode(){
        return code;
    }
}
