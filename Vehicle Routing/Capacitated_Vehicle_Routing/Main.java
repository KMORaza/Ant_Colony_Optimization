package Capacitated_Vehicle_Routing;
// Created: September 2022
public class Main {
    public static void main(String[] args) {
        int numCities = 5;
        double[][] distances = {
                {0, 10, 15, 20, 25},
                {10, 0, 35, 25, 30},
                {15, 35, 0, 30, 10},
                {20, 25, 30, 0, 5},
                {25, 30, 10, 5, 0}
        };
        Graph graph = new Graph(numCities, distances);
        int numAnts = 10;
        double alpha = 1.0;
        double beta = 2.0;
        double evaporationRate = 0.5;
        AntColony antColony = new AntColony(numAnts, alpha, beta, evaporationRate, graph);
        antColony.runACO(100);
        Ant bestAnt = getBestAnt(antColony);
        if (bestAnt != null) {
            System.out.println("Best Tour: " + bestAnt.getTour());
            System.out.println("Best Tour Length: " + bestAnt.getTourLength());
        } else {
            System.out.println("No solution found.");
        }
    }
    private static Ant getBestAnt(AntColony antColony) {
        Ant bestAnt = null;
        double bestTourLength = Double.MAX_VALUE;
        for (Ant ant : antColony.getAnts()) {
            if (ant.getTourLength() < bestTourLength) {
                bestAnt = ant;
                bestTourLength = ant.getTourLength();
            }
        }
        return bestAnt;
    }
}