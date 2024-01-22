package Single_Machine_Total_Weighted_Tardiness_Problem;
// Created: September 2022
import java.util.ArrayList;
import java.util.List;
public class Ant {
    private List<Job> tour;
    private boolean[] visited;
    public Ant(int numJobs) {
        tour = new ArrayList<>();
        visited = new boolean[numJobs];
    }
    public void visitJob(Job job) {
        tour.add(job);
        visited[job.getId()] = true;
    }
    public List<Job> getTour() {
        return tour;
    }
    public boolean hasVisited(int jobId) {
        return visited[jobId];
    }
}