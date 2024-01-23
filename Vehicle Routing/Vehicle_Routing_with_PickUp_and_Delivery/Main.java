package Vehicle_Routing_with_PickUp_and_Delivery;
// Created: September 2022
import java.util.List;
public class Main {
    public static void main(String[] args) {
        Graph graph = new Graph();
        Node node1 = new Node(0, 1.0, 2.0);
        Node node2 = new Node(1, 3.0, 4.0);
        graph.addNode(node1);
        graph.addNode(node2);
        int numAnts = 10;
        double alpha = 1.0;
        double beta = 2.0;
        double evaporationRate = 0.5;
        AntColony antColony = new AntColony(graph, numAnts, alpha, beta, evaporationRate);
        int numIterations = 100;
        antColony.runACO(numIterations);
        List<Integer> bestTour = antColony.getBestTour();
        double bestTourLength = antColony.getBestTourLength();
        System.out.println("Best Tour: " + bestTour);
        System.out.println("Best Tour Length: " + bestTourLength);
    }
}