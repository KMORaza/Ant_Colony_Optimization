package Partition_Problem;
// Created: September 2022
import java.util.Arrays;
public class AntColony {
    private int[] numbers;
    private int numAnts;
    private Ant[] ants;
    private int[] bestPartition;
    private int[] remainingItems;
    private double[][] pheromoneMatrix;
    private double rho = 0.2;
    private double Q = 1.0;
    private int numIterations = 200;
    public AntColony(int[] numbers, int numAnts, int numIterations) {
        this.numbers = numbers;
        this.numAnts = numAnts;
        this.numIterations = numIterations;
        this.ants = new Ant[numAnts];
        this.bestPartition = null;
        this.remainingItems = Arrays.copyOf(numbers, numbers.length);
        this.pheromoneMatrix = new double[numbers.length][numbers.length];
        initializeAnts();
        initializePheromones();
    }
    private void initializeAnts() {
        for (int i = 0; i < numAnts; i++) {
            ants[i] = new Ant(this, numbers.length);
        }
    }
    private void initializePheromones() {
        for (int i = 0; i < pheromoneMatrix.length; i++) {
            Arrays.fill(pheromoneMatrix[i], 1.0);
        }
    }
    public int[] solve() {
        for (int iteration = 0; iteration < numIterations; iteration++) {
            for (Ant ant : ants) {
                ant.constructSolution(remainingItems);
            }
            updateBestPartition();
            evaporatePheromones();
            depositPheromones();
            remainingItems = Arrays.copyOf(numbers, numbers.length);
        }
        return bestPartition;
    }
    private void updateBestPartition() {
        for (Ant ant : ants) {
            int[] partition = ant.getPartition();
            if (bestPartition == null || ant.getSolutionValue() > calculateValue(bestPartition)) {
                bestPartition = partition;
            }
        }
    }
    int calculateValue(int[] partition) {
        int sum1 = 0;
        int sum2 = 0;
        for (int i = 0; i < partition.length; i++) {
            if (partition[i] == 1) {
                sum1 += numbers[i];
            } else {
                sum2 += numbers[i];
            }
        }
        return Math.abs(sum1 - sum2);
    }
    private void evaporatePheromones() {
        for (int i = 0; i < pheromoneMatrix.length; i++) {
            for (int j = 0; j < pheromoneMatrix[i].length; j++) {
                pheromoneMatrix[i][j] *= (1.0 - rho);
            }
        }
    }
    private void depositPheromones() {
        for (Ant ant : ants) {
            int[] partition = ant.getPartition();
            double pheromoneDeposit = Q / (1.0 + calculateValue(partition));
            for (int i = 0; i < partition.length; i++) {
                if (partition[i] == 1) {
                    for (int j = 0; j < pheromoneMatrix[i].length; j++) {
                        pheromoneMatrix[i][j] += pheromoneDeposit;
                    }
                }
            }
        }
    }
    public int[] getRemainingItems() {
        return remainingItems;
    }
}