package Open_Job_Scheduling_Algorithm;
// Created: September 2022
import java.util.ArrayList;
import java.util.List;
public class AntColony {
    private int numAnts;
    private List<Ant> ants;
    public AntColony(int numAnts, int numJobs) {
        this.numAnts = numAnts;
        ants = new ArrayList<>();
        for (int i = 0; i < numAnts; i++) {
            ants.add(new Ant(numJobs));
        }
    }
    public List<Ant> getAnts() {
        return ants;
    }
    public int getNumAnts() {
        return numAnts;
    }
}