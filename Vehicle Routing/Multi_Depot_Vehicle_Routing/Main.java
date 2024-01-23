package Multi_Depot_Vehicle_Routing;
// Created: September 2022
import java.util.ArrayList;
import java.util.List;
public class Main {
    public static void main(String[] args) {
        List<Ant> ants = initializeAnts();
        double[][] distanceMatrix = initializeDistanceMatrix();
        Optimizer aco = new Optimizer(ants, distanceMatrix);
        int numIterations = 100;
        aco.runACO(numIterations);
        Ant bestAnt = findBestAnt(ants, distanceMatrix);
        System.out.println("Best Tour: " + bestAnt.getTour());
        System.out.println("Tour Length: " + calculateTourLength(bestAnt.getTour(), distanceMatrix));
        System.out.println("Ant Pheromones: ");
        printPheromones(aco.getPheromoneMatrix());
    }
    private static List<Ant> initializeAnts() {
        List<Ant> ants = new ArrayList<>();
        int numAnts = 5;
        for (int i = 0; i < numAnts; i++) {
            ants.add(new Ant(i));
        }
        return ants;
    }
    private static double[][] initializeDistanceMatrix() {
        int numLocations = 4; // Adjust as needed
        double[][] distanceMatrix = new double[numLocations][numLocations];
        distanceMatrix[0][1] = 5;
        distanceMatrix[0][2] = 10;
        distanceMatrix[0][3] = 15;
        distanceMatrix[1][0] = 5;
        distanceMatrix[1][2] = 8;
        distanceMatrix[1][3] = 9;
        distanceMatrix[2][0] = 10;
        distanceMatrix[2][1] = 8;
        distanceMatrix[2][3] = 20;
        distanceMatrix[3][0] = 15;
        distanceMatrix[3][1] = 9;
        distanceMatrix[3][2] = 20;
        return distanceMatrix;
    }
    private static Ant findBestAnt(List<Ant> ants, double[][] distanceMatrix) {
        Ant bestAnt = ants.get(0);
        for (Ant ant : ants) {
            if (calculateTourLength(ant.getTour(), distanceMatrix) < calculateTourLength(bestAnt.getTour(), distanceMatrix)) {
                bestAnt = ant;
            }
        }
        return bestAnt;
    }
    private static double calculateTourLength(List<Customer> tour, double[][] distanceMatrix) {
        double length = 0.0;
        for (int i = 0; i < tour.size() - 1; i++) {
            int from = tour.get(i).getId();
            int to = tour.get(i + 1).getId();
            length += distanceMatrix[from][to];
        }
        return length;
    }
    private static void printPheromones(double[][] pheromoneMatrix) {
        for (int i = 0; i < pheromoneMatrix.length; i++) {
            for (int j = 0; j < pheromoneMatrix[i].length; j++) {
                System.out.print(pheromoneMatrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}