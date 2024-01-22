package Permutation_Flow_Shop_Problem;
// Created: September 2022
import java.util.ArrayList;
public class Colony {
    private ArrayList<Ant> ants;
    public Colony(int numAnts, int numJobs) {
        ants = new ArrayList<>(numAnts);
        for (int i = 0; i < numAnts; i++) {
            ants.add(new Ant(numJobs));
        }
    }
    public Ant getAnt(int index) {
        return ants.get(index);
    }
    public ArrayList<Ant> getAnts() {
        return ants;
    }
}