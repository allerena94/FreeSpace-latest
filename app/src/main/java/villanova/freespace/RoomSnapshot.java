package villanova.freespace;

public class RoomSnapshot {
    private String building;
    private int roomNumber;
    private String roomType;
    private int maxCapacity;
    private int count;
    private int groupBooked;

    public RoomSnapshot() { }

    public String getBuilding() {
        return building;
    }

    public int getCount() {
        return count;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public String getRoomType() {
        return roomType;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public int getGroupBooked() {
        return groupBooked;
    }
}