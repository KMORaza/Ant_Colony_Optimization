package Quadratic_Assigment_Problem;
// Created: September 2022
import java.util.Random;
public class Ant {
    private int numOfLocations;
    private int[] tour;
    private boolean[] visited;
    private int[][] distances;
    private int[][] flows;
    private double[][] pheromones;
    private Random random;
    public Ant(int numOfLocations, int[][] distances, int[][] flows, double[][] pheromones) {
        this.numOfLocations = numOfLocations;
        this.distances = distances;
        this.flows = flows;
        this.pheromones = pheromones;
        this.tour = new int[numOfLocations];
        this.visited = new boolean[numOfLocations];
        this.random = new Random();
    }
    public void constructSolution() {
        int initialLocation = random.nextInt(numOfLocations);
        tour[0] = initialLocation;
        visited[initialLocation] = true;
        for (int i = 1; i < numOfLocations; i++) {
            int nextLocation = chooseNextLocation(tour[i - 1]);
            tour[i] = nextLocation;
            visited[nextLocation] = true;
        }
    }
    private int chooseNextLocation(int currentLocation) {
        double[] probabilities = calculateProbabilities(currentLocation);
        double randomValue = random.nextDouble();
        double cumulativeProbability = 0.0;
        for (int i = 0; i < numOfLocations; i++) {
            cumulativeProbability += probabilities[i];
            if (randomValue <= cumulativeProbability && !visited[i]) {
                return i;
            }
        }
        for (int i = 0; i < numOfLocations; i++) {
            if (!visited[i]) {
                return i;
            }
        }
        return -1;
    }
    private double[] calculateProbabilities(int currentLocation) {
        double[] probabilities = new double[numOfLocations];
        double total = 0.0;
        for (int i = 0; i < numOfLocations; i++) {
            if (!visited[i]) {
                probabilities[i] = Math.pow(pheromones[currentLocation][i], 2.0)
                        * Math.pow(1.0 / distances[currentLocation][i], 5.0);
                total += probabilities[i];
            }
        }
        for (int i = 0; i < numOfLocations; i++) {
            probabilities[i] /= total;
        }
        return probabilities;
    }
    public int[] getTour() {
        return tour;
    }
}