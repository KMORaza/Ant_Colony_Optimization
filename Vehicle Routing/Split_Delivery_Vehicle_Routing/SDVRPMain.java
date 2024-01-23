package Split_Delivery_Vehicle_Routing;
// Created: September 2022
public class Main {
    public static void main(String[] args) {
        Node[] nodes = {
                new Node(0, 0.0, 0.0),
                new Node(1, 1.0, 2.0),
                new Node(2, 2.0, 1.0),
                new Node(3, 2.0, 3.0),
                new Node(4, 3.0, 0.0),
        };
        Vehicle[] vehicles = {
                new Vehicle(10),
        };
        int numAnts = 5;
        int maxIterations = 100;
        Graph graph = new Graph(nodes);
        Optimizer aco = new Optimizer(graph, numAnts);
        aco.runACO(maxIterations);
        int[] bestTour = getBestTour(aco);
        printTour(bestTour);
    }
    private static int[] getBestTour(Optimizer aco) {
        Ant[] ants = aco.getAnts();
        double bestTourLength = Double.MAX_VALUE;
        int[] bestTour = null;
        for (Ant ant : ants) {
            int[] tour = ant.getTour();
            double tourLength = calculateTourLength(aco.getGraph(), tour);
            if (tourLength < bestTourLength) {
                bestTourLength = tourLength;
                bestTour = tour.clone();
            }
        }
        return bestTour;
    }
    private static double calculateTourLength(Graph graph, int[] tour) {
        double tourLength = 0;
        for (int i = 0; i < tour.length - 1; i++) {
            int currentNode = tour[i];
            int nextNode = tour[i + 1];
            tourLength += graph.getDistance(currentNode, nextNode);
        }
        return tourLength;
    }
    private static void printTour(int[] tour) {
        System.out.println("Best Tour:");
        for (int node : tour) {
            System.out.print(node + " ");
        }
        System.out.println();
    }
}
