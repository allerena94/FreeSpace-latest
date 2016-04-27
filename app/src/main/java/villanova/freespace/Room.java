package villanova.freespace;

public class Room {
    private String name;
    private int count;
    private String roomType;
    private String maxCapacity;

    public Room (String name, String roomType, int count, String maxCapacity) {
        this.name = name;
        this.roomType = roomType;
        this.count = count;
        this.maxCapacity = maxCapacity;
    }
    public void setName(String name) {
        this.name= name;
    }

    public String getName() {
        return name;
    }

    public void setCount(int count) {
        this.count= count;
    }

    public void incrementCount() { count++; }

    public int getCount() {
        return count;
    }

    public void setRoomType(String roomType) {
        this.roomType= roomType;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setMaxCapacity(String maxCapacity) {
        this.maxCapacity= maxCapacity;
    }

    public String getMaxCapacity() {
        return maxCapacity;
    }

    public String getStatus(){
        return Integer.toString(count)+"/"+maxCapacity;
    }
}
