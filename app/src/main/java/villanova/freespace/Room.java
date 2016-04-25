package villanova.freespace;

public class Room {
    private String name;
    private String count;
    private String maxCapacity;

    public Room (String name, String count, String maxCapacity) {
        this.name = name;
        this.count = count;
        this.maxCapacity = maxCapacity;
    }
    public void setName(String name) {
        this.name= name;
    }
    public String getName() {
        return name;
    }
    public void setCount(String count) {
        this.count= count;
    }
    /*public String getCount() {
        return count;
    }*/
    public void setMaxCapacity(String maxCapacity) {
        this.maxCapacity= maxCapacity;
    }
    /*public String getMaxCapacity() {
        return maxCapacity;
    }*/
    public String getStatus(){
        return count+"/"+maxCapacity;
    }
}
