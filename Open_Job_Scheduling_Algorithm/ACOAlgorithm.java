package Open_Job_Scheduling_Algorithm;
// Created: September 2022
import java.util.List;
import java.util.Random;
public class ACOAlgorithm {
    private AntColony antColony;
    private double[][] pheromoneMatrix;
    private double[][] visibilityMatrix;
    private double alpha = 1.0;
    private double beta = 2.0;
    private double evaporationRate = 0.5;
    private double initialPheromone = 1.0;
    private int numMachines;
    public ACOAlgorithm(AntColony antColony, int numMachines) {
        this.antColony = antColony;
        int numJobs = antColony.getAnts().get(0).getTour().size();
        this.numMachines = numMachines;
        pheromoneMatrix = new double[numJobs][numMachines];
        for (int i = 0; i < numJobs; i++) {
            for (int j = 0; j < numMachines; j++) {
                pheromoneMatrix[i][j] = initialPheromone;
            }
        }
        visibilityMatrix = new double[numJobs][numMachines];
        for (int i = 0; i < numJobs; i++) {
            for (int j = 0; j < numMachines; j++) {
                visibilityMatrix[i][j] = 1.0;
            }
        }
    }
    public void runACO() {
        int numIterations = 100;
        for (int iteration = 0; iteration < numIterations; iteration++) {
            constructSolutions();
            updatePheromones();
        }
    }
    private void constructSolutions() {
        for (Ant ant : antColony.getAnts()) {
            List<Integer> unvisitedJobs = ant.getUnvisitedJobs();
            while (!unvisitedJobs.isEmpty()) {
                int randomJob = unvisitedJobs.remove(new Random().nextInt(unvisitedJobs.size()));
                ant.visit(randomJob);
            }
        }
    }
    private void updatePheromones() {
        for (int i = 0; i < pheromoneMatrix.length; i++) {
            for (int j = 0; j < pheromoneMatrix[0].length; j++) {
                pheromoneMatrix[i][j] *= (1.0 - evaporationRate);
                pheromoneMatrix[i][j] += 0.1;
            }
        }
    }
}