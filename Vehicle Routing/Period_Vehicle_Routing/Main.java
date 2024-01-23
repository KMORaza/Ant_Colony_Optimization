package Period_Vehicle_Routing;
// Created: September 2022
import java.util.ArrayList;
import java.util.List;
public class Main {
    public static void main(String[] args) {
        Location location1 = new Location(1, 0.0, 0.0);
        Location location2 = new Location(2, 1.0, 1.0);
        Graph graph = new Graph();
        graph.addLocation(location1);
        graph.addLocation(location2);
        int numLocations = graph.getLocations().size();
        List<Ant> ants = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            ants.add(new Ant(numLocations));
        }
        double alpha = 1.0;
        double beta = 2.0;
        double evaporationRate = 0.5;
        double Q = 100.0;
        int numAnts = 5;
        int numIterations = 100;
        Optimization aco = new Optimization(graph, ants, alpha, beta, evaporationRate, Q, numAnts, numIterations);
        aco.solve();
    }
}