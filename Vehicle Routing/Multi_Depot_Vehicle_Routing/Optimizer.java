package Multi_Depot_Vehicle_Routing;
// Created: September 2022
import java.util.List;
public class Optimizer {
    private List<Ant> ants;
    private double[][] pheromoneMatrix;
    private double[][] visibilityMatrix;
    private double[][] distanceMatrix;
    private double evaporationRate = 0.5;
    private double alpha = 1.0;
    private double beta = 2.0;
    public Optimizer(List<Ant> ants, double[][] distanceMatrix) {
        this.ants = ants;
        this.distanceMatrix = distanceMatrix;
        initializePheromones();
        initializeVisibility();
    }
    public double[][] getPheromoneMatrix() {
        return pheromoneMatrix;
    }
    public void runACO(int iterations) {
        for (int i = 0; i < iterations; i++) {
            for (Ant ant : ants) {
                constructSolution(ant);
            }
            updatePheromones();
        }
    }
    private void initializePheromones() {
        int numLocations = distanceMatrix.length;
        pheromoneMatrix = new double[numLocations][numLocations];
        double initialPheromone = 1.0 / numLocations;
        for (int i = 0; i < numLocations; i++) {
            for (int j = 0; j < numLocations; j++) {
                pheromoneMatrix[i][j] = initialPheromone;
            }
        }
    }
    private void initializeVisibility() {
        int numLocations = distanceMatrix.length;
        visibilityMatrix = new double[numLocations][numLocations];
        for (int i = 0; i < numLocations; i++) {
            for (int j = 0; j < numLocations; j++) {
                if (i != j) {
                    visibilityMatrix[i][j] = 1.0 / distanceMatrix[i][j];
                } else {
                    visibilityMatrix[i][j] = 0.0;
                }
            }
        }
    }
    private void constructSolution(Ant ant) {
        int numLocations = distanceMatrix.length;
        boolean[] visited = new boolean[numLocations];
        int currentLocation = ant.getTour().isEmpty() ? 0 : ant.getTour().get(ant.getTour().size() - 1).getId();
        visited[currentLocation] = true;
        for (int step = 0; step < numLocations - 1; step++) {
            int nextLocation = selectNextLocation(currentLocation, visited);
            ant.addToTour(getCustomerById(nextLocation));
            visited[nextLocation] = true;
            currentLocation = nextLocation;
        }
    }
    private void updatePheromones() {
        evaporatePheromones();
        depositPheromones();
    }
    private void evaporatePheromones() {
        int numLocations = pheromoneMatrix.length;
        for (int i = 0; i < numLocations; i++) {
            for (int j = 0; j < numLocations; j++) {
                pheromoneMatrix[i][j] *= (1.0 - evaporationRate);
            }
        }
    }
    private double[] calculateProbabilities(int currentLocation, boolean[] visited) {
        int numLocations = distanceMatrix.length;
        double[] probabilities = new double[numLocations];
        double totalProbability = 0.0;
        for (int i = 0; i < numLocations; i++) {
            if (!visited[i]) {
                probabilities[i] = Math.pow(pheromoneMatrix[currentLocation][i], alpha) * Math.pow(visibilityMatrix[currentLocation][i], beta);
                totalProbability += probabilities[i];
            } else {
                probabilities[i] = 0.0;
            }
        }
        for (int i = 0; i < numLocations; i++) {
            if (totalProbability > 0) {
                probabilities[i] /= totalProbability;
            } else {
                probabilities[i] = 1.0 / numLocations;
            }
        }
        return probabilities;
    }
    private void depositPheromones() {
        for (Ant ant : ants) {
            List<Customer> tour = ant.getTour();
            double tourLength = calculateTourLength(tour);
            double pheromoneDeposit = 1.0 / tourLength;
            for (int i = 0; i < tour.size() - 1; i++) {
                int from = tour.get(i).getId();
                int to = tour.get(i + 1).getId();
                pheromoneMatrix[from][to] += pheromoneDeposit;
                pheromoneMatrix[to][from] += pheromoneDeposit;
            }
        }
    }
    private double calculateTourLength(List<Customer> tour) {
        double length = 0.0;
        for (int i = 0; i < tour.size() - 1; i++) {
            int from = tour.get(i).getId();
            int to = tour.get(i + 1).getId();
            length += distanceMatrix[from][to];
        }
        return length;
    }
    private int selectNextLocation(int currentLocation, boolean[] visited) {
        double[] probabilities = calculateProbabilities(currentLocation, visited);
        double randomValue = Math.random();
        double cumulativeProbability = 0.0;
        for (int i = 0; i < probabilities.length; i++) {
            cumulativeProbability += probabilities[i];
            if (randomValue <= cumulativeProbability && !visited[i]) {
                return i;
            }
        }
        for (int i = 0; i < visited.length; i++) {
            if (!visited[i]) {
                return i;
            }
        }
        return -1;
    }
    private Customer getCustomerById(int customerId) {
        for (Ant ant : ants) {
            for (Customer customer : ant.getTour()) {
                if (customer.getId() == customerId) {
                    return customer;
                }
            }
        }
        throw new IllegalArgumentException("Customer with ID " + customerId + " not found.");
    }
}