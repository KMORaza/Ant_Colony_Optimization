package Resource_Constrained_Project_Scheduling_Problem;
// Created: September 2022
public class Resource {
    private int id;
    public int availability;
    public Resource(int id, int availability) {
        this.id = id;
        this.availability = availability;
    }
    public int getId() {
        return id;
    }
    public int getAvailability() {
        return availability;
    }
    @Override
    public String toString() {
        return "Resource{" +
                "id=" + id +
                ", availability=" + availability +
                '}';
    }
}
