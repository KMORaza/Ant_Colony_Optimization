package Resource_Constrained_Project_Scheduling_Problem;
// Created: September 2022
import java.util.ArrayList;
import java.util.List;
public class Ant {
    private List<Task> tour;
    public Ant() {
        this.tour = new ArrayList<>();
    }
    public void addToTour(Task task) {
        this.tour.add(task);
    }
    public List<Task> getTour() {
        return tour;
    }
    @Override
    public String toString() {
        return "Ant{" +
                "tour=" + tour +
                '}';
    }
}