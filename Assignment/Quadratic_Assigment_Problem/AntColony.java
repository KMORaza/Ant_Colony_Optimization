package Quadratic_Assigment_Problem;
// Created: September 2022
import java.util.Arrays;
import java.util.Random;
public class AntColony {
    private int numOfLocations;
    private int[][] distances;
    private int[][] flows;
    private int numOfAnts;
    private double evaporationRate;
    private double pheromoneDepositRate;
    private double[][] pheromones;
    public AntColony(int numOfLocations, int[][] distances, int[][] flows,
                     int numOfAnts, double evaporationRate, double pheromoneDepositRate) {
        this.numOfLocations = numOfLocations;
        this.distances = distances;
        this.flows = flows;
        this.numOfAnts = numOfAnts;
        this.evaporationRate = evaporationRate;
        this.pheromoneDepositRate = pheromoneDepositRate;
        this.pheromones = initializePheromones();
    }
    public int[] solve() {
        int maxIterations = 100;
        int[] globalBestSolution = null;
        double globalBestObjectiveValue = Double.POSITIVE_INFINITY;
        for (int iteration = 0; iteration < maxIterations; iteration++) {
            int[][] antSolutions = new int[numOfAnts][numOfLocations];
            double[] antObjectives = new double[numOfAnts];
            for (int antIndex = 0; antIndex < numOfAnts; antIndex++) {
                Ant ant = new Ant(numOfLocations, distances, flows, pheromones);
                ant.constructSolution();
                antSolutions[antIndex] = ant.getTour();
                antObjectives[antIndex] = calculateObjectiveValue(antSolutions[antIndex]);
                if (antObjectives[antIndex] < globalBestObjectiveValue) {
                    globalBestObjectiveValue = antObjectives[antIndex];
                    globalBestSolution = Arrays.copyOf(antSolutions[antIndex], numOfLocations);
                }
            }
            updatePheromones(antSolutions, antObjectives);
        }
        return globalBestSolution;
    }
    public double calculateObjectiveValue(int[] solution) {
        double objectiveValue = 0.0;
        for (int i = 0; i < numOfLocations; i++) {
            for (int j = 0; j < numOfLocations; j++) {
                objectiveValue += pheromones[i][j] * distances[solution[i]][solution[j]] * flows[i][j];
            }
        }
        return objectiveValue;
    }
    private double[][] initializePheromones() {
        double initialPheromone = 0.1;
        double[][] pheromones = new double[numOfLocations][numOfLocations];
        for (int i = 0; i < numOfLocations; i++) {
            for (int j = 0; j < numOfLocations; j++) {
                pheromones[i][j] = initialPheromone;
            }
        }
        return pheromones;
    }
    private void updatePheromones(int[][] antSolutions, double[] antObjectives) {
        double evaporationRate = 0.5;
        double pheromoneDeposit = 1.0;
        for (int i = 0; i < numOfLocations; i++) {
            for (int j = 0; j < numOfLocations; j++) {
                pheromones[i][j] *= (1 - evaporationRate);
            }
        }
        for (int antIndex = 0; antIndex < numOfAnts; antIndex++) {
            for (int i = 0; i < numOfLocations - 1; i++) {
                int currentLocation = antSolutions[antIndex][i];
                int nextLocation = antSolutions[antIndex][i + 1];
                pheromones[currentLocation][nextLocation] += pheromoneDeposit / antObjectives[antIndex];
            }
        }
    }
}