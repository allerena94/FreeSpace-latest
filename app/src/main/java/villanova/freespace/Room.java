package villanova.freespace;

/**
 * Created by david_000 on 4/25/2016.
 */
public class Room {
    private String building;
    private int currcap;
    private int maxcap;
    private String room;
    private String type;
    public Room() {
    }
    public String getBuilding(){
        return building;
    }
    public int getCurrcap(){
        return currcap;
    }
    public int getMaxcap(){
        return maxcap;
    }
    public String getRoom(){
        return room;
    }
    public String getType(){
        return type;
    }
}
