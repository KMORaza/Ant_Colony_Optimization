package Job_Shop_Scheduling_Problem;
// Created: September 2022
import java.util.ArrayList;
import java.util.List;
public class Ant {
    private List<Integer> tour;
    public Ant() {
        tour = new ArrayList<>();
    }
    public void addToTour(int jobId) {
        tour.add(jobId);
    }
    public List<Integer> getTour() {
        return tour;
    }
}