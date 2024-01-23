package Time_Dependent_Vehicle_Routing;
// Created: September 2022
import java.util.ArrayList;
import java.util.List;
public class AntColony {
    private List<Ant> ants;
    private int maxIterations;
    private TDVRPInstance tdvrpInstance;
    private PheromoneMatrix pheromoneMatrix;
    private List<List<Integer>> distanceMatrix;
    public AntColony(int numberOfAnts, int maxIterations, TDVRPInstance tdvrpInstance) {
        this.maxIterations = maxIterations;
        this.tdvrpInstance = tdvrpInstance;
        this.pheromoneMatrix = new PheromoneMatrix(tdvrpInstance.getNumberOfLocations());
        this.distanceMatrix = convertArrayToList(tdvrpInstance.getDistances());
        initializeAnts(numberOfAnts);
    }
    private List<List<Integer>> convertArrayToList(int[][] distances) {
        List<List<Integer>> result = new ArrayList<>();
        for (int[] row : distances) {
            List<Integer> listRow = new ArrayList<>();
            for (int value : row) {
                listRow.add(value);
            }
            result.add(listRow);
        }
        return result;
    }
    private void initializeAnts(int numberOfAnts) {
        ants = new ArrayList<>();
        for (int i = 0; i < numberOfAnts; i++) {
            ants.add(new Ant(tdvrpInstance.getNumberOfLocations()));
        }
    }
    public void run() {
        for (int iteration = 0; iteration < maxIterations; iteration++) {
            for (Ant ant : ants) {
                ant.constructTour(pheromoneMatrix, distanceMatrix, tdvrpInstance);
                updatePheromones(ant);
            }
            evaporatePheromones();
        }
    }
    private void updatePheromones(Ant ant) {
        List<Integer> tour = ant.getTour();
        double tourLength = calculateTourLength(tour);
        double pheromoneDelta = 1.0 / tourLength;
        for (int i = 0; i < tour.size() - 1; i++) {
            int from = tour.get(i);
            int to = tour.get(i + 1);
            pheromoneMatrix.updatePheromone(from, to, pheromoneDelta);
        }
    }
    private void evaporatePheromones() {
        pheromoneMatrix.evaporate();
    }
    private double calculateTourLength(List<Integer> tour) {
        double tourLength = 0.0;
        for (int i = 0; i < tour.size() - 1; i++) {
            int from = tour.get(i);
            int to = tour.get(i + 1);
            double distance = distanceMatrix.get(from).get(to);
            tourLength += distance;
        }
        return tourLength;
    }
    public List<Ant> getAnts() {
        return ants;
    }
}