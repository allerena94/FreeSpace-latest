package villanova.freespace;

public class Room {
    private String building;
    private String roomNumber;
    private String roomType;
    private String maxCapacity;
    private int count;
    private boolean groupBooked;

    public Room (String building, String roomNumber, String roomType, String maxCapacity, int count) {
        this.building = building;
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.maxCapacity = maxCapacity;
        this.count = count;
    }

    public String getBuilding() {
        return building;
    }

    public String getName() {
        return building + " " + roomNumber;
    }

    /*public void setCount(int count) {
        this.count = count;
    }*/

    public void incrementCount() { count++; }

    public int getCount() {
        return count;
    }

    /*public void setRoomType(String roomType) {
        this.roomType= roomType;
    }*/

    public String getRoomNumber() {
        return roomNumber;
    }

    public String getRoomType() {
        return roomType;
    }

    /*public void setMaxCapacity(String maxCapacity) {
        this.maxCapacity= maxCapacity;
    }*/

    public String getMaxCapacity() {
        return maxCapacity;
    }

    public String getStatus(){
        return Integer.toString(count)+"/"+maxCapacity;
    }
}

