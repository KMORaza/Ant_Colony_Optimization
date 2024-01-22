package Open_Job_Scheduling_Algorithm;
// Created: September 2022
import java.util.ArrayList;
import java.util.List;
// Created: September 2022
public class Ant {
    private List<Integer> tour;
    private boolean[] visited;
    private List<Integer> unvisitedJobs;
    public Ant(int numJobs) {
        tour = new ArrayList<>();
        visited = new boolean[numJobs];
        unvisitedJobs = new ArrayList<>();
        for (int i = 0; i < numJobs; i++) {
            unvisitedJobs.add(i);
        }
    }
    public List<Integer> getTour() {
        return tour;
    }
    public void visit(int job) {
        tour.add(job);
        visited[job] = true;
        unvisitedJobs.remove(Integer.valueOf(job));
    }
    public boolean isVisited(int job) {
        return visited[job];
    }
    public boolean isTourComplete(int numJobs) {
        return tour.size() == numJobs;
    }
    public List<Integer> getUnvisitedJobs() {
        return unvisitedJobs;
    }
}