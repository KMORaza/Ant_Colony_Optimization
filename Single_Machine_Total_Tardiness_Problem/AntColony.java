package Single_Machine_Total_Tardiness_Problem;
// Created: September 2022
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
public class AntColony {
    private List<Ant> ants;
    private double[][] pheromones;
    private List<Job> jobs;
    public AntColony(List<Job> jobs, int antCount, double alpha, double beta) {
        this.jobs = jobs;
        int maxJobID = jobs.stream().mapToInt(Job::getId).max().orElse(0);
        this.pheromones = new double[maxJobID + 1][maxJobID + 1];
        this.ants = new ArrayList<>();
        for (int i = 0; i < antCount; i++) {
            ants.add(new Ant(pheromones, alpha, beta));
        }
    }
    public void runACO(int iterations) {
        for (int i = 0; i < iterations; i++) {
            for (Ant ant : ants) {
                ant.constructSolution(jobs);
            }
            updatePheromones();
            Ant bestAnt = Collections.min(ants, Comparator.comparingInt(a -> calculateTotalTardiness(a.getSolution())));
            updatePheromones();
        }
    }
    private void updatePheromones() {
        for (int i = 0; i < pheromones.length; i++) {
            for (int j = 0; j < pheromones[i].length; j++) {
                pheromones[i][j] *= 0.9;  // Evaporation factor
            }
        }
        for (Ant ant : ants) {
            List<Job> solution = ant.getSolution();
            double pheromoneToAdd = 1.0 / calculateTotalTardiness(solution);
            for (int i = 0; i < solution.size() - 1; i++) {
                int from = solution.get(i).getId();
                int to = solution.get(i + 1).getId();
                pheromones[from][to] += pheromoneToAdd;
            }
        }
    }
    protected int calculateTotalTardiness(List<Job> solution) {
        int totalTardiness = 0;
        int currentTime = 0;
        for (Job job : solution) {
            currentTime += job.getProcessingTime();
            int tardiness = Math.max(0, currentTime - job.getDueDate());
            totalTardiness += tardiness;
        }
        return totalTardiness;
    }
    public Ant getBestAnt() {
        return Collections.min(ants, Comparator.comparingInt(a -> calculateTotalTardiness(a.getSolution())));
    }
}