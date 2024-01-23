package Stochastic_Vehicle_Routing;
// Created: September 2022
import java.util.ArrayList;
import java.util.List;
public class Main {
    public static void main(String[] args) {
        StochasticVRP problem = createStochasticVRPInstance();
        int numAnts = 5;
        double alpha = 1.0;
        double beta = 2.0;
        double rho = 0.5;
        AntColony antColony = new AntColony(problem, numAnts, alpha, beta, rho);
        int numIterations = 100;
        antColony.runACO(numIterations);
        System.out.println("Final Pheromone Matrix:");
        printPheromoneMatrix(antColony.getPheromoneMatrix());
        System.out.println("Best Tour: " + antColony.getBestTour());
        System.out.println("Best Cost: " + antColony.getBestCost());
    }
    private static void printPheromoneMatrix(double[][] pheromoneMatrix) {
        for (int i = 0; i < pheromoneMatrix.length; i++) {
            for (int j = 0; j < pheromoneMatrix[i].length; j++) {
                System.out.print(pheromoneMatrix[i][j] + " ");
            }
            System.out.println();
        }
    }
    private static StochasticVRP createStochasticVRPInstance() {
        int numNodes = 10;
        int numVehicles = 3;
        List<Double>[] distanceMatrix = initializeDistanceMatrix();
        return new StochasticVRP(numNodes, numVehicles, distanceMatrix);
    }
    private static List<Double>[] initializeDistanceMatrix() {
        List<Double>[] distanceMatrix = new List[10];
        for (int i = 0; i < 10; i++) {
            distanceMatrix[i] = new ArrayList<>();
            for (int j = 0; j < 10; j++) {
                distanceMatrix[i].add(Math.random() * 10);
            }
        }
        return distanceMatrix;
    }
}