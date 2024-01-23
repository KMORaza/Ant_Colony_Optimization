package Time_Dependent_Vehicle_Routing;
// Created: September 2022
import java.util.List;
import java.util.ArrayList;
public class TDVRPACO {
    private AntColony antColony;
    private int numberOfAnts = 10;
    private int maxIterations = 100;
    private TDVRPInstance tdvrpInstance;
    public void initialize() {
        List<Coordinate> coordinates = initializeCoordinates();
        tdvrpInstance = new TDVRPInstance(coordinates);
        antColony = new AntColony(numberOfAnts, maxIterations, tdvrpInstance);
    }
    private List<Coordinate> initializeCoordinates() {
        List<Coordinate> coordinates = new ArrayList<>();
        coordinates.add(new Coordinate(1, 2));
        coordinates.add(new Coordinate(3, 4));
        return coordinates;
    }
    public void runACO() {
        for (int iteration = 0; iteration < maxIterations; iteration++) {
            antColony.run();
        }
    }
    public void printSolution() {
        List<Ant> ants = antColony.getAnts();
        for (Ant ant : ants) {
            List<Integer> tour = ant.getTour();
            System.out.println("Ant Tour: " + tour);
        }
    }
}