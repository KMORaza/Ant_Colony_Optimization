package Group_Shop_Scheduling_Problem;
// Created: September 2022
import java.util.ArrayList;
import java.util.List;
public class Colony {
    private List<Ant> ants;
    public Colony(int numAnts) {
        ants = new ArrayList<>();
        for (int i = 0; i < numAnts; i++) {
            ants.add(new Ant(i));
        }
    }
    public List<Ant> getAnts() {
        return ants;
    }
}