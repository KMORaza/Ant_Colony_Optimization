package Resource_Constrained_Project_Scheduling_Problem;
// Created: September 2022
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class AntColony {
    private List<Ant> ants;
    private double[][] pheromones;
    public AntColony(List<Ant> ants) {
        this.ants = ants;
        int numTasks = ants.get(0).getTour().size();
        this.pheromones = new double[numTasks][numTasks];
        for (int i = 0; i < numTasks; i++) {
            Arrays.fill(pheromones[i], 1.0);
        }
    }
    public void updatePheromones() {
        double evaporationRate = 0.1;
        for (int i = 0; i < pheromones.length; i++) {
            for (int j = 0; j < pheromones[i].length; j++) {
                pheromones[i][j] *= (1 - evaporationRate);
            }
        }
        double depositionRate = 1.0;
        for (Ant ant : ants) {
            List<Task> antTour = ant.getTour();
            for (int i = 0; i < antTour.size() - 1; i++) {
                int from = antTour.get(i).getId();
                int to = antTour.get(i + 1).getId();
                pheromones[from][to] += depositionRate;
                pheromones[to][from] += depositionRate;
            }
        }
    }
    public List<Ant> getAnts() {
        return ants;
    }
    public double[][] getPheromones() {
        return pheromones;
    }
    @Override
    public String toString() {
        return "AntColony{" +
                "ants=" + ants +
                ", pheromones=" + Arrays.deepToString(pheromones) +
                '}';
    }
}