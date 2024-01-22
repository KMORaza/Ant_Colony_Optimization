package Permutation_Flow_Shop_Problem;
// Created: September 2022
import java.util.ArrayList;
public class Ant {
    private ArrayList<Integer> tour;
    private boolean[] visited;
    public Ant(int numJobs) {
        tour = new ArrayList<>(numJobs);
        visited = new boolean[numJobs];
        for (int i = 0; i < numJobs; i++) {
            visited[i] = false;
        }
    }
    public void visit(int jobIndex) {
        tour.add(jobIndex);
        visited[jobIndex] = true;
    }
    public boolean hasVisited(int jobIndex) {
        return visited[jobIndex];
    }
    public ArrayList<Integer> getTour() {
        return tour;
    }
}